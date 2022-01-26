package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.Iterator;
import java.util.List;

public class Opposants {
    private Joueur joueurEsku;
    private Joueur joueurZaku;

    public Opposants(Joueur joueurEsku, Joueur joueurZaku) {
        this.joueurEsku = joueurEsku;
        this.joueurZaku = joueurZaku;
    }

    public void tourner() {
        var tmp = joueurEsku;
        joueurEsku = joueurZaku;
        joueurZaku = tmp;
    }

    public Joueur joueurEsku() {
        return joueurEsku;
    }

    public Joueur joueurZaku() {
        return joueurZaku;
    }

    public List<Joueur> dansLOrdre() {
        return List.of(joueurEsku, joueurZaku);
    }
}
