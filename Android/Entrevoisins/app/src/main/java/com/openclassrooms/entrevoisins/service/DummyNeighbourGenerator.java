package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, false ,"Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d","Saint Pierre du mont à 5km","+33 6 86 57 90 14","www.facebook.fr/caroline","Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(2, false ,"Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e","","","",""),
            new Neighbour(3, false ,"Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f","","","",""),
            new Neighbour(4, false ,"Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a","","","",""),
            new Neighbour(5, false ,"Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b","","","",""),
            new Neighbour(6, false ,"Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c","","","",""),
            new Neighbour(7, false ,"Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d","","","",""),
            new Neighbour(8, false ,"Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b","","","",""),
            new Neighbour(9, false ,"Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d","","","",""),
            new Neighbour(10, false ,"Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d","","","",""),
            new Neighbour(11, false ,"Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d","","","",""),
            new Neighbour(12, false ,"Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d","","","","")
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}
