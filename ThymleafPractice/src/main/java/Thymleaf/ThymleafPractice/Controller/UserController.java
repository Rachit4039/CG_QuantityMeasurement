package Thymleaf.ThymleafPractice.Controller;

import Thymleaf.ThymleafPractice.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    //variable expression
   @GetMapping("/varExpression")
    public String var(Model model){
        User u = new User("Rachit","rachit@efd","SE","male");
        model.addAttribute("u",u);
        return "variable-Expression";
    }

    //selection expression
    @GetMapping("/selExpression")
    public String selExpr(Model model){
        User u = new User("Rachit2","rachit@2","SE2","male");
        model.addAttribute("u",u);
        return "selection-expression";
    }

    //message expression
    @GetMapping("/messExpression")
    public String messExpr(Model model){
        User u = new User("Rachit3","rachit@3","SE3","male");

        return "message-expression";
    }

    //link expression
    @GetMapping("/linkExpression")
    public String linkExpr(Model model){
        User u = new User("Rachit4","rachit@4","SE4","male");

        return "link-expression";
    }


    //fragement expression
    @GetMapping("/fragExpression")
    public String fragExpr(Model model){


        return "fragment-expression";
    }


    @GetMapping("/users")
    public String users(Model model){
        User u1 = new User("Rachit","rachit@efd","SE","male");
        User u2 = new User("Rachit2","rachit@efd2","SE2","male");
        User u3 = new User("Rachit3","rachit@efd3","SE3","male");
        List<User>a = new ArrayList<>();
        a.add(u1);
        a.add(u2);
        a.add(u3);
        model.addAttribute("a",a);
        return "users";
    }

    @GetMapping("ifUnless")
    public String ifUnless(Model model){
        User u1 = new User("Rachit","rachit@efd","SE","male");
        User u2 = new User("Rachit2","rachit@efd2","SE2","male");
        User u3 = new User("Rachit3","rachit@efd3","SE3","male");
        List<User>a = new ArrayList<>();
        a.add(u1);
        a.add(u2);
        a.add(u3);
        model.addAttribute("a",a);
        return "if-unless";
    }

    @GetMapping("switchcase")
    public String switchCase(Model model) {
        User u1 = new User("Rachit", "rachit@efd", "Admin", "male");


        model.addAttribute("user", u1);
        return "switch-case";
    }
}
