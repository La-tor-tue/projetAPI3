package be.condorcet.projet3_2.entities;


import be.condorcet.projet3_2.entities.associations.TravailID;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString
@Entity

@Table(name = "APITRAVAIL", schema = "ORA9", catalog = "ORCL")
@IdClass(TravailID.class)
public class Travail {
    @Id
    private int idEmp;
    @Id
    private int idPj;

    @Column(name = "TAPOURCENTAGE")
    private int taPourcent;
    @Column(name = "TADATEENGAG")
    private LocalDate taDateEngagement;

    @ToString.Exclude
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "IDEMP", referencedColumnName = "IDEMP")
    private Employe taEmp;

    @ToString.Exclude
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "IDPJ", referencedColumnName = "IDPJ")
    private Projet taPj;

}
