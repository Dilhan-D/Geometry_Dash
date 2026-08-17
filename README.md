# Geometry Dash

Clone de **Geometry Dash** réalisé en **Java avec JavaFX**.

Le but du projet est de reproduire les principales mécaniques du jeu : déplacement automatique du joueur, saut, collisions avec les obstacles, effets visuels, musique et différents skins.

## Fonctionnalités

* Déplacement automatique du joueur
* Saut avec la touche `ESPACE`
* Rotation du joueur pendant les sauts
* Mode vol avec les anneaux
* Plusieurs types d'obstacles
* Système de collision
* 12 skins disponibles
* Effets de particules
* Arrière-plan avec effet de parallaxe
* Musique et effets sonores
* Possibilité de couper la musique
* Écran de fin de niveau

## Technologies

* **Java**
* **JavaFX**
* Gestion audio avec un système dédié au projet

## Structure du projet

```text
GeométrieDash/
├── src/
│   ├── game/
│   │   ├── Player.java
│   │   ├── Level.java
│   │   ├── Input.java
│   │   └── GameObject.java
│   │
│   ├── obstacles/
│   │   ├── GameObstacle.java
│   │   ├── Block.java
│   │   ├── Spike.java
│   │   └── FlyRing.java
│   │
│   ├── menu/
│   │   ├── Menu.java
│   │   └── EndLevelScreen.java
│   │
│   ├── audio/
│   │   └── AudioManager.java
│   │
│   └── resources/
│       ├── skins/
│       ├── icons/
│       └── Background.png
```

### Quelques classes

* `Player` : gère le joueur, ses déplacements, ses sauts et ses effets.
* `Level` : contient la logique du niveau et les différents éléments qui le composent.
* `Input` : gère les entrées du clavier.
* `GameObject` : interface commune aux objets du jeu.
* `GameObstacle` : classe de base pour les différents obstacles.
* `AudioManager` : s'occupe de la musique et des effets sonores.
* `Menu` : gère le menu principal et la sélection des skins.

## Contrôles

| Touche   | Action              |
| -------- | ------------------- |
| `ESPACE` | Saut / Vol          |
| `ESC`    | Retour au menu      |
| Clic     | Sélection d'un skin |

## Installation

### Prérequis

* Java 11 ou supérieur
* JavaFX 11 ou supérieur
* Un IDE Java compatible, par exemple IntelliJ IDEA ou Eclipse

### Lancement

1. Cloner le projet.
2. Ouvrir le projet dans l'IDE.
3. Vérifier que JavaFX est correctement configuré.
4. Compiler le projet.
5. Lancer `Main.java`.

## Améliorations possibles

Quelques fonctionnalités pourraient être ajoutées par la suite :

* Plusieurs niveaux
* Davantage de types d'obstacles
* Système de score
* Sauvegarde de la progression
* Éditeur de niveaux
* Davantage de skins et de personnalisations
