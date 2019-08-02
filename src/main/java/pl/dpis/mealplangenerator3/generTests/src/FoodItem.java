public class FoodItem implements Comparable{
    private final String name;
    private final float protein;
    private final float fat;
    private final float carbs;
    private final float calories;
    private final int mass;

    FoodItem(String name, float protein, float fat, float carbs, float calories)  {
        this.name=name;
        this.protein=protein;
        this.fat=fat;
        this.carbs=carbs;
        this.calories=protein*4+fat*9+carbs*4+calories;
        this.mass=100;// in mg
    }

    public String getName() {
        return name;
    }

    public float getProtein() {
        return protein;
    }

    public float getFat() {
        return fat;
    }

    public float getCarbs() {
        return carbs;
    }

    public float getCalories() {
        return calories;
    }

    @Override
    public int compareTo(Object o) {
        return (int) calories;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\t" +"Protein: " + getProtein() + "\t" +
                "Fat: " + getFat() + "\t" +
                "Carbs: " + getCarbs() + "\t" +
                "Calories: " + getCalories();
    }
}
