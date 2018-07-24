package com.example.mlevi.tictactoe;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicTac implements View.OnClickListener {
    private Board board;
    private final int X = 1;
    private final int O = -1;
    private final int BLANK = 0;
    private Button button;
    private int ticOrTac;
    private boolean vsHuman;

    public TicTac(Button b, boolean humanOrPC)
    {
        vsHuman = humanOrPC;
        button = b;
        b.setOnClickListener(this);
        ticOrTac = BLANK;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isX() {
        return ticOrTac == X;
    }

    public boolean isO() {
        return ticOrTac == O;
    }

    public void setToX() {
        ticOrTac = X;
        button.setText("X");
    }

    public void setToO() {
        button.setText("O");
        ticOrTac = O;
    }

    public void reset() {
        button.setText("");
        ticOrTac = BLANK;
    }

    public int getSign() {
        return ticOrTac;
    }

    @Override
    public void onClick(View view) {
        if (vsHuman)
        {
            if (board.xPlayerTurn())
                setToX();
            else
                setToO();
            if(!board.checkForWinners()&& board.hasMoves())
                board.switchTurns();
        }
        else
        {
            if (board.xPlayerTurn()) {
                setToX();
                if(!board.checkForWinners() && board.hasMoves())
                    board.pcMove();
            }
        }
    }
}