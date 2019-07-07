package br.com.joaoreis.bakingapp.detail.viewmodel;

import androidx.lifecycle.ViewModel;

public class StepViewModel extends ViewModel {

    private long position;

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }
}
