package br.ifpb.monteiro.ads.projeto2.scream.services;

import br.ifpb.monteiro.ads.projeto2.scream.dao.ContaDAO;
import br.ifpb.monteiro.ads.projeto2.scream.entities.Conta;
import br.ifpb.monteiro.ads.projeto2.scream.util.jpa.Transactional;
import java.util.List;

/**
 *
 * @author Mauricio
 */
public class ContaService {

    private ContaDAO contaDao;

    public ContaService() {
        this.contaDao = new ContaDAO();
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

    public void edit(Conta entity) {
        contaDao.edit(entity);
    }

    public Conta find(Long id) {
        return (Conta) contaDao.find(id);
    }

    public List<Conta> findAll() {
        return contaDao.findAll();
    }

    public List<Conta> findRange(int[] range) {
        return contaDao.findRange(range);
    }

    @Transactional
    public void remove(Conta entity) {
        this.contaDao.remove(entity);
    }

    public ContaDAO getContaDao() {
        return contaDao;
    }

    public void setContaDao(ContaDAO contaDao) {
        this.contaDao = contaDao;
    }

}
