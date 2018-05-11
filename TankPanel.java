import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;

public class TankPanel extends JPanel {

	PlayerTank t = new PlayerTank(20,20);
	boolean showMenu = true;
	
	Timer timer = new Timer(500,null);
	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame("Tank Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		TankPanel tp = new TankPanel();
		frame.add(tp);
		tp.setPreferredSize(new Dimension(800,800));
		frame.pack();
		frame.setVisible(true);
		tp.setUpKeyMappings();
		tp.startGame();
	}
	private void setUpKeyMappings() {

		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"left");
		this.getActionMap().put("left",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit left arrow!!");
				t.move(-7,0);
				repaint();
			}
		});
		
		
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
		this.getActionMap().put("right",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit right arrow!!");
				t.move(7,0);
				repaint();
			}
		});
		
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"),"up");
		this.getActionMap().put("up",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit up arrow!!");
				t.move(0,7);
				repaint();
			}
		});
		
		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"down");
		this.getActionMap().put("down",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit down arrow!!");
				t.move(0,-7);
				repaint();
			}
		});
		this.requestFocusInWindow();
		
	}
	private void startGame() {
		timer.setDelay(50);
		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tick();
			}
			
		});
		timer.start();
	}
	protected void tick() {
		System.out.println("Timer went off!");
		repaint();
	}
	public void paintComponent(Graphics g) {
		t.draw(g);
	//Level One	
		ArrayList<Wall> levelOne = new ArrayList<Wall>();
		Arena a = new Arena(levelOne);
		Wall w1 = new Wall(90,80,10,200);
		Wall w2 = new Wall(90,80,200,10);
		
		Wall w3 = new Wall(90,480,10,200);
		Wall w4 = new Wall(90,680,200,10);
		
		Wall w5 = new Wall(520,80,200,10);
		Wall w6 = new Wall(720,80,10,200);
		
		Wall w7 = new Wall(520,680,200,10);
		Wall w8 = new Wall(720,480,10,210);
		
		
		levelOne.add(w1);
		levelOne.add(w2);
		levelOne.add(w3);
		levelOne.add(w4);
		levelOne.add(w5);
		levelOne.add(w6);
		levelOne.add(w7);
		levelOne.add(w8);
		a.draw(g);
		
	//Level
		
		
	}

}
