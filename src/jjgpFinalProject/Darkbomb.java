package jjgpFinalProject;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Darkbomb extends spell {

	private Image testImage;
	public Darkbomb() {
		super(2);
		setName("DarkBomb");
		try {
			testImage = ImageIO.read(getClass().getClassLoader().getResource("darkbomb.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
		testImage = testImage.getScaledInstance(minion.RES_CARD_WIDTH, minion.RES_CARD_LENGTH, Image.SCALE_DEFAULT);
		setCardImg(testImage);
		setNeedsTarget(true);
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
	public void play(board b) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void target(targetable t, board b) {
		t.dmg(3, b);
	}


	@Override
	public void target(minion m) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void target(targetable t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String file() {
		// TODO Auto-generated method stub
		return null;
	}

}
