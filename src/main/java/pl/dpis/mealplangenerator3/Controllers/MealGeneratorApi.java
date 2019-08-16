package pl.dpis.mealplangenerator3.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dpis.mealplangenerator3.generTests.src.MealPlan;

@RestController
public class MealGeneratorApi {

    @Autowired
   private MealGenerator mealGenerator;

//    @RequestMapping("/generate")
//    public MealPlan generate(int calories) {
//        return mealGenerator.generateMealForDay(calories);
//    }
    @RequestMapping("/generate")
    public MealPlan generate() {
        return mealGenerator.generateMealForDay();
    }
}

