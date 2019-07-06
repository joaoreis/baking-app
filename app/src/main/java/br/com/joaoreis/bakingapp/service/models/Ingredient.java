
package br.com.joaoreis.bakingapp.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Ingredient implements Parcelable {

    @Json(name = "quantity")
    private double quantity;

    @Json(name = "measure")
    private String measure;

    @Json(name = "name")
    private String name;


    protected Ingredient(Parcel in) {
        quantity = in.readDouble();
        measure = in.readString();
        name = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(quantity);
        dest.writeString(measure);
        dest.writeString(name);
    }
}
