package jjgpFinalProject;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class LifeTap extends HeroPower{
	private Image image;
	public LifeTap(int hero) {
		super(2, hero);
		setNeedsTarget(false);
		setName("LifeTap");
		try {
			image = ImageIO.read(getClass().getClassLoader().getResource("lifetap.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
		setImage(image);
		//image = image.getScaledInstance((int)(150 * 0.75),(int)(210 * 0.75),Image.SCALE_DEFAULT);
	}

	@Override
	public void play(board b) {
		if(getUses()<1) {
			b.getHero(getHero()).drawCard();
			b.getHero(getHero()).dmg(2);
			incUses();
			b.getHero(getHero()).setMana(b.getHero(getHero()).getMana()-getCost());
		}
		
	}

	@Override
	public void target(targetable t, board b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void target(minion m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enlarge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetImg() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHero(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCardImg(Image img) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void play(board b, targetable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play(board b, ArrayList<targetable> t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play(board b, minion m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String file() {
		// TODO Auto-generated method stub
		return null;
	}

}
