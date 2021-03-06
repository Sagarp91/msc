import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class HighSpeaker extends Speaker{
	private final Color REG_COLOR = new Color(255, 150, 150);
	public HighSpeaker(int x, int y){
		setLoc(x, y);
		counter = 0;
		angleRate = (3.14 / 80);
                LIMIT = (3.14 / 6);
	}

	public void spawn(){
		Image img = Main.highNotes.get((int)(Math.random() * Main.highNotes.size()));
		DrawPanel.addBullet(new HighBullet(xLoc, yLoc, counter / 2 + (3.14 / 2) + aim, img));
	}
}
