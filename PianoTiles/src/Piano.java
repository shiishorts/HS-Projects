import doodlepad.Pad;
import java.io.IOException;
import doodlepad.Sound;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;

import doodlepad.Text;

public class Piano extends Pad{
	private Keys[][] key;
	private int score;
	
	
	public Piano() {
		super(400,650);
		key = new Keys[4][4];
		enterKeys();
	}
	
	public void enterKeys() {
		for(int i=0; i<key.length; i++) {
			int r = (int)(Math.random()*key[i].length);
			for(int k=0; k<key[i].length; k++)
				if(k!=r)
					key[i][k] = new Keys(k*100, i*150, true);
				else
					key[i][r]=new Keys(r*100, i*150, false);
		}
	}
	
	public void onKeyPressed(String keyText, String j) {
		String c = keyText;
		int d=-1;
		String e = "";
		
		switch (c) {
		case "D":
			d=0;
			e= "high";
			break;
		case "F":
			d=1;
			e="high2";
			break;
		case "J":
			d=2;
			e="mid";
			break;
		case "K":
			d=3;
			e="low";
			break;
		}
		
		if(d>-1 && gameOver(3,d)) {
			Text t = new Text("Piano isn't for you :( Score: "+score, 10,610, 25);
			writeToTxt();
			setEventsEnabled(false);
		}
		else if(d>-1){
			score++;
			//Sound note = new Sound(e+".wav");
			//note.play();
			moveTiles(3,d);
		}
		else
			System.out.println("Not a valid key!!!!!!");
	}
	
	public void moveTiles(int r, int c) {
		key[r][c].setFillColor(Color.WHITE);
		for(int i=r; i>0; i--) {
			key[i][findBlack(key[i-1])].setFillColor(Color.BLACK);
			key[i-1][findBlack(key[i-1])].setFillColor(Color.WHITE);
		}
		key[0][(int)(Math.random()*key[0].length)].setFillColor(Color.BLACK);
	}
	
	public int findBlack(Keys[] a) {
		for(int i=0; i<a.length; i++)
			if(a[i].getFillColor().equals(Color.BLACK))
				return i;
		return -1;
	}
	
	public boolean gameOver(int r, int c) {
		if(key[r][c].getFillColor().equals(Color.WHITE)) {
			key[r][c].setFillColor(Color.RED);
			return true;	
		}
		return false;
	}
	
	
	public void writeToTxt() {
		
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(".\\pianoScores.txt", true));
			w.write(""+score);
			w.newLine();
			w.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
