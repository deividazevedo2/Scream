package br.ifpb.monteiro.scream.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;

import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.entities.Produto;
import br.ifpb.monteiro.scream.services.ProdutoService;
import br.ifpb.monteiro.scream.services.SecurityService;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;

/**
 *
 * @author Markus
 */
@Named(value = "produtoController")
@RequestScoped
public class ProdutoController implements Serializable{
    
	private static final long serialVersionUID = 1L;

	@Inject
    private ProdutoService service;
	
	@Inject
	private SecurityService ss;
        
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
        this.produto= new Produto();
        JsfUtil.addSuccessMessage("Produto Criado com Sucesso");
    }
    
    private void registrarData() {
    	Calendar calendar = GregorianCalendar.getInstance();
    	SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
    	dateFormat.format(calendar.getTime());
    	calendar = dateFormat.getCalendar();
    	produto.setDataInicio(calendar.getTime());	
    }

    
    public void delete(Produto produtoSelect){
    	if (ss.isAuthorized("scientist")) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
    	service.remove(produtoSelect);
    	JsfUtil.addSuccessMessage("Produto Apagado com Sucesso");
    }
    
    public void editar(){
    	if(produtoSelect.getId()==null){
    		JsfUtil.addErrorMessage("Erro ao selecionar seu produto, por favor tente mais tarde");
    	}else {
    		service.update(produtoSelect);
    		JsfUtil.addSuccessMessage("Produto atualizado com sucesso");
		}
		
    }
    
    public void verItens(Produto produto){
    	try {
    		System.out.println("Produto: "+produto);
    		FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
    		
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
    	//produtoSelect= new Produto();
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

	public ItemProductBacklog getItemProductBacklog() {
		return itemProductBacklog;
	}

	public void setItemProductBacklog(ItemProductBacklog itemProductBacklog) {
		this.itemProductBacklog = itemProductBacklog;
	}
	
     
}