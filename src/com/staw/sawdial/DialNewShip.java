package com.staw.sawdial;

import java.util.HashMap;

import com.staw.listener.ChangeImgListener;
import com.staw.util.Datos;
import com.staw.util.Dial;
import com.staw.util.FactoryDial;
import com.staw.util.Move;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DialNewShip extends Activity {
	
	private HashMap<String, Move> moves;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dial_new_ship);
		moves = new HashMap<String, Move>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dial_new_ship, menu);
		
		
		int [] mov1 = {R.id.dial1l, R.id.dial1hl, R.id.dial1f, 
				       R.id.dial1hr, R.id.dial1r, R.id.dial1b,
				       R.id.dial1s};
		int [] mov2 = {R.id.dial2l, R.id.dial2hl, R.id.dial2f, 
			           R.id.dial2hr, R.id.dial2r, R.id.dial2b,
			           R.id.dial2s};
		int [] mov3 = {R.id.dial3l, R.id.dial3hl, R.id.dial3f, 
			           R.id.dial3hr, R.id.dial3r, R.id.dial3b,
			           R.id.dial3s};
		int [] mov4 = {R.id.dial4l, R.id.dial4hl, R.id.dial4f, 
			           R.id.dial4hr, R.id.dial4r, R.id.dial4b,
			           R.id.dial4s};
		int [] mov5 = {R.id.dial5l, R.id.dial5hl, R.id.dial5f, 
			           R.id.dial5hr, R.id.dial5r, R.id.dial5b,
			           R.id.dial5s};
		int [] mov6 = {R.id.dial6l, R.id.dial6hl, R.id.dial6f, 
			           R.id.dial6hr, R.id.dial6r, R.id.dial6b,
			           R.id.dial6s};
		
		int [] move = {Move.MOV_LEFT,  Move.MOV_HALF_LEFT, Move.MOV_FORWARD,
				      Move.MOV_HALF_RIGHT, Move.MOV_RIGHT, Move.MOV_BACKWARD,
				      Move.MOV_SWING
					 };
		
		for(int n = 0; n < 7; n++) {
			inicializar(mov1[n], move[n], 1);
			inicializar(mov2[n], move[n], 2);
			inicializar(mov3[n], move[n], 3);
			inicializar(mov4[n], move[n], 4);
			inicializar(mov5[n], move[n], 5);
			inicializar(mov6[n], move[n], 6);
		}
		
		return true;
	}
	
	private void inicializar(int id, int arrow, int number) {
		ImageView img = (ImageView)findViewById(id);
		img.setOnClickListener(new ChangeImgListener(this, img, arrow, number));
	}
	
	
	public void cancel(View view) {
		finish();
	}
	
	public void accept(View view) {
        /*Intent i = new Intent(getApplicationContext(), DialSelActivity.class);
        startActivity(i);*/
		TextView txtname = (TextView) findViewById(R.id.txtname);
		String nombre = txtname.getText().toString();
		if (nombre == null || "".equals(nombre.trim())) {
			alert("You have to give a name.");
		} else {
			Context context = this;
			Dial dial = FactoryDial.create(nombre, moves);
			int total = Datos.totalShip(context);
			Datos.safe(this, total + 1	, dial);
			finish();
		}
	}

	public void programarMove(String clave, Move move) {
		if (move == null) {
			moves.remove(clave);
		} else {
			moves.put(clave, move);
		}
	}
	
	public void alert(String mensaje) {
		Context context = this;
		AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(mensaje);
        builder1.setCancelable(true);
        builder1.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert11 = builder1.create();
        alert11.show();		
	}
	
	

}
