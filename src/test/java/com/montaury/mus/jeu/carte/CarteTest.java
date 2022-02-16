package com.montaury.mus.jeu.carte;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarteTest {
    @Test
    void devrait_considerer_l_as_plus_petit_que_le_deux() {
        assertThat(Carte.AS_BATON.comparerAvec(Carte.DEUX_COUPE)).isEqualTo(ValeurCarte.Comparaison.PLUS_PETITE);
    }

    @Test
    void devrait_considerer_le_deux_plus_grand_que_l_as() {
        assertThat(Carte.DEUX_COUPE.comparerAvec(Carte.AS_PIECE)).isEqualTo(ValeurCarte.Comparaison.PLUS_GRANDE);
    }

    @Test
    void devrait_considerer_deux_cartes_de_meme_valeur_du_meme_rang() {
        assertThat(Carte.DEUX_COUPE.comparerAvec(Carte.DEUX_EPEE)).isEqualTo(ValeurCarte.Comparaison.MEME_RANG);
    }
}
