package br.com.joaoreis.bakingapp.recipes.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.recipes.viewmodel.StepViewModel;
import br.com.joaoreis.bakingapp.service.models.Step;

public class StepFragment extends Fragment implements ProgressiveMediaSource.SourceInfoRefreshListener {

    private Step step;
    private TextView stepDescription;
    private SimpleExoPlayer exoPlayer;
    private PlayerView playerView;
    private StepViewModel viewModel;

    public StepFragment(Step step) {
        this.step = step;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.step_fragment, container, false);


        initializeViews(view);
        initializePlayer(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(StepViewModel.class);
    }

    private void initializeViews(View view) {
        stepDescription = view.findViewById(R.id.step_description);
        stepDescription.setText(step.getDescription());
    }

    private void initializePlayer(View view) {
        playerView = view.findViewById(R.id.player);
        Uri videoUri = getUri();
        if (getUri() != null) {
            playerView.setVisibility(View.VISIBLE);
            playMedia(videoUri);
        }
    }

    private Uri getUri() {
        if (!step.getVideoURL().isEmpty()) {
            return Uri.parse(step.getVideoURL());
        }
        if (!step.getThumbnailURL().isEmpty()) {
            return Uri.parse(step.getThumbnailURL());
        }
        return null;
    }

    private void playMedia(Uri videoUri) {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayerFactory.newSimpleInstance(requireContext(), new DefaultTrackSelector(), new DefaultLoadControl());
            playerView.setPlayer(exoPlayer);

            String user = Util.getUserAgent(requireContext(), getResources().getString(R.string.app_name));
            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(requireContext(), user);
            DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory,
                    extractorsFactory).createMediaSource(videoUri);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onSourceInfoRefreshed(MediaSource source, Timeline timeline, @Nullable Object manifest) {

    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        releasePlayer();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (exoPlayer != null) {
            viewModel.setPosition(exoPlayer.getContentPosition());
            exoPlayer.setPlayWhenReady(!exoPlayer.getPlayWhenReady());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (exoPlayer != null) {
            exoPlayer.seekTo(viewModel.getPosition());
            exoPlayer.setPlayWhenReady(true);
        }
    }

}
