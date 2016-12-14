package com.IB.SL.entity.mob.peaceful;

import java.util.List;

import com.IB.SL.Game;
import com.IB.SL.entity.mob.Mob;
import com.IB.SL.entity.mob.Player;
import com.IB.SL.graphics.AnimatedSprite;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.SpriteSheet;
import com.IB.SL.util.Sound;

public class Horse extends Mob {

	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.Horse_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.Horse_right, 32, 32, 3);

	private AnimatedSprite animSprite = left;
	double xa = 0;
	double ya = 0;
	final double ox, oy;
	double time = 0;
	public int fireRate = 0;
	public static boolean justspawned = false;
	Player p = null;
	private int breatheTime = 519;

	public Horse(int x, int y) {
		this.mobhealth = 150;
		this.x = x << 4;
		this.y = y << 4;
		this.ox = x;
		this.oy = y;
		
		this.name = "Horse";
		this.invulnerable = true;
		this.ySort = false;
		//sprite = Sprite.Occulus;
	}

	private void move() {
	      List<Player> players = level.getPlayers(this, 20);
	      if (players.size() > 0) {
	    	  Player p = players.get(0);
	    	  if (p.ridingOn == null || p.ridingOn == this) {
	    			  
	    	  breatheTime++;
	    	  if (breatheTime % 520 == 0) {
	    		  Sound.Play(Sound.horse_Whinny, false);
	    	  }
				if (!Game.getGame().key.Sprint) {					
				p.animSprite.setFrame(0);
				
				p.speed = 3;
				p.riding = true;
				p.ridingOn = this;
			
				this.x = p.getX();
				this.y = p.getY();
				this.walking = p.walking;
				
				} else {
					p.speed = 1;
					p.riding = false;
					p.ridingOn = null;
					this.breatheTime = 519;
				}
				}
	      } 
	  /*    if (xa != 0 || ya != 0) {
	  		//move(xa, ya);
	  		walking = true;
	  	}else {
	  		walking = false;
	  	}*/
	   }
	public void update() {
		if (this.dir == DIRECTION.LEFT) {
			animSprite = left;
		}
		if (this.dir == DIRECTION.RIGHT) {
			animSprite = right;					
		}
		
		time++;
		move();
		
		if (Game.getGame().getPlayer().ridingOn != this) {
			walking = false;
		}
		
		if (walking) 
			animSprite.update();
		else
			animSprite.setFrame(0);

	}
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		if (this.dir == DIRECTION.RIGHT) {
			screen.render32Mob((int) (x - 14), (int) (y - 15), this);
		} else {
			screen.render32Mob((int) (x - 18), (int) (y - 15), this);
		}
	}

}
