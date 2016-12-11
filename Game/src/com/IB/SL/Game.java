package com.IB.SL;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.IB.SL.entity.Entity;
import com.IB.SL.entity.mob.Player;
import com.IB.SL.entity.mob.PlayerMP;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.font;
import com.IB.SL.graphics.font8x8;
import com.IB.SL.graphics.UI.GUI;
import com.IB.SL.input.Keyboard;
import com.IB.SL.input.Mouse;
import com.IB.SL.level.Level;
import com.IB.SL.level.TileCoord;
import com.IB.SL.level.tile.Tile;
import com.IB.SL.level.worlds.Maps;
import com.IB.SL.level.worlds.SpawnHaven;
import com.IB.SL.util.LoadProperties;
import com.IB.SL.util.SaveGame;
import com.IB.SL.util.Sound;

@SuppressWarnings("static-access")

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;	
	
	public Tile tile;
	public GUI gui;
	private Thread thread;
	public static JFrame frame; 
	public Keyboard key;
	public transient font font;
	
	public static int width = 300; // 300 //520
	public static int height = 168; // 168 //335
	public static int scale = 4;
	public static final int TILE_BIT_SHIFT = 4;


	public static String title = "Square Legacy PR: 0.6 | S: 3 | BUILD: 104";// [NEXT UPDATE TO 4]
	public double xScroll, yScroll;
	
	public static Level level;
	public static Level level2;
	private Player player;
	public int frames = 0;
	public static int mouseMotionTime = 0;
	private boolean invokedLoad = false;

	public boolean autoSave = true;

	public static boolean switchedTo3;
	private boolean running = false;
	public static boolean loading = false; 
	public transient font8x8 font8x8;
	public static int currentLevelId;
	private static boolean blankBoolean = false;
	public static String PersonNameGetter = "Player";
	public static boolean Dead = false;
	public static boolean showAVG;
	public static boolean recAVG_FPS = false;
	
	public int ClickTime = 0;
	public int time2 = 0;
	boolean playingHope = false;
	public boolean atMenu = true;
	static String version = "";
	public static boolean devModeOn = false;
	private boolean devModeReleased = true;
	public LoadProperties loadProp;
	public static boolean devModeInfoOn = false;
	public static boolean loadGame = true;
	int loadGameTime = 0;
	public String titleTampered = title + " | [Modded] ";
	public int loadTime = 0;
	TileCoord playerSpawn;
   private ArrayList<String> devs = new ArrayList<String>();
	File screenshots = null;
	public static boolean runTut = false;

	int saveTime = 0;
	private int warningTime = 0;
	/**
	 * 0 = stop; 1 = menu; 2 = [m]Protocol: (in-game); 3 = [a]Protocol:
	 * (in-game); 4 = pause; 5 = modded/tampered; 6 = dead; 7 = Splash;
	 */
	public enum gameState {
		STOP, MENU, INGAME, INGAME_A, PAUSE, MODDED, DEATH, SPLASH;
	}
	
	public HashMap<String, Boolean> properties = new HashMap<String, Boolean>();

	private boolean releasedDevInfo = true;
		
	private Screen screen;
	public WindowHandler windowHandler;
	private BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();

	@SuppressWarnings("unused")
	private int time = 0;

	public gameState gameState;
	public int ExpStor;
	public boolean developerPlaying;
	private int developerTime;

	private void folderCreation() {
		File SavesFolder = new File(LoadProperties.basePath + "/Saves");

		if (SavesFolder.exists()) {
			System.out.println("File: " + SavesFolder
					+ " exists, not creating a new directory");
		}
		if (!SavesFolder.exists()) {
			System.out.println("Creating Directory: " + SavesFolder);

			boolean result = SavesFolder.mkdir();
			if (result) {
				System.out.println(SavesFolder + " Created Successfully");
			}

		}

		if (screenshots.exists()) {
			System.out.println("File: " + screenshots
					+ " exists, not creating a new directory");
		}
		if (!screenshots.exists()) {
			System.out.println("Creating Directory: " + screenshots.getAbsolutePath());

			boolean result = screenshots.mkdir();
			if (result) {
				System.out.println(screenshots.getAbsolutePath() + " Created Successfully");
			}
		}
	}

	/*
	 * void WriteVersion() throws IOException { PrintWriter writer; try { writer
	 * = new PrintWriter( Game.basePath + "/Saves/version.dat", "UTF-8");
	 * writer.println(version); writer.close(); } catch (FileNotFoundException |
	 * UnsupportedEncodingException e) { e.printStackTrace(); } }
	 */

	/*
	 * private void VersionFile() throws IOException { File xyFile = new
	 * File(basePath + "/Saves/" + "version.dat"); if (!xyFile.exists()) {
	 * xyFile.createNewFile(); try(PrintWriter out = new PrintWriter(new
	 * BufferedWriter(new FileWriter(xyFile, false)))) { out.println(0); }catch
	 * (IOException e) { } } DataInputStream xyStream = new DataInputStream(new
	 * FileInputStream(xyFile)); String xyStored = xyStream.readLine();
	 * System.out.println(xyStored); Double.parseDouble(xyStored); /* if
	 * (xyStored.contains("/")) { String xStored = xyStored.substring(0
	 * ,xyStored.indexOf("/")); //System.out.println(xStored); String yStored =
	 * xyStored.replace(xStored + "/", ""); //System.out.println(yStored);
	 * 
	 * int xStoredInt = Integer.parseInt(xStored); int yStoredInt =
	 * Integer.parseInt(yStored);
	 * 
	 * //System.out.println(xStoredInt / 16 + " / " + yStoredInt / 16);
	 * //System.out.println(players.get(i).getX() + " / " +
	 * players.get(i).getY()); /*players.get(i).setX(xStoredInt);
	 * players.get(i).setY(yStoredInt); } }
	 */

