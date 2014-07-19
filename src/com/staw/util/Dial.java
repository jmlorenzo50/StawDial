package com.staw.util;

import java.util.ArrayList;
import java.util.List;

public class Dial {
	
	private String nombre;
	
	private List<Move> moves;
	
	private int index;
	
	private boolean hide;
	
	public Dial(String nombre) {
		this.nombre = nombre;
		this.hide = false;
		moves = new ArrayList<Move>();
	}
	
	public void clean() {
		moves.clear();
	}

	public void addMove(Move move) {
		moves.add(move);
	}

	public void addMove(int arrow, int color, int number) {
		moves.add(new Move(arrow, color, number));
	}
	
	public List<Move> getMoves() {
		return moves;
	}

	public String getNombre() {
		return nombre;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void next() {
		this.index = (index + 1) % moves.size();
	}
	
	public void back() {
		this.index = (index - 1) % moves.size();
		if (this.index < 0) this.index = moves.size() -1;  
	}
	
	
	public boolean isHide() {
		return hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

	public String toSerial() {
		String salida = nombre + ":" + index + ":" + (hide?"S":"N");
		for (Move move : moves) {
			salida = salida + ":" + move.toSerial();
		}
		return salida;
	}
	
	public static Dial toDial(String serial) {
		String[] saux = serial.split(":");
		Dial dial = new Dial(saux[0]);
		dial.setIndex(Integer.parseInt(saux[1]));
		dial.setHide("S".equals(saux[2]));
		for (int i = 3; i < saux.length; i++) {
			Move move = Move.toMove(saux[i]);
			dial.addMove(move);
		}
		return dial;
	}

	public boolean hasError() {
		return (moves == null || moves.size() == 0);
	}

}
