import java.awt.Color;

import doodlepad.Text;

public class TicTacToe extends Board{
	private Piece pieces[][];
	
	public TicTacToe() {
		super(3,3);
		pieces = new Piece[3][3];
		putPieces();
	}
	
	public void putPieces() {
		int width = getTileWidth();
		for(int i=0; i<pieces.length; i++)
			for(int k=0; k<pieces[i].length; k++)
				pieces[i][k]= new Piece(getPosX(k), getPosY(i),width,this);
	}
	
	public void endGame(Color co) {
		String s = "Cat's Game";
		if(co.equals(Color.RED))
			s= "Red Won!";
		else if (co.equals(Color.BLUE))
			s="Blue Won!";
		
		Text t = new Text(s, 50, 50, 40);
		
		this.setEventsEnabled(false);
	}
	
	public void computerTurn() {
		int x = (int)(Math.random()*3);
		int y = (int)(Math.random()*3);
		Color b= Color.RED;
		
		while(!pieces[y][x].getColor().equals(Color.GRAY)) {
			x = (int)(Math.random()*3);
			y = (int)(Math.random()*3);
		}
		
		//so before we get started on explaining this I just want to say
		//that there may be an easier solution to this but 
		//my brain no longer functions :cries:
		//if u want to win you can comment out starting from here
		
		if(pieces[0][0].getColor().equals(b)||pieces[0][2].getColor().equals(b)||pieces[2][0].getColor().equals(b)||pieces[2][2].getColor().equals(b))
			if(pieces[1][1].getColor().equals(Color.GRAY)) {
				pieces[1][1].onMouseClicked(0,0,0);
				return;
			}
			
		for(int i=0; i<3; i++) {
			if(pieces[i][2].getColor().equals(Color.GRAY)&&pieces[i][0].getColor().equals(b)&&pieces[i][1].getColor().equals(b)) {
				pieces[i][2].onMouseClicked(0,0,0);
				return;
			}
			if(pieces[i][1].getColor().equals(Color.GRAY)&&pieces[i][2].getColor().equals(b)&&pieces[i][0].getColor().equals(b)) {
				pieces[i][1].onMouseClicked(0,0,0);
				return;
			}
			if(pieces[i][0].getColor().equals(Color.GRAY)&&pieces[i][1].getColor().equals(b)&&pieces[i][2].getColor().equals(b)) {
				pieces[i][0].onMouseClicked(0,0,0);
				return;
			}
			if(pieces[2][i].getColor().equals(Color.GRAY)&&pieces[0][i].getColor().equals(b)&&pieces[1][i].getColor().equals(b)) {
				pieces[2][i].onMouseClicked(0,0,0);
				return;
			}
			if(pieces[1][i].getColor().equals(Color.GRAY)&&pieces[2][i].getColor().equals(b)&&pieces[0][i].getColor().equals(b)) {
				pieces[1][i].onMouseClicked(0,0,0);
				return;
			}
			if(pieces[0][i].getColor().equals(Color.GRAY)&&pieces[1][i].getColor().equals(b)&&pieces[2][i].getColor().equals(b)) {
				pieces[0][i].onMouseClicked(0,0,0);
				return;
			}
		}
		
		
		if(pieces[1][1].getColor().equals(b)) {
			if(pieces[0][0].getColor().equals(b)&&pieces[2][2].getColor().equals(Color.GRAY)) {
				pieces[2][2].onMouseClicked(0,0,0);
				return;
			}
			if(pieces[2][2].getColor().equals(b)&&pieces[0][0].getColor().equals(Color.GRAY)) {
				pieces[0][0].onMouseClicked(0,0,0);
				return;
			}
		}
		
		if(pieces[0][0].getColor().equals(b)&&pieces[2][2].getColor().equals(b)&&pieces[1][1].getColor().equals(Color.GRAY)) {
			pieces[1][1].onMouseClicked(0,0,0);
			return;
		}
		
		if(pieces[1][1].getColor().equals(b)) {
			if(pieces[0][2].getColor().equals(b)&&pieces[2][0].getColor().equals(Color.GRAY)) {
				pieces[2][0].onMouseClicked(0,0,0);
				return;
			}
			if(pieces[2][0].getColor().equals(b)&&pieces[0][2].getColor().equals(Color.GRAY)) {
				pieces[0][2].onMouseClicked(0,0,0);
				return;
			}
		}
		
		if(pieces[2][0].getColor().equals(b)&&pieces[0][2].getColor().equals(b)&&pieces[1][1].getColor().equals(Color.GRAY)) {
			pieces[1][1].onMouseClicked(0,0,0);
			return;
		}
		//to here
		
		pieces[y][x].onMouseClicked(0,0,0);
	}
	
	public boolean gameOver() {
		int n = 1;
		Color b;
		if(Piece.numPieces()<6)
			return false;
		
		for(int k=0; k<3; k++) {
			b = pieces[0][k].getColor();
			for(int w=1; w<3; w++)
				if(!b.equals(Color.GRAY)&&pieces[w][k].getColor().equals(b))
					n++;
			if(n==3) {
				endGame(b);
				return true;
			}
			n=1;
		}
		
		for(int i=0; i<3; i++) {
			b = pieces[i][0].getColor();
			for(int m=1; m<3; m++)
				if(!b.equals(Color.GRAY)&&pieces[i][m].getColor().equals(b))
					n++;
			if(n==3) {
				endGame(b);
				return true;
			}
			n=1;
		}
		b=pieces[0][0].getColor();
		if(!b.equals(Color.GRAY)) {
			if(pieces[1][1].getColor().equals(b)&&pieces[2][2].getColor().equals(b))
				n+=2;
			if(n==3) {
				endGame(b);
				return true;
			}
		}
		n=1;
		
		b=pieces[2][0].getColor();
		if(!b.equals(Color.GRAY)) {
			if(pieces[1][1].getColor().equals(b)&&pieces[0][2].getColor().equals(b))
				n+=2;
			if(n==3) {
				endGame(b);
				return true;
			}
		}

		
		if(Piece.numPieces()==9) {
			endGame(Color.GREEN);
			return true;
		}
		
		return false; 
	}	
}
