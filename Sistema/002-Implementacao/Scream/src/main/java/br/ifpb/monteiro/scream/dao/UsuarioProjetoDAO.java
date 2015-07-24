package br.ifpb.monteiro.scream.dao;

import br.ifpb.monteiro.scream.entities.UsuarioProjeto;

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
