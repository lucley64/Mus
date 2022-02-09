package com.montaury.mus.jeu.joueur;

import java.util.HashSet;
import java.util.Set;

public class Equipe{
    private Set<Joueur> membres;
    private String nom;

    public Equipe(Set<Joueur> membres, String nom) {
        this.membres = membres;
        this.nom = nom;
    }

    public Equipe(String nom) {
        this.nom = nom;
        this.membres = new HashSet<>();
    }

    public Set<Joueur> getMembres() {
        return membres;
    }

    public String nom(){
        return nom;
    }

    public Set<Main>

}
