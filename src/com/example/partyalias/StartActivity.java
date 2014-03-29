package com.example.partyalias;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		Toast.makeText(getBaseContext(), "gie", Toast.LENGTH_LONG).show();
		
		final Button StartButton = (Button) findViewById(R.id.btnStart);
		StartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goto2 = new Intent(getBaseContext(), StartGame1.class);
                startActivity(goto2);
            }
        });
		
		final Button Instructions = (Button) findViewById(R.id.btnInstructions);
		Instructions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoInstr = new Intent(getBaseContext(), Instructions.class);
                startActivity(gotoInstr);
            }
        });
		
		final Button Share = (Button) findViewById(R.id.btnShare);
		Share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoShare = new Intent(getBaseContext(), PlayGame.class);
                startActivity(gotoShare);
            }
        });
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

}
