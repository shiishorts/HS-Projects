import doodlepad.Text;

public class Twenty48 extends FourByFour{
	private Tile2048[][] tiles;
	
	public Twenty48() {
		tiles = new Tile2048[4][4];
		addAnother();
	}
	
	public void addAnother() {
		int r = (int)(Math.random()*4);
		int c = (int)(Math.random()*4);
		while(tiles[r][c]!=null) {
			r = (int)(Math.random()*4);
			c = (int)(Math.random()*4);
		}
		//System.out.println("Row: "+ r + " Column: "+c);
		tiles[r][c] = new Tile2048(getPosX(c), getPosY(r), getTileWidth());
	}
	public void move(int oRow, int oCol, int nRow, int nCol) {
		Tile2048 t = tiles[oRow][oCol];
		tiles[nRow][nCol]=t;
		tiles[oRow][oCol] = null;
		t.setLocation(getPosX(nCol), getPosY(nRow));
		//System.out.println("Loc1: "+oRow+" "+oCol + " Loc2: "+nRow+" "+nCol);
	}
	
	public void merge(int r1, int c1, int r2, int c2) {
		removeShape(tiles[r1][c1]);
		removeShape(tiles[r1][c1].getText());
		tiles[r1][c1]=null;
		tiles[r2][c2].doubleValue(getPosX(c2),getPosY(r2));
		Tile2048.decreaseTiles();
	}
	
	public boolean moveRight() {
		int f =0;
		boolean y = false;
		
		for(int i=0; i<4; i++)
			for(int k=2; k>-1;k--) {
				if(tiles[i][k]!=null) {
					f=k+1;
					while(f<4 && tiles[i][f]==null)
						f++;
					if(f<4 && tiles[i][f]!= null && tiles[i][f].getValue()==tiles[i][k].getValue()) {
						merge(i,k,i,f);
						y = true;
					}
					else if(tiles[i][f-1]==null){
						move(i,k,i,f-1);
						y=true;
					}
				}
			}
		return y;
	}
	
	public boolean moveLeft() {
		int f =0;
		boolean y = false;
		
		for(int i=0; i<4; i++)
			for(int k=1; k<4; k++) {
				if(tiles[i][k]!=null) {
					f=k-1;
					while(f>-1 && tiles[i][f]==null)
						f--;
					if(f>-1 && tiles[i][f]!=null && tiles[i][f].getValue()==tiles[i][k].getValue()) {
						merge(i,k,i,f);
						y=true;
					}
					else if(tiles[i][f+1]==null) {
						move(i,k,i,f+1);
						y=true;
					}
				}
			}
		return y;
	}
	
	public boolean moveUp() {
		int f =0;
		boolean y = false;
		
		for(int i=0; i<4; i++)
			for(int k=0; k<4; k++) {
				if(tiles[k][i]!=null) {
					f=k-1;
					while(f>-1 && tiles[f][i]==null)
						f--;
					if(f>-1 && tiles[f][i]!=null && tiles[f][i].getValue()==tiles[k][i].getValue()) {
						merge(k,i,f,i);
						y=true;
					}
					else if(tiles[f+1][i]==null) {
						move(k,i,f+1,i);
						y=true;
					}
				}
			}
			
		return y;
	}
	
	public boolean moveDown() {
		int f =0;
		boolean y = false;
		
		for(int i=0; i<4; i++)
			for(int k=2; k>-1; k--)
				if(tiles[k][i]!=null) {
					f=k+1;
					while(f<4 && tiles[f][i]==null)
						f++;
					if(tiles[f-1][i]==null) {
						move(k,i,f-1,i);
						y=true;
					}
					else if(f<4 && tiles[f][i].getValue()==tiles[k][i].getValue()) {
						merge(k,i,f,i);
						y=true;
					}
				}
		return y;
	}
	
	public boolean noMoves() {
		int t =0;
		int c = 0;
		if(Tile2048.getNumTiles()==16) {
			for(int i=0; i<3; i++)
				for(int k=0; k<3; k++) {
					t= tiles[i][k].getValue();
					if(t==tiles[i][k+1].getValue() || i!=0 && t==tiles[i-1][k].getValue())
						c++;
						}
			for(int p=0; p<3; p++)
				if(tiles[p][3].getValue()==tiles[p+1][3].getValue())
					c++;
			if(c==0)
				return true;
		}
		
		return false;
	}
	
	public void onKeyPressed(String keyText, String w) {
		
		if(keyText.equals("W") && moveUp())
			addAnother();
		if(keyText.equals("S") && moveDown())
			addAnother();
		if(keyText.equals("A") && moveLeft())
			addAnother();
		if(keyText.equals("D") && moveRight())
			addAnother();
		
		if(noMoves()) {
			Text t = new Text("GAME\nOVER",30,80,80);
			setEventsEnabled(false);
		}
		
	}
	
}
