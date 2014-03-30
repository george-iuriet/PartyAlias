package com.example.partyalias;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;

public class StartGame2 extends Activity {

	gameSettings game;
	final Context context = this;
	private ImageView btn1;
	private ImageView btn2;
	private ImageView btn3;
	private ImageView btn4;
	private EditText edit1;
	private EditText edit2;
	private EditText edit3;
	private EditText edit4;
	private Button start;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_game2);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Intent intent = getIntent();
		 
        
        game = (gameSettings) intent.getSerializableExtra("Game");
		
        start = (Button) findViewById(R.id.btnStart);
		
		btn1 = (ImageView) findViewById(R.id.team1);
		btn2 = (ImageView) findViewById(R.id.team2);
		btn3 = (ImageView) findViewById(R.id.team3);
		btn4 = (ImageView) findViewById(R.id.team4);
		

		edit1 = (EditText) findViewById(R.id.name1);
		edit2 = (EditText) findViewById(R.id.name2);
		edit3 = (EditText) findViewById(R.id.name3);
		edit4 = (EditText) findViewById(R.id.name4);
		
		switch (game.no_of_teams)
		{
		case 2: btn3.setVisibility(View.GONE);
				btn4.setVisibility(View.GONE); 
				edit3.setVisibility(View.GONE);
				edit4.setVisibility(View.GONE);
				break;
		case 3: btn4.setVisibility(View.GONE); 
				edit4.setVisibility(View.GONE);
				break;
		}
		
		
		start.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View v) {
				  load();			
				  Intent gotoboard = new Intent(StartGame2.this, GameBoard.class);
				  gotoboard.putExtra("Game", game);
				  startActivity(gotoboard);
			  }
			});
		
		btn1.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View v) {
				  
				  showDial(1);				
			  }
			});
		
		btn2.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View v) {
				  
				  showDial(2);				
			  }
			});
		
		btn3.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View v) {
				  
				  showDial(3);				
			  }
			});
		
		btn4.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View v) {
				  
				  showDial(4);				
			  }
			});
		
		
		
	}
	
	public void changeColor(int currentTeam, int new_color)
	{
		btn1 = (ImageView) findViewById(R.id.team1);
		btn2 = (ImageView) findViewById(R.id.team2);
		btn3 = (ImageView) findViewById(R.id.team3);
		btn4 = (ImageView) findViewById(R.id.team4);
		
		switch (currentTeam)
		{
		case 1: btn1.setBackgroundColor(new_color);
				break;
			
		case 2: btn2.setBackgroundColor(new_color);
				break;
			
		case 3: btn3.setBackgroundColor(new_color);
				break;
			
		case 4: btn4.setBackgroundColor(new_color);
				break;
		
		}
		
	}
	
	public void showDial(final int currentTeam)
	{
		// custom dialog
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog);
		dialog.setTitle("Team  " + currentTeam + " color");

		
		ImageView c1 = (ImageView) dialog.findViewById(R.id.color1);  // lime 
		ImageView c2 = (ImageView) dialog.findViewById(R.id.color2);  // cyan
		ImageView c3 = (ImageView) dialog.findViewById(R.id.color3);  // pink
		ImageView c4 = (ImageView) dialog.findViewById(R.id.color4);  // orange
		ImageView c5 = (ImageView) dialog.findViewById(R.id.color5);  // teal
		ImageView c6 = (ImageView) dialog.findViewById(R.id.color6);  // violet
		ImageView c7 = (ImageView) dialog.findViewById(R.id.color7);  // red
		ImageView c8 = (ImageView) dialog.findViewById(R.id.color8); // brown
		
		
		
		
		c1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				int col = getResources().getColor(R.color.Lime);
				changeColor(currentTeam, col);
				dialog.dismiss();
			}
		});
		c2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				int col = getResources().getColor(R.color.Cyan);
				changeColor(currentTeam, col);
				dialog.dismiss();
			}
		});
		c3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				int col = getResources().getColor(R.color.Pink);
				changeColor(currentTeam, col);
				dialog.dismiss();
			}
		});
		c4.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				int col = getResources().getColor(R.color.Orange);
				changeColor(currentTeam, col);
				dialog.dismiss();
			}
		});
		c5.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				int col = getResources().getColor(R.color.Teal);
				changeColor(currentTeam, col);
				dialog.dismiss();
			}
		});
		c6.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				int col = getResources().getColor(R.color.Violet);
				changeColor(currentTeam, col);
				dialog.dismiss();
			}
		});
		c7.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				int col = getResources().getColor(R.color.Red);
				changeColor(currentTeam, col);
				dialog.dismiss();
			}
		});
		c8.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				int col = getResources().getColor(R.color.Brown);
				changeColor(currentTeam, col);
				dialog.dismiss();
			}
		});
		

		dialog.show();
	
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
		getMenuInflater().inflate(R.menu.start_game2, menu);
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
	
	public void load()
	{
		btn1 = (ImageView) findViewById(R.id.team1);
		btn2 = (ImageView) findViewById(R.id.team2);
		btn3 = (ImageView) findViewById(R.id.team3);
		btn4 = (ImageView) findViewById(R.id.team4);
		

		edit1 = (EditText) findViewById(R.id.name1);
		edit2 = (EditText) findViewById(R.id.name2);
		edit3 = (EditText) findViewById(R.id.name3);
		edit4 = (EditText) findViewById(R.id.name4);
		
		switch (game.no_of_teams)
		{
		case 4: game.names[0] = edit1.getText().toString();
				game.names[1] = edit2.getText().toString();
				game.names[2] = edit3.getText().toString();
				game.names[3] = edit4.getText().toString();
				
				ColorDrawable drawable = (ColorDrawable) btn1.getBackground();
				game.colors[0] = drawable.getColor();
				
				drawable = (ColorDrawable) btn2.getBackground();
				game.colors[1] = drawable.getColor();
				
				drawable = (ColorDrawable) btn3.getBackground();
				game.colors[2] = drawable.getColor();
				
				drawable = (ColorDrawable) btn3.getBackground();
				game.colors[3] = drawable.getColor();
				break;
				
		case 3: game.names[0] = edit1.getText().toString();
				game.names[1] = edit2.getText().toString();
				game.names[2] = edit3.getText().toString();
				drawable = (ColorDrawable) btn1.getBackground();
				game.colors[0] = drawable.getColor();
				
				drawable = (ColorDrawable) btn2.getBackground();
				game.colors[1] = drawable.getColor();
				
				drawable = (ColorDrawable) btn3.getBackground();
				game.colors[2] = drawable.getColor();
				
				break;
		case 2: game.names[0] = edit1.getText().toString();
				game.names[1] = edit2.getText().toString();
				drawable = (ColorDrawable) btn1.getBackground();
				game.colors[0] = drawable.getColor();
				
				drawable = (ColorDrawable) btn2.getBackground();
				game.colors[1] = drawable.getColor();
				break;
		}
		
	}
	

}
