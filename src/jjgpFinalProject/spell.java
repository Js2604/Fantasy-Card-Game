package jjgpFinalProject;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public abstract class spell extends playable{
	final static int CARD_WIDTH = 150;
	final static int CARD_LENGTH = 210;
	final static int RES_CARD_WIDTH = (int)(CARD_WIDTH*1.3);
	final static int RES_CARD_LENGTH = (int)(CARD_LENGTH*1.3);
	final int CIRCLE_SIZE=45;
	private Image cardImg, cardImgHold, cardImgLarge;
	private boolean cardHovered=false;
	private int cost;
	private String name;
	int hero = 1;
	
	public spell(int cost) {
		this.cost=cost;
	}

	////////////////////////////////
	
	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawImage(cardImg,x,y,null);
	}

	
	/*public abstract void play(board b);
	public abstract void play(targetable t);
	public abstract void play(ArrayList<targetable> t);
	public abstract void play(minion m);*/

	public void enlarge() {
		cardImg=cardImgLarge;
	}
	public void resetImg() {
		cardImg=cardImgHold;
	}
	
	public abstract void target(targetable t);

	////////setters//////////////////
	@Override
	public void setHero(int i) {
		hero=i;
	}
	public void setName(String string) {
		name=string;
	}

	@Override
	public void setCardImg(Image img) {
		cardImg=img.getScaledInstance(CARD_WIDTH,CARD_LENGTH,Image.SCALE_DEFAULT);
		cardImgLarge=img;
		cardImgHold=cardImg;
	}

	////////getters//////////////////
	@Override
	public Image getImage() {
		return cardImg;
	}
	@Override
	public int getCost() {
		return cost;
	}
	public String toString() {
		return name;
	}

	public abstract void target(targetable t, board b);
	
	///////////////////
}
