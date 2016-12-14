package com.IB.SL.level.worlds;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.IB.SL.Game;
import com.IB.SL.entity.inventory.item.equipables.rings.PreciousRing;
import com.IB.SL.entity.mob.Player;
import com.IB.SL.entity.mob.hostile.FrostSpirit;
import com.IB.SL.entity.mob.hostile.PoisonZombie;
import com.IB.SL.entity.mob.hostile.Slime;
import com.IB.SL.entity.mob.hostile.UndeadCaster;
import com.IB.SL.entity.mob.hostile.Zombie;
import com.IB.SL.entity.mob.hostile.minions.Slimey;
import com.IB.SL.entity.mob.peaceful.Carraige;
import com.IB.SL.entity.mob.peaceful.Guard;
import com.IB.SL.entity.mob.peaceful.Horse;
import com.IB.SL.graphics.SpriteSheet;
import com.IB.SL.level.Level;
import com.IB.SL.level.TileCoord;
import com.IB.SL.level.interactables.Teleporter;
import com.IB.SL.util.Sound;

public class MainLevel extends Level{
		
	/**
	 * 
	 */

	public static boolean spawnASM = false;

	protected static java.util.Random Random = new Random();
	public static java.util.Random random = Random;
	//protected static Random randomYRandom = new Random();
	//public static int randomY = randomYRandom.nextInt(1 + 4);

	
	public MainLevel(String path) { 
		super(path);
	}
	
	
/*	public float noise(float x, float y) {
	    return simplexnoise(x / 5, y / 5);
	}
	
	private float simplexnoise(float f, float g) {
		return 0;
	}


	public float[][] generateOctavedSimplexNoise(int width, int height, int octaves, float roughness, float scale){
	      float[][] totalNoise = new float[width][height];
	       float layerFrequency = scale;
	       float layerWeight = 1;
	       float weightSum = 0;

	       for (int octave = 0; octave < octaves; octave++) {
	          //Calculate single layer/octave of simplex noise, then add it to total noise
	          for(int x = 0; x < width; x++){
	             for(int y = 0; y < height; y++){
	                totalNoise[x][y] += (float) noise(x * layerFrequency,y * layerFrequency) * layerWeight;
	             }
	          }
	          
	          //Increase variables with each incrementing octave
	           layerFrequency *= 2;
	           weightSum += layerWeight;
	           layerWeight *= roughness;
	           
	       }
	       return totalNoise;
	   }*/
	
