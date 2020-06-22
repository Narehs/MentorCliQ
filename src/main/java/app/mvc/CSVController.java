package app.mvc;

import app.exception.EmptyFileException;
import app.service.FileService;
import app.service.MatcherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class CSVController {

    private final FileService fileService;

    private final MatcherService matcherService;

    CSVController(MatcherService matcherService, FileService fileService) {
        this.matcherService = matcherService;
        this.fileService = fileService;
    }

    @GetMapping("/")
    public String index() {
        return "uploader";
    }

    @PostMapping("/uploader")
    public ModelAndView uploadCSVFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttr) {
        if (file.isEmpty()) {
         throw new EmptyFileException("File is empty");
        } else {
            fileService.storeFile(file);
            redirectAttr.addFlashAttribute("status", true);
        }
        return new ModelAndView("redirect:" + "/getFile");
    }


    @GetMapping("/getFile")
    public String getFile(Model model) {
        Map<String, Object> pa = matcherService.getMatches();
        model.addAttribute("employees", pa.get("employees"));
        model.addAttribute("sets", pa.get("all"));
        model.addAttribute("maxSet", pa.get("maxSet"));
        model.addAttribute("index", pa.get("maxIndex"));
        return "employees";
    }
}
