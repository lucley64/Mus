package com.montaury.mus;

import com.montaury.mus.jeu.Partie;
import com.montaury.mus.console.AffichageEvenements;
import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.Opposants;

import java.util.Scanner;

public class JeuDeMus {
    public static void main(String[] args) {
        System.out.print("Entrez votre nom: ");
        var nomJoueur = new Scanner(System.in).next();
        var joueurHumain = Joueur.humain(nomJoueur);

        Equipe equipeHumainOrdinateur = new Equipe(joueurHumain, Joueur.ordinateurAllie(), joueurHumain.nom() + " et " + "Ordinateur Allié");
        Equipe equipeOrdinateurOrdinateur = new Equipe(Joueur.ordinateur(), Joueur.ordinateur(), "Ordinateur Opposant 1" + " et " + "Ordinateur Opposant 2");

        var partie = new Partie(new AffichageEvenements(joueurHumain));
        var resultat = partie.jouer(new Opposants(equipeHumainOrdinateur, equipeOrdinateurOrdinateur));

        System.out.println("Le vainqueur de la partie est " + resultat.vainqueur().nom());
    }
}
