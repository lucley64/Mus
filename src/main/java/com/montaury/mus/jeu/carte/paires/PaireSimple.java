package com.montaury.mus.jeu.carte.paires;


import com.montaury.mus.jeu.carte.ValeurCarte;

public class PaireSimple extends Paires {
  private final ValeurCarte valeurCarte;
  public static final int POINTS_BONUS = 1;


  public PaireSimple(ValeurCarte valeurCarte) {
    super(POINTS_BONUS);
    this.valeurCarte = valeurCarte;
  }

  public ValeurCarte valeur() {
    return valeurCarte;
  }

  public boolean estMeilleureOuEgaleA(Paires paires) {
    return paires instanceof PaireSimple && valeurCarte.valeur() >= ((PaireSimple) paires).valeur().valeur();
  }

  boolean estMeilleureQue(PaireSimple autrePaire) {
    return valeurCarte.aUnRangSuperieurA(autrePaire.valeur());
  }

  boolean estIdentiqueA(PaireSimple autrePaire) {
    return valeurCarte.estDeMemeRangQue(autrePaire.valeur());
  }
}
