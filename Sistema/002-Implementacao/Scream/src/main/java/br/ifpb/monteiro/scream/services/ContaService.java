package br.ifpb.monteiro.scream.services;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.ifpb.monteiro.scream.controllers.ContaController;
import br.ifpb.monteiro.scream.dao.ContaDAO;
import br.ifpb.monteiro.scream.entities.Conta;
import br.ifpb.monteiro.scream.util.jpa.Transactional;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;

/**
 *
 * @author Mauricio
 */
public class ContaService {
    
    @Inject
    private ContaDAO contaDao;
    
    @Inject
    private SecurityService securityService;
    
    public ContaService() {
        //        this.contaDao = new ContaDAO();
    }
    
    
    private boolean validarEmail(Conta entity){
        
        List<Conta> contas = contaDao.query("select conta from Conta conta "
                + "where conta.email = ?1",
                entity.getEmail());
        
        if (contas.size() > 0) {
            return false;
        }
        return true;
    }
    
    private boolean validarUsuario(Conta entity){
        
        List<Conta> contas = contaDao.query("select conta from Conta conta "
                + "where conta.usuario = ?1",
                entity.getUsuario());
        
        if (contas.size() > 0) {
            return false;
        }
        return true;
    }
    
    public int count() {
        return contaDao.count();
    }
    
    @Transactional
    public void create(Conta entity) {
        if(validarEmail(entity) && validarUsuario(entity)){
//			try {
            this.contaDao.create(entity);
            this.securityService.registrar(entity, entity.getSenha());
            voltaTelaLogin();
//			} catch (Exception e) {
//				System.err.println("Erro no ContaService: " + e.getMessage());
//			}
        }else{
            if (!validarEmail(entity)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Falha no Registro:",
                                "Já existe uma conta com esse Email"));
            }if (!validarUsuario(entity)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Falha no Registro:",
                                "Já existe uma conta com esse Username"));
            }
            
        }
        
    }
    public void voltaTelaLogin() {
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("/scream/login.xhtml");
        } catch (IOException ex) {
            JsfUtil.addErrorMessage(ex, "Pagina não encontrada");
            Logger.getLogger(ContaController.class.getName()).log(Level.SEVERE,
                    null, ex);
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
    
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
    
    
    
}
