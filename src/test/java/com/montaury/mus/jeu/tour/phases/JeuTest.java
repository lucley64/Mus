package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Main;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.main;
import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurAvec;
import static org.assertj.core.api.Assertions.assertThat;

class JeuTest {

    @Test
    void ne_doit_pas_faire_participer_un_joueur_qui_n_a_pas_le_jeu() {
//        Main main = main(Carte.VALET_PIECE, Carte.SIX_PIECE, Carte.QUATRE_BATON, Carte.ROI_BATON);
//
//        boolean peutParticiper = jeu.peutParticiper(main);
//
//        assertThat(peutParticiper).isFalse();
    }

    @Test
    void doit_faire_participer_un_joueur_ayant_le_jeu() {
//        Main main = main(Carte.VALET_PIECE, Carte.CAVALIER_PIECE, Carte.ROI_BATON, Carte.AS_PIECE);
//
//        boolean peutParticiper = jeu.peutParticiper(main);
//
//        assertThat(peutParticiper).isTrue();
    }

    @Test
    void devrait_faire_gagner_le_joueur_ayant_31_par_rapport_a_32() {
//        Main mainJoueurEsku = main(Carte.SIX_COUPE, Carte.ROI_EPEE, Carte.VALET_BATON, Carte.SIX_BATON);
//        Main mainJoueurZaku = main(Carte.VALET_PIECE, Carte.CAVALIER_PIECE, Carte.ROI_BATON, Carte.AS_PIECE);
//
//        boolean mainEskuEstMeilleure = jeu.mainEskuEstMeilleure(mainJoueurEsku, mainJoueurZaku);
//
//        assertThat(mainEskuEstMeilleure).isFalse();
    }

    @Test
    void devrait_faire_gagner_le_joueur_ayant_40_par_rapport_a_37() {
//        Main mainJoueurEsku = main(Carte.SEPT_BATON, Carte.ROI_EPEE, Carte.VALET_BATON, Carte.VALET_COUPE);
//        Main mainJoueurZaku = main(Carte.VALET_PIECE, Carte.CAVALIER_PIECE, Carte.ROI_BATON, Carte.ROI_COUPE);
//
//        boolean mainEskuEstMeilleure = jeu.mainEskuEstMeilleure(mainJoueurEsku, mainJoueurZaku);
//
//        assertThat(mainEskuEstMeilleure).isFalse();
    }

    @Test
    void devrait_faire_gagner_le_joueur_ayant_36_par_rapport_a_33() {
//        Main mainJoueurEsku = main(Carte.SEPT_BATON, Carte.SIX_BATON, Carte.VALET_BATON, Carte.VALET_COUPE);
//        Main mainJoueurZaku = main(Carte.VALET_PIECE, Carte.CAVALIER_PIECE, Carte.ROI_BATON, Carte.TROIS_BATON);
//
//        boolean mainEskuEstMeilleure = jeu.mainEskuEstMeilleure(mainJoueurEsku, mainJoueurZaku);
//
//        assertThat(mainEskuEstMeilleure).isTrue();
    }

    @Test
    void devrait_faire_gagner_le_joueur_esku_en_cas_d_egalite() {
//        Main mainJoueurEsku = main(Carte.VALET_EPEE, Carte.AS_BATON, Carte.VALET_BATON, Carte.VALET_COUPE);
//        Main mainJoueurZaku = main(Carte.VALET_PIECE, Carte.CAVALIER_PIECE, Carte.ROI_BATON, Carte.AS_PIECE);
//
//        boolean mainEskuEstMeilleure = jeu.mainEskuEstMeilleure(mainJoueurEsku, mainJoueurZaku);
//
//        assertThat(mainEskuEstMeilleure).isTrue();
    }

    @Test
    void devrait_accorder_un_bonus_de_3_pour_31() {
//        Joueur joueurEsku = unJoueurAvec(main(Carte.VALET_EPEE, Carte.AS_BATON, Carte.VALET_BATON, Carte.VALET_COUPE));
//
//        int pointsBonus = jeu.pointsBonus(joueurEsku);
//
//        assertThat(pointsBonus).isEqualTo(3);
    }

    @Test
    void devrait_accorder_un_bonus_de_2_pour_32() {
//        Joueur joueurEsku = unJoueurAvec(main(Carte.VALET_EPEE, Carte.DEUX_BATON, Carte.VALET_BATON, Carte.VALET_COUPE));
//
//        int pointsBonus = jeu.pointsBonus(joueurEsku);
//
//        assertThat(pointsBonus).isEqualTo(2);
    }

    private final Jeu jeu = new Jeu();
}