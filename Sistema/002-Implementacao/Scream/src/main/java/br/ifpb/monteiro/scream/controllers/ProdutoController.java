package br.ifpb.monteiro.scream.controllers;

import br.ifpb.monteiro.scream.entities.Produto;
import br.ifpb.monteiro.scream.services.ProdutoService;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 *
 * @author Markus
 */
@Named(value = "produtoController")
@RequestScoped
public class ProdutoController {
    
    @Inject
    private ProdutoService service;
    
    private Produto produto= new Produto();
    
    private DashboardModel model;
    
    /**
     * Creates a new instance of ProdutoController
     */
    public ProdutoController() {
    }
    
    public void create(){
        service.create(produto);
        JsfUtil.addSuccessMessage("Produto Criado com Sucesso");
    }
    
    @PostConstruct
    public void init(){
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();
        
        column1.addWidget("fazer");
        column2.addWidget("fazendo");
        column3.addWidget("feito");
        
        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);
        
        
    }
    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
        
        addMessage(message);
    }
    
    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
        
        addMessage(message);
    }
    
    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
        
        addMessage(message);
    }
    
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public DashboardModel getModel() {
        return model;
    }

    public ProdutoService getService() {
        return service;
    }

    public void setService(ProdutoService service) {
        this.service = service;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
}
