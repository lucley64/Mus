package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.Manche;
import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Main;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.tour.phases.dialogue.Dialogue;

import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

import static com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix.KANTA;
import static com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix.PASO;
import static com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix.TIRA;

public abstract class Phase {
    private final String nom;

    protected Phase(String nom) {
        this.nom = nom;
    }

    public String nom() {
        return nom;
    }

    public final Resultat jouer(Evenements affichage, Opposants opposants) {
        affichage.nouvellePhase(this);
        var participants = participantsParmi(opposants);
        if (participants.aucun()) {
            return Phase.Resultat.nonJouable();
        }
        if (participants.estUnique()) {
            Joueur premier = participants.premier();
            return Phase.Resultat.termine(premier.equipe, 0, pointsBonus(premier));
        }
        var resultat = new Dialogue(affichage).derouler(participants);
        return conclure(resultat, participants);
    }

    private Resultat conclure(Dialogue.Recapitulatif dialogue, Participants participants) {
        if (dialogue.terminePar(TIRA)) {
            var joueurEmportantLaMise = dialogue.dernierJoueurAyantMise();
            var equipeEmportantLaMise = joueurEmportantLaMise.equipe;
            return Phase.Resultat.termine(equipeEmportantLaMise, dialogue.pointsEngages(), pointsBonus(joueurEmportantLaMise));
        }
        var vainqueur = meilleurParmi(participants);
        if (dialogue.terminePar(KANTA)) {
            var equipeVaiqueur = vainqueur.equipe;
            return Phase.Resultat.termine(equipeVaiqueur, Manche.Score.POINTS_POUR_TERMINER_MANCHE, 0);
        }
        var bonus = pointsBonus(vainqueur);
        var equipeVaiqueur = vainqueur.equipe;
        return Phase.Resultat.termine(equipeVaiqueur, 0, bonus + (dialogue.terminePar(PASO) && bonus != 0 ? 0 : dialogue.pointsEngages()));
    }

    public Participants participantsParmi(Opposants opposants) {
        return new Participants(opposants.dansLOrdre().stream()
                .filter(joueur -> peutParticiper(joueur.main()))
                .collect(Collectors.toList()));
    }

    protected boolean peutParticiper(Main main) {
        return true;
    }

    private Joueur meilleurParmi(Participants participants) {
        Joueur meilleur = null;
        for (Joueur joueur : participants.dansLOrdre()) {
            meilleur = meilleur == null ? joueur : meilleurEntre(meilleur, joueur);
        }
        return meilleur;
    }

    private Joueur meilleurEntre(Joueur joueurEsku, Joueur joueurZaku) {
        return mainEskuEstMeilleure(joueurEsku.main(), joueurZaku.main()) ? joueurEsku : joueurZaku;
    }

    protected abstract boolean mainEskuEstMeilleure(Main mainJoueurEsku, Main mainJoueurZaku);

    protected int pointsBonus(Joueur vainqueur) {
        return 0;
    }

    public static class Resultat {
        public static Resultat nonJouable() {
            return new Resultat(null, 0, 0);
        }

        public static Resultat termine(Equipe vainqueur, int pointsImmediats, int pointsFinDuTour) {
            return new Resultat(vainqueur, pointsImmediats, pointsFinDuTour);
        }

        private final Equipe vainqueur;
        public final int pointsImmediats;
        public final int pointsFinDuTour;

        private Resultat(Equipe vainqueur, int pointsImmediats, int pointsFinDuTour) {
            this.pointsImmediats = pointsImmediats;
            this.vainqueur = vainqueur;
            this.pointsFinDuTour = pointsFinDuTour;
        }

        public Optional<Equipe> vainqueur() {
            return Optional.ofNullable(vainqueur);
        }
    }
}
