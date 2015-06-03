/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.scream.controllers;

import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.entities.Produto;
import br.ifpb.monteiro.scream.services.ItemProductBacklogService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Mauricio
 */
@Named(value = "itemProductBacklogController")
@RequestScoped
public class ItemProductBacklogController {
    
    @Inject
    private ItemProductBacklogService itemProductBacklogService;
    
    private Produto produto;
    
    private ItemProductBacklog itemProductBacklog;
    
    private ItemProductBacklog selectItemProductBacklog;
    
    private List<ItemProductBacklog> listItensProductBacklog;
    
    @PostConstruct
    public void Init(){
    	produto = new Produto();
        itemProductBacklog = new ItemProductBacklog();
        selectItemProductBacklog= new ItemProductBacklog();
        setListItensProductBacklog(findAll());
    }
    
    public void create(){
    	gerarData();
        itemProductBacklogService.create(itemProductBacklog);
    }
    
    private void gerarData() {
    	Calendar calendar = GregorianCalendar.getInstance();
    	SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
    	dateFormat.format(calendar.getTime());
    	calendar = dateFormat.getCalendar();
    	itemProductBacklog.setDataInicio(calendar.getTime());
		
	}

	public void remove() {
        this.itemProductBacklogService.remove(selectItemProductBacklog);
    }
    
    public void update(){
        this.itemProductBacklogService.update(selectItemProductBacklog);
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
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
