package jjgpFinalProject;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class board{
	final static int BOARD_WIDTH=1920;
	final static int BOARD_LENGTH=1080;
	private hero h1;
	private hero h2;
	private Image testImage;
	public board(hero h1, hero h2) {
		this.h1 = h1;
		this.h2 = h2;		
		
		
		try {
			testImage = ImageIO.read(getClass().getClassLoader().getResource("board.png"));
		} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
		}
		testImage = testImage.getScaledInstance(BOARD_WIDTH, BOARD_LENGTH, Image.SCALE_DEFAULT);
	}
	public void draw(Graphics g) {
		
		g.drawImage(testImage,0,0,null);
		h1.draw(g);
		h2.draw(g);
	}
	public hero getHero(int i) {
		if (i == 1) {
			return h1;
		}
		else {
			return h2;
		}
	}
	
	public boolean contains(int x, int y) {
		if(x > 300 && x < 1500 && y > 300 && y < 800) {
			return true;
		}
		return false;
	}

}
