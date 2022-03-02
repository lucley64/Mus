package com.montaury.mus.jeu;

import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurFaisantChoix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class MancheTest {

    @BeforeEach
    void setUp() {
        manche = new Manche(mock(Evenements.class));
    }

    @Test
    void devrait_terminer_la_manche_si_hordago_au_grand() {
        var equipeEsku = new Equipe(unJoueurFaisantChoix(new Mintza(), new Hordago()),
                Joueur.ordinateur(),
                "hordago + rien");
        var equipeZaku = new Equipe(unJoueurFaisantChoix(new Kanta()),
                Joueur.ordinateur(),
                "accepte + rien");


        var resultat = manche.jouer(new Opposants(equipeEsku, equipeZaku));

        assertThat(resultat.vainqueur()).isNotNull();
        assertThat(resultat.pointsVaincu()).isZero();
    }

    @Test
    void devrait_terminer_la_manche_si_un_joueur_a_40_points() {
        var joueurEsku1 = unJoueurFaisantChoix(new Mintza(), new Imido(), new Gehiago(2));
        var joueurEsku2 = unJoueurFaisantChoix(new Mintza(), new Paso());
        var joueurZaku1 = unJoueurFaisantChoix(new Gehiago(40), new Tira());
        var joueurZaku2 = unJoueurFaisantChoix(new Paso());

        var equipeEsku = new Equipe(joueurEsku1, joueurEsku2, joueurEsku1.nom() + " et " + joueurEsku2.nom());
        var equipeZaku = new Equipe(joueurZaku1, joueurZaku2, joueurZaku1.nom() + " et " + joueurZaku2.nom());

        var resultat = manche.jouer(new Opposants(equipeEsku, equipeZaku));

        assertThat(resultat.vainqueur()).isEqualTo(equipeEsku);
        assertThat(resultat.pointsVaincu()).isZero();
    }

    @Test
    void devrait_changer_l_ordre_des_opposants_a_la_fin_du_tour() {
        var joueurEsku1 = unJoueurFaisantChoix(new Mintza(), new Hordago());
        var joueurEsku2 = unJoueurFaisantChoix(new Kanta());
        var joueurZaku1 = unJoueurFaisantChoix(new Kanta());
        var joueurZaku2 = unJoueurFaisantChoix(new Kanta());

        var equipeEsku = new Equipe(joueurEsku1, joueurEsku2, joueurEsku1.nom() + " et " + joueurEsku2.nom());
        var equipeZaku = new Equipe(joueurZaku1, joueurZaku2, joueurZaku1.nom() + " et " + joueurZaku2.nom());
        var opposants = new Opposants(equipeEsku, equipeZaku);

        manche.jouer(opposants);

        assertThat(opposants.dansLOrdre()).containsExactly(equipeZaku.joueur1(),equipeEsku.joueur1(),equipeZaku.joueur2()
        ,equipeEsku.joueur2());
    }

    private Manche manche;
}
