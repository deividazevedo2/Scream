package br.ifpb.monteiro.ads.projeto2.scream.controllers;

import br.ifpb.monteiro.ads.projeto2.scream.entities.Conta;
import br.ifpb.monteiro.ads.projeto2.scream.exceptions.ScreamException;
import br.ifpb.monteiro.ads.projeto2.scream.services.ContaService;
import br.ifpb.monteiro.ads.projeto2.scream.util.jsf.JsfUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mauricio
 */
@ManagedBean
public class ContaController {

    private ContaService contaService;

    private Conta selected;

    public ContaController() {
        this.contaService = new ContaService();
        this.selected = new Conta();
    }

    public Conta getSelected() {
        return selected;
    }

    public void setSelected(Conta selected) {
        this.selected = selected;
    }

    public void create() {
        contaService.create(selected);
        JsfUtil.addSuccessMessage("Conta adicionada com sucesso!");
        voltaTelaLogin();
    }

    public ContaService getContaService() {
        return contaService;
    }

    public void setContaService(ContaService contaService) {
        this.contaService = contaService;
    }

    public void voltaTelaLogin() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Scream/login.xhtml");
        } catch (IOException ex) {
            JsfUtil.addErrorMessage(ex, "Pagina não encontrada");
            Logger.getLogger(ContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
