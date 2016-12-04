package com.IB.SL.level.interactables;

import java.util.List;
import java.util.Random;

import com.IB.SL.Game;
import com.IB.SL.Game.gameState;
import com.IB.SL.entity.inventory.ChestInventory;
import com.IB.SL.entity.inventory.EquipableItem;
import com.IB.SL.entity.inventory.item.Item;
import com.IB.SL.entity.inventory.item.consumables.CoinBag;
import com.IB.SL.entity.inventory.item.consumables.HealthPot;
import com.IB.SL.entity.inventory.item.consumables.ManaPot;
import com.IB.SL.entity.inventory.item.consumables.StaminaPot;
import com.IB.SL.entity.inventory.item.equipables.staves.wand_ArcaneTwig;
import com.IB.SL.entity.inventory.item.equipables.staves.wand_FlareScepter;
import com.IB.SL.entity.mob.PlayerMP;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.Sprite;
import com.IB.SL.graphics.UI.GUI;
import com.IB.SL.util.Debug;

public class Chest extends Interactable {
	
	public enum Type {
		EASY, NORMAL, HARD, ADVANCED, RANDOM, STAFF1, EMPTY;
	}
	
	public Type type;
	protected static java.util.Random Rand = new Random();

	public static Sprite sprite;
	boolean added = false;
	boolean addedNormal = false;
	private Sprite Chest = Sprite.Chest_Dungeon_C;
	private GUI gui;

	public Chest(int x, int y, Type type) {
		this.x = x << Game.TILE_BIT_SHIFT;
		this.y = y << Game.TILE_BIT_SHIFT;
		this.xBound = 11;
		this.yBound = 5;
		this.xOffset = -7;
		this.yOffset = -13;
		this.mobhealth = 1000;
		this.ChestInventory = new ChestInventory(16);
		ChestInventory.setType(ChestInventory.chestType.Normal);
		this.gui = new GUI();
		this.invulnerable = true;
		this.type = type;
		if (this.type == this.type.RANDOM) {
		int random = (Rand.nextInt((20 - 1) + 1) + 1);
	if (random == 20) {
		this.type = this.type.STAFF1;
	} else if (random == 13) {
		this.type = this.type.ADVANCED;
	} else if (random >= 10 && random <= 12) {
		this.type = this.type.HARD;
	} else if (random >= 6 && random <= 9) {
		this.type = this.type.NORMAL;
	} else if (random >= 1 && random <= 5) {
		this.type = this.type.EMPTY;
	}
		}
		
	}
	
	boolean addItem(Item item) {
		boolean added = false;
		if (ChestInventory.add(item)) {	
			} else {
				added = false;
		}
		return added;
	}
	
	public void generateEasyItems() {
		if (added == false) {
			ChestInventory.add(new ManaPot());
			ChestInventory.add(new StaminaPot());
			added =! added;
		}
	}
	
	public void generateNormalItems() {
		if (added == false) {
		ChestInventory.add(new HealthPot());
		ChestInventory.add(new StaminaPot());
		ChestInventory.add(new CoinBag(CoinBag.Type.RANDOM));

		added =! added;
		}
	}
	
	public void testItem() {
		List<Item> items = level.getItemsFixed((int)this.x + 8, (int) this.y + 8, 20);
		for (int i = 0; i < items.size(); i++) {
			if (items.size() > 0) {
				if (items instanceof CoinBag) {
				} else {
					if (ChestInventory.add(items.get(i))) {
						items.get(i).remove();
						
						
//							System.out.println("TRUE: " + items.get(i));
					}
				}}}
			}
	
	public void genStaff1() {
		if (added == false) {
		ChestInventory.add(new wand_FlareScepter(EquipableItem.slot_WEAPON));
		added =! added;
		}
		}
	
	
	public void decideGeneration() {
		if (type == type.RANDOM) {
			type = type.EASY;
		}
		if (Game.getGame().getLevel() != null) {
			if (type == type.EASY) {
				generateEasyItems();
			} else if (type == type.NORMAL) {
				generateNormalItems();
			} else if (type == type.HARD) {
			generateHardItems();
			} else if (type == type.ADVANCED) {
				generateAdvancedItems();
			} else if (type == type.STAFF1) {
				genStaff1();
			}
			
			
//			ChestInventory.add(new UncompiledMatter(UncompiledMatter.Tier.RANDOM));

			
			
			
			if (Game.getGame().gameState == Game.getGame().gameState.INGAME_A) {
				testItem();
			}
		}
	}
	
