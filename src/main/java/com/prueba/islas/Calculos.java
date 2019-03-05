/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.islas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jalvez
 */
public class Calculos {

    public static int calcularIslas(int filas, int columnas, Integer[][] region) {
        //inicializo visitados en 0
        Integer[][] visitados = new Integer[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                visitados[i][j] = 0;
            }
        }
        int cantIslas = 0;
        //
        Dupla<Integer, Integer> puntoCualquiera = new Dupla(0, 0);
        while (puntoCualquiera != null) {
            int filaActual = puntoCualquiera.i;
            int columnaActual = puntoCualquiera.j;
            int valor = region[filaActual][columnaActual];
            visitados[filaActual][columnaActual] = 1;
            //
            if (valor == 1) {
                //si cai en una isla no visitada, recorro isla (por bfs)
                List<Dupla<Integer, Integer>> colaVisita = new ArrayList();
                colaVisita.add(puntoCualquiera);
                while (!colaVisita.isEmpty()) {
                    Dupla<Integer, Integer> puntoActual = colaVisita.remove(0);
                    List<Dupla<Integer, Integer>> adyacentes = obtenerTierrasAdyacentesNoVisitadas(puntoActual.i, puntoActual.j, region, visitados, filas, columnas);
                    Iterator it = adyacentes.iterator();
                    while (it.hasNext()) {
                        Dupla<Integer, Integer> ady = (Dupla) it.next();
                        visitados[ady.i][ady.j] = 1;
                        colaVisita.add(ady);
                    }
                }
                cantIslas++;
            }
            puntoCualquiera = obtenerTierraNoVisitada(filas, columnas, region, visitados);
        }
        return cantIslas;
    }

    private static Dupla<Integer, Integer> obtenerTierraNoVisitada(int filas, int columnas, Integer[][] region, Integer[][] visitados) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (visitados[i][j] == 0) {
                    return new Dupla(i, j);
                }
            }
        }
        return null;
    }

    private static List<Dupla<Integer, Integer>> obtenerTierrasAdyacentesNoVisitadas(int filaActual,
            int columnaActual,
            Integer[][] region,
            Integer[][] visitados,
            int filasTope,
            int columnasTope) {
        List<Dupla<Integer, Integer>> adyacentes = new ArrayList();
        //Caso 1: resto 1 a filaActual
        adyacentes.add(new Dupla(filaActual - 1, columnaActual));
        //Caso 2: sumo 1 a filaActual
        adyacentes.add(new Dupla(filaActual + 1, columnaActual));
        //Caso 3: resto 1 a columnaActual
        adyacentes.add(new Dupla(filaActual, columnaActual - 1));
        //Caso 4: sumo 1 a columnaActual
        adyacentes.add(new Dupla(filaActual, columnaActual + 1));
        //Caso 5: resto 1 a filaActual y resto 1 columnaActual
        adyacentes.add(new Dupla(filaActual - 1, columnaActual - 1));
        //Caso 6: resto 1 a filaActual y sumo 1 a columnaActual
        adyacentes.add(new Dupla(filaActual - 1, columnaActual + 1));
        //Caso 7: sumo 1 a filaActual y resto 1 a columnaActual
        adyacentes.add(new Dupla(filaActual + 1, columnaActual - 1));
        //Caso 8: sumo 1 a filaActual y sumo 1 a columnaActual
        adyacentes.add(new Dupla(filaActual + 1, columnaActual + 1));
        //verifico que no este en visitados, que las coordenadas esten entre cero y el tope y que sea un punto de tierra
        List<Dupla<Integer, Integer>> adyacentesARemover = new ArrayList();
        Iterator<Dupla<Integer, Integer>> it = adyacentes.iterator();
        while (it.hasNext()) {
            Dupla<Integer, Integer> ady = it.next();
            if (ady.i < 0 || ady.j < 0 || ady.i >= filasTope || ady.j >= columnasTope || visitados[ady.i][ady.j] == 1 || region[ady.i][ady.j] == 0) {
                adyacentesARemover.add(ady);
                //si es agua ya marco como visitada para no volver a elegirla en obtenerTierraNoVisitada
                if (!(ady.i < 0 || ady.j < 0 || ady.i >= filasTope || ady.j >= columnasTope) && region[ady.i][ady.j] == 0) {
                    visitados[ady.i][ady.j] = 1;
                }
            }
        }
        it = adyacentesARemover.iterator();
        while (it.hasNext()) {
            Dupla<Integer, Integer> ady = it.next();
            adyacentes.remove(ady);
        }
        return adyacentes;
    }

}
