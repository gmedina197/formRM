package com.mt;

public class Transition {
	String qi;
	String x;
	String qj;
	String y;
	String dir;
	
	String translatedTransition;
	
	public Transition(
			String qi,
			String x,
			String qj,
			String y,
			String dir
	) {
	
		this.qi = qi;
		this.x = x;
		this.qj=qj;
		this.y = y;
		this.dir = dir;	
	}
}
