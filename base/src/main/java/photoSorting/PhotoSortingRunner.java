package photoSorting;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import photoSorting.repository.PhotoRepository;
import photoSorting.service.PhotoService;
import photoSorting.service.PhotoSortingService;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

@Slf4j
@SpringBootApplication
public class PhotoSortingRunner {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(PhotoSortingRunner.class, args);

//        final ConfigurableApplicationContext appContext = SpringApplication.run(PhotoSortingRunner.class, args);
//        final PhotoSortingService photoSortingService = appContext.getBean(PhotoSortingService.class);
//        final PhotoService photoService = appContext.getBean(PhotoService.class);
//        final PhotoRepository photoRepository = appContext.getBean(PhotoRepository.class);
//
//        log.info("Список по гадам:" + photoRepository.findYears());
//
//        log.info("" + photoService.allPhoto());
//        photoSortingService.fileSort(photoSortingService.fileSearch(new File("C:\\TestJavaFiles\\photoSorted\\Start"))
//                , new File("C:\\TestJavaFiles\\photoSorted\\Finish")
//                , new File("C:\\TestJavaFiles\\photoSorted\\Start"));
    }
}
