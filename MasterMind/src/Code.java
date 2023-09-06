
public class Code extends Sequence{
	public Code() {
		super(false);
	}
	public String toString() {
		return "Code: "+super.toString();
	}
	
	public int redPegs(Guess g) {
		int guess[] = g.getSequence();
		int me[] = this.getSequence();
		int c =0;
		for(int i=0; i<me.length;i++)
			if(me[i]==guess[i])
					c++;
		return c;
	}
	
	public int whitePegs(Guess g) {
		int code[]=getSequence();
		int c=0;
		for(int i=0; i<code.length; i++)
			if(g.contains(code[i]))
				c++;
		return c-redPegs(g);
	}
}
