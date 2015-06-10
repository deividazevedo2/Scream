package br.ifpb.monteiro.scream.controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.toolbar.Toolbar;
import org.primefaces.component.toolbar.ToolbarGroup;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import com.sun.faces.application.ActionListenerImpl;

import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.entities.Produto;
import br.ifpb.monteiro.scream.services.ProdutoService;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;

/**
 *
 * @author Markus
 */
@Named(value = "produtoController")
@RequestScoped
public class ProdutoController{
    
    @Inject
    private ProdutoService service;
        
    private Produto produto= new Produto();
    
    private Produto produtoSelect;
    
    private List<Produto> listProduto;
    
    private ItemProductBacklog itemProductBacklog;
        
    /**
     * Creates a new instance of ProdutoController
     */
    public ProdutoController() {
    }
    
    public void create(){
    	registrarData();
        service.create(produto); 
        produto= new Produto();
        JsfUtil.addSuccessMessage("Produto Criado com Sucesso");
    }
    
    private void registrarData() {
    	Calendar calendar = GregorianCalendar.getInstance();
    	SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
    	dateFormat.format(calendar.getTime());
    	calendar = dateFormat.getCalendar();
    	produto.setDataInicio(calendar.getTime());	
    }

    public void delete(){
    	service.remove(produtoSelect);
    	JsfUtil.addSuccessMessage("Produto Apagado com Sucesso");
    }
    
    public void editar(){
    	service.update(produtoSelect);
    	JsfUtil.addSuccessMessage("Produto Editado com Sucesso");
		
    }
    
    public void verItens(){
    	try { 		
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("/Scream/itensProduto/index.xhtml");
        } catch (IOException ex) {
            JsfUtil.addErrorMessage(ex, "Pagina não encontrada");
            Logger.getLogger(ContaController.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    
    @PostConstruct
    public void init(){
    	itemProductBacklog =new ItemProductBacklog();
    	produtoSelect= new Produto();
        listProduto= service.findAll();                
        
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
	
	public List<Produto> getListProduto() {
		return listProduto;
	}

	public void setListProduto(List<Produto> listProduto) {
		this.listProduto = listProduto;
	}

	public Produto getProdutoSelect() {
		return produtoSelect;
	}

	public void setProdutoSelect(Produto produtoSelect) {
		this.produtoSelect = produtoSelect;
	}
	
	public void produtoSelecionado(Produto produto){
		this.produtoSelect= produto;
	}

	public ItemProductBacklog getItemProductBacklog() {
		return itemProductBacklog;
	}

	public void setItemProductBacklog(ItemProductBacklog itemProductBacklog) {
		this.itemProductBacklog = itemProductBacklog;
	}
	
     
}
