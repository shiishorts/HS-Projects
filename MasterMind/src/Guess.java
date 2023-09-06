
public class Guess extends Sequence {
	private static int num;
	
	public Guess() {
		super(true);
		num++;
	}
	public static int getNumGuesses() {
		return num;
	}
	public String toString() {
		return "Guess: "+super.toString();
	}
	
}
