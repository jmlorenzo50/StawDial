package com.staw.util;

/**
 * @author jorge
 *
 */
public class Move {
	
	
	
	public static int MOV_LEFT = 1001;
	
	public static int MOV_HALF_LEFT = 1002;

	public static int MOV_FORWARD = 1003;

	public static int MOV_HALF_RIGHT = 1004;
	
	public static int MOV_RIGHT = 1005;

	public static int MOV_BACKWARD = 1006;

	public static int MOV_SWING = 1007;
	
	
	public static int COL_WHITE = 2001;
	
	public static int COL_GREEN = 2002;
	
	public static int COL_RED = 2003;
	
	private int arrow;
	
	private int color;
	
	private int number;
	
	
	/**
	 * @param arrow
	 * @param color
	 * @param number
	 */
	public Move(int arrow, int color, int number) {
		super();
		this.arrow = arrow;
		this.color = color;
		this.number = number;
	}

	/**
	 * @return
	 */
	public int getArrow() {
		return arrow;
	}

	public void setArrow(int arrow) {
		this.arrow = arrow;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public String toSerial() {
		String salida = arrow + "#" + color + "#" + number;
		return salida;
	}

	public static Move toMove(String serial) {
		String[]serials = serial.split("#");
		Move move = new Move(Integer.parseInt(serials[0]), 
				             Integer.parseInt(serials[1]), 
				             Integer.parseInt(serials[2]));
		return move;
	}
	

}
