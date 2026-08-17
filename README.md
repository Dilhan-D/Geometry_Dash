# Geometry Dash 

Un clone du célèbre jeu Geometry Dash développé en Java avec JavaFX.

## 🎮 Caractéristiques

- Gameplay basé sur le timing et les réflexes
- Système de skins personnalisables (12 skins disponibles)
- Effets visuels (particules, rotations)
- Système de collision précis
- Musique et effets sonores
- Mode vol avec anneaux spéciaux
- Obstacles variés (pics, blocs, anneaux)

## 🛠️ Technologies Utilisées

- Java
- JavaFX pour l'interface graphique
- Système de gestion audio personnalisé

## 🏗️ Architecture du Projet

GeométrieDash/
├── src/
│   ├── game/
│   │   ├── Player.java         # Gestion du joueur
│   │   ├── Level.java          # Création et gestion des niveaux
│   │   ├── Input.java          # Gestion des entrées
│   │   └── GameObject.java     # Interface de base
│   ├── obstacles/
│   │   ├── GameObstacle.java   # Classe de base des obstacles
│   │   ├── Block.java          # Blocs sur lesquels atterrir
│   │   ├── Spike.java          # Obstacles mortels
│   │   └── FlyRing.java        # Anneaux donnant le mode vol
│   ├── menu/
│   │   ├── Menu.java           # Menu principal
│   │   └── EndLevelScreen.java # Écran de fin de niveau
│   ├── audio/
│   │   └── AudioManager.java   # Gestion de l'audio
│   └── resources/
│       ├── skins/              # Images des skins
│       ├── icons/              # Icônes de l'interface
│       └── Background.png      # Arrière-plan du jeu
```

## 🎯 Fonctionnalités Principales

### Joueur
- Saut avec la touche ESPACE
- Rotation automatique en saut
- Système de particules (trails)
- Changement de skin

### Niveau
- Obstacles placés stratégiquement
- Système de collision sophistiqué
- Fond parallaxe
- Sol avec motif en grille

### Interface
- Menu principal avec sélection de skin
- Bouton mute pour la musique
- Écran de fin de niveau

## 🎵 Audio
- Musique de jeu
- Musique de menu
- Système de mute/unmute

## 🎮 Contrôles
- ESPACE : Saut/Vol
- ESC : Retour au menu
- Clic sur les skins pour changer d'apparence

## 🔧 Installation et Lancement

1. Assurez-vous d'avoir Java et JavaFX installés
2. Clonez le repository
3. Compilez le projet
4. Lancez le jeu via la classe principale Main.java

## 🚀 Prérequis

- Java 11 ou supérieur
- JavaFX 11 ou supérieur
- Un IDE compatible Java (Eclipse, IntelliJ, etc.)

## 🔧 Améliorations Possibles
- Ajout de nouveaux niveaux
- Plus de types d'obstacles
- Système de score
- Sauvegarde de la progression
- Mode éditeur de niveau
