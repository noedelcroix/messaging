# Projet MOBG5

Short android project built with Kotlin. It is a message app working on Firebase and allowing to exchange user id (to pair) with nearby messages. It was my mobile course project. It is a completely experimental project.

## Description

~~Messaging permet d'envoyer des messages vers des pairs de confiance et de manière sécurisée. Pour communiquer avec un pair, il faut au préalable avoir effectué un échange de clés publiques via NFC avec le pair. Une clé ECDH est alors générée sur base de la clé publique reçue via NFC et notre clé privée. Les messages sont stockés chiffrés pâr la clé ECDH dans une base de données Firebase.~~
L'implémentation de la génération des clés ECDH et de l'encryption des messages n'ayant pas été fait, les messages ne sont pas sécurisés et seul l'échange des user id Firebase est fait via des des Nearby Messages, l'échange NFC via Android Beam étant déprécié.

## Persistance des données

Les messages sont stockés chiffrés pâr la clé ECDH dans une base de données Firebase.

Les données relatives aux comptes utilisateurs, aux notes et aux critiques sont persistées via firebase : <url du projet firebase>

## Auteur

**Noé Delcroix** g55990
