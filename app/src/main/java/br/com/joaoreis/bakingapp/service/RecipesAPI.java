package br.com.joaoreis.bakingapp.service;

import java.util.List;

import br.com.joaoreis.bakingapp.service.models.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesAPI {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipes();

}
