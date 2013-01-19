import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class LowSpeaker extends Speaker{
	private final double LIMIT = (3.14 / 2);
	boolean increment = true;
	public LowSpeaker(int x, int y){
		REG_COLOR = new Color(255, 220, 200);
		setLoc(x, y);
		counter = 0;
	}

	/**
	 * Creates one regular bullet.
	 */
	public void spawn(){
                for (int i = 0; i < 5; ++i){
                    Image img = Main.lowNotes.get((int)(Math.random() * Main.lowNotes.size()));
                    DrawPanel.addBullet(new LowBullet(xLoc, yLoc, (3.14 / 4) + ((i + .5) / 10.0) * (3.14 / 1), img));
                }
	}
        public void animate(){}
}
