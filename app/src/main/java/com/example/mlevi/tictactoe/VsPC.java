package com.example.mlevi.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VsPC extends AppCompatActivity
{
    private Board board;
    private TicTac pointer;

    public VsPC (Board board)
    {
        this.board=board;
    }

    public void move()
    {
        pointer = board.lookForWinningMove(false);
        if (pointer!=null)
        {
            pointer.setToO();
            board.checkForWinners();
        }
        //else if (board.hasMoves())
        //{
        else
        {
            pointer = board.blockWinningMove();
            if (pointer!=null)
            {
                pointer.setToO();
            }
            else {
                pointer = board.findPrefferedMove();
                pointer.setToO();
            }
        }
    }
}