	public void update() {
		decideGeneration();
		if (ChestInventory.isEmpty()) {
			Chest = Sprite.Chest_Dungeon_O;
		}
			}
	
	
	/*public boolean OpenChest(Screen screen) {
		List<Player> players = level.getPlayers(this, 20);
		boolean Transpot = false;
			if (level.getPlayersBool(this, 20)) {
				if (Game.getGame().getPlayer().input.generalActivator) {
					System.out.println(ChestInventory.listItems(0));
					System.out.println(ChestInventory.listItems(1));
					System.out.println(ChestInventory.listItems(2));
					System.out.println(ChestInventory.listItems(3));
					gui.renderName(screen, "Chest", (int)x, (int)y, -4, true, false, true);
					for(int i = 0; i < players.size(); i++) {
						players.get(i).inChest = true;
*/
	
	private void generateAdvancedItems() {
		if (added == false) {
		ChestInventory.add(new HealthPot());
		ChestInventory.add(new ManaPot());
		ChestInventory.add(new StaminaPot());
		ChestInventory.add(new ManaPot());
		ChestInventory.add(new StaminaPot());
		ChestInventory.add(new CoinBag(CoinBag.Type.RANDOM));
		ChestInventory.add(new CoinBag(CoinBag.Type.RANDOM));
		ChestInventory.add(new wand_ArcaneTwig(EquipableItem.slot_WEAPON));

			added =! added;
		}
	}

	private void generateHardItems() {
		if (added == false) {
		ChestInventory.add(new HealthPot());
		ChestInventory.add(new ManaPot());
		ChestInventory.add(new CoinBag(CoinBag.Type.RANDOM));
		ChestInventory.add(new StaminaPot());
			added =! added;
		}
	}

	public void OpenChest(Screen screen) {
		List<PlayerMP> players = level.getPlayersFixed((int)this.x + 8, (int) this.y + 8, 20);
		if (level.getPlayersFixedBool((int)this.x + 8, (int)this.y + 8, 20)) {
			if(!ChestInventory.isEmpty()) {
			Chest = Sprite.Chest_Dungeon_O;
			}
			for (int i = 0; i < players.size(); i++) {
				gui.renderName(screen, "Open - F", (int) x - 16, (int) y - 4, -3, true, false, true);
			//	if (players.get(i).input != null) {
				try {
			if (players.get(i).input.generalActivator && !players.get(i).inventoryEnabled) {
				gui.renderInventory(screen, this, players.get(i));//17
						players.get(i).inChest = true;
			} else {
				players.get(i).inChest = false;
			}
			} catch (Exception e) {
				
			}
			}
				
		} else if (!ChestInventory.isEmpty()) {
			Chest = Sprite.Chest_Dungeon_C;
		}
	} 
	
/*	public boolean EntityTele(double xi, double yi, Level level, List<Entity> entities) {
		boolean TransportEntity = false;
		int xp = 0, yp = 0;
		for (int i = 0; i < entities.size(); i++) {
			xp = (int) entities.get(i).getX();
			yp = (int) entities.get(i).getY();
				if (xp < (int) x + sprite.getWidth() + 1
	            && xp > (int) x - 1
	            && yp < (int) y  + sprite.getHeight() + 1
	            && yp > (int)y - 1
	           
	            ) {
					TransportEntity = true;
				}
		}
		return TransportEntity;
	}*/
	
	public void render(Screen screen) {
		if (Game.getGame().gameState == gameState.INGAME_A) {
			Debug.drawRect(screen, (int)x, (int)y, 16, 16, 0xFF00FF, true);
		}
		//int radius = level.radius / 2 + 5;
		//screen.renderLight((int)x - 34 + radius, (int)y - 30 + radius, 50 - radius, 20, 20, 40);
		sprite = Chest;
		screen.renderMobSpriteUniversal((int)x, (int)y, sprite);
		
	}
	
	public void renderGUI(Screen screen) {
		OpenChest(screen);
	}
	
}
