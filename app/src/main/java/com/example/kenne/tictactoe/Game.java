package com.example.kenne.tictactoe;

import android.service.quicksettings.Tile;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.io.Serializable;
import java.util.TimerTask;

public class Game implements Serializable {

    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public TileState choose(int row, int column) {
        if( board[row][column]== TileState.BLANK) {
            if(playerOneTurn) {
                playerOneTurn = false;
                board[row][column]= TileState.CROSS;
                movesPlayed += 1;
                return TileState.CROSS;
            }
            else {
                playerOneTurn = true;
                board[row][column]= TileState.CIRCLE;
                movesPlayed += 1;
                return TileState.CIRCLE;
            }
        }
        else {
            return TileState.INVALID;
        }
    }

    public GameState won() {
        for(int i=0; i<3; i++){
            // check wins for player one
            if(board[i][0]== TileState.CROSS && board[i][1]== TileState.CROSS  && board[i][2]== TileState.CROSS){
                return GameState.PLAYER_ONE; // horizontal wins
            }
            else if(board[0][i]== TileState.CROSS && board[1][i]== TileState.CROSS  && board[2][i]== TileState.CROSS){
                return GameState.PLAYER_ONE; // vertical wins
            }
            else if((board[0][2]== TileState.CROSS && board[1][1]== TileState.CROSS  && board[2][0]== TileState.CROSS)||(board[2][2]== TileState.CROSS && board[1][1]== TileState.CROSS  && board[0][0]== TileState.CROSS)) {
                return GameState.PLAYER_ONE; // diagonal wins
            }
            // check wins for player two
            else if(board[i][0]== TileState.CIRCLE && board[i][1]== TileState.CIRCLE  && board[i][2]== TileState.CIRCLE){
                return GameState.PLAYER_TWO; // horizontal wins
            }
            else if(board[0][i]== TileState.CIRCLE && board[1][i]== TileState.CIRCLE  && board[2][i]== TileState.CIRCLE){
                return GameState.PLAYER_TWO; // vertical wins
            }
            else if((board[0][2]== TileState.CIRCLE && board[1][1]== TileState.CIRCLE  && board[2][0]== TileState.CIRCLE)||(board[2][2]== TileState.CIRCLE && board[1][1]== TileState.CIRCLE  && board[0][0]== TileState.CIRCLE)) {
                return GameState.PLAYER_TWO; // diagonal wins
            }
        }
        if(movesPlayed==9){
            return GameState.DRAW;
        }
        else {
            return GameState.IN_PROGRESS;
        }
    }

    public void Restore(android.support.v7.widget.GridLayout layout){
        int button_id = 0;
        for(int x_as=0; x_as<layout.getColumnCount(); x_as++) {
            for(int y_as=0; y_as<layout.getColumnCount(); y_as++) {

                Button button = (Button) layout.getChildAt(button_id);
                button_id += 1;

                TileState state = board[x_as][y_as];
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

            }
        }
    }

}
