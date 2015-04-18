/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.ads.projeto2.scream.dao;

import br.ifpb.monteiro.ads.projeto2.scream.dao.facade.UsuarioProjetoDAOIF;
import br.ifpb.monteiro.ads.projeto2.scream.entities.UsuarioProjeto;

/**
 *
 * @author Mauricio
 */
public class UsuarioProjetoDAO extends GenericDAO<UsuarioProjeto> implements UsuarioProjetoDAOIF{
    
    public UsuarioProjetoDAO(){
        super(UsuarioProjeto.class);
    }
    
}
