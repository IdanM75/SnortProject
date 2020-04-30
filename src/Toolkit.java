import java.awt.image.BufferedImage; 
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Toolkit {
	
	public static final String res_path = "res//";
	
	public static BufferedImage loadImage(String type,String path)
	{
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File(res_path + path + "." + type));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
	}
	
}
