package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.carte.Defausse;
import com.montaury.mus.console.AffichageEvenements;
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
    mus = new Mus(paquetEntierCroissant(), defausse, new AffichageEvenements(joueurEsku));
    interfaceJoueurEsku = mock(InterfaceJoueur.class);
    interfaceJoueurZaku = mock(InterfaceJoueur.class);
    joueurEsku = new Joueur("J1", interfaceJoueurEsku);
    joueurZaku = new Joueur("J2", interfaceJoueurZaku);
    opposants = new Opposants(joueurEsku, joueurZaku);
  }

  @Test
  void devrait_distribuer_quatre_cartes_a_chaque_joueur() {
    when(interfaceJoueurEsku.faireChoixParmi(anyList())).thenReturn(new Mintza());

    mus.jouer(opposants);

    assertThat(joueurEsku.main().cartes()).containsExactly(Carte.AS_BATON, Carte.AS_COUPE, Carte.AS_EPEE, Carte.AS_PIECE);
    assertThat(joueurZaku.main().cartes()).containsExactly(Carte.DEUX_BATON, Carte.DEUX_COUPE, Carte.DEUX_EPEE, Carte.DEUX_PIECE);
  }

  @Test
  void devrait_se_terminer_si_le_joueur_esku_veut_sortir() {
    when(interfaceJoueurEsku.faireChoixParmi(anyList())).thenReturn(new Mintza());

    mus.jouer(opposants);

    verify(interfaceJoueurZaku, times(0)).faireChoixParmi(anyList());
  }

  @Test
  void devrait_se_terminer_si_le_joueur_zaku_veut_sortir() {
    when(interfaceJoueurEsku.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
    when(interfaceJoueurZaku.faireChoixParmi(anyList())).thenReturn(new Mintza());

    mus.jouer(opposants);

    verify(interfaceJoueurEsku, times(0)).cartesAJeter();
  }

  @Test
  void devrait_demander_les_cartes_a_jeter_aux_joueurs_s_ils_vont_mus() {
    when(interfaceJoueurEsku.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus(), new Mintza());
    when(interfaceJoueurZaku.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());

    mus.jouer(opposants);

    verify(interfaceJoueurEsku, times(1)).cartesAJeter();
    verify(interfaceJoueurZaku, times(1)).cartesAJeter();
  }

  @Test
  void devrait_defausser_les_cartes_a_jeter_si_les_joueurs_vont_mus() {
    when(interfaceJoueurEsku.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus(), new Mintza());
    when(interfaceJoueurEsku.cartesAJeter()).thenReturn(List.of(Carte.AS_COUPE));
    when(interfaceJoueurZaku.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
    when(interfaceJoueurZaku.cartesAJeter()).thenReturn(List.of(Carte.DEUX_COUPE));

    mus.jouer(opposants);

    assertThat(defausse.reprendreCartes()).containsExactly(Carte.AS_COUPE, Carte.DEUX_COUPE);
  }

  @Test
  void devrait_distribuer_des_cartes_pour_remplacer_les_cartes_jetees_si_les_joueurs_vont_mus() {
    when(interfaceJoueurEsku.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus(), new Mintza());
    when(interfaceJoueurEsku.cartesAJeter()).thenReturn(List.of(Carte.AS_COUPE));
    when(interfaceJoueurZaku.faireChoixParmi(anyList())).thenReturn(new com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus());
    when(interfaceJoueurZaku.cartesAJeter()).thenReturn(List.of(Carte.DEUX_COUPE));

    mus.jouer(opposants);

    assertThat(joueurEsku.main().cartes()).containsExactly(Carte.AS_BATON, Carte.AS_EPEE, Carte.AS_PIECE, Carte.TROIS_BATON);
    assertThat(joueurZaku.main().cartes()).containsExactly(Carte.DEUX_BATON, Carte.DEUX_EPEE, Carte.DEUX_PIECE, Carte.TROIS_COUPE);
  }

  private Mus mus;
  private InterfaceJoueur interfaceJoueurEsku;
  private InterfaceJoueur interfaceJoueurZaku;
  private Joueur joueurEsku;
  private Joueur joueurZaku;
  private Opposants opposants;
  private Defausse defausse;
}