	protected void loadLevel(String path) {
		minimap_enabled  = true;
		Overworld  = true;
		SpawnList.clear();
		SpawnList.add(new Zombie(-1, -1));
		SpawnList.add(new PoisonZombie(-1, -1));
		SpawnList.add(new UndeadCaster(-1, -1));

		
		SpawnTime_MOD = 280;
		
		try {
			BufferedImage image = ImageIO.read(MainLevel.class.getResource(path));
			System.out.println("Overlay Path: " + "/overlays" + path);
			int w = width = image.getWidth();
			int h = height = image.getHeight();

			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		}catch (IOException e) {	
			e.printStackTrace();
			System.out.println("IOException! Failed To Load Level File!");
		}
		
		try {
			BufferedImage overlayImage = ImageIO.read(MainLevel.class.getResource("/overlays" + path));  			int overlayW = width = overlayImage.getWidth();
			int overlayH = height = overlayImage.getHeight();
			overlayTiles = new int[overlayW * overlayH];
			torchTiles = new int[overlayW * overlayH];
			overlayImage.getRGB(0,0,overlayW,overlayH,overlayTiles,0,overlayW);
			overlayImage.getRGB(0,0, overlayW, overlayH, torchTiles, 0, overlayW);
			System.out.println("Loaded Overlays");
		} catch (IOException e) {
			System.out.println("Failed To Load Torches");
		}
		
		
		
		
		
      /*  add(new Shooting(277, 478));
		//System.out.println("X: " + entities.get(x).getX() / 16 + " Y: " + entities.get(x).getY() / 16);
		add(new AStarTracker(276,458));
		//add(new AStarHostile(285, 456));
		add(new Zombie(285, 456));

		add(new UndeadCaster(282, 461));
		add(new UndeadCaster(278, 465));
		add(new UndeadCaster(269, 459));
		//add(new Tracker(270, 456));
		add(new UndeadCaster(270, 456));

		add(new Shooting(276, 458));
		for (int i = 0; i < 1; i++) {
		add(new Dumby(274,461));
		add(new Occulus(402, 130));
		
		}
		for (int i = 0; i < 1000; i++) {
			add(new AStarTracker(randomX * randomX, randomY * randomY));
			System.out.println("X: " + entities.get(i).getX() + " Y: " + entities.get(i).getY());

		}*/
		
		Game.currentLevelId = Maps.mainId;

		add(new Guard(615, 230, new TileCoord(601, 230), new TileCoord(636, 230)));
		add(new Guard(652, 230, new TileCoord(649, 230), new TileCoord(685, 230)));
		add(new Guard(615, 228, new TileCoord(601, 228), new TileCoord(636, 228)));
		add(new Guard(652, 228, new TileCoord(649, 228), new TileCoord(685, 228)));
		
		//add(new Teleporter(270, 459));
		
		add(new Carraige(840, 170, "Ghelln"));
		add(new Carraige(241, 867, "Fenir"));
		add(new Carraige(640, 270, "Astellon"));

		for(int i = 0; i < 80; i++){
			int sx = (random.nextInt(30) + 12) * 16;
			int sy = (random.nextInt(50) + 15) * 16;
			if (returnTileXY(tile, sx, sy).toString().equals(tile.Grass.toString()) || returnTileXY(tile, sx, sy).toString().equals(tile.Swamp.toString()) || returnTileXY(tile, sx, sy).toString().equals(tile.Sand.toString()) || returnTileXY(tile, sx, sy).toString().equals(tile.Snow.toString())) {
			if (!returnTileXY(tile, sx, sy).solid()) {
		         add(new Teleporter(sx, sy));
		         }
			}
			

        // add(new Occulus(sx, sy));
			//System.out.println("X: " + entities.get(x).getX() / 16 + " Y: " + entities.get(x).getY() / 16);
        
      }
		
		/*for(int i = 0; i < 290; i++){
			int sx = (random.nextInt(30) + 12) * 16;
			int sy = (random.nextInt(50) + 15) * 16;
			
			if(!returnTileXY(tile, sx, sy).solid()) {
				add(new PoisonZombie(sx, sy));
			}
		}*/
		
		for(int i = 0; i < 530; i++){
			int sx = (random.nextInt(40) + 15) * 16;
			int sy = (random.nextInt(40) + 15) * 16;
			//while(!returnTileXY(tile, sx, sy).solid()) {
			if(!returnTileXY(tile, sx, sy).solid()) {
				add(new UndeadCaster(sx, sy));
			}
				//sx = 0;
			//	sy = 0;
			//}
			/*	if (!returnTileXY(tile, sx, sy).solid()) {

	         }*/
		}
		
		/*for(int i = 0; i < 100000; i++){
			int sx = (random.nextInt(31) + 15) * 16;
			//int sx = (dropChance.nextInt((6 - 1) + 1) + 1);
			int sy = (random.nextInt(25) + 15) * 16;
				if (!returnTileXY(tile, sx, sy).solid()) {
	         if (returnTileXY(tile, sx, sy).toString().equals(tile.Wood.toString())) {
	         add(new Dumby(sx, sy));
	         }
	         }
		}*/

		for(int i = 0; i < 500; i++){
			int sx = (random.nextInt(30) + 12) * 16;
			int sy = (random.nextInt(50) + 15) * 16;
			
			//while(!returnTileXY(tile, sx, sy).solid()) {
			if(!returnTileXY(tile, sx, sy).solid()) {
				add(new Zombie(sx, sy));
			}
			
			
			//	sx = 0;
			//	sy = 0;
		//	}
			
			/*if (!returnTileXY(tile, sx, sy).solid()) {
	         
			}*/
		}


		
		add(new PreciousRing(638 * 16, 208 * 16, 3000, 1, PreciousRing.slot_HEAD));

		for (int i = 0; i < 50; i++) {
			int sx = (random.nextInt(45) + 10) * 16;
			int sy = (random.nextInt(45) + 10) * 16;
		//	while(!returnTileXY(tile, sx, sy).solid()) {
			if(!returnTileXY(tile, sx, sy).solid()) {
			add(new Slimey(sx, sy));
			if(!returnTileXY(tile, sx - 2, sy + 2).solid()) {
				add(new Slime(sx - 2, sy + 2));
			}
			if(!returnTileXY(tile, sx + 2, sy - 2).solid()) {
				add(new Slime(sx + 2, sy - 2));
			}
			if(!returnTileXY(tile, sx + 3, sy + 3).solid()) {
				add(new Slime(sx + 3, sy + 3));
			}
			if(!returnTileXY(tile, sx + 3, sy - 3).solid()) {
				add(new Slime(sx + 3, sy - 3));
			}
		//	sx = 0;
		//	sy = 0;
			}
			
			/*if (!returnTileXY(tile, sx, sy).solid()) {

			}*/
		}
		/*for(int i = 0; i < 6000; i++){
			int sx = (random.nextInt(30) + 12) * 16;
			int sy = (random.nextInt(50) + 15) * 16;
	         add(new Shooting(sx, sy));
		}*/
	       //  add(new Occulus(sx, sy));
	
		add(new Horse(230, 863));
		add(new Horse(226, 865));

		add(new Horse(633, 264));
		add(new Horse(637, 262));
		
		add(new Horse(619, 459));
		add(new Horse(831, 166));
		add(new Horse(827, 164));

		add(new Horse(791, 426));
		add(new Horse(787, 427));

		add(new Horse(616, 716));
		add(new Horse(620, 719));

	      } 
	
