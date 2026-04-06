package Thymleaf.ThymleafPractice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HelloWorldController {
    @GetMapping("/hello-world")
    public String helloWorld(Model model){
        model.addAttribute("message","Hellooooo world");
        return "hello-world";
    }
}
