package be.condorcet.projet3_2.webservices;

import be.condorcet.projet3_2.entities.Projet;
import be.condorcet.projet3_2.services.InterfProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/projets")
public class RestProjet {

    @Autowired
    private InterfProjetService projetService;

    //----Lire un projet pour un id------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Projet> getProjet(@PathVariable(value = "id")int id)throws Exception{
        System.out.println("Recherche du projet avec: "+id);
        Projet projet =projetService.read(id);
        return new ResponseEntity<>(projet, HttpStatus.OK);
    }

    //-----Lire un projet sur un Titre----
    @RequestMapping(value = "/titre={titre}", method = RequestMethod.GET)
    public ResponseEntity<Projet> getProjet(@PathVariable(value = "titre") String titre) throws Exception{
        System.out.println("Recherche du projet sur le titre: "+titre);
        Projet projet = projetService.read(titre);
        return new ResponseEntity<>(projet,HttpStatus.OK);
    }

    //-----Créer un projet ---------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) throws Exception{
        System.out.println("Creation du projet: "+projet.getPjTitre());
        projetService.create(projet);
        return new ResponseEntity<>(projet,HttpStatus.OK);
    }

    //------Update un projet ------------
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Projet> updateProjet(@PathVariable(value = "id")int id,@RequestBody Projet upprojet) throws Exception{
        System.out.println("Update du projet: "+id);
        upprojet.setIdProjet(id);
        Projet pj=projetService.update(upprojet);
        return new ResponseEntity<>(pj,HttpStatus.OK);
    }

    //-----Delete un projet ----------------
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProjet(@PathVariable(value = "id")int id) throws Exception{
        System.out.println("Delete du projet: "+id);
        Projet projet = projetService.read(id);
        projetService.delete(projet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-----Trouver tous les projets --------
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Projet>> getAll() throws Exception{
        System.out.println("Recherche de tous les projets");
        return new ResponseEntity<>(projetService.all(),HttpStatus.OK);
    }

    //-----Trouver tous les projets ---------

    @RequestMapping(value = "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Projet>> getAll(Pageable pageable) throws Exception{
        System.out.println("Recherche de tous les projets");
        return new ResponseEntity<>(projetService.allp(pageable),HttpStatus.OK);
    }


    //------Gestion des erreures ------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception e) {
        System.out.println("ERROR: "+e.getMessage());
        return ResponseEntity.notFound().header("ERROR",e.getMessage()).build();
    }
}