	protected void generateLevel() {
		for (int y = 0; y < 64; y++) {
			for (int x = 0; x < 64; x++) {
				getTile(x, y);
				}
			}
		}
	
	public void checkExits(Player player, Level level, int x, int y) {
		refresh();
		
		//if (x == 641 && y == 219) {
	//	}
		
		
		if (x == 729 && y == 362) {//296, 464 Spawn Haven
			Sound.switchMusic(Sound.Boss, 0.8f);
			Game.getGame().setLevel(new Dungeon01(Maps.dungeon01));
			level = Game.getGame().getLevel();
			level.add(player);
			Level.Overworld = false;
			Level.minimap_enabled = true;
			player.setPosition(new TileCoord(49, 55)); 
			SpriteSheet.minimapDYN = new SpriteSheet("/levels/Dungeons/Labarynth.png", 256);
			return;
		}
		
		if (x == 654 && y == 78 || x == 655 && y == 78) {//296, 464 Spawn Haven
			Sound.switchMusic(Sound.TheIcicleFalls, 0.7f);
			Game.getGame().setLevel(new Dungeon03(Maps.dungeon03));
			level = Game.getGame().getLevel();
			level.add(player);
			Level.Overworld = false;
			Level.minimap_enabled = true;
			player.setPosition(new TileCoord(177, 66)); 
			SpriteSheet.minimapDYN = new SpriteSheet(Maps.dungeon03, 356);
			return;
		}
		
		
		if (x == 401 && y == 735) {//296, 464 Spawn Haven
			Sound.switchMusic(Sound.VoidDungeon, 0.8f);
			Game.getGame().setLevel(new Dungeon02(Maps.dungeon02));
			level = Game.getGame().getLevel();
			level.add(player);
			Level.Overworld = false;
			Level.minimap_enabled = true;
			player.setPosition(new TileCoord(146, 155)); 
			SpriteSheet.minimapDYN = new SpriteSheet(Maps.dungeon02, 256);
			return;
		}
		
		Game.getGame().setLevel(new SpawnHaven(Maps.SpawnHaven));
		level = Game.getGame().getLevel();
		level.add(player);
		player.setPosition(new TileCoord(52, 78));
		Level.Overworld = false;
		Level.minimap_enabled = false;
		
		}
	
	public void checkExits(Player player, Level level, int x, int y, boolean e) {
		refresh();
		
		if (x == 729 && y == 362) {//296, 464 Spawn Haven
			player.setPosition(49, 55, Maps.dungeon1Id, true); 
			return;
		}
		
		if (x == 654 && y == 78 || x == 655 && y == 78) {//296, 464 Spawn Haven
			player.setPosition(177, 66, Maps.dungeon3Id, true); 
			return;
		}
		
		
		if (x == 401 && y == 735) {//296, 464 Spawn Haven
			player.setPosition(146, 155, Maps.dungeon2Id, true); 
			return;
		}
		
		player.setPosition(52, 78, Maps.spawnHavenId, true); 
		
		}

	}
	


