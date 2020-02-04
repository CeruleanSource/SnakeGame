package snakeGame;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game implements Runnable {
	JFrame game;
	static Panel panel;
	static labelsPanel panel2;
	JLabel label = new JLabel("Game over");
	
	public Game() {
		label.setFont(new Font("Times New Roman", Font.BOLD, 50));
		game = new JFrame();
		panel = new Panel();
		game.setSize(1000, 1200);
		game.setResizable(false);
		game.setTitle("Snake Game");
		game.add(panel);
		game.add(panel2 = new labelsPanel());
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setLocation(1100, 200);
		game.setVisible(true);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}

	public void run() {
	}
}
