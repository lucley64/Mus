package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.joueur.Main;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.main;
import static org.assertj.core.api.Assertions.assertThat;

class GrandTest {

    @Test
    void devrait_faire_gagner_le_joueur_esku_s_il_a_la_plus_grande_carte() {
//        Main mainJoueurEsku = main(Carte.AS_BATON, Carte.CINQ_PIECE, Carte.CAVALIER_BATON, Carte.SIX_COUPE);
//        Main mainJoueurZaku = main(Carte.DEUX_BATON, Carte.TROIS_PIECE, Carte.SEPT_BATON, Carte.AS_PIECE);
//
//        boolean mainEskuEstMeilleure = grand.mainEskuEstMeilleure(mainJoueurEsku, mainJoueurZaku);
//
//        assertThat(mainEskuEstMeilleure).isTrue();
    }

    @Test
    void devrait_faire_gagner_le_joueur_zaku_s_il_a_la_plus_grande_carte() {
//        Main mainJoueurEsku = main(Carte.AS_BATON, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.SIX_COUPE);
//        Main mainJoueurZaku = main(Carte.DEUX_BATON, Carte.TROIS_PIECE, Carte.CAVALIER_BATON, Carte.AS_PIECE);
//
//        boolean mainEskuEstMeilleure = grand.mainEskuEstMeilleure(mainJoueurEsku, mainJoueurZaku);
//
//        assertThat(mainEskuEstMeilleure).isFalse();
    }

    @Test
    void devrait_faire_gagner_le_joueur_qui_a_la_seconde_plus_grande_carte_si_la_premiere_est_egale() {
//        Main mainJoueurEsku = main(Carte.AS_BATON, Carte.CINQ_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE);
//        Main mainJoueurZaku = main(Carte.DEUX_BATON, Carte.VALET_PIECE, Carte.QUATRE_BATON, Carte.SEPT_PIECE);
//
//        boolean mainEskuEstMeilleure = grand.mainEskuEstMeilleure(mainJoueurEsku, mainJoueurZaku);
//
//        assertThat(mainEskuEstMeilleure).isFalse();
    }

    @Test
    void devrait_faire_gagner_le_joueur_esku_si_les_deux_mains_sont_egales() {
//        Main mainJoueurEsku = main(Carte.AS_BATON, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE);
//        Main mainJoueurZaku = main(Carte.VALET_PIECE, Carte.SIX_PIECE, Carte.QUATRE_BATON, Carte.AS_PIECE);
//
//        boolean mainEskuEstMeilleure = grand.mainEskuEstMeilleure(mainJoueurEsku, mainJoueurZaku);
//
//        assertThat(mainEskuEstMeilleure).isTrue();
    }

    private final Grand grand = new Grand();
}