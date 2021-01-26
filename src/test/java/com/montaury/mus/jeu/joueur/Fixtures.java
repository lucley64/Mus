package com.montaury.mus.jeu.joueur;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Choix;
import java.util.Arrays;

public class Fixtures {
  public static Joueur unJoueur() {
    return new Joueur("Ordinateur", new FausseInterfaceJoueur());
  }

  public static Joueur unJoueurAvec(Main main) {
    Joueur joueur = new Joueur("Ordinateur", new FausseInterfaceJoueur());
    joueur.donnerCartes(main.cartes());
    return joueur;
  }

  public static Joueur unJoueurFaisantChoix(Choix... choix) {
    return new Joueur("Ordinateur", new FausseInterfaceJoueur(choix));
  }

  public static Main main(Carte... cartes) {
    return new Main(Arrays.asList(cartes));
  }
}
