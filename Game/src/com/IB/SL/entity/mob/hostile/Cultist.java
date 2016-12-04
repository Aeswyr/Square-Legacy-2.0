package com.IB.SL.entity.mob.hostile;

import java.util.List;

import com.IB.SL.Game;
import com.IB.SL.Game.gameState;
import com.IB.SL.entity.inventory.ActiveEffects;
import com.IB.SL.entity.mob.Mob;
import com.IB.SL.entity.mob.Player;
import com.IB.SL.entity.projectile.WizardProjectile2;
import com.IB.SL.graphics.AnimatedSprite;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.Sprite;
import com.IB.SL.graphics.SpriteSheet;
import com.IB.SL.graphics.UI.GUI;
import com.IB.SL.level.Node;
import com.IB.SL.level.RayCast;
import com.IB.SL.level.TileCoord;
import com.IB.SL.level.tile.Tile;
import com.IB.SL.util.Sound;
import com.IB.SL.util.Vector2i;

public class Cultist extends Mob {

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.Cultist_down,
			16, 16, 2);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.Cultist_up, 16,
			16, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.Cultist_left,
			16, 16, 2);
	private AnimatedSprite right = new AnimatedSprite(
			SpriteSheet.Cultist_right, 16, 16, 2);
	private GUI gui;
	private AnimatedSprite animSprite = down;
	double xa = 0;
	double ya = 0;
	double time = 0;
	public int fireRate = 0;
	public static boolean justspawned = false;
	private List<Node> path = null;
	double healTime = 0;
	private double abilityTime = 0;
	private double usageTime = 0;
	private double fleeTime = 0;
	private boolean wasEvading = false;
	private double evadeTime = 0;
	public Tile tile;
	private int visability;
	RayCast raycast;
	RayCast raycastFlee;
	private int dirInt = 0;
	List<Player> players;
	
	public Cultist(int x, int y) {
		this.maxhealth = 60;
		this.mobhealth = maxhealth;
		this.Exp = 25;
		gui = new GUI();
		this.entityState = entityState.Wander;
		this.speed = 0.5;
		this.x = x << 4;
		this.y = y << 4;
		this.id = 74324;
		this.hostility = hostility.AGR;
		this.name = "Cultist";
		sprite = Sprite.playerback;
		this.effects = new ActiveEffects(7, this);
	}

	private void move() {
	      List<Player> players = level.getPlayers(this, 125 - visability);
	      if (players.size() > 0 && entityState != entityState.Evade && entityState != entityState.Flee) {
	         xa = 0;
	         ya = 0;
	         double px = level.getPlayerAt(0).getX();
	         double py = (int) level.getPlayerAt(0).getY();
	         Vector2i start = new Vector2i((int) getX() >> Game.TILE_BIT_SHIFT, (int)getY() >> Game.TILE_BIT_SHIFT);
	         Vector2i destination = new Vector2i(px / TileCoord.TILE_SIZE, py / TileCoord.TILE_SIZE);
	         if (time % 1 == 0) path = level.findPath(start, destination);
	         if (path != null) {
	            if (path.size() > 0) {
	               Vector2i vec = path.get(path.size() - 1).tile;
	               if (x < vec.getX() << 4) xa++;
	               if (x > vec.getX() << 4) xa--;
	               if (y < vec.getY() << 4) ya++;
	               if (y > vec.getY() << 4) ya--;
	            }
	     
	         }
	      } else if (entityState == entityState.Flee) {
		      if (players.size() > 0) {
			         xa = 0;
			         ya = 0;
			         double px = level.getPlayerAt(0).getX();
			         double py = (int) level.getPlayerAt(0).getY();
			         Vector2i start = new Vector2i((int) getX() >> Game.TILE_BIT_SHIFT, (int)getY() >> Game.TILE_BIT_SHIFT);
			         Vector2i destination = new Vector2i(px / TileCoord.TILE_SIZE, py / TileCoord.TILE_SIZE);
			         if (time % 1 == 0) path = level.findPath(start, destination);
			         if (path != null) {
			            if (path.size() > 0) {
			               Vector2i vec = path.get(path.size() - 1).tile;
			               if(!raycast.hasCollided()) {
			               if (x < vec.getX() << 4) xa--;
			               if (x > vec.getX() << 4) xa++;
			               if (y < vec.getY() << 4) ya--;
			               if (y > vec.getY() << 4) ya++;
					            } else {
					            	entityState = entityState.Attack;
					            }
			            }
					         }
		      		}
		      } else if (entityState == entityState.Evade) {
			      if (players.size() > 0) {
				         xa = 0;
				         ya = 0;
				         double px = level.getPlayerAt(0).getX();
				         double py = (int) level.getPlayerAt(0).getY();
				         Vector2i start = new Vector2i((int) getX() >> Game.TILE_BIT_SHIFT, (int)getY() >> Game.TILE_BIT_SHIFT);
				         Vector2i destination = new Vector2i(px / TileCoord.TILE_SIZE, py / TileCoord.TILE_SIZE);
				         if (time % 1 == 0) path = level.findPath(start, destination);
				         if (path != null) {
				            if (path.size() > 0) {
				               Vector2i vec = path.get(path.size() - 1).tile;
				               if (x < vec.getX() << 4) xa--;
				               if (x > vec.getX() << 4) xa++;
				               if (y < vec.getY() << 4) ya--;
				               if (y > vec.getY() << 4) ya++;
						            }
						         }
			      }
		      
	      } else { 
	         if (time % (random.nextInt(50) + 30) == 0) {
	            xa = random.nextInt(3) - 1;
	            ya = random.nextInt(3) - 1;
	            if (random.nextInt(2) == 0) {
	               xa = 0;
	               ya = 0;
	            }
	         }
	      }
	      if (xa != 0 || ya != 0) {
	         move(xa * speed, ya * speed);
	         walking = true;
	      } else {
	         walking = false;
	      }
	   }
	public void update() {
		if (time % 8 == 0) {
			this.hurt = false;
		}
        raycastFlee = level.RayCast(new Vector2i(x, y - 8), dirInt, (int)20);
		if (level.brightness <= 0) {
			visability = (int) (level.brightness * -1 / 2);
		} else {
			visability = 0;
		}
		
		
		
		
		
		
	      List<Player> players = level.getPlayers(this, 90 - visability);
	      List<Player> playersFlee = level.getPlayers(this, 100 - visability);

		if (this.evadeTime >= 125 && this.wasEvading && playersFlee.size() - 1 <= 0 && raycastFlee.hasCollided()) {
				this.entityState = entityState.Flee;
				this.fleeTime++;
		} else if (this.mobhealth <= 3) {
			this.entityState = entityState.Evade;
			this.wasEvading = true;
			this.evadeTime++;
		} else if (players.size() > 0) {
			this.entityState = entityState.Attack;
		} else {
			this.entityState = entityState.Wander;
		}
		
		if (this.entityState != entityState.Wander) {
			//System.out.println("STATE: " + this.entityState + " HEALTH: " + this.mobhealth + " FleeTime: " + fleeTime);
		}
		updateShooting();
        if (fireRate > 0) {
            fireRate--;
        }
		time++;
		healTime++;
		abilityTime++;
		
		if ((this.fleeTime > 100)) {
			this.wasEvading = false;
			fleeTime = 0;
		}
		
		if (this.evadeTime > 125) {
			this.evadeTime = 0;
		}
		
		if (usageTime > 20) {
			this.speed -= 0.5;
			usageTime = 0;
		}
		
		if ((abilityTime % 200) == 0) {
			if ((abilityTime % 60) == 0 && this.speed == 1) {
				usageTime++;
				this.speed += 0.5;
		}
		}
		if ((this.mobhealth < 5 && (healTime % 125) == 0) && this.entityState == entityState.Flee) {
			this.mobhealth += 1;
			System.out.println(mobhealth);
			healTime = 0;
		}
		
		if ((this.mobhealth < 5 && (healTime % 210) == 0) && this.entityState == entityState.Wander) {
			this.mobhealth += 1;
			System.out.println(mobhealth);
			healTime = 0;
		}
		
		move();
		if (walking)
			animSprite.update();
		else
			animSprite.setFrame(0);
		if (ya < 0) {
			animSprite = up;
			dir = DIRECTION.UP;
			dirInt = 5;
		} else if (ya > 0) {
			animSprite = down;
			dir = DIRECTION.DOWN;
			dirInt = 8;
		}
		if (xa < 0) {
			animSprite = left;
			dir = DIRECTION.LEFT;
			dirInt = 3;
		} else if (xa > 0) {
			animSprite = right;
			dir = DIRECTION.RIGHT;
			dirInt = 0;
		}
	}
	public void render(Screen screen) {
		if (this.mobhealth < this.maxhealth) screen.renderSprite((int) x - 16, (int)y - 24, gui.renderMobHealthExperiment(this, 20), true);
		sprite = animSprite.getSprite();
		this.xOffset = -8;
		this.yOffset = -15;
		screen.renderMobSprite((int) (x + xOffset), (int) (y + yOffset), this);
		if (Game.getGame().gameState == gameState.INGAME_A) {
			screen.drawRect((int)x + xOffset, (int)y + yOffset, sprite.getWidth(), sprite.getHeight(), 0xFF0000, true);
			try {
				if (players.size() > 0) {					
				Game.getGame().getScreen().drawVectors(Game.getGame().getLevel().BresenhamLine((int)x, (int)y, raycastFlee.rayVector.x, raycastFlee.rayVector.y), 0xffFF3AFB, true);				
				}
				} catch (NullPointerException e) {
					
				}
			}
	}
	private void updateShooting() {
		 players = level.getPlayers(this, 110 - visability);
		/*if (entities.get(i).hostility == entities.get(i).hostility.NEU) {
		}*/
		if ((players.size() > 0) && fireRate <= 0 && entityState != entityState.Flee) {
			double px = level.getClientPlayer().getX();
			double py = level.getClientPlayer().getY();
			double sx = this.getX();
			double sy = this.getY();
			double dx = px - sx;
			double dy = py - sy;
			double dir = Math.atan2(dy, dx);
			raycast = level.RayCast(new Vector2i(x, y), dir, (int)10);
			if(!raycast.hasCollided()) {
			DemonOrb(x, y + 4, dir);
			level.getClientPlayer().incombat = true;
			Sound.Play(Sound.Spell2, false);
			fireRate = WizardProjectile2.FIRE_RATE;
			} else {
				visability += 50;
			}
			
		}
	}

}
