import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.randomtower.engine.entity.Entity;


public class Player extends Entity{

	public Player(float x, float y) throws SlickException {
		super(x, y);
		
		setGraphic(new Image("res/sticky1.png"));
	}

}
