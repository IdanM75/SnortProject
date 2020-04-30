import java.awt.Color;     
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.io.BufferedReader;
import java.io.FileWriter;

import java.util.Collections;

public class DrawPanel extends JPanel 
{
	static Scanner reader=new Scanner(System.in);
	
	public static String whoStart=TestDraw.whoStart;
	public static int sizeRow =TestDraw.sizeRow;
	public static int sizeColumn=TestDraw.sizeColumn;
	public static int deepRed=TestDraw.deepRed;
	public static int deepYellow=TestDraw.deepYellow;
	
	public static int isClicked = -1;
	public static Point isClickedCube=new Point(-1,-1);
	
	public static final String pathFile = "res//";
	public static FileWriter writerFile;
	public static BufferedReader readerFile;
	
	public static int beta;
	public static int alpha;
	
	public static double  paintBar;
	
	public static Point mousePos;
	
	public static Button [][] BoardImagesArray;
	
	public static boolean isMousePressed=false;
	
	
	int sizeButton=5;
	private Button [] BoardButtonsArray;
	private Button turn;
	private Board paintBoard= new Board();
	
	int saveUndoI=0;
	int saveUndoJ=0;
	String undoPath="emptyE";
	boolean ifWasEmpty=false;
	
	int saveUndoIBin=0;
	int saveUndoJBin=0;
	String undoPathBin="emptyE";
		
	int arc_rotation;
	int changeColor;
	boolean isChangeColor=false;
	
	Timer timer = new Timer();
	Timer timer1 = new Timer();
	boolean timerPos=true;
	
	String ifWin;
	
	int height=TestDraw.height;
	int width=TestDraw.width;
	
	Point pointFromMinMax;
	
	String saveTurnAtFirst;
	
	Random rand = new Random();
	Random randMove = new Random();
	int  numRad;
	int numRadMove;
	
	int IPlace;
	int JPlace;
	
	int whoRed;
	int whoYellow;
	int sizeBoardRow;
	int sizeBoardColumn;
	
	
	final JComponent[] inputs1 = new JComponent[] {
			new JLabel("Its a draw!"),
	};

	final JComponent[] inputs2 = new JComponent[] {
			new JLabel("The Red Won!"),
	};
	
	final JComponent[] inputs3 = new JComponent[] {
			new JLabel("The Yellow Won!"),
	};
	
	public static Button[][] getBoardImagesArray() {
		return BoardImagesArray;
	}

	public static void setBoardImagesArray(Button[][] boardImagesArray) {
		BoardImagesArray = boardImagesArray;
	}

	
	
