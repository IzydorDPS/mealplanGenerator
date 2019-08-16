package pl.dpis.mealplangenerator3.Controllers;

import org.springframework.stereotype.Service;
import pl.dpis.mealplangenerator3.generTests.src.FoodItem;
import pl.dpis.mealplangenerator3.generTests.src.MealPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MealGenerator {
    private Random random = new Random();
    private List<FoodItem> listOfFoods = new ArrayList<>();

    private MealGenerator() {
        for (int i = 0; i < 10000; i++) {
            listOfFoods.add(new FoodItem("food" + i, random.nextFloat() * 50, random.nextFloat() * 50, random.nextFloat() * 50, random.nextFloat() * 100));
        }
    }
    public MealPlan generateMealForDay() {
       return generateMealForDay( 2000);
    }

    public MealPlan generateMealForDay(int calories) {
        //Nutritional Requirements
        int targetProtein = 137;
        int targetFat = 94;
        int targerCarbs = 120;
        int targetCalories = calories;
        int mealQuantity = 3;//todo

        int accuracy = 5;

        int reject = 0;
        int trys = 0;

        long timeStart = System.currentTimeMillis();
        MealPlan todayMealplan = null;
        todayMealplan = new MealPlan(mealQuantity,targetProtein, targetFat, targerCarbs, targetCalories, accuracy);

        do {
            if (trys % 200 == 0) {
                todayMealplan = new MealPlan(targetProtein, targetFat, targerCarbs, targetCalories, accuracy);
            }
            //select random food
            FoodItem food = listOfFoods.get((random.nextInt(listOfFoods.size())));

            if (todayMealplan.isFoodFit(food)) {
                todayMealplan.addFood(food);
            } else {
                reject++;
            }
            if (reject > 3) {
                reject = 0;
                todayMealplan.removeHighestNutrientFood();

            }

            trys++;
        } while (!todayMealplan.isNutrientsMet());
        return todayMealplan;
    }
}
