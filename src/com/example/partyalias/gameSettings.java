package com.example.partyalias;

import java.io.Serializable;

public class gameSettings implements Serializable{ 
	
	private static final long serialVersionUID = 1L;

	public String language;
	public int difficulty; // 0, 1, 2
	public int duration;
	public int no_of_teams;
	public String names[]; 
	public int colors[];
	public int scores[];
	public int current;
	
	public gameSettings()
	{
		language = "ENGLISH";
		difficulty = 0;
		duration = 60;
		no_of_teams = 4;
		names = new String[] {"Team1","Team2", "Team3", "Team4"};
		colors = new int[4];
		scores = new int[4];
		for (int i=0;i<4;i++)
		{
			scores[i] = 0;
		}
		current = 0;
	}
	
	public gameSettings(String lang, int diff, int dur, int no, String name[], int col[], int sc[], int cur)
	{
		language = lang;
		difficulty = diff;
		duration = dur;
		no_of_teams = no;
		for(int i=0; i<no;i++)
		{
			names[i] = name[i];
		}
		for(int i=0;i<no;i++)
		{
			colors[i] = col[i]; 
		}
		for(int i=0;i<no; i++)
		{
			scores[i]=sc[i];     			
		}
		current = cur;
		
	}
}
