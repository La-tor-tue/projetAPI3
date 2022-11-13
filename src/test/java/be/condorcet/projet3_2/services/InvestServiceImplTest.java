package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Discipline;
import be.condorcet.projet3_2.entities.Employe;
import be.condorcet.projet3_2.entities.Invest;
import be.condorcet.projet3_2.entities.Projet;
import be.condorcet.projet3_2.entities.associations.InvestID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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

            int idPj = pj1.getIdProjet();
            int idDis = dis1.getIdDis();

            InvestID tmp = new InvestID(idDis, idPj);

            inv1 = new Invest(tmp, dis1, pj1, 10);

            System.out.println("CREATION DE L INVEST " + inv1 + " /DU PJ " + pj1 + " ET DE LA DISCIPLINE " + dis1);
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
        } catch (Exception e) {
            System.out.println("TEAR DOWN ERROR : ERREUR DE DELETE: " + e.getMessage());
        }

        try {
            interfProjetService.delete(pj1);
            System.out.println("DESTRUCTION ANNEXE DE : " + pj1);
            interfDisciplineService.delete(dis1);
            System.out.println("DESTRUCTION ANNEXE DE : " + dis1);
        } catch (Exception e) {
            System.out.println("TEAR DOWN ERROR : ERREUR DE DELETE: " + e.getMessage());

        }
    }

    @Test
    void create() {
        assertNotEquals(0, inv1.getIdDis(), "Id Discipline non inséré");
        assertNotEquals(0, inv1.getIdPj(), "Id Projet non inséré");
        assertEquals(10, inv1.getInvQuantJH(), "Quantité JH non record");
    }

    @Test
    void read() {
        try {
            Invest invTest = interfInvestService.read(inv1.getId());
            assertEquals(10, invTest.getInvQuantJH(), "Quantité différente");
            assertEquals(pj1, invTest.getIdPj(), "Projet différent");
            assertEquals(dis1, invTest.getIdDis(), "Discipline différent");
        } catch (Exception e) {
            fail("Recherche d'INVEST ERROR: " + e);
        }
    }

    @Test
    void testDoublon() {
        InvestID tmp = new InvestID(dis1.getIdDis(), pj1.getIdProjet());
        Invest investissment = new Invest(tmp, dis1, pj1, 10);
        Assertions.assertThrows(Exception.class, () -> {
            interfInvestService.create(investissment);
        }, "Create double Invest");
    }

    @Test
    void readByAssociation() {
        try {
            int idPj = pj1.getIdProjet();
            int idDis = dis1.getIdDis();
            Invest investissement = interfInvestService.read(idPj, idDis);
            assertEquals(idDis, investissement.getIdDis(), "Id Disciplines différents");
            assertEquals(idPj, investissement.getIdPj(), "Id Projet différents");
            assertEquals(10, investissement.getInvQuantJH(), "Quantité différents");
        } catch (Exception e) {
            fail("Recherche sans resultat");
        }

    }

    @Test
    void update() {
        try {
            inv1.setInvQuantJH(20);
            inv1 = interfInvestService.update(inv1);
            assertEquals(20, inv1.getInvQuantJH(), "Quantiter différents");
        } catch (Exception e) {
            fail("Erreur update fail: " + e);
        }

    }

    @Test
    void delete() {
        try {
            interfInvestService.delete(inv1);
            Assertions.assertThrows(Exception.class, () -> {
                interfInvestService.read(inv1.getId());
            }, "Record non delete");
        } catch (Exception e) {
            fail("Erreur delete: " + e);
        }
    }

    @Test
    void all() {
        try {
            List<Invest> listInvest=interfInvestService.all();
            assertNotEquals(0,listInvest.size(),"Liste vide");
        }catch (Exception e){
            fail("Erreur du read All: "+e);
        }
    }
}