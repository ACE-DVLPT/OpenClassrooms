package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, false ,"Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d","Saint Pierre du mont à 5km","+33 6 86 57 90 14","www.facebook.fr/caroline","Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(2, true ,"Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e","4 rue saint Antonin","+33 6 20 15 75 78","www.facebook.fr/Jack",""),
            new Neighbour(3, false ,"Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f","40 rue Pradal","+33 6 65 48 78 23","www.facebook.fr/Chloé",""),
            new Neighbour(4, false ,"Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a","54 rue du Canon d'Arcale","+33 6 65 48 23 75","www.facebook.fr/Vincent",""),
            new Neighbour(5, true ,"Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b","45  rue des Fontaines","+33 6 48 23 54 78","www.facebook.fr/Elodie",""),
            new Neighbour(6, false ,"Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c","55 rue de la République","+33 7 84 62 15 78","www.facebook.fr/Sylvain",""),
            new Neighbour(7, false ,"Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d","12 rue du Tchad","+33 6 95 48 75 36","www.facebook.fr/Laetitia",""),
            new Neighbour(8, false ,"Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b","5 rue de la Gravette","+33 7 18 56 48 56","www.facebook.fr/Dan",""),
            new Neighbour(9, false ,"Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d","12 rue Gastn Phoebus","+33 7 15 85 63 12","www.facebook.fr/Joseph",""),
            new Neighbour(10, false ,"Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d","36 Avenue Alain Gerbault","+33 6 48 56 23 17","www.facebook.fr/Emma",""),
            new Neighbour(11, false ,"Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d","24 rue d'Antipoul","+33 7 62 18 23 54","www.facebook.fr/Patrick",""),
            new Neighbour(12, false ,"Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d","22 rue des Gardenias","+33 6 14 56 15 47","www.facebook.fr/Ludovic","")
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}
