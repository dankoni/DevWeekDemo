package com.example.devopsapp.devweek;

import android.os.Bundle;
import android.widget.Toast;

import com.example.devopsapp.devweek.base.BaseActivity;
import com.example.devopsapp.devweek.quiz_ui.NavigationListener;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainActivity extends BaseActivity implements NavigationListener {

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
        Toast.makeText(MainActivity.this, "fffff", Toast.LENGTH_LONG).show();
        navController.navigate(R.id.action_move2question);
    }
}
