import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import doodlepad.Oval;
import doodlepad.Pad;
import doodlepad.Text;

public class Snake extends Pad{
	private ArrayList<Oval> bod;
	private Oval head, apple;
	private int numApples;
	private final int WIDTH = 30;
	private boolean headRight, headLeft, headUp, headDown;
	
	public Snake(Color a) {
		super("Snake",500,500,Color.LIGHT_GRAY);
		head = new Oval(250,250,WIDTH,WIDTH,this);
		head.setFillColor(Color.CYAN);
		headRight = true;
		
		
		int w = 250;
		bod = new ArrayList<Oval>();
		for(int i=0; i<3; i++) {
			w-=WIDTH;
			bod.add(0,new Oval(w,250,WIDTH,WIDTH,this));
			bod.get(0).setFillColor(a);
		}
		
		makeApple();
	}
	
	public boolean intersectApple() {
		if(head.intersects(apple))
			return true;
		for(Oval b: bod)
			if(b.intersects(apple))
				return true;
		return false;
			
	}
	
	public void makeApple() {
		int x = (int)(Math.random()*(450-(WIDTH*2)+1))+WIDTH*2;
		int y = (int)(Math.random()*(450-(WIDTH*2)+1))+WIDTH*2;
		apple = new Oval(x,y,WIDTH,WIDTH,this);
		if(intersectApple()) {
			removeShape(apple);
			makeApple();
		}
		else {
			apple.setFillColor(Color.RED);
		}
	}
	
	public boolean ateApple() {
		if(head.intersects(apple)) {
			numApples++;
			removeShape(apple);
			return true;
		}
		return false;
	}
	
	public void moveSnake(int changeX, int changeY) {
		double ox = head.getX();
		double oy = head.getY();
		
		head.move(changeX,changeY);
		
		if(ateApple()) {
			Color b = bod.get(0).getFillColor();
			bod.add(new Oval(ox,oy,WIDTH,WIDTH,this));
			bod.get(bod.size()-1).setFillColor(b);
			makeApple();
		}
		else {
			Color b = bod.get(0).getFillColor();
			removeShape(bod.remove(0));
			bod.add(new Oval(ox,oy,WIDTH,WIDTH,this));
			bod.get(bod.size()-1).setFillColor(b);
		}
	}
	
	public void moveRight() {
		if(headLeft)
			return;
		headLeft= false;
		headUp = false;
		headDown = false;
		headRight = true;
		
		moveSnake(WIDTH, 0);
	}
	
	public void moveLeft() {
		if(headRight)
			return;
		headLeft= true;
		headUp = false;
		headDown = false;
		headRight = false;
		
		moveSnake(-WIDTH, 0);
	}
	
	public void moveUp() {
		if(headDown)
			return;
		headLeft= false;
		headUp = true;
		headDown = false;
		headRight = false;
		
		moveSnake(0, -WIDTH);
	}
	public void moveDown() {
		if(headUp)
			return;
		headLeft= false;
		headUp = false;
		headDown = true;
		headRight = false;
		
		moveSnake(0, WIDTH);
	}
	
	public boolean gameOver() {
		
		if(head.getX()<=0+5 || head.getX()>=500-5 || head.getY()<=0+5 || head.getY()>=500-5)
			return true;
		for(int i=0; i<bod.size()-1; i++)
			if(bod.get(i).intersects(head))
				return true;
		return false;
	}
	
	public void onKeyPressed(String keyText, String e) {
		if(!gameOver()) {
			if(keyText.equals("D"))
				moveRight();
			if(keyText.equals("A"))
				moveLeft();
			if(keyText.equals("W"))
				moveUp();
			if(keyText.equals("S"))
				moveDown();
		}
	}
	
	public void move(Clip clip) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException{

        boolean u, d, l, r;
        while(!gameOver()) {
            if(headRight) moveRight();
            else if(headLeft) moveLeft();
            else if(headUp) moveUp();
            else if(headDown) moveDown();
            TimeUnit.MILLISECONDS.sleep(250);
        }
      //ends theme music and plays game over sound after snake dies
        clip.stop();
        clip.close();
        File gameOverPath = new File("gameover.wav");
        AudioInputStream s2 = AudioSystem.getAudioInputStream(gameOverPath);
        clip.open(s2);
        clip.start();

        //waits 6 seconds after playing game over sound, then ends all audio objects
        TimeUnit.SECONDS.sleep(2);
        clip.stop();
        clip.close();
        setEventsEnabled(false);
        Text t;
        if (numApples == 1)
            t = new Text(numApples +" APPLE EATEN!",30,50,50);
        else
            t = new Text(numApples +" APPLES EATEN!",30,50,50);

    }



    public static void main(String[] args) throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException {
        //theme music setup
        Clip clip = AudioSystem.getClip();
        File themePath = new File("theme.wav");
        AudioInputStream s1 = AudioSystem.getAudioInputStream(themePath);
        clip.open(s1);
        clip.start();

        //starts the game
        Color bodyC = new Color(93, 200, 253);
        Snake game = new Snake(bodyC);
        game.move(clip);

        
    }


}
