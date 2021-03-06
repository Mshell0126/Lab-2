package com.example.jsu.lab_2rockpaperscissors;

import android.content.pm.PathPermission;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import android.widget.*;
import android.util.Pair;

import java.util.Arrays;
import java.util.Random;

import static com.example.jsu.lab_2rockpaperscissors.R.id.TextViewPlayerWeapon;

public class RPSactivity extends AppCompatActivity {
    private int playerScore;
    private int computerScore;

    Pair rockWins = new Pair(Weapon.ROCK, Weapon.SCISSORS);
    Pair paperWins = new Pair(Weapon.PAPER, Weapon.ROCK);
    Pair scissorsWins = new Pair(Weapon.SCISSORS, Weapon.PAPER);

    private Random random;

    public enum Weapon{
        ROCK("Rock", "Rock blunts scissors!", "Draw! You both make pet rocks!"),
        PAPER("Paper", "Paper covers rock!", "Draw! You both make origami!"),
        SCISSORS("Scissors", "Scissors cut paper!", "Draw! You both carry your pair safely! :3 ");

        private String message;
        private String winCondition;
        private String drawmsg;

        private Weapon(String msg, String wCndtn, String drw) {
            message = msg;
            winCondition = wCndtn;
            drawmsg = drw;
        }

        @Override
        public String toString() {return message;}

        public String condition(){return winCondition;}

        public String draw(){return drawmsg;}
    }

    private TextView plyrwpn;
    private TextView cmptrwpn;
    private TextView scoreboard;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpsactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        plyrwpn = (TextView) findViewById(TextViewPlayerWeapon);
        cmptrwpn = (TextView)findViewById(R.id.TextViewComputerWeapon);
        scoreboard = (TextView) findViewById(R.id.TextViewScores);
        resultText = (TextView)findViewById(R.id.TextViewResult);
        random = new Random();
    }
    
    public void rockButtonPressed(View v){
        buttonPressed("Rock");
    }
    
    public void paperButtonPressed(View v){
        buttonPressed("Paper");
    }
    
    public void scissorsButtonPressed(View v){
        buttonPressed("Scissors");
    }
    
    public void buttonPressed(String button){

        Weapon playerWeapon = null;

        switch (button){
            case "Rock":
                playerWeapon = Weapon.ROCK;
                break;

            case "Paper":
                playerWeapon = Weapon.PAPER;
                break;

            case "Scissors":
                playerWeapon = Weapon.SCISSORS;
                break;
        }

        String player_weapon = "Player's Weapon: " + playerWeapon.toString();
        plyrwpn.setText(player_weapon);

        Weapon computerWeapon = pickWeapon();

        String computer_weapon = "Computer's Weapon: " + computerWeapon.toString();
        cmptrwpn.setText(computer_weapon);
        


        Pair matchup = new Pair(playerWeapon, computerWeapon);
        String result = "";
        if (playerWeapon.equals(computerWeapon)){
            result = playerWeapon.draw();

        }
        else if (Arrays.asList(rockWins, paperWins, scissorsWins).contains(matchup)){
            playerScore ++;
            result = "Player wins ... " + playerWeapon.condition();

        }
        else{
            computerScore ++;
            result = "Computer wins ... " + computerWeapon.condition();
        }
        resultText.setText(result);

        String scores = "Player: " + playerScore + ", Computer: " + computerScore;
        scoreboard.setText(scores);
    }
    
    public Weapon pickWeapon(){
        Weapon w = null;
        int choice = random.nextInt(3) + 1;

        switch (choice) {
            
            case 1:
                w = Weapon.ROCK;
                break;
                
            case 2:
                w = Weapon.PAPER;
                break;
            
            case 3:
                w = Weapon.SCISSORS;
                break;
        }
        
        return w;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rpsactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
