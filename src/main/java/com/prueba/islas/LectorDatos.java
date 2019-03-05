/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.islas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author jalvez
 */
public class LectorDatos {

    public static void main(String[] args) throws FileNotFoundException {
        //el scanner puede ser tanto por File como por System.in (consola)
        System.out.println("Leyendo archivo test1.txt en la raiz");
        Scanner scan = new Scanner(new File("test1.txt")).useDelimiter("^0\\s0$");
        while (scan.hasNext()) {
            String cabezal = scan.nextLine();
            //calculo largo-ancho
            int columnas = Integer.parseInt(cabezal.split("\\s")[0]);
            int filas = Integer.parseInt(cabezal.split("\\s")[1]);
            //armo region como matriz
            if (columnas > 0 && filas > 0) {
                Integer[][] region = new Integer[filas][columnas];
                for (int i = 0; i < filas; i++) {
                    String line = scan.nextLine();
                    String[] fila_array = line.split("\\s");
                    for (int j = 0; j < columnas; j++) {
                        region[i][j] = Integer.parseInt(fila_array[j]);
                    }
                }
                int cantIslas = Calculos.calcularIslas(filas, columnas, region);
                System.out.println(cantIslas);
            }
        }
    }

}
