package be.condorcet.projet3_2.entities;


import be.condorcet.projet3_2.entities.associations.TravailID;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity

@Table(name = "APITRAVAIL", schema = "ORA9", catalog = "ORCL")
public class Travail {
    @EmbeddedId
    private TravailID id;

    @Column(name = "TAPOURCENTAGE")
    private int taPourcent;
    @Column(name = "TADATEENGAG")
    private LocalDate taDateEngagement;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEmp")
    private Employe idEmp;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPj")
    private Projet idPj;

}
