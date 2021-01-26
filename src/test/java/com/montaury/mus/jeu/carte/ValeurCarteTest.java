package com.montaury.mus.jeu.carte;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValeurCarteTest {
  @Test
  void devrait_retourner_la_valeur_correcte_pour_le_jeu() {
    assertThat(ValeurCarte.AS.valeurPourJeu()).isEqualTo(1);
    assertThat(ValeurCarte.DEUX.valeurPourJeu()).isEqualTo(2);
    assertThat(ValeurCarte.TROIS.valeurPourJeu()).isEqualTo(3);
    assertThat(ValeurCarte.QUATRE.valeurPourJeu()).isEqualTo(4);
    assertThat(ValeurCarte.CINQ.valeurPourJeu()).isEqualTo(5);
    assertThat(ValeurCarte.SIX.valeurPourJeu()).isEqualTo(6);
    assertThat(ValeurCarte.SEPT.valeurPourJeu()).isEqualTo(7);
    assertThat(ValeurCarte.VALET.valeurPourJeu()).isEqualTo(10);
    assertThat(ValeurCarte.CAVALIER.valeurPourJeu()).isEqualTo(10);
    assertThat(ValeurCarte.ROI.valeurPourJeu()).isEqualTo(10);
  }
}