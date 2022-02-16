package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.ValeurCarte;
import com.montaury.mus.jeu.joueur.Main;

import static com.montaury.mus.jeu.carte.ValeurCarte.Comparaison.PLUS_GRANDE;
import static com.montaury.mus.jeu.carte.ValeurCarte.Comparaison.PLUS_PETITE;

public class Grand extends Phase {
    public Grand() {
        super("Grand");
    }

    @Override
    protected boolean mainEskuEstMeilleure(Main mainJoueurEsku, Main mainJoueurZaku) {
        var cartesJoueurEsku = mainJoueurEsku.cartesDuPlusGrandAuPlusPetit();
        var cartesJoueurZaku = mainJoueurZaku.cartesDuPlusGrandAuPlusPetit();

        for (var i = 0; i < Main.TAILLE; i++) {
            ValeurCarte.Comparaison compare = cartesJoueurEsku.get(i).comparerAvec(cartesJoueurZaku.get(i));
            if (compare == PLUS_GRANDE) {
                return true;
            }
            if (compare == PLUS_PETITE) {
                return false;
            }
        }
        return true;
    }
}
