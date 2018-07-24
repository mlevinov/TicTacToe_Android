package com.example.mlevi.tictactoe;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import com.example.mlevi.tictactoe.R;
import com.example.mlevi.tictactoe.TicTac;

public class Board {
    private final String MESSEGE = "\nto Play again press reset";
    private final String WINNER_X;
    private final String WINNER_O;
    private int[] sumForRows;
    private int[] sumForCols;
    private int mainDaigSum;
    private int secondDaigSum;
    private TextView status;
    private TextView xPlayerName;
    private TextView oPlayerName;
    private final int WINNER_IS_X = 3;
    private final int WINNER_IS_O = -3;
    private TicTac[][] board;
    private final int SIZE = 3;
    private boolean xPlayerTurn;
    private VsPC pcPlayer;
    private final int[][] prefferedMoves = {{1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2}, {0, 1}, {1, 0}, {1, 2}, {2, 1}};

    public Board(TicTac[][] board, boolean humanVsHuman, TextView status, TextView xPlayerName, TextView oPlayerName) {
        sumForCols = new int[SIZE];
        sumForRows = new int[SIZE];
        this.xPlayerName = xPlayerName;
        WINNER_X = "WINNER IS "+xPlayerName.getText().toString();
        this.oPlayerName = oPlayerName;
        WINNER_O = "WINNER IS "+oPlayerName.getText().toString();
        mainDaigSum = 0;
        secondDaigSum = 0;
        this.status = status;
        xPlayerTurn = true;
        this.board = board;
        xPlayerInFocus(true);
        pcPlayer = new VsPC(this);
    }

    public void pcMove() {
        pcPlayer.move();
    }

    public void xPlayerInFocus(boolean focus) {
        if (focus) {
            xPlayerName.setBackgroundColor(Color.YELLOW);
            oPlayerName.setBackgroundColor(Color.WHITE);
        } else {
            xPlayerName.setBackgroundColor(Color.WHITE);
            oPlayerName.setBackgroundColor(Color.YELLOW);
        }
    }

    public void resetBoard() {
        for (int i = 0; i < SIZE; i++) {
            sumForCols[i] = 0;
            sumForRows[i] = 0;
            for (int j = 0; j < SIZE; j++) {
                board[i][j].reset();
            }
        }
        mainDaigSum = 0;
        secondDaigSum = 0;
        status.setText("GOOD LUCK");
        xPlayerInFocus(true);
        xPlayerTurn=true;
        pcPlayer = new VsPC(this);
    }

    public TicTac blockWinningMove() {
        return lookForWinningMove(true);
    }

    public TicTac findPrefferedMove()
    {
        for (int i = 0; i<prefferedMoves.length; i++)
            for (int j = 0; j<prefferedMoves[i].length-1; j++)
                if (board[prefferedMoves[i][j]][prefferedMoves[i][j+1]].getSign() == 0)
                    return board[prefferedMoves[i][j]][prefferedMoves[i][j+1]];
        return null;
    }

    public TicTac lookForWinningMove(boolean forX)
    {
        int sumToLook = 0;
        if (forX)
            sumToLook = 2;
        else
            sumToLook = -2;
        //look for a row with two O's and a free place
        for (int i = 0; i<SIZE; i++)
        {
            if(sumForRows[i]==sumToLook)
                return findEmptyCell(i,true);
        }
        //look for a colum with two O's and a free place
        for (int j = 0; j<SIZE; j++)
        {
            if(sumForCols[j]==sumToLook)
                return findEmptyCell(j,false);
        }
        // mainDiagonal
        if (mainDaigSum == sumToLook)
            return findEmtyCellInDiagonal(true);
        if (secondDaigSum == sumToLook)
            return findEmtyCellInDiagonal(false);
        return null;
    }

    private TicTac findEmtyCellInDiagonal(boolean mainDaigonal) {
        if (mainDaigonal) {

            for (int i = 0; i<SIZE; i++)
                if (board[i][i].getSign()==0)
                    return board[i][i];
        }
        else {
            for (int i = 0; i<SIZE; i++)
                if (board[i][SIZE-1-i].getSign()==0)
                    return board[i][SIZE-1-i];
        }
        return null;
    }

    private TicTac findEmptyCell(int i, boolean row)
    {
        if (row)
        {
            for (int j = 0; j<SIZE; j++)
                if (board[i][j].getSign()==0)
                    return board[i][j];
        }
        else {
            for (int j = 0; j<SIZE; j++)
                if (board[j][i].getSign()==0)
                    return board[j][i];
        }
        return null;
    }

    public boolean xPlayerTurn() {
        return xPlayerTurn;
    }

    public void switchTurns() {
        xPlayerTurn = !xPlayerTurn;
        xPlayerInFocus(xPlayerTurn);
    }

    public boolean checkForWinners() {
        sumRowsAndCols();
        sumDiagonals();
        for (int i = 0; i < SIZE; i++) {
            if (sumForRows[i] == WINNER_IS_X)
            {
                status.setText(WINNER_X+MESSEGE);
                return true;
            }
            else if (sumForRows[i] == WINNER_IS_O)
            {
                status.setText(WINNER_O+MESSEGE);
                return true;
            }
        }
        for (int j = 0; j < SIZE; j++)
        {
            if (sumForCols[j] == WINNER_IS_X)
            {
                status.setText(WINNER_X+MESSEGE);
                return true;
            }
            else if (sumForCols[j] == WINNER_IS_O)
            {
                status.setText(WINNER_O+MESSEGE);
                return true;
            }
        }
        if (mainDaigSum == WINNER_IS_X || secondDaigSum == WINNER_IS_X)
        {
            status.setText(WINNER_X+MESSEGE);
            return true;
        }
        else if (mainDaigSum == WINNER_IS_O || secondDaigSum == WINNER_IS_O) {
            status.setText(WINNER_O+MESSEGE);
            return true;
        }
        return false;
    }

    private void sumRowsAndCols() {
        for (int i = 0; i < SIZE; i++) {
            sumRow(i);
            sumCol(i);
        }
    }

    private void sumRow(int row) {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
            sum += this.board[row][i].getSign();
        sumForRows[row] = sum;
    }

    private void sumCol(int col) {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
            sum += this.board[i][col].getSign();
        sumForCols[col] = sum;
    }

    private void sumDiagonals() {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < SIZE; i++) {
            sum1 += board[i][i].getSign();
            sum2 += board[i][SIZE - 1 - i].getSign();
        }
        mainDaigSum = sum1;
        secondDaigSum = sum2;
    }

    public boolean hasMoves()
    {
        for (int i = 0; i<SIZE; i++)
        {
           for (int j = 0; j<SIZE; j++)
           {
                if (board[i][j].getSign() == 0)
                    return true;
            }
        }
        status.setText("IT'S A DRAW!\n TO PLAY AGAIN PRESS RESET");
        return false;
    }
}

