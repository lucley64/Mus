package com.montaury.mus.jeu.carte;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static java.util.Collections.shuffle;

public class Paquet {
  public static Paquet nouveauMelange(Defausse defausse) {
    List<Carte> cartes = new ArrayList<>(Carte.toutes());
    shuffle(cartes);
    return new Paquet(cartes, defausse);
  }

  private final Queue<Carte> cartes;
  private final Defausse defausse;

  Paquet(List<Carte> cartes, Defausse defausse) {
    this.cartes = new ArrayDeque<>(cartes);
    this.defausse = defausse;
  }

  public List<Carte> tirer(int nbCartes) {
    if (cartes.size() < nbCartes) {
      var cartesDefaussees = this.defausse.reprendreCartes();
      shuffle(cartesDefaussees);
      cartes.addAll(cartesDefaussees);
    }
    var tirees = new ArrayList<Carte>();
    for (var i = 0; i < nbCartes; i++) {
      tirees.add(cartes.poll());
    }
    return tirees;
  }
}
