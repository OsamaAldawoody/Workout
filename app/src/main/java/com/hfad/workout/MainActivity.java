package com.hfad.workout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    public void itemClicked(Long id) {
        View fragmentContainer = (View)findViewById(R.id.fragment_container);
        WorkoutDetailFragment detail = new WorkoutDetailFragment();
        if (fragmentContainer != null){
            detail.setWorkoutId(id);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container,detail);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }else{
            Intent i = new Intent(this,DetailActivity.class);
            i.putExtra("workoutId",id);
            startActivity(i);
        }
    }
}
