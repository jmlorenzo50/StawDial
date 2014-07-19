package com.staw.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;

public class Datos {
	
	private static List<Dial> memoria;
	
	public static void safe(Context context, int number, Dial valor) {
		int total = totalShip(context);
		String clave = "ship" + number;
		SharedPreferences prefs = context.getSharedPreferences("StawDialShips", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(clave, valor.toSerial());
		if(total < number) {
			editor.putInt("ships", number);
		}
		
		editor.commit();	
	}
	
	public static Dial load(Context context, int number) {
		String clave = "ship" + number;
		SharedPreferences prefs = context.getSharedPreferences("StawDialShips",Context.MODE_PRIVATE);
		String serial = prefs.getString(clave,"");
		return Dial.toDial(serial);
	}
	
	public static int totalShip(Context context) {
		String clave = "ships";
		SharedPreferences prefs = context.getSharedPreferences("StawDialShips",Context.MODE_PRIVATE);
		int total = prefs.getInt(clave,0);
		return total;
	}
	
	public static List<Dial> load(Context context) {
		int n = totalShip(context);
		List<Dial> salida = new ArrayList<Dial>();
		for (int i = 0; i < n; i++) {
			salida.add(load(context, i+1));
		}
		return salida;
	}
	
	
	public static void clear(Context context) {
		String clave = "ships";
		SharedPreferences prefs = context.getSharedPreferences("StawDialShips",Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.clear();
		editor.putInt(clave,0);
		editor.commit();
	}
	
	public static void memoriza(List<Dial> datos) {
		memoria = new ArrayList<Dial>();
		for (Dial dial : datos) {
			memoria.add(dial);	
		}
	}
	
	public static void recuperar(Context context) {
		if (memoria != null) {
			clear(context);
			for (int i = 0; i < memoria.size(); i++) {
				safe(context, i+1, memoria.get(i));
			}
			memoria = null;
		}
	}

}
