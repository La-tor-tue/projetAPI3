package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Travail;
import be.condorcet.projet3_2.entities.associations.TravailID;

public interface InterfTravailService extends InterfService<Travail, TravailID>{

    Travail read(Integer idPj, Integer idEmp) throws Exception;
}
