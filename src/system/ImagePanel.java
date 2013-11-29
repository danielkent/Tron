package system;
/**
 * @author Simon Lei
 * @version  2013.11.25
 * A JPanel which allows easy implementation of a background image.
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage background;

    /**
     * Class constructor.
     * @param picture the filename of the picture to set as background
     */
    public ImagePanel(String picture) {
       try {                
          background = ImageIO.read(new File(picture));
       } catch (IOException ex) {
			System.out.println("Error picture not found");
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);   
    }

}
