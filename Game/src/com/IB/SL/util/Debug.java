package com.IB.SL.util;

import com.IB.SL.Game;
import com.IB.SL.graphics.Screen;

public class Debug {

	private Debug() {
		
	}
	
	public static void drawRect(Screen screen, int x, int y, int width, int height, boolean fixed){
	      screen.drawRect(x, y, width, height, 0xFF0000, fixed);
	   }
	   
	   public static void drawRect(Screen screen, int x, int y, int width, int height, int color, boolean fixed){
	      screen.drawRect(x, y, width, height, color, fixed);
	   }
	
	   public static void drawLine(Screen screen, int x0, int y0, int x1, int y1, int colour, boolean fixed){
		   screen.drawVectors(Game.getGame().getLevel().BresenhamLine(x0, y0, x1, y1), colour, fixed);
		}
	   
	   
}
