package com.montaury.mus.jeu.joueur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Equipe{
    private final Set<Joueur> membres;
    private final String nom;
    private List<Joueur> dansLOrdre = new ArrayList<>();

    public Equipe(Set<Joueur> membres, String nom) {
        this.membres = membres;
        dansLOrdre.addAll(this.membres);
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

    public Set<Main> main(){
        Set<Main> mainsDesJoueurs = new HashSet<>();
        for(Joueur membre : membres){
            mainsDesJoueurs.add(membre.main());
        }
        return mainsDesJoueurs;
    }

    public Joueur premier(){
        return dansLOrdre.get(0);
    }

}
