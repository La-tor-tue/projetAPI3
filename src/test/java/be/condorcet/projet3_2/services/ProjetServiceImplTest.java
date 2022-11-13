package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Discipline;
import be.condorcet.projet3_2.entities.Projet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjetServiceImplTest {
    @Autowired
    private InterfProjetService projetService;

    Projet pj;

    @BeforeEach
    void setUp() {
        try {
            pj = new Projet(null,"TitreTest",null,null,new BigDecimal(10),new ArrayList<>(),new ArrayList<>());
            projetService.create(pj);
            System.out.println("Création du projet: " + pj);
        }catch (Exception e){
            System.out.println("Erreur lors de la création du projet "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            projetService.delete(pj);
            System.out.println("Delete du projet: " + pj);
        }catch (Exception e){
            System.out.println("Erreur lors du delete du projet "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,pj.getIdProjet(),"Id projet inchangé");
        assertEquals("TitreTest",pj.getPjTitre(),"Titre non enregistré");
        assertEquals( new BigDecimal(10),pj.getPjCout(),"Cout non enregistré");
    }

    @Test
    void read() {

        try {
            int idpj=pj.getIdProjet();
            Projet tmp=projetService.read(idpj);
            assertEquals("TitreTest",tmp.getPjTitre(),"Titre non enregistré");
            assertEquals(new BigDecimal(10),tmp.getPjCout(),"Cout non enregistré");

        }catch (Exception e){
            System.out.println("Erreur lors du read du projet "+e);
        }

    }

    @Test
    void update() {
        try {
            pj.setPjCout(new BigDecimal(20));
            pj.setPjTitre("TitreTestUpdate");

            assertEquals("TitreTestUpdate",pj.getPjTitre(),"Titre non modifié");
            assertEquals(new BigDecimal(20),pj.getPjCout(),"Cout non modifié");

        }catch (Exception e){
            System.out.println("Erreur lors de l'enregistrement du projet "+e);
        }
    }

    @Test
    void delete() {
        try {
            projetService.delete(pj);
            Assertions.assertThrows(Exception.class, () -> {
                projetService.read(pj.getIdProjet());
            },"Record Non Detruit");
        }catch (Exception e){
            System.out.println("Erreur lors du delete du projet "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Projet> projetList = projetService.all();
            assertNotEquals(0,projetList.size(),"La liste est vide");
        } catch (Exception e) {
            fail("Erreur lors de la lecture de tous les projets "+e);
        }

    }
}