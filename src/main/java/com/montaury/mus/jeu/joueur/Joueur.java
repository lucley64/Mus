package com.montaury.mus.jeu.joueur;

import com.montaury.mus.console.InterfaceJoueurHumain;
import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.equipe.Equipe;

import java.util.List;

public class Joueur {
    public static Joueur humain(String nom) {
        return new Joueur(nom, new InterfaceJoueurHumain());
    }

    public static Joueur ordinateurAllie() {
        return new Joueur("Ordinateur Allie", new InterfaceJoueurOrdinateur());
    }
    public static Joueur ordinateur() {
        return new Joueur("Ordinateur Adversaire", new InterfaceJoueurOrdinateur());
    }

    private final String nom;
    public final InterfaceJoueur interfaceJoueur;
    private final Main main = Main.vide();
    private Equipe equipe;

    public Joueur(String nom, InterfaceJoueur interfaceJoueur) {
        this.nom = nom;
        this.interfaceJoueur = interfaceJoueur;
    }

    public String nom() {
        return nom;
    }

    public Main main() {
        return main;
    }

    public void donnerCartes(List<Carte> cartes) {
        main.ajouter(cartes);
        interfaceJoueur.nouvelleMain(main);
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Equipe getEquipe() {
        return equipe;
    }
}
