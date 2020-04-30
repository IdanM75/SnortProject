import java.awt.Graphics;       
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;



public class Button implements MouseListener
{
	
	private BufferedImage texture;
	private Point position;
	private int width;
	private int height;
	private boolean clicked = false;
	private String path;
	
	final JComponent[] inputs1 = new JComponent[] {
			new JLabel("You Cant Put Here A Tool"),
	};
	
	final JComponent[] inputs2 = new JComponent[] {
			new JLabel("Game Saved"),
	};
	
	final JComponent[] inputs3 = new JComponent[] {
			new JLabel("Game Loaded"),
	};
	
	final JComponent[] inputs4 = new JComponent[] {
			new JLabel("New Game Loaded"),
	};
	
	final JComponent[] inputs5 = new JComponent[] {
			new JLabel("When You Leaving The Game You Hurts In Programmer Feelings :("),
	};
	
	final JComponent[] inputs6 = new JComponent[] {
			new JLabel("You Cant Undo If The Other Player Isnt A Humen"),
	};
	
	public Rectangle collider(){
		
		return new Rectangle(position.x, position.y, width, height);
		
	}

	
	public Button(String path, Point position, int width, int height)
	{
		super();
		this.path=path;
		this.texture = Toolkit.loadImage("png", path);
		this.position = position;
		this.width = width;
		this.height = height;
		TestDraw.addListener(this);
	}	

	public void checkUndo(String word, Button [][] a, Button bturn, int saveUndoI, int saveUndoJ,
						  int deepRed, int deepYellow,int saveUndoIBin, int saveUndoJBin,
						  Button [] BoardButtonsArray, String undoPath, String undoPathBin)
	{
		String savePath;
		
		if(clicked)
		{
			if(word.equals("undo"))
			{
				if(deepRed==-1 || deepYellow==-1)
				{
					if((a[saveUndoI][saveUndoJ].getPath().equals("redE")) || (a[saveUndoI][saveUndoJ].getPath().equals("yellowE")))
					{		
						savePath=a[saveUndoI][saveUndoJ].getPath();
					
						a[saveUndoI][saveUndoJ].setPath(undoPath);
						a[saveUndoI][saveUndoJ].setTexture(Toolkit.loadImage("png", undoPath));
						a[saveUndoI][saveUndoJ].updateBoardAround( a, saveUndoI ,saveUndoJ);
					
						if(deepRed!=-1 || deepYellow!=-1)
						{
							if((a[saveUndoIBin][saveUndoJBin].getPath().equals("redE")) || (a[saveUndoIBin][saveUndoJBin].getPath().equals("yellowE")))
							{									
								a[saveUndoIBin][saveUndoJBin].setPath(undoPathBin);
								a[saveUndoIBin][saveUndoJBin].setTexture(Toolkit.loadImage("png", undoPathBin));
								a[saveUndoIBin][saveUndoJBin].updateBoardAround( a, saveUndoIBin , saveUndoJBin);
							}
						}
						
						if(!savePath.equals(bturn))
						{
							changeTurn(bturn);
						}
					
						if(deepRed!=-1 || deepYellow!=-1)
						{
							changeTurn(bturn);
						}
						
					}
				}
				
				clicked = false;
			}	
			
		}
		
		if(DrawPanel.isClicked==0)
		{
			BoardButtonsArray[0].setTexture(Toolkit.loadImage("png", "undoPressed"));
		}
		else
		{
			BoardButtonsArray[0].setTexture(Toolkit.loadImage("png", "undo"));
		}
	}
	
