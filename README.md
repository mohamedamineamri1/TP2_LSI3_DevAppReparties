# TP2 : Sockets TCP en mode connecté 

**Etudiant :** AMRI  Mohamed Amine 

**Groupe :** LSI 3 

**Matière :** Développement d'applications réparties 

Enseignante des TPs : MMe Zeineb DAOUD 

**Année universitaire :** 2026 – 2027 


## Environnement
- JDK 17
- IDE : Eclipse 
- Git

## Structure du projet

```
src/
├── activite2_1/   (échange de chaînes de caractères)
│   ├── Serveur.java
│   ├──  Client.java
    └──Captures (execution)
└── activite2_2/   (échange d'objets)
    ├── Operation.java
    ├── Serveur.java
    ├── Captures (execution)
    └── Client.java
```

## Activité 2-1 : Échange de chaînes de caractères

Le client envoie le premier opérande, l'opérateur et le deuxième opérande,
chacun sur une ligne. Le serveur calcule et renvoie le résultat.
La syntaxe est validée côté client (expression régulière) avant l'envoi.

Classes utilisées : `BufferedReader`, `InputStreamReader`, `PrintWriter`.

## Activité 2-2 : Échange d'objets

Le client envoie un objet `Operation` (classe `Serializable`) contenant
les deux opérandes et l'opérateur. Le serveur calcule et renvoie le résultat.

Classes utilisées : `Serializable`, `ObjectOutputStream`, `ObjectInputStream`. 

## Conclusion

Ce TP a permis de comprendre l'echange de chaines et d'objets
entre un client et un serveur via les sockets TCP en Java.
