package photoSorting.service;

import java.io.File;
import java.util.ArrayList;

public interface PhotoSortingService {

    ArrayList<File> fileSearch(File startLocationPhoto);
    void fileSort(ArrayList<File> arrayListFile, File finishLocationPhoto, File startLocationPhoto) throws Exception;
    void deleteFile(Long photoId);
}
