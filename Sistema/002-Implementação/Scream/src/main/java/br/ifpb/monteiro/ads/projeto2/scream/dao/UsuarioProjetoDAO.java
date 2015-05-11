package br.ifpb.monteiro.ads.projeto2.scream.dao;

import br.ifpb.monteiro.ads.projeto2.scream.entities.UsuarioProjeto;
import java.io.Serializable;

/**
 *
 * @author Mauricio
 */
public class UsuarioProjetoDAO extends GenericDAO<UsuarioProjeto> implements Serializable {

    public UsuarioProjetoDAO() {
        super(UsuarioProjeto.class);
    }

}
