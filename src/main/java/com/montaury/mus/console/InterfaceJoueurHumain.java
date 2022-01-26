package com.montaury.mus.console;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.joueur.InterfaceJoueur;
import com.montaury.mus.jeu.joueur.Main;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Choix;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Idoki;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mintza;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mus;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InterfaceJoueurHumain implements InterfaceJoueur {
    private static final Map<String, Choix> choixSimples = Map.of(
            "MUS", new Mus(),
            "MINTZA", new Mintza(),
            "PASO", new Paso(),
            "IDOKI", new Idoki(),
            "TIRA", new Tira(),
            "HORDAGO", new Hordago(),
            "KANTA", new Kanta(),
            "IMIDO", new Imido(),
            "GEHIAGO", new Gehiago()
    );
    private final Scanner scanner;
    private Main main;

    public InterfaceJoueurHumain() {
        this(new Scanner(System.in));
    }

    InterfaceJoueurHumain(Scanner scannerSaisie) {
        this.scanner = scannerSaisie;
    }

    @Override
    public void nouvelleMain(Main main) {
        this.main = main;
    }

    @Override
    public List<Carte> cartesAJeter() {
        while (true) {
            afficherLigne("Veuillez saisir les positions des cartes à jeter (ex: 1,3) :");
            String aJeter = scanner.nextLine();
            var cartes = identifierCartes(aJeter);
            if (!cartes.isEmpty()) {
                return cartes;
            }
            afficherLigne("Choix incorrect, veuillez re-saisir");
        }
    }

    private List<Carte> identifierCartes(String aJeter) {
        try {
            var indicesCartesAJeter = Arrays.stream(aJeter.split(","))
                    .map(String::trim)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            if (indicesCartesAJeter.stream().allMatch(indice -> indice >= 1 && indice <= 4)) {
                return indicesCartesAJeter
                        .stream()
                        .map(indiceCarte -> main.cartesDuPlusGrandAuPlusPetit().get(indiceCarte - 1))
                        .collect(Collectors.toList());
            }
        } catch (NumberFormatException e) {
            // la saisie n'est pas un chiffre
        }
        return Collections.emptyList();
    }

    @Override
    public Choix faireChoixParmi(List<TypeChoix> choixPossibles) {
        while (true) {
            afficherChoixPossibles(choixPossibles);
            String choixSaisi = scanner.nextLine();
            Optional<Choix> choix = identifier(choixSaisi.trim().toUpperCase());
            if (choix.isPresent() && choixPossibles.contains(choix.get().type())) {
                return choix.get();
            }
            afficherLigne("Choix incorrect, veuillez re-saisir");
        }
    }

    private static Optional<Choix> identifier(String choixSaisi) {
        if (choixSimples.containsKey(choixSaisi)) {
            return Optional.of(choixSimples.get(choixSaisi));
        }
        return identifierMise(choixSaisi);
    }

    private static Optional<Choix> identifierMise(String choixSaisi) {
        var mots = choixSaisi.split("\\s+");
        if (mots.length == 2) {
            try {
                var mise = Integer.parseInt(mots[1]);
                if (mots[0].equals("IMIDO")) {
                    return Optional.of(new Imido(mise));
                }
                if (mots[0].equals("GEHIAGO")) {
                    return Optional.of(new Gehiago(mise));
                }
            } catch (NumberFormatException e) {
                // la mise n'est pas un nombre
            }
        }
        return Optional.empty();
    }

    private void afficherChoixPossibles(List<TypeChoix> choixPossibles) {
        afficher("Faites un choix entre (en toutes lettres): ");
        afficherLigne(choixPossibles.stream().map(TypeChoix::nom).collect(Collectors.joining(" | ")));
    }

    private void afficherLigne(String string) {
        System.out.println(string);
    }

    private void afficher(String string) {
        System.out.print(string);
    }
}
