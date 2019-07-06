package br.com.joaoreis.bakingapp.recipes.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.service.models.Step;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {
    private boolean twoPane;
    private List<Step> steps = new ArrayList<>();
    private  OnStepClickListener listener;

    public StepAdapter(boolean twoPane) {
        this.twoPane = twoPane;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    public void setListener(OnStepClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        Context context = parent.getContext();
        int layoutId = R.layout.step_content;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutId, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        String stepTitle = steps.get(position).getShortDescription();
        holder.stepTitle.setText(stepTitle);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }


    public class StepViewHolder extends RecyclerView.ViewHolder{

        private TextView stepTitle;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            stepTitle = itemView.findViewById(R.id.step_title);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onClick(steps.get(position));
                }
            });
        }
    }
}
