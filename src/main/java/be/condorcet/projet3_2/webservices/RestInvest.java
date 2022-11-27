package be.condorcet.projet3_2.webservices;


import be.condorcet.projet3_2.entities.Discipline;
import be.condorcet.projet3_2.entities.Invest;
import be.condorcet.projet3_2.entities.associations.InvestID;
import be.condorcet.projet3_2.services.InvestServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/invests")
public class RestInvest {

    @Autowired
    private InvestServiceImpl investService;

    //----Lire un Invest pour un id------
    @RequestMapping(value = "/{idDis}/{idPj}", method = RequestMethod.GET)
    public ResponseEntity<Invest> getInvest(@PathVariable(value = "idDis") int idDis,
                                            @PathVariable(value = "idPj") int idPj)throws Exception{
        System.out.println("Recherche de l'Invest': "+idDis+ " et "+idPj);
        Invest invest = investService.read(new InvestID(idDis,idPj));
        return new ResponseEntity<>(invest, HttpStatus.OK);
    }

    //-----Lire un Invest sur ............ ----
    /*  ????????CODE POUR RECHERCHER LES INVEST D UNE DISCIPLINE OU D UN PROJET ??????
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Discipline>> getDiscipline(@PathVariable(value = "nom") String nom) throws Exception{
        System.out.println("Recherche des disciplines ayant le nom: "+nom);
        List<Discipline> lDis = disciplineService.read(nom);
        return new ResponseEntity<>(lDis,HttpStatus.OK);
    }
    */
    //-----Créer un INVEST ---------------
    @RequestMapping(value = "/{idDis}/{idPj}", method = RequestMethod.POST)
    public ResponseEntity<Invest> createInvest(@PathVariable(value = "idDis")int idDis,
                                               @PathVariable(value = "idPj")int idPj,
                                               @RequestBody Invest invest) throws Exception{
        System.out.println("Creation de l'Invest': "+idDis+" et "+idPj);
        InvestID id=new InvestID(idDis,idPj);
        invest.setId(id);
        System.out.println(invest);
        investService.create(invest);
        return new ResponseEntity<>(invest,HttpStatus.OK);
    }

    //------Update un INVEST ------------
    @RequestMapping(value = "/{idDis}/{idPj}",method = RequestMethod.PUT)
    public ResponseEntity<Invest> updateInvest(@PathVariable(value = "idDis")int idDis,
                                               @PathVariable(value = "idPj")int idPj,
                                               @RequestBody Invest upinvest) throws Exception{
        System.out.println("Update de l'Invest: "+idDis+ " et "+idPj);
        upinvest.setId(new InvestID(idDis,idPj));
        Invest invest = investService.update(upinvest);
        return new ResponseEntity<>(invest,HttpStatus.OK);
    }

    //-----Delete un INVEST----------------
    @RequestMapping(value = "/{idDis}/{idPj}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteInvest(@PathVariable(value = "idDis")int idDis,
                                             @PathVariable(value = "idPj")int idPj) throws Exception{
        System.out.println("Update de l'Invest: "+idDis+ " et "+idPj);
        Invest invest = investService.read(new InvestID(idDis,idPj));
        investService.delete(invest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-----Trouver toutes les INVEST --------
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Invest>> getAll() throws Exception{
        System.out.println("Recherche de tous les Invest");
        return new ResponseEntity<>(investService.all(),HttpStatus.OK);
    }

    //-----Trouver toutes les Invest Pageable  ---------

    @RequestMapping(value = "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Invest>> getAll(Pageable pageable) throws Exception{
        System.out.println("Recherche de tous les Invests");
        return new ResponseEntity<>(investService.allp(pageable),HttpStatus.OK);
    }


    //------Gestion des erreures ------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception e) {
        System.out.println("ERROR: "+e.getMessage());
        return ResponseEntity.notFound().header("ERROR",e.getMessage()).build();
    }
}
