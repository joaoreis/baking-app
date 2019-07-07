package br.com.joaoreis.bakingapp.recipes.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.databinding.ActivityMainBinding;
import br.com.joaoreis.bakingapp.detail.ui.DetailMasterActivity;
import br.com.joaoreis.bakingapp.recipes.viewmodel.MainViewModel;

import static br.com.joaoreis.bakingapp.Constants.EXTRA_RECIPE;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle(getTitle());

        setupRecyclerView(binding);
        setupViewModel();
    }

    private void setupRecyclerView(ActivityMainBinding binding) {

        recyclerView = (RecyclerView) binding.recipeListLayout;
        assert recyclerView != null;
        recipeAdapter = new RecipeAdapter();
        recipeAdapter.setOnItemClickListener(recipe ->
        {
            Context context = MainActivity.this;
            Intent intent = new Intent(context, DetailMasterActivity.class);
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
