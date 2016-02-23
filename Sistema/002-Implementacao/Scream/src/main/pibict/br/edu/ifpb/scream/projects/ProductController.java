package br.edu.ifpb.scream.projects;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;

//import br.ifpb.monteiro.scream.entities.Projeto;
//import br.ifpb.monteiro.scream.services.ProdutoService;
import br.edu.ifpb.scream.security.SecurityService;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;

/**
 *
 * @author Mauricio
 */
@Named(value = "productController")
@Model
public class ProductController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoService service;

	@Inject
	private SecurityService ss;

	private Product product;

	private Product produtoSelect;

	private List<Product> listProduto;

//	private ItemProductBacklog itemProductBacklog;

	FacesContext contexto = FacesContext.getCurrentInstance();

	/**
	 * Creates a new instance of ProductController
	 */
	public ProductController() {
	}

	@PostConstruct
	public void init() {
		product = new Product();
//		itemProductBacklog = new ItemProductBacklog();
		listProduto = service.findAll();
		produtoSelect = (Product) contexto.getExternalContext().getSessionMap().get("product");
	}

	public void create() {
		//if (ss.isAuthorized("SCRUM_MASTER")) {
		if(validarProduto(product)){
//			registrarData();
			service.create(product);
			this.product = new Product();
			JsfUtil.addSuccessMessage("Product Criado com Sucesso");
			redirect();
			lookingFor();
		}
	}
	
	public void lookingFor(){
		Product p = service.find(1L);
		System.out.println(p.getName()+"HUEHUEHUEHUE");
	}


	public void delete(Product produtoSelect) {
		//        if (ss.isAuthorized("SCRUM_MASTER")) {
		//            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		JsfUtil.addSuccessMessage("Product Apagado com Sucesso");
		service.remove(produtoSelect);
		redirect();
		
	}

	public void editar() {


		if (produtoSelect.getId() == null) {
			JsfUtil.addErrorMessage("Erro ao selecionar seu product, por favor tente mais tarde");

		} else {
			if (validarProduto(produtoSelect)){
				service.update(produtoSelect);
				JsfUtil.addSuccessMessage("Product atualizado com sucesso");
				//redirect();

			}
		}

	}

	public Boolean validarProduto(Product produto){
		if(produto.getName()==null || produto.getName().equals(""))
			return false;

		return true;
	}
	
	public void submit() {
	    System.out.println("Selected item: " );
	}

//	public void verItens(Product product) {
//		try {
//			System.out.println("Product: " + product);
//			FacesContext.getCurrentInstance().getExternalContext().getRequest();
//			FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
//
//			FacesContext.getCurrentInstance().getExternalContext()
//			.redirect("/Scream/itensProduto/index.xhtml");
//		} catch (IOException ex) {
//			JsfUtil.addErrorMessage(ex, "Pagina não encontrada");
//			Logger.getLogger(ContaController.class.getName()).log(Level.SEVERE,
//					null, ex);
//		}
//	}


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

	public void redirect(){
		try {//Redirect para atualização das informações
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/Scream/app.xhtml");
		} catch (IOException e) {
			JsfUtil.addErrorMessage("Aconteceu algo inesperado ao apagar este product");
		}
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

	public Product getProduct() {
		return product;
	}

	public List<Product> getListProduto() {
		return listProduto;
	}

	public void setListProduto(List<Product> listProduto) {
		this.listProduto = listProduto;
	}

	public Product getProdutoSelect() {
		return produtoSelect;
	}

	public void setProdutoSelect(Product produtoSelect) {
		this.produtoSelect = produtoSelect;
	}
	
	

//	public ItemProductBacklog getItemProductBacklog() {
//		return itemProductBacklog;
//	}
//
//	public void setItemProductBacklog(ItemProductBacklog itemProductBacklog) {
//		this.itemProductBacklog = itemProductBacklog;
//	}


	public void setProduct(Product product) {
		this.product = product;
	}

	public void manterProduto() {
		contexto.getExternalContext().getSessionMap().put("product", produtoSelect);
	}

}