package br.ifpb.monteiro.scream.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.entities.Produto;
import br.ifpb.monteiro.scream.services.ItemProductBacklogService;
import br.ifpb.monteiro.scream.services.ProdutoService;

/**
 *
 * @author Mauricio
 */
@Named(value = "itemProductBacklogController")
@RequestScoped
public class ItemProductBacklogController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ItemProductBacklogService itemProductBacklogService;

	@Inject
	private ProdutoService produtoService;

	private List<Produto> listProduto;

	@ManagedProperty(value="#{param.id}")
	private Long idProduto;

	private Produto produtoSelecionado;

	private ItemProductBacklog itemProductBacklog;

	private ItemProductBacklog selectItemProductBacklog;

	private List<ItemProductBacklog> listItensProductBacklog;
	
	private List<ItemProductBacklog> listItensProduto;

	@PostConstruct
	public void Init(){
		produtoSelecionado = produtoService.find(buscaIdURL());
		itemProductBacklog = new ItemProductBacklog();
		selectItemProductBacklog= new ItemProductBacklog();
		setListItensProduto(buscaItens());
		//setListProduto(produtoService.findAll());
		setListItensProductBacklog(findAll());
	}

	public void create(){
		gerarData();
		itemProductBacklog.setProduto(produtoSelecionado);
		itemProductBacklogService.create(itemProductBacklog);
	}


	public void remove() {
		this.itemProductBacklogService.remove(selectItemProductBacklog);
	}

	public void update(){
		this.itemProductBacklogService.update(selectItemProductBacklog);
	}

	public void buscaProduto(){
		find(idProduto);

	}

	private void gerarData() {
		Calendar calendar = GregorianCalendar.getInstance();
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.format(calendar.getTime());
		calendar = dateFormat.getCalendar();
		itemProductBacklog.setDataInicio(calendar.getTime());

	}


	private Long buscaIdURL(){

		FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String idAux = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		Long convertID = Long.parseLong(idAux);

		return convertID;
	}

	private List<ItemProductBacklog> buscaItens(){

		return itemProductBacklogService.findItensProduto(buscaIdURL());
	}



	//Pesquisas no Banco

	public int count() {
		return itemProductBacklogService.count();
	}

	public ItemProductBacklog find(Long id) {
		return (ItemProductBacklog) itemProductBacklogService.find(id);
	}


	public List<ItemProductBacklog> findRange(int[] range) {
		return itemProductBacklogService.findRange(range);
	}

	public List<ItemProductBacklog> findAll(){
		List<ItemProductBacklog> itemProductBacklogs = itemProductBacklogService.findAll();
		return itemProductBacklogs;
	}

	public ItemProductBacklog getSelectItemProductBacklog() {
		return selectItemProductBacklog;
	}

	public void setSelectItemProductBacklog(ItemProductBacklog selectItemProductBacklog) {
		this.selectItemProductBacklog = selectItemProductBacklog;
	}

	public List<ItemProductBacklog> getListItensProductBacklog() {
		return listItensProductBacklog;
	}

	public void setListItensProductBacklog(List<ItemProductBacklog> listItensProductBacklog) {
		this.listItensProductBacklog = listItensProductBacklog;
	}

	public ItemProductBacklog getItemProductBacklog() {
		return itemProductBacklog;
	}

	public void setItemProductBacklog(ItemProductBacklog itemProductBacklog) {
		this.itemProductBacklog = itemProductBacklog;
	}

	public Produto getProduto() {
		return produtoSelecionado;
	}

	public void setProduto(Produto produto) {
		this.produtoSelecionado = produto;
	}

	public List<Produto> getListProduto() {
		return listProduto;
	}

	public void setListProduto(List<Produto> listProduto) {
		this.listProduto = listProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public List<ItemProductBacklog> getListItensProduto() {
		return listItensProduto;
	}

	public void setListItensProduto(List<ItemProductBacklog> listItensProduto) {
		this.listItensProduto = listItensProduto;
	}

}
