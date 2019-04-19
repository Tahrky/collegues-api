# collegues-api

## Recherche d'un collegue selon son nom (GET)

Lien pour l'application : https://biraben-collegues-api.herokuapp.com/collegues?nom=XXX
```
Noms disponibles :
- Bob (2 retours)
- Adeline (1 retour)
```
## Recherche d'un collegue selon son matricule (GET)
Lien pour l'application : https://biraben-collegues-api.herokuapp.com/collegues/${MATRICULE}

```
Un des collegues dont le nom est bob possède un matricule par défaut qui est : "1"
```

## Ajout d'un collegue (POST)
Lien pour l'application : https://biraben-collegues-api.herokuapp.com/collegues
Pour ajouter un collègue, toutes les informations doivent être remplies (nom, prénoms, email, photoUrl, dateDeNaissance) et valides.

```
{
   "nom" : "Pablo",
   "prenoms" : "Miguel",
   "dateDeNaissance" : "2000-01-01",
   "photoUrl" : "https://photoQuiExistePas.com/89854.jpg",
   "email": "pouetpouet@a.a"
}
```

## Modification de l'Email et/ou de la PhotoUrl (PATCH)
Lien pour l'application : https://biraben-collegues-api.herokuapp.com/collegues

```
{
   "photoUrl" : "https://photoQuiExistePas.com/89854.jpg"
}
```
