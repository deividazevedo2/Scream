package br.ifpb.monteiro.scream.services;

import java.util.List;

import javax.inject.Inject;

import br.ifpb.monteiro.scream.dao.ContaDAO;
import br.ifpb.monteiro.scream.entities.Conta;
import br.ifpb.monteiro.scream.util.jpa.Transactional;

/**
 *
 * @author Mauricio
 */
public class ContaService {

    @Inject
    private ContaDAO contaDao;
    
    private SecurityService securityService = new SecurityService();

    public ContaService() {
//        this.contaDao = new ContaDAO();
    }
   

    public int count() {
        return contaDao.count();
    }

    @Transactional
    public void create(Conta entity) {
        try {
            this.contaDao.create(entity);
        } catch (Exception e) {
            System.err.println("Erro no Service: " + e.getMessage());
        }
    }

    public Conta find(Long id) {
        return (Conta) contaDao.findById(id);
    }

    public List<Conta> findAll() {
        return contaDao.findAll();
    }

    public List<Conta> findRange(int[] range) {
        return contaDao.findRange(range);
    }

    @Transactional
    public void remove(Conta entity) {
        this.contaDao.delete(entity);
    }

    public ContaDAO getContaDao() {
        return contaDao;
    }

    public void setContaDao(ContaDAO contaDao) {
        this.contaDao = contaDao;
    }
    
    public SecurityService getSecurityService() {
		return securityService;
	}

}
