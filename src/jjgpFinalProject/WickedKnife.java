package jjgpFinalProject;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WickedKnife extends weapon{
	private Image image;
	public WickedKnife() {
		super(2, 1, 1);
		setName("WickedKnife");
		try {
			image = ImageIO.read(getClass().getClassLoader().getResource("WickedKnife.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
		image = image.getScaledInstance(minion.RES_CARD_WIDTH, minion.RES_CARD_LENGTH, Image.SCALE_DEFAULT);
		setCardImg(image);
	}
	@Override
	public void battleCry(board b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void play(board b, targetable t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deathrattle(board b) {
		
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
