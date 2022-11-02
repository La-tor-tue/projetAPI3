package be.condorcet.projet3_2.entities;


import jdk.jfr.Enabled;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIPROJET", schema = "ORA9", catalog = "ORCL")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projet_generator")
    @SequenceGenerator(name = "projet_generator", sequenceName = "APIPROJET_SEQ1", allocationSize = 1)
    @Column(name = "IDPJ")
    private Integer idProjet;
    @NonNull
    @Column(name = "PJTITRE")
    private String pjTitre;
    @Column(name = "PJDATEDEBUT")
    private LocalDate pjDateDebut;
    @Column(name = "PJDATEFIN")
    private LocalDate pjDateFin;
    @Column(name = "PJCOUT")
    private BigDecimal pjCout;


    @ToString.Exclude
    @OneToMany(mappedBy = "taPj")
    private List<Travail> listTravail;

    @ToString.Exclude
    @OneToMany(mappedBy = "invPj")
    private List<Invest> listInvest;

}
