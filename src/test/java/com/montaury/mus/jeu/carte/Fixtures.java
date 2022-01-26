package com.montaury.mus.jeu.carte;

import java.util.Arrays;
import java.util.List;

public class Fixtures {
    public static Paquet paquetEntierCroissant() {
        return paquetAvec(Arrays.asList(Carte.values()));
    }

    public static Paquet paquetAvec(Carte... cartes) {
        return new Paquet(Arrays.asList(cartes), new Defausse());
    }

    private static Paquet paquetAvec(List<Carte> cartes) {
        return new Paquet(cartes, new Defausse());
    }

    public static Defausse defausseAvec(List<Carte> cartes) {
        Defausse defausse = new Defausse();
        defausse.jeter(cartes);
        return defausse;
    }
}
