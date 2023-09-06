import java.awt.Color;
import doodlepad.Rectangle;
import doodlepad.Text;

public class Tile2048 extends Tile{
	private int r;
	private int g;
	private int b;
	private static int tiles;
	
	public Tile2048(int x, int y, int s) {
		super(x,y,s);
		setValue(2);
		
		if(tiles<7) {
			if((Math.random()) < (double)3/10) {
				r=20;
				b=235;
			}
			else {
				r=0;
				b=255;
			}
		}
		
		if(tiles>6) {
			if(Math.random() < (double)3/10) {
				r=0;
				b=255;
			}
			else {
				r=20;
				b=235;
			}
		}
		
		if(r>0)
			setValue(4);
		setColor(r,g,b);
		Text t = getText();
		tiles++;
	}
	
	public void doubleValue(int x, int y) {
		setValue(getValue()*2);
		r+=40;
		b-=40;
		setColor(r,g,b);
		moveText(x,y);
	}
	public static int getNumTiles() { return tiles; }
	public static void decreaseTiles() { tiles--; }
}
