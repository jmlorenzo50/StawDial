package com.staw.listener;

import com.staw.sawdial.DialNewShip;
import com.staw.sawdial.R;
import com.staw.util.Move;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ChangeImgListener implements OnClickListener {
	
	private DialNewShip dial;
	
	private ImageView img;
	
	private int arrow; 
	
	private int number;
	
	private int posicion;
	
	private String clave;
	
	public ChangeImgListener(DialNewShip dial, ImageView img, int arrow, int number) {
		this.dial = dial;
		this.img = img;
		this.arrow = arrow;
		this.number = number;
		posicion = 0;
		this.clave = "A" + arrow + "N" + number; 
	}

	@Override
	public void onClick(View v) {
		Move move;
		if (posicion == 0) {
			img.setImageResource(R.drawable.color_green);
			posicion = 1;
			move = new Move(arrow, Move.COL_GREEN, number);
		} else if (posicion == 1) {
			img.setImageResource(R.drawable.color_white);
			posicion = 2;
			move = new Move(arrow, Move.COL_WHITE, number);
		} else if (posicion == 2) {
			img.setImageResource(R.drawable.color_red);
			posicion = 3;
			move = new Move(arrow, Move.COL_RED, number);
		} else {
			img.setImageResource(R.drawable.mov_none);
			posicion = 0;
			move = null;
		}
		
		dial.programarMove(clave, move);
	}

}
