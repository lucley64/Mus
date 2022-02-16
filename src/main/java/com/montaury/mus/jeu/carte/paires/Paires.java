package com.montaury.mus.jeu.carte.paires;

public abstract class Paires {
    private final int pointsBonus;

    protected Paires(int pointsBonus) {
        this.pointsBonus = pointsBonus;
    }

    public final int pointsBonus() {
        return pointsBonus;
    }

    public abstract boolean estMeilleureOuEgaleA(Paires paires);
}
