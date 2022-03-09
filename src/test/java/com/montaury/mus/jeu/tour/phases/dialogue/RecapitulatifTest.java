package com.montaury.mus.jeu.tour.phases.dialogue;

import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Idoki;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;

import java.util.List;

import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.unJoueur;
import static org.assertj.core.api.Assertions.assertThat;

class RecapitulatifTest {

    private final Joueur joueur1 = unJoueur();
    private final Joueur joueur2 = unJoueur();

    @Test
    void devrait_compter_1_point_engage_si_tous_les_joueurs_sont_paso() {
        Dialogue.Recapitulatif dialogue = new Dialogue.Recapitulatif(List.of(
                new Dialogue.ChoixJoueur(new Paso(), joueur1),
                new Dialogue.ChoixJoueur(new Paso(), joueur2))
        );

        assertThat(dialogue.pointsEngages()).isEqualTo(1);
    }

    @Test
    void devrait_compter_1_point_engage_pour_imido_tira() {
        Dialogue.Recapitulatif dialogue = new Dialogue.Recapitulatif(List.of(
                new Dialogue.ChoixJoueur(new Imido(), joueur1),
                new Dialogue.ChoixJoueur(new Tira(), joueur2))
        );

        assertThat(dialogue.pointsEngages()).isEqualTo(1);
    }

    @Test
    void devrait_compter_1_point_engage_pour_paso_imido_tira() {
        Dialogue.Recapitulatif dialogue = new Dialogue.Recapitulatif(List.of(
                new Dialogue.ChoixJoueur(new Paso(), joueur1),
                new Dialogue.ChoixJoueur(new Imido(), joueur2),
                new Dialogue.ChoixJoueur(new Tira(), joueur1))
        );

        assertThat(dialogue.pointsEngages()).isEqualTo(1);
    }

    @Test
    void devrait_compter_1_point_engage_pour_3_imido_tira() {
        Dialogue.Recapitulatif dialogue = new Dialogue.Recapitulatif(List.of(
                new Dialogue.ChoixJoueur(new Imido(3), joueur1),
                new Dialogue.ChoixJoueur(new Tira(), joueur2))
        );

        assertThat(dialogue.pointsEngages()).isEqualTo(1);
    }

    @Test
    void devrait_compter_1_point_engage_pour_hordago_tira() {
        Dialogue.Recapitulatif dialogue = new Dialogue.Recapitulatif(List.of(
                new Dialogue.ChoixJoueur(new Hordago(), joueur1),
                new Dialogue.ChoixJoueur(new Tira(), joueur2))
        );

        assertThat(dialogue.pointsEngages()).isEqualTo(1);
    }

    @Test
    void devrait_compter_2_points_engages_pour_imido_idoki() {
        Dialogue.Recapitulatif dialogue = new Dialogue.Recapitulatif(List.of(
                new Dialogue.ChoixJoueur(new Imido(), joueur1),
                new Dialogue.ChoixJoueur(new Idoki(), joueur2))
        );

        assertThat(dialogue.pointsEngages()).isEqualTo(2);
    }

    @Test
    void devrait_compter_2_points_engages_pour_imido_hordago_tira() {
        Dialogue.Recapitulatif dialogue = new Dialogue.Recapitulatif(List.of(
                new Dialogue.ChoixJoueur(new Imido(), joueur1),
                new Dialogue.ChoixJoueur(new Hordago(), joueur2),
                new Dialogue.ChoixJoueur(new Tira(), joueur1))
        );

        assertThat(dialogue.pointsEngages()).isEqualTo(2);
    }

}
