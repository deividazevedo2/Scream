/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.ads.projeto2.scream.dao;

import br.ifpb.monteiro.ads.projeto2.scream.dao.facade.ContaDAOIF;
import br.ifpb.monteiro.ads.projeto2.scream.entities.Conta;

/**
 *
 * @author Mauricio
 */
public class ContaDAO extends GenericDAO<Conta> implements ContaDAOIF{
    
    public ContaDAO(){
        super(Conta.class);
    }
    
}
