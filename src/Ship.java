import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * @author gabe Main Object class that denotes characteristics of a spaceship.
 *         Movement, location, ID, and health.
 */
public class Ship extends JLabel implements IGame {
	protected int health;
	protected int xpos;
	protected int ypos;
	protected int xspeed, yspeed;
	protected long id;
	private JLabel game;//game background
	private Game gameObj;

	public Ship() {
		this.id = System.currentTimeMillis();
		Random rand = new Random();
		xpos = rand.nextInt(SpaceInvadersFrame.WIDTH-50);
		ypos = SpaceInvadersFrame.HEIGHT - 200;
		xspeed = 15;
		yspeed = 0;
		health = 100;
		this.setSize(300, 200);
		this.setOpaque(false);
		this.setIcon(new ImageIcon(SpaceInvadersFrame.player_img));
		setLocation(xpos,ypos);
		this.setVisible(true);
		
	}

	public void damageByEnemy(int damage) {
		this.health -= damage;
	}

	public boolean isDead() {
		return this.health <= 0;
	}

	public int getPlayerHealth() {
		return this.health;
	}

	public void moveLeft() {
		if(xpos-xspeed<-25)return;//boundary case
		xpos-=xspeed;
		this.setLocation(xpos, ypos);
	}

	public void moveRight() {
		if(xpos+xspeed>SpaceInvadersFrame.WIDTH-100)return;//boundary case
		xpos+=xspeed;
		this.setLocation(xpos, ypos);
	}

	public void shoot() {
				Laser l = new  Laser(xpos+(55),ypos);
				gameObj.addLaser(l);
			
	}

	@Override
	public void setGame(Game gameObj) {
		this.gameObj = gameObj;
	}
	public long getID(){return this.id;}
@Override 
public boolean equals(Object o){
	if(o instanceof Ship == false)return false;
	Ship s = (Ship)o;
	return this.id == s.getID();
}
	
}
