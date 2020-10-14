package jjgpFinalProject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

public abstract class minion extends playable implements targetable{
	final static int CARD_WIDTH = 150;
	final static int CARD_LENGTH = 210;
	final static int RES_CARD_WIDTH = (int)(CARD_WIDTH*1.3);
	final static int RES_CARD_LENGTH = (int)(CARD_LENGTH*1.3);
	final int CIRCLE_SIZE=45;
	private Image cardImg, cardImgHold, cardImgLarge, minionImg;
	private boolean selected=false;
	private boolean cardHovered=false;
	private int maxhp;
	private int numTurns=0;
	private int hp;
	private int atk, numAtks;
	private int cost;
	private int[] loc=new int[2];
	private String name;
	int hero = 1;
	private boolean silenced = false;
	private int beyop;
	//BC bc;
	public minion(int atk, int hp, int cost) {
		this.cost=cost;
		this.hp = hp;
		this.atk = atk;
		maxhp = this.hp;

	}

	//////////////getters//////////////////////
	public int gethp() {
		return hp;
	}
	public int getatk() {
		return atk;
	}
	public int[] getLocOnBoard() { // gets location on board
		return loc;
	}
	public int getX() {
		return loc[0];
	}
	public int getY() {
		return loc[1];
	}
	@Override
	public boolean canAttack() {
		if(numTurns > 0 && numAtks==0&&atk>0)
			return true;
		return false;
	}
	public Image getImage() {
		return cardImg;
	}
	public int getCost() {
		return cost;
	}
	public boolean isMinion() {
		return true;
	}
	///////////////setters///////////////
	public void setCardImg(Image img) {
		cardImg=img.getScaledInstance(CARD_WIDTH,CARD_LENGTH,Image.SCALE_DEFAULT);
		cardImgLarge=img;
		cardImgHold=cardImg;
		minionImg=cardImgHold;
	}
	public void setName(String s) {
		name = s;
	}
	public void setatk(int i){
		atk=i;
	}
	public void setX(int x) {
		loc[0]=x;
	}
	public void setY(int y) {
		loc[1]=y;
	}
	public void sethp(int i) {
		hp = i;
		maxhp = i;
	}
	public void setNumTurns(int i) {
		numTurns=i;
	}
	@Override
	public void setNumAtks(int i) {
		numAtks=i;
	}
	@Override
	public void setHero(int i) {
		hero=i;
	}
	public void incTurns(board b) {
		if(toString().equals("justin"))
			die(b);
		numTurns++;
	}

	////////////////////////////////////////////////////////////

