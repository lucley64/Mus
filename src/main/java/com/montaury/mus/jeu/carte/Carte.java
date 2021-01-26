package com.montaury.mus.jeu.carte;

import java.util.Set;

import static com.montaury.mus.jeu.carte.TypeCarte.BATON;
import static com.montaury.mus.jeu.carte.TypeCarte.COUPE;
import static com.montaury.mus.jeu.carte.TypeCarte.EPEE;
import static com.montaury.mus.jeu.carte.TypeCarte.PIECE;
import static com.montaury.mus.jeu.carte.ValeurCarte.AS;
import static com.montaury.mus.jeu.carte.ValeurCarte.CAVALIER;
import static com.montaury.mus.jeu.carte.ValeurCarte.CINQ;
import static com.montaury.mus.jeu.carte.ValeurCarte.DEUX;
import static com.montaury.mus.jeu.carte.ValeurCarte.QUATRE;
import static com.montaury.mus.jeu.carte.ValeurCarte.ROI;
import static com.montaury.mus.jeu.carte.ValeurCarte.SEPT;
import static com.montaury.mus.jeu.carte.ValeurCarte.SIX;
import static com.montaury.mus.jeu.carte.ValeurCarte.TROIS;
import static com.montaury.mus.jeu.carte.ValeurCarte.VALET;

public enum Carte {
  AS_BATON(AS, BATON),
  AS_COUPE(AS, COUPE),
  AS_EPEE(AS, EPEE),
  AS_PIECE(AS, PIECE),

  DEUX_BATON(DEUX, BATON),
  DEUX_COUPE(DEUX, COUPE),
  DEUX_EPEE(DEUX, EPEE),
  DEUX_PIECE(DEUX, PIECE),

  TROIS_BATON(TROIS, BATON),
  TROIS_COUPE(TROIS, COUPE),
  TROIS_EPEE(TROIS, EPEE),
  TROIS_PIECE(TROIS, PIECE),

  QUATRE_BATON(QUATRE, BATON),
  QUATRE_COUPE(QUATRE, COUPE),
  QUATRE_EPEE(QUATRE, EPEE),
  QUATRE_PIECE(QUATRE, PIECE),

  CINQ_BATON(CINQ, BATON),
  CINQ_COUPE(CINQ, COUPE),
  CINQ_EPEE(CINQ, EPEE),
  CINQ_PIECE(CINQ, PIECE),

  SIX_BATON(SIX, BATON),
  SIX_COUPE(SIX, COUPE),
  SIX_EPEE(SIX, EPEE),
  SIX_PIECE(SIX, PIECE),

  SEPT_BATON(SEPT, BATON),
  SEPT_COUPE(SEPT, COUPE),
  SEPT_EPEE(SEPT, EPEE),
  SEPT_PIECE(SEPT, PIECE),

  VALET_BATON(VALET, BATON),
  VALET_COUPE(VALET, COUPE),
  VALET_EPEE(VALET, EPEE),
  VALET_PIECE(VALET, PIECE),

  CAVALIER_BATON(CAVALIER, BATON),
  CAVALIER_COUPE(CAVALIER, COUPE),
  CAVALIER_EPEE(CAVALIER, EPEE),
  CAVALIER_PIECE(CAVALIER, PIECE),

  ROI_BATON(ROI, BATON),
  ROI_COUPE(ROI, COUPE),
  ROI_EPEE(ROI, EPEE),
  ROI_PIECE(ROI, PIECE);

  public static Set<Carte> toutes() {
    return Set.of(values());
  }

  private final TypeCarte type;
  private final ValeurCarte valeur;

  Carte(ValeurCarte valeur, TypeCarte type) {
    this.valeur = valeur;
    this.type = type;
  }

  public TypeCarte type() {
    return type;
  }

  public int valeur() {
    return valeur.valeur();
  }

  public ValeurCarte valeurCarte() {
    return valeur;
  }

  public int pointsPourJeu() {
    return valeur.valeurPourJeu();
  }

  public ValeurCarte.Comparaison comparerAvec(Carte autre) {
    return valeur.comparerAvec(autre.valeur);
  }
}
