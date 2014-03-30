package com.example.partyalias;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.example.partyalias.XMLDOMParser;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PlayGame extends Activity {

	gameSettings game;
	public String category;
	public String language;
	public String[] wordList;
	public int wordNr;
	public int roundScore=0;
	public int duration;
	
	// XML node names
	static final String ATTR_LANGUAGE = "lang";
	static final String ATTR_CATEGORY = "categ";
	static final String NODE_LANGUAGE = "language";
	static final String NODE_CATEGORY = "category";
	static final String NODE_WORD = "word";	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);
		Intent intent = getIntent();       
        game = (gameSettings) intent.getSerializableExtra("Game");
        getGameSettings();
		parseXMLfile();
		writeScore();
		generateWord();
		final Button PassButton = (Button) findViewById(R.id.buttonPass);
		PassButton.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	roundScore--;
	            writeScore();
	            generateWord();
	        }
	    });
		
		final Button CorrectButton = (Button) findViewById(R.id.buttonCorrect);
		CorrectButton.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	roundScore++;
	            writeScore();
	            generateWord();
	        }
	    });
		startChronometer();
		
	}

	public void parseXMLfile()
	{
		XMLDOMParser parser = new XMLDOMParser();
		AssetManager manager = getAssets();
		InputStream stream;
		try
		{
			stream = manager.open("wordlist.xml");
			Document doc = parser.getDocument(stream);
			//NodeList wordlistNodeList = doc.getElementsByTagName(NODE_WORDLIST);
			NodeList languageNodeList = doc.getElementsByTagName(NODE_LANGUAGE);
			for(int i=0; i<languageNodeList.getLength();i++)
			{
				Element languageNode = (Element) languageNodeList.item(i);
				if(languageNode.getAttribute(ATTR_LANGUAGE).equals(language))
				{
					NodeList categoryNodeList = languageNode.getElementsByTagName(NODE_CATEGORY);
					for(int j=0; j<categoryNodeList.getLength(); j++)
					{
						Element categoryNode = (Element) categoryNodeList.item(j);
						if(categoryNode.getAttribute(ATTR_CATEGORY).equals(category))
						{
							NodeList wordsNodeList = categoryNode.getElementsByTagName(NODE_WORD);
							wordNr = wordsNodeList.getLength();
							wordList = new String[wordNr];
							for(int k=0; k<wordsNodeList.getLength(); k++)
								{
								Element wordNode = (Element) wordsNodeList.item(k);
								wordList[k] = wordNode.getTextContent();
								}
						}
						
					}
				}
			}
		}

		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void generateWord()
	{
		Random r = new Random();
		int generated = r.nextInt(wordNr-1);
		EditText wordInput = (EditText) findViewById(R.id.cardInput);
		wordInput.setText(wordList[generated]);
		wordInput.setGravity(Gravity.CENTER_HORIZONTAL);
		
//		Toast.makeText(getApplicationContext(),wordList[generated], Toast.LENGTH_LONG).show();
		
	}
	
	public void writeScore()
	{
		TextView scoreInput = (TextView) findViewById(R.id.scoreInput);
		scoreInput.setText(roundScore + "");
		scoreInput.setGravity(Gravity.CENTER_HORIZONTAL);
	}
	
	public void startChronometer()
	{
		final TextView chronometerInput = (TextView) findViewById(R.id.chronoMeter);
		new CountDownTimer(duration*1000, 1000) {

		     public void onTick(long millisUntilFinished) {
		    	 long minutes = millisUntilFinished / 1000 / 60;
		    	 long seconds = (millisUntilFinished / 1000) - (minutes * 60) ;
		    	 
		    	 if(seconds >=10)	 
		    		 chronometerInput.setText("0" + minutes + ":" + seconds);
		    	 else
		    		 chronometerInput.setText("0" + minutes + ":0" + seconds);
		    	 if(minutes==0 && seconds<=5)
		    		 chronometerInput.setTextColor(Color.parseColor("#C24641"));
		    	 chronometerInput.setGravity(Gravity.CENTER_HORIZONTAL);
		     }

		     public void onFinish() {
		    	 chronometerInput.setText("00:00");
		    	 showFinalDialog();
		    	 //finishRound();
		    	 
		    	 
		     }
		  }.start();
	}
	
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_game, menu);
		return true;
	}
	
	void getGameSettings()
	{
        duration=game.duration;
        language = game.language;
        switch(game.difficulty)
        {
        case 0: category = "BEGINNER";
        		break;
        case 1: category = "INTERMEDIATE";
				break;
        	
        case 2: category = "HARD";
        		break;
        }
	
	}
	
	void finishRound()
	{
		game.scores[game.current]=game.scores[game.current]+roundScore;
		game.current++;
		Intent gotoboard = new Intent(PlayGame.this, GameBoard.class);
		gotoboard.putExtra("Game", game);
		startActivity(gotoboard);
		finish();
	}
	
	void showFinalDialog()
	{
		AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                PlayGame.this);
        builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Did any team give the correct answer?");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
        		PlayGame.this,
                android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add(game.names[0]);
        arrayAdapter.add(game.names[1]);
        if(game.no_of_teams==3)
        	arrayAdapter.add(game.names[2]);
        if(game.no_of_teams==4)
        	arrayAdapter.add(game.names[3]);
        builderSingle.setNegativeButton("No",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	switch(which)
                        {
                        case 0: game.scores[0]=game.scores[0]+1;
                        		break;
                        case 1: game.scores[1]=game.scores[1]+1;
                				break;
                        case 2: game.scores[2]=game.scores[2]+1;
                        		break;
                        case 3: game.scores[3]=game.scores[3]+1;
                				break;
                        }
                    	
                    	finishRound();
                    	
                    }
                });
        builderSingle.show();
	}

}
