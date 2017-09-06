import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
/**
 * 
 * @author gabe
 *SpaceInvaders Portfolio project.
 *Demonstrates my knowledge in Object Oriented Design and code read and writability.
 *This class is the entry point.
 *Just a Frame. This class will launch the main menu Screen. From there is will launch the game.
 *SpaceInvadersFrame (Frame) -> MainMenuPanel (JPanel) -> Game (JPanel)
 */
public class SpaceInvadersFrame extends JFrame{

	public static int HEIGHT=800,WIDTH=1200;//frame attribures
	public  static final String bg_image="src\\pics\\space.png",player_img="src\\pics\\player.png",enemy_img="src\\pics\\enemy.png";// image paths
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpaceInvadersFrame frame = new SpaceInvadersFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. Frame is just the main container.
	 * Add Jpanel component. MainMenuPanel is a panel that will begin the game and will be attached to the main container.
	 */
	public SpaceInvadersFrame() {
		
		setResizable(false);
		setTitle("SpaceInvaders by Gabriel Martinez ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(this.WIDTH,this.HEIGHT);
		MainMenuPanel m = new MainMenuPanel();
		m.setFrame(this);
		m.init();
		this.add(m);
		this.setLocationRelativeTo(null);
		
	}

}
