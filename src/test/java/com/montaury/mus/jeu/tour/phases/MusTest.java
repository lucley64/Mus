package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.carte.Defausse;
import com.montaury.mus.console.AffichageEvenements;
import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.joueur.InterfaceJoueur;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mintza;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.carte.Fixtures.paquetEntierCroissant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MusTest {

    @BeforeEach
    void setUp() {
        defausse = new Defausse();
        mus = new Mus(paquetEntierCroissant(), defausse, new AffichageEvenements(joueurEsku1));
        interfaceJoueurEsku1 = mock(InterfaceJoueur.class);
        interfaceJoueurEsku2 = mock(InterfaceJoueur.class);
        interfaceJoueurZaku1 = mock(InterfaceJoueur.class);
        interfaceJoueurZaku2 = mock(InterfaceJoueur.class);
        joueurEsku1 = new Joueur("J1", interfaceJoueurEsku1);
        joueurEsku2 = new Joueur("J2", interfaceJoueurEsku2);
        joueurZaku1 = new Joueur("J3", interfaceJoueurZaku1);
        joueurZaku2 = new Joueur("J4", interfaceJoueurZaku2);
        equipeEsku = new Equipe(joueurEsku1, joueurEsku2,"Ezku");
        equipeZaku = new Equipe(joueurZaku1, joueurZaku2, "Zaku");
        opposants = new Opposants(equipeEsku, equipeZaku);
    }

    @Test
    void devrait_distribuer_quatre_cartes_a_chaque_joueur() {
        when(interfaceJoueurEsku1.faireChoixParmi(anyList())).thenReturn(new Mintza());

        mus.jouer(opposants);

        assertThat(joueurEsku1.main().cartes()).containsExactly(Carte.AS_BATON, Carte.AS_COUPE, Carte.AS_EPEE, Carte.AS_PIECE);
        assertThat(joueurZaku1.main().cartes()).containsExactly(Carte.DEUX_BATON, Carte.DEUX_COUPE, Carte.DEUX_EPEE, Carte.DEUX_PIECE);
        assertThat(joueurEsku2.main().cartes()).containsExactly(Carte.TROIS_BATON, Carte.TROIS_COUPE, Carte.TROIS_EPEE, Carte.TROIS_PIECE);
        assertThat(joueurZaku2.main().cartes()).containsExactly(Carte.QUATRE_BATON, Carte.QUATRE_COUPE, Carte.QUATRE_EPEE, Carte.QUATRE_PIECE);
    }

    @Test
    void devrait_se_terminer_si_le_joueur_esku_veut_sortir() {
        when(interfaceJoueurEsku1.faireChoixParmi(anyList())).thenReturn(new Mintza());

        mus.jouer(opposants);

        verify(interfaceJoueurZaku1, times(0)).faireChoixParmi(anyList());
    }

    @Test
    void devrait_se_terminer_si_le_joueur_zaku_veut_sortir() {
        when(interfaceJoueurEsku1.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
        when(interfaceJoueurZaku1.faireChoixParmi(anyList())).thenReturn(new Mintza());

        mus.jouer(opposants);

        verify(interfaceJoueurEsku1, times(0)).cartesAJeter();
    }

    @Test
    void devrait_demander_les_cartes_a_jeter_aux_joueurs_s_ils_vont_mus() {
        when(interfaceJoueurEsku1.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus(), new Mintza());
        when(interfaceJoueurZaku1.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
        when(interfaceJoueurEsku2.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
        when(interfaceJoueurZaku2.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());



        mus.jouer(opposants);

        verify(interfaceJoueurEsku1, times(1)).cartesAJeter();
        verify(interfaceJoueurZaku1, times(1)).cartesAJeter();
        verify(interfaceJoueurEsku2, times(1)).cartesAJeter();
        verify(interfaceJoueurZaku2, times(1)).cartesAJeter();
    }

    @Test
    void devrait_defausser_les_cartes_a_jeter_si_les_joueurs_vont_mus() {
        when(interfaceJoueurEsku1.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus(), new Mintza());
        when(interfaceJoueurEsku1.cartesAJeter()).thenReturn(List.of(Carte.AS_COUPE));
        when(interfaceJoueurZaku1.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
        when(interfaceJoueurZaku1.cartesAJeter()).thenReturn(List.of(Carte.DEUX_COUPE));
        when(interfaceJoueurEsku2.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
        when(interfaceJoueurEsku2.cartesAJeter()).thenReturn(List.of(Carte.TROIS_COUPE));
        when(interfaceJoueurZaku2.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
        when(interfaceJoueurZaku2.cartesAJeter()).thenReturn(List.of(Carte.QUATRE_COUPE));

        mus.jouer(opposants);

        assertThat(defausse.reprendreCartes()).containsExactly(Carte.AS_COUPE, Carte.DEUX_COUPE, Carte.TROIS_COUPE, Carte.QUATRE_COUPE);
    }

    @Test
    void devrait_distribuer_des_cartes_pour_remplacer_les_cartes_jetees_si_les_joueurs_vont_mus() {
        when(interfaceJoueurEsku1.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus(), new Mintza());
        when(interfaceJoueurEsku1.cartesAJeter()).thenReturn(List.of(Carte.AS_COUPE));
        when(interfaceJoueurZaku1.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
        when(interfaceJoueurZaku1.cartesAJeter()).thenReturn(List.of(Carte.DEUX_COUPE));
        when(interfaceJoueurEsku2.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
        when(interfaceJoueurEsku2.cartesAJeter()).thenReturn(List.of(Carte.TROIS_COUPE));
        when(interfaceJoueurZaku2.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
        when(interfaceJoueurZaku2.cartesAJeter()).thenReturn(List.of(Carte.QUATRE_COUPE));

        mus.jouer(opposants);

        assertThat(joueurEsku1.main().cartes()).containsExactly(Carte.AS_BATON, Carte.AS_EPEE, Carte.AS_PIECE, Carte.CINQ_BATON);
        assertThat(joueurZaku1.main().cartes()).containsExactly(Carte.DEUX_BATON, Carte.DEUX_EPEE, Carte.DEUX_PIECE, Carte.CINQ_COUPE);
        assertThat(joueurEsku2.main().cartes()).containsExactly(Carte.TROIS_BATON, Carte.TROIS_EPEE, Carte.TROIS_PIECE, Carte.CINQ_EPEE);
        assertThat(joueurZaku2.main().cartes()).containsExactly(Carte.QUATRE_BATON, Carte.QUATRE_EPEE, Carte.QUATRE_PIECE, Carte.CINQ_PIECE);
    }

    private Mus mus;
    private InterfaceJoueur interfaceJoueurEsku1;
    private InterfaceJoueur interfaceJoueurEsku2;
    private InterfaceJoueur interfaceJoueurZaku1;
    private InterfaceJoueur interfaceJoueurZaku2;
    private Equipe equipeEsku;
    private Joueur joueurEsku1;
    private Joueur joueurEsku2;
    private Equipe equipeZaku;
    private Joueur joueurZaku1;
    private Joueur joueurZaku2;
    private Opposants opposants;
    private Defausse defausse;
}