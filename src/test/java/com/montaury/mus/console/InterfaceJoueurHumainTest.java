package com.montaury.mus.console;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Idoki;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.main;
import static org.assertj.core.api.Assertions.assertThat;

class InterfaceJoueurHumainTest {

    private StringReadable entreeUtilisateur;
    private InterfaceJoueurHumain interfaceJoueurHumain;

    @BeforeEach
    void setUp() {
        entreeUtilisateur = new StringReadable();
        interfaceJoueurHumain = new InterfaceJoueurHumain(new Scanner(entreeUtilisateur));
    }

    @Test
    void devrait_identifier_le_choix_paso_saisi() {
        entreeUtilisateur.simulerSaisieLigne("Paso");

        var choix = interfaceJoueurHumain.faireChoixParmi(List.of(TypeChoix.PASO));

        assertThat(choix).isInstanceOf(Paso.class);
    }

    @Test
    void devrait_identifier_le_choix_paso_saisi_avec_casse_majuscule() {
        entreeUtilisateur.simulerSaisieLigne("PASO");

        var choix = interfaceJoueurHumain.faireChoixParmi(List.of(TypeChoix.PASO));

        assertThat(choix).isInstanceOf(Paso.class);
    }

    @Test
    void devrait_identifier_le_choix_hordago_saisi() {
        entreeUtilisateur.simulerSaisieLigne("hordago");

        var choix = interfaceJoueurHumain.faireChoixParmi(List.of(TypeChoix.HORDAGO));

        assertThat(choix).isInstanceOf(Hordago.class);
    }

    @Test
    void devrait_identifier_le_choix_idoki_saisi() {
        entreeUtilisateur.simulerSaisieLigne("Idoki");

        var choix = interfaceJoueurHumain.faireChoixParmi(List.of(TypeChoix.IDOKI));

        assertThat(choix).isInstanceOf(Idoki.class);
    }

    @Test
    void devrait_identifier_le_choix_tira_saisi() {
        entreeUtilisateur.simulerSaisieLigne("Tira");

        var choix = interfaceJoueurHumain.faireChoixParmi(List.of(TypeChoix.TIRA));

        assertThat(choix).isInstanceOf(Tira.class);
    }

    @Test
    void devrait_identifier_le_choix_kanta_saisi() {
        entreeUtilisateur.simulerSaisieLigne("Kanta");

        var choix = interfaceJoueurHumain.faireChoixParmi(List.of(TypeChoix.KANTA));

        assertThat(choix).isInstanceOf(Kanta.class);
    }

    @Test
    void devrait_identifier_le_choix_imido_saisi_sans_mise() {
        entreeUtilisateur.simulerSaisieLigne("Imido");

        var choix = interfaceJoueurHumain.faireChoixParmi(List.of(TypeChoix.IMIDO));

        assertThat(choix).isInstanceOf(Imido.class);
    }

    @Test
    void devrait_identifier_le_choix_imido_saisi_avec_mise() {
        entreeUtilisateur.simulerSaisieLigne("Imido 5");

        var choix = interfaceJoueurHumain.faireChoixParmi(List.of(TypeChoix.IMIDO));

        assertThat(choix)
                .isInstanceOf(Imido.class)
                .extracting("mise")
                .isEqualTo(5);
    }

    @Test
    void devrait_identifier_le_choix_gehiago_saisi_sans_mise() {
        entreeUtilisateur.simulerSaisieLigne("Gehiago");

        var choix = interfaceJoueurHumain.faireChoixParmi(List.of(TypeChoix.GEHIAGO));

        assertThat(choix).isInstanceOf(Gehiago.class);
    }

    @Test
    void devrait_identifier_le_choix_gehiago_saisi_avec_mise() {
        entreeUtilisateur.simulerSaisieLigne("Gehiago 12");

        var choix = interfaceJoueurHumain.faireChoixParmi(List.of(TypeChoix.GEHIAGO));

        assertThat(choix)
                .isInstanceOf(Gehiago.class)
                .extracting("mise")
                .isEqualTo(12);
    }

    @Test
    void devrait_redemander_la_saisie_tant_que_le_choix_est_incorrect() {
        entreeUtilisateur.simulerSaisieLigne("blah");
        entreeUtilisateur.simulerSaisieLigne("Gehiago");
        entreeUtilisateur.simulerSaisieLigne("");
        entreeUtilisateur.simulerSaisieLigne("Imido x");
        entreeUtilisateur.simulerSaisieLigne("Imido 1 2");
        entreeUtilisateur.simulerSaisieLigne("Imido");

        var choix = interfaceJoueurHumain.faireChoixParmi(List.of(TypeChoix.IMIDO));

        assertThat(choix).isInstanceOf(Imido.class);
    }

    @Test
    void devrait_identifier_les_cartes_a_jeter() {
        entreeUtilisateur.simulerSaisieLigne("1,2,3,4");
        interfaceJoueurHumain.nouvelleMain(main(Carte.CAVALIER_EPEE, Carte.CINQ_EPEE, Carte.AS_PIECE, Carte.TROIS_BATON));

        var cartesAJeter = interfaceJoueurHumain.cartesAJeter();

        assertThat(cartesAJeter).containsOnly(Carte.CAVALIER_EPEE, Carte.CINQ_EPEE, Carte.AS_PIECE, Carte.TROIS_BATON);
    }

    @Test
    void devrait_redemander_la_saisie_tant_que_les_cartes_a_jeter_sont_incorrectes() {
        entreeUtilisateur.simulerSaisieLigne("blah");
        entreeUtilisateur.simulerSaisieLigne("");
        entreeUtilisateur.simulerSaisieLigne("1,x");
        entreeUtilisateur.simulerSaisieLigne("1,2,3,4,5");
        entreeUtilisateur.simulerSaisieLigne("12");
        entreeUtilisateur.simulerSaisieLigne("-8");
        entreeUtilisateur.simulerSaisieLigne("1");
        interfaceJoueurHumain.nouvelleMain(main(Carte.CAVALIER_EPEE, Carte.CINQ_EPEE, Carte.AS_PIECE, Carte.TROIS_BATON));

        var cartesAJeter = interfaceJoueurHumain.cartesAJeter();

        assertThat(cartesAJeter).containsOnly(Carte.CAVALIER_EPEE);
    }

    private static class StringReadable implements Readable {
        private final StringBuilder saisie = new StringBuilder();

        public void simulerSaisieLigne(String ligne) {
            this.saisie.append(ligne).append('\n');
        }

        @Override
        public int read(CharBuffer cb) {
            cb.append(saisie);
            return saisie.length();
        }
    }
}
