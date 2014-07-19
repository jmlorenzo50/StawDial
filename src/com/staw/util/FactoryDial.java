package com.staw.util;

import java.util.Collection;
import java.util.HashMap;

public class FactoryDial {
	
	private FactoryDial() {
		// TODO Auto-generated constructor stub
	}
	
	public static Dial IksNeghVarClass() {
        Dial dial = new Dial("NEGH'VAR CLASS " + Dado.tirar(5000));
        dial.addMove(Move.MOV_HALF_LEFT, Move.COL_GREEN, 1);
        dial.addMove(Move.MOV_FORWARD, Move.COL_GREEN, 1);
        dial.addMove(Move.MOV_HALF_RIGHT, Move.COL_GREEN, 1);
        
        dial.addMove(Move.MOV_LEFT, Move.COL_WHITE, 2 );
        dial.addMove(Move.MOV_HALF_LEFT, Move.COL_WHITE, 2);
        dial.addMove(Move.MOV_FORWARD, Move.COL_GREEN, 2);
        dial.addMove(Move.MOV_HALF_RIGHT, Move.COL_WHITE, 2);
        dial.addMove(Move.MOV_RIGHT, Move.COL_WHITE, 2);
        
        dial.addMove(Move.MOV_LEFT, Move.COL_RED, 3);
        dial.addMove(Move.MOV_HALF_LEFT, Move.COL_WHITE, 3);
        dial.addMove(Move.MOV_FORWARD, Move.COL_WHITE, 3);
        dial.addMove(Move.MOV_HALF_RIGHT, Move.COL_WHITE, 3);
        dial.addMove(Move.MOV_RIGHT, Move.COL_RED, 3);
        dial.addMove(Move.MOV_SWING, Move.COL_RED, 3);

        dial.addMove(Move.MOV_FORWARD, Move.COL_WHITE, 4);
        
        return dial;
	}



	public static Dial IksVorchaClass() {
        Dial dial = new Dial("VOR'CHA CLASS");
        dial.addMove(Move.MOV_HALF_LEFT, Move.COL_GREEN, 1);
        dial.addMove(Move.MOV_FORWARD, Move.COL_GREEN, 1);
        dial.addMove(Move.MOV_HALF_RIGHT, Move.COL_GREEN, 1);
        
        dial.addMove(Move.MOV_LEFT, Move.COL_WHITE, 2 );
        dial.addMove(Move.MOV_HALF_LEFT, Move.COL_WHITE, 2);
        dial.addMove(Move.MOV_FORWARD, Move.COL_GREEN, 2);
        dial.addMove(Move.MOV_HALF_RIGHT, Move.COL_WHITE, 2);
        dial.addMove(Move.MOV_RIGHT, Move.COL_WHITE, 2);
        
        dial.addMove(Move.MOV_LEFT, Move.COL_RED, 3);
        dial.addMove(Move.MOV_HALF_LEFT, Move.COL_WHITE, 3);
        dial.addMove(Move.MOV_FORWARD, Move.COL_WHITE, 3);
        dial.addMove(Move.MOV_HALF_RIGHT, Move.COL_WHITE, 3);
        dial.addMove(Move.MOV_RIGHT, Move.COL_RED, 3);
        dial.addMove(Move.MOV_SWING, Move.COL_RED, 3);

        dial.addMove(Move.MOV_FORWARD, Move.COL_WHITE, 4);
        
        return dial;
	}

	public static Dial create(String nombre, HashMap<String, Move> moves) {
		Dial dial = new Dial(nombre);
		for (int number=1; number<=6; number++){
			for (int arrow=Move.MOV_LEFT; arrow<=Move.MOV_SWING; arrow++) {
				String clave = "A" + arrow + "N" + number; 
				Move move = moves.get(clave);
				if (move != null) {
					dial.addMove(move);
				}
			}
		}
		
		
		/*		Collection<Move> col =  moves.values();
		 * for (Move move : col) {
			dial.addMove(move);
		}*/
		return dial;
	}

}
