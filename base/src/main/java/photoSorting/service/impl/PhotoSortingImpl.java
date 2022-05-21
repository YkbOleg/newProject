package photoSorting.service.impl;


import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import photoSorting.model.Photo;
import photoSorting.repository.PhotoRepository;
import photoSorting.service.PhotoSortingService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Service
public class PhotoSortingImpl implements PhotoSortingService {

    private final PhotoRepository photoRepository;

    public PhotoSortingImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    /**
     * Формирования списка всех файлов в каталоге
     * @param startLocationPhoto - каталог с файлами
     * @return Список файлов из каталога
     */
    @Override
    public ArrayList<File> fileSearch(File startLocationPhoto) {
        ArrayList<File> lstFileResult = new ArrayList<>();

        for (File fl : Arrays.asList(startLocationPhoto.listFiles())) {
            if(fl.isFile()){
                lstFileResult.add(fl);
            }
            else{
                lstFileResult.addAll(fileSearch(fl));
            }
        }
        return lstFileResult;
    }

    /**
     * Удаление пустых каталогов
     * @param file - Каталог подлежащий отчистки
     */
    private void deleteDirectoryRecursive(File file) {
        for (File f : file.listFiles()) {
            if (f.isDirectory() && f.listFiles().length == 0) {
                f.delete();
            }

            if (f.isDirectory() && f.listFiles().length > 0) {
                deleteDirectoryRecursive(f);
                if (f.isDirectory() && f.listFiles().length == 0) {
                    f.delete();
                }
            }
        }
    }

    /**
     * Удаление файла
     * @param photoId - Id файла для удаления
     */
    @Override
    public void deleteFile(Long photoId){
        Photo file = photoRepository.findById(photoId).get();
        File locationFile = new File(file.getLocation() + "/" + file.getName());
        locationFile.delete();
    }

    /**
     * Сортировка файлов по каталогам
     * @param arrayListFile - Список файлов для сортировки
     * @param finishLocationPhoto - Каталог для размещения отсортированных файлов
     * @param startLocationPhoto - Каталог с файлами для сортировки
     * @throws Exception
     */
    @Override
    public void fileSort(ArrayList<File> arrayListFile
            , File finishLocationPhoto
            , File startLocationPhoto) throws Exception{
        //log.info("Список файлов" + arrayListFile);
        String fileLocation = finishLocationPhoto.getPath();
        File chaosLocation = new File(fileLocation + "/chaos");
        if(!chaosLocation.exists()){
            if(!chaosLocation.mkdir()){
                log.info("Каталог {} не создан", chaosLocation);
            }
            else{
                log.info("Каталог {} создан", chaosLocation);
            }
        }
        else{
            log.info("Католог уже создан");
        }

        for (File fl : arrayListFile) {
            Metadata metadata = ImageMetadataReader.readMetadata(fl);
            File finishFileLocation;
            Photo photo = new Photo();

            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    if(Objects.equals(tag.getTagName(), "Date/Time Original")){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                        String strDate = tag.getDescription();
                        photo.setCreate(dateFormat.parse(strDate));
                        String dataCreateFile = tag.getDescription().substring(0, tag.getDescription().indexOf(" "));
                        String[] createDir = dataCreateFile.split(":");
                        String folderMonth;
                        switch (createDir[1]) {
                            case "01": folderMonth = "Январь";break;
                            case "02": folderMonth = "Февраль";break;
                            case "03": folderMonth = "Март";break;
                            case "04": folderMonth = "Апрель";break;
                            case "05": folderMonth = "Май";break;
                            case "06": folderMonth = "Июнь";break;
                            case "07": folderMonth = "Июль";break;
                            case "08": folderMonth = "Август";break;
                            case "09": folderMonth = "Сентябрь";break;
                            case "10": folderMonth = "Октябрь";break;
                            case "11": folderMonth = "Ноябрь";break;
                            case "12": folderMonth = "Декабрь";break;
                            default: folderMonth = "";
                        }

                        String year = createDir[0] + "_год";
                        String month = createDir[0] + "." + createDir[1] + "_" + folderMonth;
                        String day = createDir[2];
                        System.out.println(year);
                        System.out.println(month);
                        System.out.println(day);

                        finishFileLocation = new File(fileLocation + "/" + year + "/" + month);
                        photo.setLocation(finishFileLocation.getAbsolutePath());
                        if (!finishFileLocation.mkdirs()) {
                            log.info("Каталог {} не создан", finishFileLocation );
                        }
                    }
                    if(Objects.equals(tag.getTagName(), "File Name")){
                        photo.setName(tag.getDescription());
                    }
                    if(Objects.equals(tag.getTagName(), "File Size")){
                        photo.setSize(Long.parseLong(tag.getDescription().replaceAll("[^0-9]", "")));
                    }
                }
                if (directory.hasErrors()) {    //Проверка ошибок
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s", error);
                    }
                }
            }

            if(!Objects.equals(photo.getCreate(), null)
                    && !Objects.equals(photo.getName(), null)
                    && !Objects.equals(photo.getSize(), null)
                    && !Objects.equals(photo.getLocation(), null)){
                String md5Hex = DigestUtils.md5Hex(photo.getCreate().toString() + photo.getSize() + photo.getName());
                photo.setHash(md5Hex);
                if(!photoRepository.existsIfHash(photo.getHash())){
                    photoRepository.save(photo);
                    fl.renameTo(new File(new File(photo.getLocation()), fl.getName()));
                }
                else{
                    fl.renameTo(new File(chaosLocation, fl.getName()));
                }
            }
            else{
                log.info("Файл {} не добавлен в БД (нет одного или нескольких обязательных параметров)!", fl.getName());
                log.info("Файл {} перенесён в каталог: {}", fl.getName(), chaosLocation.getAbsolutePath());
                fl.renameTo(new File(chaosLocation, fl.getName()));
            }
        }
        deleteDirectoryRecursive(startLocationPhoto);
    }
}
