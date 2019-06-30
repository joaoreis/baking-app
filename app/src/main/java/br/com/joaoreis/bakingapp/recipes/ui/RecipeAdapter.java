package br.com.joaoreis.bakingapp.recipes.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.databinding.RecipeContentBinding;
import br.com.joaoreis.bakingapp.service.models.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    List<Recipe> recipes = new ArrayList<>();

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutId = R.layout.recipe_content;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutId, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        RecipeContentBinding binding = holder.binding;
        String recipeTitle = recipes.get(position).getName();
        binding.recipeTitle.setText(recipeTitle);
        binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        private RecipeContentBinding binding;

        private RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}