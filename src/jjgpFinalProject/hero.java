package jjgpFinalProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class hero implements targetable{

	private int numAtks;
	private int hp;
	private weapon curWeapon;
	private int heroNum;
	private int totalMana, mana;
	final static int MANA_WIDTH=32;
	final static int MANA_Y=978;
	private Image image;
	private int atk=0;
	private boolean selected;
	private hand h = new hand();
	private deck d;
	private minion[] minions =new minion[7];
	private int minionY;
	private int maxhp = 30;
	private Image manaCrystal;
	private Image manaCrystalEmpty;
	private HeroPower heropower;
	public hero(int whichOne, deck d, HeroPower heropower) {
		this.heropower = heropower;
		this.d = d;
		selected = false;
		hp = maxhp;
	    //curWeapon = new weapon(6, 5);
		heroNum = whichOne;
		try {
			manaCrystal = ImageIO.read(getClass().getClassLoader().getResource("manacrystal.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
		manaCrystal=manaCrystal.getScaledInstance(hero.MANA_WIDTH, hero.MANA_WIDTH, Image.SCALE_DEFAULT);
		
		try {
			manaCrystalEmpty = ImageIO.read(getClass().getClassLoader().getResource("manacrystalempty.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
		manaCrystalEmpty=manaCrystalEmpty.getScaledInstance(hero.MANA_WIDTH, hero.MANA_WIDTH, Image.SCALE_DEFAULT);
		if (heroNum == 0) {
			totalMana = 0;
			minionY=300;
			try {
				image = ImageIO.read(getClass().getClassLoader().getResource("h1.gif"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			for(int i=0; i<d.getDeck().size(); i++) {
				d.getDeck().get(i).get().setHero(0);
			}
			curWeapon = new BloodRazor();
			curWeapon.setHero(0);
			addMinion(new ZombieChow());
			addMinion(new ImpGangBoss());
			addMinion(new AcidicSwampOoze());
			addMinion(new IronbeakOwl());
			
		}
		else {
			totalMana = 1;
			minionY=510;
			try {
				image = ImageIO.read(getClass().getClassLoader().getResource("h2.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		heropower.setX(1920/2+104);
		heropower.setY(620 * heroNum + 125);
		mana = totalMana;
		image = image.getScaledInstance(125, 150, Image.SCALE_DEFAULT);
		// TODO Auto-generated constructor stub
	}
	/*public void addMinion(int x) {
		minion m = (minion) h.remove(x).get();
		minions.add(m);
		
	}*/
	/*public void add(deck d) {
		cards.add(d.getDeck().remove(0)); // error out of bounds the thin b e remvoing everythign b 
		int space = 0;
		if(cards.size()>3) {
			space = 3 * 75;
		}
		else {
			space = cards.size() * 75;
		}
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).setX((board.BOARD_WIDTH/2 - (space/2)) + (space/cards.size()*i));
		}
		if(cards.size()>3) {
			for(int i=0;i<cards.size();i++) {
				cards.get(i).setX((cards.get(i)).getX()-30);
			}
		}
	}*/
	public int minionsSize() {
		int count = 0;
		for (int i = 0; i < 7; i++) {
			if (minions[i] != null)
				count++;
		}
		return count;
	}
	public boolean addMinion(minion m) {
		for (int j = 0; j < 7; j++) {
			if (minions[j] == null) {
				m.setHero(heroNum);
				m.setY(minionY);
				
				//m.setX(200*j+500);
				minions[j] = m;
				int space = minionsSize()*150;
				int count = 0;
				for (int i = 0; i < 7; i++) {
					if (minions[i] != null) {
						
						minions[i].setX((1920/2 - (space/2)) + (space/minionsSize()*count));
						count++;
					}
				}
				
				return true;
			}
		}
		return false;
	}
	public boolean removeMinion(minion m) {
        for(int i = 0; i < 7; i++) {
            if (minions[i] != null && minions[i].equals(m)) {
                minions[i] = null;
                int space = minionsSize()*150;
                int count = 0;
                for (int b = 0; b < 7; b++) {
                    if (minions[b] != null) {
                        minions[b].setX((1920/2 - (space/2)) + (space/minionsSize()*count));
                        count++;
                    }
                }
                return true;
            }
        }
        return false;
	}
	/*public boolean removeMinion(minion m) {
		for(int i = 0; i < 7; i++) {
			if (minions[i] != null && minions[i].equals(m)) {
				minions[i] = null;
				System.out.println("removed");
				int space = minionsSize()*150;
				int count = 0;
				for (int b = 0; b < 7; b++) {
					if (minions[b] != null) {
						
						minions[b].setX((1920/2 - (space/2)) + (space/minionsSize()*count));
						count++;
					}
				}
				return true;
			}
		}
		return false;
	}*/
	public int getTotalMana() {
		return totalMana;
	}
	
	public int getMana() {
		return mana;
	}
	public hand getHand() {
		return h;
	}
	
	public minion[] getMinions() {
		return minions;
	}
	public void drawCard() {
		h.add(d);
	}
	public void drawSpec(int i) {
		h.add(d,i);
		
	}
	public void giveWeapon(weapon w, board b) {
		if (curWeapon != null)
			curWeapon.deathrattle(b);
		curWeapon = w;
	}

	public int gethp() {
		return hp;          
	}
	
	public boolean isMinion() {
		return false;
	}

	public weapon getWeapon() {
		return curWeapon;
	}

	public void dmg(int i) {
		hp -= i;
	}
	
	public void select(boolean b) {
		selected = b;
	}

	public void attack(minion m, board b) {
		if (canAttack()) {
			if (curWeapon != null) {
				dmg(m.getatk());
				m.dmg(curWeapon.getDmg(), b);
				curWeapon.reduceDura(1, b);
			}
			numAtks++;
			System.out.println(totalMana);
		}
	}

	public void attack(hero h, board b) {
		if (canAttack()) {
			if (curWeapon != null) {
				h.dmg(curWeapon.getDmg());
				curWeapon.reduceDura(1, b);
			}
			numAtks++;
		}
	}

	public boolean contains(int x, int y) { // helps with clicking/hovering; since hero has definite location this is hard-coded
		if (x > 891 && x < 1016 && y > 620*heroNum+124 && y < 620*heroNum+276) return true;
		return false;
	}

	public void draw(Graphics g) {
		
		if (hp > 0) {
			if (selected) {
				g.setColor(Color.GREEN);
				g.fillRect(892, 620*heroNum+125, 125, 150);
			}
			for (int j = 0; j < 7; j++) {
				if (minions[j] != null) {
					minions[j].drawMinion(g);
				}
			}
			if (curWeapon != null)
				curWeapon.drawWeapon(g, 1920/2 - 250, 620 * heroNum + 125);
			if(heroNum==1) {
				int i=0;
				while(i<mana) {
					g.drawImage(manaCrystal, 1300+i*(MANA_WIDTH), MANA_Y, null);
					i++;
				}
				while(i<totalMana) {
					g.drawImage(manaCrystalEmpty, 1300+i*(MANA_WIDTH), MANA_Y, null);
					i++;
				}
				g.setColor(new Color(63,97,178));
				g.fillOval(1260-50, MANA_Y, MANA_WIDTH, MANA_WIDTH);
				g.fillRect(1260-40, MANA_Y+2, 50, 30);
				g.fillOval(1260, MANA_Y, MANA_WIDTH, MANA_WIDTH);
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", Font.BOLD, 25));
		
				g.drawString(""+mana+"/"+totalMana, 1215, MANA_Y+27);
			}
			heropower.draw(g, heropower.getX(), heropower.getY());
			g.setFont(new Font("TimesRoman", Font.PLAIN, 36));
			g.drawImage(image, (1920/2)-68, 620*heroNum+125, null);
			g.setColor(Color.YELLOW);
			if (curWeapon != null)
				g.fillOval(697+140, 240+620*heroNum, 80, 80);
			g.setColor(Color.RED);
			g.fillOval(807+185, 240+620*heroNum, 80, 80);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD,36));
			if (curWeapon != null)
				g.drawString(""+curWeapon.getDmg(), 697+155, 240+620*heroNum+50);
			g.drawString(hp+"", 807+215, 240+620*heroNum+50);
			h.draw(g);
		}
	}
	

	@Override
	public void sethp(int i) {
		hp = i;
		
	}
	
	@Override
	public void setNumAtks(int i) {
		numAtks=i;
	}

	@Override
	public void heal(int i) {
		hp += i;
		if (hp > maxhp) {
			hp = maxhp;
		}
	}

	@Override
	public void setatk(int i) {
		atk=i;
	}
	public void setMana(int i) {
		mana=i;
	}
	public HeroPower getHeroPower() {
		return heropower;
	}
	public void setTotalMana(int i) {
		if(i<=10)
			totalMana=i;
	}
	
	public void incTotalMana() {
		if(totalMana<10)
			totalMana++;
	}
	public void printList() {
		String s="";
		for(int i=0; i<7; i++)
			s+=minions[i]+", ";
		s=s.substring(0,s.length()-2);
		System.out.println(s);
	}
	@Override
	public int getatk() {
		return atk;
	}
	@Override
	public int getX() {
		return 881;
	}
	@Override
	public int getY() {
		return 100+heroNum*610;
	}
	@Override
	public boolean canAttack() {
		if(curWeapon != null && numAtks==0)
			return true;
		return false;
	}
	@Override
	public void setSilence(boolean b) {
		// TODO Auto-generated method stub
		
	}
	public deck getDeck() {
		return d;
	}
	
	@Override
	public void dmg(int i, board b) {
		hp -= i;
		
	}
	

}