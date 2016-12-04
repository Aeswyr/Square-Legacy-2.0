package com.IB.SL.level.worlds;

import java.io.Serializable;

public class Maps implements Serializable{
	
	public static int spawnHavenId = 0;
	public static int mainId = 1;
	public static int tutWorldId = 2;
	public static int dungeon1Id = 3;
	public static int dungeon2Id = 4;
	public static int voidBossRoomId = 5;
	public static int dungeon3Id = 6;
	
	public static int swampId = -1;  // Unused

	public static String main = ("/levels/WorldMap.png");
	public static String Swamp = ("/levels/Swamp.png");	
	public static String SpawnHaven = ("/levels/SpawnHaven.png");
	public static String Tutorial_World = ("/levels/Tutorial.png");

	public static String dungeon01 = ("/levels/Dungeons/Labarynth.png");
	public static String dungeon02 = ("/levels/Dungeons/VoidDungeon.png");
	public static String dungeon03 = ("/levels/Dungeons/IceTemple.png");
	public static String VoidBossRoom = ("/levels/Dungeons/VoidBossRoom.png");
}
