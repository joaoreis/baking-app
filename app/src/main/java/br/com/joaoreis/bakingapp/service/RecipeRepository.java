package br.com.joaoreis.bakingapp.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.joaoreis.bakingapp.service.models.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {

    private final RecipeService recipeService = new RecipeService();
    private final MutableLiveData<List<Recipe>> recipes = new MutableLiveData<>();


    public LiveData<List<Recipe>> getRecipes() {
        Call<List<Recipe>> call = recipeService.getRecipes();
        call.enqueue(new RecipeCallback());
        return recipes;
    }

    class RecipeCallback implements Callback<List<Recipe>> {

        @Override
        public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
            if (response.isSuccessful() && response.body() != null) {
                recipes.postValue(response.body());
            }
        }

        @Override
        public void onFailure(Call<List<Recipe>> call, Throwable t) {
            //TODO: add logging
        }
    }
}
