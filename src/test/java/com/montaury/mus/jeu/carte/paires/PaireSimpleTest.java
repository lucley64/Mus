package com.montaury.mus.jeu.carte.paires;

import com.montaury.mus.jeu.carte.ValeurCarte;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaireSimpleTest {
    @Test
    void doivent_fournir_un_bonus_de_1() {
        PaireSimple paireSimple = new PaireSimple(ValeurCarte.CINQ);

        int pointsBonus = paireSimple.pointsBonus();

        assertThat(pointsBonus).isEqualTo(1);
    }

    @Test
    void sont_meilleurs_qu_une_paire_simple_de_rang_inferieur() {
        PaireSimple paireSimple = new PaireSimple(ValeurCarte.CINQ);

        boolean meilleureOuEgale = paireSimple.estMeilleureOuEgaleA(new PaireSimple(ValeurCarte.QUATRE));

        assertThat(meilleureOuEgale).isTrue();
    }

    @Test
    void sont_egales_a_une_paire_simple_de_meme_rang() {
        PaireSimple paireSimple = new PaireSimple(ValeurCarte.CINQ);

        boolean meilleureOuEgale = paireSimple.estMeilleureOuEgaleA(new PaireSimple(ValeurCarte.CINQ));

        assertThat(meilleureOuEgale).isTrue();
    }

    @Test
    void sont_moins_bonnes_que_des_meds() {
        PaireSimple paireSimple = new PaireSimple(ValeurCarte.ROI);

        boolean meilleureOuEgale = paireSimple.estMeilleureOuEgaleA(new Meds(ValeurCarte.AS));

        assertThat(meilleureOuEgale).isFalse();
    }

    @Test
    void sont_moins_bonnes_que_des_doubles() {
        PaireSimple paireSimple = new PaireSimple(ValeurCarte.ROI);

        boolean meilleureOuEgale = paireSimple.estMeilleureOuEgaleA(new Doubles(new PaireSimple(ValeurCarte.AS), new PaireSimple(ValeurCarte.AS)));

        assertThat(meilleureOuEgale).isFalse();
    }
}
