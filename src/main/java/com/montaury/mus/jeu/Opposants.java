package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Equipe;

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

  public List<Equipe> dansLOrdre() {
    return List.of(equipeEsku, equipeZaku);
  }
}
