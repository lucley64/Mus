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

  public Equipe EquipeEsku() {
    return equipeEsku;
  }

  public Equipe EquipeZaku() {
    return equipeZaku;
  }

  public List<Equipe> dansLOrdre() {
    return List.of(equipeEsku, equipeZaku);
  }
}
