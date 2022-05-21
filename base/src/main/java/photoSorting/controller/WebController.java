package photoSorting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import photoSorting.repository.PhotoRepository;
import photoSorting.service.PhotoService;

import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebController {


    private final PhotoService photoService;
    private final PhotoRepository photoRepository;

    @RequestMapping("/photoSorting")
    public String photoSorting(Model model) {
        model.addAttribute("findYears", photoRepository.findYears());
        return "photoSorting/photoSorting";
    }

    @GetMapping(value = "/tableView")
    public String tableView(Model model,
                            @Valid Integer year) {
        if (Objects.equals(year, null)){
            model.addAttribute("allPhoto", photoService.allPhoto());
        }
        else {
            model.addAttribute("allPhoto", photoService.findYear(year));
        }
        return "photoSorting/tableView";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam("photoId") Long photoId,
                         @RequestParam("action") String action,
                         @RequestParam("year") String year) {
        if(Objects.equals(action, "delete")){
            photoService.deleteById(photoId);
        }
        return "redirect:/tableView?year=" + year.substring(0, 4);
    }

}
