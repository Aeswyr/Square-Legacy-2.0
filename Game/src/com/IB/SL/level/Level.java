package com.IB.SL.level;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.IB.SL.Game;
import com.IB.SL.Game.gameState;
import com.IB.SL.entity.Entity;
import com.IB.SL.entity.Entity.HOSTILITY;
import com.IB.SL.entity.inventory.Effect;
import com.IB.SL.entity.inventory.EquipableItem;
import com.IB.SL.entity.inventory.Equipment;
import com.IB.SL.entity.inventory.Inventory;
import com.IB.SL.entity.inventory.item.Item;
import com.IB.SL.entity.inventory.item.equipables.staves.wand_ContradictionWand;
import com.IB.SL.entity.inventory.item.equipables.staves.wand_FlareScepter;
import com.IB.SL.entity.inventory.item.equipables.staves.wand_Pulsefire;
import com.IB.SL.entity.inventory.item.equipables.staves.wand_StygianScepter;
import com.IB.SL.entity.inventory.item.equipables.staves.wand_VoidCrook;
import com.IB.SL.entity.mob.Mob;
import com.IB.SL.entity.mob.Mob.DIRECTION;
import com.IB.SL.entity.mob.Player;
import com.IB.SL.entity.mob.PlayerMP;
import com.IB.SL.entity.mob.bosses.CopperGuardian;
import com.IB.SL.entity.mob.bosses.Occulus;
import com.IB.SL.entity.mob.bosses.VoidBoss;
import com.IB.SL.entity.mob.hostile.FrostSpirit;
import com.IB.SL.entity.mob.hostile.PoisonZombie;
import com.IB.SL.entity.mob.hostile.Slime;
import com.IB.SL.entity.mob.hostile.UndeadCaster;
import com.IB.SL.entity.mob.hostile.Zombie;
import com.IB.SL.entity.mob.hostile.minions.Slimey;
import com.IB.SL.entity.mob.peaceful.Alice;
import com.IB.SL.entity.particle.DamageIndicator;
import com.IB.SL.entity.particle.Particle;
import com.IB.SL.entity.projectile.Projectile;
import com.IB.SL.entity.spawner.Spawner;
import com.IB.SL.entity.spawner.WallParticleSpawner;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.Weather.Rain;
import com.IB.SL.level.tile.Tile;
import com.IB.SL.level.tile.tiles.Water;
import com.IB.SL.level.worlds.Maps;
import com.IB.SL.level.worlds.SpawnHaven;
import com.IB.SL.util.Vector2i;

public class Level implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2980023760275759466L;
	public int width, height;
	protected int[] tilesInt;
	public int[] tiles;
	public int[] overlayTiles;
	public int[] torchTiles;
	public Tile tile;
	public static int nighttime = 0, daytime = 0;
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Projectile> Projectiles = new ArrayList<Projectile>();
	public List<Particle> particles = new ArrayList<Particle>();
	public List<PlayerMP> players = new ArrayList<PlayerMP>();
	public List<Occulus> Occulus = new ArrayList<Occulus>();
	public List<CopperGuardian> CopperGuardian = new ArrayList<CopperGuardian>();
	public List<VoidBoss> VoidBoss = new ArrayList<VoidBoss>();

	public List<Item> items = new ArrayList<Item>();
	public List<Entity> entitiesList = new ArrayList<Entity>();
	public List<Spawner> Spawner = new ArrayList<Spawner>();
	public List<Effect> StatusFx = new ArrayList<Effect>();

	//public List<AnimatedTile> tiles_anim = new ArrayList<AnimatedTile>();
	double addX, addY;
	ArrayList<Integer> xRad = new ArrayList<Integer>();
	double tx, ty;
	double otx, oty;
	
	
	int bitBrickHex = 0xffFF0000; 
	int bitMetalHex = 0xffCCCCCC;
	int BluefogHex = 0xff000059;
	int BookshelfBottomHex = 0xffD9F2C6;
	int BookshelfTopHex = 0xffCEEB5C;
	int BrickCeilingHex = 0xffF20000;
	int BrickWallHex = 0xffE50000;
	int CaveCeilingHex = 0xff666666;
	int CaveWallHex = 0xff595959;
	int CobbleStoneHex = 0xffF2F2F2;
	int CobblestoneCeilingHex = 0xff7F7F7F;
	int CobbleStoneWallHex = 0xff727272;
	int CrackedBrickHex = 0xffE5E5E5;
	int DarkStoneHex = 0xff0C0C0C;
	int DeepLavaHex = 0xffCC7E0A;
	int DeepWaterHex = 0xff0000BF;
	int DirtHex = 0xff725F00;
	int DirtCeilingHex = 0xff665300;
	int DirtWallHex = 0xff594800;
	int DresserBottomHex = 0xffB7CCA7;
	int DresserTopHex = 0xffC2D8B1;
	int GrassHex = 0xff00CC00;
	int HellBrickHex = 0xffBF0000;
	int HellBrickCeilingHex = 0xff7F0000;
	int HellbrickWallHex = 0xff720000;
	int HellCaveCeilingHex = 0xff660000;
	int HellCaveWallHex = 0xff590000;
	int HellsandHex = 0xffCC0000;
	int HellSandCeilingHex = 0xffB20000;
	int HellSandWallHex = 0xffA50000;
	int HellstoneHex = 0xffD80000;
	int IceHex = 0xffA3A3FF;
	int IceBrickHex = 0xffE6E6F2;
	int IceBrickCeilingHex = 0xffDADAE5;
	int IceBrickWallHex = 0xffCDCDD8;
	int IceCaveCeilingHex = 0xffC1C1CC;
	int IceCaveWallHex = 0xffB8B8C1;
	int IceSandHex = 0xffB2B2FF;
	int IceSandCeilingHex = 0xffA9A9F2;
	int IceSandWallHex = 0xffA0A0E5;
	int LavaHex = 0xffFF9A0C;
	int MetalHex = 0xffBFBFBF;
	int MetalCeilingHex = 0xffB2B2B2;
	int MetalWallHex = 0xffA5A5A5;
	int MossCeilingHex = 0xffA5D2A5;
	int MossWallHex = 0xff97BF97;
	int ObsidianCeilingHex = 0xff590059;
	int ObsidianWallHex = 0xff4C004C;
	int PathDirtHex = 0xff99A53D;
	int SandHex = 0xffFFFCB2;
	int SandBrickHex = 0xffD8D697;
	int SandBrickCeilingHex = 0xffCCC98E;
	int SandBrickWallHex = 0xffD8D597;
	int SandCeilingHex = 0xffF2EFA9;
	int SandWallHex = 0xffE5E3A0;
	int SnowHex = 0xffFFFFFF;
	int StoneBrickHex = 0xff4C4C4C;
	int StoneBrickCeilingHex = 0xff3F3F3F;
	int StoneBrickWallHex = 0xff333333;
	int SwampHex = 0xff956533;
	int VoidTileHex = 0xffFF00FF;
	int WaterHex = 0xff0000CC;
	int WoodHex = 0xffFFBC75;
	int WoodCeilingHex = 0xffF2B36F;
	int WoodWallHex = 0xffE5A969;
	int SwirlyHex = 0xffFF70E9;
	int DoorHex = 0xff000000;

	/**
	 * Overworld
	 */

