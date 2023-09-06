import java.util.Arrays;
import java.util.Scanner;

public class Sequence {
	private int arr[];
	
	public boolean contains(int a) {
		for(int i=0; i<arr.length; i++)
			if(arr[i]==a)
				return true;
		return false;
	}
	
	public Sequence(boolean b) {
		arr=new int[4];
		int n2 = (int)(Math.random()*7)+1;
		Scanner kb = new Scanner(System.in);
		if(b==false) {
			for(int i=0; i<arr.length; i++) {
				if(!contains(n2))
					arr[i]=n2;
				else
					i--;
				n2=(int)(Math.random()*7)+1;
			}
		}
		else {
			System.out.println("Put in 4 numbers from 1-7");
			for(int k=0; k<arr.length; k++) {
				arr[k]=kb.nextInt();
			}
		}
	}
	public int[] getSequence() {return arr;}
	public String toString() {return Arrays.toString(arr);}
	
}
