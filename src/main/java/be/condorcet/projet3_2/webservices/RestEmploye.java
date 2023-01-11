package be.condorcet.projet3_2.webservices;


import be.condorcet.projet3_2.entities.Discipline;
import be.condorcet.projet3_2.entities.Employe;
import be.condorcet.projet3_2.entities.Projet;
import be.condorcet.projet3_2.services.EmployeServiceImpl;
import be.condorcet.projet3_2.services.InterfEmployeService;
import be.condorcet.projet3_2.services.InterfProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/employes")
public class RestEmploye {

    @Autowired
    private EmployeServiceImpl employeService;


    //----Lire un Employe pour un id------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employe> getEmploye(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("Recherche de l'employe: " + id);
        Employe employe = employeService.read(id);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    //-----Lire les Employes sur le nom----
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Employe>> getEmploye(@PathVariable(value = "nom") String nom) throws Exception {
        System.out.println("Recherche des employes ayant le nom: " + nom);
        List<Employe> lEmp = employeService.read(nom);
        return new ResponseEntity<>(lEmp, HttpStatus.OK);
    }

    //Lire les disciplines des employées ayant le nom qui commence part
    @RequestMapping(value = "/rech={rech}", method = RequestMethod.GET)
    public ResponseEntity<List<Discipline>> getDisciplinesByNomLike(@PathVariable(value = "rech") String nom) throws Exception {
        System.out.println("Recherche des employes ayant le nom: " + nom);
        List<Employe> lEmp = employeService.read(nom);
        List<Discipline> lDis = new ArrayList<>();
        if (lEmp != null) {
            for (int i = 0; i < lEmp.size(); i++) {
                if (!lDis.contains(lEmp.get(i).getEmpDis())) lDis.add(lEmp.get(i).getEmpDis());
            }
        }
        return new ResponseEntity<>(lDis, HttpStatus.OK);
    }
    @RequestMapping(value = "/iddis={iddis}", method = RequestMethod.GET)
    public ResponseEntity<List<Employe>> getEmployeByDiscipline(@PathVariable(value = "iddis") int iddis) throws Exception {
        System.out.println("Recherche des employes ayant la discipline id: " + iddis);
        List<Employe> lEmp = employeService.allByDis(iddis);
        return new ResponseEntity<>(lEmp, HttpStatus.OK);
    }

    //-----Créer un Employe ---------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Employe> createEmploye(@RequestBody Employe employe) throws Exception {
        System.out.println("Creation de l'Employe: " + employe.getEmpNom() + " " + employe.getEmpPrenom());
        employeService.create(employe);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    //------Update un Employe ------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employe> updateEmploye(@PathVariable(value = "id") int id, @RequestBody Employe upemploye) throws Exception {
        System.out.println("Update de l'employe: " + id);
        upemploye.setIdEmp(id);
        Employe emp = employeService.update(upemploye);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    //-----Delete un Employe ----------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEmploye(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("Delete de l'employe: " + id);
        Employe employe = employeService.read(id);
        employeService.delete(employe);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-----Trouver tous les Employes --------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Employe>> getAll() throws Exception {
        System.out.println("Recherche de tous les employes");
        return new ResponseEntity<>(employeService.all(), HttpStatus.OK);
    }

    //-----Trouver tous les Employes Pageable ---------

    @RequestMapping(value = "/allp", method = RequestMethod.GET)
    public ResponseEntity<Page<Employe>> getAll(Pageable pageable) throws Exception {
        System.out.println("Recherche de tous les Employes");
        return new ResponseEntity<>(employeService.allp(pageable), HttpStatus.OK);
    }

    //------Gestion des erreures ------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception e) {
        System.out.println("ERROR: " + e.getMessage());
        return ResponseEntity.notFound().header("ERROR", e.getMessage()).build();
    }
}
