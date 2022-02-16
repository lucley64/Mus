package com.montaury.mus.jeu.joueur;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Choix;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Idoki;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mintza;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InterfaceJoueurOrdinateur implements InterfaceJoueur {
    private final Map<TypeChoix, Choix> choixPreetablis = Map.of(
            TypeChoix.MUS, new Mus(),
            TypeChoix.MINTZA, new Mintza(),
            TypeChoix.IMIDO, new Imido(),
            TypeChoix.PASO, new Paso(),
            TypeChoix.IDOKI, new Idoki(),
            TypeChoix.TIRA, new Tira(),
            TypeChoix.GEHIAGO, new Gehiago(),
            TypeChoix.KANTA, new Kanta()
    );
    private Main main;
    private final Random random = new Random();

    @Override
    public void nouvelleMain(Main main) {
        this.main = main;
    }

    @Override
    public List<Carte> cartesAJeter() {
        return new ArrayList<>(main.cartes());
    }

    @Override
    public Choix faireChoixParmi(List<TypeChoix> choixPossibles) {
        var choixOrdinateur = new ArrayList<>(choixPossibles);
        // on ne veut pas que l'ordinateur fasse Hordago
        choixOrdinateur.remove(TypeChoix.HORDAGO);
        var choixAleatoire = choixOrdinateur.get(random.nextInt(choixOrdinateur.size()));
        return choixPreetablis.get(choixAleatoire);
    }
}
