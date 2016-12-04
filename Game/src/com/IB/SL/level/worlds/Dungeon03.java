package com.IB.SL.level.worlds;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;

import com.IB.SL.Game;
import com.IB.SL.entity.mob.Player;
import com.IB.SL.entity.mob.bosses.Occulus;
import com.IB.SL.entity.mob.hostile.Cultist;
import com.IB.SL.entity.mob.hostile.FrostSpirit;
import com.IB.SL.graphics.SpriteSheet;
import com.IB.SL.level.Level;
import com.IB.SL.level.TileCoord;
import com.IB.SL.level.interactables.Chest;
import com.IB.SL.util.Sound;

public class Dungeon03 extends Level implements Serializable{
	/**
	 * 
	 */
	protected static java.util.Random Random = new Random();
	public static java.util.Random random = Random;
	public Dungeon03(String path) {
		super(path);
	}
	
	
	
	
	protected void loadLevel(String path) {
		minimap_enabled = true;
		Level.Overworld = false;
		/*try {
			BufferedImage image = ImageIO.read(swampLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();

			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		}catch (IOException e) {	
			e.printStackTrace();
			System.out.println("IOException! Failed To Load Level File!");
		}*/
		
		try {
			BufferedImage image = ImageIO.read(Dungeon03.class.getResource(path));
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
			BufferedImage overlayImage = ImageIO.read(Dungeon03.class.getResource("/overlays" + path));
			int overlayW = width = overlayImage.getWidth();
			int overlayH = height = overlayImage.getHeight();
			overlayTiles = new int[overlayW * overlayH];
			torchTiles = new int[overlayW * overlayH];
			overlayImage.getRGB(0,0,overlayW,overlayH,overlayTiles,0,overlayW);
			overlayImage.getRGB(0,0, overlayW, overlayH, torchTiles, 0, overlayW);
			System.out.println("Loaded Overlays");
		} catch (IOException e) {
			System.out.println("Failed To Load Torches");
		}
		
		Game.currentLevelId = Maps.dungeon3Id;

		
		for(int i = 0; i < 500; i++){
			int sx = (random.nextInt((356 - 1) + 1) + 1);
			int sy = (random.nextInt((356 - 1) + 1) + 1);
			/*int sx = (random.nextInt(64) + 15) * 16;
			int sy = (random.nextInt(97) + 15) * 16;*/
			//while(!returnTileXY(tile, sx, sy).solid()) {
			if(!returnTileXY(tile, sx, sy).solid()) {
				add(new FrostSpirit(sx, sy));
			}
		}		
		
		for(int i = 0; i < 1000; i++){
			int sx = (random.nextInt((356 - 1) + 1) + 1);
			int sy = (random.nextInt((356 - 1) + 1) + 1);
			/*int sx = (random.nextInt(64) + 15) * 16;
			int sy = (random.nextInt(97) + 15) * 16;*/
			//while(!returnTileXY(tile, sx, sy).solid()) {
			if(!returnTileXY(tile, sx, sy).solid()) {
				add(new Cultist(sx, sy));
			}
		}		
		
		for(int i = 0; i < 130; i++){
			int sx = (random.nextInt((356 - 1) + 1) + 1);
			int sy = (random.nextInt((356 - 1) + 1) + 1);
			/*int sx = (random.nextInt(64) + 15) * 16;
			int sy = (random.nextInt(97) + 15) * 16;*/
			//while(!returnTileXY(tile, sx, sy).solid()) {
			if(!returnTileXY(tile, sx, sy).solid()) {
				add(new Chest(sx, sy, Chest.Type.RANDOM));
			}
		}
		
		//add(new Coin(49, 53, 50000, 1, Coin.Type.RANDOM));

		//add(new CopperGuardian(93, 131));
		for(int i = 0; i < 3; i++){
			int sx = (random.nextInt((356 - 1) + 1) + 1);
			int sy = (random.nextInt((356 - 1) + 1) + 1);
			if(!returnTileXY(tile, sx, sy).solid()) {
				add(new Occulus(sx, sy));
			}
		}
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
		Sound.switchMusic(Sound.HopeOgg, 0.8f);
		Game.getGame().setLevel(new MainLevel(Maps.main));
		level = Game.getGame().getLevel();
		level.add(player);
		level.Overworld = true;
		level.minimap_enabled = true;
		SpriteSheet.minimapDYN = new SpriteSheet("/levels/WorldMap.png", 1024);

		player.setPosition(new TileCoord(654, 82));

		}
}
	
