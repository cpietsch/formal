package com.chrispie.formal;
import processing.core.*;


public class trail {
	  int anzahl=20;
	  int pos=0;
	  int num=60;
	  float totalSpeed=0.1f;
	  PVector[] speicher= new PVector[num];
	  PVector[] position= new PVector[anzahl];
	  PVector[] speed = new PVector[anzahl];
	  PVector velocity = new PVector();
	  float[] farbe = new float[anzahl];

	  float dick=0;
	  int total=0;

	  PApplet p;
	  
	  trail(PApplet _p){
		  p = _p;
	     for(int n=0;n<anzahl-1;n=n+1){
	      position[n] = new PVector(p.random(0,p.width),p.random(0,p.height));
	      speed[n] = new PVector(0,0);
	      farbe[n] = p.random(100,255);
	    } 
	    
	     for(int n=0;n<num;n=n+1){
	       speicher[n] = new PVector(0,0);
	     }
	  }
	  
	  void set(float x, float y){
	    if(pos > 1 && speicher[pos].x != speicher[pos-1].x){
	      
	    }
	    speicher[pos].x = x;
	    speicher[pos].y = y;
	    
	    
	    //position[0].x = x;
	    //position[0].y = y;
	    
	    
	  }
	  void draw(){
	    
	    position[0].x = speicher[pos].x;
	    position[0].y = speicher[pos].y;
	    
	    speicher[pos].x *= 0.7;
	    speicher[pos].y *= 0.7;
	    
		pos++;
		if(pos>num-1) pos = 0;
	    
	    for(int n=1;n<anzahl-1;n++){

	    float tmpX=position[n-1].x;
	    float tmpY=position[n-1].y;

	    velocity.x=tmpX;
	    velocity.y=tmpY;

	    speed[n].x=position[n].x-velocity.x;
	    speed[n].y=position[n].y-velocity.y;

	    //strokeWeight(speed[n].mag()/4);
	    dick=speed[n].mag()/4;

	    velocity.sub(position[n]);
	    //ellipse(position[0].x, position[0].y, 30, 30);
	    speed[n].x=position[n].x-velocity.x;
	    speed[n].y=position[n].y-velocity.y;

	    //line(position[n].x, position[n].y, speed[n].x , speed[n].y);
	    p.pushMatrix();
	    p.translate(position[n].x, position[n].y, 10);
	    //p.rect(position[n].x, position[n].y,100,100);
	  
	    p.rotateZ(speed[n].x/100);
	    p.rotateX(speed[n].x/100);
	    p.rotateY(speed[n].x/100);
	    //rotateY(dick/40);
	    p.scale((float) (.3+dick/15));
	    //strokeWeight(dick/10);
	    //fill(farbe[n],255,255);
	    //stroke(0,0,0);
	    p.box(40,10,10);
	    p.popMatrix();
	    //
	    velocity.mult(totalSpeed);
	    position[n].add(velocity);

	    } 
	    
	  }
	}