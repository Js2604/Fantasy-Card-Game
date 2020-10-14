package jjgpFinalProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public abstract class weapon extends playable{

	private int dura;
	private int dmg;
	private int hero = 1;
	private Image image;
	private int cost;
	private String name;
	private Image cardImg, cardImgHold, cardImgLarge, weaponImg;
	final static int CARD_WIDTH = 150;
	final static int CARD_LENGTH = 210;
	final static int RES_CARD_WIDTH = (int)(CARD_WIDTH*1.3);
	final static int RES_CARD_LENGTH = (int)(CARD_LENGTH*1.3);
	final int CIRCLE_SIZE=(int)(45 * 0.75);
	
	public weapon(int dura, int dmg, int cost) {
		this.dura = dura;
		this.dmg = dmg;
		this.cost = cost;
	}
	
	public int getDura() {
		return dura;
	}
	public void die(board b) {
		b.getHero(hero).giveWeapon(null, b);
		System.out.println(hero);
	}
	public int getCost() {
		return cost;
	}
	public abstract void deathrattle(board b);

	public int getDmg() {
		return dmg;
	}
	public void setName(String s) {
		name = s;
	}
	public String toString() {
		return name;
	}
	public void reduceDura(int i, board b) {
		dura -= i;
		if (dura < 1)
			die(b);
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawImage(cardImg,x,y,null);
		
	}
	public void drawWeapon(Graphics g, int x, int y) {
		g.drawImage(weaponImg, x, y, null);
		g.drawImage(weaponImg, x, y, null);
		g.setColor(Color.BLACK);
		g.fillOval(x-(int)(2.5), y+CARD_LENGTH - (int)(40 * 2.15), CIRCLE_SIZE, CIRCLE_SIZE);
		g.setColor(Color.BLACK);
		g.fillOval(x+CARD_WIDTH-(int)(40 * 1.75), y+CARD_LENGTH-(int)(40 * 2.15), CIRCLE_SIZE, CIRCLE_SIZE);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		
		if (dmg<10) g.drawString(dmg+"", x + (int)(12 * 0.75), y+(int)(CARD_LENGTH * 0.75)-(int)(12 * 0.75));	
		else g.drawString(dmg+"", x-(int)(1*0.75), y+(int)(128*0.75));
		if (dura<10) g.drawString(""+dura, x+(int)(CARD_WIDTH*0.75)-(int)(22*0.9), y+(int)(CARD_LENGTH*0.75)-(int)(12*0.75));
		else g.drawString(""+dura, x+(int)(79*0.75), y+(int)(128*0.75));
	}

	@Override
	public void enlarge() {
		cardImg=cardImgLarge;
		
	}

	@Override
	public void resetImg() {
		cardImg=cardImgHold;
		
	}

	@Override
	public void setHero(int i) {
		hero = i;
		
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return cardImg;
	}

	@Override
	public void setCardImg(Image img) {
		cardImg=img.getScaledInstance(CARD_WIDTH,CARD_LENGTH,Image.SCALE_DEFAULT);
		cardImgLarge=img;
		cardImgHold=cardImg;
		//weaponImg=cardImgHold;
		weaponImg = img.getScaledInstance((int)(CARD_WIDTH * 0.75),(int)(CARD_LENGTH * 0.75),Image.SCALE_DEFAULT);
	}

	@Override
	public void play(board b) {
		//b.getHero(hero).getHand().remove()
		b.getHero(hero).giveWeapon(this, b);
		for(int i=0; i<7; i++) {
			if(b.getHero(hero).getMinions()[i] != null && b.getHero(hero).getMinions()[i].toString().equals(new BrannBronzebeard().toString()) && b.getHero(hero).getMinions()[i].isSilenced() == false)
				battleCry(b);
		}
		battleCry(b);
	}

	public abstract void battleCry(board b);

	@Override
	public void play(board b, ArrayList<targetable> t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play(board b, minion m) {
		// TODO Auto-generated method stub
		
	}

}