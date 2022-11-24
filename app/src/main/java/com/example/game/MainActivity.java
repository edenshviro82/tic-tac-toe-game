package com.example.game;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView msg;
    int[][] bord;
    int turn = 1;
    ImageButton[] ims;
    boolean win=false;
    TextView title;
    boolean start=false;
    Button playBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.mainActivityTitle);
        msg = findViewById(R.id.mainActivityMsg);
        playBtn = findViewById(R.id.mainActivityPlayBtn);
        ims = new ImageButton[9];




        for (int i = 0; i < ims.length; i++) {
            String id = "mainActivityBtn" + i;
            int resID = getResources().getIdentifier(id, "id", getPackageName());
            ims[i] = (findViewById(resID));
            ims[i].setOnClickListener(this);
        }


        playBtn.setOnClickListener(view -> {
            title.setText("X turn");

            msg.setText("game started");
            start=true;
            bord=new int[3][3];
            turn=1;
            win=false;
            for (int i = 0; i < ims.length; i++) {
                ims[i].setImageResource(R.drawable.white);
            }

        });
    }

    //X/O buttons
    @Override
    public void onClick(View view) {
        if(start){
            playBtn.setText("play again");
            int tag = Integer.parseInt(view.getTag().toString());
            Log.d("TAG", "button clicked = " + tag);
            int rIndex = tag / 3;
            int cIndex = tag % 3;
            Log.d("TAG", "row = " + rIndex +" col = "+cIndex);


            if (bord[rIndex][cIndex] == 0) {
                if (turn == 1) {
                    title.setText("O turn");
                    ims[tag].setImageResource(R.drawable.x);
                    bord[rIndex][cIndex] = 1;

                }else {
                    title.setText("X turn");
                    ims[tag].setImageResource(R.drawable.ic_launcher_foreground);
                    bord[rIndex][cIndex] = -1;
                }


                if ((bord[rIndex][0] == turn && bord[rIndex][1] == turn && bord[rIndex][2] == turn) ||
                        (bord[0][cIndex] == turn && bord[1][cIndex] == turn && bord[2][cIndex] == turn) ||
                        (bord[0][0] == turn && bord[1][1] == turn && bord[2][2] == turn)||
                        (bord[0][2] == turn && bord[1][1] == turn && bord[2][0] == turn))
                    win = true;


                if(win) {
                    if (turn == 1)
                        title.setText("X wins");
                    else
                        title.setText("O wins");

                    start=false;
                }

                turn*=-1;

            }



        }




    }
}