package jjgpFinalProject;

import java.util.ArrayList;

public class deck {
	private ArrayList<card> deck = new ArrayList<card>();
	public deck() {
		//for(int i = 0; i < 30; i++) {
		deck.add(new card(new Beefery()));
		deck.add(new card(new fingerCircle()));
		deck.add(new card(new CB()));
		deck.add(new card(new ImpGangBoss()));
		deck.add(new card(new IronbeakOwl()));
		deck.add(new card(new ZombieChow()));
		deck.add(new card(new BloodRazor()));
		deck.add(new card(new Gwanway()));	
		deck.add(new card(new Justin()));	
		deck.add(new card(new WickedKnife()));
		deck.add(new card(new AcidicSwampOoze()));
		deck.add(new card(new BrannBronzebeard()));
		deck.add(new card(new Wisp()));	
		deck.add(new card(new Darkbomb()));
		deck.add(new card(new ElvenArcher()));	
		deck.add(new card(new WickedKnife()));
		deck.add(new card(new AcidicSwampOoze()));
		deck.add(new card(new BrannBronzebeard()));
		deck.add(new card(new Wisp()));	
		deck.add(new card(new Darkbomb()));
		deck.add(new card(new ElvenArcher()));	
		deck.add(new card(new IronbeakOwl()));
		deck.add(new card(new ZombieChow()));
		deck.add(new card(new BloodRazor()));
		deck.add(new card(new WickedKnife()));
		deck.add(new card(new AcidicSwampOoze()));
		deck.add(new card(new BrannBronzebeard()));
		deck.add(new card(new Wisp()));	
		deck.add(new card(new Darkbomb()));
		deck.add(new card(new ElvenArcher()));	
		//}
	}
	public ArrayList<card> getDeck() {
		return deck;
	}

}
