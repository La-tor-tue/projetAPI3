package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Discipline;

import java.util.List;

public interface InterfDisciplineService extends InterfService<Discipline,Integer> {
    public List<Discipline> read(String nom);
}
