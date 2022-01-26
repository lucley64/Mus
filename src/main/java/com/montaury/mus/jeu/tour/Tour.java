package com.montaury.mus.jeu.tour;

import com.montaury.mus.jeu.Manche;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.carte.Defausse;
import com.montaury.mus.jeu.carte.Paquet;
import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.tour.phases.FauxJeu;
import com.montaury.mus.jeu.tour.phases.Grand;
import com.montaury.mus.jeu.tour.phases.Jeu;
import com.montaury.mus.jeu.tour.phases.Mus;
import com.montaury.mus.jeu.tour.phases.Paires;
import com.montaury.mus.jeu.tour.phases.Petit;
import com.montaury.mus.jeu.tour.phases.Phase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tour {

    private final Evenements evenements;
    private final Paquet paquet;
    private final Defausse defausse;

    public Tour(Evenements evenements) {
        this.evenements = evenements;
        this.defausse = new Defausse();
        this.paquet = Paquet.nouveauMelange(defausse);
    }

    Tour(Evenements evenements, Paquet paquet, Defausse defausse) {
        this.evenements = evenements;
        this.paquet = paquet;
        this.defausse = defausse;
    }

    public void jouer(Opposants opposants, Manche.Score score) {
        evenements.nouveauTour(opposants);
        new Mus(paquet, defausse, evenements).jouer(opposants);

        var resultats = new ResultatsPhases();
        Iterator<Phase> phases = phasesJouablesPar(opposants).iterator();
        do {
            var resultatPhase = phases.next().jouer(evenements, opposants);
            resultatPhase.vainqueur().ifPresent(joueur -> score.scorer(joueur, resultatPhase.pointsImmediats));
            resultats.ajouter(resultatPhase);
        } while (phases.hasNext() && score.vainqueur().isEmpty());
        resultats.attribuerPointsRestants(score);
    }

    private static Iterable<Phase> phasesJouablesPar(Opposants opposants) {
        Phase phaseDuJeu = new Jeu();
        if (phaseDuJeu.participantsParmi(opposants).aucun()) {
            phaseDuJeu = new FauxJeu();
        }
        return List.of(new Grand(), new Petit(), new Paires(), phaseDuJeu);
    }

    static class ResultatsPhases {
        private final List<Phase.Resultat> resultats = new ArrayList<>();

        public void ajouter(Phase.Resultat resultat) {
            this.resultats.add(resultat);
        }

        public void attribuerPointsRestants(Manche.Score score) {
            var resultatPhase = resultats.iterator();
            while (resultatPhase.hasNext() && score.vainqueur().isEmpty()) {
                var resultat = resultatPhase.next();
                resultat.vainqueur().ifPresent(vainqueur ->
                        score.scorer(vainqueur, resultat.pointsFinDuTour));
            }
        }
    }
}
