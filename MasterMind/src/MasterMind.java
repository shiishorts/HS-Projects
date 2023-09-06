
public class MasterMind {
	private Code code;
	
	public MasterMind() {
		code = new Code();
		playGame();
	}
	private void playGame() {
		boolean gameOver = false;
		while(!gameOver) {
			Guess g =new Guess();
			int w = code.whitePegs(g);
			int r = code.redPegs(g);
			System.out.println("Red pegs: "+r+" White pegs: "+w);
			if(r==4 && Guess.getNumGuesses()<8) {
				gameOver=true;
				System.out.println("You win! It took you "+Guess.getNumGuesses()+" tries!");
			}
			if(Guess.getNumGuesses()>7) {
				gameOver=true;
				System.out.println("You Lost. Here is the "+code.toString());
			}
		}
		
	}
	
	
}
