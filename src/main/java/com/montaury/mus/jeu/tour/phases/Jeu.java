package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Main;

import java.util.List;

public class Jeu extends Phase {

  private static final List<Integer> RANG_JEUX = List.of(31, 32, 40, 37, 36, 35, 34, 33);

  public Jeu() {
    super("Jeu");
  }

  public static boolean aLeJeu(Main main) {
    return RANG_JEUX.contains(main.pointsPourJeu());
  }

  @Override
  protected boolean peutParticiper(Main main) {
    return aLeJeu(main);
  }

  @Override
  protected boolean mainEskuEstMeilleure(Main mainJoueurEsku, Main mainJoueurZaku) {
    return rangDuJeu(mainJoueurEsku) <= rangDuJeu(mainJoueurZaku);
  }

  private int rangDuJeu(Main main) {
    return RANG_JEUX.indexOf(main.pointsPourJeu());
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return vainqueur.main().pointsPourJeu() == 31 ? 3 : 2;
  }
}
