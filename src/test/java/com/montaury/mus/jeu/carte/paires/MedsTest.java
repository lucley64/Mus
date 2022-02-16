package com.montaury.mus.jeu.carte.paires;

import com.montaury.mus.jeu.carte.ValeurCarte;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MedsTest {
    @Test
    void doivent_fournir_un_bonus_de_2() {
        Meds meds = new Meds(ValeurCarte.CINQ);

        int pointsBonus = meds.pointsBonus();

        assertThat(pointsBonus).isEqualTo(2);
    }

    @Test
    void sont_meilleurs_qu_une_paire_simple() {
        Meds meds = new Meds(ValeurCarte.CINQ);

        boolean meilleureOuEgale = meds.estMeilleureOuEgaleA(new PaireSimple(ValeurCarte.ROI));

        assertThat(meilleureOuEgale).isTrue();
    }

    @Test
    void sont_moins_bons_que_des_doubles() {
        Meds meds = new Meds(ValeurCarte.ROI);

        boolean meilleureOuEgale = meds.estMeilleureOuEgaleA(new Doubles(new PaireSimple(ValeurCarte.AS), new PaireSimple(ValeurCarte.AS)));

        assertThat(meilleureOuEgale).isFalse();
    }

    @Test
    void sont_meilleurs_que_des_meds_de_rang_inferieur() {
        Meds meds = new Meds(ValeurCarte.SEPT);

        boolean meilleureOuEgale = meds.estMeilleureOuEgaleA(new Meds(ValeurCarte.SIX));

        assertThat(meilleureOuEgale).isTrue();
    }

    @Test
    void sont_moins_bonnes_que_des_meds_de_rang_superieur() {
        Meds meds = new Meds(ValeurCarte.SEPT);

        boolean meilleureOuEgale = meds.estMeilleureOuEgaleA(new Meds(ValeurCarte.VALET));

        assertThat(meilleureOuEgale).isFalse();
    }
}
