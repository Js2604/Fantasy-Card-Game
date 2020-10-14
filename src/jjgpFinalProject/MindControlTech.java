package jjgpFinalProject;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
//BUGGED DOESNT WORK
public class MindControlTech extends minion{
	private Image testImage;
	public MindControlTech() {
		super(3,3,3);
		setName("MCTech");
		try {
			testImage = ImageIO.read(getClass().getClassLoader().getResource("MCTech.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
		testImage = testImage.getScaledInstance(minion.RES_CARD_WIDTH, minion.RES_CARD_LENGTH, Image.SCALE_DEFAULT);
		setCardImg(testImage);
	}

	
	public void battleCry(board b) {
		int count = 0;
		for(int i = 0; i < 7; i++) {
			if(getHero() == 1) {
				if(b.getHero(0).getMinions()[i] != null)
					count++;
			}
			else if (getHero() == 0) {
				if(b.getHero(1).getMinions()[i] != null)
					count++;
			}
		}
		if (count > 3) {
			int count2 = 0;
			for(int i = 0; i < 7; i++) {
				if(b.getHero(getHero()).getMinions()[i] != null)
					count2++;
			}
			if (count2 < 7) {
				if(getHero() == 1) {
					b.getHero(getHero()).addMinion(b.getHero(0).getMinions()[(int)(Math.random() * count)]);
					b.getHero(0).removeMinion(b.getHero(0).getMinions()[(int)(Math.random() * count)]);
				}
				else if(getHero() == 0) 
					b.getHero(getHero()).addMinion(b.getHero(1).getMinions()[(int)(Math.random() * count)]);
					b.getHero(1).removeMinion(b.getHero(1).getMinions()[(int)(Math.random() * count)]);
			}
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