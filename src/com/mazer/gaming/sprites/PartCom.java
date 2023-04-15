package com.mazer.gaming.sprites;

import java.awt.Graphics;


import com.mazer.gaming.utils.GameConstants;

public abstract class PartCom implements GameConstants{
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	protected int speed;
	protected int CurrentMove = WALK;
	protected int index = 0;
	public int getCurrentMove() {
		return CurrentMove;
	}
	public void setCurrentMove(int currentMove) {
		this.CurrentMove = currentMove;
		index=0;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public abstract  void paintSprite(Graphics pen);
}
