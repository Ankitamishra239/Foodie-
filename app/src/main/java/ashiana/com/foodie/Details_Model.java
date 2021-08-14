package ashiana.com.foodie;

import androidx.recyclerview.widget.RecyclerView;

public class Details_Model {


    private String Calories, Protein, Fat, Carbs, Fibre, name, Ingredient1, Ingredient2, Ingredient3, Food_Image, Calories_from_fat, serving_size;

    public Details_Model() {
        // for firebase
    }

    public Details_Model(String calories, String protein, String fat, String carbs, String fibre, String name, String ingredient1, String ingredient2, String ingredient3, String food_Image, String calories_from_fat, String serving_size) {
        Calories = calories;
        Protein = protein;
        Fat = fat;
        Carbs = carbs;
        Fibre = fibre;
        this.name = name;
        Ingredient1 = ingredient1;
        Ingredient2 = ingredient2;
        Ingredient3 = ingredient3;
        Food_Image = food_Image;
        Calories_from_fat = calories_from_fat;
        this.serving_size = serving_size;
    }

    public String getCalories() {
        return Calories;
    }

    public void setCalories(String calories) {
        Calories = calories;
    }

    public String getProtein() {
        return Protein;
    }

    public void setProtein(String protein) {
        Protein = protein;
    }

    public String getFat() {
        return Fat;
    }

    public void setFat(String fat) {
        Fat = fat;
    }

    public String getCarbs() {
        return Carbs;
    }

    public void setCarbs(String carbs) {
        Carbs = carbs;
    }

    public String getFibre() {
        return Fibre;
    }

    public void setFibre(String fibre) {
        Fibre = fibre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredient1() {
        return Ingredient1;
    }

    public void setIngredient1(String ingredient1) {
        Ingredient1 = ingredient1;
    }

    public String getIngredient2() {
        return Ingredient2;
    }

    public void setIngredient2(String ingredient2) {
        Ingredient2 = ingredient2;
    }

    public String getIngredient3() {
        return Ingredient3;
    }

    public void setIngredient3(String ingredient3) {
        Ingredient3 = ingredient3;
    }

    public String getFood_Image() {
        return Food_Image;
    }

    public void setFood_Image(String food_Image) {
        Food_Image = food_Image;
    }

    public String getCalories_from_fat() {
        return Calories_from_fat;
    }

    public void setCalories_from_fat(String calories_from_fat) {
        Calories_from_fat = calories_from_fat;
    }

    public String getServing_size() {
        return serving_size;
    }

    public void setServing_size(String serving_size) {
        this.serving_size = serving_size;
    }
}