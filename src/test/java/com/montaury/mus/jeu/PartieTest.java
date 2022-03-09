package com.montaury.mus.jeu;

import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mintza;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Paso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurFaisantChoix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class PartieTest {

    @BeforeEach
    void setUp() {
        partie = new Partie(mock(Evenements.class));
    }

    @Test
    void devrait_faire_gagner_la_premiere_equipe_a_3_manches() {
        var joueurEsku1 = unJoueurFaisantChoix(new Mintza(), new Hordago(), new Kanta());
        var joueurZaku1 = unJoueurFaisantChoix(new Kanta(), new Mintza(), new Hordago());
        var joueurEsku2 =  unJoueurFaisantChoix(new Kanta(), new Mintza(), new Hordago());
        var joueurZaku2 = unJoueurFaisantChoix(new Kanta(), new Mintza(), new Hordago());

        var equipeEsku = new Equipe(joueurEsku1, joueurEsku2, joueurEsku1.nom() + " et " + joueurEsku2.nom());
        var equipeZaku = new Equipe(joueurZaku1, joueurZaku2, joueurZaku1.nom() + " et " + joueurZaku2.nom());
        var opposants = new Opposants(equipeEsku,equipeZaku);


        Partie.Resultat resultat = partie.jouer(opposants);

        assertThat(resultat.vainqueur()).isNotNull();
        assertThat(resultat.score().resultatManches()).hasSizeGreaterThanOrEqualTo(3);
    }

    private Partie partie;
}