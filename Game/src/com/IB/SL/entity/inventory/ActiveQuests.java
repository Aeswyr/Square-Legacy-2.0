package com.IB.SL.entity.inventory;

import com.IB.SL.graphics.Screen;

public class ActiveQuests {
	private Quest[] quests;
	private Inventory inv;
	private int firstFree;

	public ActiveQuests(int size, Inventory inv) {
		quests = new Quest[size];
		firstFree = 0;
		this.inv = inv;
	}
	
	public boolean add(Quest quest) {
		if (firstFree == quests.length) {
			System.out.println("Could Not Add Quest: " + quest.getName() + " You Have Too Many Active Quests!");
			return false;
		} 
		
		
		for (int i = firstFree; i < quests.length; i++) {
			if (quests[i] == null) {	
				firstFree = i;
				System.out.println("New Quest Active: " + quest.getName() + " At Index: " + i);
				quests[firstFree] = quest;
				quest.remove();
				return true;

			}
			
		}
		firstFree = quests.length;
		return true;
	}
	
	public void render(Screen screen) {
		if (inv.tab == inv.tab.QUESTS) {
			inv.renderTabQUESTS(screen, quests);
		}
		}
	}
	
