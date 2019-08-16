package pl.dpis.mealplangenerator3.generTests.src;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {
    static Random random = new Random();
    ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        List<FoodItem> listOfFoods = new ArrayList<>();

        fillUpList(listOfFoods);

        //Nutritional Requirements
        int targetProtein = 137;
        int targetFat = 94;
        int targerCarbs = 120;
        int targetCalories = 2000;

        int mealQuantity = 3;

        int accuracy = 5;

        int reject = 0;
        int trys = 0;


        long timeStart = System.currentTimeMillis();
        MealPlan todayMealplan = null;
        //133_870 ms
        //85_864 ms

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

        long duration = System.currentTimeMillis() - timeStart;
        System.out.println(duration + " ms\ttrys:" + trys + "\n");
        System.out.println("targetProtein: " + targetProtein + "\n" +
                "targetFat: " + targetFat + "\n" +
                "targerCarbs: " + targerCarbs + "\n" +
                "targetCalories: " + targetCalories);

        System.out.println("\n" + todayMealplan.toString());
        todayMealplan.printFood();

        todayMealplan.regenerateFoodAtIndex(4, listOfFoods);
         duration = System.currentTimeMillis() - timeStart;
        System.out.println(duration + " ms\ttrys:" + trys + "\n");
        System.out.println("\n" + todayMealplan.toString());
        todayMealplan.printFood();
    }

    private static int getUserInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        return input.nextInt();
    }

    private static void fillUpList(List listOfFoods) {
        for (int i = 0; i < 10000; i++) {
            listOfFoods.add(new FoodItem("food" + i, random.nextFloat() * 50, random.nextFloat() * 50, random.nextFloat() * 50, random.nextFloat() * 100));
        }
    }
}
