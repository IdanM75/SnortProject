import java.awt.event.ActionListener; 
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;


public class TestDraw 
{
public	static JFrame application;
	
	public static int height=700;
	public static int width=878;
	
	public static String whoStart;
	public static int sizeRow;
	public static int sizeColumn;
	public static int deepRed;
	public static int deepYellow;
	
	static int numRad;
	static Random rand = new Random();
	
	public static void main ( String args[] ) throws IOException
	{
		application = new JFrame();
		
		final openDialog dialog=new openDialog();
	    dialog.show();
		
		dialog.okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(dialog.rdbtnRed.isSelected())
				{
					whoStart="redE";
				}
				else
				{
					if(dialog.rdbtnYellow.isSelected())
					{
						whoStart="yellowE";
					}
					else
					{
						numRad = rand.nextInt(2) + 1;
						if(numRad==1)
						{
							whoStart="redE";
						}
						else
						{
							whoStart="yellowE";
						}
					}
				}
				
				if(dialog.redWizdom.getSelectedIndex()==0)
				{
					deepRed=-1;
				}
				if(dialog.redWizdom.getSelectedIndex()==1)
				{
					deepRed=1;
				}
				if(dialog.redWizdom.getSelectedIndex()==2)
				{
					deepRed=3;
				}

				if(dialog.yellowWizdom.getSelectedIndex()==0)
				{
					deepYellow=-1;
				}
				if(dialog.yellowWizdom.getSelectedIndex()==1)
				{
					deepYellow=1;
				}
				if(dialog.yellowWizdom.getSelectedIndex()==2)
				{
					deepYellow=3;
				}
				
				sizeColumn=dialog.columnSize.getSelectedIndex()+5;
					
				sizeRow=dialog.rowSize.getSelectedIndex()+5;
				
				
				
				DrawPanel panel = new DrawPanel();

				application.getContentPane().add(panel);
				application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				application.add ( panel );
				application.setSize( width , height );
				application.setVisible ( true ); 
				
				application.addMouseMotionListener(new MouseMotion());
				
				application.setBounds(70, 20, width-10, height-10);
				application.setResizable(false);
				
				dialog.dispose();

			}
		});	    
		
		dialog.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				
				dialog.dispose();

			}
		});	    
		
	}
	
	public static void addListener(MouseListener l)
	{
		application.addMouseListener(l);
	}
	
	public static void removeListener(MouseListener l)
	{
		application.removeMouseListener(l);
	}
	
	public static void addMotion(MouseMotionListener l)
	{
		application.addMouseMotionListener(l);
	}
}
