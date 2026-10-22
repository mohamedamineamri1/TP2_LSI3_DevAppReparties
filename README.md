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

## Interprétation des résultats

### Activité 2-1 : chaînes de caractères
- Le client envoie l'opération en trois lignes (opérande 1, opérateur, opérande 2).
- Le serveur calcule et renvoie le résultat : `55 * 25` donne `1375.0`.
- Le résultat est décimal (`1375.0`) car les opérandes sont convertis en `double`.
- La division par zéro (`10 / 0`) renvoie un message d'erreur sans faire planter le serveur.
- Les syntaxes invalides (`25 ** 55`, `abc + 3`) sont rejetées par le client grâce à l'expression régulière, avant tout envoi.
- La saisie de `FIN` ferme proprement les deux programmes.

### Activité 2-2 : objets
- Le client envoie un seul objet `Operation` (`Serializable`) au lieu de trois chaînes.
- Le serveur reçoit l'objet, lit ses attributs, calcule et renvoie le même résultat (`1375.0`), ce qui prouve que la sérialisation fonctionne.
- Les types sont conservés (`double`, `char`) : aucune conversion n'est nécessaire côté serveur.
- Les flux doivent être créés dans l'ordre `ObjectOutputStream`, puis `flush()`, puis `ObjectInputStream`, sinon les deux programmes se bloquent.

### Comparaison
Les deux approches donnent le même résultat. Les chaînes sont simples mais demandent un protocole manuel. Les objets sont plus structurés et extensibles, mais ils imposent la classe `Operation` des deux côtés et le langage Java.

## Conclusion

Ce TP a permis de comprendre l'echange de chaines et d'objets
entre un client et un serveur via les sockets TCP en Java.