int Cactus = 0xff00F700;
int FlowerCactus = 0xff00ED00;
int ColoredFlowers = 0xffFFDB00;
int YellowFlowers = 0xffFFFF00;
int AnvilHex = 0xff494949;
int StoveHex = 0xff303030;

	/**
	 * Overworld & Dungeon
	 */

int Torch = 0xFFFF6A00;
int Pebble = 0xffD3D3D3;
int BlueMushroom = 0xff1111FF;
int BlueMushroomDirt = 0xff1010ED;
int RedMushroom = 0xffFF1111;
int RedMushroomDirt = 0xffFF1111;
int GreenMushroom = 0xff11FF11;
int GreenMushroomDirt = 0xff11FF11;
int DirtPatch = 0xffB8DD00;
int RedmushroomDirt = 0xffED1010;

	/**
	 * Dungeon
	 */

int Bone = 0xffFCFCFC;
int CrossedBones = 0xffF9F9F9;
int Skull = 0xffF7F7F7;
int DarkCastle = 0xff444444;
int Cave = 0xff545454;
int Portal = 0xff7421A1;
int PathVertical = 0xff333A00;
int PathHorizontal = 0xff3B4400;
int PathCross = 0xff485400;
int PathCornerTL = 0xff515E00;
int PathCornerTR = 0xff5D6D00;
int PathCornerBL = 0xff657700;
int PathCornerBR = 0xff728700;
int PathEndLeft = 0xff7B9100;
int PathEndRight = 0xff88A000;
int PathEndTop = 0xff8EAA00;
int PathEndBottom = 0xff9BBA00;
int BrokenSword = 0xffA3C400;

	/**
	 * Village
	 */

int RedBed = 0xffFF0C0C;
int BlueBed = 0xff0C0CFF;
int GreenBed = 0xff0CFF0C;
int OrangeBed = 0xffFFDB0C;
int TopChair = 0xff8ADD0D;
int BottomChair = 0xff87D30C;
int LeftChair = 0xff80C40B;
int RightChair = 0xff79BA0B;
int Castle = 0xffAAAAAA;
int Village = 0xff69A009;
int Table = 0xff6FAA0A;
	
Tile setTiles;
	private Comparator<Node> nodeSorter = new Comparator<Node>() {
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost)
				return +1;
			if (n1.fCost > n0.fCost)
				return -1;
			return 0;

		}
	};

