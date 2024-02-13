package com.mazer.gaming.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Power extends PartCom {
String playerName="";
	public Power(int x,String playerName){
		super();
		this.x=x;
		this.playerName=playerName;
	
	y=50;
	h=50;
	w=500;

	}
public void printRectangle(Graphics pen) {
	pen.setColor(Color.RED);
	pen.fillRect(x,y,w,h);
	pen.setColor(Color.GREEN);
	pen.fillRect(x,y,getHealth(),h);
	pen.setColor(Color.WHITE);
	pen.setFont(new Font ("Times", Font.BOLD,30));
	pen.drawString(playerName, x, y+80);
}
	@Override
	public void paintSprite(Graphics pen) {
		// TODO Auto-generated method stub
		
	}

	
}
