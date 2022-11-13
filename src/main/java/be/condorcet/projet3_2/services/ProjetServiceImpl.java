package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Projet;
import be.condorcet.projet3_2.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class ProjetServiceImpl implements InterfProjetService {

    @Autowired
    private ProjetRepository projetRepository;

    @Override
    public Projet create(Projet projet) throws Exception {
        projetRepository.save(projet);
        return projet;
    }

    @Override
    public Projet read(Integer id) throws Exception {
        Optional<Projet> opj = projetRepository.findById(id);
        return opj.get();
    }

    @Override
    public Projet update(Projet projet) throws Exception {
        read(projet.getIdProjet());
        projetRepository.save(projet);
        return projet;
    }

    @Override
    public void delete(Projet projet) throws Exception {
    projetRepository.delete(projet);
    }

    @Override
    public List<Projet> all() throws Exception {
        return projetRepository.findAll();
    }

    public List<Projet> read(String titre){
        return projetRepository.findProjetsByPjTitreLike(titre);
    }
}
