package com.montaury.mus.jeu.tour;

import com.montaury.mus.jeu.Manche;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.carte.Defausse;
import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Idoki;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mintza;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.carte.Fixtures.paquetAvec;
import static com.montaury.mus.jeu.carte.Fixtures.paquetEntierCroissant;
import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurFaisantChoix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TourTest {

    @BeforeEach
    void setUp() {
        evenementsDeJeu = mock(Evenements.class);
        tour = new Tour(evenementsDeJeu, paquetEntierCroissant(), new Defausse());
    }

    @Test
    void devrait_donner_tous_les_points_a_l_equipe_esku_si_le_joueur_zaku_fait_tira() {
        var joueurEsku1 = unJoueurFaisantChoix(new Mintza(), new Imido(), new Imido(), new Imido(), new Imido());
        var joueurEsku2 = unJoueurFaisantChoix();
        var joueurZaku1 = unJoueurFaisantChoix(new Tira());
        var joueurZaku2 = unJoueurFaisantChoix(new Tira());

        var equipeEsku = new Equipe(joueurEsku1, joueurEsku2, joueurEsku1.nom() + " et " + joueurEsku2.nom());
        var equipeZaku = new Equipe(joueurZaku1, joueurZaku2, joueurZaku1.nom() + " et " + joueurZaku2.nom());
        var opposants = new Opposants(equipeEsku, equipeZaku);
        var score = new Manche.Score(opposants);

        tour.jouer(opposants, score);

        assertThat(score.vainqueur()).isEmpty();
        assertThat(score.scoreParEquipe()).containsEntry(equipeEsku, 8);
        assertThat(score.scoreParEquipe()).containsEntry(equipeZaku, 0);
    }

    @Test
    void devrait_repartir_les_points_si_tout_est_paso() {
        var joueurEsku1 = unJoueurFaisantChoix(new Mintza(), new Paso(), new Paso(), new Paso(), new Paso());
        var joueurZaku1 = unJoueurFaisantChoix(new Paso());
        var joueurEsku2 = unJoueurFaisantChoix(new Paso());
        var joueurZaku2 = unJoueurFaisantChoix(new Paso());

        var equipeEsku = new Equipe(joueurEsku1, joueurEsku2, joueurEsku1.nom() + " et " + joueurEsku2.nom());
        var equipeZaku = new Equipe(joueurZaku1, joueurZaku2, joueurZaku1.nom() + " et " + joueurZaku2.nom());
        var opposants = new Opposants(equipeEsku, equipeZaku);
        var score = new Manche.Score(opposants);

        tour.jouer(opposants, score);

        assertThat(score.vainqueur()).isEmpty();
        assertThat(score.scoreParEquipe()).containsEntry(equipeEsku, 1);
        assertThat(score.scoreParEquipe()).containsEntry(equipeZaku, 5);
    }

    @Test
    void devrait_faire_gagner_le_joueur_zaku_si_hordago_au_grand() {
        var joueurEsku1 = unJoueurFaisantChoix(new Mintza(), new Hordago());
        var joueurZaku1 = unJoueurFaisantChoix(new Kanta());
        var joueurEsku2 = unJoueurFaisantChoix();
        var joueurZaku2 = unJoueurFaisantChoix();

        var equipeEsku = new Equipe(joueurEsku1, joueurEsku2, joueurEsku1.nom() + " et " + joueurEsku2.nom());
        var equipeZaku = new Equipe(joueurZaku1, joueurZaku2, joueurZaku1.nom() + " et " + joueurZaku2.nom());
        var opposants = new Opposants(equipeEsku, equipeZaku);
        var score = new Manche.Score(opposants);

        tour.jouer(opposants, score);

        assertThat(score.vainqueur()).contains(equipeZaku);
        assertThat(score.scoreParEquipe()).containsEntry(equipeEsku, 0);
        assertThat(score.scoreParEquipe()).containsEntry(equipeZaku, 40);
    }

    @Test
    void devrait_partager_les_points_si_tout_est_idoki() {
        var joueurEsku1 = unJoueurFaisantChoix(new Mintza(), new Imido(), new Imido(), new Imido(), new Imido());
        var joueurZaku1 = unJoueurFaisantChoix(new Idoki());
        var joueurEsku2 = unJoueurFaisantChoix();
        var joueurZaku2 = unJoueurFaisantChoix();

        var equipeEsku = new Equipe(joueurEsku1, joueurEsku2, joueurEsku1.nom() + " et " + joueurEsku2.nom());
        var equipeZaku = new Equipe(joueurZaku1, joueurZaku2, joueurZaku1.nom() + " et " + joueurZaku2.nom());
        var opposants = new Opposants(equipeEsku, equipeZaku);
        var score = new Manche.Score(opposants);

        tour.jouer(opposants, score);

        assertThat(score.vainqueur()).isEmpty();
        assertThat(score.scoreParEquipe()).containsEntry(equipeEsku, 2);
        assertThat(score.scoreParEquipe()).containsEntry(equipeZaku, 10);
    }

    @Test
    void devrait_partager_les_points_si_tout_est_gehiago_puis_idoki() {
        var joueurEsku1 = unJoueurFaisantChoix(new Mintza(), new Imido(), new Idoki(), new Imido(), new Idoki(), new Imido(), new Idoki(), new Imido(), new Idoki());
        var joueurZaku1 = unJoueurFaisantChoix(new Gehiago(2));
        var joueurEsku2 = unJoueurFaisantChoix();
        var joueurZaku2 = unJoueurFaisantChoix();

        var equipeEsku = new Equipe(joueurEsku1, joueurEsku2, joueurEsku1.nom() + " et " + joueurEsku2.nom());
        var equipeZaku = new Equipe(joueurZaku1, joueurZaku2, joueurZaku1.nom() + " et " + joueurZaku2.nom());
        var opposants = new Opposants(equipeEsku, equipeZaku);
        var score = new Manche.Score(opposants);

        tour.jouer(opposants, score);

        assertThat(score.vainqueur()).isEmpty();
        assertThat(score.scoreParEquipe()).containsEntry(equipeEsku, 4);
        assertThat(score.scoreParEquipe()).containsEntry(equipeZaku, 16);
    }

    @Test
    void devrait_privilegier_le_joueur_esku_si_les_mains_sont_identiques() {
        var joueurEsku1 = unJoueurFaisantChoix(new Mintza(), new Imido(), new Imido(), new Imido(), new Imido());
        var joueurZaku1 = unJoueurFaisantChoix(new Idoki());
        var joueurEsku2 = unJoueurFaisantChoix();
        var joueurZaku2 = unJoueurFaisantChoix();

        var equipeEsku = new Equipe(joueurEsku1, joueurEsku2, joueurEsku1.nom() + " et " + joueurEsku2.nom());
        var equipeZaku = new Equipe(joueurZaku1, joueurZaku2, joueurZaku1.nom() + " et " + joueurZaku2.nom());
        var opposants = new Opposants(equipeEsku, equipeZaku);
        var score = new Manche.Score(opposants);

        Tour tour = new Tour(evenementsDeJeu, paquetAvec(Carte.AS_BATON, Carte.DEUX_BATON, Carte.TROIS_BATON, Carte.QUATRE_BATON, Carte.AS_COUPE, Carte.DEUX_COUPE, Carte.TROIS_COUPE, Carte.QUATRE_COUPE, Carte.AS_PIECE, Carte.DEUX_PIECE, Carte.TROIS_PIECE, Carte.QUATRE_PIECE,Carte.QUATRE_BATON, Carte.AS_EPEE, Carte.DEUX_EPEE, Carte.TROIS_EPEE, Carte.QUATRE_EPEE), new Defausse());

        tour.jouer(opposants, score);

        assertThat(score.vainqueur()).isEmpty();
        assertThat(score.scoreParEquipe()).containsEntry(equipeEsku, 7);
        assertThat(score.scoreParEquipe()).containsEntry(equipeZaku, 0);
    }

    @Test
    void devrait_attribuer_les_bonus_au_joueur_ayant_la_meilleure_main_pour_chaque_phase() {
        var joueurEsku1 = unJoueurFaisantChoix(new Mintza(), new Paso(), new Paso());
        var joueurZaku1 = unJoueurFaisantChoix(new Paso(), new Paso());
        var joueurZaku2 = unJoueurFaisantChoix(new Paso(), new Paso());
        var joueurEsku2 = unJoueurFaisantChoix(new Paso(), new Paso());


        var equipeEsku = new Equipe(joueurEsku1, joueurEsku2, joueurEsku1.nom() + " et " + joueurEsku2.nom());
        var equipeZaku = new Equipe(joueurZaku1, joueurZaku2, joueurZaku1.nom() + " et " + joueurZaku2.nom());
        var opposants = new Opposants(equipeEsku, equipeZaku);
        var score = new Manche.Score(opposants);

        Tour tour = new Tour(evenementsDeJeu, paquetAvec(Carte.ROI_BATON, Carte.ROI_COUPE, Carte.VALET_BATON, Carte.AS_EPEE,
                                                        Carte.DEUX_COUPE, Carte.TROIS_COUPE, Carte.QUATRE_COUPE, Carte.CINQ_COUPE,
                                                        Carte.DEUX_EPEE,Carte.TROIS_EPEE,Carte.QUATRE_EPEE,Carte.CINQ_EPEE,
                                                        Carte.DEUX_BATON, Carte.TROIS_BATON, Carte.QUATRE_BATON, Carte.CINQ_BATON
                                                        ), new Defausse());

        tour.jouer(opposants, score);

        assertThat(score.vainqueur()).isEmpty();
        assertThat(score.scoreParEquipe()).containsEntry(equipeEsku, 6);
        assertThat(score.scoreParEquipe()).containsEntry(equipeZaku, 0);
    }

    private Evenements evenementsDeJeu;
    private Tour tour;
}
