package jjgpFinalProject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class game{

	Image testImage;
//to do: dragging out of hand, hero power, mana limits

	public enum Class{Mage, Paladin, Shaman, Warlock, Warrior, Druid, Rogue, Hunter, Priest};
	private targetable sel;
	private card selC;
	private int selCard = -1;
	static JFrame frame;
	static JPanel panel;
	hand h = new hand();
	static hero h1 = new hero(1, new deck(), new LifeTap(1));
	static hero h2 = new hero(0, new deck(), new LifeTap(0));
	Timer rope;
	private int currX, currY;
	private boolean drawArrowPls=false;
	private int arrX, arrY;
	final int TURN_LENGTH=21;
	final int ROPE_TIME=20;
	int CONV_TURN_LENGTH=TURN_LENGTH*4;
	int CONV_ROPE_TIME=ROPE_TIME*4;
	static int numRem=0;
	private int time = CONV_TURN_LENGTH;
	ArrayList<Integer> index = new ArrayList();
	static ArrayList<JButton> buttons = new ArrayList();
	private boolean selected, gameOver, h1Turn;
	BufferedImage ropeImg;
	static board b = new board(h1, h2);
	Graphics g;
	card c;
	public static Image endTurnButton, manaCrystal, manaCrystalEmpty;
	boolean mousePressed = false;
	public static void main(String[] args) throws IOException{
		new game().start();
	}
	public void playCard(int i, int hero) {
			b.getHero(hero).setMana(b.getHero(hero).getMana()-b.getHero(hero).getHand().getHand().get(i).get().getCost());
			b.getHero(hero).getHand().remove(i).get().play(b);
	}
	
	public void dmgMinion(int hero, int index, int dmg) {
		b.getHero(hero).getMinions()[index].dmg(dmg, b);
	}
	public void dmgHero(int hero, int dmg) {
		b.getHero(hero).dmg(dmg);
	}
	public void minionDie(int hero, int index) {
		b.getHero(hero).getMinions()[index].die(b);
	}
	//******************************************************
	private static Image getScaledImage(Image srcImg, int w, int h){
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
	
	
	public static void replaceCard(int rand, int ind) {
		System.out.println(rand);

		if(ind==0)
			panel.remove(0);
		else
			panel.remove(Math.max(0, ind-numRem));
		numRem++;
		b.getHero(1).drawSpec(rand);
		frame.repaint();
	}

	//******************************************************
	
	
	
	private void start() throws IOException {
		//link for the deck we're attempting to make; you can also get pictures from here:
		//https://www.hearthpwn.com/decks/388371-s21-asia-1-100-win-rate-21win-0lose
		ActionListener t = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!gameOver) {
					time--;
					if(time<CONV_ROPE_TIME) {
						frame.repaint();
					}
					if(time==0) {
						if(!h1Turn) {
		                    for(int i = 0; i < 7; i++) {
		                        if (h1.getMinions()[i] != null)
		                            h1.getMinions()[i].incTurns(b);
		                    }
		                }
		                if(h1Turn) {
		                    for(int i = 0; i < 7; i++) {
		                        if (h2.getMinions()[i] != null)
		                            h2.getMinions()[i].incTurns(b);
		                    }
		                }
						h1.getHeroPower().resetUses();
						h2.getHeroPower().resetUses();
						switchTurn();
					}
				}
			}
		};
		rope=new Timer(250, t);
		openImages();
		
		sel=null;
		selected = false;
		gameOver = false;
		h1Turn=true;
		
		frame = new JFrame("Hearthstone");
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				draw(g);
				if(time<CONV_ROPE_TIME) {
					drawRope(g, time);
				}
				if(selected) {
					drawArrow(g, currX, currY);
				}
				if(drawArrowPls) {
					drawArrow(g, arrX, arrY, currX, currY);
				}
				if(selC!=null) {
					selC.draw(g,currX-minion.CARD_WIDTH/2, currY-minion.CARD_LENGTH/2);
				}
			}
		};
		//********************************************
		buttons = new ArrayList();
		index = new ArrayList();

		for(int i=0;i<3;i++) {
			buttons.add(new JButton());
			index.add(i);
			//ImageIcon temp = new ImageIcon(getScaledImage(b.getHero(1).getHand().getHand().get(i).get().getImage(),400,500));
			ImageIcon temp = new ImageIcon(getScaledImage(b.getHero(1).getDeck().getDeck().get(i).get().getImage(),400,500));
			buttons.get(i).setIcon(temp);
			buttons.get(i).setBackground(Color.WHITE);
			buttons.get(i).setBorderPainted(false);
			buttons.get(i).setOpaque(false);
			buttons.get(i).setBackground(Color.white);
			buttons.get(i).setBorder(null);
			buttons.get(i).setBorderPainted(false);
			buttons.get(i).setBounds(80+640*i,200,400,500);
			if(i==0)
				buttons.get(i).addMouseListener(new GenericActionListener(i));
			if(i==1)
				buttons.get(i).addMouseListener(new GenericActionListener2(i));
			if(i==2)
				buttons.get(i).addMouseListener(new GenericActionListener3(i));
			panel.add(buttons.get(i));

			if(i==0) {
				panel.setLayout(null);
			}

		}

		JButton good = new JButton();
		good.setBounds(850, 800, 200, 100);
		good.setBackground(Color.GRAY);
		good.setBorderPainted(false);
		good.setForeground(Color.WHITE);
		good.setText("Continue");
		good.setFont(new Font("Arial", Font.BOLD, 30));

		panel.add(good);

		JLabel jlabel = new JLabel();
		jlabel.setFont(new Font("Arial",Font.BOLD,25));
		jlabel.setForeground(Color.WHITE);
		jlabel.setBounds(20,900,1920,100);
		jlabel.setText("Click cards to get a different random card up to once. "
				+ "You can choose not swap any cards. Click continue once you're ready");

		panel.add(jlabel);

		frame.repaint();
		
		//********************************************
		
		//********************************************
		good.addMouseListener(new MouseListener() {        
			@Override
			public void mouseReleased(MouseEvent arg0) {}           
			@Override
			public void mousePressed(MouseEvent arg0) {}            
			@Override
			public void mouseExited(MouseEvent arg0) {}           
			@Override
			public void mouseEntered(MouseEvent arg0) {

			}
			@Override
			public void mouseClicked(MouseEvent e) {

				if (GenericActionListener.rand==0) {
					panel.remove(0);
					b.getHero(1).drawCard();
				}


				if (GenericActionListener2.rand==0) {
					if(GenericActionListener.rand>0) {
						panel.remove(1);
						b.getHero(1).drawSpec(1);
					}
					else {
						panel.remove(0);
						b.getHero(1).drawSpec(0);
					}
				}
				if (GenericActionListener3.rand==0) {

					if(GenericActionListener2.rand>0&&GenericActionListener.rand>0) {
						panel.remove(0);
						b.getHero(1).drawSpec(2);
					}

					else if(GenericActionListener2.rand>0 || GenericActionListener.rand>0) {
						panel.remove(1);
						b.getHero(1).drawSpec(1);
					}
					else {
						panel.remove(0);
						b.getHero(1).drawSpec(0);
					}

				}
				System.out.println(numRem);
				if(GenericActionListener3.rand==0 && GenericActionListener2.rand==0 && GenericActionListener.rand==0) {
					panel.remove(0);
					panel.remove(0);

				}
				else {
					panel.remove(0);
					panel.remove(0);

				}
				frame.repaint();
				rope.start();
			} 
		});
		
		//********************************************
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setSize(1920, 1080);
		frame.setVisible(true);
		
		panel.setBackground(Color.BLACK);
		
		panel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent me) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent me) {
				currX=me.getX();
				currY=me.getY();
				minionHover(me);
				cardHover(me);
				frame.repaint();
			}
			
		});
		
		panel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent me) {
				mousePressed = true;
				if(containsEndTurn(me.getX(), me.getY())) {
					
					if(!h1Turn) {
	                    for(int i = 0; i < 7; i++) {
	                        if (h1.getMinions()[i] != null)
	                            h1.getMinions()[i].incTurns(b);
	                    }
	                }
	                if(h1Turn) {
	                    for(int i = 0; i < 7; i++) {
	                        if (h2.getMinions()[i] != null)
	                            h2.getMinions()[i].incTurns(b);
	                    }
	                }
					h1.getHeroPower().resetUses();
					h2.getHeroPower().resetUses();
					switchTurn();
				}
				
				//System.out.println(me.getX() +", "+me.getY());
				if (!gameOver) {
					if (!selected) {
						if (h1Turn && h1.getHeroPower().contains(me.getX(), me.getY()) && h1.getMana() - h1.getHeroPower().getCost() > -1) {
							if (!h1.getHeroPower().needsTarget()) {
								h1.getHeroPower().play(b);
		
							}
								
						}
						if (!h1Turn && h2.getHeroPower().contains(me.getX(), me.getY()) && h2.getMana() - h2.getHeroPower().getCost() > -1) {
							if (!h2.getHeroPower().needsTarget()) {
								h2.getHeroPower().play(b);
							}
								
						}
								
						if (h1Turn && h1.contains(me.getX(), me.getY()) && h1.canAttack()) {
							selected = true;
							sel = h1;
							h1.select(true);
						}
						if (!h1Turn && h2.contains(me.getX(), me.getY())&&h2.canAttack()) {
							selected = true;
							sel = h2;
							h2.select(true);
						}
						for (int i = 0; i < 7; i++) {
							if (h1Turn && h1.getMinions()[i] != null && h1.getMinions()[i].contains(me.getX(), me.getY())
									&&h1.getMinions()[i].canAttack()) {
								selected = true;
								sel = h1.getMinions()[i];
								h1.getMinions()[i].select(true);
							}
							if (!h1Turn && h2.getMinions()[i] != null && h2.getMinions()[i].contains(me.getX(), me.getY())
									&&h2.getMinions()[i].canAttack()) {
								selected = true;
								sel = h2.getMinions()[i];
								h2.getMinions()[i].select(true);
							}
						}
						if(h1Turn) {
							for(int i=0;i<h1.getHand().getHand().size(); i++) {
								if(h1.getHand().getHand().get(i).contains(me.getX(), me.getY())
										&&h1.getMana()-h1.getHand().getHand().get(i).get().getCost()>=0) {
									if(i==h1.getHand().getHand().size()-1) {
										selectCard(1,i);
										if(b.getHero(1).getHand().get(i).get().needsTarget()&&
												!(b.getHero(1).getHand().get(i).get().targetsOnlyMinions()&&boardIsEmpty()))
											target(b.getHero(1).getHand().get(i));
									}
									else if(!h1.getHand().getHand().get(i+1).contains(me.getX(), me.getY())) {
										selectCard(1,i);
										if(b.getHero(1).getHand().get(i).get().needsTarget()&&
												!(b.getHero(1).getHand().get(i).get().targetsOnlyMinions()&&boardIsEmpty()))
											target(b.getHero(1).getHand().get(i));
									}
								}
							}
						}
						if(!h1Turn) {
							for(int i=0;i<h2.getHand().getHand().size(); i++) {
								if(h2.getHand().getHand().get(i).contains(me.getX(), me.getY())
										&&h2.getMana()-h2.getHand().getHand().get(i).get().getCost()>=0) {
									if(i==h2.getHand().getHand().size()-1) {
										selectCard(0,i);
										if(b.getHero(0).getHand().get(i).get().needsTarget()&&
												!(b.getHero(0).getHand().get(i).get().targetsOnlyMinions()&&boardIsEmpty()))
											target(b.getHero(0).getHand().get(i));
									}
									else if(!h2.getHand().getHand().get(i+1).contains(me.getX(), me.getY())) {
										selectCard(0,i);
										if(b.getHero(0).getHand().get(i).get().needsTarget()&&
												!(b.getHero(0).getHand().get(i).get().targetsOnlyMinions()&&boardIsEmpty()))
											target(b.getHero(0).getHand().get(i));
									}
								}
							}
						}
						// hard-coded in stuff for selecting a hero or minion
					}
					else {
						/*for(int i=0;i<h2.getHand().getHand().size(); i++) {
							if(b.contains(me.getX(), me.getY())) {
								if(i==h2.getHand().getHand().size()-1) {
									selected = false;
									if (selCard == h2.getHand().get(i)) {
										h2.getHand().get(i).get().play(b);
										checkGameOver();
									}
								}
							}
						}
						
						for(int i=0;i<h2.getHand().getHand().size(); i++) {
							if(b.contains(me.getX(), me.getY())) {
								if(i==h2.getHand().getHand().size()-1) {
									selected = false;
									if (selCard == h2.getHand().get(i)) {
										h2.getHand().get(i).get().play(b);
										checkGameOver();
									}
								}
							}
						}*/
						if(h1Turn == true && selCard != -1) {
							if ((!h1.getHand().getHand().get(selCard).get().needsTarget()||boardIsEmpty())&&b.contains(me.getX(), me.getY())) {
								playCard(selCard, 1);
								resetHandSelected();
								checkGameOver();
							}
							else if(h1.getHand().getHand().get(selCard).get().needsTarget()) {
								if(h1.getHand().getHand().get(selCard).contains(me.getX(), me.getY())) {
									resetHandSelected();
								}
								for(int i=0; i<7;i++) {
									if(selCard != -1) {
										if(h1.getMinions()[i]!=null&&h1.getMinions()[i].contains(me.getX(), me.getY()))
											trigger(h1.getMinions()[i]);
										else if(h2.getMinions()[i]!=null&&h2.getMinions()[i].contains(me.getX(), me.getY()))
											trigger(h2.getMinions()[i]);
									}
								}
								if(selCard!=-1) {
									if(!h1.getHand().getHand().get(selCard).get().targetsOnlyMinions()) {
										if(h1.contains(me.getX(), me.getY()))
											trigger(h1);
										else if(h2.contains(me.getX(), me.getY()))
											trigger(h2);
									}
								}
							}
							selC=null;
							resetHandSelected();
						}
						if(h1Turn == false && selCard != -1) {
							if ((!h2.getHand().getHand().get(selCard).get().needsTarget()||boardIsEmpty())&&b.contains(me.getX(), me.getY())) {
								playCard(selCard, 0);
								resetHandSelected();
								checkGameOver();
							}
							else if(h2.getHand().getHand().get(selCard).get().needsTarget()) {
								if(h2.getHand().getHand().get(selCard).contains(me.getX(), me.getY())) {
									resetHandSelected();
								}
								for(int i=0; i<7;i++) {
									if(selCard != -1) {
										if(h1.getMinions()[i]!=null&&h1.getMinions()[i].contains(me.getX(), me.getY()))
											trigger(h1.getMinions()[i]);
										else if(h2.getMinions()[i]!=null&&h2.getMinions()[i].contains(me.getX(), me.getY()))
											trigger(h2.getMinions()[i]);
									}
								}
								if(selCard!=-1) {
									if(!h2.getHand().getHand().get(selCard).get().targetsOnlyMinions()) {
										if(h1.contains(me.getX(), me.getY()))
											trigger(h1);
										else if(h2.contains(me.getX(), me.getY()))
											trigger(h2);
									}
								}
							}
							selC=null;
							resetHandSelected();
						}
						if (h1.contains(me.getX(), me.getY())) {
							selected = false;
							if (sel == h2) {
								h2.attack(h1, b);
								checkGameOver();
							}
							for (int i = 0; i < 7; i++) {
								if (sel == h2.getMinions()[i] && sel != null) {
									h2.getMinions()[i].attack(h1, b);
									checkGameOver();
								}
							}
						}
						if (h2.contains(me.getX(), me.getY())) {
							selected = false;
							if (sel == h1) {
								h1.attack(h2, b);
								checkGameOver();
							}
							for (int i = 0; i < 7; i++) {
								if (sel == h1.getMinions()[i] && sel != null) {
									h1.getMinions()[i].attack(h2, b);
									checkGameOver();
								}
							}
						}
						for (int i = 0; i < 7; i++) {
							if (h1.getMinions()[i] != null && h1.getMinions()[i].contains(me.getX(), me.getY())) {
								selected = false;
								if (sel == h2) {
									h2.attack(h1.getMinions()[i], b);
									checkGameOver();
								}
								for (int j = 0; j < 7; j++) {
									if (sel == h2.getMinions()[j] && sel != null) {
										h2.getMinions()[j].attack(h1.getMinions()[i], b);
										checkGameOver();
									}
								}
							}
							if (h2.getMinions()[i] != null && h2.getMinions()[i].contains(me.getX(), me.getY())) {
								selected = false;
								if (sel == h1) {
									h1.attack(h2.getMinions()[i], b);
									checkGameOver();
								}
								for (int j = 0; j < 7; j++) {
									if (sel == h1.getMinions()[j] && sel != null) {
										h1.getMinions()[j].attack(h2.getMinions()[i], b);
										checkGameOver();
									}
								}
							}
						}
						if (!selected) resetSelect();
						// hard-coded in stuff for the second selection
					}
					frame.repaint();
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mousePressed = false;
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		frame.repaint();
	}
	private void selectCard(int hero, int i) {
		selected = true;
		selCard = i;
		if(!b.getHero(hero).getHand().getHand().get(i).get().needsTarget())
			selC=b.getHero(hero).getHand().getHand().get(i);
	}
	
	private void target(card c) {
		drawArrowPls=true;
		arrX=c.getX()+minion.CARD_WIDTH/2;
		arrY=c.getY()+minion.CARD_LENGTH/2;
	}
	
	private void trigger(targetable t) {
			if(h1Turn) {
				h1.getHand().getHand().get(selCard).get().target(t, b);
			}
			else {
				h2.getHand().getHand().get(selCard).get().target(t, b);
			}
			playCard(selCard, 1);
			resetHandSelected();
			checkGameOver();
	}
	
	private boolean boardIsEmpty() {
		for(int i=0; i<7;i++) {
			if(h1.getMinions()[i]!=null||h2.getMinions()[i]!=null)
				return false;
		}
		return true;
	}
	
	private void resetHandSelected() {
		drawArrowPls=false;
		selCard = -1;
		selected = false;
	}
	
	private void switchTurn() {//switches between hero1's turn and hero2's turn
		if(h1Turn) {
			h2.incTotalMana();
			h2.setMana(h2.getTotalMana());
			h1.setNumAtks(0);
			for (int i = 0; i < 7; i++) {
				if (h1.getMinions()[i]!=null)
					h1.getMinions()[i].setNumAtks(0);
			}
		//	h2.drawCard();
		}
		else {
			h1.incTotalMana();
			h1.setMana(h1.getTotalMana());
			if(h1.getHand().getHand().size()<10)
				h1.drawCard();
			else {
				//discard
			}
			h2.setNumAtks(0);
			for (int i = 0; i < 7; i++) {
				if (h2.getMinions()[i]!=null)
					h2.getMinions()[i].setNumAtks(0);
			}
		//	h1.drawCard();
		}
		resetHandSelected();
		selC=null;
		time=CONV_TURN_LENGTH;

		h1Turn = !h1Turn;
	}
	
	private boolean containsEndTurn(int x, int y) {
		if(x>1465&&x<1614&&y>460&&y<535)
			return true;
		return false;
	}

	private void drawRope(Graphics g, int t) {
		for(int i=0; i<(t*4); i++) {
			g.drawImage(ropeImg,1430-i/4*ropeImg.getWidth(null), 490, null);
		}

	}
	
	private void drawArrow(Graphics g, int x1, int y1, int x, int y) {
		 g.setColor(Color.RED);
         Graphics2D g2 = (Graphics2D) g;
         g2.setStroke(new BasicStroke(24));
     	int hyp = (int)(calcHyp(x-x1, y-y1)/70);
         for(int i=0; i<hyp; i+=2) {  	
         	g2.drawLine(x1+i*((x-x1)/hyp), y1+i*((y-y1)/hyp), x1+(i+1)*((x-x1)/hyp), y1+(i+1)*((y-y1)/hyp));
         }
         if (h1Turn && h2.contains(x,y)||h1.contains(x,y)) {  	
             drawTarget(x, y, g2);
         }
         for (int i = 0; i < 7; i++) {
             if (h2.getMinions()[i]!=null&&h2.getMinions()[i].contains(x, y)) {
                 drawTarget(x, y, g2);
             }
             if (h1.getMinions()[i]!=null&&h1.getMinions()[i].contains(x, y)) {
                 drawTarget(x, y, g2);
             }
         }
	}
	
	private void drawArrow(Graphics g, int x, int y) {
		if(sel!=null) {
            g.setColor(Color.RED);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(24));
            int x1=sel.getX()+minion.CARD_WIDTH/2;
        	int y1=sel.getY()+minion.CARD_LENGTH/2;
        	int hyp = (int)(calcHyp(x-x1, y-y1)/70);
            for(int i=0; i<hyp; i+=2) {  	
            	g2.drawLine(x1+i*((x-x1)/hyp), y1+i*((y-y1)/hyp), x1+(i+1)*((x-x1)/hyp), y1+(i+1)*((y-y1)/hyp));
            }
            if (h1Turn && h2.contains(x,y)) {  	
                drawTarget(x, y, g2);
            }
            else if(!h1Turn && h1.contains(x,y)){
                drawTarget(x, y, g2);
            }
            for (int i = 0; i < 7; i++) {
                if (h1Turn&&h2.getMinions()[i]!=null&&h2.getMinions()[i].contains(x, y)) {
                    drawTarget(x, y, g2);
                }
                if (!h1Turn&&h1.getMinions()[i]!=null&&h1.getMinions()[i].contains(x, y)) {
                    drawTarget(x, y, g2);
                }
            }
        }
	}
	
	private double calcHyp(int i, int j) {
		return Math.sqrt(Math.pow(i, 2)+ Math.pow(j, 2));
	}
	private void drawTarget(int x, int y, Graphics2D g2) {
		g2.setStroke(new BasicStroke(8));
		g2.drawOval(x-40, y-40, 80, 80);
		g2.drawOval(x-20, y-20, 40, 40);
	}
	
	private void cardHover(MouseEvent me) {
		for(int i=0;i<h1.getHand().getHand().size(); i++) {
			if(h1.getHand().getHand().get(i).contains(me.getX(), me.getY())) {
				if(i==h1.getHand().getHand().size()-1) {
					hoverThisCard(i);
				}
				else if(!h1.getHand().getHand().get(i+1).contains(me.getX(), me.getY())) {
					hoverThisCard(i);
				}
				else {
					resetCardHover(i);
				}
			}
			else {
					resetCardHover(i);
			}
		}
		
	}

	private void resetCardHover(int i) {
		h1.getHand().getHand().get(i).get().setCardHover(false);
		h1.getHand().getHand().get(i).get().resetImg();
	}

	private void hoverThisCard(int i) {
		h1.getHand().getHand().get(i).get().setCardHover(true);
		h1.getHand().getHand().get(i).get().enlarge();
	}
	
	private void minionHover(MouseEvent me) {
		for(int i=0; i<7; i++) {
			if(h1.getMinions()[i] != null && h1.getMinions()[i].contains(me.getX(), me.getY())){
				//for future use idk
			}
		}
	}
	
	private void checkGameOver() { // locks the game if a hero dies
		if (h1.gethp() == 0 || h2.gethp() == 0) gameOver = true;
		
	} 
	
	private void resetSelect() {//unselects everything
		for (int j = 0; j < 7; j++) {
			if (h1.getMinions()[j] != null) {
				h1.getMinions()[j].select(false);
			}
			if (h2.getMinions()[j] != null) {
				h2.getMinions()[j].select(false);
			}
			h1.select(false);
			h2.select(false);
		}
		sel=null;
	}
	
	public void draw(Graphics g) {
		b.draw(g);
		if(!h1Turn)
			g.drawImage(endTurnButton, 1460, 452, null);
		//g.drawImage(h1.getHand().getHand().get(0).get().getImage(), 10, 10, null);
		//h.draw(g);
		//he.draw(g);
		//he2.draw(g);
	}
	public void openImages() {
		try {
			endTurnButton = ImageIO.read(getClass().getClassLoader().getResource("endturn.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}
		endTurnButton=endTurnButton.getScaledInstance(160, 85, Image.SCALE_DEFAULT);
		
		try {
			ropeImg = ImageIO.read(getClass().getClassLoader().getResource("rope4.png"));
			} catch (IOException e) {
			System.out.println("Problem with ...");
			e.printStackTrace();
			}


	}


}
