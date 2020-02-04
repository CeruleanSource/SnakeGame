package snakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class labelsPanel extends JPanel {
	JButton startButton = new JButton("Start game");

	public labelsPanel() {
		this.setSize(1000, 200);
		this.setBounds(0, 1000, 1000, 200);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		g.drawString("Eaten: " + (Panel.eatenCount-1), 20, 1100);
		if (Panel.gameOver) {
			g.drawString("Game OVER", 20, 1050);
		}
	}
}
