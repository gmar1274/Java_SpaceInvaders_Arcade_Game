import java.util.Random;

import javax.swing.ImageIcon;
/**
 * 
 * @author gabe
 *This class represents an enemy Ship. The game is designed to randomly generate a jlabel ship and add it to the Game Class. This class extends from a Ship Object.
 *
 */
public class Enemy extends Ship{
	private boolean isEnemy;
	private int width,height;
	private int damage=5;
	private boolean damaged=false;//when it crosses below the user.
	public Enemy() {
		this.id = System.currentTimeMillis();
		this.damaged=false;
		this.isEnemy=true;
		Random rand =new Random();
		width  =SpaceInvadersFrame.WIDTH;
		height = SpaceInvadersFrame.HEIGHT;
		xpos = rand.nextInt(width-100);
		ypos = rand.nextInt(height-500);
		xspeed = rand.nextInt(15)+1;
		if(rand.nextBoolean()){//just a bit more random movement
			xspeed = -xspeed;
		}
		yspeed = rand.nextInt(15)+1  ;
		if(rand.nextBoolean()){//random movement
			yspeed = -yspeed;
		}
		health = 100;
		this.setSize(140, 140);
		this.setOpaque(false);
		this.setIcon(new ImageIcon(SpaceInvadersFrame.enemy_img));
		this.setVisible(true);
	}
	/**
	 * Boundary verification. Stays in play until it reaches the bottom of the screen.
	 */
	public void updateSpeed() {
		if(ypos+yspeed > height){
			 damaged=true;
			 return;
		}
		if(xpos + xspeed > width || xpos+xspeed < 0 ){
			xspeed = -xspeed;
		}
		xpos+= xspeed;
		if(ypos+yspeed < -50){
			yspeed = -yspeed;
		}
		xpos += xspeed;
		ypos += yspeed;
		this.setLocation(xpos, ypos);
	}
	/**
	 * Indicates that the ship has reached the player. So it needs to decrement points and disapear.
	 * @return
	 */
	public boolean didDamage(){
		return this.damaged;
	}
	
	/**
	 * @return hash of object
	 */
	public long getID(){
		return this.id;
	}
	/**
	 * 
	 * @return points that decrements health
	 */
	public int getDamage(){
		return this.damage;
	}
	public int getXPos(){return this.xpos;}
	public int getYPos(){return this.ypos;}
}
