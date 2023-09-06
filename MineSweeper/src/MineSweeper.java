import java.util.Scanner;

import doodlepad.Image;
import doodlepad.Pad;
import doodlepad.Sound;
import doodlepad.Text;

public class MineSweeper extends Pad {
	private Cell[][] cells;
	private final int MINES;
	private static final int WIDTH = 576;
	
	public MineSweeper(int a) {
		super(WIDTH,WIDTH);
		if(a!=1) {
			MINES = 40;
			cells = new Cell[16][16];
		}
		else {
			MINES = 10;
			cells = new Cell[9][9];
		}
		setUp();
	}
	
	public boolean inBounds(int row, int col) {
		return row>=0 && row<=cells.length-1 && col>=0 && col<=cells.length-1;
	}
	
	public int countMines(int row, int col) {
		int mines = 0;
		if(cells[row][col].isMine())
			return -1;
		for(int k=row-1; k<=row+1; k++)
			for(int i=col-1; i<=col+1; i++) {
				if(inBounds(k,i)&&cells[k][i].isMine())
					mines++;
		}
		
		return mines;
	}

	
	public void fillArray() {
		int a = WIDTH/cells.length;
		for(int i=0; i<cells.length; i++)
			for(int k=0; k<cells.length; k++)
				cells[i][k] = new Cell(a, i, k, this);
	}
	
	public int getNumSafeCells() {
		return (cells.length*cells.length)-MINES;
	}
	
	public void placeMines() {
		int r1 = (int)(Math.random()*cells.length);
		int r2 = (int)(Math.random()*cells.length);
		int c = 1;
		while(c<=MINES) {
			while(cells[r1][r2].isMine()) {
				r1= (int)(Math.random()*cells.length);
				r2= (int)(Math.random()*cells.length);
			}
			cells[r1][r2].setMine();
			c++;
		}
		
	}
	
	public void setAdjacentCount() {
		for(int i=0; i<cells.length; i++)
			for(int k=0; k<cells.length; k++)
				cells[i][k].setCount(countMines(i,k));
	}
	
	public void setUp() {
		fillArray();
		placeMines();
		setAdjacentCount();
	}
	
	public void gameOver(boolean a) {
		String s = "YOU WON!";
		if(a) {
			s = "YOU LOST";
			Image b = new Image("crying.jpg", 0, 0, 576, 576);
			Sound d = new Sound("kaboom.wav");
			d.play();
		}
		else {
			Image an = new Image("dog.jpg",0,0, 576,576);
		}
		
		Text t = new Text(s, 10, 100, 100);
	}
	
	public void sweep(int row, int col) {
		for(int i=row-1; i<row+2; i++)
			for(int k=col-1; k<col+2; k++)
				if(inBounds(i,k)) //&& cells[r][c].getFillColor().equals(Color.GRAY)
					cells[i][k].onMouseClicked(0,0,0);
		
	}
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("1 for easy mode any other number for hard >:)))");
		int a = kb.nextInt();
		MineSweeper coolBeans = new MineSweeper(a);
	}
}
