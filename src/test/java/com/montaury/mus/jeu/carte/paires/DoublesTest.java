package com.montaury.mus.jeu.carte.paires;

import com.montaury.mus.jeu.carte.ValeurCarte;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DoublesTest {
    @Test
    void doivent_fournir_un_bonus_de_3() {
        Doubles doubles = new Doubles(new PaireSimple(ValeurCarte.CINQ), new PaireSimple(ValeurCarte.SIX));

        int pointsBonus = doubles.pointsBonus();

        assertThat(pointsBonus).isEqualTo(3);
    }

    @Test
    void sont_meilleurs_qu_une_paire_simple() {
        Doubles doubles = new Doubles(new PaireSimple(ValeurCarte.CINQ), new PaireSimple(ValeurCarte.SIX));

        boolean meilleureOuEgale = doubles.estMeilleureOuEgaleA(new PaireSimple(ValeurCarte.ROI));

        assertThat(meilleureOuEgale).isTrue();
    }

    @Test
    void sont_meilleurs_que_des_meds() {
        Doubles doubles = new Doubles(new PaireSimple(ValeurCarte.CINQ), new PaireSimple(ValeurCarte.SIX));

        boolean meilleureOuEgale = doubles.estMeilleureOuEgaleA(new Meds(ValeurCarte.ROI));

        assertThat(meilleureOuEgale).isTrue();
    }

    @Test
    void sont_meilleures_que_des_doubles_dont_la_meilleure_paire_est_plus_petite() {
        Doubles doubles = new Doubles(new PaireSimple(ValeurCarte.CINQ), new PaireSimple(ValeurCarte.SIX));

        boolean meilleureOuEgale = doubles.estMeilleureOuEgaleA(new Doubles(new PaireSimple(ValeurCarte.DEUX), new PaireSimple(ValeurCarte.CINQ)));

        assertThat(meilleureOuEgale).isTrue();
    }

    @Test
    void sont_meilleures_que_des_doubles_dont_la_meilleure_paire_est_egale_mais_la_seconde_paire_est_plus_petite() {
        Doubles doubles = new Doubles(new PaireSimple(ValeurCarte.CINQ), new PaireSimple(ValeurCarte.SIX));

        boolean meilleureOuEgale = doubles.estMeilleureOuEgaleA(new Doubles(new PaireSimple(ValeurCarte.SIX), new PaireSimple(ValeurCarte.QUATRE)));

        assertThat(meilleureOuEgale).isTrue();
    }

    @Test
    void sont_egale_si_les_deux_paires_des_doubles_sont_egales() {
        Doubles doubles = new Doubles(new PaireSimple(ValeurCarte.CINQ), new PaireSimple(ValeurCarte.SIX));

        boolean meilleureOuEgale = doubles.estMeilleureOuEgaleA(new Doubles(new PaireSimple(ValeurCarte.SIX), new PaireSimple(ValeurCarte.CINQ)));

        assertThat(meilleureOuEgale).isTrue();
    }

    @Test
    void sont_moins_bonnes_si_la_plus_grande_paire_est_plus_petite() {
        Doubles doubles = new Doubles(new PaireSimple(ValeurCarte.CINQ), new PaireSimple(ValeurCarte.SIX));

        boolean meilleureOuEgale = doubles.estMeilleureOuEgaleA(new Doubles(new PaireSimple(ValeurCarte.SEPT), new PaireSimple(ValeurCarte.QUATRE)));

        assertThat(meilleureOuEgale).isFalse();
    }

    @Test
    void sont_moins_bonnes_si_la_plus_grande_paire_est_egale_mais_la_seconde_est_plus_petite() {
        Doubles doubles = new Doubles(new PaireSimple(ValeurCarte.TROIS), new PaireSimple(ValeurCarte.SIX));

        boolean meilleureOuEgale = doubles.estMeilleureOuEgaleA(new Doubles(new PaireSimple(ValeurCarte.SIX), new PaireSimple(ValeurCarte.QUATRE)));

        assertThat(meilleureOuEgale).isFalse();
    }
}
