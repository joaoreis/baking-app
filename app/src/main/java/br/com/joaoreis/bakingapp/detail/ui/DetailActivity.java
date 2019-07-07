package br.com.joaoreis.bakingapp.detail.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.service.models.Ingredients;
import br.com.joaoreis.bakingapp.service.models.Step;

import static br.com.joaoreis.bakingapp.Constants.EXTRA_INGREDIENTS;
import static br.com.joaoreis.bakingapp.Constants.EXTRA_STEP;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        FragmentManager fragmentManager = getSupportFragmentManager();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Step step = bundle.getParcelable(EXTRA_STEP);
            Ingredients ingredients = bundle.getParcelable(EXTRA_INGREDIENTS);

            if (step != null) {
                StepFragment fragment = new StepFragment(step);
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .commit();
            }

            if (ingredients != null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, new IngredientFragment(ingredients.getIngredients()))
                        .commit();
            }
        }
    }
}