	public String win(Button [][] a)
	{
		int countRed=0;
		int countYellow=0;
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				if(a[i][j].getPath().equals("ownredE") || a[i][j].getPath().equals("emptyE"))
				{
					countRed++;
				}
				if(a[i][j].getPath().equals("ownyellowE") || a[i][j].getPath().equals("emptyE"))
				{
					countYellow++;
				}
					
			}
		}
		if(countRed==0 && countYellow!=0)
		{
			return ("Yellow");
		}
		if(countRed!=0 && countYellow==0)
		{
			return ("Red");
		}
		if(countRed==0 && countYellow==0)
		{
			return("Non");
		}
		return "a";
		
	}
	
	public static int numYellowWin(Button [][] a)
	{
		int countYellow=0;
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				if(a[i][j].getPath().equals("ownyellowE") || a[i][j].getPath().equals("emptyE"))
				{
					countYellow++;
				}	
			}
		}
		return countYellow;

	}
	
	public static int numRedWin(Button [][] a) 
	{
		int countRed=0;
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				if(a[i][j].getPath().equals("ownredE") || a[i][j].getPath().equals("emptyE"))
				{
					countRed++;
				}					
			}
		}
		return countRed;

	}
	
	public void winEventFire(String player)
	{
		if(player.equals("draw"))
		{
			JOptionPane.showMessageDialog(null, inputs1, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			if(player.equals("Red"))
				JOptionPane.showMessageDialog(null, inputs2, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, inputs3, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
		}
			timer.cancel();
			System.exit(0);
	}

	
	
	public int howRedGood(Button [][] a)
	{
		int countRedGood=0;
		int countWas=0;
		int countHas=0;
		
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				if(a[i][j].getPath().equals("ownredE"))
				{					
					if(j > 0)
					{
						countWas++;
						
						if(a[i][j-1].getPath().equals("ownredE") || a[i][j-1].getPath().equals("redE") || a[i][j-1].getPath().equals("ownBoth"))
						{
							countRedGood++;
						}
					}
					if(i > 0)
					{
						countWas++;
						
						if(a[i-1][j].getPath().equals("ownredE") || a[i-1][j].getPath().equals("redE") || a[i-1][j].getPath().equals("ownBoth"))
						{
							countRedGood++;
						}
					}
					if(j < sizeColumn - 1)
					{
						countWas++;
						
						if(a[i][j+1].getPath().equals("ownredE") || a[i][j+1].getPath().equals("redE") || a[i][j+1].getPath().equals("ownBoth"))
						{
							countRedGood++;
						}
					}
					if(i < sizeRow - 1)
					{
						countWas++;
						
						if(a[i+1][j].getPath().equals("ownredE") || a[i+1][j].getPath().equals("redE") || a[i+1][j].getPath().equals("ownBoth"))
						{
							countRedGood++;
						}
					}	
				}
				
				if(countWas==countRedGood && countRedGood>0)
				{
					countHas++;
				}
				
				countRedGood=0;
				countWas=0;
			}
		}
		return countHas;

	}

	public int howYellowGood(Button [][] a)
	{
		int countYellowGood=0;
		int countWas=0;
		int countHas=0;
		
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				if(a[i][j].getPath().equals("ownyellowE"))
				{
					if(j > 0)
					{
						countWas++;
						
						if(a[i][j-1].getPath().equals("ownyellowE") || a[i][j-1].getPath().equals("yellowE") || a[i][j-1].getPath().equals("ownBoth"))
						{
							countYellowGood++;
						}
					}
					if(i > 0)
					{
						countWas++;
						
						if(a[i-1][j].getPath().equals("ownyellowE") || a[i-1][j].getPath().equals("yellowE") || a[i-1][j].getPath().equals("ownBoth"))
						{
							countYellowGood++;
						}
					}
					if(j < sizeColumn - 1)
					{
						countWas++;
						
						if(a[i][j+1].getPath().equals("ownyellowE") || a[i][j+1].getPath().equals("yellowE") || a[i][j+1].getPath().equals("ownBoth"))
						{
							countYellowGood++;
						}
					}
					if(i < sizeRow - 1)
					{
						countWas++;
						
						if(a[i+1][j].getPath().equals("ownyellowE") || a[i+1][j].getPath().equals("yellowE") || a[i+1][j].getPath().equals("ownBoth"))
						{
							countYellowGood++;
						}
					}	
				}
				
				if(countWas==countYellowGood && countYellowGood>0)
				{
					countHas++;
				}
				
				countYellowGood=0;
				countWas=0;		
			}
		}
		return countHas;
	}
	
	public int numRedHad(Button [][] a) 
	{
		int countRed=0;
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				if(a[i][j].getPath().equals("ownredE"))
				{
					countRed++;
				}					
			}
		}
		return countRed;
	}
	
	public int numYellowHad(Button [][] a)
	{
		int countYellow=0;
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				if(a[i][j].getPath().equals("ownyellowE"))
				{
					countYellow++;
				}	
			}
		}
		return countYellow;
	}

	public int redSide(Button [][] a)
	{
		int countRedGood=0;
		
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				if(a[i][j].getPath().equals("ownredE"))
				{					
					if(j > 0)
					{					
						if(a[i][j-1].getPath().equals("ownredE"))
						{
							countRedGood++;
						}
					}
					if(i > 0)
					{
						if(a[i-1][j].getPath().equals("ownredE"))
						{
							countRedGood++;
						}
					}
					if(j < sizeColumn - 1)
					{
						if(a[i][j+1].getPath().equals("ownredE"))
						{
							countRedGood++;
						}
					}
					if(i < sizeRow - 1)
					{
						if(a[i+1][j].getPath().equals("ownredE"))
						{
							countRedGood++;
						}
					}	
				}
			}
		}
		return countRedGood;

	}

	public int yellowSide(Button [][] a)
	{
		int countYellowGood=0;
		
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				if(a[i][j].getPath().equals("ownyellowE"))
				{
					if(j > 0)
					{
						if(a[i][j-1].getPath().equals("ownyellowE"))
						{
							countYellowGood++;
						}
					}
					if(i > 0)
					{
						if(a[i-1][j].getPath().equals("ownyellowE"))
						{
							countYellowGood++;
						}
					}
					if(j < sizeColumn - 1)
					{
						if(a[i][j+1].getPath().equals("ownyellowE"))
						{
							countYellowGood++;
						}
					}
					if(i < sizeRow - 1)
					{
						if(a[i+1][j].getPath().equals("ownyellowE"))
						{
							countYellowGood++;
						}
					}	
				}
			}
		}
		return countYellowGood;
	}
	
	
	
	public int laserBin(Button [][] a, Button bturn, int deep, String turn, int deepSave)
	{
		int gradeMax=-1000;
		String save;
		
		int saveIMax=-1;
		int saveJMax=-1;
		String savePath="redE";
		
		int gradeRed;
		int gradeYellow;
		
		if(bturn.getPath().equals("redE"))
		{
			if(numRedWin(a)==0)
			{
				return 0;
			}
			if(numYellowWin(a)==0)
			{
				return 1000;
			}
		}
		if(bturn.getPath().equals("yellowE"))
		{
			if(numYellowWin(a)==0)
			{
				return 0;
			}
			if(numRedWin(a)==0)
			{
				return 1000;
			}
		}		
		
		if(deep<deepSave && deep>=0)
		{
			for(int i=0;i<sizeRow;i++)
			{
				for(int j=0;j<sizeColumn;j++)
				{
					if((!a[i][j].getPath().equals("yellowE") && !(a[i][j].getPath().equals("redE"))) &&
							((bturn.getPath().equals("yellowE") && !a[i][j].getPath().equals("ownredE")) || (bturn.getPath().equals("redE") && !a[i][j].getPath().equals("ownyellowE"))) &&
							!a[i][j].getPath().equals("ownBoth"))
					{
						save = a[i][j].getPath();

						a[i][j].setPath(bturn.getPath());
						//a[i][j].setTexture(Toolkit.loadImage("png", bturn.getPath()));
						a[i][j].updateBoardAroundWST(a , i, j) ;					//WST!!						
						
						if (bturn.getPath().equals("redE"))
						{
							gradeRed=numRedHad(a)+howRedGood(a);
							if(gradeRed > gradeMax)
							{
								gradeMax=gradeRed;
								saveIMax=i;
								saveJMax=j;
								savePath="redE";
							}
							if(gradeRed == gradeMax)
							{
								numRadMove = randMove.nextInt(2) + 1;
								if(numRadMove==1)
								{
									gradeMax=gradeRed;
									saveIMax=i;
									saveJMax=j;
									savePath="redE";
								}
							}
						}	
						if (bturn.getPath().equals("yellowE"))
						{
							gradeYellow=numYellowHad(a)+howYellowGood(a);
							if(gradeYellow > gradeMax)
							{
								gradeMax=gradeYellow;
								saveIMax=i;
								saveJMax=j;
								savePath="yellowE";
							}
							if(gradeYellow == gradeMax)
							{
								numRadMove = randMove.nextInt(2) + 1;
								if(numRadMove==1)
								{
									gradeMax=gradeYellow;
									saveIMax=i;
									saveJMax=j;
									savePath="yellowE";
								}
							}
						}
					
						a[i][j].setPath(save);
						//a[i][j].setTexture(Toolkit.loadImage("png", save));
						a[i][j].updateBoardAroundWST(a , i, j) ;			//WST
						
						
					}
				}
			}
			
			if(gradeMax!=-1000)
				a[saveIMax][saveJMax].setPath(savePath);
		
			if(!bturn.getPath().equals(turn))
				gradeMax=gradeMax*(-1);
		
			bturn.changeTurn(bturn);
		}
		
		if(deep==deepSave)
		{
			if (bturn.getPath().equals("redE"))
			{
				gradeRed=numRedHad(a)+howRedGood(a);
				gradeMax=gradeRed;
			}	
			if (bturn.getPath().equals("yellowE"))
			{
				gradeYellow=numYellowHad(a)+howYellowGood(a);
				gradeMax=gradeYellow;

			}
			if(deep!=0)
			{
				bturn.changeTurn(bturn);
				return (gradeMax+laserBin(a, bturn, deep, turn, deepSave+1));
			}
		}

		if(deep==0)
		{
			return gradeMax;
		}
	
		return (gradeMax+laserBin(a, bturn, deep-1, turn, deepSave));
	}

	public int minMaxBest(Button [][] a, Button bturn, int deep, String turn)
	{		
		int gradeMax=-10000;
		int gradeMin=10000;
		String save;
		
		String saveTurn;
		
		int holdGrade=0;
		
		if(bturn.getPath().equals("redE"))
		{
			if(numRedWin(a)==0)
			{
				return 9000;
			}
			if(numYellowWin(a)==0)
			{
				return 0;
			}
		}
		if(bturn.getPath().equals("yellowE"))
		{
			if(numYellowWin(a)==0)
			{
				return 9000;
			}
			if(numRedWin(a)==0)
			{
				return 0;
			}
		}
		
		if(deep==0)
		{
			if (turn.equals("redE"))
			{
				return (numRedHad(a)*2+howRedGood(a)-numYellowHad(a)*2-howYellowGood(a));
			}	
			if (turn.equals("yellowE"))
			{
				return (numYellowHad(a)*2+howYellowGood(a)-numRedHad(a)*2-howRedGood(a));
			}	
		}
		
		if(deep > 0)
		{
			if(deep % 2 == 0 && bturn.getPath().equals(turn))
			{
				bturn.changeTurnWST(bturn);
			}
			if(deep % 2 == 1 && !(bturn.getPath().equals(turn)))
			{
				bturn.changeTurnWST(bturn);
			}

			saveTurn=bturn.getPath();
			
			for(int i=0;i<a.length;i++)
			{
				for(int j=0;j<a[i].length;j++)
				{
					if(		(bturn.getPath().equals("yellowE") && a[i][j].getPath().equals("ownyellowE")) || 
							(bturn.getPath().equals("redE")    && a[i][j].getPath().equals("ownredE"))    || 
							a[i][j].getPath().equals("emptyE"))
					{
						save = a[i][j].getPath();

						a[i][j].setPath(bturn.getPath());
						//a[i][j].setTexture(Toolkit.loadImage("png", bturn.getPath()));
						a[i][j].updateBoardAroundWST(a , i, j) ;					//WST!!						
						
						//textDraw(a);
						
						holdGrade= minMaxBest(a, bturn,deep-1,turn);
						bturn.setPath(saveTurn);
						
						if(bturn.getPath().equals(turn))
						{
							if(holdGrade > DrawPanel.alpha && DrawPanel.alpha!=-10000)
							{
								a[i][j].setPath(save);
								//a[i][j].setTexture(Toolkit.loadImage("png", save));
								a[i][j].updateBoardAroundWST(a , i, j) ;			//WST
								
								//System.out.println(bturn.getPath()+" cut at "+deep+" deep , the grade is: "+holdGrade);
								return holdGrade ;
							}
							if(holdGrade > gradeMax)
							{
								gradeMax=holdGrade;
							}
						}
						else
						{
						 	if(holdGrade < DrawPanel.beta && DrawPanel.beta!=10000)
							{
								a[i][j].setPath(save);
								//a[i][j].setTexture(Toolkit.loadImage("png", save));
								a[i][j].updateBoardAroundWST(a , i, j) ;			//WST
								
								//System.out.println(bturn.getPath()+" cut at "+deep+" deep , the grade is: "+holdGrade);
								return holdGrade ;
							}
							if(holdGrade < gradeMin)
							{
								gradeMin=holdGrade;
							}
						}

						a[i][j].setPath(save);
						//a[i][j].setTexture(Toolkit.loadImage("png", save));
						a[i][j].updateBoardAroundWST(a , i, j) ;			//WST
					}
				}
			}
			
			if(bturn.getPath().equals(turn))
			{
				DrawPanel.alpha=gradeMax;
				//System.out.println(bturn.getPath()+" return the gradeMax: "+gradeMax+" at "+deep+" deep");
				return gradeMax;
			}
			else
			{
				DrawPanel.beta=gradeMin;
				//System.out.println(bturn.getPath()+" return the gradeMin: "+gradeMin+" at "+deep+" deep");
				return gradeMin;
			}
			
		}
		return 0;
	}

	public Point newMinMax(Button [][] a, Button bturn ,int deep)
	{
		Point saveMove = new Point(-1, -1);
		
		int iWork;
		int jWork;
		int gradeHelp;
		int gradeMax=-1000;
		double saveNum=0;
		double num=0;
		
		ArrayList<Point> Moves = new ArrayList<Point>();
		
		String saveCli;		
		String saveTurnPath=bturn.getPath();
		
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				if(		(bturn.getPath().equals("yellowE") && a[i][j].getPath().equals("ownyellowE")) || 
						(bturn.getPath().equals("redE")    && a[i][j].getPath().equals("ownredE"))    || 
						a[i][j].getPath().equals("emptyE"))
				{
					Moves.add(new Point(i,j));
					saveNum++;
				}
			}
		}
		
		Collections.shuffle(Moves);
		
		for(Point Move : Moves)
		{
			iWork=(int) Move.getX();
			jWork=(int) Move.getY();
			
			saveCli = a[iWork][jWork].getPath();
			
			a[iWork][jWork].setPath(bturn.getPath());
			a[iWork][jWork].updateBoardAroundWST(a, iWork, jWork);
			
			//DrawPanel.beta=10000;
			DrawPanel.alpha=-10000;
			
			gradeHelp=minMaxBest(a, bturn, deep-1, bturn.getPath());
			
			if(gradeHelp > gradeMax)
			{
				saveMove.setLocation(iWork, jWork);
				gradeMax=gradeHelp;
			}
			
			a[iWork][jWork].setPath(saveCli);
			a[iWork][jWork].updateBoardAroundWST(a , iWork, jWork);
			bturn.setPath(saveTurnPath);
			
			num++;
			DrawPanel.paintBar=(num/saveNum);
			repaint();
			
		}
		
		return saveMove;
	}
	
	
	
	
	public void textDraw(Button [][] a)
	{
		System.out.println("------------------------");
	
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				if(a[i][j].getPath().equals("emptyE"))
				{
					System.out.print("|E ");
				}
				if(a[i][j].getPath().equals("redE"))
				{
					System.out.print("|R ");
				}
				if(a[i][j].getPath().equals("yellowE"))
				{
					System.out.print("|Y ");
				}
				if(a[i][j].getPath().equals("ownredE"))
				{
					System.out.print("|OR");
				}
				if(a[i][j].getPath().equals("ownyellowE"))
				{
					System.out.print("|OY");
				}
				if(a[i][j].getPath().equals("ownBoth"))
				{
					System.out.print("|B ");
				}
			}
			System.out.println("|");
		}
		System.out.println("------------------------");
	}
	
	
	
	public DrawPanel()
	{			
		final File file = new File(pathFile + "SaveArry.txt");
		
		BoardImagesArray =new Button[sizeRow][sizeColumn];
		BoardButtonsArray =new Button[sizeButton];
		
		Arrays arrays= new Arrays();
				
		arrays.create2DArrayImages(BoardImagesArray,sizeRow, sizeColumn);
		arrays.createArrayButtons(BoardButtonsArray,sizeButton);
		
		turn = new Button(whoStart, new Point(556,580), 51, 51);
		turn.setTexture(Toolkit.loadImage("png", whoStart));
	
		saveTurnAtFirst = whoStart;
		
		setBackground(Color.lightGray);
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() 
			{			
				for(int i=0;i<sizeButton;i++)
				{
					BoardButtonsArray[i].checkUndo(BoardButtonsArray[i].getPath(), BoardImagesArray, turn, saveUndoI, saveUndoJ, deepRed, deepYellow, saveUndoIBin, saveUndoJBin, BoardButtonsArray, undoPath, undoPathBin);
					
					BoardButtonsArray[i].checkSaveGame(BoardButtonsArray[i].getPath(), BoardImagesArray, turn, BoardButtonsArray, file);
					
					BoardButtonsArray[i].checkLoadGame(BoardButtonsArray[i].getPath(), BoardImagesArray, turn, BoardButtonsArray, file);
					
					BoardButtonsArray[i].checkNewGame(BoardButtonsArray[i].getPath(), BoardImagesArray, turn, saveTurnAtFirst, BoardButtonsArray);
					
					BoardButtonsArray[i].checkExit(BoardButtonsArray[i].getPath(), timer, BoardButtonsArray);
				}
				
				if(turn.getPath().equals("yellowE") && deepYellow!=-1)
				{
					timerPos=false;
					//=========================
					DrawPanel.beta=10000;
					//=========================
					pointFromMinMax=newMinMax(BoardImagesArray,turn,deepYellow);
					IPlace=(int) pointFromMinMax.getX();
					JPlace=(int) pointFromMinMax.getY();
						
					undoPathBin=BoardImagesArray[IPlace][JPlace].getPath();
						
					BoardImagesArray[IPlace][JPlace].setPath(turn.getPath());
					BoardImagesArray[IPlace][JPlace].setTexture(Toolkit.loadImage("png", turn.getPath()));
					BoardImagesArray[IPlace][JPlace].updateBoardAround(BoardImagesArray, IPlace, JPlace);
						
					saveUndoIBin=IPlace;
					saveUndoJBin=JPlace;
					
					turn.setPath("redE");
					turn.setTexture(Toolkit.loadImage("png", "redE"));
					
					timerPos=true;
				}
				else
				{
					if(turn.getPath().equals("redE") && deepRed!=-1)
					{
						timerPos=false;
						
						//=========================
						DrawPanel.beta=10000;
						//=========================
						pointFromMinMax=newMinMax(BoardImagesArray,turn,deepRed);
						IPlace=(int) pointFromMinMax.getX();
						JPlace=(int) pointFromMinMax.getY();
							
						undoPathBin=BoardImagesArray[IPlace][JPlace].getPath();
							
						BoardImagesArray[IPlace][JPlace].setPath(turn.getPath());
						BoardImagesArray[IPlace][JPlace].setTexture(Toolkit.loadImage("png", turn.getPath()));
						BoardImagesArray[IPlace][JPlace].updateBoardAround(BoardImagesArray, IPlace, JPlace);
							
						saveUndoIBin=IPlace;
						saveUndoJBin=JPlace;

						turn.setPath("yellowE");
						turn.setTexture(Toolkit.loadImage("png", "yellowE"));
						
						timerPos=true;
					}
					else
					{
						//mousePos=getMousePosition();
						//System.out.println(mousePos.getX()/*+" "+mousePos.getY()*/);
						
						for(int i=0;i<BoardImagesArray.length;i++)
						{
							for(int j=0;j<BoardImagesArray[i].length;j++)
							{
								
								if(BoardImagesArray[i][j].getPath().equals("emptyE")||BoardImagesArray[i][j].getPath().equals("ownredE")||BoardImagesArray[i][j].getPath().equals("ownyellowE"))
								{
									ifWasEmpty=true;
									undoPath=BoardImagesArray[i][j].getPath();
								}
						
								BoardImagesArray[i][j].updateBoard(BoardImagesArray,i,j,turn);
						
								if((BoardImagesArray[i][j].getPath().equals("redE") || BoardImagesArray[i][j].getPath().equals("yellowE")) && ifWasEmpty==true)
								{
									saveUndoI=i;
									saveUndoJ=j;	
								}
								ifWasEmpty=false;
							}
						}
					}
				}

				arc_rotation +=20;
				
				if(changeColor>240)
					isChangeColor=true;
				if(changeColor<10)
					isChangeColor=false;
				
				if(isChangeColor==false)
					changeColor+=10;
				else
					changeColor-=10;
				
				//repaint();
				
				ifWin=win(BoardImagesArray);
				if(ifWin.equals("Red"))
				{
					System.out.println("Red wins!");
					winEventFire("Red");
				}
				if(ifWin.equals("Yellow"))
				{
					System.out.println("Yellow wins!");
					winEventFire("Yellow");
				}
				if(ifWin.equals("Non"))
				{
					System.out.println("Draw!");
					winEventFire("draw");
				}
				
				
				if((deepYellow==-1 || deepRed==-1) && isMousePressed==false)
				{
					for(int i=0;i<BoardImagesArray.length;i++)
					{
						for(int j=0;j<BoardImagesArray[i].length;j++)
						{
							BoardImagesArray[i][j].mouseOn(MouseMotion.pos , turn);
						}
					}
				}
			}
			
		}, 0, 60);
	
		timer1.schedule(new TimerTask() {
			
			@Override
			public void run() 
			{
				if((deepYellow==-1 || deepRed==-1) && timerPos==true && isMousePressed==false)
				{
					for(int i=0;i<BoardImagesArray.length;i++)
					{
						for(int j=0;j<BoardImagesArray[i].length;j++)
						{
							BoardImagesArray[i][j].mouseOn(MouseMotion.pos , turn);
						}
					}
			
					repaint();
				}
				else
				{
					repaint();
				}
			}
		}, 0, 60);
		
	}
	
	
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent( g );

		paintBoard.Draw(g, BoardImagesArray, BoardButtonsArray, turn, arc_rotation, changeColor, deepRed, deepYellow);
		
	}
	
}
