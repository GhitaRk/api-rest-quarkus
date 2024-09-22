# Projet api-rest-quarkus_cave

## Sommaire

[TOC]

## 1 - Configuration de l'environnement de travail

### 1.1 - Configuration de git

```shell
git config --global user.name "<Prénom Nom>"
git config --global user.email "<prénom.nom>@grenoble-inp.org"
```

### 1.2 - Récupération du projet git

- https

```shell
git clone https://gitlab.com/cs550g01/api-rest-quarkus_cave.git
```

- ssh

```shell
git clone git@gitlab.com:cs550g01/api-rest-quarkus_cave.git
```

> Si ssh, n'oubliez pas d'ajouter une clef ssh à votre compte gitlab

Cette commande va créer un dossier `api-rest-quarkus_cave` dans votre répertoire courant avec les source du projet.

### 1.3 - Importation du projet dans Eclipse

Dans Eclipse, cliquez sur `File` -> `Import...` -> `Maven` -> `Existing Maven Projects` puis `Next >` et `Browse ...`.

### 1.4 - Configuration de Apache Maven dans Eclipse

Dans `Window` -> `Preferences` -> `Maven` -> `User Settings` remplir la partie `Global Settings` avec le chemin du fichier suivant : `/opt/Maven/Maven-3.8.4/conf/setting.xml`, puis cliquez sur `Apply`.


## 2 - Lancer le serveur Quarkus

Dans le répertoire du projet, lancer la commande suivante :

```shell
mvn quarkus:dev
```

Par défaut, l'application est déployée à l'adresse suivante : `http://localhost:8080`.