//	public static String currentLevel = spawn.toString();
	
	 public static int brightness = 50;
	public static boolean overSurvival = false;
	public static boolean overMenu = false;
	public static boolean overQuit = false;
	public static boolean overResume = false;
	public static boolean overMenuPause = false;
	public static boolean overQuitPause = false;
	public static boolean Overworld;
	public static boolean minimap_enabled;
	
	public int radius = 10;
	int time = 0;
	        boolean day = false, night = false;
	        
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();

	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();

		// add(new ParticleSpawner(29 * 16, 31*16, 13, 35, this));

	}
	
	/*private String getName() {
		return Level.currentLevel;
	}
	private HashMap<String, Level> levelMap = new HashMap<String, Level>();
	private Level currentLevel1;

	public void addLevel(Level level) {
	     levelMap.put(level.getName(), level);
	}


	public Level getLevel(String name) {
	     return levelMap.get(name);
	}

	public Level getCurrentLevel() { return currentLevel1; }

	public void gotoLevel(Player player, String levelName) {
	     Level level = getLevel(levelName);
	     currentLevel1 = level;
	     player.setLevel(level);

	     // do other initialization stuff to the level here
	}*/
	
	
	
	protected void generateLevel() {

	}
	
	protected void loadLevel(String path) {
		/*for (int i = 0; i < entities.size(); i++) {
		entities.get(i).remove();
		}*/
	}
	
	public void updateTest() {
			//loadLevel("/levels/Swamp.png");
	}
	
	 public void time1() {
		 
	      if (brightness <= -50) {
	         night = true;
	         day = false;
	      }
	      
	      if (brightness >= 50) {
	         day = true;
	         night = false;
	      }
	      
	      if (night) {
	    	  if (nighttime > 2500) {
	    		  daytime = 0;
	    		  brightness++;
	    	  }
	         return;
	      }
	      
	      if (day) {
	    	  if (daytime > 2600) {
	    	  if (brightness > -50) {
	    		  nighttime = 0;
	         brightness--;
	    		  }
	    	  }
	         return;
	      }
	      
	      brightness++;
	   }
	 
	 public void checkTile() {
		 if (players.size() > 0) {
			 for (int i = 0; i < players.size(); i++) {
				 if (returnTile(tile).equalsIgnoreCase("Water")) {
					players.get(i).swimming = true;
				} else {
					players.get(i).swimming = false;
				}
			 }
		 }
	 }
	 
	 public String returnTile(Tile tile) {
		 String tileString = "";
		 try {
		tile = getTile((int)getPlayerAt(0).getX() >> Game.TILE_BIT_SHIFT, (int)getPlayerAt(0).getY() >> Game.TILE_BIT_SHIFT);
		tileString = tile.toString();
		tileString = tileString.replace("com.IB.SL.level.tile.", "");
		tileString = tileString.substring(0,tileString.indexOf("@"));
		 } catch (Exception e) {
			 
		 }
		 return tileString;
	 }
	 
	 public String returnOverlayTile(Tile tile) {	 
		 String tileString = "";
		tile = getOverlayTile((int)getPlayerAt(0).getX() >> Game.TILE_BIT_SHIFT, (int)getPlayerAt(0).getY() >> Game.TILE_BIT_SHIFT);
		if(tile != null) {
		tileString= tile.toString();
		tileString = tileString.replace("com.IB.SL.level.tile.", "");
		tileString = tileString.substring(0,tileString.indexOf("@")); 
		}  else {
			tileString = "[None]";
		}
	 return tileString;
	 
	 }
	 
	 public String returnTileFromValue(Tile tile, int xy, int yx) {	 
			tile = getTile(xy, yx);
			String tileString = tile.toString();
			tileString = tileString.replace("com.IB.SL.level.tile.", "");
			tileString = tileString.substring(0,tileString.indexOf("@"));
			return tileString;
		 }
	 
	 public Tile returnTileXY(Tile tile, int xy, int yx) {	 
			tile = getTile(xy, yx);
			return tile;
		 }
	 
	 public String returnTile(Tile tile, List<Entity> entities) {	 
			tile = getTile((int)entities.get(0).getX() >> Game.TILE_BIT_SHIFT, (int)entities.get(0).getY() >> Game.TILE_BIT_SHIFT);
			String tileString = tile.toString();
			tileString = tileString.replace("com.IB.SL.level.tile.", "");
			tileString = tileString.substring(0,tileString.indexOf("@"));
			return tileString;
		 }
		 
	 
	 
	 boolean radius1;
	 boolean radius2;
	private Vector2i start;
	private Vector2i goal;
	
	  private Comparator<Entity> ySort = new Comparator<Entity>() {
		    public int compare(Entity e1, Entity e2) {
		      if (e1.y > e2.y) return 1; // Shift Up
		      if (e1.y < e2.y) return -1; // Shift Down
		      return 0;
		    }
		  };
			public boolean isRaining = false;
			public Rain rain = new Rain();

			
	public int myRandom(int min, int max) {
		Random rand = new Random();
		return  random.nextInt((max - min) + 1) + min;
	}
			
	HashMap<Mob, Integer> spawn = new HashMap<Mob, Integer>();
	int  ji = 0;
	public void update() {

		spawn.put(new Zombie(-1, -1), 40);
		spawn.put(new PoisonZombie(-1, -1), 60);
		spawn.put(new FrostSpirit(-1, -1), 60);
		
		Mob minKey = null;
		int minVal = Integer.MAX_VALUE;
		for (Mob key : spawn.keySet()) {
			int val = spawn.get(key);
			if (val < minVal) {
				minVal = val;
				minKey = key;
			}
		}
		
		int lx = (int)(Game.getGame().getPlayer().x / 16) - (int)(((Game.getGame().getScreen().width / 16) / 2) + 8);
		int rx = (int)(Game.getGame().getPlayer().x / 16) + (int)(((Game.getGame().getScreen().width / 16) / 2) + 8);
		
		int ty = (int)(Game.getGame().getPlayer().y / 16) - (int)(((Game.getGame().getScreen().height / 16) / 2) + 5);
		int by = (int)(Game.getGame().getPlayer().y / 16) + (int)(((Game.getGame().getScreen().height / 16) / 2) + 5);
		
		
	//	System.out.println(minVal);

		
		for (; ji < 10000; ji++) {

			int sx = myRandom(lx, rx);
			int sy = myRandom(ty, by);

			System.out.println("X: " + sx + " Y: " + sy);

			if (!returnTileXY(tile, sx, sy).solid()) {
				add(new WallParticleSpawner((int) (sx * 16), (int) (sy * 16), 20000, 1, this));
			}
		}
		
		
		if (Game.getGame().getLevel().isRaining) {
			rain.update(false);
		}
		//TODO: Add mob spawning just outside of player view
	/*	for (int i = 0; i < players.size(); i++) {
			 addX = players.get(i).x / 16;
					 addY = 54;
					 
			 tx = (players.get(i).x / 16)- 12;
					 ty = (players.get(i).y / 16) - 8;
			 otx = players.get(i).x + 12;
					 oty = (players.get(i).y / 16) + 8;
		}
		
		
		for (int j = (int)tx; j < (int)otx; j++) {
		xRad.add(j);
	}
		
		for(int i = 0; i < xRad.size();i++) {
		if (i <= 24) {
			if (xRad.get(i) != (int)addX) {
				System.out.println(":VALID SPAWN LOCATION, X: " + addX + " , I: " + i);
			} else {
				System.out.println("INVALID SPAWN LOCATION, X: " + addX + " , I: " + i);
			//	break;
			}
		}
	}
		*/
		Collections.sort(entitiesList, ySort);
		updateTest();
		
		/*for (int i = 0; i < tiles_anim.size(); i++) {
			tiles_anim.get(i).update();
		}*/

		
		if (radius <= 10) {
			radius1 = true;
			radius2 = false;
		}
		
		if (radius >= 100) {
			radius2 = true;
			radius1 = false;
		}
		if (radius1) {
			radius++;
			radius++;
		}
		if (radius2) {
			radius--;
			radius--;
		}
		
		returnTile(tile);
		//System.out.println(returnTile(tile));
		checkTile();
		
		
		
		if (this.Overworld) {
		if (night) {
			nighttime++;
		}
		if (day) {
			daytime++;
		}
		//System.out.println("[Level: 135] Brightness: " + brightness + " Day: " + day);
	      time++;
	      
	      if(time % 26 == 0) {  //ALTER 10 FOR HOW LONG IT TAKES DAY/NIGHT
	          time1();
	      }
	      
	      if(time > 1000000) {      //prevent it from going too large
	         time = 0;
	      }
		} else {
			day = true;
			night = false;
			time = 0;
			brightness = 50;
		}
		if (Overworld) {			
		for (int i = 0; i < entities.size(); i++) {
			if (this.getPlayersBool(entities.get(i), 350)) {  //TODO: Make a list of entities for entities that need to update even when the player is not within 350px
					entities.get(i).update();
				}
			}
		} else {
			for (int i = 0; i < entities.size(); i++) {
						entities.get(i).update();
				}
		}
		
		for (int i = 0; i < StatusFx.size(); i++) {
			StatusFx.get(i).update();
		}
		for (int i = 0; i < Projectiles.size(); i++) {
			Projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}
		
		
	
		/*for (int i = 0; i < HealthPot.size(); i++) {
			HealthPot.get(i).update();
		}
		for (int i = 0; i < ManaPot.size(); i++) {
			ManaPot.get(i).update();
		}*/
		for (int i = 0; i < items.size(); i++) {
			items.get(i).update();
		} 
		
	/*	for (int i = 0; i < tiles_anim.size(); i++) {
			tiles_anim.get(i).update();
		}*/
		
		Water.update();
		
		}

	

	public List<Projectile> getProjectiles() {
		return Projectiles;
	}


	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solidtwo = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size - xOffset) >> Game.TILE_BIT_SHIFT;
			int yt = (y - c / 2 * size - yOffset) >> Game.TILE_BIT_SHIFT;
			if (getTile(xt, yt).solidtwo())
				solidtwo = true;

			
		}
		return solidtwo;

	}
	
	private void renderMiniMap(Screen screen) {
		if (minimap_enabled == true && Game.getGame().gameState != Game.getGame().gameState.PAUSE) {
			int size = 45;
			int x = 255;
			int y = 0;
			
			
			if (Game.getGame().getPlayer().input.map) {
				size = 200;
				 x = 50;
				 y = 0;
			}
			screen.renderMiniMap(x, y, size);
		}
	}
	
	 /*  private void renderMiniMap(Screen screen, int width, int height, int x, int y) {
		 //  System.out.println("DRAWING");
		      screen.drawFillRect(x, y, width, height, 0x000000, false);
		      for (int i = 0; i < players.size(); i++) {
		         screen.renderMiniMap((int)players.get(i).getX(), (int)players.get(i).getY(), 0xff0000ff, width, height, x, y);
		      }
		      for (int i = 0; i < entities.size(); i++) {
		         screen.renderMiniMap((int)entities.get(i).getX(), (int)entities.get(i).getY(), 0xffff0000, width, height, x, y);
		      }
		      screen.drawRect(x, y, width, height, 0x9B9B9B, false);

		   }*/
	
	 /* private void renderMiniMap(Screen screen, int width, int height, int x, int y) {
		    screen.drawFillRect(x, y, width, height, 0x000000, false);
		    /*  for (int i = 0; i < tilesList.size(); i++) {
		         screen.renderMiniMap(tileCoord.x(), tileCoord.y(), tilesList.get(i).getColor(), width, height, x, y);
		      }
		      for (int i = 0; i < players.size(); i++) {
		         screen.renderMiniMap((int)players.get(i).getX(), (int)players.get(i).getY(), 0xffFF00FF, width, height, x, y);
		      }
		      for (int i = 0; i < entities.size(); i++) {
		         screen.renderMiniMap((int)entities.get(i).getX(), (int)entities.get(i).getY(), 0xffff0000, width, height, x, y);
		      }
		      screen.drawRect(x, y, width, height, 0x9B9B9B, false);

		   }*/
	
	  private void renderMiniMap(Screen screen, int width, int height, int x, int y) {
		  screen.drawFillRect(x, y, width, height, 0x000000, false);
     
		 // screen.renderMiniMap(x, y, SpriteSheet.maps_Spawn, width, height);
		  
      screen.drawRect(x, y, width, height, 0x9B9B9B, false);

   }
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> Game.TILE_BIT_SHIFT;
		int x1 = (xScroll + screen.width + TileCoord.TILE_SIZE) >> Game.TILE_BIT_SHIFT;
		int y0 = yScroll >> Game.TILE_BIT_SHIFT;
		int y1 = (yScroll + screen.height + TileCoord.TILE_SIZE) >> Game.TILE_BIT_SHIFT;
				for (int y = y0; y < y1; y++) {
					  for (int x =x0; x < x1; x++) {
						// renderMiniMap(screen, 32, 32, 40, 40);
					    Tile tile = getTile(x,y);
					    tile.render(x,y,screen);
					    Tile overlayTile = getOverlayTile(x,y);
					    if (overlayTile != null) overlayTile.render(x,y,screen);
					    Tile torchTiles = getTorchTile(x,y);
					    if (torchTiles != null) torchTiles.render(x,y,screen);
					    if (setTiles != null) setTiles.render(x, y, screen);

					  }
					}

		
		
		/*for (int i = 0; i < HealthPot.size(); i++) {
			HealthPot.get(i).render(screen);
				}
		for (int i = 0; i < ManaPot.size(); i++) {
			ManaPot.get(i).render(screen);
				}*/	
			/*screen.blendTiles(283 * 16, 477 * 16, tile.CobbleStone, tile.Swamp);
			screen.blendTiles(282 * 16, 477 * 16, tile.CobbleStone, tile.Swamp);*/

				for(int i = 0; i < entities.size(); i++){
					if (!entities.get(i).ySort)
			         entities.get(i).render(screen);
			      }
				
				for(int i = 0; i < entitiesList.size(); i++){
					//		if (this.getPlayersBool(entitiesList.get(i), 350)) {  //TODO: Make a list of entities for entities that need to update even when the player is not within 350px
					if (entitiesList.get(i).ySort)
			         entitiesList.get(i).render(screen);
			   //   }
				}
				for(int i = 0; i < StatusFx.size(); i++) {
					StatusFx.get(i).render(screen);
				}
				
				for(int i = 0; i < entitiesList.size(); i++){
					entitiesList.get(i).renderGUI(screen);
				}
				
				for(int i = 0; i < players.size(); i++) {
					players.get(i).renderGUI(screen);
				}
				
				
				 if (Game.getGame().getLevel().isRaining) {
					 rain.render(screen);
				 }
		//screen.renderSprite(644 * 16, 206 * 16, Sprite.robobob, true);
		 renderMiniMap(screen);

		//screen.renderLight(277 * 16, 477 * 16, 40, 20, 20, 20, "test");

			/*	screen.renderSprite(4, 4, Sprite.abilitybox, false);
				screen.renderSprite(24, 4, Sprite.abilitybox, false);
				screen.renderSprite(44, 4, Sprite.abilitybox, false);
				screen.renderSprite(60, 4, Sprite.abilitybox, false);
				screen.renderSprite(76, 4, Sprite.abilitybox, false);*/

			//	public void renderMenu(Screen screen) {
				/*if (Player.inventoryOn) {
					screen.renderSheet(85, 40, SpriteSheet.inventory1, false);
					if (Game.healthPotionsRemaining > 0) {
					screen.renderSprite(131, 62, Sprite.HealthPotion, false);
					}
				}	*/

							
		
		//renderMiniMap(screen, 32, 32, 5, 5);

		//screen.renderLight(280 * 16 - radius, 490 * 16 - radius, radius);
		//screen.renderLight(277 * 16 - radius, 477 * 16 - radius, radius, 20, 20, 20);

	
		remove();
	}
	private void remove() {
		for(int i = 0; i < entitiesList.size(); i++){
	         if(entitiesList.get(i).isRemoved()) entitiesList.remove(i);
	      }
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved())
				entities.remove(i);
		}
		for (int i = 0; i < StatusFx.size(); i++) {
			if (StatusFx.get(i).isRemoved())
				StatusFx.remove(i);
		}
		for (int i = 0; i < Projectiles.size(); i++) {
			if (Projectiles.get(i).isRemoved())
				Projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved())
				particles.remove(i);
		}
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isRemoved())
				players.remove(i);
		}
		for (int i = 0; i < Spawner.size(); i++) {
			if (Spawner.get(i).isRemoved())
			Spawner.remove(i);
		}
		/*for (int i = 0; i < HealthPot.size(); i++) {
			if (HealthPot.get(i).isRemoved())
				HealthPot.remove(i);
		}
		for (int i = 0; i < ManaPot.size(); i++) {
			if (ManaPot.get(i).isRemoved())
				ManaPot.remove(i);
		}*/
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).isRemoved())
				items.remove(i);
		}
	}

	public void add(Entity e) {
		e.init(this);
		if (e instanceof Effect) {
			StatusFx.add((Effect) e);
		} else {
			
		entitiesList.add(e);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			e.r = new Rectangle((int)e.x, (int)e.y, e.getSprite().getWidth(), e.getSprite().getHeight());
			Projectiles.add((Projectile) e);
		} else if (e instanceof Player) {
			players.add((PlayerMP) e);
		} else if (e instanceof Spawner) {
			Spawner.add((Spawner) e);
		} else if (e instanceof Item) {
			items.add((Item) e);
			System.out.println("ADDING: " + ((Item)e).name);
		} else {
			entities.add(e);
			if (e instanceof Mob) {
				try {
				e.maxhealth *= (10 / ( 1 + Math.pow(Math.E, -0.1 * (Game.getGame().getPlayer().Lvl - 40)))) + 1;
				//System.out.println(e.maxhealth);
				e.mobhealth = e.maxhealth;
				} catch (NullPointerException err) {
					
				}
			}
		}
	}
		
		//TODO: SEND PACKET TO ADD TO ALL CLIENTS
		
	}

	public List<PlayerMP> getPlayers() {
		return players;

	}

	public Player getPlayerAt(int index) {
		return players.get(index);
	}
	
	public Entity getEntityAt(int index) {
		return entities.get(index);
	}

	public Player getClientPlayer() {
		return players.get(0);
	}
	
	
	public List<Entity> AOEFull(Screen screen, int x, int y, int rad, boolean render, int color) {
		List<Entity> result;
		if (render) {
			drawAOE(screen, x, y, rad, color);
		}

		x >>= 4;
		y >>= 4;

		result = getEntitiesListFixed(x, y, rad);
		return result;
	}
	
	
	public void drawAOE(Screen screen, int x, int y, int rad, int color) {
		x >>= 4;
		y >>= 4;
		
		screen.drawCir(x, y, rad, color, true);

	}
	
	public List<Vector2i> BresenhamLine(int x1, int y1, int x2, int y2){
		   List<Vector2i> result = new ArrayList<Vector2i>();
		   int dy = y2 - y1;
		   int dx = x2 - x1;
		   int xs, ys;
		   
		   if(dy < 0) {dy = -dy; ys = -1;} else { ys = 1;}
		   if(dx < 0) {dx = -dx; xs = -1;} else { xs = 1;}
		   dy <<= 1;
		   dx <<= 1;
		   
		   result.add(new Vector2i(x1, y1));
		   if(dx > dy){
		      int fraction = dy - (dx >> 1);
		      while(x1 != x2){
		         if(fraction >= 0){
		            y1 += ys;
		            fraction -= dx;
		         }
		         x1 += xs;
		         fraction += dy;
		         result.add(new Vector2i(x1, y1));
		      }
		   }else{
		      int fraction = dx - (dy >> 1);
		      while(y1 != y2){
		         if(fraction >= 0){
		            x1 += xs;
		            fraction -= dy;
		         }
		         y1 += ys;
		         fraction += dx;
		         result.add(new Vector2i(x1, y1));
		      }
		   }
		   return result;
		}
	
	public RayCast RayCast(Vector2i pos, double angle, float rayLength){
		   RayCast result = new RayCast();
		   result.setCollided(false);
		   if(rayLength <= 0){
		      result.setCollided(this.getTile(pos.getX()>>4, pos.getY()>>4).solid());
		      result.setPosition(pos);
		      return result;
		   }
		   double adjacent = pos.getX()+rayLength*Math.cos(angle);
		   double opposite = pos.getY()+rayLength*Math.sin(angle);
		   List<Vector2i> rayLine = BresenhamLine(pos.getX(), pos.getY(), (int)adjacent, (int)opposite);
		   if(!rayLine.isEmpty()){
		      for(int rayVectorIndex = 0;rayVectorIndex < rayLine.size();rayVectorIndex++){
		         Vector2i rayVector = rayLine.get(rayVectorIndex);
		         result.rayVector = rayVector;
		         if(this.getTile(rayVector.getX()>>4, rayVector.getY()>>4).solid()){
		            result.setPosition(rayVector);
		            result.setCollided(true);
		            break;
		         }
		      }
		   }
		   return result;
		}
	
	public List<Node> findPath(Vector2i start, Vector2i goal) {
		this.start = start;
		this.goal = goal;
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, getDistance(start, goal));
		openList.add(current);
		while (openList.size() > 0) {
			current = openList.get(0);
			Collections.sort(openList, nodeSorter);
			if (current.tile.equals(goal)) {
				List<Node> path = new ArrayList<Node>();
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {
				if (i == 4)
					continue;
				int x = current.tile.getX();
				int y = current.tile.getY();
				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;
				Tile at = getTile(x + xi, y + yi);
				if (at == null)
					continue;
				if (at.solid())
					continue;
				Vector2i a = new Vector2i(x + xi, y + yi);
				double gCost = current.gCost + (getDistance(current.tile, a)/* == 1 ? 1 : 1*/);
				double hCost = getDistance(a, goal);
				Node node = new Node(a, current, gCost, hCost);
				if (vecInList(closedList, a) && gCost >= node.gCost)
					continue;
				if (!vecInList(openList, a) || gCost < node.gCost)
					openList.add(node);

			}
		}
		closedList.clear();
		return null;
	}

	private boolean vecInList(List<Node> list, Vector2i vector) {
		for (Node n : list) {
			if (n.tile.equals(vector))
				return true;
		}
		return false;
	}

	private double getDistance(Vector2i tile, Vector2i goal) {
		double dx = tile.getX() - goal.getX();
		double dy = tile.getY() - goal.getY();
		
		return Math.sqrt(dx * dx + dy * dy);
	}

	/*public void addTile(AnimatedTile t) {
		tiles_anim.add(t);
	}*/
	
	
	public List<Entity> getEntities(Entity e, int radius, HOSTILITY host) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).hostility == host) {
			Entity entity = entities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius)
				result.add(entity);
				}
		}
		return result;
	}
	
	public List<Entity> getEntities(Entity e, int radius, HOSTILITY host, HOSTILITY host2) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).hostility == host || entities.get(i).hostility == host2) {
			Entity entity = entities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius)
				result.add(entity);
				}
		}
		return result;
	}
	
	public List<Entity> getEntities(Entity e, int radius, List<Entity> entities) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius)
				result.add(entity);
		}
		return result;
	}
	
	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius)
				result.add(entity);
		}
		return result;
	}
	
	public List<Item> getItems(Entity e, int radius) {
		List<Item> result = new ArrayList<Item>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < items.size(); i++) {
			Item items = this.items.get(i);
			int x = (int) items.getX();
			int y = (int) items.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius)
				result.add(items);
		}
		return result;
	}
	
	public List<Item> getItemsFixed(int xx, int yy, int radius) {
		List<Item> result = new ArrayList<Item>();
		int ex = xx;
		int ey = yy;
		for (int i = 0; i < items.size(); i++) {
			Item items = this.items.get(i);
			int x = (int) items.getX();
			int y = (int) items.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius)
				result.add(items);
		}
		return result;
	}
	
	public List<Entity> getEntitiesFixed(int xx, int yy, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = xx;
		int ey = yy;
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius)
				result.add(entity);
		}
		return result;
	}
	
	public List<Entity> getEntities(int x, int y, int radius)  {
		List<Entity> result = new ArrayList<Entity>();
		int ex = x;
		int ey = y;
		for (Entity e : entities) {
			int xx = (int) e.getX();
			int yy = (int) e.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius)
				result.add(e);
		}
		return result;
	}
	
	public List<Entity> getEntitiesListFixed(int xx, int yy, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = xx;
		int ey = yy;
		for (int i = 0; i < entitiesList.size(); i++) {
			Entity entity = entitiesList.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius)
				result.add(entity);
		}
		return result;
	}

	public List<Player> getPlayers(Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int x = (int) player.getX();
			int y = (int) player.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius && player.setInvisible == false)
				result.add(player);
		}
		return result;
	}
	
	public List<PlayerMP> getPlayers(Entity e, int radius, boolean no) {
		List<PlayerMP> result = new ArrayList<PlayerMP>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < players.size(); i++) {
			PlayerMP player = players.get(i);
			int x = (int) player.getX();
			int y = (int) player.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius && player.setInvisible == false)
				result.add(player);
		}
		return result;
	}
	
	
	public boolean getPlayersFixedBool(int xx, int yy, int radius) {
		boolean there = false;
		int ex = (int) xx;
		int ey = (int) yy;
		for (int i = 0; i < players.size(); i++) {
			PlayerMP player = players.get(i);
			int x = (int) player.getX();
			int y = (int) player.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) {
				there = true;
		} else {
			there = false;
		}
		}
			return there;
	}

	public boolean getPlayersBool(Entity e, int radius) {
		boolean there = false;
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < players.size(); i++) {
			PlayerMP player = players.get(i);
			int x = (int) player.getX();
			int y = (int) player.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) {
				there = true;
		} else {
			there = false;
		}
		}
			return there;
	}
	
	public List<PlayerMP> getPlayersFixed(int xx, int yy, int radius) {
		List<PlayerMP> result = new ArrayList<PlayerMP>();
		int ex = (int) xx;
		int ey = (int) yy;
		for (int i = 0; i < players.size(); i++) {
			PlayerMP player = players.get(i);
			int x = (int) player.getX();
			int y = (int) player.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius)
				result.add(player);
		}
		return result;
	}
	
	/*public void addTile(AnimatedTile e) {
		tiles_anim.add(e);
	}*/
	

	public Tile getTile(int x, int y) {				
		
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.VoidTile;
		
		if (tiles[x + y * width] == bitBrickHex)
			   return Tile.bitBrick;
			if (tiles[x + y * width] == bitMetalHex)
			   return Tile.bitMetal;
			if (tiles[x + y * width] == BluefogHex)
			   return Tile.Bluefog;
			if (tiles[x + y * width] == BookshelfBottomHex)
			   return Tile.BookshelfBottom;
			if (tiles[x + y * width] == BookshelfTopHex)
			   return Tile.BookshelfTop;
			if (tiles[x + y * width] == BrickCeilingHex)
			   return Tile.BrickCeiling;
			if (tiles[x + y * width] == BrickWallHex)
			   return Tile.BrickWall;
			if (tiles[x + y * width] == CaveCeilingHex)
			   return Tile.CaveCeiling;
			if (tiles[x + y * width] == CaveWallHex)
			   return Tile.CaveWall;
			if (tiles[x + y * width] == CobbleStoneHex)
			   return Tile.CobbleStone;
			if (tiles[x + y * width] == CobblestoneCeilingHex)
			   return Tile.CobblestoneCeiling;
			if (tiles[x + y * width] == CobbleStoneWallHex)
			   return Tile.CobbleStoneWall;
			if (tiles[x + y * width] == CrackedBrickHex)
			   return Tile.CrackedBrick;
			if (tiles[x + y * width] == DarkStoneHex)
			   return Tile.DarkStone;
			if (tiles[x + y * width] == DeepLavaHex)
			   return Tile.DeepLava;
			if (tiles[x + y * width] == DeepWaterHex)
			   return Tile.DeepWater;
			if (tiles[x + y * width] == DirtHex)
			   return Tile.Dirt;
			if (tiles[x + y * width] == DirtCeilingHex)
			   return Tile.DirtCeiling;
			if (tiles[x + y * width] == DirtWallHex)
			   return Tile.DirtWall;
			if (tiles[x + y * width] == DresserBottomHex)
			   return Tile.DresserBottom;
			if (tiles[x + y * width] == DresserTopHex)
			   return Tile.DresserTop;
			if (tiles[x + y * width] == GrassHex)
			   return Tile.Grass;
			if (tiles[x + y * width] == HellBrickHex)
			   return Tile.HellBrick;
			if (tiles[x + y * width] == HellBrickCeilingHex)
			   return Tile.HellBrickCeiling;
			if (tiles[x + y * width] == HellbrickWallHex)
			   return Tile.HellbrickWall;
			if (tiles[x + y * width] == HellCaveCeilingHex)
			   return Tile.HellCaveCeiling;
			if (tiles[x + y * width] == HellCaveWallHex)
			   return Tile.HellCaveWall;
			if (tiles[x + y * width] == HellsandHex)
			   return Tile.Hellsand;
			if (tiles[x + y * width] == HellSandCeilingHex)
			   return Tile.HellSandCeiling;
			if (tiles[x + y * width] == HellSandWallHex)
			   return Tile.HellSandWall;
			if (tiles[x + y * width] == HellstoneHex)
			   return Tile.Hellstone;
			if (tiles[x + y * width] == IceHex)
			   return Tile.Ice;
			if (tiles[x + y * width] == IceBrickHex)
			   return Tile.IceBrick;
			if (tiles[x + y * width] == IceBrickCeilingHex)
			   return Tile.IceBrickCeiling;
			if (tiles[x + y * width] == IceBrickWallHex)
			   return Tile.IceBrickWall;
			if (tiles[x + y * width] == IceCaveCeilingHex)
			   return Tile.IceCaveCeiling;
			if (tiles[x + y * width] == IceCaveWallHex)
			   return Tile.IceCaveWall;
			if (tiles[x + y * width] == IceSandHex)
			   return Tile.IceSand;
			if (tiles[x + y * width] == IceSandCeilingHex)
			   return Tile.IceSandCeiling;
			if (tiles[x + y * width] == IceSandWallHex)
			   return Tile.IceSandWall;
			if (tiles[x + y * width] == LavaHex)
			   return Tile.Lava;
			if (tiles[x + y * width] == MetalHex)
			   return Tile.Metal;
			if (tiles[x + y * width] == MetalCeilingHex)
			   return Tile.MetalCeiling;
			if (tiles[x + y * width] == MetalWallHex)
			   return Tile.MetalWall;
			if (tiles[x + y * width] == MossCeilingHex)
			   return Tile.MossCeiling;
			if (tiles[x + y * width] == MossWallHex)
			   return Tile.MossWall;
			if (tiles[x + y * width] == ObsidianCeilingHex)
			   return Tile.ObsidianCeiling;
			if (tiles[x + y * width] == ObsidianWallHex)
			   return Tile.ObsidianWall;
			if (tiles[x + y * width] == PathDirtHex)
			   return Tile.PathDirt;
			if (tiles[x + y * width] == SandHex)
			   return Tile.Sand;
			if (tiles[x + y * width] == SandBrickHex)
			   return Tile.SandBrick;
			if (tiles[x + y * width] == SandBrickCeilingHex)
			   return Tile.SandBrickCeiling;
			if (tiles[x + y * width] == SandBrickWallHex)
			   return Tile.SandBrickWall;
			if (tiles[x + y * width] == SandCeilingHex)
			   return Tile.SandCeiling;
			if (tiles[x + y * width] == SandWallHex)
			   return Tile.SandWall;
			if (tiles[x + y * width] == SnowHex)
			   return Tile.Snow;
			if (tiles[x + y * width] == StoneBrickHex)
			   return Tile.StoneBrick;
			if (tiles[x + y * width] == StoneBrickCeilingHex)
			   return Tile.StoneBrickCeiling;
			if (tiles[x + y * width] == StoneBrickWallHex)
			   return Tile.StoneBrickWall;
			if (tiles[x + y * width] == SwampHex)
			   return Tile.Swamp;
			if (tiles[x + y * width] == VoidTileHex)
			   return Tile.VoidTile;
			if (tiles[x + y * width] == WaterHex)
			   return Tile.Water;
			if (tiles[x + y * width] == WoodHex)
			   return Tile.Wood;
			if (tiles[x + y * width] == WoodCeilingHex)
			   return Tile.WoodCeiling;
			if (tiles[x + y * width] == WoodWallHex)
			   return Tile.WoodWall;
			if (tiles[x + y * width] == SwirlyHex)
			   return Tile.swirly;

			
			
		return Tile.VoidTile;

	}
	
	public Tile getOverlayTile(int x, int y) {
		  if (x < 0 || y < 0 || x >= width || y >= height) return null;
		//  if (overlayTiles != null) {
		    if (overlayTiles[x + y * width] == Cactus)
			   return Tile.Cactus;
			if (overlayTiles[x + y * width] == FlowerCactus)
			   return Tile.FlowerCactus;
			if (overlayTiles[x + y * width] == ColoredFlowers)
			   return Tile.ColoredFlowers;
			if (overlayTiles[x + y * width] == YellowFlowers) 
			   return Tile.YellowFlowers;
			if (overlayTiles[x + y * width] == Pebble)
			   return Tile.Pebble;
			if (overlayTiles[x + y * width] == BlueMushroom)
			   return Tile.BlueMushroom;
			if (overlayTiles[x + y * width] == BlueMushroomDirt)
			   return Tile.BlueMushroomDirt;
			if (overlayTiles[x + y * width] == RedMushroom)
			   return Tile.RedMushroom;
			if (overlayTiles[x + y * width] == RedMushroomDirt)
			   return Tile.RedMushroomDirt;
			if (overlayTiles[x + y * width] == GreenMushroom)
			   return Tile.GreenMushroom;
			if (overlayTiles[x + y * width] == GreenMushroomDirt)
			   return Tile.GreenMushroomDirt;
			if (overlayTiles[x + y * width] == DirtPatch)
			   return Tile.DirtPatch;
			if (overlayTiles[x + y * width] == DarkCastle)
			   return Tile.DarkCastle;
			if (overlayTiles[x + y * width] == Castle)
			   return Tile.Castle;
			if (overlayTiles[x + y * width] == Village)
			   return Tile.Village;
			if (overlayTiles[x + y * width] == Cave)
			   return Tile.Cave;
			if (overlayTiles[x + y * width] == Portal)
			   return Tile.Portal;
			if (overlayTiles[x + y * width] == PathVertical)
			   return Tile.PathVertical;
			if (overlayTiles[x + y * width] == PathHorizontal)
			   return Tile.PathHorizontal;
			if (overlayTiles[x + y * width] == PathCross)
			   return Tile.PathCross;
			if (overlayTiles[x + y * width] == PathCornerTL)
			   return Tile.PathCornerTL;
			if (overlayTiles[x + y * width] == PathCornerTR)
			   return Tile.PathCornerTR;
			if (overlayTiles[x + y * width] == PathCornerBL)
			   return Tile.PathCornerBL;
			if (overlayTiles[x + y * width] == PathCornerBR)
			   return Tile.PathCornerBR;
			if (overlayTiles[x + y * width] == PathEndLeft)
			   return Tile.PathEndLeft;
			if (overlayTiles[x + y * width] == PathEndRight)
			   return Tile.PathEndRight;
			if (overlayTiles[x + y * width] == PathEndTop)
			   return Tile.PathEndTop;
			if (overlayTiles[x + y * width] == PathEndBottom)
			   return Tile.PathEndBottom;
			if (overlayTiles[x + y * width] == BrokenSword)
			   return Tile.BrokenSword;
			if (overlayTiles[x + y * width] == RedBed)
			   return Tile.RedBed;
			if (overlayTiles[x + y * width] == BlueBed)
			   return Tile.BlueBed;
			if (overlayTiles[x + y * width] == GreenBed)
			   return Tile.GreenBed;
			if (overlayTiles[x + y * width] == OrangeBed)
			   return Tile.OrangeBed;
			if (overlayTiles[x + y * width] == TopChair)
			   return Tile.TopChair;
			if (overlayTiles[x + y * width] == BottomChair)
			   return Tile.BottomChair;
			if (overlayTiles[x + y * width] == LeftChair) return Tile.LeftChair;
			if (overlayTiles[x + y * width] == RightChair) return Tile.RightChair;
			if (overlayTiles[x + y * width] == Table) return Tile.Table;
			if (overlayTiles[x + y * width] == AnvilHex) return Tile.Anvil;
			if (overlayTiles[x + y * width] == StoveHex) return Tile.Stove;
			if (overlayTiles[x + y * width] == Skull) return Tile.skull;
			if (overlayTiles[x + y * width] == CrossedBones) return Tile.crossbone;
			if (overlayTiles[x + y * width] == Bone) return Tile.bone;
			if (overlayTiles[x + y * width] == DoorHex) return Tile.DoorTile;
			if (overlayTiles[x + y * width] == 0) return Tile.crossbone;

		  return null;
	//}
		//return null;
	}
	
	public void resetLevel(Player player) {
		
	}
	
