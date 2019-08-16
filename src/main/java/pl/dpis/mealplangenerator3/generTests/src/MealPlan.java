package pl.dpis.mealplangenerator3.generTests.src;

import pl.dpis.mealplangenerator3.generTests.src.FoodItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static pl.dpis.mealplangenerator3.generTests.src.main.random;

public class MealPlan implements Serializable {
    List<FoodItem> foodItemList;

    private float Calories;
    private float Fat;
    private float Carbs;
    private float Protein;


    private int mealQuantity;
    private float targetCalories;
    private float targetFat;
    private float targerCarbs;
    private float targetProtein;

    private float accuracy;

    public MealPlan(List<FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
    }

    public MealPlan() {
        this.foodItemList = new ArrayList<>();
    }

    public MealPlan(int mealQuantity, int targetProtein, int targetFat, int targerCarbs, int targetCalories, int accuracy) {
        this.targetCalories = targetCalories;
        this.targetFat = targetFat;
        this.targerCarbs = targerCarbs;
        this.targetProtein = targetProtein;
        this.accuracy = accuracy;
        this.mealQuantity = mealQuantity;


        this.foodItemList = new ArrayList<>();
    }

    public MealPlan(int targetProtein, int targetFat, int targerCarbs, int targetCalories, int accuracy) {
        this.targetCalories = targetCalories;
        this.targetFat = targetFat;
        this.targerCarbs = targerCarbs;
        this.targetProtein = targetProtein;
        this.accuracy = accuracy;


        this.foodItemList = new ArrayList<>();
    }

    public List<FoodItem> getFoodItemList() {
        return foodItemList;
    }

    public float getProtein() {

        return this.Protein;
    }

    public float getFat() {
        return this.Fat;
    }

    public float getCarbs() {
        return this.Carbs;
    }

    public float getCalories() {
        return this.Calories;
    }

    public void addFood(FoodItem food) {
        foodItemList.add(food);

        this.Calories = foodItemList.stream().map(FoodItem::getCalories).reduce(0.0f, Float::sum);
        this.Fat = foodItemList.stream().map(FoodItem::getFat).reduce(0.0f, Float::sum);
        this.Carbs = foodItemList.stream().map(FoodItem::getCarbs).reduce(0.0f, Float::sum);
        this.Protein = foodItemList.stream().map(FoodItem::getProtein).reduce(0.0f, Float::sum);
    }

    public void removeFood(FoodItem food) {
        foodItemList.remove(food);

        this.Calories = foodItemList.stream().map(FoodItem::getCalories).reduce(0.0f, Float::sum);
        this.Fat = foodItemList.stream().map(FoodItem::getFat).reduce(0.0f, Float::sum);
        this.Carbs = foodItemList.stream().map(FoodItem::getCarbs).reduce(0.0f, Float::sum);
        this.Protein = foodItemList.stream().map(FoodItem::getProtein).reduce(0.0f, Float::sum);
    }

    @Override
    public String toString() {
        return "Protein: " + getProtein() + "\n" +
                "Fat: " + getFat() + "\n" +
                "Carbs: " + getCarbs() + "\n" +
                "Calories: " + getCalories();
    }

    public boolean isNutrientsMet() {


//        if ((Math.abs((targetCalories - getCalories())) / targetCalories * 100) <= accuracy) {
//            return true;
//        }

        if ((Math.abs((targetCalories - Calories)) / targetCalories * 100) <= accuracy) {
            if ((Math.abs((targetProtein - Protein)) / targetProtein * 100) <= accuracy) {
                if ((Math.abs((targetFat - Fat)) / targetFat * 100) <= accuracy * 2) {
                    if ((Math.abs((targerCarbs - Carbs)) / targerCarbs * 100) <= accuracy * 2) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isFoodFit(FoodItem food) {

        //       double val=(Math.abs(food.getCalories()-(targetCalories-getCalories())))/ targetCalories * 100;
        //       boolean x =((Math.abs(food.getCalories()-(targetCalories-getCalories())))/ targetCalories * 100)<= accuracy;

        if (food.getCalories() < (targetCalories - getCalories()) || ((Math.abs(food.getCalories() - (targetCalories - getCalories()))) / targetCalories * 100) <= accuracy) {
            if (food.getProtein() < (targetProtein - getProtein()) || ((Math.abs(food.getProtein() - (targetProtein - getProtein()))) / targetProtein * 100) <= accuracy) {
                if (food.getFat() < (targetFat - getFat()) || ((Math.abs(food.getFat() - (targetFat - getFat()))) / targetFat * 100) <= accuracy) {
                    if (food.getCarbs() < (targerCarbs - getCarbs()) || ((Math.abs(food.getCarbs() - (targerCarbs - getCarbs()))) / targerCarbs * 100) <= accuracy) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void removeHighestNutrientFood() {


        HashMap<String, Float> map = new HashMap<>();
        //    map.put("distanceCalories", Math.abs(targetCalories - getCalories()));
        map.put("distanceProtein", Math.abs(targetProtein - getProtein()));
        map.put("distanceFat", Math.abs(targetFat - getFat()));
        map.put("distanceCarbs", Math.abs(targerCarbs - getCarbs()));

        String key = Collections.max(map.entrySet(), (entry1, entry2) -> (int) (entry1.getValue() - entry2.getValue())).getKey();

        FoodItem food;
        switch (key) {
            case "distanceCalories":
                food = Collections.max(getFoodItemList(), (entry1, entry2) -> (int) (entry1.getCalories() - entry2.getCalories()));
                getFoodItemList().remove(food);
                break;
            case "distanceProtein":
                food = Collections.max(getFoodItemList(), (entry1, entry2) -> (int) (entry1.getProtein() - entry2.getProtein()));
                removeFood(food);
                break;
            case "distanceFat":
                food = Collections.max(getFoodItemList(), (entry1, entry2) -> (int) (entry1.getFat() - entry2.getFat()));
                removeFood(food);
                break;
            case "distanceCarbs":
                food = Collections.max(getFoodItemList(), (entry1, entry2) -> (int) (entry1.getCarbs() - entry2.getCarbs()));
                removeFood(food);
                break;

        }


    }

    public void printFood() {
        int id = 0;
        for (FoodItem f : foodItemList) {
            System.out.println("ID:" + id + "\t" + f.toString());
            id++;
        }
    }

    public void regenerateFoodAtIndex(int userInput, List<FoodItem> listOfFoods) {
        this.removeFood(foodItemList.get(userInput));
        long timeStart = System.currentTimeMillis();
        FoodItem food;
        do {
            if (System.currentTimeMillis() - timeStart > 1000) {
                System.out.println("Nie znaleziono potrawy w wyznaczonym czasie");
                break;
            }

            //select random food
            food = listOfFoods.get((random.nextInt(listOfFoods.size())));

            if (this.isFoodFit(food)) {
                this.addFood(food);
                if (!this.isNutrientsMet()) {
                    this.removeFood(food);
                }
            }
        } while (!this.isNutrientsMet());
    }
}
