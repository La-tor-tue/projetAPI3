package be.condorcet.projet3_2.entities.associations;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InvestID implements Serializable {

    @Column(name = "IDDIS")
    private int idDis;

    @Column(name = "IDPJ")
    private int idPj;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvestID investID = (InvestID) o;
        return idDis == investID.idDis && idPj == investID.idPj;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDis, idPj);
    }
}
