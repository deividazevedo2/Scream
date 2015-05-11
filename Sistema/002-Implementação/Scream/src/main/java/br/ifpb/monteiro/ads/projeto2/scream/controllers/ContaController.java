package br.ifpb.monteiro.ads.projeto2.scream.controllers;

import br.ifpb.monteiro.ads.projeto2.scream.entities.Conta;
import br.ifpb.monteiro.ads.projeto2.scream.services.ContaService;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

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
    }

    public ContaService getContaService() {
        return contaService;
    }

    public void setContaService(ContaService contaService) {
        this.contaService = contaService;
    }

}
