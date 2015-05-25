/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.scream.controllers;

import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.services.ItemProductBacklogService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author Mauricio
 */
public class ItemProductBacklogController {
    
    @Inject
    private ItemProductBacklogService itemProductBacklogService;
    
    private ItemProductBacklog itemProductBacklog;
    
    @PostConstruct
    public void Init(){
        itemProductBacklog = new ItemProductBacklog();
    }
    
    public void create(){
        itemProductBacklogService.create(itemProductBacklog);
    }
    
    public void remove(ItemProductBacklog entity) {
        this.itemProductBacklogService.remove(entity);
    }
    
    public void update(ItemProductBacklog entity){
        this.itemProductBacklogService.update(entity);
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
}
