package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;
import java.util.ArrayList;
import java.util.List;

public class Participants {
  private final List<Equipe> dansLOrdre;

  public Participants(List<Equipe> dansLOrdre) {
    this.dansLOrdre = dansLOrdre;
  }

  public boolean aucun() {
    return dansLOrdre.isEmpty();
  }

  public boolean estUnique() {
    return dansLOrdre.size() == 1;
  }

  public Joueur premier() {
    return dansLOrdre.get(0).premier();
  }

  public Joueur adversaireDe(Joueur joueurParlant) {
    return joueurParlant == premier() ? dansLOrdre.get(1).premier() : premier();
  }

  public Iterable<Equipe> dansLOrdre() {
    return dansLOrdre;
  }

  public Participants retirer(Equipe equipe) {
    var equipes = new ArrayList<>(dansLOrdre);
    equipes.remove(equipe);
    return new Participants(equipes);
  }
}
