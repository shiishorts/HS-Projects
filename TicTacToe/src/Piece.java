import java.awt.Color;
import doodlepad.Oval;

public class Piece extends Oval{
	private Board board;
	private static int numPieces;
	
	public Piece(double x,double y,int r, Board c) {
		super(x,y,r,r);
		board = c;
		setFillColor(Color.GRAY);
	}
	
	public boolean equals(Piece p) {
		return this.getColor().equals(p.getColor());
	}
	
	public Color getColor() {
		return getFillColor();
	}
	
	public void onMouseClicked(double x, double y, int b) {
		if(getFillColor().equals(Color.GRAY)) {
			numPieces++;
			if(numPieces%2!=0)
				setFillColor(Color.RED);
			else
				setFillColor(Color.BLUE);
			
			if(!((TicTacToe)board).gameOver() && numPieces%2!=0)
				((TicTacToe)board).computerTurn();
		}
		
		else
			System.out.println("INVALID");
	}
	
	public static int numPieces() {
		return numPieces;
	}
	
}
