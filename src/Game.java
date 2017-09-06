import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 * 
 * @author gabe Main Logic class. This class will display and controll all of
 *         the game logic.
 *         1) initializes timer and data structures, 
 *         2) then creates gui for player and ships
 *         3) lastly starts the main thread timer
 *         
 *         Game finishes when player dies or no more enemies.
 */

public class Game extends JPanel implements ActionListener, IBackground, IFrame {
	protected SpaceInvadersFrame frame;
	protected MainMenuPanel main;
	private Timer timer;
	private JLabel game_screen;// the background that displays all visuals.
	private Ship player_ship;
	private HashMap<Long, Enemy> enemy_ships_hashmap;
	private HashMap<Long, Laser> laser_hm;

	public Game() {

	}

	/**
	 * Initialiaze players: ship and enemy ships
	 */
	private void initPlayers() {
		this.enemy_ships_hashmap = new HashMap<>();
		player_ship = new Ship();
		player_ship.setGame(this);
		game_screen.add(player_ship);

		game_screen.addKeyListener(new KeyListener() {
			/////////////////////////// keylistener methods

			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					player_ship.shoot();
					break;
				case KeyEvent.VK_ESCAPE:
					if (timer.isRunning())
						timer.stop();
					else
						timer.start();
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_KP_LEFT:
					player_ship.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_KP_RIGHT:
					player_ship.moveRight();
					break;
				default:
					System.out.println(e.getKeyCode());
					break;
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

		});
		game_screen.requestFocus();
		Random rand = new Random();
		int size = rand.nextInt(100) + 1;
		for (int i = 0; i < size; ++i) {
			Enemy e = new Enemy();
			enemy_ships_hashmap.put(e.id, e);
			game_screen.add(e);
			e.setLocation(e.xpos, e.ypos);
		}
	}

	/**
	 * Main thread to update GUI. Every 100mili sec.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		this.repaint();
		if(this.enemy_ships_hashmap.size()==0){
			this.gameOver();
		}
		/*
		 * if (System.currentTimeMillis() % 60 == 0) {
		 * System.out.println(System.currentTimeMillis() % 60000); }
		 */

		for (Component comp : this.game_screen.getComponents()) {
			if (comp instanceof Laser) {
				Laser l = (Laser) comp;
				l.updateSpeed();
				for (Component c : game_screen.getComponents()) {
					if (c instanceof Enemy) {
						Enemy e = (Enemy) c;
						if (l.checkCollision(e)) {
							l.setEnemyShip(e);
							laser_hm.remove(l.getID());
							enemy_ships_hashmap.remove(l.getEnemyShip().getID());
							game_screen.remove(l.getEnemyShip());
							game_screen.remove(comp);
							System.out.println("HIT!! "+l.getID());
							return;
						}
					}
				}

			} else if (comp instanceof Enemy) {
				Enemy e = (Enemy) comp;
				e.updateSpeed();
				if (e.didDamage()) {
					System.out
							.println("DAMAGED!!! -" + e.getDamage() + " User health: " + player_ship.getPlayerHealth());

					player_ship.damageByEnemy(e.getDamage());
					this.enemy_ships_hashmap.remove(e.getID());
					this.game_screen.remove(comp);
				}
				if (player_ship.isDead()) {
					this.gameOver();
				}

			}
		}
	}

	/**
	 * Clean up. Executes when user health surpasses 0.
	 */
	private void gameOver() {
		System.out.println("GAME OVER");
		timer.stop();
		clearGameScreen();
		this.repaint();

		this.main.displayMainMenu();
	}

	/**
	 * Loops through JLabel component and removes component. Also clears the
	 * memory of hashmap of ships and lasers, and deletes player_ship.
	 */
	private void clearGameScreen() {
		this.enemy_ships_hashmap.clear();
		this.laser_hm.clear();
		//player_ship = null;
		for (Component c : this.game_screen.getComponents()) {
			if (c instanceof JLabel) {
				game_screen.remove(c);
			}
			else if(c instanceof Ship){
				game_screen.remove(c);
			}else if(c instanceof Laser){
				game_screen.remove(c);
			}
		}
	}

	@Override
	public void setBackgroundJLabel(JLabel label) {
		this.game_screen = label;

	}

	@Override
	public void setFrame(SpaceInvadersFrame frame) {
		this.frame = frame;
	}
/**
 * Begin setting up variables for game, gui etc..
 */
	public void init() {
		this.laser_hm = new HashMap<>();
		this.timer = new Timer(100, this);
		initPlayers();
		this.timer.start();
	}

	@Override
	public void setMainMenu(MainMenuPanel main) {
		this.main = main;

	}

	/**
	 * creates a laser object to main game controller. Saves as hashmap. To
	 * update later in actionlisetner
	 * 
	 * @param l
	 */
	public void addLaser(Laser l) {
		this.game_screen.add(l);
		this.laser_hm.put(l.getID(), l);
	}

}
