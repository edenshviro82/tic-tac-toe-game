package com.example.game;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int[][] bord;
    int turn = 1;
    ImageButton[] ims;
    boolean win=false;
    TextView title;
    boolean start=false;
    Button playBtn;
    LinearLayout l1;
    ImageView vertical0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.mainActivityTitle);
        playBtn = findViewById(R.id.mainActivityPlayBtn);
        l1= findViewById(R.id.mainActivityLayout0);
        ims = new ImageButton[9];
        vertical0=findViewById(R.id.mainActivityIV0);




        for (int i = 0; i < ims.length; i++) {
            String id = "mainActivityBtn" + i;
            int resID = getResources().getIdentifier(id, "id", getPackageName());
            ims[i] = (findViewById(resID));
            ims[i].setOnClickListener(this);
        }


        playBtn.setOnClickListener(view -> {
            title.setBackgroundResource(R.drawable.xplay);
            title.setText("");
            start=true;
            bord=new int[3][3];
            turn=1;
            win=false;
            for (int i = 0; i < ims.length; i++) {
                ims[i].setImageResource(R.drawable.backone);
                vertical0.setBackgroundResource(R.drawable.empty);
            }

        });
    }
    public boolean isWin(int rIndex, int cIndex,int turn)
    {
        return ((bord[rIndex][0] == turn && bord[rIndex][1] == turn && bord[rIndex][2] == turn) ||
                (bord[0][cIndex] == turn && bord[1][cIndex] == turn && bord[2][cIndex] == turn) ||
                (bord[0][0] == turn && bord[1][1] == turn && bord[2][2] == turn)||
                (bord[0][2] == turn && bord[1][1] == turn && bord[2][0] == turn));
    }

    public boolean isDraw()
    {
        for (int row=0; row<3; row++)
        {
            for (int col=0; col<3; col++)
            {
                if(bord[row][col] == 0 )
                    return false;
            }
        }
        return true;
    }

    //X/O buttons
    @Override
    public void onClick(View view) {
        if(start){
            playBtn.setText("play again");
            int tag = Integer.parseInt(view.getTag().toString());
            int rIndex = tag / 3;
            int cIndex = tag % 3;

            if (bord[rIndex][cIndex] == 0) {
                if (turn == 1) {
                    title.setBackgroundResource(R.drawable.oplay);
                    ims[tag].setImageResource(R.drawable.x1);
                    bord[rIndex][cIndex] = 1;

                }else {
                    title.setBackgroundResource(R.drawable.xplay);
                    ims[tag].setImageResource(R.drawable.o1);
                    bord[rIndex][cIndex] = -1;
                }

                if(isDraw()){
                    title.setBackgroundResource(R.drawable.nowin);
                }

                win = isWin(rIndex,cIndex,turn);

                if(win) {
                    if((bord[0][0] == turn && bord[1][0] == turn && bord[2][0] == turn))
                        vertical0.setBackgroundResource(R.drawable.mark3);
                    if((bord[0][1] == turn && bord[1][1] == turn && bord[2][1] == turn))
                        vertical0.setBackgroundResource(R.drawable.mark4);
                    if((bord[0][2] == turn && bord[1][2] == turn && bord[2][2] == turn))
                        vertical0.setBackgroundResource(R.drawable.mark5);
                    if((bord[0][0] == turn && bord[1][1] == turn && bord[2][2] == turn))
                        vertical0.setBackgroundResource(R.drawable.mark1);
                    if((bord[0][2] == turn && bord[1][1] == turn && bord[2][0] == turn))
                        vertical0.setBackgroundResource(R.drawable.mark2);
                    if((bord[0][0] == turn && bord[0][1] == turn && bord[0][2] == turn))
                        vertical0.setBackgroundResource(R.drawable.mark6);
                    if((bord[1][0] == turn && bord[1][1] == turn && bord[1][2] == turn))
                        vertical0.setBackgroundResource(R.drawable.mark7);
                    if((bord[2][0] == turn && bord[2][1] == turn && bord[2][2] == turn))
                        vertical0.setBackgroundResource(R.drawable.mark8);



                    if (turn == 1) {
                        title.setBackgroundResource(R.drawable.xwin);


                    }else {
                        title.setBackgroundResource(R.drawable.owin);


                    }
                    start=false;
                }



                turn*=-1;

            }



        }




    }
}