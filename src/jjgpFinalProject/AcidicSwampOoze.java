package jjgpFinalProject;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class AcidicSwampOoze extends minion{
	private Image testImage;
	public AcidicSwampOoze() {
		super(3,2, 2);
		setName("AcidicSwampOoze");
		try {
			testImage = ImageIO.read(getClass().getClassLoader().getResource("AcidicSwampOoze.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
		testImage = testImage.getScaledInstance(minion.RES_CARD_WIDTH, minion.RES_CARD_LENGTH, Image.SCALE_DEFAULT);
		setCardImg(testImage);
	}
	
	public void battleCry(board b) {
		if(getHero() == 1) {
			b.getHero(0).giveWeapon(null, b);
		}
		else if(getHero() == 0) {
			b.getHero(1).giveWeapon(null, b);
		}
	}

	@Override
	public void deathrattle(board b) {
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
	public void target(targetable t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void target(minion m) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void target(targetable t, board b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String file() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}