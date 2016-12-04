package com.IB.SL.entity.projectile;

import java.util.List;

import com.IB.SL.Game;
import com.IB.SL.entity.Entity;
import com.IB.SL.entity.mob.Mob;
import com.IB.SL.entity.mob.PlayerMP;
import com.IB.SL.entity.spawner.RockShatterSpawner;
import com.IB.SL.entity.spawner.RockSpawner;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.Sprite;
import com.IB.SL.util.Sound;


public class ThrowableRock extends Projectile{
	
	public static int FIRE_RATE = 80;
	public static int time = 0;
	public boolean isPlayers = false;
	
	private Mob mob;
	public ThrowableRock(double x, double y, double dir, Mob mob) {
		super(x, y, dir);
		range = random.nextInt(30) + 60;
		speed = 1.5;
		damage = 3;
		this.mob = mob;
		sprite = Sprite.RockTHROWING;
		this.collisionSound = Sound.Rock;
		this.breakParticle = 1;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		this.id = 3;
	}
	
	public void update() {
		List<PlayerMP> players = level.players;
		List<Entity> entities = level.entities;

		if (mob == Game.getGame().getPlayer()) {			
			Collision(this, entities); 
		} else {
			PlayerCollision(players, level.Projectiles, 5, 5); 			
		}
		
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 4, -2, 8)) {
			level.add(new RockShatterSpawner((int) (x + nx), (int) (y + ny), 20, 4, level));
			Sound.Play(Sound.Rock, false);
			 remove();
	}
	
		move();
		
	}
	
	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) {
			level.add(new RockSpawner((int) (x + nx), (int) (y + ny), 40, 1, level));
			remove();
		}
	}

	public double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin -y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int)x - 8,(int)y - 14, this);
	}
}