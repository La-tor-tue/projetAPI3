package be.condorcet.projet3_2.entities;

import be.condorcet.projet3_2.entities.associations.InvestID;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "APIINVESTISSEMENT", schema = "ORA9", catalog = "ORCL")
@IdClass(InvestID.class)
public class Invest {

    @Id
    private int idDis;
    @Id
    private int idPj;

    @Column(name = "INVQUANTITEJH")
    private int invQuantJH;

    @ToString.Exclude
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "DISID",referencedColumnName = "IDDIS")
    private Discipline invDis;

    @ToString.Exclude
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "PJID",referencedColumnName = "IDPJ")
    private Projet invPj;

}
