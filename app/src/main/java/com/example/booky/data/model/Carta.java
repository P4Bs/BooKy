package com.example.booky.data.model;

import java.util.List;

public class Carta {
    private List<Plato> carta;

    public Carta(List<Plato> carta){
        this.carta = carta;
    }

    public Plato getPlatoAtIndex(int index){
        return carta.get(index);
    }
}
