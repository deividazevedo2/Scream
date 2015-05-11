package br.ifpb.monteiro.ads.projeto2.scream.dao;

import br.ifpb.monteiro.ads.projeto2.scream.entities.Conta;
import java.io.Serializable;

/**
 *
 * @author Mauricio
 */
public class ContaDAO extends GenericDAO<Conta> implements Serializable {

    public ContaDAO() {
        super(Conta.class);
    }

}
