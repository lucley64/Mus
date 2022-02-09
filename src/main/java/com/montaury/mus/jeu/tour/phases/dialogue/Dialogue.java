package com.montaury.mus.jeu.tour.phases.dialogue;

import com.montaury.mus.jeu.Manche;
import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.Participants;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Choix;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix.IDOKI;
import static com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix.KANTA;
import static com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix.TIRA;

public class Dialogue {
  private final Evenements affichage;

  public Dialogue(Evenements affichage) {
    this.affichage = affichage;
  }

  public final Recapitulatif derouler(Participants participants) {
    var deroulement = new Deroulement(participants);
    var choix = new ArrayList<ChoixJoueur>();
    while (!deroulement.estTermine()) {
      var joueurParlant = deroulement.prochainJoueur();
      var choixJoueur = joueurParlant.interfaceJoueur.faireChoixParmi(deroulement.choixPossibles());
      affichage.choixFait(joueurParlant, choixJoueur);
      choix.add(new ChoixJoueur(choixJoueur, joueurParlant));
      deroulement = choixJoueur.influerSur(deroulement);
    }
    return new Recapitulatif(choix);
  }

  public static class ChoixJoueur {
    public final Choix choix;
    public final Joueur joueur;

    public ChoixJoueur(Choix choix, Joueur joueur) {
      this.choix = choix;
      this.joueur = joueur;
    }

    public int mise() {
      return choix.mise();
    }
  }

  public static class Deroulement {

    public static Deroulement termine() {
      return new Deroulement(new Participants(Collections.emptyList()));
    }

    private final Participants participants;
    private Iterator<Iterator<Joueur>> equipesDevantParler;
    private List<TypeChoix> prochainsChoixPossibles = TypeChoix.INITIAUX;
    private Joueur joueurParlant;

    public Deroulement(Participants participants) {
      this.participants = participants;
      List<Iterator<Joueur>> equipesDevantParlerIterator = new ArrayList<>();
      for(Equipe equipe : participants.dansLOrdre()){
        equipesDevantParlerIterator.add(equipe.joueurDevantParler());
      }
      this.equipesDevantParler = equipesDevantParlerIterator.iterator();
    }

    private Deroulement(Participants participants, Iterator<Iterator<Joueur>> equipesDevantParler, List<TypeChoix> prochainsChoixPossibles) {
      this.participants = participants;
      this.equipesDevantParler = equipesDevantParler;
      this.prochainsChoixPossibles = prochainsChoixPossibles;
    }

    public Joueur prochainJoueur() {
      joueurParlant = equipesDevantParler.next().next();
      return joueurParlant;
    }

    public boolean estTermine() {
      return !equipesDevantParler.hasNext();
    }

    public List<TypeChoix> choixPossibles() {
      return prochainsChoixPossibles;
    }

    public Deroulement basculerSurAdversaire(List<TypeChoix> prochainsChoixPossibles) {
      equipesDevantParler = Collections.singletonList(participants.adversaireDe(joueurParlant)).iterator();
      this.prochainsChoixPossibles = prochainsChoixPossibles;
      return this;
    }

    public Deroulement retirerJoueurParlant() {
      return new Deroulement(participants.retirer(joueurParlant), joueursDevantParler, prochainsChoixPossibles);
    }
  }

  public static class Recapitulatif {
    private final int pointsEngages;
    private final List<ChoixJoueur> mises;
    private final Choix dernierChoix;

    public Recapitulatif(List<ChoixJoueur> choix) {
      mises = choix.stream().filter(choixJoueur -> choixJoueur.choix.type().estUneMise()).collect(Collectors.toList());
      dernierChoix = choix.get(choix.size() - 1).choix;
      pointsEngages = calculerPointsEngages();
    }

    private int calculerPointsEngages() {
      if (dernierChoix.est(TIRA)) {
        return mises.size() == 1 ? 1 : mises.subList(0, mises.size() - 1).stream().mapToInt(ChoixJoueur::mise).sum();
      }
      if (dernierChoix.est(IDOKI)) {
        return mises.stream().mapToInt(ChoixJoueur::mise).sum();
      }
      if (dernierChoix.est(KANTA)) {
        return Manche.Score.POINTS_POUR_TERMINER_MANCHE;
      }
      // paso
      return 1;
    }

    public int pointsEngages() {
      return pointsEngages;
    }

    public Joueur dernierJoueurAyantMise() {
      return mises.get(mises.size() - 1).joueur;
    }

    public boolean terminePar(TypeChoix typeChoix) {
      return dernierChoix.est(typeChoix);
    }
  }
}
