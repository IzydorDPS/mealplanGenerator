package pl.dpis.mealplangenerator3.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    @GetMapping("/test")
    public String index() {
        return "test";
    }


}
