package com.IB.SL.entity.inventory.quests;

import com.IB.SL.Game;
import com.IB.SL.entity.inventory.Quest;

public class testQuest extends Quest	{
	
	
	public testQuest() {
		this.name = "Test";
		this.desc = "Thanks for\nhelping us\ntest the game,\n" + Game.PersonNameGetter + " \n\n\n      -SL Team";
		
		//Add "/" character for fonts
		
		
	}
}
