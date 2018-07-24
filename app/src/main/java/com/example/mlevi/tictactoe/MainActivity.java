package com.example.mlevi.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button plsyVsHumanButton = (Button)findViewById(R.id.vSHumanButton);
        plsyVsHumanButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent insertNames = new Intent(getApplicationContext(),InsertNames.class);
                insertNames.putExtra("VS_HUMAN",true);
                startActivity(insertNames);
            }
        });

        Button plsyVsPCButton = (Button)findViewById(R.id.vSPCButton);
        plsyVsPCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent insertNames = new Intent(getApplicationContext(),InsertNames.class);
                insertNames.putExtra("VS_HUMAN",false);
                startActivity(insertNames);
            }
        });
        Button exitButton = (Button)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
                System.exit(0);
            }
        });
    }
}
