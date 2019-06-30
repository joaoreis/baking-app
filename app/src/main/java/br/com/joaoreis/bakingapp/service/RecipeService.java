package br.com.joaoreis.bakingapp.service;


import java.util.List;

import br.com.joaoreis.bakingapp.service.models.Recipe;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RecipeService implements RecipesAPI {

    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net";
    private RecipesAPI recipesAPI;

    public RecipeService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        recipesAPI = retrofit.create(RecipesAPI.class);

    }

    @Override
    public Call<List<Recipe>> getRecipes() {
        return recipesAPI.getRecipes();
    }
}
