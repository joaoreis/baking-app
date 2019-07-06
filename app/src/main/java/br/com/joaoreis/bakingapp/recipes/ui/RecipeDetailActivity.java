package br.com.joaoreis.bakingapp.recipes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.recipes.viewmodel.RecipeDetailViewModel;
import br.com.joaoreis.bakingapp.service.models.Recipe;

import static br.com.joaoreis.bakingapp.recipes.ui.RecipeDetailFragment.EXTRA_RECIPE;

public class RecipeDetailActivity extends AppCompatActivity {

    private boolean twoPane;
    private Recipe recipe;
    private RecyclerView recyclerView;
    private StepAdapter stepAdapter;
    private RecipeDetailViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);


        if (findViewById(R.id.item_detail_container) != null) {
            twoPane = true;
        }

        Intent callingIntent = getIntent();
        Bundle extras = callingIntent.getExtras();
        recipe = extras.getParcelable(EXTRA_RECIPE);
        setupRecyclerView();
        setupViewModel();
    }

    private void setupRecyclerView() {
        if (twoPane) {
            recyclerView = findViewById(R.id.step_recycler_view);
        } else {
            recyclerView = findViewById(R.id.step_recycler_view);
        }
        assert recyclerView != null;
        stepAdapter = new StepAdapter(twoPane);
        stepAdapter.setListener(step -> {
            if (twoPane) {
                Toast.makeText(RecipeDetailActivity.this, "TWOPANE" + step.getId(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RecipeDetailActivity.this, "SINGLE PANE" + step.getShortDescription(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(stepAdapter);
    }

    private void setupViewModel() {
        stepAdapter.setSteps(recipe.getSteps());
        viewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel.class);
    }

}
