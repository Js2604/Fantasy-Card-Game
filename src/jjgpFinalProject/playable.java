package jjgpFinalProject;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class playable {
	private boolean targetsOnlyMinions=false;
	private boolean needsTarget=false;
	private boolean cardHovered=false;
	public abstract void draw(Graphics g, int x, int y);
	public abstract void play(board b);
	public abstract void target(targetable t, board b);
	public abstract void target(minion m);
	public abstract void enlarge();
	public abstract int getCost();
	
	public boolean targetsOnlyMinions() {
		return targetsOnlyMinions;
	}
	public void setTargetsOnlyMinions(boolean b) {
		targetsOnlyMinions=b;
	}
	public boolean needsTarget() {
		return needsTarget;
	}
	public void setNeedsTarget(boolean b) {
		needsTarget=b;
	}
	public abstract void resetImg();
	public abstract void setHero(int i);
	public abstract void setCardImg(Image img);
	public abstract Image getImage();
	public abstract String file();
	public abstract void play(board b, targetable t);
	public abstract void play(board b, ArrayList<targetable> t);
	public abstract void play(board b, minion m);
	public boolean cardHovered() {
		return cardHovered;
	}
	public void setCardHover(boolean b) {
		cardHovered=b;
	}
}
