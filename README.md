# Mus!

![Mus](img/mus.png)

La Fédération Française de Mus (https://www.federation-mus.fr/) souhaite depuis longtemps réaliser une application pour permettre à tous les amoureux de ce jeu de pouvoir jouer n’importe où et n’importe quand.

Pour ceux qui ne connaitraient pas encore ce jeu de cartes (comment est-ce possible ?), c’est un jeu  qui se pratique à 1 contre 1 ou le plus souvent à 2 contre 2. Pour espérer remporter une partie, il vous faudra avoir de la chance (beaucoup), bluffer (à vos risques et périls) et prendre les bonnes décisions. Les règles sont présentées dans les pages suivantes de ce document.

La FFM décide finalement de faire appel à un jeune développeur local, Pettan JUANICOTENA, pour réaliser l’application. Ce développeur suit les principes du développement agile, aussi il a prévu de travailler sur plusieurs cycles, afin de faire valider les fonctionnalités au fur et à mesure :

* Itération 1 : Le joueur peut défier l’ordinateur en 1 contre 1 dans un terminal.
* Itération 2 : Le joueur peut jouer en 2 contre 2 dans un terminal (adversaires et équipier ordinateurs uniquement).
* Itération 3 : Le joueur peut personnaliser le niveau de l’ordinateur (Débutant, Intermédiaire, Confirmé).
* Itération 4 : Le joueur peut affronter ses amis en réseau.
* Itération 5: Le joueur peut jouer dans une interface graphique en 2D sur mobile.

Comme Pettan est plutôt débordé en ce moment, il fait appel à vous pour prendre le relai. Il a malgré tout réussi à finaliser la première itération, en Java. Ce dépôt _Git_ en contient le code source.

Familiarisez-vous avec les [règles du jeu](docs/regles_du_jeu.pdf), jouez avec cette application et notez d’ores et déjà vos idées d’amélioration et bugs potentiels. Vous pouvez aussi rechercher _El Mus_ sur Play Store ou Apple Store pour apprendre les règles (application en espagnol...).

## Lancer les tests

    ./gradlew test

## Lancer l'application

    ./gradlew -q --console plain run

## Construire le jar de l'application

    ./gradlew assemble

Le jar se trouvera dans build/libs/
