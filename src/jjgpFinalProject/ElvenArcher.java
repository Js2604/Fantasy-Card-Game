package jjgpFinalProject;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ElvenArcher extends minion{
	private Image testImage;
	public ElvenArcher() {
		super(1,1,1);
		setName("ElvenArcher");
		try {
			testImage = ImageIO.read(getClass().getClassLoader().getResource("ElvenArcher.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
		testImage = testImage.getScaledInstance(minion.RES_CARD_WIDTH, minion.RES_CARD_LENGTH, Image.SCALE_DEFAULT);
		setCardImg(testImage);
		setTargetsOnlyMinions(true);
		setNeedsTarget(true);
	}

	public void battleCry(board b) {
		
	}

	@Override
	public void deathrattle(board b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void battleCry(board b, targetable t) {

		
	}
	@Override
	public void battleCry(board b, ArrayList<targetable> t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void battleCry(board b, minion m) {
		
	}

	@Override
	public void target(targetable t) {	
		
	}

	@Override
	public void target(minion m) {

	}

	@Override
	public void target(targetable t, board b) {
		// TODO Auto-generated method stub
		t.dmg(1, b);
	}

	@Override
	public String file() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}