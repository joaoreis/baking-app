package br.com.joaoreis.bakingapp.detail.viewmodel;

import androidx.lifecycle.ViewModel;

import br.com.joaoreis.bakingapp.service.models.Recipe;

public class RecipeMasterlViewModel extends ViewModel {

    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