public void resetLevelPostDeath(Player player) {
	Game.getGame().setLevel(new SpawnHaven(Maps.SpawnHaven));
	add(player);
	player.setPosition(new TileCoord(52, 78));
	Level.Overworld = false;
	Level.minimap_enabled = false;
	
	}

	public Tile getTorchTile(int x, int y) {
		  if (x < 0 || y < 0 || x >= width || y >= height) return null;
		//  if (torchTiles != null) {
		  if (torchTiles[x + y * width] == Torch) 
			  return Tile.TorchTile;
		  else {
			  return null;
		  }
		//  }
		//return null;
	}
	public boolean getTile(double x, double y, Tile tile) {
		return false;
	}


	
	public void checkExits(Player player, Level level, int x, int y) {
	}

	protected static java.util.Random Random = new Random();
	public static java.util.Random random = Random;
	
	public void damage(int x, int y, Mob mob, long Exp, double damage, String name, int ExpV) {
		int chance  = (random.nextInt((101 - 1) + 1) + 1);
		
		double critMult = 2;
		int color;
		String dmgInd = "0";
		
		if(chance <= (getClientPlayer().stat_AGI / 3)) {
			damage *= critMult;
			dmgInd = "" + Math.round(damage) + "!";
		} else {
			dmgInd = "" + Math.round(damage);
		}
		
        if (!mob.invulnerable) {
        		mob.mobhealth -= (damage);        		
        		mob.hurt = true;
		try {
			add(new DamageIndicator((int)(mob.getX() - mob.getSprite().getWidth() / 2), (int)((y - (mob.getSprite().getHeight() * 1.5))), 15, 1, dmgInd, 0xffDD0011));
		} catch (Exception e) {
			e.printStackTrace();
		}
      //  mob.getSprite().pixels[mob.getSprite().getWidth() * mob.getSprite().getHeight()] = MyColor.changeBrightness(0, 60, false);
        mob.incombat = true;
        if (mob.mobhealth <= 0){
        	for(int i = 0; i < mob.effects.effects.length; i++) {
        		mob.effects.removeEffect(mob.effects.effects[i]);
        	}
        	mob.death();
        	mob.remove();
        	
    					if (Game.Dead == false && Game.getGame().gameState != gameState.INGAME_A) {
    						Game.getGame().getLevel().getClientPlayer().kills += 1;
	        				Game.getGame().getLevel().getClientPlayer().ExpC += Exp + ExpV;
	        				if (mob.inventory != null) mob.inventory.dropAll();
	        				}
    						if (mob.hostility != mob.hostility.PASS) {
    					Game.getGame().getPlayer().money += (mob.maxhealth / 2);
	    				}
        }
					}
	}
	
	public void damagePlayer(int x, int y, PlayerMP player, long Exp, double damage, String name, int ExpV) {
		int color;
		int chance  = (random.nextInt((101 - 1) + 1) + 1);
		String dmgInd = "0";
		damage -= (player.stat_DEF * 1/4);
		
		if (damage < 1) {
			damage = 1;
		}
		
		if(chance <= (getClientPlayer().stat_AGI / 5)) {
			damage = 0;
			dmgInd = "*Dodged*";
		} else {
			dmgInd = "" + Math.round(damage);
		}
		
		System.out.println("Damage: " + damage);
        if (!player.invulnerable) {
        	if (Game.getGame().gameState != gameState.INGAME_A) {        		
        	player.mobhealth -= (damage);
        	}
		try {
			//add(new DamageIndicator((int)(player.getX()), (int)((y - 20)), 15, 1, dmgInd, 0xffDD0011));
		} catch (Exception e) {
			e.printStackTrace();
		}
      //  mob.getSprite().pixels[mob.getSprite().getWidth() * mob.getSprite().getHeight()] = MyColor.changeBrightness(0, 60, false);
		player.incombat = true;
        if (player.mobhealth <= 0){
        	//player.remove();
        	player.dead = true;
        }
        }
	}
	
	protected void refresh() {
		for (int i = 0; i < entities.size(); i++) {
			entities.remove(i);
			entities.clear();
			
			//tiles_anim.clear();
		}
	}

	public List<Entity> getEntities() {
		return entities;
	}

	   public void removePlayerMP(String UUID) {
	     System.out.println("REMOVING PLAYER... " + UUID);
		   int index = 0;
	        for (Player e : players) {
	            if (e instanceof PlayerMP && ((PlayerMP) e).getUUID().equals(UUID)) {
	                break;
	            }
	            index++;
	        }
	        System.out.println("PLAYER REMOVED: " + players.get(index).getUsername());
	        players.get(index).remove();
	   }
	
	
	private int getPlayerMPIndex(String username) {
		int index = 0;
		for(Player e : players) {
			if (e instanceof PlayerMP && ((PlayerMP)e).getUsername().equals(username)) {
				break;
			}
			index++;
		}
		return index;
	}
	
	private int getPlayerMPIndexUUID(String UUID) {
		int index = 0;
		for(Player e : players) {
			if (e instanceof PlayerMP && ((PlayerMP)e).getUUID().equals(UUID)) {
				break;
			}
			index++;
		}
		return index;
	}


	
	public void movePlayer(String UUID, double x, double y, boolean walking, DIRECTION direction, int lvl, double mobhealth) {
		int index = getPlayerMPIndexUUID(UUID);
		PlayerMP player = (PlayerMP) this.players.get(index);
		
		player.x = x;
		player.y = y;
		player.walking = walking;
		player.setDir(direction);
		
		if (player.Lvl != lvl) {
			player.Lvl = lvl;
		}
		player.mobhealth = mobhealth;
		}


	public String getEntityByID(int id) {
		String entityName = "";
		
		switch(id) {
		
		case 0: entityName = "ALICE";
		break;
		
		case 5: entityName = "SLIME";
		break;
		
		case 6: entityName = "SLIMEY";
		break;
		
		case 7: entityName = "UNDCAST";
		break;
		
		case 10: entityName = "ZOMB";
		break;
		
		}
		return entityName;
	}
	
	public String getProjByID(int id) { 
		String proj = "-1";
		
		switch(id) {
		case -1: proj = "WISBOLT";
		break;
		case 0: proj = "ALICE";
		break;
		case 1: proj = "ALICE";
		break;
		case 2: proj = "ALICE";
		break;
		case 3: proj = "ALICE";
		break;
		case 4: proj = "WISBOLT";
		break;
		case 5: proj = "SLIME";
		break;
		case 6: proj = "SLIMEY";
		break;
		case 7: proj = "UNDCAST";
		break;
		case 8: proj = "GLDORB";
		break;
		case 9: proj = "ALICE";
		break;
		case 10: proj = "RDBLAST";
		break;
		case 11: proj = "SRBOLT";
		break;
		
		}
		return proj;
	}
	
	public void moveProjectile(String UUID, double angle, int id) {
		int index = getPlayerMPIndexUUID(UUID);
		PlayerMP player = (PlayerMP) this.players.get(index);
		
		if(getProjByID(id).equals("WISBOLT"))  {
			player.shoot(player.x, player.y, angle);
		}
		
		if(getProjByID(id).equals("GLDORB"))  {
			player.GoldenOrb(player.x, player.y, angle);
		}
		
		if(getProjByID(id).equals("RDBLAST"))  {
			player.RadialBlast(player.x, player.y, angle);
		}
		
		if(getProjByID(id).equals("SRBOLT"))  {
			player.PierceSpell(player.x, player.y, angle);
		}
	}

	public void MPTeleport(String tp) {
		try {
		int index = getPlayerMPIndex(tp);
		PlayerMP player = (PlayerMP) this.players.get(index);
		
		getClientPlayer().setX((int)player.x);
		getClientPlayer().setY((int)player.y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

public void addMultiplayerEntity(int id, int x, int y, int index, String UUID) {
		if (getEntityByID(id).equals("-1")) {
			System.out.println("Skipping Unknown Entity");
		}
		if (getEntityByID(id).equals("ALICE")) {
			entities.get(index).remove();
			this.entities.add(index, new Alice(x, y));
			entities.get(index).setUUID(UUID);
		}
		if (getEntityByID(id).equals("UNDCAST")) {
			entities.get(index).remove();
			this.entities.add(index, new UndeadCaster(x, y));
			entities.get(index).setUUID(UUID);
		}
		if (getEntityByID(id).equals("ZOMB")) {
			entities.get(index).remove();
			this.entities.add(index, new Zombie(x, y));
			entities.get(index).setUUID(UUID);
		}
		if (getEntityByID(id).equals("SLIME")) {
			entities.get(index).remove();
			this.entities.add(index, new Slime(x, y));
			entities.get(index).setUUID(UUID);
		}
		if (getEntityByID(id).equals("SLIMEY")) {
			entities.get(index).remove();
			this.entities.add(index, new Slimey(x, y));
			entities.get(index).setUUID(UUID);
		}
		System.out.println("ENTITIES NEW: " + entities.size());
	}


	public void addInvById(Inventory inv, int id) {
		if (id == -1) {
			System.out.println("No (Valid) Weapon is Equipped!");
		}else if (id == 12) {
			inv.add(new wand_Pulsefire(EquipableItem.slot_WEAPON));
		}else if (id == 13) {
			inv.add(new wand_VoidCrook(EquipableItem.slot_WEAPON));
		}else if (id == 14) {
			inv.add(new wand_FlareScepter(EquipableItem.slot_WEAPON));
		} else if (id == 15) {
			inv.add(new wand_StygianScepter(EquipableItem.slot_WEAPON));
		} else if (id == 16) {
			inv.add(new wand_ContradictionWand(EquipableItem.slot_WEAPON));

		}
	}
	
	public void addEquipsById(Equipment equip, int id) {
		if (id == -1) {
			System.out.println("No (Valid) Weapon is Equipped!");
		}else if (id == 12) {
			equip.Equip(new wand_Pulsefire(EquipableItem.slot_WEAPON));
		}else if (id == 13) {
			equip.Equip(new wand_VoidCrook(EquipableItem.slot_WEAPON));
		}else if (id == 14) {
			equip.Equip(new wand_FlareScepter(EquipableItem.slot_WEAPON));
		} else if (id == 15) {
			equip.Equip(new wand_StygianScepter(EquipableItem.slot_WEAPON));
		} else if (id == 16) {
			equip.Equip(new wand_ContradictionWand(EquipableItem.slot_WEAPON));

		}
	}

	public void addLevelById(int id) {
	
	}
	
	public void addDoorTile(int x, int y) {
		try {			
		overlayTiles[x + y * width] = 0xff000000;
		tiles[x + y * width] = SwirlyHex;

		} catch (Exception e) {
			
		}
	}	
	
}
