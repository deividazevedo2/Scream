/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.scream.services;

import br.ifpb.monteiro.scream.dao.DefinicaoDeProntoDAO;
import br.ifpb.monteiro.scream.entities.DefinicaoDePronto;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.entities.enums.DefinicaoDeProntoEnum;
import br.ifpb.monteiro.scream.util.jpa.Transactional;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Mauricio
 */
public class DefinicaoDeProntoService {
    
    @Inject
    private DefinicaoDeProntoDAO definicaoDeProntoDAO;
    
    @Transactional
    public void update(DefinicaoDePronto entity){
        try {
            this.definicaoDeProntoDAO.update(entity);
        } catch (Exception e) {
            System.err.println("Erro no DefinicaoDeProntoService: " + e.getMessage());
        }
    }
    
    public void createDefinicaoProntoRelease(Projeto projeto){
        DefinicaoDePronto entity = new DefinicaoDePronto();
        entity.setDescricao("");
        entity.setTipoDefinicao(DefinicaoDeProntoEnum.RELEASE);
        entity.setProjeto(projeto);
        try {
            this.definicaoDeProntoDAO.create(entity);
        } catch (Exception e) {
            System.err.println("Erro no DefinicaoDeProntoService: " + e.getMessage());
        }
    }
    
    public void createDefinicaoProntoSprint(Projeto projeto){
        DefinicaoDePronto entity = new DefinicaoDePronto();
        entity.setDescricao("");
        entity.setTipoDefinicao(DefinicaoDeProntoEnum.SPRINT);
        entity.setProjeto(projeto);
        try {
            this.definicaoDeProntoDAO.create(entity);
        } catch (Exception e) {
            System.err.println("Erro no DefinicaoDeProntoService: " + e.getMessage());
        }
    }
    
    public void createDefinicaoProntoPB(Projeto projeto){
        DefinicaoDePronto entity = new DefinicaoDePronto();
        entity.setDescricao("");
        entity.setTipoDefinicao(DefinicaoDeProntoEnum.PRODUCTBACKLOG);
        entity.setProjeto(projeto);
        try {
            this.definicaoDeProntoDAO.create(entity);
        } catch (Exception e) {
            System.err.println("Erro no DefinicaoDeProntoService: " + e.getMessage());
        }
    }
    
    public DefinicaoDePronto find(Long id) {
        return (DefinicaoDePronto) definicaoDeProntoDAO.findById(id);
    }
    
    public List<DefinicaoDePronto> findAll() {
        return definicaoDeProntoDAO.findAll();
    }
    
}
