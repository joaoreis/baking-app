package br.com.joaoreis.bakingapp.detail.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.service.models.Ingredient;

public class IngredientFragment extends Fragment {

    private RecyclerView recyclerView;
    private IngredientAdapter ingredientAdapter;
    private List<Ingredient> ingredients;

    public IngredientFragment(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View ingredientView = inflater.inflate(R.layout.ingredient_fragment, container, false);
        setupViews(ingredientView);
        return ingredientView;
    }

    private void setupViews(View ingredientView) {
        ingredientAdapter = new IngredientAdapter();
        ingredientAdapter.setIngredients(ingredients);
        recyclerView = ingredientView.findViewById(R.id.ingredient_recycler_view);
        recyclerView.setAdapter(ingredientAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
    }

}
