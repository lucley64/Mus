package com.montaury.mus.jeu.joueur;

import java.util.*;

public class Equipe{
    private final Set<Joueur> membres;
    private final String nom;
    private List<Joueur> dansLOrdre = new ArrayList<>();
    private Iterator<Joueur> joueurDevantParler;

    public Equipe(Set<Joueur> membres, String nom) {
        this.membres = membres;
        dansLOrdre.addAll(this.membres);
        joueurDevantParler = dansLOrdre.iterator();
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

    public Iterable<Joueur> dansLOrdre(){
        return dansLOrdre;
    }

    public Iterator<Joueur> joueurDevantParler(){
        return joueurDevantParler;
    }
}
