package com.example.partyalias;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class StartGame1 extends Activity {
	
	gameSettings game;
	SeekBar s1;
	SeekBar s2;
	SeekBar s3;
	TextView val;
	TextView di;
	TextView du;
	String language;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_game1);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Bundle extras = getIntent().getExtras();
		  if (extras!=null)
		   language = extras.getString("language");
		
			s1 = (SeekBar) findViewById(R.id.seek1);
	    	s2 = (SeekBar) findViewById(R.id.seek2);
	    	s3 = (SeekBar) findViewById(R.id.seek3);

	    	 s1.setOnSeekBarChangeListener(new list1());
	    	 s2.setOnSeekBarChangeListener(new list2());
	    	 s3.setOnSeekBarChangeListener(new list3());

		final Button StartButton = (Button) findViewById(R.id.dialogButtonCancel);

		StartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	int no = s1.getProgress();
            	int dif = s2.getProgress();
            	int dur = s3.getProgress();
                game = new gameSettings();
                game.no_of_teams = no + 2;
                game.difficulty = dif;
                game.duration = dur;
            	game.language = language;
                Intent goto3 = new Intent(StartGame1.this, StartGame2.class);
               
                goto3.putExtra("Game", game);
                startActivity(goto3);
            }
        });
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_game1, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private class list1 implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser) {
            int value = progress + 2;
            val = (TextView) findViewById(R.id.valNo);
            val.setText(""+ value);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {}

        public void onStopTrackingTouch(SeekBar seekBar) {}

    }
	private class list2 implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser) {
        	  di = (TextView) findViewById(R.id.valDif);   
        	switch(progress)
        	{
        	case 0:  di.setText("Beginner");
        			 break;
        	case 1:  di.setText("Medium");
        			 break;
        	case 2:  di.setText("Hard");
        			 break;
        	}
            
           
        }

        public void onStartTrackingTouch(SeekBar seekBar) {}

        public void onStopTrackingTouch(SeekBar seekBar) {}

    }
	private class list3 implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser) {
                            // Log the progress
            du = (TextView) findViewById(R.id.valDur);
            du.setText(progress + " s");
        }

        public void onStartTrackingTouch(SeekBar seekBar) {}

        public void onStopTrackingTouch(SeekBar seekBar) {}

    }

}
