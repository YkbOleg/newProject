package photoSorting.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import photoSorting.dto.ResultDto;
import photoSorting.service.PhotoSortingService;

import java.io.File;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class InputController {

    @Value("${spring.application.name}")
    private String application;

    private final PhotoSortingService photoSortingService;

    @SneakyThrows
    @PostMapping("/sorting")
    public ResultDto sorting(@RequestParam("catalogStart") String startLocation
            ,@RequestParam("catalogFinish") String finishLocation){
        photoSortingService.fileSort(photoSortingService.fileSearch(new File(startLocation)), new File(finishLocation), new File(startLocation));
        return new ResultDto(application, "Готово!");
    }
}

