
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

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {


        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        public Ingredient[] newArray(int size) {
            return (new Ingredient[size]);
        }

    };

    private Ingredient(Parcel in) {
        this.quantity = in.readInt();
        this.measure = in.readString();
        this.name = in.readString();
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(quantity);
        dest.writeValue(measure);
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }

}
