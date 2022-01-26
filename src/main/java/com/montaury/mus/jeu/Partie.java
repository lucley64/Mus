package com.montaury.mus.jeu;

import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Partie {
    private final Evenements affichage;

    public Partie(Evenements affichage) {
        this.affichage = affichage;
    }

    public Resultat jouer(Opposants opposants) {
        affichage.nouvellePartie();
        var score = new Partie.Score(opposants);
        Optional<Joueur> vainqueur;
        do {
            var resultat = new Manche(affichage).jouer(opposants);
            vainqueur = score.enregistrer(resultat);
            affichage.mancheTerminee(score);
        } while (vainqueur.isEmpty());
        return new Resultat(vainqueur.get(), score);
    }

    public static class Score {
        private static final int NB_MANCHES_A_GAGNER = 3;

        private final List<Manche.Resultat> resultatManches = new ArrayList<>();
        private final Map<Joueur, Integer> manchesGagneesParJoueur = new HashMap<>();

        public Score(Opposants opposants) {
            this.manchesGagneesParJoueur.put(opposants.joueurEsku(), 0);
            this.manchesGagneesParJoueur.put(opposants.joueurZaku(), 0);
        }

        public Optional<Joueur> enregistrer(Manche.Resultat score) {
            resultatManches.add(score);
            manchesGagneesParJoueur.put(score.vainqueur(), manchesGagneesParJoueur.get(score.vainqueur()) + 1);
            return vainqueur();
        }

        public List<Manche.Resultat> resultatManches() {
            return resultatManches;
        }

        public Optional<Joueur> vainqueur() {
            return manchesGagneesParJoueur.keySet().stream()
                    .filter(joueur -> manchesGagneesParJoueur.get(joueur) == NB_MANCHES_A_GAGNER).findAny();
        }
    }

    public static class Resultat {
        private final Joueur vainqueur;
        private final Score score;

        private Resultat(Joueur vainqueur, Score score) {
            this.vainqueur = vainqueur;
            this.score = score;
        }

        public Joueur vainqueur() {
            return vainqueur;
        }

        public Score score() {
            return score;
        }
    }
}
