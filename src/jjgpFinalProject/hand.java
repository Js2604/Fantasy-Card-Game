package jjgpFinalProject;

import java.awt.Graphics;
import java.util.ArrayList;

public class hand {
	private ArrayList<card> cards = new ArrayList<card>();
	public hand() {
		
		// TODO Auto-generated constructor stub
	}
	public void draw(Graphics g) {
		for (card c: cards) {
			c.draw(g, c.getX(), c.getY());
		}
		for (card c: cards) {
			if(c.get().cardHovered())
				c.draw(g, c.getX(), c.getY());
		}
	}
	public ArrayList<card> getHand() {
		return cards;
	}
	public card remove(int x) {
		card c = cards.remove(x);
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
		return c;
	}
	public boolean remove(card c) {
		Boolean b = cards.remove(c);
		
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
		return b;
	}

	public card get(int i) {
		return cards.get(i);
	}
	public void add(deck d) {
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
	}
	public void add(deck d, int k) {
		cards.add(d.getDeck().remove(k)); // error out of bounds the thin b e remvoing everythign b 
		if(d.getDeck().size()<7) {
			k-=(7-d.getDeck().size());
		}
		
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
	}
}
