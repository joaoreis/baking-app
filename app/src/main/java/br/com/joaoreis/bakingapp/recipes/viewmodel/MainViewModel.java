package br.com.joaoreis.bakingapp.recipes.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.joaoreis.bakingapp.service.RecipeRepository;
import br.com.joaoreis.bakingapp.service.models.Recipe;

public class MainViewModel extends ViewModel {

    private RecipeRepository repository = new RecipeRepository();
    private LiveData<List<Recipe>> recipes = new MutableLiveData<>();

    public LiveData<List<Recipe>> getRecipes() {
        recipes = repository.getRecipes();
        return recipes;
    }
}
