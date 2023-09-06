import java.awt.Color;
import doodlepad.Pad;
import doodlepad.Rectangle;

public class Keys extends Rectangle{
	private static final double w = 100;
	private static final double h = 150;
	
	
	public Keys(double x, double y, boolean a) {
		super(x, y, w, h);
		if(a) 
			setFillColor(Color.WHITE);
		else
			setFillColor(Color.BLACK);
	}
	
}
