package jjgpFinalProject;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ZombieChow extends minion{
	private Image testImage;
	public ZombieChow() {
		super(2,3,1);
		setName("zombieChow");
		try {
			testImage = ImageIO.read(getClass().getClassLoader().getResource("zombiechow.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
		testImage = testImage.getScaledInstance(minion.RES_CARD_WIDTH, minion.RES_CARD_LENGTH, Image.SCALE_DEFAULT);
		setCardImg(testImage);
	}
	
	public void battleCry(board b) {
		
	}

	@Override
	public void deathrattle(board b) {
		if (super.isSilenced() == false) {
			if(getHero() == 0)
				b.getHero(1).heal(5);
			else if(getHero() == 1)
				b.getHero(0).heal(5);
		}
		
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