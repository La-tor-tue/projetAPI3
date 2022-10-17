package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Discipline;

import java.util.List;

public interface InterfDisciplineService extends InterfService<Discipline> {
    public List<Discipline> read(String nom);
}
