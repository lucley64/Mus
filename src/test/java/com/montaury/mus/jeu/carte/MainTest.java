package com.montaury.mus.jeu.carte;

import com.montaury.mus.jeu.joueur.Main;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.main;
import static org.assertj.core.api.Assertions.assertThat;

class MainTest {
  @Test
  void devrait_additionner_les_valeurs_des_cartes_pour_les_points_du_jeu() {
    Main main = main(Carte.QUATRE_BATON, Carte.CINQ_PIECE, Carte.SIX_COUPE, Carte.SEPT_EPEE);

    int points = main.pointsPourJeu();

    assertThat(points).isEqualTo(22);
  }

  @Test
  void devrait_compter_10_pour_les_figures_pour_les_points_du_jeu() {
    Main main = main(Carte.VALET_BATON, Carte.CAVALIER_COUPE, Carte.ROI_EPEE, Carte.DEUX_PIECE);

    int points = main.pointsPourJeu();

    assertThat(points).isEqualTo(32);
  }
}