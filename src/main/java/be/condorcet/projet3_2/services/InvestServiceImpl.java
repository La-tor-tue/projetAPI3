package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Invest;
import be.condorcet.projet3_2.entities.associations.InvestID;
import be.condorcet.projet3_2.repositories.InvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class InvestServiceImpl implements InterfInvestService{

    @Autowired
    private InvestRepository investRepository;

    @Override
    public Invest create(Invest invest) throws Exception {
        investRepository.save(invest);
        return invest;
    }

    @Override
    public Invest read(InvestID id) throws Exception {
        Optional<Invest> oInv=investRepository.findById(id);
        return oInv.get();
    }

    @Override
    public Invest read(Integer idPj, Integer idDis) throws Exception {
        return investRepository.findByIdPjAndIdDis(idPj,idDis);
    }

    @Override
    public Invest update(Invest invest) throws Exception {
        read(invest.getIdPj(), invest.getIdDis());
        investRepository.save(invest);
        return invest;
    }

    @Override
    public void delete(Invest invest) throws Exception {
        investRepository.delete(invest);
    }

    @Override
    public List<Invest> all() throws Exception {
        return investRepository.findAll();
    }
}
