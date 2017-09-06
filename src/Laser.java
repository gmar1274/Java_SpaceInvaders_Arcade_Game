import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * 
 * @author gabe
 *This class represents a laser object coming from the player. Just an encounter destroys the enemy ship
 */
public class Laser extends JLabel {
	private int xpos,ypos,yspeed;
	private long id;
	private String laser_img="src\\pics\\laser.png";
	private boolean collided;
	private Enemy enemy;
	public Laser(int x,int y){
		collided = false;
		this.id=System.currentTimeMillis();
		this.setIcon(new ImageIcon(laser_img));
		this.setSize(30, 60);
		this.xpos=x;
		this.ypos=y;
		this.setLocation(xpos, ypos);
		yspeed=-20;//neg because going up
		this.setVisible(true);
	}
	/**
	 * add in firework like impact and or sounds when enemy is hit.
	 */
	public void displayImpact(){
		
	}
	public void updateSpeed() {
		if(ypos+yspeed<-40)return;
		this.ypos += yspeed;
		this.setLocation(xpos, ypos);
	}
	public long getID(){
		return this.id;
	}
	@Override 
	public boolean equals(Object o){
		if(o instanceof Laser == false)return false;
		Laser l = (Laser)o;
		return this.id==l.getID();
	}
	public boolean checkCollision(Enemy e) {
		int laser_left =this.xpos;// (this.xpos+this.WIDTH) ; //left corner plus entire laser width
		int laser_right = this.xpos+this.WIDTH;
		int ship_left= e.getX();// = (e.getXPos()+e.getWidth());//same thing^
		int ship_right = e.getXPos() + e.getWidth();
		int ship_top = e.getY();
		int ship_bottom = e.getY()+e.getHeight()-20;
		if(laser_left>=ship_left && laser_right<=ship_right && this.ypos<=ship_bottom&&this.ypos>ship_top){//in width boundary now check Y-axis where laser.y <= ship..
			this.collided=true;
		}
		return this.collided;
	}
	public boolean didCollide() {
		return this.collided;
	}
	public void setEnemyShip(Enemy e) {
		this.enemy = e;
	}
	public Enemy getEnemyShip(){return this.enemy;};
	
}
