package com.montaury.mus.jeu.carte;

import java.util.List;

import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.carte.Fixtures.defausseAvec;
import static org.assertj.core.api.Assertions.assertThat;

class PaquetTest {
    @Test
    void devrait_tirer_la_carte_du_dessus() {
        Paquet paquet = new Paquet(List.of(Carte.AS_COUPE, Carte.DEUX_EPEE), new Defausse());

        List<Carte> cartes = paquet.tirer(1);

        assertThat(cartes).containsOnly(Carte.AS_COUPE);
    }

    @Test
    void devrait_melanger_la_defausse_et_reconstituer_le_paquet_s_il_est_epuise() {
        Paquet paquet = new Paquet(List.of(Carte.QUATRE_EPEE), defausseAvec(List.of(Carte.CINQ_COUPE)));

        List<Carte> cartes = paquet.tirer(2);

        assertThat(cartes).containsExactly(Carte.QUATRE_EPEE, Carte.CINQ_COUPE);
    }
}
