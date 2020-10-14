package jjgpFinalProject;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class card {
	private int manaCost;
	private minion m;
	private spell s;
	private weapon w;
	private int x;
	private playable p;
	final static int ogY=900;
	private int y;

	public card(playable p){
		this.p = p;
		y=ogY;
	}

	public void draw(Graphics g, int x, int y) {
		if(p.cardHovered())
			p.draw(g, x-35, y-130);
		else
			p.draw(g, x, y);
	}

	public playable get() {
		return p;
	}
	
	public boolean contains(int x1, int y1) {
		if(x1>x&&x1<x+minion.CARD_WIDTH&&
			y1>y&&y1<y+minion.CARD_LENGTH)
			return true;
		return false;
	}
	
	public void setY(int i) {
		y = i;
	}
	public void setX(int i) {
		x = i;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

}
