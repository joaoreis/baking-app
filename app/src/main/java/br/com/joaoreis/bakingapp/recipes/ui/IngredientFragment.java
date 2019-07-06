package br.com.joaoreis.bakingapp.recipes.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.service.models.Ingredient;
import br.com.joaoreis.bakingapp.service.models.Recipe;

import static br.com.joaoreis.bakingapp.recipes.ui.RecipeDetailActivity.EXTRA_INGREDIENTS;

public class IngredientFragment extends Fragment {

    private RecyclerView recyclerView;
    private IngredientAdapter ingredientAdapter;
    private List<Ingredient> ingredients;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View ingredientView =  inflater.inflate(R.layout.ingredient_fragment, container, false);
        setupIngredients(ingredientView);
        return ingredientView;
    }

    private void setupIngredients(View ingredientView) {
       Bundle bundle = getArguments();
        if (bundle != null) {
            Recipe recipe = bundle.getParcelable(EXTRA_INGREDIENTS);
            ingredients = recipe.getIngredients();

            recyclerView = ingredientView.findViewById(R.id.ingredient_recycler_view);
            ingredientAdapter = new IngredientAdapter();
            ingredientAdapter.setIngredients(ingredients);
            recyclerView.setAdapter(ingredientAdapter);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Recipe recipe = bundle.getParcelable(EXTRA_INGREDIENTS);
            ingredients = recipe.getIngredients();
        }
    }
}
