package br.com.joaoreis.bakingapp.detail.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.detail.viewmodel.RecipeMasterlViewModel;
import br.com.joaoreis.bakingapp.service.models.Ingredient;
import br.com.joaoreis.bakingapp.service.models.Ingredients;
import br.com.joaoreis.bakingapp.service.models.Recipe;
import br.com.joaoreis.bakingapp.service.models.Step;
import br.com.joaoreis.widget.IngredientWidgetProvider;

import static br.com.joaoreis.bakingapp.Constants.EXTRA_INGREDIENTS;
import static br.com.joaoreis.bakingapp.Constants.EXTRA_RECIPE;
import static br.com.joaoreis.bakingapp.Constants.EXTRA_STEP;

public class DetailMasterActivity extends AppCompatActivity {

    private boolean twoPane;
    private Recipe recipe;
    private RecyclerView recyclerView;
    private CardView ingredientCard;
    private StepAdapter stepAdapter;
    private RecipeMasterlViewModel viewModel;
    private FragmentManager fragmentManager;
    private ActionBar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.item_detail_container) != null) {
            twoPane = true;
        }

        Intent callingIntent = getIntent();
        Bundle extras = callingIntent.getExtras();
        if (extras != null) {
            recipe = extras.getParcelable(EXTRA_RECIPE);
        }

        setupViewModel(recipe);
        setupToolbar(recipe.getName());
        setupRecyclerView();
        setupIngredients();
        IngredientWidgetProvider.sendRefreshBroadcast(this, recipe);
    }

    private void setupToolbar(String recipeName) {
        toolbar = getSupportActionBar();
        assert toolbar != null;
        toolbar.setTitle(recipeName);
    }

    private void setupIngredients() {
        ingredientCard = findViewById(R.id.ingredients);
        ingredientCard.setOnClickListener(v -> {
            if (twoPane) {
                showIngredientsFragment(recipe.getIngredients());
            } else {
                Context context = DetailMasterActivity.this;
                Bundle bundle = new Bundle();
                bundle.putParcelable(EXTRA_INGREDIENTS, new Ingredients(recipe.getIngredients()));
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


    }

    private void showIngredientsFragment(List<Ingredient> ingredients) {
        fragmentManager.beginTransaction()
                .replace(R.id.item_detail_container, new IngredientFragment(ingredients))
                .commit();
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.step_recycler_view);
        assert recyclerView != null;
        stepAdapter = new StepAdapter(twoPane);
        stepAdapter.setSteps(viewModel.getRecipe().getSteps());
        stepAdapter.setListener(step -> {
            if (twoPane) {
                showStepFragment(step);
            } else {
                Context context = DetailMasterActivity.this;
                Bundle bundle = new Bundle();
                bundle.putParcelable(EXTRA_STEP, step);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        recyclerView.setAdapter(stepAdapter);
    }

    private void showStepFragment(Step step) {
        StepFragment fragment = new StepFragment(step);
        fragmentManager.beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit();
    }

    private void setupViewModel(Recipe recipe) {
        viewModel = ViewModelProviders.of(this).get(RecipeMasterlViewModel.class);
        viewModel.setRecipe(recipe);
    }
}
