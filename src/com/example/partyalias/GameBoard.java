package com.example.partyalias;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameBoard extends Activity {
	
	gameSettings game;
	TextView current;
	TextView s1;
	TextView s2;
	TextView s3;
	TextView s4;
	TextView n1;
	TextView n2;
	TextView n3;
	TextView n4;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_board);
		
		
		Intent intent = getIntent();       
        game = (gameSettings) intent.getSerializableExtra("Game");
        if (game.current == game.no_of_teams)
        	game.current = 0;
        current = (TextView) findViewById(R.id.teamText);
        current.setText(game.names[game.current]);
//        game.current++;
        
        s1 = (TextView) findViewById(R.id.score1);
        s2 = (TextView) findViewById(R.id.score2);
        s3 = (TextView) findViewById(R.id.score3);
        s4 = (TextView) findViewById(R.id.score4);
        n1 = (TextView) findViewById(R.id.name1);
        n2 = (TextView) findViewById(R.id.name2);
        n3 = (TextView) findViewById(R.id.name3);
        n4 = (TextView) findViewById(R.id.name4);
         
        switch(game.no_of_teams)
        {
        case 4: s1.setText(game.scores[0] + "");
        		s2.setText(game.scores[1] + "");
        		s3.setText(game.scores[2] + "");
        		s4.setText(game.scores[3] + "");
        		n1.setText(game.names[0]);
        		n2.setText(game.names[1]);
        		n3.setText(game.names[2]);
        		n4.setText(game.names[3]);
        		break;
        case 3: s1.setText(game.scores[0] + "");
				s2.setText(game.scores[1] + "");
				s3.setText(game.scores[2] + "");
				n1.setText(game.names[0]);
        		n2.setText(game.names[1]);
        		n3.setText(game.names[2]);
				break;
        	
        case 2: s1.setText(game.scores[0] + "");
        		s2.setText(game.scores[1] + "");
        		n1.setText(game.names[0]);
        		n2.setText(game.names[1]);
        		break;
        }
        
        final Button StartButton = (Button) findViewById(R.id.buttonStart);
        StartButton.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {                
                Intent goToCards = new Intent(GameBoard.this, PlayGame.class);
                goToCards.putExtra("Game", game);
				  startActivity(goToCards);
				  finish();
	        }
	    });
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_board, menu);
		return true;
	}

}
