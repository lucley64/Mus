package com.montaury.mus.jeu;

import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.Tour;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Manche {
  private final Evenements affichage;

  public Manche(Evenements affichage) {
    this.affichage = affichage;
  }

  public Resultat jouer(Opposants opposants) {
    affichage.nouvelleManche();
    var score = new Score(opposants);
    do {
      new Tour(affichage).jouer(opposants, score);
      affichage.tourTermine(opposants, score);
      opposants.tourner();
    } while (score.vainqueur().isEmpty());
    return new Resultat(score.vainqueur().get(), score.pointsVaincu().get());
  }

  public static class Score {
    public static final int POINTS_POUR_TERMINER_MANCHE = 40;

    private final Map<Equipe, Integer> scoreParJoueur = new HashMap<>();

    public Score(Opposants opposants) {
      scoreParJoueur.put(opposants.equipeEsku(), 0);
      scoreParJoueur.put(opposants.equipeZaku(), 0);
    }

    public Map<Equipe, Integer> scoreParJoueur() {
      return scoreParJoueur;
    }

    public void scorer(Equipe equipe, int points) {
      if (vainqueur().isEmpty()) {
        scoreParJoueur.put(equipe, Math.min(scoreParJoueur.get(equipe) + points, POINTS_POUR_TERMINER_MANCHE));
      }
    }

    public Optional<Equipe> vainqueur() {
      return scoreParJoueur.keySet().stream().filter(equipe -> scoreParJoueur.get(equipe) == POINTS_POUR_TERMINER_MANCHE).findAny();
    }

    public Optional<Integer> pointsVaincu() {
      return vainqueur().isEmpty() ?
        Optional.empty() :
        scoreParJoueur.values().stream().filter(points -> points < POINTS_POUR_TERMINER_MANCHE).findAny();
    }
  }

  public static class Resultat {
    private final Equipe vainqueur;
    private final int pointsVaincu;

    public Resultat(Equipe equipe, int pointsVaincu) {
      vainqueur = equipe;
      this.pointsVaincu = pointsVaincu;
    }

    public Equipe vainqueur() {
      return vainqueur;
    }

    public int pointsVaincu() {
      return pointsVaincu;
    }
  }
}
