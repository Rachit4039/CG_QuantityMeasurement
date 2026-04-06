package Thymleaf.ThymleafPractice.Controller;

import Thymleaf.ThymleafPractice.Model.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class FormController {


    private List<UserForm> userList = new ArrayList<>();


    @GetMapping("/add")
    public String userRegister(Model model) {

        UserForm userForm = new UserForm();


        model.addAttribute("userForm", userForm);

        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);

        model.addAttribute("users", userList);

        return "register-form";
    }
}