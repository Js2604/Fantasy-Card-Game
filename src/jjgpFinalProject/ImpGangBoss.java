package jjgpFinalProject;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImpGangBoss extends minion{
	private Image testImage;
	public ImpGangBoss() {
		super(2,4, 3);
		setName("ImpGangBoss");
		try {
			testImage = ImageIO.read(getClass().getClassLoader().getResource("ImpGangBoss.png"));
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
	public void dmg(int i, board b) {
		System.out.println("BEYOP");
		super.dmg(i);
		if (super.isSilenced() == false && i>0) {
			int count  = 0;
			for (int k = 0; k < 7; k++) {
				if(b.getHero(getHero()).getMinions()[k] != null)
					count++;
			}
			if(count < 7) {
				b.getHero(getHero()).addMinion(new Imp());
			}
		}
		if (super.gethp() < 1)
			super.die(b);
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
