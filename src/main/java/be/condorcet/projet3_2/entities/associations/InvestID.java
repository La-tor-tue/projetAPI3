package be.condorcet.projet3_2.entities.associations;

import java.io.Serializable;

public class InvestID implements Serializable {

    private int idDis;

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
        return (idDis + idPj);
    }
}
