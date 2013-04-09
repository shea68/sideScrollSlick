import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


public class Board extends BasicGame{
	
// Initialize all the global vars needed
private TiledMap map;
private Animation sprite, right;
private float x = 34f, y = 360f;
private static final int SIZE = 34;
private boolean jumping;
private float verticalSpeed;
private boolean blocked[][];
	// Constructor for the Board class.
	public Board() {
		super("Shit Scroller");
		// TODO Auto-generated constructor stub
	}
	
	private boolean blocked(float x, float y) {
		return blocked[(int) x][(int) y];
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		
		
		// Initialize the images & put into an Image[]
		Image img1 = new Image("res/sticky1.png");
		Image img2 = new Image("res/sticky2.png");
		Image[] movementRight = {img2, img1};
		
		// Initialize int[] for how often you want to redraw/refresh.
		int[] duration = {180, 180};
		
		// Initialize the map, create the animation and sprite.
		map = new TiledMap("res/mappin.tmx");
		right = new Animation(movementRight, duration, false);
		sprite = right;
		
		blocked = new boolean[map.getWidth()][map.getHeight()];
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y= 0; y < map.getHeight(); y++) {
				int tileID = map.getTileId(x, y, 0);
				String value = map.getTileProperty(tileID, "blocked", "false");
				if ("true".equals(value)) {
					blocked[x][y] = true;
				}
			}
		}
		
		jumping = false;
		verticalSpeed = 0.0f;
	}


	@Override
	public void render(GameContainer gv, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		// Render the map and draw the sprite onto the screen.
		map.render(0, 0);
		sprite.draw((int)x, (int)y);
			
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		
		if (input.isKeyDown(Input.KEY_RIGHT)) {
		
			sprite.update(delta);
            // The lower the delta the slowest the sprite will animate.
            x += delta * 0.1f;
           
		}
		if (input.isKeyPressed(Input.KEY_UP) && !jumping) {
		     verticalSpeed = -5.0f * delta;//negative value indicates an upward movement
		     jumping = true;
		}

		if (jumping) {
		     verticalSpeed += 0.6f * delta;//change this value to alter gravity strength
		
		}
		if (!blocked(x, verticalSpeed + 20)) {
		y += verticalSpeed;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			
		}
		
		
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer gameContain = new AppGameContainer(new Board());
		gameContain.setDisplayMode(750, 480, false);
		gameContain.setShowFPS(true);
		gameContain.start();
		
	}

	}
