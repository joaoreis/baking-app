
package br.com.joaoreis.bakingapp.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    @Json(name = "id")
    private int id;

    @Json(name = "name")
    private String name;

    @Json(name = "ingredients")
    private List<Ingredient> ingredients = new ArrayList<>();

    @Json(name = "steps")
    private List<Step> steps = new ArrayList<>();

    @Json(name = "servings")
    private int servings;

    @Json(name = "image")
    private String image;
    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {


        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        public Recipe[] newArray(int size) {
            return (new Recipe[size]);
        }

    };

    private Recipe(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        in.readList(this.ingredients, Ingredient.class.getClassLoader());
        in.readList(this.steps, Step.class.getClassLoader());
        this.servings = in.readInt();
        this.image = in.readString();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeList(ingredients);
        dest.writeList(steps);
        dest.writeValue(servings);
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }

}
