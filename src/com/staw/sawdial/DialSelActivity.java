package com.staw.sawdial;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.staw.adapter.DialArrayAdapter;
import com.staw.adapter.UndoBarController;
import com.staw.util.Dado;
import com.staw.util.Datos;
import com.staw.util.Dial;

public class DialSelActivity extends Activity implements UndoBarController.UndoListener {

	private UndoBarController mUndoBarController;
	
	private List<Dial> diales;
	
	private DialArrayAdapter adapter;
	
	private int MAX_DIAL = 21;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dial_sel);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		diales = new ArrayList<Dial>();
		Context context = this;
		int total = Datos.totalShip(context);
		for (int i = 1; i <= total; i++) {
			Dial dial = Datos.load(context, i);
			if (!dial.hasError()) {
				diales.add(dial);
			}
		}
		
		//TextView textView1 = (TextView) findViewById(R.id.textView1);
		//textView1.setText("Maximum number of dials is " + MAX_DIAL);
		
		ListView listview = (ListView) findViewById(R.id.listView1);	

		adapter = new DialArrayAdapter(this, diales, mUndoBarController);
	    listview.setAdapter(adapter);
	    listview.setOnItemClickListener(new OnItemClickListener() {
	    	  @Override
	    	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    		  // Launching new Activity on selecting single List Item
	              Intent i = new Intent(getApplicationContext(), DialAleatorio.class);
	              // sending data to new activity
	              i.putExtra("dialPosition", position+1);
	              startActivity(i);	    		  
	    	  }
	    }); 
	    
	    mUndoBarController = new UndoBarController(findViewById(R.id.undobar), this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.dial_sel, menu);
		return true;
	}
	
	public void addDial(View view) {
		if (diales.size() < MAX_DIAL) {
	        Intent i = new Intent(getApplicationContext(), DialNewShip.class);
	        startActivity(i);
		} else {
			alert("The space is full.");
		}
	}
	
	public void tapAllDial(View view) {
    	Context context = this;
		for (int i = 0; i < diales.size(); i++) {
			Dial dial = diales.get(i);
	    	dial.setHide(true);
			Datos.safe(context, i+1, dial);
		}
		ListView listview = (ListView) findViewById(R.id.listView1);
		listview.refreshDrawableState();
		this.onResume();
		
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
	
    public void lanzar(View view) {
    	Context context = this;
		for (int i = 0; i < diales.size(); i++) {
			Dial dial = diales.get(i);
	    	int opciones = dial.getMoves().size();
	    	int index = Dado.tirar(opciones) - 1;
	    	dial.setIndex(index);
	    	
			Datos.safe(context, i+1, dial);
		}
		ListView listview = (ListView) findViewById(R.id.listView1);
		listview.refreshDrawableState();
		this.onResume();
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mUndoBarController.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mUndoBarController.onRestoreInstanceState(savedInstanceState);
    }

    
	@Override
	public void onUndo(Parcelable token) {
		Context context = this;
		Datos.recuperar(context);
		onResume();
	}
	

}
