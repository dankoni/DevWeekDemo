package com.example.devopsapp.devweek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.devopsapp.devweek.quiz_ui.NavigationListener;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity implements NavigationListener {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.fragment);
        navController.navigate(R.id.quizEntry);

    }

    @Override
    public void get2question() {
        navController.navigate(R.id.action_move2question);
    }


}
