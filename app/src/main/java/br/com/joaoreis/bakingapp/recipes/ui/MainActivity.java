package br.com.joaoreis.bakingapp.recipes.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.databinding.ActivityMainBinding;
import br.com.joaoreis.bakingapp.recipes.ui.adapters.RecipeAdapter;
import br.com.joaoreis.bakingapp.recipes.viewmodel.MainViewModel;
import br.com.joaoreis.bakingapp.service.models.Recipe;

import static br.com.joaoreis.bakingapp.recipes.ui.RecipeDetailFragment.EXTRA_RECIPE;

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

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle(getTitle());

        if (findViewById(R.id.item_detail_container) != null) {
            twoPane = true;
        }

        setupRecyclerView(binding);
        setupViewModel();
    }

    private void setupRecyclerView(ActivityMainBinding binding) {
        if (twoPane) {
            recyclerView = binding.recipeListLayout.findViewById(R.id.recipe_list);
        } else {
            recyclerView = (RecyclerView) binding.recipeListLayout;

        }
        assert recyclerView != null;
        recipeAdapter = new RecipeAdapter();
        recipeAdapter.setOnItemClickListener(recipe ->
        {

            Context context = MainActivity.this;
            Toast.makeText(context, "clicou em: " + recipe.getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(EXTRA_RECIPE, recipe);
            intent.putExtras(bundle);
            context.startActivity(intent);

        });
        recyclerView.setAdapter(recipeAdapter);
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getRecipes().observe(this, recipes -> recipeAdapter.setRecipes(recipes));

    }
}
