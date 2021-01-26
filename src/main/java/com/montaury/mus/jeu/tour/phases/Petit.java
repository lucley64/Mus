package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Main;

import static com.montaury.mus.jeu.carte.ValeurCarte.Comparaison.PLUS_GRANDE;
import static com.montaury.mus.jeu.carte.ValeurCarte.Comparaison.PLUS_PETITE;

public class Petit extends Phase {
  public Petit() {
    super("Petit");
  }

  @Override
  protected boolean mainEskuEstMeilleure(Main mainJoueurEsku, Main mainJoueurZaku) {
    var cartesJoueurEsku = mainJoueurEsku.cartesDuPlusGrandAuPlusPetit();
    var cartesJoueurZaku = mainJoueurZaku.cartesDuPlusGrandAuPlusPetit();

    for (int i = Main.TAILLE - 1; i >= 0; i--) {
      var comparaison = cartesJoueurEsku.get(i).comparerAvec(cartesJoueurZaku.get(i));
      if (comparaison == PLUS_PETITE) {
        return true;
      }
      if (comparaison == PLUS_GRANDE) {
        return false;
      }
    }
    return true;
  }
}
