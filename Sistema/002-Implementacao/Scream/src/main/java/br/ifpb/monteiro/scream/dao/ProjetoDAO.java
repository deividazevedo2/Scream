/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.scream.dao;

import br.ifpb.monteiro.scream.entities.Projeto;

/**
 *
 * @author Mauricio
 */
public class ProjetoDAO extends GenericDAO<Projeto>{
    
    private static final long serialVersionUID = 1L;
    
    public ProjetoDAO() {
        super(Projeto.class);
    }
    
}
