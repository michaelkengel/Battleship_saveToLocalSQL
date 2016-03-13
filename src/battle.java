import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Canvas;

public class battle { // trying to get jframe working
	leaderboard get = new leaderboard();
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					battle window = new battle();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public battle() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 544, 427);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblOutput = new JLabel();
		lblOutput.setBounds(126, 142, 61, 16);
		frame.getContentPane().add(lblOutput);
		
		JTextPane txtpnWelcomeToBattleship = new JTextPane();
		txtpnWelcomeToBattleship.setText("Welcome to Battleship");
		txtpnWelcomeToBattleship.setBounds(199, 142, 146, 16);
		frame.getContentPane().add(txtpnWelcomeToBattleship);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(215, 211, 117, 29);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnHighscoes = new JButton("Scores");
		btnHighscoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHighscoes.setBounds(215, 252, 117, 29);
		frame.getContentPane().add(btnHighscoes);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(213, 25, 100, 100);
		frame.getContentPane().add(canvas);
	}
}
