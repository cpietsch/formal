/*
 * FORMAL 1.1
 * by Christopher Pietsch
 * cpietsch@gmail.com
 */

package com.chrispie.formal;

import android.view.MotionEvent;
import processing.core.*;
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

public class MainActivity extends PApplet {
	
	int TouchEvents;
	float xTouch[];
	float yTouch[];
	int currentPointerId = 0;
	boolean printFPS;
	int maxTouchEvents = 3;
	MultiTouch[] mt;  
	
	float totalSpeed=0.1f;
	
	String touchEvent = "";    // string for touch event type
	float pressure = 0.0f;      // pressure and size variables
	float pointerSize = 0.0f;

	trail t[];
	
	@Override
	public String sketchRenderer() {
		return OPENGL; 
	}

	public void setup() {
		frameRate(200);
		orientation(PORTRAIT);

		  //size(displayWidth, displayHeight,OPENGL);
		  background(255);
		  
		  mt = new MultiTouch[maxTouchEvents];
		  t = new trail [maxTouchEvents];

		  for(int i=0; i < maxTouchEvents; i++) {
			  mt[i] = new MultiTouch();
			  t[i] = new trail(this);
		  }

		  
    }
 
    public void draw() {
	  
	    pushMatrix();
	    fill(0,0,0,10);
	    translate(0,0,-100);
	    rect(-100,-100,width+200,height+200);
	    fill(255);
	    popMatrix();
	    
	 
	        for(int i=0; i < maxTouchEvents; i++) {
	          // If that event been touched...
	          if(mt[i].touched == true) {
	        	 t[i].set(mt[i].motionX, mt[i].motionY); 
	          }
	          t[i].draw();
	        }
	       
	
    }
   

public boolean surfaceTouchEvent(MotionEvent me) {
  // Find number of touch points:
  int pointers = me.getPointerCount();
  // Set all MultiTouch data to "not touched":
  for(int i=0; i < maxTouchEvents; i++) {
    mt[i].touched = false;
  }
  //  Update MultiTouch that 'is touched':
  for(int i=0; i < maxTouchEvents; i++) {
    if(i < pointers) {
      mt[i].update(me, i);
    }
    // Update MultiTouch that 'isn't touched':
    else {
      mt[i].update();
    }
  }

  // If you want the variables for motionX/motionY, mouseX/mouseY etc.
  // to work properly, you'll need to call super.surfaceTouchEvent().
  return super.surfaceTouchEvent(me);
}

//------------------------------
// Class to store our multitouch data per touch event.

class MultiTouch {
  // Public attrs that can be queried for each touch point:
  float motionX, motionY;
  float pmotionX, pmotionY;
  float size, psize;
  int id;
  boolean touched = false;

  // Executed when this index has been touched:
  //void update(MotionEvent me, int index, int newId){
  void update(MotionEvent me, int index) {
    // me : The passed in MotionEvent being queried
    // index : the index of the item being queried
    // newId : The id of the pressed item.

    // Tried querying these via' the 'historical' methods,
    // but couldn't get consistent results.
    pmotionX = motionX;
    pmotionY = motionY;
    psize = size; 

    motionX = me.getX(index);
    motionY = me.getY(index);
    size = me.getSize(index);

    id = me.getPointerId(index);
    touched = true;
  }

  // Executed if this index hasn't been touched:
  void update() {
    pmotionX = motionX;
    pmotionY = motionY;
    psize = size;
    touched = false;
  }
}
	
}
