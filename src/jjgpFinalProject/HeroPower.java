package jjgpFinalProject;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class HeroPower extends playable{
	private int x;
	private int y;
	private int cost;
	private int numUses;
	private int hero;
	private String name;
	private Image flipped, image;
	public HeroPower(int cost, int hero) {
		this.cost = cost;
		this.hero = hero;
		try {
			flipped = ImageIO.read(getClass().getClassLoader().getResource("heropowerflipped.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
	}
	public int getCost() {
		return cost;
	}
	public void setImage(Image i) {
		image=i;
	}
	public void draw(Graphics g, int x, int y) {
		if(numUses>0)
			g.drawImage(flipped, x, y, null);
		else {
			g.drawImage(image, x, y-6, null);
		}
		
	}
	public void incUses() {
		numUses++;
	}
	public void resetUses() {
		numUses = 0;
	}
	public int getUses() {
		return numUses;
	}

	public void setName(String s) {
		name=s;
	}
	public String toString() {
		return name;
	}
	public int getHero() {
		return hero;
	}
	public void setX(int i) {
		x = i;
	}
	public void setY(int i) {
		y = i;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean contains(int x, int y) {
		if (x > getX() && x < getX()+146 && y > getY() && y < getY()+150)
			return true;
		return false;
	}
}
