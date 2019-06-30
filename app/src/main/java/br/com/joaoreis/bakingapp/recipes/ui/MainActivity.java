package br.com.joaoreis.bakingapp.recipes.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.databinding.ActivityMainBinding;
import br.com.joaoreis.bakingapp.recipes.viewmodel.MainViewModel;
import br.com.joaoreis.bakingapp.service.models.Recipe;

public class MainActivity extends AppCompatActivity {

    private boolean twoPane;
    private List<Recipe> recipeList = new ArrayList<>();


    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        recyclerView = binding.recipeList.findViewById(R.id.recipe_recyclerView);
        recipeAdapter = new RecipeAdapter();
        recyclerView.setAdapter(recipeAdapter);

        setupViewModel();
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getRecipes().observe(this, recipes -> recipeList = recipes);

    }
}
