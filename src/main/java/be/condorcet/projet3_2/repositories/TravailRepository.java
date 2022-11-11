package be.condorcet.projet3_2.repositories;

import be.condorcet.projet3_2.entities.Travail;
import be.condorcet.projet3_2.entities.associations.TravailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravailRepository extends JpaRepository<Travail, TravailID> {
    Travail findTravailByIdEmpAndIdPj(Integer idEmp,Integer idPj);
}
