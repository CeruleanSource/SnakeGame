package snakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.border.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Panel extends JPanel implements KeyListener {
	/**
	 * 
	 */
	static int x = 40, y = 40, eatenCount = 1;
	static int randx, randy, width = 40;
	static boolean up = false, down = false, left = false, right = false;
	static boolean eaten = true;
	static boolean gameOver = false;
	boolean alive = true;
	Thread snakeMovement;
	snakeBit head;
	Color headColor = new Color(100, 200, 200);
	Color tailColor = new Color(100, 150, 150);
	Random random = new Random();
	ArrayList<snakeBit> snake = new ArrayList<snakeBit>();
	JLabel eatenLabel;
	
	public Panel() {
		setFocusable(true);
		setSize(1000, 1000);
		this.setBounds(0, 0, 1000, 1000);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		down = true;
		addKeyListener(this);
		head = new snakeBit(x, y);
		snake.add(head);
		snakeMovement = new Thread() {
			@Override
			public void run() {
				System.out.println("running");
				while (y <= 950 && y >= 5 && x >= 10 && x <= 920 && alive) {
					if (right) {
						x = x + 40;
					} else if (left) {
						x = x - 40;
					} else if (up) {
						y = y - 40;
					} else if (down) {
						y = y + 40;
					}
					snake.add(head = new snakeBit(x, y));
					snake.remove(0);
					repaint();
					if ((y >= (randy * 10 - 25) && y <= (randy * 10 + 25))
							&& (x >= (randx * 10 - 25) && x <= (randx * 10 + 25))) {
						eaten = true;
						eatenCount++;
						Game.panel2.repaint();
						Game.panel.repaint();
						snake.add(head = new snakeBit(x, y));
					} else {
						for (int i = 0; i < snake.size() - 1; i++) {
							if (snake.get(snake.size() - 1).getX() == snake.get(i).getX()
									&& snake.get(snake.size() - 1).getY() == snake.get(i).getY()) {
								System.out.println("Uh oh");
								// System.out.println(
								// "i: " + (snake.size() - 1) + ", Head x y: " + snake.get(snake.size() -
								// 1).getX()
								// + ", " + snake.get(snake.size() - 1).getY());
								// System.out.println(
								// "i: " + i + ", Tail x y: " + snake.get(i).getX() + ", " +
								// snake.get(i).getY());
								alive = false;
								break;
							}
						}
					}
					try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Game Over");
				gameOver = true;
			}
		};
		snakeMovement.start();
	}

	@Override
	public boolean isFocusTraversable() {
		return true;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = snake.size() - 1; i >= 0; i--) {
			if (i == snake.size() - 1) {
				g.setColor(Color.BLACK);
				g.drawRect(x, y, width, 40);
				g.setColor(headColor);
				g.fillRect(x, y, width, 40);
			} else {
				g.setColor(tailColor);
				g.fillRect(snake.get(i).getX(), snake.get(i).getY(), width, 40);
				g.setColor(Color.magenta);
				g.drawRect(x, y, width, 40);
			}
		}
		if (eaten) {
			randx = random.nextInt(85);
			randy = random.nextInt(85);
			eaten = false;
		}
		g.setColor(Color.MAGENTA);
		g.fillOval(randx * 10, randy * 10, 30, 30);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN && up == false) {
			down = true;
			up = false;
			right = false;
			left = false;
		} else if (e.getKeyCode() == KeyEvent.VK_UP && down == false) {
			down = false;
			up = true;
			right = false;
			left = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT && right == false) {
			down = false;
			up = false;
			right = false;
			left = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && left == false) {
			down = false;
			up = false;
			right = true;
			left = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
