# EPSI_TDD_Restaurant


## Composition du groupe: 

CAPRIO LOMBARDI Thomas

DOURSIN Greg

AUDERAERT Arthur

DIMONT Jimmy

M'HAMDI Farid

ROUICHI Cédric

**Description**

Développement du sujet Restaurant en utilisant la méthode du TDD.


**Remarques**

* Attention le code est loin d'être très très propre, tant au niveau de la conception qu'au niveau de la complexité des méthodes. La consigne étant de faire des tests, de nombreuses notions de POO ont été homises, l'héritage n'est pas optimal.
De plus, je suis nul en tests.

* Les règles de nommages sont les suivantes:
    Tous les attributs d'objets possèdent leur type en préfixe.
- a pour ArrayList
- o pour objet (ao = ArrayList<Object> avec le type d'objet généralement contenu dans le nom du paramètre)
Ex: aoCommandes est une ArrayList<Commande>.

- s pour String
- d pour double
- b pour boolean 
- etc



* Les itérations 3 et 4 ne contiennent pas les différentes factorisations effectuées lors de l'itération 2.


### Installation:
**Iteration 1,2,3**
- Necessite Junit: junit-4.10.jar

**Iteration 3**
- Necessite sqlite -> ajouter au build path le jar suivant: sqlite-jdbc-3;34.0.jar



**Iteration 4** 
- npm install dans le dossier API/
- Surement remplacer le chemin en dur ligne 8 de index.js ...
## Concernant les tests:

Bien que nous ayons utilisé différentes façons de tester (unitaire, recette, intégration... ) 
il aurait été intéressant, dans une situation réelle, de réaliser des tests de performances.
En effet, le sujet étant ici la restauration, l’efficacité d’un service et donc de la prise de commande par un serveur est primordiale. Tester la rapidité des changements de menu, l’envoi d’une commande en cuisine ou encore le refus d’une commande par celle-ci aurait été pertinent.


