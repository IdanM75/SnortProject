
import java.awt.Point; 

public class Arrays 
{	
	
	public void create2DArrayImages(Button[][] a,int sizeRow, int sizeColumn)
	{
		
		for(int i=0;i<sizeRow;i++)
		{
			for(int j=0;j<sizeColumn;j++)
			{
				a[i][j]=  new Button("emptyE", new Point(320+(52*j),20 + (52*i)), 51, 51);
			}
		}
	}
	
	public void createArrayButtons(Button[] a,int sizeRow)
	{
				a[0]= new Button("undo", new Point(48, 68+(86*0)), 204, 64);    
				a[1]= new Button("saveGame", new Point(48, 68+(86*1)), 204, 64);
				a[2]= new Button("loadGame", new Point(48, 68+(86*2)), 204, 64);
				a[3]= new Button("newGame", new Point(48, 68+(86*3)), 204, 64);
				a[4]= new Button("exit", new Point(48, 580), 204, 64);	
	}
}
