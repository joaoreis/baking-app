package br.com.joaoreis.bakingapp.recipes.viewmodel;

import androidx.lifecycle.ViewModel;

import br.com.joaoreis.bakingapp.service.models.Recipe;

public class RecipeDetailViewModel extends ViewModel {

    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