	public void checkSaveGame(String word, Button [][] a, Button bturn, Button [] BoardButtonsArray, File file)
	{
		if(clicked)
		{
			if(word.equals("saveGame"))
			{
				try {
					DrawPanel.writerFile = new FileWriter(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					DrawPanel.writerFile.write(bturn.getPath()+"\n");
					DrawPanel.writerFile.write(a.length+"\n");
					DrawPanel.writerFile.write(a[0].length+"\n\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(int i=0;i<a.length;i++)
				{
					for(int j=0;j<a[0].length;j++)
					{
						try {
							DrawPanel.writerFile.write(a[i][j].getPath()+"\n");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				try {
					DrawPanel.writerFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//saveTurn=bturn.getPath();
			
				JOptionPane.showMessageDialog(null, inputs2, "Idan_Snort_Project", JOptionPane.PLAIN_MESSAGE);
				
				clicked = false;
				
			}
		}
		
		if(DrawPanel.isClicked==1)
		{
			BoardButtonsArray[1].setTexture(Toolkit.loadImage("png", "saveGamePressed"));
		}
		else
		{
			BoardButtonsArray[1].setTexture(Toolkit.loadImage("png", "saveGame"));
		}
	}
	
	public void checkLoadGame(String word, Button [][] a, Button bturn, Button [] BoardButtonsArray, File file)
	{
		if(clicked)
		{
			if(word.equals("loadGame"))
			{				
				String saveTurn="emptyE";
				int saveRow=0;
				int saveColumn=0; 
				String savePath;
				
				try {
					DrawPanel.readerFile = new BufferedReader(new FileReader(file));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					saveTurn = DrawPanel.readerFile.readLine();
					saveRow= Integer.parseInt(DrawPanel.readerFile.readLine());
					saveColumn= Integer.parseInt(DrawPanel.readerFile.readLine());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Arrays arr = new Arrays();
				DrawPanel.setBoardImagesArray(new Button[saveRow][saveColumn]);
				arr.create2DArrayImages(DrawPanel.getBoardImagesArray(), saveRow, saveColumn);
				
				try {
					bturn.setPath(saveTurn);
					bturn.setTexture(Toolkit.loadImage("png",saveTurn));
					DrawPanel.readerFile.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(int i=0;i<DrawPanel.getBoardImagesArray().length;i++)
				{
					for(int j=0;j<DrawPanel.getBoardImagesArray()[i].length;j++)
					{
						try {
							savePath=DrawPanel.readerFile.readLine();
							DrawPanel.getBoardImagesArray()[i][j].setPath(savePath);
							DrawPanel.getBoardImagesArray()[i][j].setTexture(Toolkit.loadImage("png", savePath));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
					}
				}
				
				try {
					DrawPanel.readerFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				JOptionPane.showMessageDialog(null, inputs3, "Idan_Snort_Project", JOptionPane.PLAIN_MESSAGE);
				
				clicked = false;
				
			}
		}
		
		if(DrawPanel.isClicked==2)
		{
			BoardButtonsArray[2].setTexture(Toolkit.loadImage("png", "loadGamePressed"));
		}
		else
		{
			BoardButtonsArray[2].setTexture(Toolkit.loadImage("png", "loadGame"));
		}
	}
	
	public void checkNewGame(String word, Button [][] a, Button bturn, String saveTurnAtFirst, Button [] BoardButtonsArray)
	{
		if(clicked)
		{
			if(word.equals("newGame"))
			{
				for(int i=0;i<a.length;i++)
				{
					for(int j=0;j<a[0].length;j++)
					{
						a[i][j].setPath("emptyE");
						a[i][j].setTexture(Toolkit.loadImage("png","emptyE"));
					}
				}
				bturn.setPath(saveTurnAtFirst);
				bturn.setTexture(Toolkit.loadImage("png",saveTurnAtFirst));
				
				JOptionPane.showMessageDialog(null, inputs4, "Idan_Snort_Project", JOptionPane.PLAIN_MESSAGE);
				
				clicked = false;
				
			}
		}
		
		if(DrawPanel.isClicked==3)
		{
			BoardButtonsArray[3].setTexture(Toolkit.loadImage("png", "newGamePressed"));
		}
		else
		{
			BoardButtonsArray[3].setTexture(Toolkit.loadImage("png", "newGame"));
		}
	}
	
	public void checkExit(String word, Timer timer, Button [] BoardButtonsArray)
	{
		if(clicked)
		{
			if(word.equals("exit"))
			{
				JOptionPane.showMessageDialog(null, inputs5, "Idan_Snort_Project", JOptionPane.PLAIN_MESSAGE);
				timer.cancel();
				System.exit(0);
				
				clicked = false;
				
			}			
		}
		
		if(DrawPanel.isClicked==4)
		{
			System.out.println("1");
			BoardButtonsArray[4].setTexture(Toolkit.loadImage("png", "exitPressed"));
		}
		else
		{
			BoardButtonsArray[4].setTexture(Toolkit.loadImage("png", "exit"));
		}
	}
	
	
	
	public void ownElseEmprove(Button [][] a,int i, int j)
	{
		int jMinus1Yellow=0;
		int iMinus1Yellow=0;
		int jPlus1Yellow=0;
		int iPlus1Yellow=0;
		
		int jMinus1Red=0;
		int iMinus1Red=0;
		int jPlus1Red=0;
		int iPlus1Red=0;
		
		if(j > 0)
		{
			if(a[i][j-1].getPath().equals("yellowE"))
			{
				jMinus1Yellow++;
			}
		}
		if(i > 0)
		{
			if(a[i-1][j].getPath().equals("yellowE"))
			{
				iMinus1Yellow++;
			}
		}
		if(j < a[0].length - 1)
		{
			if(a[i][j+1].getPath().equals("yellowE"))
			{
				jPlus1Yellow++;
			}
		}
		if(i < a.length - 1)
		{
			if(a[i+1][j].getPath().equals("yellowE"))
			{
				iPlus1Yellow++;
			}
		}
		
		
		
		if(j > 0)
		{
			if(a[i][j-1].getPath().equals("redE"))
			{
				jMinus1Red++;
			}
		}
		if(i > 0)
		{
			if(a[i-1][j].getPath().equals("redE"))
			{
				iMinus1Red++;
			}
		}
		if(j < a[0].length - 1)
		{
			if(a[i][j+1].getPath().equals("redE"))
			{
				jPlus1Red++;
			}
		}
		if(i < a.length - 1)
		{
			if(a[i+1][j].getPath().equals("redE"))
			{
				iPlus1Red++;
			}
		}
		
		
		if(((jMinus1Yellow==0) && (iMinus1Yellow==0) && (jPlus1Yellow==0) && (iPlus1Yellow==0)) && 
		   ((jMinus1Red==0)    && (iMinus1Red==0)    && (jPlus1Red==0)    && (iPlus1Red==0))
		  )
		{
			a[i][j].setPath("emptyE");
			a[i][j].setTexture(Toolkit.loadImage("png","emptyE"));
		}
		if(((jMinus1Yellow==1) || (iMinus1Yellow==1) || (jPlus1Yellow==1) || (iPlus1Yellow==1)) && 
		   ((jMinus1Red==0)    && (iMinus1Red==0)    && (jPlus1Red==0)    && (iPlus1Red==0))
		 )
		{
			a[i][j].setPath("ownyellowE");
			a[i][j].setTexture(Toolkit.loadImage("png","ownyellowE"));
		}
		if(((jMinus1Yellow==0) && (iMinus1Yellow==0) && (jPlus1Yellow==0) && (iPlus1Yellow==0)) && 
		   ((jMinus1Red==1)    || (iMinus1Red==1)    || (jPlus1Red==1)    || (iPlus1Red==1))
		  )
		{
			a[i][j].setPath("ownredE");
			a[i][j].setTexture(Toolkit.loadImage("png","ownredE"));
		}
		if(((jMinus1Yellow==1) || (iMinus1Yellow==1) || (jPlus1Yellow==1) || (iPlus1Yellow==1)) && 
		   ((jMinus1Red==1)    || (iMinus1Red==1)    || (jPlus1Red==1)    || (iPlus1Red==1))
		  )
		{
			a[i][j].setPath("ownBoth");
			a[i][j].setTexture(Toolkit.loadImage("png","ownBoth"));
		}
		
	}
	
	public void updateBoard(Button [][] a ,int i ,int j, Button bturn)  
	{
		if(clicked)
		{					
			
			
			String oppose = bturn.getPath().equals("yellowE") ? "redE" : "yellowE";
			String opposeOwnership = "own" + oppose;
			 
		 	if(a[i][j].getPath().equals("yellowE") || 
					   a[i][j].getPath().equals("redE") ||
					   a[i][j].getPath().equals(opposeOwnership) ||
					   a[i][j].getPath().equals("ownBoth"))  
					
			{
				JOptionPane.showMessageDialog(null, inputs1, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
			}
			
			 
			if((!a[i][j].getPath().equals("yellowE") && !(a[i][j].getPath().equals("redE"))) &&
				((bturn.getPath().equals("yellowE") && !a[i][j].getPath().equals("ownredE")) || (bturn.getPath().equals("redE") && !a[i][j].getPath().equals("ownyellowE"))) &&
				!a[i][j].getPath().equals("ownBoth"))
			{
					a[i][j].setPath(bturn.getPath());
					a[i][j].setTexture(Toolkit.loadImage("png",bturn.getPath()));
					

					bturn.setPath(oppose);
					bturn.setTexture(Toolkit.loadImage("png", oppose));
			
					updateBoardAround(a , i, j) ;
			}
			clicked = false;
	    }	
		
		if(DrawPanel.isClickedCube.getX() != -1 && DrawPanel.isClickedCube.getY() != -1)
		{
			if(DrawPanel.isClickedCube.getX() > 320+ (52*j) && DrawPanel.isClickedCube.getX() < 320+ (52*j) + 51 &&
			   DrawPanel.isClickedCube.getY() > 20 + (52*i) && DrawPanel.isClickedCube.getY() < 20 + (52*i) + 51)
			{
				a[i][j].setTexture(Toolkit.loadImage("png","emptyEPressed"));
			}
		}
		else
		{
			if(a[i][j].getPath().equals("emptyE") || (a[i][j].getPath().equals("ownredE")) || (a[i][j].getPath().equals("ownyellowE")) ||
			  (a[i][j].getPath().equals("ownBoth")))
			{
				ownElseEmprove(a,i,j);
			}
			else
			{
				if(a[i][j].getPath().equals("redE"))
				{
					a[i][j].setTexture(Toolkit.loadImage("png","redE"));
				}
				else
				{
					if(a[i][j].getPath().equals("yellowE"))
					{
						a[i][j].setTexture(Toolkit.loadImage("png","yellowE"));
					}
				}
			}
		}
		
		
	}
	
	public void updateBoardAround(Button [][] a ,int i ,int j) 
	{		
		if(j > 0)
		{
			if(!a[i][j-1].getPath().equals("yellowE") && !(a[i][j-1].getPath().equals("redE")))
			{
				ownElseEmprove(a, i,  j-1);
			}
		}
		if(i > 0)
		{
			if(!a[i-1][j].getPath().equals("yellowE") && !(a[i-1][j].getPath().equals("redE")))
			{
				ownElseEmprove(a, i-1, j);
			}
		}
		if(j < a[0].length - 1)
		{
			if(!a[i][j+1].getPath().equals("yellowE") && !(a[i][j+1].getPath().equals("redE")))
			{
				ownElseEmprove(a, i, j+1);
			}
		}
		if(i < a.length - 1)
		{
			if(!a[i+1][j].getPath().equals("yellowE") && !(a[i+1][j].getPath().equals("redE")))
			{
				ownElseEmprove(a, i+1, j);
			}
		}				
	}
	
	public void changeTurn(Button bturn)
	{
		if(bturn.getPath().equals("yellowE"))
		{
			bturn.setPath("redE");
			bturn.setTexture(Toolkit.loadImage("png", "redE"));
		}
		else
		{
			bturn.setPath("yellowE");
			bturn.setTexture(Toolkit.loadImage("png", "yellowE"));
		}
	}
	
	public void changeTurnWST(Button bturn)
	{
		if(bturn.getPath().equals("yellowE"))
		{
			bturn.setPath("redE");
		}
		else
		{
			bturn.setPath("yellowE");
		}
	}
	

	
	public void ownElseEmproveWST(Button [][] a,int i, int j)
	{
		int jMinus1Yellow=0;
		int iMinus1Yellow=0;
		int jPlus1Yellow=0;
		int iPlus1Yellow=0;
		
		int jMinus1Red=0;
		int iMinus1Red=0;
		int jPlus1Red=0;
		int iPlus1Red=0;
		
		if(j > 0)
		{
			if(a[i][j-1].getPath().equals("yellowE"))
			{
				jMinus1Yellow++;
			}
		}
		if(i > 0)
		{
			if(a[i-1][j].getPath().equals("yellowE"))
			{
				iMinus1Yellow++;
			}
		}
		if(j < a[0].length - 1)
		{
			if(a[i][j+1].getPath().equals("yellowE"))
			{
				jPlus1Yellow++;
			}
		}
		if(i < a.length - 1)
		{
			if(a[i+1][j].getPath().equals("yellowE"))
			{
				iPlus1Yellow++;
			}
		}
		
		
		
		if(j > 0)
		{
			if(a[i][j-1].getPath().equals("redE"))
			{
				jMinus1Red++;
			}
		}
		if(i > 0)
		{
			if(a[i-1][j].getPath().equals("redE"))
			{
				iMinus1Red++;
			}
		}
		if(j < a[0].length - 1)
		{
			if(a[i][j+1].getPath().equals("redE"))
			{
				jPlus1Red++;
			}
		}
		if(i < a.length - 1)
		{
			if(a[i+1][j].getPath().equals("redE"))
			{
				iPlus1Red++;
			}
		}
		
		
		if(((jMinus1Yellow==0) && (iMinus1Yellow==0) && (jPlus1Yellow==0) && (iPlus1Yellow==0)) && 
		   ((jMinus1Red==0)    && (iMinus1Red==0)    && (jPlus1Red==0)    && (iPlus1Red==0))
		  )
		{
			a[i][j].setPath("emptyE");
		}
		if(((jMinus1Yellow==1) || (iMinus1Yellow==1) || (jPlus1Yellow==1) || (iPlus1Yellow==1)) && 
		   ((jMinus1Red==0)    && (iMinus1Red==0)    && (jPlus1Red==0)    && (iPlus1Red==0))
		 )
		{
			a[i][j].setPath("ownyellowE");
		}
		if(((jMinus1Yellow==0) && (iMinus1Yellow==0) && (jPlus1Yellow==0) && (iPlus1Yellow==0)) && 
		   ((jMinus1Red==1)    || (iMinus1Red==1)    || (jPlus1Red==1)    || (iPlus1Red==1))
		  )
		{
			a[i][j].setPath("ownredE");
		}
		if(((jMinus1Yellow==1) || (iMinus1Yellow==1) || (jPlus1Yellow==1) || (iPlus1Yellow==1)) && 
		   ((jMinus1Red==1)    || (iMinus1Red==1)    || (jPlus1Red==1)    || (iPlus1Red==1))
		  )
		{
			a[i][j].setPath("ownBoth");
		}
		
	}
	
	public void updateBoardAroundWST(Button [][] a ,int i ,int j) 
	{		
		if(j > 0)
		{
			if(!a[i][j-1].getPath().equals("yellowE") && !(a[i][j-1].getPath().equals("redE")))
			{
				ownElseEmproveWST(a, i,  j-1);
			}
		}
		if(i > 0)
		{
			if(!a[i-1][j].getPath().equals("yellowE") && !(a[i-1][j].getPath().equals("redE")))
			{
				ownElseEmproveWST(a, i-1, j);
			}
		}
		if(j < a[0].length - 1)
		{
			if(!a[i][j+1].getPath().equals("yellowE") && !(a[i][j+1].getPath().equals("redE")))
			{
				ownElseEmproveWST(a, i, j+1);
			}
		}
		if(i < a.length - 1)
		{
			if(!a[i+1][j].getPath().equals("yellowE") && !(a[i+1][j].getPath().equals("redE")))
			{
				ownElseEmproveWST(a, i+1, j);
			}
		}				
	}
	
	
	
	public void render(Graphics g) {
		g.drawImage(texture, position.x,position.y, null);
	}
	
	public void Destractur()
	{
		TestDraw.removeListener(this);
	}

	public boolean inRange(Point point)
	{
		return collider().intersects(new Rectangle(point.x, point.y, 1, 1));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(inRange(new Point(e.getPoint().x,  e.getPoint().y - 26)))
		{
			clicked = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		DrawPanel.isMousePressed=true;
		
		if(inRange(new Point(e.getPoint().x,  e.getPoint().y - 26)))
		{			
			if(e.getPoint().y - 26 > 68+(86*0) &&  e.getPoint().y - 26 < 68+(86*0)+64 &&
				e.getPoint().x > 48 && e.getPoint().x < 48+204)
				DrawPanel.isClicked = 0;
			if(e.getPoint().y - 26 > 68+(86*1) &&  e.getPoint().y - 26 < 68+(86*1)+64 &&
				e.getPoint().x > 48 && e.getPoint().x < 48+204)
				DrawPanel.isClicked = 1;
			if(e.getPoint().y - 26 > 68+(86*2) &&  e.getPoint().y - 26 < 68+(86*2)+64 &&
				e.getPoint().x > 48 && e.getPoint().x < 48+204)
				DrawPanel.isClicked = 2;
			if(e.getPoint().y - 26 > 68+(86*3) &&  e.getPoint().y - 26 < 68+(86*3)+64 &&
				e.getPoint().x > 48 && e.getPoint().x < 48+204)
				DrawPanel.isClicked = 3;
			if(e.getPoint().y - 26 > 580 &&  e.getPoint().y - 26 < 580+64 &&
				e.getPoint().x > 48 && e.getPoint().x < 48+204)
				DrawPanel.isClicked = 4;
			
			if(DrawPanel.isClicked==-1)
			{
				DrawPanel.isClickedCube=new Point(e.getPoint().x,e.getPoint().y - 26);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(inRange(new Point(e.getPoint().x,  e.getPoint().y - 26)))
		{
			clicked = true;
			DrawPanel.isClicked = -1;
			DrawPanel.isClickedCube=new Point(-1,-1);
		}
		
		DrawPanel.isMousePressed=false;
	}

	
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}



	public String getPath() {
		return path;
	}

	
	public void setPath(String path) {
		this.path = path;
	}

	
	public BufferedImage getTexture() {
		return texture;
	}

	
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public void mouseOn(Point pos , Button turn)
	{
		if(path.equals("emptyE"))
		{
			if(inRange(new Point(pos.x, pos.y - 26)))
			{
				texture = Toolkit.loadImage("png", "emptyEOn");
			}
		}
		if(path.equals("ownyellowE") && turn.getPath().equals("yellowE"))
		{
			if(inRange(new Point(pos.x, pos.y - 26)))
			{
				texture = Toolkit.loadImage("png", "ownyellowEOn");
			}
		}
		if(path.equals("ownredE") && turn.getPath().equals("redE"))
		{
			if(inRange(new Point(pos.x, pos.y - 26)))
			{
				texture = Toolkit.loadImage("png", "ownredEOn");
			}
		}
	}
	
	
}
