import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame {
	private static JFrame mainFrame = new JFrame("frame");
	
	ScoreLabel scoreLabel;
	private static JPanel mainFramePanel = new JPanel();
	private static JPanel contentPanel = new JPanel(){
		public Dimension getPreferredSize() {
            return new Dimension(410, 810);
        }
	};
	private static JPanel gamePanel = new JPanel() {
		public Dimension getPreferredSize() {
            return new Dimension(407, 807);
        }
	};
	private static Color color[] = {Color.blue,Color.orange,Color.green,Color.pink,Color.white,Color.yellow,Color.red};
	
	private static int mapMass[][];
	private static Clip clip;
	
	private static KeyAdapter keys;
	public GameFrame() {
		mainFrame.setSize( 900, 900);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
				
		Thread myThready = new Thread(new Runnable()
		{
		
			public void run() 
			{
				
				for(boolean i = true;i!=false;) {
					for(int j = 0;j<7;j++) {
						gamePanel.setBorder(BorderFactory.createLineBorder(color[j],5));
						try {
							Thread.sleep(120);
						} catch (InterruptedException e) {
						}
					}
				}
			}
		});
		gamePanel.setLayout(null);										//������� ������
		gamePanel.setBackground(Color.BLACK);
		
		scoreLabel = new ScoreLabel();
		contentPanel.add(gamePanel);
		contentPanel.add(scoreLabel);
		contentPanel.setBackground(Color.black);
		
		mainFramePanel.add(contentPanel);
		
		mainFrame.setContentPane(mainFramePanel);
		mainFrame.getContentPane().setBackground( Color.BLACK );
		myThready.start();
		try {
			game();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private static void game() throws InterruptedException {
		mapMass = new int[10][20];
		for(boolean lvl1 = false;lvl1!=true;) {
			Shape shape = getRandomShape();
			for(boolean lvl2 = false;lvl2!=true;) {
				
				int mapMassLvl2[][] = cloneMass(mapMass);
				
				addShapeMap(mapMassLvl2,shape);
				butGenerate(shape, mapMassLvl2,mapMass);
				
				repaintMap(mapMassLvl2);
				Thread.sleep(1000);
				
				
				
				if(!shape.shapeTimeMovementY(mapMass)) {
					addShapeMap(mapMass,shape);
					checkLines(mapMass);
					break;
				}
				
				
			}
		}
		
	}
	public static void checkLines(int mapMass[][]) {
		boolean fullLines[] = new boolean[19];
		for(int i = 0;i<19;i++) {
			int colFull = 0;
			for(int j = 0;j<10;j++) {
				if(mapMass[j][i+1] !=0 ) {
				colFull++;	
				}
			}
			if(colFull==10) {
				fullLines[i]=true;
			}
		}
		boolean isSomeLineFull = false;
		int finalMass[][] = cloneMass(mapMass);
		for(int i = 0;i<19;i++) {
			if(fullLines[i]) {
				isSomeLineFull = true;
				for(int j =0;j<10;j++) {
					finalMass[j][i+1] = 0;
				}
			}
		}
	if(isSomeLineFull) {
		for(int i = 0;i<2;i++) {
			try {
				repaintMap(finalMass);
				Thread.sleep(500);
				repaintMap(mapMass);
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
			
			try {
				repaintMap(finalMass);
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			
			for(int i = 0;i<19;i++) {
				if(fullLines[i]) {
					for(int j = 0;j<10;j++) {
						mapMass[j][i+1]=0;
					}
					for(int k = i;k>0;k--) {
						for(int j = 0;j<10;j++) {
							mapMass[j][k+1] = mapMass[j][k];
						}
					}
					
				}
			}
			repaintMap(mapMass);
			
	}
		
		
	}
	public static int[][] cloneMass(int mass[][]) {
		int finMass[][] = new int[10][20];
		for(int i = 0;i<10;i++) {
			for(int j = 0;j<20;j++) {
				finMass[i][j] = mass[i][j];
			}
		}
		return finMass;
	}
	private static void addShapeMap(int map[][],Shape shape) {
		int shapeMass[][] = shape.getCurPosition();
		for(int i = 0;i<4;i++) {
				map[shapeMass[i][0]][shapeMass[i][1]] = shape.shapeColor;
		}
	}
	
	
	private static void butGenerate(Shape shape, int mass[][],int mapMass[][]) {
		gamePanel.removeKeyListener(keys);
		
		
		 keys = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				
	            int key=e.getKeyCode();
	           // System.out.println("KeyPressed "+key);
	            if(key==KeyEvent.VK_LEFT){
	            	shape.shapeMovementX(1, mapMass);
					int resMass[][] = cloneMass(mapMass);
					addShapeMap(resMass,shape);
					repaintMap(resMass);
	            }
	            if(key == KeyEvent.VK_RIGHT) {
	            	shape.shapeMovementX(2, mapMass);
					int resMass[][] = cloneMass(mapMass);
					addShapeMap(resMass,shape);
					repaintMap(resMass);
	            }
	           
	           
			}
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
	            //System.out.println("KeyPressed "+key);
				 if(key == KeyEvent.VK_DOWN) {
	            	shape.shapeTimeMovementY(mapMass);
	            	int resMass[][] = cloneMass(mapMass);
					addShapeMap(resMass,shape);
					repaintMap(resMass);	
	             }
				 if(key == KeyEvent.VK_UP) {
		            	shape.shapeRotation(mapMass);
						int resMass[][] = cloneMass(mapMass);
						addShapeMap(resMass,shape);
						repaintMap(resMass);
		         }
			}
		};
		gamePanel.addKeyListener(keys);
		gamePanel.requestFocusInWindow();
		
	}
	
	private static void repaintMap(int mass[][]) {
		gamePanel.removeAll();
		int xLoc = 4, yLoc = 4;
		for(int i = 0;i<20;i++) {
			for(int j = 0;j<10;j++) {
				JLabel label = new JLabel();
				label.setDoubleBuffered(true);
				label.setSize(40, 40);
				label.setLocation(xLoc, yLoc);
				if(mass[j][i]==0) {
				label.setBackground(Color.black);
				}else {
					label.setIcon(new ImageIcon("images/"+mass[j][i]+".png"));
				}
				label.setOpaque(true);
				gamePanel.add(label);
				xLoc += 40;
			}
			yLoc += 40;
			xLoc = 4;
		}
		refresh();
	}
	
	private static Shape getRandomShape() {
		Random rand = new Random();
		int x = rand.nextInt(7);
		switch(x) {
			case 0:{return new ShapeO();}
			case 1:{return new ShapeI();}
			case 2:{return new ShapeS();}
			case 3:{return new ShapeZ();}
			case 4:{return new ShapeL();}
			case 5:{return new ShapeJ();}
			case 6:{return new ShapeT();}
		}
		return null;
	}
	public static void refresh() {
		mainFrame.repaint();
		mainFrame.revalidate();
	}
}
