package com.staw.util;

import java.util.Random;

public class Dado {
	
	
	public static int tirar(int caras) {
        Random rn = new Random();
        int numero = Math.abs(rn.nextInt() % caras);
        return numero  + 1;
	}
	
	public static float grados(int opciones) {
        int grados = 360 / opciones;
        Random rn = new Random();
        int n = tirar(15);
        float numero = n * grados;
        return numero;
	}
	
	public static void main(String[] args) {
		
		for (int i= 0; i<100; i++) {
			System.out.println(Dado.tirar(6));
		}
	}
	
	

}
