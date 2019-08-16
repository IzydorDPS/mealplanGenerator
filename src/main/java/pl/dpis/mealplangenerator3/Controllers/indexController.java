package pl.dpis.mealplangenerator3.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class indexController {

    @Autowired
    private MealGeneratorApi mealGenerator;

    @GetMapping("/test")
    public String index() {
        return "test";
    }

    @GetMapping("/generator")
    public String generator(Model model) {
        model.addAttribute("meal", mealGenerator.generate());
        return "generator";
    }

}
