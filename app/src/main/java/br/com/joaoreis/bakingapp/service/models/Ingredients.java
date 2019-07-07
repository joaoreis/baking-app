package br.com.joaoreis.bakingapp.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Ingredients implements Parcelable {
    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
    List<Ingredient> ingredients;

    public Ingredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    protected Ingredients(Parcel in) {
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(ingredients);
    }
}
