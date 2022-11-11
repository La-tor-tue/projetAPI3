package be.condorcet.projet3_2.entities.associations;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class InvestID implements Serializable {

    private int idDis;

    private int idPj;

}
