/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.islas;

import java.util.Objects;

/**
 *
 * @author jalvez
 */
public class Dupla<I, J> {

    public final I i;
    public final J j;

    public Dupla(I x, J y) {
        this.i = x;
        this.j = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dupla<?, ?> other = (Dupla<?, ?>) obj;
        if (!Objects.equals(this.i, other.i)) {
            return false;
        }
        if (!Objects.equals(this.j, other.j)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dupla{" + "i=" + i + ", j=" + j + '}';
    }

}
