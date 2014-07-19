package com.staw.sawdial;

import com.staw.util.Dado;
import com.staw.util.Datos;
import com.staw.util.Dial;
import com.staw.util.FactoryDial;
import com.staw.util.Move;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class DialAleatorio extends Activity {
	
	private Dial dial;
	
	private int shipnumber;
	
	int xOld;
	
    int yOld; 

	
	private void init() {
		Intent i = getIntent();
		shipnumber = i.getIntExtra("dialPosition",0);
		Context context = this;
		dial = Datos.load(context, shipnumber);
	}
	
	private void safeDial() {
		Context context = this;
		Datos.safe(context, shipnumber, dial);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		refrescar();
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial_aleatorio);
        init();
		refrescar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dial_aleatorio, menu);
        return true;
    }
    
    private void limpiar() {
    	ImageView mov_left = (ImageView)findViewById(R.id.movl);
    	mov_left.setVisibility(View.INVISIBLE);
    	ImageView mov_swing = (ImageView)findViewById(R.id.movs);
    	mov_swing.setVisibility(View.INVISIBLE);
    	ImageView mov_right = (ImageView)findViewById(R.id.movr);
    	mov_right.setVisibility(View.INVISIBLE);
    	ImageView mov_half_right = (ImageView)findViewById(R.id.movhr);
    	mov_half_right.setVisibility(View.INVISIBLE);
    	ImageView mov_backward = (ImageView)findViewById(R.id.movb);
    	mov_backward.setVisibility(View.INVISIBLE);
    	ImageView mov_forward = (ImageView)findViewById(R.id.movf);
    	mov_forward.setVisibility(View.INVISIBLE);
    	ImageView mov_half_left = (ImageView)findViewById(R.id.movhl);
    	mov_half_left.setVisibility(View.INVISIBLE);
    	
    	ImageView movwhite = (ImageView)findViewById(R.id.movwhite);
    	movwhite.setVisibility(View.INVISIBLE);
    	ImageView movred = (ImageView)findViewById(R.id.movred);
    	movred.setVisibility(View.INVISIBLE);
    	ImageView movgreen = (ImageView)findViewById(R.id.movgreen);
    	movgreen.setVisibility(View.INVISIBLE);
    	
    }
    
    private void mostrarMove(Move move) {
    	limpiar();
    	
    	TextView txtnombre = (TextView)findViewById(R.id.tvnombre);
    	txtnombre.setText(dial.getNombre());

    	TextView txt = (TextView)findViewById(R.id.txtnmumber);
    	txt.setText(move.getNumber() + "");

    	ImageButton btnTap = (ImageButton)findViewById(R.id.btnTap);
    	ImageView dialtap = (ImageView)findViewById(R.id.dialtap);
    	if (dial.isHide()) {
    		dialtap.setVisibility(View.VISIBLE);
    		btnTap.setImageResource(R.drawable.scanoff);
		} else {
    		dialtap.setVisibility(View.INVISIBLE);
    		btnTap.setImageResource(R.drawable.scanon);
    	}
    	
    	if (move.getArrow() == Move.MOV_LEFT) {
	    	ImageView mov_left = (ImageView)findViewById(R.id.movl);
	    	mov_left.setVisibility(View.VISIBLE);
    	}
    	if (move.getArrow() == Move.MOV_SWING) {
	    	ImageView mov_swing = (ImageView)findViewById(R.id.movs);
	    	mov_swing.setVisibility(View.VISIBLE);
    	}
    	if (move.getArrow() == Move.MOV_RIGHT) {
	    	ImageView mov_right = (ImageView)findViewById(R.id.movr);
	    	mov_right.setVisibility(View.VISIBLE);
    	}
    	if (move.getArrow() == Move.MOV_HALF_RIGHT) {
	    	ImageView mov_half_right = (ImageView)findViewById(R.id.movhr);
	    	mov_half_right.setVisibility(View.VISIBLE);
    	}
    	if (move.getArrow() == Move.MOV_BACKWARD) {
	    	ImageView mov_backward = (ImageView)findViewById(R.id.movb);
	    	mov_backward.setVisibility(View.VISIBLE);
    	}
    	if (move.getArrow() == Move.MOV_FORWARD) {
	    	ImageView mov_forward = (ImageView)findViewById(R.id.movf);
	    	mov_forward.setVisibility(View.VISIBLE);
    	}
    	if (move.getArrow() == Move.MOV_HALF_LEFT) {
	    	ImageView mov_half_left = (ImageView)findViewById(R.id.movhl);
	    	mov_half_left.setVisibility(View.VISIBLE);
    	}
    	
    	if (move.getColor() == Move.COL_WHITE) {
	    	ImageView movwhite = (ImageView)findViewById(R.id.movwhite);
	    	movwhite.setVisibility(View.VISIBLE);
    	}	
    	if (move.getColor() == Move.COL_RED) {
	    	ImageView movred = (ImageView)findViewById(R.id.movred);
	    	movred.setVisibility(View.VISIBLE);
    	}
    	if (move.getColor() == Move.COL_GREEN) {
	    	ImageView movgreen = (ImageView)findViewById(R.id.movgreen);
	    	movgreen.setVisibility(View.VISIBLE);
    	}	

    }
    
    public void lanzar(View view) {
    	int opciones = dial.getMoves().size();
    	int index = Dado.tirar(opciones) - 1;
    	dial.setIndex(index);
    	safeDial();
    	refrescar();
    }
    
    public void next(View view) {
    	dial.next();
    	safeDial();
    	refrescar();
    }
    
    public void back(View view) {
    	dial.back();
    	safeDial();
    	refrescar();
    }
    
    public void changetap(View view) {
    	dial.setHide(!dial.isHide());
    	safeDial();
    	refrescar();
    }

    public void refrescar() {
    	Move move = dial.getMoves().get(dial.getIndex());
    	mostrarMove(move);
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

	@Override
	public boolean onTouchEvent (MotionEvent event) {
		int action = event.getAction();
		
		final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
		 
		if (action==MotionEvent.ACTION_DOWN) {
			xOld = X;
			yOld = Y;
		} else if (action==MotionEvent.ACTION_UP) {
			int incX = Math.abs(xOld - X);
			int incY = Math.abs(yOld - Y);
			
			if (incX > incY) {
	            if (xOld > X) {
	            	dial.back();
	            	safeDial();
	            	refrescar();
	            } else if (xOld < X) {
	            	dial.next();
	            	safeDial();
	            	refrescar();
	            }
			} else {
	            if (yOld > Y) {
	            	dial.setHide(true);
	            	safeDial();
	            	refrescar();
	            } else if (yOld < Y) {
	            	dial.setHide(false);
	            	safeDial();
	            	refrescar();
	            }
			}
		} else if (action==MotionEvent.ACTION_MOVE) {
		}	
		return true;	}
    
}
