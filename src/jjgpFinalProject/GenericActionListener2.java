package jjgpFinalProject;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GenericActionListener2 implements MouseListener {
	private String file;
	private int x;
	private Image zoom;
	private static ImageIcon zoomIC;
	private int ind;
	static int rand=0;
	int clickNum=0;
	ArrayList<Integer> check;

	public GenericActionListener2(int ind/*String f, int x, int ind*/) throws IOException {
		this.ind=ind;
		/*
		file=f;
		this.x=x;
		zoom = ImageIO.read(new File(file));
		zoom.getScaledInstance(150, 210, Image.SCALE_DEFAULT);
		zoomIC = new ImageIcon(zoom);
		this.ind=ind;
		 */

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clickNum++;
		check = new ArrayList();
		check.add(0);
		check.add(1);
		check.add(2);
		check.add(GenericActionListener.rand);
		check.add(GenericActionListener3.rand);
		int sub=0;
		
		if(clickNum==1) {
			Random gen = new Random();
			rand=gen.nextInt(game.h1.getDeck().getDeck().size());
			
			if(rand>check.get(3)&&check.get(3)!=0)
				sub++;
			if(rand>check.get(4)&&check.get(4)!=0)
				sub++;

			while(check.contains(rand)) {
				System.out.println(rand);
				rand=gen.nextInt(game.h1.getDeck().getDeck().size()-sub);
			}
			
			game.replaceCard(rand, ind);
		}

		System.out.println("BBBBBBBBBBBBBBb");
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public static MouseListener create(Class<MouseListener> class1, String string, game game, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	//	public static MouseListener create(Class<MouseListener> class1, String string, game game, String string2) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}