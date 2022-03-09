package com.montaury.mus.jeu;

import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;

import java.util.*;

public class Opposants {
    private Equipe equipeEsku;
    private Equipe equipeZaku;
    private List<Joueur> dansLOrdre;

    public Opposants(Equipe equipeEsku, Equipe equipeZaku) {
        this.equipeEsku = equipeEsku;
        this.equipeZaku = equipeZaku;
        this.dansLOrdre = List.of(equipeEsku.joueur1(), equipeZaku.joueur1(),
                equipeEsku.joueur2(), equipeZaku.joueur2());
    }

    public void tourner() {
        List<Joueur> nouvelOrdre = new ArrayList<>();
        nouvelOrdre.add(dansLOrdre.get(1));
        nouvelOrdre.add(dansLOrdre.get(2));
        nouvelOrdre.add(dansLOrdre.get(3));
        nouvelOrdre.add(dansLOrdre.get(0));
        dansLOrdre = nouvelOrdre;
    }

    public Equipe equipeEsku() {
        return equipeEsku;
    }

    public Equipe equipeZaku() {
        return equipeZaku;
    }

    public List<Joueur> dansLOrdre() {
        return dansLOrdre;
    }
}
