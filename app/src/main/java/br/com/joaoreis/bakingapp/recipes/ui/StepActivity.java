package br.com.joaoreis.bakingapp.recipes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.List;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.service.models.Ingredient;
import br.com.joaoreis.bakingapp.service.models.Recipe;
import br.com.joaoreis.bakingapp.service.models.Step;

import static br.com.joaoreis.bakingapp.recipes.ui.RecipeDetailActivity.EXTRA_INGREDIENTS;
import static br.com.joaoreis.bakingapp.recipes.ui.RecipeDetailActivity.EXTRA_STEP;

public class StepActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        fragmentManager = getSupportFragmentManager();

        Bundle bundle = getIntent().getExtras();

        Step step =  bundle.getParcelable(EXTRA_STEP);
        Recipe recipe  = bundle.getParcelable(EXTRA_INGREDIENTS);

        if (step != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, new StepFragment(step))
                    .commit();
        }

        if (recipe != null) {
            List<Ingredient> ingredients = recipe.getIngredients();
            fragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, new IngredientFragment(new IngredientAdapter(), ingredients))
                    .commit();
        }
    }
}
