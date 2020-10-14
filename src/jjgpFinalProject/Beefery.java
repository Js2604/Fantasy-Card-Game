package jjgpFinalProject;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Beefery extends minion{
	private String file = "src/Beefery.png";
	private Image testImage;
	public Beefery() {
		super(99, 6, 1);
		setName("Beefery");
		try {
			testImage= ImageIO.read(getClass().getClassLoader().getResource("Beefery.png"));
			//testImage = ImageIO.read(getClass().getClassLoader().getResource("Beefery.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
		testImage = testImage.getScaledInstance(minion.RES_CARD_WIDTH, minion.RES_CARD_LENGTH, Image.SCALE_DEFAULT);
		setCardImg(testImage);
	}
	
	@Override
	public void target(targetable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void target(minion m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void battleCry(board b, targetable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void battleCry(board b, ArrayList<targetable> t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void battleCry(board b, minion m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void battleCry(board b) {
		// TODO Auto-generated method stub
		b.getHero(0).dmg(30);
	}

	@Override
	public void deathrattle(board b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void target(targetable t, board b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String file() {
		// TODO Auto-generated method stub
		return file;
	}

}