	public abstract void target(targetable t);
	public abstract void target(minion m);
	public void select(boolean b) {
		selected = b;
	}
	public void test(int b ) {
		beyop = b;
	}
	public void drawMinion(Graphics g) {
		if (selected) {
			g.setColor(Color.GREEN);
			g.fillRect(loc[0], loc[1], CARD_WIDTH, CARD_LENGTH);
		}
		g.drawImage(minionImg, loc[0], loc[1], null);
		g.setColor(new Color(191, 191, 0));
		g.fillOval(loc[0]-5, loc[1]+CARD_LENGTH-40, CIRCLE_SIZE, CIRCLE_SIZE);
		g.setColor(Color.RED);
		g.fillOval(loc[0]+CARD_WIDTH-40, loc[1]+CARD_LENGTH-40, CIRCLE_SIZE, CIRCLE_SIZE);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 18));

		//last 4 lines are for changing how hp and attack are drawn based on the number of digits
		if (atk<10) g.drawString(atk+"", loc[0]+12, loc[1]+CARD_LENGTH-12);	
		else g.drawString(atk+"", loc[0]-1, loc[1]+CARD_LENGTH-12);
		if(hp<maxhp) 
			g.setColor(new Color(255, 150, 0));
		if(hp>maxhp)
			g.setColor(new Color(20, 250, 20));
		if (hp<10) g.drawString(""+hp, loc[0]+CARD_WIDTH-22, loc[1]+CARD_LENGTH-12);
		else g.drawString(""+hp, loc[0]+CARD_WIDTH-28, loc[1]+CARD_LENGTH-12);
		if(isSilenced()) {
			g.setColor(Color.RED);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(8));
			g2.drawLine(loc[0]+40,loc[1]+CARD_LENGTH/2+40, loc[0]+CARD_WIDTH-40, loc[1]+CARD_LENGTH-40);
			g2.drawLine(loc[0]+40,loc[1]+CARD_LENGTH-40, loc[0]+CARD_WIDTH-40, loc[1]+CARD_LENGTH/2+40);
		}

	}
	public void draw(Graphics g, int x, int y) {
		g.drawImage(cardImg,x,y,null);
	}

	public void enlarge() {
		cardImg=cardImgLarge;
	}
	public void resetImg() {
		cardImg=cardImgHold;
	}
	public void dmg(int i) {
		hp -= i;
	}

	public void dmg(int i, board b) {
		hp -= i;
		//System.out.println("dmg");
		if (hp < 1)
			die(b);
	}

	public void die(board b) {
		deathrattle(b);
		b.getHero(hero).removeMinion(this);
		System.out.println("died");

	}
	public void heal(int i) {
		hp += i;
		if (hp > maxhp) {
			hp = maxhp;
		}
	}
	public void attack(targetable t, board b) {
		if(canAttack()) {
			t.dmg(getatk(), b);

			dmg(t.getatk(), b);
			numAtks++;
		}
	}

	public void play(board b) {
		//b.getHero(hero).getHand().remove()
		b.getHero(hero).addMinion(this);
		if(isSilenced() == false) {
			for(int i=0; i<7; i++) {
				if(b.getHero(hero).getMinions()[i] != null && b.getHero(hero).getMinions()[i].toString().equals(new BrannBronzebeard().toString()))
					battleCry(b);
			}
			battleCry(b);
		}
	}

	public abstract void battleCry(board b, targetable t);
	public abstract void battleCry(board b, ArrayList<targetable> t);
	public abstract void battleCry(board b, minion m);
	public void play(board b, targetable t) {
		b.getHero(hero).addMinion(this);
		for(int i=0; i<7; i++) {
			if(b.getHero(hero).getMinions()[i] != null && b.getHero(hero).getMinions()[i].toString().equals(new BrannBronzebeard().toString()) && b.getHero(hero).getMinions()[i].isSilenced() == false)
				battleCry(b);
		}
		battleCry(b, t);
	}

	public void play(board b, minion m) {
		b.getHero(hero).addMinion(this);
		if(isSilenced() == false) {
			for(int i=0; i<7; i++) {
				if(b.getHero(hero).getMinions()[i] != null && b.getHero(hero).getMinions()[i].toString().equals(new BrannBronzebeard().toString()))
					battleCry(b, m);
			}
			battleCry(b, m);
		}
	}
	public void play(board b, ArrayList<targetable> t) {
		b.getHero(hero).addMinion(this);
		if(isSilenced() == false) {
			for(int i=0; i<7; i++) {
				if(b.getHero(hero).getMinions()[i] != null && b.getHero(hero).getMinions()[i].toString().equals(new BrannBronzebeard().toString()))
					battleCry(b, t);
			}
			battleCry(b, t);
		}
	}

	public boolean contains(int x, int y) {
		if (x > loc[0]-5 && x < loc[0]+CARD_WIDTH-22 && y > loc[1] && y < loc[1]+CARD_LENGTH-12) 
			return true;
		return false;
	}
	public String toString() {
		return name;
	}
	/*public enum BC{targetdmg, draw, heal};
	public void battleCry(BC t) {
		bc=t;
		switch(t){
		case targetdmg:

			break;
		case draw:

			break;
		}	
	}*/
	public abstract void battleCry(board b);
	public abstract void deathrattle(board b);
	public void setSilence(boolean b) {
		silenced = b;
	}
	public boolean isSilenced() {
		return silenced;
	}
	public int getHero() {
		return hero;
	}

	//public abstract void battleCry(ArrayList<targetable> list);
	//public abstract void battleCry(targetable t);
	/*public void battleCrytargetdmg(targetable t, int i) {
		t.dmg(i);
	}

	public void battleCrytargetheal(targetable t, int i) {
		t.heal(i);
	}*/

}
