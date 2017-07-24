package com.sist.temp;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class FileMain {

	public static void main(String[] args) {
		Image image = null;
        try {
            URL url = new URL("http://cmsimg.mnet.com/clipimage/album/50/002/046/2046431.jpg");
            BufferedImage img = ImageIO.read(url);
            File file=new File("C:\\upload2\\1.jpg");
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
         e.printStackTrace();
        }
        
	}

}
