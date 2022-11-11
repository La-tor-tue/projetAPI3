package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Discipline;
import be.condorcet.projet3_2.entities.Employe;
import be.condorcet.projet3_2.entities.Invest;
import be.condorcet.projet3_2.entities.Projet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvestServiceImplTest {

    @Autowired
    private InterfInvestService interfInvestService;

    @Autowired
    private InterfProjetService interfProjetService;

    @Autowired
    private InterfDisciplineService interfDisciplineService;

    Invest inv1;
    Projet pj1;
    Discipline dis1;

    @BeforeEach
    void setUp() {
        try {
            pj1 = new Projet(null, "ProjetTest1", null, null, new BigDecimal(10), new ArrayList<>(), new ArrayList<>());
            interfProjetService.create(pj1);
            System.out.println("CREATION DU PROJET " + pj1);
            dis1 = new Discipline(null, "NomTest", "DescTest", new ArrayList<>(), new ArrayList<>());
            interfDisciplineService.create(dis1);
            System.out.println("CREATION DE LA DISCIPLINE " + dis1);
            int idPj= pj1.getIdProjet();
            int idDis= dis1.getIdDis();
            inv1 = new Invest(idDis, idPj, 10, dis1, pj1);
            interfInvestService.create(inv1);
            System.out.println("CREATION DE L INVEST " + inv1 + " /DU PJ " + pj1 + " ET DE LA DISCIPLINE " + dis1);
        } catch (Exception e) {
            System.out.println("SET UP ERROR: ERREUR DE CREATION: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        try {
            interfInvestService.delete(inv1);
            System.out.println("DESTRUCTION DE : " + inv1);
            interfProjetService.delete(pj1);
            System.out.println("DESTRUCTION ANNEXE DE : " + pj1);
            interfDisciplineService.delete(dis1);
            System.out.println("DESTRUCTION ANNEXE DE : "+dis1);
        } catch (Exception e) {
            System.out.println("TEAR DOWN ERROR : ERREUR DE DELETE: "+ e.getMessage());
        }
    }

    @Test
    void create() {
    }

    @Test
    void read(){
        
    }

    @Test
    void readByAssociation() {
        try {
            Invest invTest=interfInvestService.read(inv1.getIdPj(), inv1.getIdDis());
            assertEquals(10,invTest.getInvQuantJH(),"Quantité différente");
            assertEquals(pj1,invTest.getInvPj(),"Projet différent");
            assertEquals(dis1,invTest.getInvDis(),"Discipline différent");
        }catch (Exception e){
            fail("Recherche d'INVEST ERROR: "+e);
        }
    }

    @Test
    void testRead() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void all() {
    }
}