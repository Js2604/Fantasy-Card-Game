package jjgpFinalProject;

public interface targetable {
	boolean isMinion();
	boolean canAttack();
	void setSilence(boolean b);
	int getatk();
	void dmg(int i);
	void sethp(int i);
	void setNumAtks(int i);
	int getX();
	int getY();
	void heal(int i);
	void setatk(int i);
	void dmg(int i, board b);
}
