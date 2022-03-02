package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Main;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.main;
import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurAvec;
import static org.assertj.core.api.Assertions.assertThat;

class FauxJeuTest {

    @Test
    void ne_doit_pas_faire_participer_un_joueur_ayant_le_jeu() {
//        Main main = main(Carte.VALET_PIECE, Carte.CAVALIER_PIECE, Carte.ROI_BATON, Carte.AS_PIECE);
//
//        boolean peutParticiper = fauxJeu.peutParticiper(main);
//
//        assertThat(peutParticiper).isFalse();
    }

    @Test
    void doit_faire_participer_un_joueur_n_ayant_pas_le_jeu() {
//        Main main = main(Carte.ROI_BATON, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE);
//
//        boolean peutParticiper = fauxJeu.peutParticiper(main);
//
//        assertThat(peutParticiper).isTrue();
    }

    @Test
    void devrait_faire_gagner_le_joueur_ayant_le_plus_grand_nombre_de_points() {
//        Main mainJoueurEsku = main(Carte.AS_BATON, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE);
//        Main mainJoueurZaku = main(Carte.VALET_PIECE, Carte.SIX_PIECE, Carte.QUATRE_BATON, Carte.ROI_COUPE);
//
//        boolean mainEskuEstMeilleure = fauxJeu.mainEskuEstMeilleure(mainJoueurEsku, mainJoueurZaku);
//
//        assertThat(mainEskuEstMeilleure).isFalse();
    }

    @Test
    void devrait_faire_gagner_le_joueur_esku_en_cas_d_egalite() {
//        Main mainJoueurEsku = main(Carte.AS_BATON, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE);
//        Main mainJoueurZaku = main(Carte.VALET_PIECE, Carte.SIX_PIECE, Carte.QUATRE_BATON, Carte.AS_PIECE);
//
//        boolean mainEskuEstMeilleure = fauxJeu.mainEskuEstMeilleure(mainJoueurEsku, mainJoueurZaku);
//
//        assertThat(mainEskuEstMeilleure).isTrue();
    }

    @Test
    void doit_faire_gagner_un_bonus_de_1() {
//        Joueur joueurEsku = unJoueurAvec(main(Carte.AS_BATON, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE));
//
//        int bonus = fauxJeu.pointsBonus(joueurEsku);
//
//        assertThat(bonus).isEqualTo(1);
    }

    private final FauxJeu fauxJeu = new FauxJeu();
}
