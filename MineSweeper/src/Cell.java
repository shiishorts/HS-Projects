import java.awt.Color;

import doodlepad.Pad;
import doodlepad.Rectangle;
import doodlepad.Text;

public class Cell extends Rectangle {
	private int row, col, adjacentMines;
	private MineSweeper board;
	private boolean isMine = false;
	private static int safe; 
	
	public Cell(int width, int r, int c, MineSweeper b) {
		//switched x and y???
		super(r*width, c*width, width, width);
		board = b;
		row = r;
		col = c;
		setFillColor(Color.GRAY);
	}
	
	public boolean isMine() { 
		return isMine;
	}
	
	public void setMine() {
		isMine = true;
	}
	
	public void setCount(int a) {
		adjacentMines = a;
	}
	
	public void onMouseClicked(double a, double b, int c) {
		//you can put the finding the mine at the end
		if (adjacentMines == -1)
			board.gameOver(true);
		if(getFillColor().equals(Color.GRAY)) {
			safe++;
			setFillColor(Color.WHITE);
		}
		else 
			return;
		
		if(board.getNumSafeCells()==safe)
			board.gameOver(false);
		else if(adjacentMines==0)
			board.sweep(row, col);
		else {
			Text t = new Text(""+adjacentMines, row*getWidth()+10, col*getWidth()+20, 20, board);
		}
	}
}
