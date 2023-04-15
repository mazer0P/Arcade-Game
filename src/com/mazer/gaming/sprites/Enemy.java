package com.mazer.gaming.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;

public class Enemy extends PartCom {
	private BufferedImage ken;
	private BufferedImage loadEnemy[]= new BufferedImage[5];
	private BufferedImage loadWalk[]= new BufferedImage[6];
	private BufferedImage loadKick[]= new BufferedImage[11];
	
	public Enemy()  {
		x = 1200;
		h = 250;
		y = FLOOR-h;
		w = 100;
		speed = 0;
		
		try {
			ken = ImageIO.read(Enemy.class.getResource("enemyken.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CurrentMove = REST;
		loadEnemy();
		loadWalk();
		loadKick();
		
	}
	public void move() {
		x = x+speed;
		
	}
	public void loadEnemy() {
//		loadEnemy[]=ken.getSubimage(580, 8, 52, 97);
//		loadEnemy[1]=ken.getSubimage(532, 6, 44, 101);
		loadEnemy[0]=ken.getSubimage(255, 12, 52, 94);
		loadEnemy[1]=ken.getSubimage(178, 12, 70, 94);
		loadEnemy[2]=ken.getSubimage(104, 8, 66, 99);
		loadEnemy[3]=ken.getSubimage(30, 13, 69, 93);
		
	}
	public void loadWalk() {
		loadWalk[0]=ken.getSubimage(580, 8, 52, 97);
		loadWalk[1]=ken.getSubimage(532, 6, 44, 101);
		loadWalk[2]=ken.getSubimage(473, 11, 48, 93);
		loadWalk[3]=ken.getSubimage(428, 8, 38, 97);
		loadWalk[4]=ken.getSubimage(373, 9, 44, 97);
		loadWalk[5]=ken.getSubimage(312, 9, 49, 97);
		
	}
	public void loadKick() {
		loadKick[0]=ken.getSubimage(567, 116, 58, 94);
		loadKick[1]=ken.getSubimage(514, 120, 46, 90);
		loadKick[2]=ken.getSubimage(473, 112, 31, 99);
		loadKick[3]=ken.getSubimage(427, 144, 39, 65);
		loadKick[4]=ken.getSubimage(367, 163, 51, 45);
		loadKick[5]=ken.getSubimage(310, 161, 50, 47);
		loadKick[6]=ken.getSubimage(258, 157, 47, 52);
		loadKick[7]=ken.getSubimage(209, 144, 41, 65);
		loadKick[8]=ken.getSubimage(156, 143, 45, 67);
		loadKick[9]=ken.getSubimage(96, 144, 51, 64);
		loadKick[10]=ken.getSubimage(10, 128, 80, 81);
		
		}
	
	private BufferedImage showEnemy(){
		if(index>3) {
			index=0;
			CurrentMove=REST;
		}
		BufferedImage img = loadEnemy[index];
		index++;
		return img;
	}
	private BufferedImage showWalk(){
		if(index>5) {
			index=0;
			CurrentMove=REST;
		}
		BufferedImage img = loadWalk[index];
		index++;
		return img;
	}
	private BufferedImage showKick(){
		if(index>10) {
			index=0;
			CurrentMove=REST;
		}
		BufferedImage img = loadKick[index];
		index++;
		return img;
	}
	
	
	@Override
	public void paintSprite(Graphics pen) {
		if(CurrentMove==REST) {
			pen.drawImage(showEnemy(),x,y,w,h,null);
		}
		else if(CurrentMove==WALK) {
			pen.drawImage(showWalk(),x,y,w,h,null);
		}
		else if(CurrentMove==KICK) {
			x=x+speed;
			pen.drawImage(showKick(),x,y,w,h,null);
		}
		
		
	}
	

}
