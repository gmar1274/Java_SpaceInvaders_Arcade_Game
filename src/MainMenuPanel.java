import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel implements IFrame {

	private SpaceInvadersFrame frame;
	private JPanel panel;
	private JLabel bg_image;
	private Game g;

	/**
	 * Initialize a main component Jpanel -- this will have intro text and game
	 * button features. Jpanel because it could potentially have multiple
	 * buttons and text; therefore, jpanel is a container that can be easily
	 * maintained.
	 * 
	 * @param frame
	 *            main frame container
	 */
	public MainMenuPanel() {
	}

	public void init() {
		int alignment = frame.WIDTH / 2 - 200;
		int width = frame.WIDTH, height = frame.HEIGHT;
		this.setSize(width, height);
		setLayout(null);
		bg_image = new JLabel(new ImageIcon(frame.bg_image));
		bg_image.setSize(width, height);
		bg_image.setVisible(true);
		add(bg_image);

		panel = new JPanel();
		// panel.setSize(new Dimension(1000, 600));
		panel.setSize(width, height);
		panel.setLayout(null);
		panel.setOpaque(false);

		JTextArea menu_text = new JTextArea("SpaceInvaders \n by Gabriel Martinez");// menu
																					// text
		menu_text.setForeground(Color.WHITE);
		menu_text.setFont(new Font("Gungsuh", Font.PLAIN, 40));
		menu_text.setBounds(alignment, 15, 502, 110);
		menu_text.setOpaque(false);
		menu_text.setEditable(false);
		panel.add(menu_text);

		panel.setVisible(true);
		bg_image.add(panel);

		JButton btnStart = new JButton("Start");// menu button
		btnStart.setForeground(SystemColor.textHighlight);
		btnStart.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 20));
		btnStart.setBounds(alignment + 100, menu_text.HEIGHT + 300, 200, 100);
		btnStart.setBorderPainted(false);
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playGame();
			}
		});
		panel.add(btnStart);
		this.setVisible(true);
	}

	/**
	 * Hide main menu screen. Begin the game.
	 */
	protected void playGame() {
		bg_image.remove(panel);
		if (g == null)
			g = new Game();
		g.setFrame(frame);
		g.setBackgroundJLabel(bg_image);
		g.setMainMenu(this);
		g.init();
		frame.add(new Game());
		frame.repaint();
	}

	public void displayMainMenu() {
		this.bg_image.add(this.panel);
		this.frame.repaint();
	}

	@Override
	public void setFrame(SpaceInvadersFrame frame) {
		this.frame = frame;
	}
}
