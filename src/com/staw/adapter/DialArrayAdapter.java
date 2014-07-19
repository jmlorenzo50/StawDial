package com.staw.adapter;

import java.util.List;

import com.staw.sawdial.DialAleatorio;
import com.staw.sawdial.R;
import com.staw.util.Datos;
import com.staw.util.Dial;
import com.staw.util.Move;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DialArrayAdapter extends ArrayAdapter<Dial> {
	
	private final Context context;
	private final List<Dial> diales;
	private UndoBarController mUndoBarController;
	
	public DialArrayAdapter(Context context, List<Dial> diales, UndoBarController mUndoBarController) {
		super(context, R.layout.list_dial, diales);
		this.context = context;
		this.diales = diales;
		this.mUndoBarController = mUndoBarController;
	}
	
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		Dial dial = diales.get(position);
    	Move move = dial.getMoves().get(dial.getIndex());
		
		View rowView = inflater.inflate(R.layout.list_dial, parent, false);
		
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		textView.setText(dial.getNombre());
		
		textView.setOnClickListener(new OnClickListener() {
	    	  @Override
	    	  public void onClick(View v) {
	              Intent i = new Intent(context, DialAleatorio.class);
	              i.putExtra("dialPosition", position+1);
	              context.startActivity(i);	    		  
	    	  }
	    }); 
		
		
		ImageButton btnscan = (ImageButton)rowView.findViewById(R.id.btnscan);
		if (dial.isHide()) {
			btnscan.setImageResource(R.drawable.scanoff);
			limpiar(rowView);
		} else {
			btnscan.setImageResource(R.drawable.scanon);
			mostrarMove(rowView, move);
		}

		btnscan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				  Dial dial = diales.get(position);
				  dial.setHide(!dial.isHide());
    			  Datos.safe(context, position+1, dial);
	    		  notifyDataSetChanged();
			}
		});
		
		ImageButton btneliminar = (ImageButton)rowView.findViewById(R.id.btneliminar);
		btneliminar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				  Datos.memoriza(diales);
	    		  diales.remove(position);
	    		  Datos.clear(context);
	    		  int i = 1;
	    		  for (Dial dial : diales) {
	    			  Datos.safe(context, i++, dial);
	    		  }
	    		  UndoBarController.setUndo(true);
	    		  notifyDataSetChanged();
			}
		});

		//ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);

		// Change icon based on name
		
		/*String s = values[position];
 
		System.out.println(s);
 
		if (s.equals("WindowsMobile")) {
			imageView.setImageResource(R.drawable.windowsmobile_logo);
		} else if (s.equals("iOS")) {
			imageView.setImageResource(R.drawable.ios_logo);
		} else if (s.equals("Blackberry")) {
			imageView.setImageResource(R.drawable.blackberry_logo);
		} else {
			imageView.setImageResource(R.drawable.android_logo);
		}*/
		
		return rowView;
	}	
	
    private void limpiar(View rowView ) {
    	ImageView mov_left = (ImageView)rowView.findViewById(R.id.movl);
    	mov_left.setVisibility(View.INVISIBLE);
    	ImageView mov_swing = (ImageView)rowView.findViewById(R.id.movs);
    	mov_swing.setVisibility(View.INVISIBLE);
    	ImageView mov_right = (ImageView)rowView.findViewById(R.id.movr);
    	mov_right.setVisibility(View.INVISIBLE);
    	ImageView mov_half_right = (ImageView)rowView.findViewById(R.id.movhr);
    	mov_half_right.setVisibility(View.INVISIBLE);
    	ImageView mov_backward = (ImageView)rowView.findViewById(R.id.movb);
    	mov_backward.setVisibility(View.INVISIBLE);
    	ImageView mov_forward = (ImageView)rowView.findViewById(R.id.movf);
    	mov_forward.setVisibility(View.INVISIBLE);
    	ImageView mov_half_left = (ImageView)rowView.findViewById(R.id.movhl);
    	mov_half_left.setVisibility(View.INVISIBLE);
    	TextView txt = (TextView)rowView.findViewById(R.id.txtnmumber);
    	txt.setVisibility(View.INVISIBLE);
    }	
    
    private void mostrarMove(View rowView, Move move) {
    	limpiar(rowView);
    	
    	TextView txt = (TextView)rowView.findViewById(R.id.txtnmumber);
    	txt.setText(move.getNumber() + "");
    	txt.setVisibility(View.VISIBLE);
    	
    	ImageView mov = null;
    	
    	if (move.getArrow() == Move.MOV_LEFT) {
    		mov = (ImageView)rowView.findViewById(R.id.movl);
    		mov.setVisibility(View.VISIBLE);
    	}
    	if (move.getArrow() == Move.MOV_SWING) {
    		mov = (ImageView)rowView.findViewById(R.id.movs);
    		mov.setVisibility(View.VISIBLE);
    	}
    	if (move.getArrow() == Move.MOV_RIGHT) {
    		mov = (ImageView)rowView.findViewById(R.id.movr);
    		mov.setVisibility(View.VISIBLE);
    	}
    	if (move.getArrow() == Move.MOV_HALF_RIGHT) {
    		mov = (ImageView)rowView.findViewById(R.id.movhr);
    		mov.setVisibility(View.VISIBLE);
    	}
    	if (move.getArrow() == Move.MOV_BACKWARD) {
    		mov = (ImageView)rowView.findViewById(R.id.movb);
    		mov.setVisibility(View.VISIBLE);
    	}
    	if (move.getArrow() == Move.MOV_FORWARD) {
    		mov = (ImageView)rowView.findViewById(R.id.movf);
    		mov.setVisibility(View.VISIBLE);
    	}
    	if (move.getArrow() == Move.MOV_HALF_LEFT) {
    		mov = (ImageView)rowView.findViewById(R.id.movhl);
    		mov.setVisibility(View.VISIBLE);
    	}
    	
    	if (move.getColor() == Move.COL_WHITE) {
    		mov.setBackgroundColor(0xFFFFFFFF);
    	}	
    	if (move.getColor() == Move.COL_RED) {
    		mov.setBackgroundColor(0xFFFF0000);    	
    	}
    	if (move.getColor() == Move.COL_GREEN) {
    		mov.setBackgroundColor(0xFF00FF00);
    	}	

    }
    
    @Override
    public void notifyDataSetChanged() {
    	super.notifyDataSetChanged();
	    if(UndoBarController.isUndo()) {
	    	UndoBarController.setUndo(false);
	    	mUndoBarController.showUndoBar(true, "",  null);
	    }
    }

}
