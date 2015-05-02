/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.ads.projeto2.scream.dao;

import br.ifpb.monteiro.ads.projeto2.scream.dao.facade.ContaDaoIF;
import br.ifpb.monteiro.ads.projeto2.scream.entities.Conta;
import javax.ejb.Stateless;

/**
 *
 * @author Mauricio
 */
@Stateless
public class ContaDAO extends GenericDAO<Conta> implements ContaDaoIF{
  
    public ContaDAO(){
        super(Conta.class);
    }
    
}
