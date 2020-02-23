package com.hfad.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hfad.workout.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WorkoutDetailFragment workoutDetailFragment = (WorkoutDetailFragment)getSupportFragmentManager().findFragmentById(R.id.frag_detail);;
        Long id = (Long) getIntent().getExtras().get("workoutId");
        workoutDetailFragment.setWorkoutId(id);
    }
}
