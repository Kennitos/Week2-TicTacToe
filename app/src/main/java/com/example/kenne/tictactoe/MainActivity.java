package com.example.kenne.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.lang.reflect.Array;


public class MainActivity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Game savegame = game;
        outState.putSerializable("save", savegame);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);


        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.grid);
        if(gridLayout == null) {
            Log.d(" null", " gl");
        }

        if (savedInstanceState != null) {
            game = (Game) savedInstanceState.getSerializable("save");
            game.Restore(gridLayout);

            Log.d(" grid", String.valueOf(gridLayout.getChildCount()));
        }
    }

    public void tileClicked(View view) {
        if(game.won()!= GameState.IN_PROGRESS){
            return;
        }
        int id = view.getId();
        int row = 0;
        int column = 0;

//        if(id==R.id.button1){
//            row = 0;
//            column = 0;
//        }
        if(id==R.id.button2){
//            row = 0;
            column = 1;
        }
        else if(id==R.id.button3){
//            row = 0;
            column = 2;
        }
        else if(id==R.id.button4){
            row = 1;
            column = 0;
        }
        else if(id==R.id.button5){
            row = 1;
            column = 1;
        }
        else if(id==R.id.button6){
            row = 1;
            column = 2;
        }
        else if(id==R.id.button7){
            row = 2;
            column = 0;
        }
        else if(id==R.id.button8){
            row = 2;
            column = 1;
        }
        else if(id==R.id.button9){
            row = 2;
            column = 2;
        }
        TileState state = game.choose(row, column);
        Button button = (Button) view;

        switch(state) {
            case CROSS:
                // do something
                button.setText("X");
                break;
            case CIRCLE:
                // do something
                button.setText("O");
                break;
            case INVALID:
                // do something different
                Log.d("dk","message");
                break;
        }

        GameState status = game.won();
        TextView result = findViewById(R.id.textView3);
        switch(status) {
            case DRAW:
                result.setText("DRAW");
                break;
            case PLAYER_ONE:
                result.setText("PLAYER ONE WINS");
                Log.d("PLAYER ONE WINS","message");
                break;
            case PLAYER_TWO:
                result.setText("PLAYER TWO WINS");
                break;
            case IN_PROGRESS:
                result.setText("IN PROGRESS");
                break;
        }
    }

    public void resetClicked(View view){
//        https://stackoverflow.com/questions/32515054/android-iterating-through-button-ids-with-a-for-loop-in-java
        game = new Game();
        TextView result = findViewById(R.id.textView3);
        result.setText("START NEW GAME");
        String[] buttonlist= {"button1","button2","button3","button4","button5","button6","button7","button8","button9"};
        for (int i =1; i <= buttonlist.length; i++){
            Button button = findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            button.setText(" ");

        }
    }

}
