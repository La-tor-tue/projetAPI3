package be.condorcet.projet3_2.repositories;

import be.condorcet.projet3_2.entities.Invest;
import be.condorcet.projet3_2.entities.associations.InvestID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestRepository extends JpaRepository<Invest, InvestID> {

    Invest findByIdPjAndIdDis(int idPJ,int idDIS);
}
