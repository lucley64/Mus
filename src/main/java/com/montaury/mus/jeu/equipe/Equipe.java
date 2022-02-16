package com.montaury.mus.jeu.equipe;

import com.montaury.mus.jeu.joueur.Joueur;

public class Equipe {
    Joueur joueur1;
    Joueur joueur2;
    String nom;

    public Equipe(Joueur joueur1, Joueur joueur2, String nom) {
        this.joueur1 = joueur1;
        joueur1.equipe = this;
        this.joueur2 = joueur2;
        joueur2.equipe = this;
        this.nom = nom;
    }

    public Joueur joueur1(){
        return joueur1;
    }

    public Joueur joueur2(){
        return joueur2;
    }

    public String nom() {
        return nom;
    }
}
