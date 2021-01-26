package com.montaury.mus.jeu.joueur;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Choix;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FausseInterfaceJoueur implements InterfaceJoueur {
  private Iterator<Choix> iterateurChoix;
  private final List<Choix> choix;
  public Main main;

  public FausseInterfaceJoueur(Choix... choix) {
    this.choix = Arrays.asList(choix);
    this.iterateurChoix = Arrays.stream(choix).iterator();
  }

  @Override
  public List<Carte> cartesAJeter() {
    return null;
  }

  @Override
  public Choix faireChoixParmi(List<TypeChoix> choixPossibles) {
    if (!iterateurChoix.hasNext()) {
      iterateurChoix = choix.iterator();
    }
    return iterateurChoix.next();
  }

  @Override
  public void nouvelleMain(Main main) {
    this.main = main;
  }
}
