import java.awt.Color; 
import java.awt.Font;
import java.awt.Graphics;

public class Board 
{
	int height=TestDraw.height;
	int width=TestDraw.width;
	
	public void Draw(Graphics g, Button [][] BoardImagesArray, Button [] BoardButtonsArray, Button bturn, int arc_rotation, int changeColor, int deepRed, int deepYellow)
	{
		int doubleInt;
		
		for (int i=0 ; i<255 ; i++)   				 //DRAW THE CHANGING COLOR
		{
			Color color = new Color (i,(i)/3,0);
			g.setColor(color);
			g.fillRect(300,2*i,width-316,2);	
		}
		
		g.fillRect(300,510,width-316,50);			//DRAW THE BOARD
		g.setColor(Color.BLACK);
		g.fillRect(0,0,300,height-40);
		g.setColor(Color.GRAY);
		g.fillRect(4,4,292,height-48);
		
		g.setColor(Color.BLACK);
		g.fillRect(300,height-140,width-316,100);
		g.setColor(Color.GRAY);
		g.fillRect(300,height-136,width-320,92);	
		
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.BOLD,20));
		g.drawString("Idan Snort Project ", 48+21, 45);
		
		Color color1 = new Color (255,(changeColor),0);   //DRAW CHANGING text
		g.setColor(color1);
		g.setFont(new Font("TimesRoman", Font.BOLD,20));
		g.drawString("Idan Snort Project ", 48+18, 42);
		
		g.setFont(new Font("TimesRoman", Font.BOLD,18)); 	// DRAW THE DESCRIPTOIN
		g.setColor(Color.black);
		g.drawString("Game Description", 70, 440);
		g.setFont(new Font("TimesRoman", Font.BOLD,16));
		g.drawString("The Goal Of The Game Is", 57, 460);
		g.drawString("To capture more areas", 65, 480);
		g.drawString("than your enemy, Try to", 62, 500);
		g.drawString("get as many points as ", 70, 520);
		g.drawString("possible, Good Luck!!!", 67, 540);
		
		if(bturn.getPath().equals("redE") && deepRed != -1)
		{
			doubleInt=(int) ((width-323)*DrawPanel.paintBar);
			
			g.setColor(Color.red);
			//g.drawRect(300, height-54, width-320, 10);
			g.drawRect(301, height-53, width-322, 8);
			g.fillRect(302, height-53, doubleInt, 8);
		}
		if(bturn.getPath().equals("yellowE") && deepYellow != -1)
		{
			doubleInt=(int) ((width-323)*DrawPanel.paintBar);
			
			g.setColor(Color.yellow);
			//g.drawRect(300, height-54, width-320, 10);
			g.drawRect(301, height-53, width-322, 8);
			g.fillRect(302, height-53, doubleInt, 8);
		}
		
		g.setFont(new Font("TimesRoman", Font.BOLD,14));  		//DRAW THE OPTIONS ON THE BOT
		g.setColor(Color.black);
		g.drawString("To The Red Left ", 345, 610);
		g.setFont(new Font("TimesRoman", Font.BOLD,16));
		g.setColor(Color.red);
		if(DrawPanel.numRedWin(BoardImagesArray)==100)
		{
			g.drawString(""+DrawPanel.numRedWin(BoardImagesArray)+"", 458, 610);
		}
		else
		{
			if(DrawPanel.numRedWin(BoardImagesArray)>=10 && DrawPanel.numRedWin(BoardImagesArray)<100)
			{
				g.drawString("0"+DrawPanel.numRedWin(BoardImagesArray)+"", 458, 610);
			}
			else
			{
				g.drawString("00"+DrawPanel.numRedWin(BoardImagesArray)+"", 458, 610);
			}
		}
		g.setColor(Color.black);
		g.drawString(" Options", 480, 610);
		g.setFont(new Font("TimesRoman", Font.BOLD,14));
		
		g.setColor(Color.black);
		g.drawString("To The Yellow Left ", 620, 610);
		g.setFont(new Font("TimesRoman", Font.BOLD,16));
		g.setColor(Color.yellow);
		if(DrawPanel.numYellowWin(BoardImagesArray)==100)
		{
			g.drawString(""+DrawPanel.numYellowWin(BoardImagesArray)+"", 752, 610);
		}
		else
		{
			if(DrawPanel.numYellowWin(BoardImagesArray)>=10 && DrawPanel.numYellowWin(BoardImagesArray)<100)
			{
				g.drawString("0"+DrawPanel.numYellowWin(BoardImagesArray)+"", 752, 610);
			}
			else
			{
				g.drawString("00"+DrawPanel.numYellowWin(BoardImagesArray)+"", 752, 610);
			}
		}
		g.setColor(Color.black);
		g.drawString(" Options", 775, 610);
		
		for(int i=0;i<BoardButtonsArray.length;i++)				//DRAW THE BUTTONS
		{
			BoardButtonsArray[i].render(g);
		}
		
		for(int i=0;i<BoardImagesArray.length;i++)					//DRAW THW BOARD
		{
			for(int j=0;j<BoardImagesArray[i].length;j++)
			{
				BoardImagesArray[i][j].render(g);
			}
		}
		
		bturn.render(g);							//DRAW THE TRUN BUTTON WITH THE MOVING ARC
		if(bturn.getPath().equals("yellowE"))
		{
			g.setColor(Color.yellow);
			g.fillArc(573, 597, 18, 18, arc_rotation, 60); 
		}
		if(bturn.getPath().equals("redE"))
		{
			g.setColor(Color.red);
			g.fillArc(573, 597, 18, 18, arc_rotation, 60); 
		}
	
	}
	
}
