package com.example.mlevi.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertNames extends AppCompatActivity {

    private String xPlayer;
    private String oPlayer;
    private boolean humanVsHuman;
    private Intent startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_names);
        Button startButton = (Button)findViewById(R.id.startGameButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                EditText xPlayerName = (EditText)findViewById(R.id.xPlayerName);
                EditText oPlayerName = (EditText)findViewById(R.id.oPlayerName);
                if (xPlayerName.getText().toString().isEmpty())
                    xPlayer = "X PLAYER";
                else
                    xPlayer = xPlayerName.getText().toString();
                if (oPlayerName.getText().toString().isEmpty())
                    oPlayer = "O PLAYER";
                else
                    oPlayer = oPlayerName.getText().toString();
                humanVsHuman = getIntent().getExtras().getBoolean("VS_HUMAN");
                startGame = new Intent(getApplicationContext(), VsHumanOrPc.class);
                startGame.putExtra("xPlayerName",xPlayer);
                startGame.putExtra("oPlayerName",oPlayer);
                startGame.putExtra("HUMAN_OR_PC",humanVsHuman);
                startActivity(startGame);
            }
        });
    }
}
