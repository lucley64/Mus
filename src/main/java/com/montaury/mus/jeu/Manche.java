package com.montaury.mus.jeu;

import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.evenements.Evenements;
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

        private final Map<Equipe, Integer> scoreParEquipe = new HashMap<>();

        public Score(Opposants opposants) {
            scoreParEquipe.put(opposants.equipeEsku(), 0);
            scoreParEquipe.put(opposants.equipeZaku(), 0);
        }

        public Map<Equipe, Integer> scoreParEquipe() {
            return scoreParEquipe;
        }

        public void scorer(Equipe equipe, int points) {
            if (vainqueur().isEmpty()) {
                scoreParEquipe.put(equipe, Math.min(scoreParEquipe.get(equipe) + points, POINTS_POUR_TERMINER_MANCHE));
            }
        }

        public Optional<Equipe> vainqueur() {
            return scoreParEquipe.keySet().stream().filter(equipe -> scoreParEquipe.get(equipe) == POINTS_POUR_TERMINER_MANCHE).findAny();
        }

        public Optional<Integer> pointsVaincu() {
            return vainqueur().isEmpty() ?
                    Optional.empty() :
                    scoreParEquipe.values().stream().filter(points -> points < POINTS_POUR_TERMINER_MANCHE).findAny();
        }
    }

    public static class Resultat {
        private final Equipe vainqueur;
        private final int pointsVaincu;

        public Resultat(Equipe joueur, int pointsVaincu) {
            vainqueur = joueur;
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
