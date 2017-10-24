package com.example.alpha.kidslearningworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class PuzzleLevel extends AppCompatActivity {

    TextView l1,l2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_puzzle_level);

        l1 = (TextView) findViewById(R.id.level1);
        l2 = (TextView) findViewById(R.id.level2);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PuzzleLevel.this, Puzzle.class);
                startActivity(intent);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PuzzleLevel.this, PuzzleLevel2.class);
                startActivity(intent);
            }
        });
    }
}
