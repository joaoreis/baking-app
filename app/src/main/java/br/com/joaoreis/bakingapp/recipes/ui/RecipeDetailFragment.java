package br.com.joaoreis.bakingapp.recipes.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.recipes.viewmodel.RecipeDetailViewModel;

public class RecipeDetailFragment extends Fragment {

    public static final String EXTRA_RECIPE = "recipe";
    private RecipeDetailViewModel mViewModel;

    public static RecipeDetailFragment newInstance() {
        return new RecipeDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_detail_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel.class);
    }

}
