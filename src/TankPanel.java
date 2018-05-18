import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
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
	
	int level = 0; //TODO change to initialize at 0, menu ending changes to 1
	boolean level1FirstTime = true;
	
	public static int panelWidth = 1400;
	public static int panelHeight = 800;
	
	public static int numWallsAcross = 28;
	public static int numWallsDown = 16;
	
	public static int wallWidth =  panelWidth/numWallsAcross;
	public static int wallHeight = panelHeight/numWallsDown;
	
	boolean rightPressed, leftPressed, upPressed, downPressed;

	ArrayList<Arena> arenaList = new ArrayList<Arena>();
	Menu theMenu;
	Arena level1Arena;
	//Arena level2Arena;
	//Arena level3Arena;
	
	
	
	static ImageLibrary imageLibrary;
	static BufferedImage background;
	static BufferedImage crosshair;
	static BufferedImage turret;
	static BufferedImage projectile;
	static BufferedImage indestructableWall;
	static BufferedImage destructableWall;
	static BufferedImage greenTank;
	static BufferedImage redTank;
	static BufferedImage blueTank;
	static BufferedImage blackTank;
	static BufferedImage greenTurret;
	static BufferedImage redTurret;
	static BufferedImage blueTurret;
	static BufferedImage blackTurret;
	static BufferedImage bullet;
	
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

		//create cursor
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		frame.getContentPane().setCursor(blankCursor);


		
		try
		{
			background = ImageIO.read(new File("images/Background2.png"));	
			crosshair = ImageIO.read(new File("images/crosshair.png"));		
			turret = ImageIO.read(new File("images/crosshair.png"));	 //TODO find image
			projectile = ImageIO.read(new File("images/crosshair.png"));	//TODO find image
			indestructableWall = ImageIO.read(new File("images/Metal_50x50.jpg"));	
			destructableWall = ImageIO.read(new File("images/Wood_50x50.png"));
			greenTank = ImageIO.read(new File("images/50x50 tanks/greenTank.png"));
			redTank = ImageIO.read(new File("images/50x50 tanks/redTank.png"));	 
			blueTank = ImageIO.read(new File("images/50x50 tanks/blueTank.png"));	
			blackTank = ImageIO.read(new File("images/50x50 tanks/blackTank.png"));	
			greenTurret = ImageIO.read(new File("images/20x50 turrets/greenTurret.png"));
			redTurret = ImageIO.read(new File("images/20x50 turrets/greenTurret.png"));
			blueTurret = ImageIO.read(new File("images/20x50 turrets/greenTurret.png"));
			blackTurret = ImageIO.read(new File("images/20x50 turrets/greenTurret.png"));
		}
		catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageLibrary.background = background;
		imageLibrary.crosshair = crosshair;
		imageLibrary.turret = turret;
		imageLibrary.projectile = projectile;
		
		imageLibrary.indestructableWall = indestructableWall;
		imageLibrary.destructableWall = destructableWall;
		
		imageLibrary.greenTank = greenTank;
		imageLibrary.redTank = redTank;
		imageLibrary.blueTank = blueTank;
		imageLibrary.blackTank = blackTank;
		
		imageLibrary.greenTurret = greenTurret;
		imageLibrary.redTurret = redTurret;
		imageLibrary.blueTurret = blueTurret;
		imageLibrary.blackTurret = blackTurret;
		imageLibrary.bullet = bullet;
		

	}
	private void setUpKeyMappings() {

		this.getInputMap().put(KeyStroke.getKeyStroke("A"),"left");
		this.getActionMap().put("left",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit left arrow!!");
				leftPressed = true;

			}
		});

		this.getInputMap().put(KeyStroke.getKeyStroke("released A"),"releasedLeft");
		this.getActionMap().put("releasedLeft",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("released left arrow!!");
				leftPressed = false;

			}
		});
		
	
		this.getInputMap().put(KeyStroke.getKeyStroke("D"),"right");
		this.getActionMap().put("right",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit right arrow!!");
				rightPressed = true;

			}
		});
		
		this.getInputMap().put(KeyStroke.getKeyStroke("released D"),"releasedRight");
		this.getActionMap().put("releasedRight",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("released right arrow!!");
				rightPressed = false;
				
			}
		});

		this.getInputMap().put(KeyStroke.getKeyStroke("W"),"up");
		this.getActionMap().put("up",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit up arrow!!");
				upPressed = true;


			}
		});
		
		this.getInputMap().put(KeyStroke.getKeyStroke("released W"),"releasedUp");
		this.getActionMap().put("releasedUp",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("released up arrow!!");
				upPressed = false;


			}
		});

		this.getInputMap().put(KeyStroke.getKeyStroke("S"),"down");
		this.getActionMap().put("down",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit down arrow!!");
				downPressed = true;
			}
		});
		
		this.getInputMap().put(KeyStroke.getKeyStroke("released S"),"releasedDown");
		this.getActionMap().put("releasedDown",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("released down arrow!!");
				downPressed = false;
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
					if(inMenu)
					{
						if((arg0.getX() > 350 && arg0.getX() < 1050) 
								&&
							(arg0.getY() > 500 && arg0.getY() < 700)
								)
						{
							inMenu = false;
							level = 1;
						}
					}
			
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
					
					//double Xd =(crosshairX-level1Arena.playerTankLocX()+25);
					//double Yd =(crosshairY-level1Arena.playerTankLocX()+25);
					//double radAngle = Math.atan(Yd/Xd);
					
				}
					
			});
		}
		
		
	}

	private void startGame() {
		timer.setDelay(1);
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
		System.out.println("timer has gone off");
		repaint();
	}
	public void paintComponent(Graphics g) {
	//	long start = System.currentTimeMillis();
		
		
		if(!level1FirstTime){
			arenaList.get(level).setInputMoveArr(getInputMoveArr());
		}
		if(level1FirstTime){
			theMenu = new Menu();
		}
		
		if (inMenu){
			theMenu.draw(g, imageLibrary);
		}
		else 
		{
			if(level1FirstTime){
				//Level One	
				
				level1Arena = new Arena(1, numWallsAcross, numWallsDown);
				arenaList.add(level1Arena);
				arenaList.add(level1Arena);
				level1FirstTime = false;
				
			}
			arenaList.get(level).draw(g, imageLibrary);
			g.setColor(Color.WHITE);
			g.drawLine(level1Arena.playerTankLocX()+25, level1Arena.playerTankLocY()+25, crosshairX, crosshairY);	
			
			//g.drawLine(level1Arena.playerTankLocX(), level1Arena.playerTankLocY(), crosshairX, crosshairY);
		}

		g.drawImage(crosshair, crosshairX-10, crosshairY-10, null);
		

	}


	public int[] getInputMoveArr(){
		int[] XandY = new int[2];
		XandY[0] = 0;
		XandY[1] = 0;
		
		if(rightPressed){
			XandY[0]+=1;
		}
		if(leftPressed){
			XandY[0]-=1;
		}
		if(upPressed){
			XandY[1]+=1;
		}
		if(downPressed){
			XandY[1]-=1;
		}
		
		return XandY;
	}
	





}
