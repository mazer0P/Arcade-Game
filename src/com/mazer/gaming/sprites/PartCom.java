package com.mazer.gaming.sprites;

import java.awt.Graphics;


import com.mazer.gaming.utils.GameConstants;

public abstract class PartCom implements GameConstants{
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	public int speed;
	protected int CurrentMove = WALK;
	protected int index = 0;
	protected boolean isAttacking = false;
	public boolean isCollide=false; 
	protected int health = 500;
	
//	public int getHealth() {
//		System.out.println(health+"2nd");
//			return health;
//		}
//	
//	public void setHealth() {
//		
//	}
	
	
	public int getHealth() {
		return health;
	}
	public void setHealth() {
		this.health = health-(int)(GameConstants.MAX_HEALTH*0.1);
	}
	public boolean isCollide() {
		return isCollide;
	}
	public void setCollide(boolean isCollide) {
		this.isCollide = isCollide;
	}
	public boolean isAttacking() {
		return isAttacking;
	}
	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
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
