import java.awt.Color;
import java.util.Scanner;

  
public class PhotoShop
{
  /** Method to show the original picture.**/
	public static void showOriginal(String picture)
	  {
	    Picture p = new Picture(picture);
	    p.explore();
	   
	  }
	
  /** Method to negate picture */
  public static void negate(String picture)
  {
    Picture p = new Picture(picture);
    p.negate();
    p.explore();
  }
  
  
  public static void borderPicture(String a, int r, int g, int b, int s){
	  Picture p=new Picture(a);
	  p.borderPicture(s, new Color(r,g,b));
	  p.explore();
  }
  
  public static void zeroBlue(String a) {
	  Picture p = new Picture(a);
	  p.zeroBlue();
	  p.explore();
  }
  
  public static void grayScale(String a) {
	  Picture p = new Picture(a);
	  p.grayScale();
	  p.explore();
  }
  
  public static void mirrorVertical(String a) {
	  Picture p = new Picture(a);
	  p.mirrorVertical();
	  p.explore();
  }
  
  public static void mirrorHorizontal(String a) {
	  Picture p = new Picture(a);
	  p.mirrorHorizontal();
	  p.explore();
  }
  
  public static void mirrorRedHorizontal(String a) {
	  Picture p = new Picture(a);
	  p.mirrorRedHorizontal();
	  p.explore();
  }
  
//Make sure that you test all of the methods you wrote in the    //Picture class.  You'll need to write add more methods to this //class and to add code to the main. 
  
  public static void main(String[] args)
  {
	String picture="image2.jpeg";
	
	Scanner kb = new Scanner(System.in);
	System.out.println("Choose a number 1-8: \n 1) Original \n 2) Zero Blue \n 3) Gray Scale "
			+ "\n 4) Negate \n 5) Mirror Vertical \n 6) Border \n 7) Mirror Horizontal \n 8) Mirror Red Horizontal");
	int e = kb.nextInt();	
	
	if(e==1)
		showOriginal(picture);
	else if(e==2)
		zeroBlue(picture);
	else if(e==3)
		grayScale(picture);
	else if(e==4)
		negate(picture);
	else if(e==5)
		mirrorVertical(picture);
	else if(e==6) {
		System.out.println("Choose rgb numbers (0-255) and a border size");
		int r = kb.nextInt();
		int g = kb.nextInt();
		int b = kb.nextInt();
		int s = kb.nextInt();
		
		borderPicture(picture,r,g,b,s);	
	}
	else if(e==7)
		mirrorHorizontal(picture);
	else if(e==8)
		mirrorRedHorizontal(picture);
	else 
		System.out.println("not a valid choice young one");
  }
}