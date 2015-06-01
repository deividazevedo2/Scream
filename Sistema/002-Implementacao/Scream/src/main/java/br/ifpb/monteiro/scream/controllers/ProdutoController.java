package br.ifpb.monteiro.scream.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
    
    private final int numeroColunas=1;
    
    private Produto produto= new Produto();
    
    private List<Produto> listProduto;
    
    private Produto produtoSelect;
    
    private DashboardModel model;
    
    private Dashboard dashboard;
    
    private CommandButton buttonEditar;
    
    private CommandButton buttonDelete;
        
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
    	SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
    	Calendar calendar = GregorianCalendar.getInstance();
    	dateFormat.format(calendar.getTime());
    	
    	
    	produto.setDataInicio(calendar.getTime());	
    }

/* public void update(){
    	service.create(produto);
    }*/
    
    public void delete(){
    	service.remove(produtoSelect);
    	JsfUtil.addSuccessMessage("Produto Apagado com Sucesso");
    }
    
    public void editar(){
    	service.update(produtoSelect);
    	JsfUtil.addSuccessMessage("Produto Editado com Sucesso");
		
    }
    
    @PostConstruct
    public void init(){
    	produtoSelect= new Produto();
        listProduto= service.findAll();        
        /*FacesContext fc = FacesContext.getCurrentInstance();
		Application application = fc.getApplication();
		ActionListener listener = new ActionScream();
		dashboard = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
		dashboard.setId("dashboard");

		model = new DefaultDashboardModel();
		for( int i = 0, n = getNumeroColunas(); i < n; i++ ) {
			DashboardColumn column = new DefaultDashboardColumn();
			model.addColumn(column);
		}
		dashboard.setModel(model);

				
		for( int i = 0, n = getListProduto().size(); i < n; i++ ) {
			Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
			panel.setId("measure_" + i);
			panel.setHeader(getListProduto().get(i).getNome());
			
			getDashboard().getChildren().add(panel);
			DashboardColumn column = model.getColumn(i%getNumeroColunas());
			column.addWidget(panel.getId());
			HtmlOutputText text = new HtmlOutputText();
			text.setValue(getListProduto().get(i).getDescricao()+"\n");

			HtmlOutputText textLinha = new HtmlOutputText();
			textLinha.setValue("                    ");
			
			Toolbar toolbar = new Toolbar();
			toolbar.setId("toolbarProduto"+i);
			
			buttonEditar= new CommandButton();
			buttonDelete= new CommandButton();
			
			buttonEditar.setValue("Editar");
			//buttonEditar.addActionListener(listener);
			buttonDelete.setValue("Apagar");
			//buttonDelete.addActionListener(listener);

			
			
			
			ToolbarGroup toolbarGroupDelete= new ToolbarGroup();
			ToolbarGroup toolbarGroupEdite= new ToolbarGroup();

			toolbarGroupEdite.getChildren().add(buttonEditar);
			toolbarGroupDelete.getChildren().add(buttonDelete);
			toolbarGroupDelete.setAlign("right");
			
			toolbar.setStyle("float: none; position: static ; text-align: center");
			toolbar.getChildren().add(toolbarGroupEdite);
			toolbar.getChildren().add(toolbarGroupDelete);
		
			
			panel.getChildren().add(text);
			panel.getChildren().add(toolbar);*/

		//}
        
        
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

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public void setModel(DashboardModel model) {
		this.model = model;
	}

	public int getNumeroColunas() {
		return numeroColunas;
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
	
     
}