/*	public void readVersion() {
		try {
			URL url = new URL(
					"jar:file:/" + loadProp.basePath + "!/version.txt");
			InputStream is = url.openStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			System.out.println(reader.readLine());
			version = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	
	public void addDevelopers() {
		devs.add("Nate");
		devs.add("coinreturn1");
		
		devs.add("Pascal");
		devs.add("Nickavia");
		
		devs.add("Robbie");
		devs.add("GeekSquad2");
		
		devs.add("*[A]7381");
		devs.add("AggressiveChairlift");

		devs.add("Varspasian");

	}
	

	public Game() {
		//load();
		addDevelopers();
		
		loadProp = new LoadProperties();
		loadProp.createDataFolder();
		screenshots = new File(LoadProperties.basePath + "/screenshots");
		gameState = gameState.SPLASH;
		Sound.loadOggs();
		//readVersion();
		/*
		 * try { WriteVersion(); } catch (IOException e2) {
		 * e2.printStackTrace(); }
		 * 
		 * try { VersionFile(); } catch (IOException e1) { e1.printStackTrace();
		 * }
		 */
		folderCreation();

		/**
		 * C:\ProgramData\SquareLegacy
		 */
		
	
		setGui(new GUI());
		getLauncherName();
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		screen = new Screen(width, height);
		frame = new JFrame();
		windowHandler = new WindowHandler(this);
		key = new Keyboard();

			setLevel(new SpawnHaven(Maps.SpawnHaven));
			playerSpawn = new TileCoord(52, 72);

			level2 = level;
		
		// TileCoord playerSpawn = new TileCoord(296, 381);
		setPlayer(new PlayerMP(playerSpawn.x(), playerSpawn.y(), key, PersonNameGetter, Entity.genUUID(), null, -1));
		level.add(getPlayer());
		addKeyListener(key);
		Mouse mouse = new Mouse();
		font = new font();
		font8x8 = new com.IB.SL.graphics.font8x8();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addMouseWheelListener(mouse);
	    
		if(devs.size() > 0) {
			for(int i = 0; i < devs.size(); i++) {
				if (devs.get(i) != null) {
						
				if (devs.get(i).toString().equalsIgnoreCase(getPlayer().getUsername())) {
					System.out.println("Welcome Developer, " + devs.get(i).toString());
					System.out.println("Enabling Square Legacy MultiPlayer features...");
					this.developerPlaying = true;
				}


				}}
		}
		//load();
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	static Level currentLevel() {
		return level2;
	}
	private void getLauncherName() {
	File launcherName = new File(LoadProperties.basePath + "/LoginData.dat");
	try {
		@SuppressWarnings("resource")
		DataInputStream MyInStream = new DataInputStream(
				new FileInputStream(launcherName));
		PersonNameGetter = MyInStream.readLine();
		gui.saveSelected = PersonNameGetter;
		//load(); 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	public void captureScreen(JFrame currentFrame, String fileName) throws AWTException {
		List<PlayerMP> players = level.players;
		System.out.println("Saved Screenshot as: " + fileName + "_" + System.currentTimeMillis() + ".png");
		Robot robot = new Robot();
		Rectangle capRectange = currentFrame.getBounds();
		BufferedImage exportImage = robot.createScreenCapture(capRectange);
		try {
			ImageIO.write(exportImage, "png", 
					new File(fileName + "_" + System.currentTimeMillis()
							+ ".png"));
		} catch (IOException e) {

			System.out.println(e);

		}

	}

	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
		
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
		
	/*inet = InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 });
    System.out.println("Sending Ping Request to " + inet);
    System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");*/
	
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		frames = 0;
		int updates = 0;
		requestFocus();
		
		
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {

				// speedModif++;
				if (gameState == gameState.MODDED) {
					title = titleTampered;
				}

				if (gameState == gameState.INGAME || gameState == gameState.INGAME_A || gameState == gameState.MODDED) {
					 //if (speedModif % 1 == 0) {
					update();
					 //speedModif = 0;
					//}
				}
				
				
				if (gameState == gameState.MENU) {
					getGui().checkMenu();
					updateMenu();
				}
				if (gameState == gameState.PAUSE) {
					updatePause();
					getGui().checkPause();
				}
				if (gameState == gameState.DEATH) {
					updateDeath();
					getGui().checkDeath();
				}
				key.update();
				gui.update();
				updateMode();
				loadGame();
				updates++;
				delta--;
			}
			render();
		
			frames++;

			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				// System.out.println(updates + " ups, " + frames + " fps");
				
				if (gameState == gameState.INGAME || gameState == gameState.INGAME_A || gameState == gameState.MODDED
						&& frames < 5000) {
					frame.setTitle(title + " | " + updates + " ups, " + frames
							+ " fps");
				}
				if (gameState == gameState.PAUSE) {
					frame.setTitle(title + " | PAUSED");
				}
				if (gameState == gameState.INGAME_A) {
					frame.setTitle("[A] " + title + " | " + updates + " ups, "
							+ frames + " fps");
				}
				if (gameState == gameState.MENU) {
					frame.setTitle(title + " | Main Menu");
				}
				
				if (this.recAVG_FPS) {				
				fpsTotal += frames;
				System.out.println("FPS: " + frames + " fpsIndex: " + ++fpsIndex + " AVG: " + fpsAVG);
				fpsAVG = fpsTotal / fpsIndex;
				}
				
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	public static int fpsIndex = 0;
	public static int fpsTotal = 0;
	public static int fpsAVG = 0;
public int deathTimeTicks = 0;
public int deathTimeSec = 0;


	public ArrayList<String> getCharDirs() {
		ArrayList<String> result = new ArrayList<String>();
		File file = new File(SaveGame.createDataFolder() + "\\Saves\\");
		String[] names = file.list();

		for(String name : names)
		{
			//System.out.println(SaveGame.createDataFolder() + "\\Saves\\" + name);
		    if (new File(SaveGame.createDataFolder() + "\\Saves\\" + name).isDirectory()) {
		    //    System.out.println(name);
		        result.add(name);
		    }
		}
		return result;
	}

	public void switchCharacter(String name) {
		boolean disabledSave = false;
		if (autoSave) {
			System.out.println("Switching Char -- Disabled AutoSave");
			autoSave = false;
			disabledSave = true;
		}
		this.PersonNameGetter = name;
		getPlayer().name = name;
		getPlayer().reset(getPlayer());
		getPlayer().invokeLoad(getPlayer());
		System.out.println("Switched To: " + getPlayer().name);
		
		if (disabledSave) {
			System.out.println("Finished Switching Char -- Enabled AutoSave");
			autoSave = true;
			disabledSave = false;
		}
		try {
		loadProp.loadPrefs(this);
		} catch (Exception e) {
			autoSave = true;
		}
		Game.switchState(Game.getGame().gameState.INGAME);
		
		if (Game.runTut) {
			getPlayer().setPosition(73, 38, Maps.tutWorldId, true);
		}
	}
	

	public void updateDeath() {	
		deathTimeTicks++;
		if (deathTimeTicks % 60 == 0) {
			if (deathTimeSec < 10) {
				deathTimeSec++;
				deathTimeTicks = 0;
			} else {
				deathTimeSec = 0;
				deathTimeTicks = 0;
				Game.switchState(gameState.INGAME);
			}
		}
		
		System.out.println("Respawning In.." + (10 - deathTimeSec));
		
			Player p = level.getClientPlayer();
			if (p.dead) {
			p.setX(playerSpawn.x());
			p.setY(playerSpawn.y());
			Game.getGame().getLevel().resetLevelPostDeath(p);
			p.mobhealth = p.maxhealth;
			p.mana = p.maxmana;
			p.stamina = p.maxstamina;
			p.money = p.money * 5/6;
		//	p.ExpC -= p.ExpC * 1/30;
			p.effects.removeAll();
			p.speed = 1;
			p.riding = false;
			p.ridingOn = null;
			Game.getGame().setLevel(new SpawnHaven(Maps.SpawnHaven));
			Game.getGame().getLevel().add(p);
				p.dead = false;
		}
	}

	public void onLaunch() {
			Dead = false;
		/*
		 * if (gameState == 2 || gameState == 3) { Sound.Play(Hope, -10, true);
		 * }
		 */
		/*
		 * List<Entity> entities = level.entities; for (int i = 0; i <
		 * entities.size(); i++) { entities.remove(i); }
		 */

	}

	public void updatePause() {
		//System.out.println("[Game: ~773] GAMESTATE: PAUSE");
		
		
		

	}

	public void updateMenu() {
		loadTime++;
		atMenu = true;
		// System.out.println("[Game: ~293] GAMESTATE: 1");
		onLaunch();
		loadGameState2();

		// Sound.Play(Sound.Rock, 10, true);
	}
	
	public void save(boolean autoOverride) {
		if (gameState != gameState.MENU) {
		loadProp.savePrefs(this);
		if (autoSave || autoOverride) {
		List<PlayerMP> players = level.players;
		if (players != null) {
			getLevel().getClientPlayer().invokeSave(getLevel().getClientPlayer());
				}
			}
		}
	}

	public void autoSave() {
			saveTime++;
			if ((saveTime % 400) == 0) {
				save(false);
				// loadProp.saveEquips((PlayerMP) this.getPlayer());
				// save(this.player.inventory.items);
				// System.out.println("SAVING THE GAME");
		}
	}

	public void updateMode() {
		// adminCmds();

		if (gameState == gameState.INGAME || gameState == gameState.INGAME_A
				|| gameState == gameState.PAUSE) {
			autoSave();
		}
		if (gameState != gameState.SPLASH && gameState != gameState.MENU
				&& gameState != gameState.DEATH && key.DevMode && !devModeOn
				&& devModeReleased && Mouse.getButton() == 2) {
			devModeOn = true;
			devModeReleased = false;
		}

		if (!key.DevMode)
			devModeReleased = true;

		if (key.DevMode && devModeOn && devModeReleased) {
			devModeOn = false;
			devModeReleased = false;
		}

		if (key.toggleDevModeInfo && !devModeInfoOn && releasedDevInfo
				&& devModeOn) {
			devModeInfoOn = true;
			releasedDevInfo = false;
		}

		if (!key.toggleDevModeInfo)
			releasedDevInfo = true;

		if (key.toggleDevModeInfo && devModeInfoOn && releasedDevInfo) {
			devModeInfoOn = false;
			releasedDevInfo = false;
		}

		if (blankBoolean) {

			width = frame.getWidth() / scale;
			height = frame.getHeight() / scale;
			/*
			 * System.out.println("HEIGHT: " + height + " || " +
			 * frame.getHeight()); System.out.println("WIDTH: " + width + " || "
			 * + frame.getWidth());
			 */

		}

		loadGameTime++;

		/*
		 * boolean playValiant = true; if (playValiant) {
		 * Sound.PlayMusic(Sound.menuMusic, true); playValiant = false; }
		 */

		if (key.capture) {
			if (screenshots.exists()) {
				try {
					captureScreen(frame, screenshots + "/Square_Legacy");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (!loadGame) {
			if (gameState != Game.getGame().gameState.MENU && key.gs1) {
				// playHope = false;

				// playMenuMusic = true;
				switchState(gameState.MENU);
			}
			if (gameState != gameState.INGAME && key.gs2) {
					if (!invokedLoad) {
						player.invokeLoad(player);
						invokedLoad = true;
					}
					switchState(gameState.INGAME);					
				}
			if (gameState != gameState.INGAME_A && key.gs3) {
				if (!invokedLoad) {
					player.invokeLoad(player);
					invokedLoad = true;
				}
				switchState(gameState.INGAME_A);					
		}

			if (gameState != gameState.PAUSE && gameState != gameState.MENU && key.gs4)
				if (player.inventoryEnabled) {
					player.inventoryEnabled = false;
					key.gs4 = false;
				} else {
					switchState(gameState.PAUSE);
				}
		}
	}

	public void loadGameState2() {
		if (loading) {
			if ((loadTime % 70) == 0) {
				loadTime = 0;
				for (int i = 0; i < 3000; i++) {
					if (i > 2000) {

						switchState(gameState.INGAME);
						getGui().fadeTime = 0;
						loading = false;
						if (!invokedLoad) {
							player.invokeLoad(player);
							invokedLoad = true;
						}
					}
				}
			}
		}
	}

	public void loadGame() {
		if (loadGame) {
			if ((loadGameTime % 100) == 0) {
				loadGameTime = 0;
				// for (int i = 0; i < 3000;  i++) {
				// if (i > 2000) {
				switchState(gameState.MENU);
				getGui().fadeTimeS = 0;
				loadGame = false;
			}
		}
	}

	// }
	// }

	public void update() {
		
		if (mouseMotionTime > 0) {
		this.mouseMotionTime--;
		}
		
		
		
		if (switchedTo3) {
			warningTime++;
		}

		if (warningTime > 200) {
			switchedTo3 = false;
			warningTime = 0;
		}
		
		if (developerPlaying && developerTime < 250) {
			developerTime++;
		}

		time2++;
		ClickTime++;

		level.update();

		/*
		 * if (swampLoaded) {
		 * 
		 * defaultLoaded = false; level = Level.Swamp; level.add(player);
		 * player.removed = true; player.isRemoved(); player.update(); }
		 */

		/*
		 * if (defaultLoaded) { swampLoaded = false; level.add(player);
		 * player.removed = true; player.isRemoved(); player.update();
		 * player.render(screen); screen.renderMob(296, 381, player); }
		 */

		if (PersonNameGetter == null) {
			PersonNameGetter = "Player";
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		
		
	//if (!screen.shakeScreen()) {
			
		
		
		xScroll = getPlayer().getX() - screen.width / 2;
		yScroll = getPlayer().getY() - screen.height / 2;
		
		
		//}

		
		if (gameState == gameState.INGAME || gameState == gameState.INGAME_A || gameState == gameState.PAUSE) {
			level.render((int) (xScroll), (int) (yScroll), screen);
			gui.render(screen);
			player.renderGUI(screen);
		}
		if (getLevel() instanceof SpawnHaven && level.Overworld) {
			//screen.drawFillRect(26, 5, 62, 9, 0xff302B23, true);
			font.render(22 * TileCoord.TILE_SIZE, 7 * TileCoord.TILE_SIZE, -2, 0xffFFFFFF, " Haven", screen, true, false);
		}
		if (gameState == gameState.MENU) {
			getGui().renderMainMenu(screen);
			//screen.renderSprite(24, 92, Sprite.button_Default, false);
			//font.render(18, 98, -4, "Survival", screen, false, false);
		} else if (gameState == gameState.PAUSE) {
			getGui().renderPause(screen);
		} else if (gameState == gameState.DEATH) {
			getGui().renderDeath(screen);
			font8x8.render(-4, 159, -3, 0xffFFFFFF, "Auto Respawn In.." + (10 - Game.getGame().deathTimeSec), screen, false, false);
		} else if (gameState == gameState.SPLASH) {
			getGui().renderSplash(screen);
		}
		
		if (Player.unlockTime > 1 && Player.unlockTime < 300) {
			font.render(-4, 22, -7, 0x990000,
					"Progress Further To Unlock This", screen, false, true);

		}
		
		
		if (showAVG) {
		if (fpsAVG < 200) {			
		font8x8.render(-5, this.height - 17, -3, 0xDB0000,
				"Average FPS: " + fpsAVG, screen, false, true);
		} else {
			font8x8.render(-5, this.height - 17, -3, 0x00ff00,
					"Average FPS: " + fpsAVG, screen, false, true);
		}
		}

		if (warningTime > 1) {
			if (warningTime < 50) {
				font.render(-5, 22, -6, 0xDB0000,
						"Warning: Not Collecting Stats", screen, false, true);
			} else if (warningTime < 100) {
				font.render(-5, 22, -6, 0xC10000,
						"Warning: Not Collecting Stats", screen, false, true);

			} else if (warningTime < 150) {
				font.render(-5, 22, -6, 0x990000,
						"Warning: Not Collecting Stats", screen, false, true);

			}
		}

		/*
		 * if (!Dead && gameState == 4) { //0xff302B29 //screen.renderSprite(36,
		 * 13, Sprite.staticGrey, false); if (Player.Lvl < 10 && Player.ExpC <
		 * 1000) { screen.drawFillRect(36, 13, 16 * 6 + 4, 59, 0xff302B23,
		 * false); Debug.drawRect(screen, 36, 13, 16 * 6 + 4, 59, 0xff000000,
		 * false); } else { screen.drawFillRect(36, 13, 16 * 7 + 4, 59,
		 * 0xff302B23, false); Debug.drawRect(screen, 36, 13, 16 * 7 + 4, 59,
		 * 0xff000000, false); } font.render(12, 15, -4, 0xFFFFFF, " Level: " +
		 * Player.Lvl + "\n Exp: " + Player.ExpC + "\n Kills: " + kills, screen,
		 * false, false); }
		 */
		
		
		/*time++;
		for(int j = 0; j < screen.height; j++) {
			for (int i = 0; i < screen.width; i++) {
				screen.pixels[i + j * screen.width] = 0;												
				if (i % 2 == 0) {
						if (time % 2  == 0) {
							screen.pixels[i + j * screen.width] = 0xffFF00FF;												
						} else {
							if (j % 2 == 0) {
							screen.pixels[i + j * screen.width] = 0xff00FF00;												
						}
					}
				}
			}
		}*/
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];

		}
		Graphics g = bs.getDrawGraphics();
		Color Opaque = new Color(5, 0, 0, 120);

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		g.setColor(Opaque);

		/*
		 * if (Player.inventoryOn) { if (Mouse.getButton() == 1) { if
		 * (Mouse.getX() < 820 + 25 && Mouse.getX() > 820 - 5 && Mouse.getY() <
		 * 164 + 42 && Mouse.getY() > 164 - 5 ) { Player.inventoryOn = false; if
		 * (ClickTime > 20) { Sound.Play(Sound.Click, false); ClickTime = 0; } }
		 * } if (useHealthPotion) { time++; } if (Mouse.getButton() == 1 &&
		 * healthPotionsRemaining > 0) { if (Mouse.getX() < 522 + 40 &&
		 * Mouse.getX() > 522 - 5 && Mouse.getY() < 245 + 42 && Mouse.getY() >
		 * 245 - 5 ) { useHealthPotion = true; grabbedHealthPotion = true; /* if
		 * (time > 20) { time = 0; healthPotionsRemaining -=1; useHealthPotion =
		 * false; } } } }
		 */


		
		
		
		/*
		  if (Player.inventoryOn == false && gameState == 2 || gameState == 3)
		  { g.fill3DRect(30 * 16 + 40, 20 * 16 - 80, 150, 30, true);
		  g.setColor(Color.WHITE); g.setFont(new Font("Verdana",0, 18));
		  //g.drawString(PersonNameGetter, 30 * 16 + 46, 20 * 16 - 59);
		  
		  }*/
		 
		/*
		 * if (Player.inventoryOn) { g.fill3DRect(0, 0, 1500, 160, true);
		 * g.fill3DRect(0, 160, 340, 1000, true); g.fill3DRect(850, 160, 350,
		 * 520, true); g.fill3DRect(340, 540, 510, 130, true); }
		 */

		/*
		 * if (level.players.size() > 0) { double
		 * mx=(level.getPlayerAt(0).getX()-width/2)+(Mouse.getX()/(Game.scale));
		 * mx=(mx/16); double
		 * my=(level.getPlayerAt(0).getY()-height/2)+(Mouse.getY
		 * ()/(Game.scale)-10); my=(my/16); // System.out.println("MX: " +
		 * (int)mx + "MY: " + (int)my); if ((int)mx == 245 && (int)my == 400) {
		 * Player.inventoryOn = true; } }
		 */

		if (gameState != gameState.SPLASH && gameState != gameState.MENU && gameState != gameState.DEATH
				&& devModeOn == true || Mouse.getButton() == 2) {
			g.setColor(Opaque);
			// g.fillRect(10, 80, 100, 1);
			g.fill3DRect(0, 0, 545, 95, false);
			g.fill3DRect(Mouse.getX() - 110, Mouse.getY() + 50, 250, 30, false);
			g.setColor(Color.lightGray);
			g.fillRect(Mouse.getX() - 4, Mouse.getY() - 4, 38, 38);
			g.setFont(new Font("Verdana", 0, 16));
			g.setColor(Color.WHITE);
			g.drawString("Player[UUID]: " + level.getPlayers(), 10, 40);
			// g.drawString("xScroll: " + xScroll + " yScroll: " + yScroll, 10,
			// 60);
			g.drawString("Tile: " + level.returnTile(tile) + " || Overlay: " + level.returnOverlayTile(tile), 10, 60);
			g.drawString("X: " + (int) getPlayer().getX() / TileCoord.TILE_SIZE + ", Y: " + (int) getPlayer().getY() / TileCoord.TILE_SIZE, 10, 20);
			g.drawString("Mouse X: " + (int) Mouse.getX() / scale + ", Mouse Y: " + Mouse.getY() / scale, Mouse.getX() - 103, Mouse.getY() + 70);
			//screen.drawLine(getPlayer(), level.entities);
			g.setColor(Color.gray);
			// g.fill3DRect(1020, 618, 300, 300, true);
			g.setColor(Color.WHITE);

			g.setFont(new Font("Verdana", 0, 18));

			/*
			 * if (gameState == 5) { g.fill3DRect(1362, 4, 55, 30, false);
			 * g.setColor(Color.WHITE); g.setFont(new Font("Verdana",0, 18));
			 * g.drawString("Map", 1372, 25); }
			 */

			if (devModeOn == true && devModeInfoOn && gameState != gameState.MENU) {
				g.setFont(new Font("Verdana", 0, 16));
				g.drawString(
						"Developer Mode: Mouse Grid, Coordinate, Player [UUID], Scrolls",
						10, 80);
				g.setFont(new Font("Verdana", 0, 16));
				g.fill3DRect(1362, 4, 55, 30, false);
				g.setColor(Color.WHITE);
				g.setFont(new Font("Verdana", 0, 18));
				g.drawString("Map", 1372, 25);

				// g.drawString("Button: " + Mouse.getButton(), 415, 80);
			}

		}
		
		//fontLayer.render(g);

		g.dispose();
		bs.show();

	}
    static Game game1;
    
    public static void setWindowIcon(String path) {
    	frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				Game.class.getResource(path)));
    	
    }
    
    public static Cursor setMouseIcon(String path) {
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = null;
		image = Toolkit.getDefaultToolkit().getImage(
				Game.class.getResource(path));

		Point hotspot = new Point(0, 0);
		Cursor cursor = toolkit.createCustomCursor(image, hotspot, "Stone");
		frame.setCursor(cursor);
		return cursor;
    }

    public static  HashMap<String, Boolean>  cmdln_args;
    public static void main(String[] args) {
    	cmdln_args = new HashMap<String, Boolean>();
    	
    	System.out.println("CALLED");
    	
    	for (String s : args) {
    		cmdln_args.put(s, true);
    	}    	
    	
		Game game = new Game();
		game1 = game;

		setWindowIcon("/Textures/sheets/wizard.png");
		game.frame.setResizable(false);			
		if (cmdln_args.containsKey("-resizeable")) {
		game.frame.setResizable(true);			
		}
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		if (cmdln_args.containsKey("-fullscreen")) {
			game.frame.setUndecorated(true);
			game.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 			
		}
		//game.frame.setOpacity(0.01F);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		setMouseIcon("/Textures/cursor.png");

		game.start();
		 centreMouse();

		
		blankBoolean = true;
		
	}
    
    
	public static Game getGame() {
		return game1;
	}
	
	public static void moveMouse(Point p) {
	    GraphicsEnvironment ge = 
	        GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gs = ge.getScreenDevices();

	    // Search the devices for the one that draws the specified point.
	    for (GraphicsDevice device: gs) { 
	        GraphicsConfiguration[] configurations =
	            device.getConfigurations();
	        for (GraphicsConfiguration config: configurations) {
	            Rectangle bounds = config.getBounds();
	            if(bounds.contains(p)) {
	                // Set point to screen coordinates.
	                Point b = bounds.getLocation(); 
	                Point s = new Point(p.x - b.x, p.y - b.y);

	                try {
	                    Robot r = new Robot(device);
	                    r.mouseMove(s.x, s.y);
	                } catch (AWTException e) {
	                    e.printStackTrace();
	                }

	                return;
	            }
	        }
	    }
	    // Couldn't move to the point, it may be off screen.
	    return;
	}
	
	
	
	public static void switchState(gameState state) {
		Mouse.setMouseB(-1);
		gameState current = Game.getGame().gameState;
		Game.getGame().gui.newCharMenu = false;
		Game.getGame().gui.charMenu = false;

		try {
		if (current != current.SPLASH) {
			if (current == current.MENU && state == state.INGAME) {
				Sound.StopMusic();
			} else if (current != current.INGAME && state == state.INGAME_A) {
				Sound.StopMusic();
				Sound.PlayOgg(Sound.HopeOgg, true, 0.8);
			} else if (state == state.MENU || state == state.DEATH) {
				Sound.StopMusic();
			}
		}
		
		} catch (Exception e) {
			System.out.println("Stopped Playing an invalid (null) Ogg");
		}
		
		if (state == state.MENU) {
			 Sound.PlayOgg(Sound.menuMusOgg, true, 0.8);
		} else if (state == state.INGAME) {
			if (current != current.INGAME_A) {
				if (current == current.PAUSE) {
					Game.getGame().save(false);
					Sound.resumeOgg();
				} else if (current == current.MENU){
					Sound.PlayOgg(Sound.HopeOgg, true, 0.8);
				}
			}
		} else if (state == state.INGAME_A) {
			switchedTo3 = true;
		} else if (state == state.PAUSE) {
			centreMouse();
			 Sound.pauseOgg();
		} else if (state == state.DEATH) {
			centreMouse();
		}

		/*
		 * if (gamestate == 1) { if (Sound.clipM.isOpen()) Sound.clipM.stop();
		 * Sound.PlayMusic(Sound.menuMusic, true); }
		 * 
		 * if (gamestate == 2) { if (Sound.clipM.isOpen()) Sound.clipM.close();
		 * 
		 * //Sound.PlayMusic(Sound.Hope, true); }
		 * 
		 * if (gamestate == 4) { Sound.StopMusic(); }
		 */

		Game.getGame().gameState = state;

	}

	public Screen getScreen() {
		return screen;
	}
	
/*	public Level level() {
		return level2;
	}*/

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Level getLevel() {
		return level;
	}

	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}
	
	
	
	public static void log(String text, boolean err) {
		if (!err) {			
			System.out.println(" >> " + text);
		} else {
			System.err.println(" >> ALERT: " + text);			
		}
	}
	
	public static void log(String text, String outboundClass, boolean err) {
		if (!err) {			
			System.out.println(" >> " + text);
		} else {
			System.err.print(outboundClass + " >> ALERT: ");
				System.out.println(text);
		}
	}
	
	public static void centreMouse() {
		int centreFrameX = frame.getX() + (frame.getWidth() / 2);
		int centreFrameY = frame.getY() + (frame.getHeight() / 2);
		moveMouse(new Point(centreFrameX, centreFrameY));
		
	}
	
	public void setMousePos(int framex, int framey) {
		moveMouse(new Point(framex, framey));
	}

	public void quit() {
		if (gameState == gameState.INGAME || gameState == gameState.INGAME_A) {
			System.out.println("Saving & Closing Application");
			save(false);			
		} else {
			System.out.println("Closing Application");
		}
				
		System.exit(0);
		
	}
	
	
	
	
}
