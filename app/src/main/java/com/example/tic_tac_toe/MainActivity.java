package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2,2};
    int[][] win={{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};
    boolean gameActive=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void reset(View view)
    {
        gameActive=true;
        activePlayer=0;
        for(int i=0;i<=gameState.length;i++)
        {gameState[i]=2;}


        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);


        TextView Xstatus=findViewById(R.id.Xstatus);
        TextView Ystatus=findViewById(R.id.Ystatus);
        Ystatus.setVisibility(Ystatus.INVISIBLE);
        Xstatus.setVisibility(Xstatus.INVISIBLE);

    }

    public void playerTap(View view)
    {
        ImageView img=(ImageView) view;
        int tappedImage=Integer.parseInt(img.getTag().toString());

        if(!gameActive)
        {reset(view);}

        TextView Xstatus=findViewById(R.id.Xstatus);
        TextView Ystatus=findViewById(R.id.Ystatus);
        TextView status=findViewById(R.id.status);
        status.setVisibility(status.INVISIBLE);
        if(gameState[tappedImage]==2 && gameActive)
        {
            //gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);

            if(activePlayer==0)
            {
                Ystatus.setVisibility(Ystatus.VISIBLE);
                Xstatus.setVisibility(Xstatus.INVISIBLE);
                img.setImageResource(R.drawable.cross);
                Xstatus.setText("Player X's turn");
                gameState[tappedImage]=0;
                activePlayer=1;
            }
            else
             if(activePlayer==1)
            {
                Xstatus.setVisibility(Xstatus.VISIBLE);
                Ystatus.setVisibility(Ystatus.INVISIBLE);
                img.setImageResource(R.drawable.dot);
                Ystatus=findViewById(R.id.Ystatus);
                Ystatus.setText("Player Y's turn");
                gameState[tappedImage]=1;
                activePlayer=0;
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }

        //winning case
        String winner = null;
        for(int[] winpostion:win)
        {
            if(gameState[winpostion[0]]==gameState[winpostion[1]] && gameState[winpostion[1]]==gameState[winpostion[2]]
            && gameState[winpostion[0]]!=2)
            {
                gameActive=false;
                if(gameState[winpostion[0]]==0)
                {winner="X is the winner"; }
                else
                    winner="Y is the winner";
            }

        }
        int tie_counter=0;
        for(int i=0;i<gameState.length;i++)
        {if(gameState[i]==1 || gameState[i]==0)
        tie_counter++;}
        if(tie_counter==9)
            winner="     Tie Game!";

        status.setVisibility(status.VISIBLE);
        status.setText(winner);

    }

}