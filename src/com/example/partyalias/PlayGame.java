package com.example.partyalias;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.io.IOException;
import java.io.InputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.example.partyalias.XMLDOMParser;
import android.content.res.AssetManager;
import android.widget.Toast;

public class PlayGame extends Activity {

	public String category = "BEGINNER";
	public String language = "ENGLISH";
	public String[] wordList;
	
	// XML node names
	static final String ATTR_LANGUAGE = "lang";
	static final String ATTR_CATEGORY = "categ";
	static final String NODE_LANGUAGE = "language";
	static final String NODE_CATEGORY = "category";
	static final String NODE_WORD = "word";	
	static final String NODE_WORDLIST = "wordlist";	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);
		parseXMLfile();
		
		
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
							wordList = new String[wordsNodeList.getLength()];
							for(int k=0; k<wordsNodeList.getLength(); k++)
								{
								Element wordNode = (Element) wordsNodeList.item(k);
								wordList[k] = wordNode.getTextContent();
								Toast.makeText(getApplicationContext(),wordList[k],
										Toast.LENGTH_LONG).show();
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
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_game, menu);
		return true;
	}

}
