# Projet MOBG5

Ce dépôt contient les sources du projet Messaging.

## Description

Messaging permet d'envoyer des messages vers des pairs de confiance et de manière sécurisée. Pour communiquer avec un pair, il faut au préalable avoir effectué un échange de clés publiques via NFC avec le pair. Une clé ECDH est alors générée sur base de la clé publique reçue via NFC et notre clé privée. Les messages sont stockés chiffrés pâr la clé ECDH dans une base de données Firebase.

## Persistance des données

Les messages sont stockés chiffrés pâr la clé ECDH dans une base de données Firebase.

Les données relatives aux comptes utilisateurs, aux notes et aux critiques sont persistées via firebase : <url du projet firebase>

## Auteur

**Noé Delcroix** g55990
