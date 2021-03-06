package com.example.partyalias;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class StartActivity extends Activity {

<<<<<<< HEAD
	public String language="ENGLISH";
	public boolean sound = true;
	
	
=======
	SharedPreferences sharedpreferences;
	public static final String MyPREFERENCES = "MyPrefs" ;
	public String language;
>>>>>>> 49377adfd91a5092ad351485609e5179bacdd416
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		if (sharedpreferences.contains("language"))
	      {
			
	         language = sharedpreferences.getString("language", "");
	      }
		else
		{
			language="ENGLISH";
		}

		final Button StartButton = (Button) findViewById(R.id.btnStart);
		StartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goto2 = new Intent(getBaseContext(), StartGame1.class);
                goto2.putExtra("language", language);
                goto2.putExtra("sound", sound);
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
            	showLanguageDialog();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        
	        case R.id.action_share:
	                // se da share
	            return true;
	        case R.id.action_sound:
	        	if (sound==true)
	        	{
	        		item.setIcon(getResources().getDrawable(R.drawable.nosound));
	        		sound = false;
	        	}
	        	else
	        	{
	        		item.setIcon(getResources().getDrawable(R.drawable.sound));
	        		sound = true;
	        	}
	        	
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	    
	}
	
	
	void showLanguageDialog()
	{
		int selected;
		if(language=="ROMANIAN")
			selected=1;
		else selected=0;
		AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                StartActivity.this, 1);
        builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Choose language");
        
        
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
        		StartActivity.this,
                android.R.layout.select_dialog_singlechoice);
        
        arrayAdapter.add("English");
        arrayAdapter.add("Romanian");
        
        builderSingle.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builderSingle.setSingleChoiceItems(arrayAdapter, selected,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	switch(which)
                        {
                        case 0: language="ENGLISH";
                        		break;
                        case 1: language="ROMANIAN";
                				break;
                        }
                    	dialog.dismiss();
                    	save();
                    }
                });
        builderSingle.show();
        
	}
	
	public void save()
	{
	    Editor editor = sharedpreferences.edit();
	    editor.putString("language", language);
	    editor.commit(); 
	}

}
