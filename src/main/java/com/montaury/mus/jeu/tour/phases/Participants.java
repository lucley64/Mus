package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private final List<Joueur> dansLOrdre;

    public Participants(List<Joueur> dansLOrdre) {
        this.dansLOrdre = dansLOrdre;
    }

    public boolean aucun() {
        return dansLOrdre.isEmpty();
    }

    public boolean estUnique() {
        return dansLOrdre.size() == 1;
    }

    public Joueur premier() {
        return dansLOrdre.get(0);
    }

    public Joueur adversaireDe(Joueur joueurParlant) {
        if (joueurParlant == premier()){
            try{
                return dansLOrdre.get(1);
            }
            catch (Exception e){
                e.printStackTrace();
                return dansLOrdre.get(0);
            }
        }else {
            return premier();
        }
    }

    public Iterable<Joueur> dansLOrdre() {
        return dansLOrdre;
    }

    public Participants retirer(Joueur joueur) {
        var joueurs = new ArrayList<>(dansLOrdre);
        joueurs.remove(joueur);
        return new Participants(joueurs);
    }
}
