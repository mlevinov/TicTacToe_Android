package com.example.mlevi.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VsHumanOrPc extends AppCompatActivity {

    private TextView xPlayerName;
    private TextView oPlayerName;
    private Board game;
    private TicTac[][] board;
    private final int SIZE = 3;
    private TextView statusLabel;
    private boolean humanOrPc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs_human_or_pc);
        statusLabel = (TextView)findViewById(R.id.statusLabel);
        xPlayerName = (TextView)findViewById(R.id.xPlayerName);
        oPlayerName = (TextView)findViewById(R.id.oPlayerName);
        xPlayerName.setText(getIntent().getExtras().getString("xPlayerName"));
        oPlayerName.setText((getIntent().getExtras().getString("oPlayerName")));
        humanOrPc = getIntent().getExtras().getBoolean("HUMAN_OR_PC");
        game = new Board (createBoard(),true, statusLabel,xPlayerName,oPlayerName);
        setBoard();
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                game.resetBoard();
            }
        });
    }


    private TicTac[][] createBoard()
    {
        board = new TicTac[SIZE][SIZE];
        board[0][0] = new TicTac((Button)findViewById(R.id.b0),humanOrPc);
        board[0][1] = new TicTac((Button)findViewById(R.id.b1),humanOrPc);
        board[0][2] = new TicTac((Button)findViewById(R.id.b2),humanOrPc);
        board[1][0] = new TicTac((Button)findViewById(R.id.b3),humanOrPc);
        board[1][1] = new TicTac((Button)findViewById(R.id.b4),humanOrPc);
        board[1][2] = new TicTac((Button)findViewById(R.id.b5),humanOrPc);
        board[2][0] = new TicTac((Button)findViewById(R.id.b6),humanOrPc);
        board[2][1] = new TicTac((Button)findViewById(R.id.b7),humanOrPc);
        board[2][2] = new TicTac((Button)findViewById(R.id.b8),humanOrPc);
        return board;
    }

    private void setBoard()
    {
        for (int i = 0; i<SIZE; i++)
            for(int j =0; j<SIZE; j++)
                board[i][j].setBoard(game);
    }
}
