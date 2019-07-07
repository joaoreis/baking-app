package br.com.joaoreis.bakingapp.recipes.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.recipes.ui.adapters.StepAdapter;
import br.com.joaoreis.bakingapp.recipes.viewmodel.RecipeDetailViewModel;
import br.com.joaoreis.bakingapp.service.models.Recipe;
import br.com.joaoreis.bakingapp.service.models.Step;

import static br.com.joaoreis.bakingapp.recipes.ui.RecipeDetailFragment.EXTRA_RECIPE;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_INGREDIENTS = "ingredients";
    public static final String EXTRA_STEP = "steps";
    private boolean twoPane;
    private Recipe recipe;
    private RecyclerView recyclerView;
    private CardView ingredients;
    private StepAdapter stepAdapter;
    private RecipeDetailViewModel viewModel;
    FragmentManager fragmentManager;


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
        setupRecyclerView();
        setupIngredients();


    }

    private void setupIngredients() {
        ingredients = findViewById(R.id.ingredients);
        ingredients.setOnClickListener(view -> {
//            IngredientFragment fragment = new IngredientFragment(new IngredientAdapter(), recipe.getIngredients());

            Context context = RecipeDetailActivity.this;
            Bundle bundle = new Bundle();
            bundle.putParcelable(EXTRA_INGREDIENTS, recipe);
            Intent intent = new Intent(context, StepActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);

        });
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.step_recycler_view);
        assert recyclerView != null;
        stepAdapter = new StepAdapter(twoPane);
        stepAdapter.setSteps(viewModel.getRecipe().getSteps());
        stepAdapter.setListener(step -> {
            if (twoPane) {
                Toast.makeText(RecipeDetailActivity.this, "TWOPANE" + step.getId(), Toast.LENGTH_SHORT).show();
                showStepFragment(step);
            } else {
                Toast.makeText(RecipeDetailActivity.this, "SINGLE PANE" + step.getShortDescription(), Toast.LENGTH_SHORT).show();
                Context context = RecipeDetailActivity.this;
                Bundle bundle = new Bundle();
                bundle.putParcelable(EXTRA_STEP, step);
                Intent intent = new Intent(context, StepActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        recyclerView.setAdapter(stepAdapter);
    }

    private void showStepFragment(Step step) {
        fragmentManager.beginTransaction()
                .replace(R.id.item_detail_container, new StepFragment(step))
                .commit();
    }

    private void setupViewModel(Recipe recipe) {
        viewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel.class);
        viewModel.setRecipe(recipe);
    }

}
