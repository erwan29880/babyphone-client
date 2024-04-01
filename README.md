# Babyphone : client   

Ce programme est l'interface client, le serveur est disponible ici : https://github.com/erwan29880/babyphone-serveur  

Ce programme a été utilisé comme babyphone, la contrainte de la maison rendait les babyphones 'talkie-walkie' impossibles à utiliser, d'où la nécessité de passer par le réseau local. De plus, les babyphones wifi du commerce sont tous avec une webcam, ce qui était inutile, et sont très onéreux.  
Le serveur est installé sur un RaspberryPi 4, sous ubuntu server 22.04LTS 64, avec un micro Usb samsic. Le client graphique a été testé sous ubuntu 22.04LTS et windows 11.

## description

Interface graphique pour la réception de données d'un micro distant. Le serveur distant se démarre en ssh au lancement du programme. 
Un seuil audio permet de définir le niveau de détection sonore pour la ligne de sortie audio, afin d'éviter les petits bruits si nécessaires. 

![capture](bc.png)

## configuration   

Le serveur doit être installé. Renseigner les différents champs du fichier configuration.Constantes. Les valeurs pour l'audio doivent être identiques à celles du serveur.

Une librairie externe est nécessaire pour la connexion ssh : JSch - java Secure channel.  
Version utilisée : jsch-0.1.55.jar

## lancement : 

La méthode main est dans la classe App.java .   
Il est possible de supprimer le lancement du serveur par SSH, ce qui évite toute configuration ssh ; il faut suivre les commentaires dans la méthode public static void main(string[] args) de la classe principale App.java

### fichier .sh et .bat   
Destinés à mon usage pour le développement et le test. Les tests dépendent de la configuration et ne sont pas fournis.