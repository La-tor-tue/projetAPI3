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
    @SequenceGenerator(name = "projet_generator", sequenceName = "API_PROJET_SEQ", allocationSize = 1)
    @Column(name = "IDPJ")
    private Integer idProjet;
    @NonNull
    private String pjTitre;
    private LocalDate pjDtDebut;
    private LocalDate pjDtFin;
    private BigDecimal pjCout;


    @ToString.Exclude
    @OneToMany(mappedBy = "taPj")
    private List<Travail> listTravail;

    @ToString.Exclude
    @OneToMany(mappedBy = "invPj")
    private List<Invest> listInvest;

}
