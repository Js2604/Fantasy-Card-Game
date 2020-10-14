package jjgpFinalProject;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BloodRazor extends weapon{
	private Image image;
	public BloodRazor() {
		super(2, 2, 4);
		setName("BloodRazor");
		try {
			image = ImageIO.read(getClass().getClassLoader().getResource("BloodRazor.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
		image = image.getScaledInstance(minion.RES_CARD_WIDTH, minion.RES_CARD_LENGTH, Image.SCALE_DEFAULT);
		setCardImg(image);
	}
	@Override
	public void battleCry(board b) {
		for(int i = 6; i > -1; i--) {
			if(b.getHero(1).getMinions()[i] != null) {
				b.getHero(1).getMinions()[i].dmg(1, b);
			}
			if(b.getHero(0).getMinions()[i] != null) {
				b.getHero(0).getMinions()[i].dmg(1, b);
			}
		}
		
	}
	@Override
	public void play(board b, targetable t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deathrattle(board b) {
		for(int i = 6; i > -1; i--) {
			if(b.getHero(1).getMinions()[i] != null) {
				b.getHero(1).getMinions()[i].dmg(1, b);
			}
			if(b.getHero(0).getMinions()[i] != null) {
				b.getHero(0).getMinions()[i].dmg(1, b);
			}
		}
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