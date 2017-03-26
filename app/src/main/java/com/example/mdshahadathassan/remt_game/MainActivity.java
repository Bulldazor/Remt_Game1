package com.example.mdshahadathassan.remt_game;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView highscore,getscore;
    Button btnPlay,reset;
    //Declare the preference
    SharedPreferences preferences;

    SharedPreferences.Editor editor;
    //file name of shared preference, name can be settd as ur wsih
    public final String PREFS_GAME="com.example.mdshahadathassan.remt_game.Game";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        highscore=(TextView)findViewById(R.id.High);
        getscore=(TextView)findViewById(R.id.Score);
        btnPlay=(Button)findViewById(R.id.Play);
        reset=(Button)findViewById(R.id.Reset);

        // Craetimg Shared pref name and mode of the game
        preferences=getSharedPreferences(PREFS_GAME,MODE_PRIVATE); // initialize the shrd prfrnce
        editor=preferences.edit(); // to edit the file

        // set initial score
        final int highScore= preferences.getInt("highScore",0);
        highscore.setText("High Score : "+highScore);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //random generator
                Random random=new Random();
                int score = random.nextInt(1000);
                getscore.setText(String.valueOf(score));
                //initialize the value of highscore
                int getSaveScore = preferences.getInt("highScore",0);
                // checking
                if(score>getSaveScore){
                    highscore.setText("High Score: "+score);
                    editor.putInt("highScore",score);
                    editor.commit();
                }

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("highScore",0);
                editor.commit();
                highscore.setText(String.valueOf(0));
                getscore.setText(String.valueOf(0));
            }
        });
    }
}
