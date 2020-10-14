package jjgpFinalProject;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Gwanway extends minion{
	private Image testImage;
	public Gwanway() {
		super(0,1,0);
		setName("gwanway");
		try {
			testImage = ImageIO.read(getClass().getClassLoader().getResource("gwanway.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
		testImage = testImage.getScaledInstance(minion.RES_CARD_WIDTH, minion.RES_CARD_LENGTH, Image.SCALE_DEFAULT);
		setCardImg(testImage);
	}
	
	public void battleCry(board b) {
		//b.getHero(0).dmg(1);
	}

	@Override
	public void deathrattle(board b) {
		if(!isSilenced()) {
				b.getHero(hero).addMinion(new BotBurger());
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
