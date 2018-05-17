import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;

public class TankPanel extends JPanel {

	
	boolean inMenu = true;
	int level = 1; //TODO change to initialize at 0, menu ending changes to 1
	boolean level1FirstTime = true;

	public static int panelWidth = 1400;
	public static int panelHeight = 800;

	public static int numWallsAcross = 28;
	public static int numWallsDown = 16;

	public static int wallWidth =  panelWidth/numWallsAcross;
	public static int wallHeight = panelHeight/numWallsDown;

	boolean rightPressed;
	boolean leftPressed;
	boolean upPressed;
	boolean downPressed;

	Arena level1Arena;
	Arena level2Arena;
	
	static BufferedImage crosshair;
	int crosshairX;
	int crosshairY;

	//	private BufferedImage indestructableWall;


	Timer timer = new Timer(1,null);
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
		tp.setPreferredSize(new Dimension(panelWidth,panelHeight));
		frame.pack();
		frame.setVisible(true);
		tp.setUpKeyMappings();
		tp.setUpMouseListeners();
		tp.startGame();
		frame.setResizable(false);


		try
		{
			crosshair = ImageIO.read(new File("images/crosshair.png"));	


		} catch (IOException e)                      
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void setUpKeyMappings() {

		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"left");
		this.getActionMap().put("left",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit left arrow!!");


			}
		});


		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
		this.getActionMap().put("right",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit right arrow!!");


			}
		});

		this.getInputMap().put(KeyStroke.getKeyStroke("UP"),"up");
		this.getActionMap().put("up",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit up arrow!!");


			}
		});

		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"down");
		this.getActionMap().put("down",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit down arrow!!");
				downPressed = true;
			}
		});




		this.requestFocusInWindow();

	}

	private void setUpMouseListeners() {
		{
			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("You just clicked!! "+arg0);
			
				}

				@Override
				public void mousePressed(MouseEvent e){
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e){
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e){
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e){
					// TODO Auto-generated method stub

				}

			});
		}
		
		{
			this.addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseDragged(MouseEvent e)
				{
					// TODO Nothing
					
				}

				@Override
				public void mouseMoved(MouseEvent e)
				{
					// TODO send to crosshair
					crosshairX = e.getX();
					crosshairY = e.getY();
				}
					
			});
		}
		
		
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





		//		try
		//		{
		//			indestructableWall = ImageIO.read(new File("images/indestructable2small.png"));	
		//			
		//
		//		} catch (IOException e)                      
		//		{
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		

	}
	protected void tick() {
		System.out.println("Timer went off!");
		repaint();
	}
	public void paintComponent(Graphics g) {
		
		
		
		System.out.println(crosshairX + crosshairY);
		
		inMenu = false;
		if (inMenu){
			//drawmenu
		}
		else if(level == 1)
		{
			if(level1FirstTime){

				//Level One	
				level1Arena = new Arena(1, numWallsAcross, numWallsDown);


				level1FirstTime = false;
			}
			level1Arena.draw(g);
g.drawImage(crosshair, crosshairX-10, crosshairY-10, null);

		}






		//Level


	}




	public Direction getDirection(){
		if(rightPressed && upPressed){
			return Direction.NORTHEAST;
		}

		return null;
	}





}
