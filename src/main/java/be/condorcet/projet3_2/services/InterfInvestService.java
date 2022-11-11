package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Invest;
import be.condorcet.projet3_2.entities.associations.InvestID;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfInvestService extends InterfService<Invest, InvestID>{


    Invest read(Integer idPj, Integer idDis) throws Exception;
}
