package com.mazer.gaming.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mazer.gaming.utils.GameConstants;

public class Player implements GameConstants {
	private int x;
	private int y;
	private int w;
	private int h;
	private int speed;
	private BufferedImage goku;
	private BufferedImage loadPlayer[] = new BufferedImage[4];
	private BufferedImage loadKick[] = new BufferedImage[3];
	private BufferedImage loadPunch[] = new BufferedImage[3];
	private BufferedImage loadMpunch[] = new BufferedImage[4];
	private BufferedImage loadJump[] = new BufferedImage[4];
	private int index = 0;
	int CurrentMove = WALK;
	int force = 0;
	private boolean isJumping =false;
	
public int getCurrentMove() {
	return CurrentMove;
}
public void setCurrentMove(int currentMove) {
	this.CurrentMove = currentMove;
	index=0;
}
public Player(){
	x = 100;
	h = 250;
	y = FLOOR-h;
	w = 100;
	speed = 0;
	try {
		goku = ImageIO.read(Player.class.getResource("goku.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	loadPlayer();
	loadKick();
	loadPunch();
	loadMpunch();
	loadJump();
	CurrentMove=REST;
}
public void loadPlayer()
{
	loadPlayer[0] = goku.getSubimage(28, 104, 122, 288);
	loadPlayer[1] = goku.getSubimage(194, 105, 132, 278);
	loadPlayer[2] = goku.getSubimage(347, 95, 125, 288);
	loadPlayer[3] = goku.getSubimage(492, 88, 120, 281);
}
public void loadJump() {
	loadJump[0] = goku.getSubimage(872, 384, 159, 256);
	loadJump[1] = goku.getSubimage(1047, 368, 161, 288);
	loadJump[2] = goku.getSubimage(1222, 364, 167, 290);
	loadJump[3] = goku.getSubimage(872, 384, 159, 256);
}
public void loadKick() {
	loadKick[0] = goku.getSubimage(836, 1309, 182, 257);
	loadKick[1] = goku.getSubimage(1034, 1293, 260, 283);
	loadKick[2] = goku.getSubimage(1340, 1276, 282, 291);
	
}
public void loadPunch() {
	loadPunch[0] = goku.getSubimage(2130, 253, 200, 249);
	loadPunch[1] = goku.getSubimage(2372, 250, 188, 249);
//	loadPunch[2] = goku.getSubimage(1340, 1276, 282, 291);
	
}
public void loadMpunch() {
	loadMpunch[0] = goku.getSubimage(1901, 0, 268, 227);
	loadMpunch[1] = goku.getSubimage(2188, 0, 276, 225);
	loadMpunch[2] = goku.getSubimage(2516, 0, 244, 222);
	loadMpunch[3] = goku.getSubimage(2800, 0, 224, 223);
}
public void jump()
{
//	System.out.println("jump");
	if(!isJumping) {
	force = -50;
	y = y+force;
	isJumping=true;
	}
	
}
public void fall()
{
//	System.out.println("fall");
	if(y>=(FLOOR-h)) {
		isJumping = false;
		return;
	}
	force = force + GRAVITY;
	y = y+force;
	
}
public void move() {
	x=x+speed;
}
public int getSpeed() {
	return speed;
}
public void setSpeed(int speed) {
	this.speed = speed;
}
private BufferedImage showPlayer() {
	if(index >2)
	{
		index=0;
	}
//	try {
//		Thread.sleep(100);
//	} catch (InterruptedException e) {
//		// TO DO Auto-generated catch block
//		e.printStackTrace();
//	}
	BufferedImage img = loadPlayer[index];
	index++;
	return   img;  //getting sub-image of sprite
}
private BufferedImage showJump(){
	if(index>3) {
		index=0;
		CurrentMove=REST;
	}
	BufferedImage img = loadJump[index];
	index++;
	return img;
}
private BufferedImage showKick(){
	if(index>2) {
		index=0;
		CurrentMove=REST;
	}
	BufferedImage img = loadKick[index];
	index++;
	return img;
}
public BufferedImage showPunch(){
	if(index>1) {
		index=0;
		CurrentMove=REST;
	}
	BufferedImage img = loadPunch[index];
	index++;
	return img;
}
public BufferedImage showMpunch(){
	if(index>1) {
		index=0;
		CurrentMove=REST;
	}
	BufferedImage img = loadMpunch[index];
	index++;
	return img;
}
public void paintPlayer(Graphics pen) {
	if(CurrentMove==REST) {
		pen.drawImage(showPlayer(),x,y,w,h,null);	
	}
	else if(CurrentMove==KICK) {
		pen.drawImage(showKick(),x,y,w,h,null);
	}
	else if(CurrentMove==PUNCH) {
		pen.drawImage(showPunch(),x,y,w,h,null);
	}
	else if(CurrentMove==MPUNCH) {
		pen.drawImage(showMpunch(),x,y,w,h,null);
	}
	else if(CurrentMove==JUMP) {
		pen.drawImage(showJump(),x,y,w,h,null);
	}

}

}
