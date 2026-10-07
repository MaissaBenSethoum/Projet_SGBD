# SGBD — Système de gestion de base de données

Projet académique réalisé à l'INSA Rouen Normandie dans le cadre de la 3e année d'Informatique et Technologies de l'Information (ITI).

## Description

Ce projet consiste à développer un système de gestion de base de données en Java, accompagné d'un interpréteur de commandes SQL.

Le projet comprend notamment :

* un moteur de gestion de base de données ;
* un interpréteur de commandes SQL développé avec JavaCC ;
* la gestion de conditions simples et composées ;
* des tests unitaires avec JUnit ;
* un système de compilation et d'exécution automatisé à l'aide d'un Makefile.

## Technologies

* Java
* JavaCC
* SQL
* JUnit
* Makefile

## Organisation

```text
sgbd/
├── src/              # Code source du projet
├── tests/            # Tests unitaires et fichiers de test
├── Makefile          # Compilation, tests et exécution
└── README.md
```

Les dossiers `classes/` et `classestest/` sont générés automatiquement lors de la compilation et ne sont pas inclus dans le dépôt.

## Utilisation

### Compiler le projet

```bash
make
```

### Générer l'interpréteur avec JavaCC

```bash
make javacc
```

### Lancer le programme

```bash
make run
```

### Exécuter tous les tests

```bash
make test
```

### Exécuter les tests du SGBD

```bash
make test_sgbd
```

### Générer la documentation Javadoc

```bash
make javadoc
```

### Créer une archive JAR

```bash
make jar
```

### Nettoyer les fichiers générés

```bash
make clean
```

## Projet académique

Projet réalisé en groupe à l'INSA Rouen Normandie — Département Informatique et Technologies de l'Information (ITI).

## Auteurs

Maïssa BEN SETHOUM
Yesmina ELLOUOMI
Ewen GUICHARD
Youssef HAJBI
Jules ROUDIL
