package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Main;

import static com.montaury.mus.jeu.tour.phases.Jeu.aLeJeu;

public class FauxJeu extends Phase {
  public FauxJeu() {
    super("Faux Jeu");
  }

  @Override
  protected boolean peutParticiper(Main main) {
    return !aLeJeu(main);
  }

  @Override
  protected boolean mainEskuEstMeilleure(Main mainJoueurEsku, Main mainJoueurZaku) {
    return mainJoueurEsku.pointsPourJeu() >= mainJoueurZaku.pointsPourJeu();
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return 1;
  }
}
