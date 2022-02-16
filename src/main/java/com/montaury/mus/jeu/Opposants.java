package com.montaury.mus.jeu;

import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;

import java.util.List;

public class Opposants {
    private Equipe equipeEsku;
    private Equipe equipeZaku;

    public Opposants(Equipe equipeEsku, Equipe equipeZaku) {
        this.equipeEsku = equipeEsku;
        this.equipeZaku = equipeZaku;
    }

    public void tourner() {
        var tmp = equipeEsku;
        equipeEsku = equipeZaku;
        equipeZaku = tmp;
    }

    public Equipe equipeEsku() {
        return equipeEsku;
    }

    public Equipe equipeZaku() {
        return equipeZaku;
    }

    public List<Joueur> dansLOrdre() {
        return List.of(equipeEsku.joueur1(), equipeZaku.joueur1(),
                equipeEsku.joueur2(), equipeZaku.joueur2());
    }
}
