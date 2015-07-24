package br.ifpb.monteiro.scream.converters;

import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.services.facade.ItemProductBacklogServiceIF;
import br.ifpb.monteiro.scream.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = ItemProductBacklog.class)
public class ItemProductBacklogConverter implements Converter {

    private final ItemProductBacklogServiceIF itemProdutoBacklogServiceIF;

    public ItemProductBacklogConverter() {
        this.itemProdutoBacklogServiceIF = CDIServiceLocator.getBean(ItemProductBacklogServiceIF.class);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

    	ItemProductBacklog objectToReturn = null;

        if (value != null) {
            objectToReturn = (ItemProductBacklog) this.itemProdutoBacklogServiceIF.find(new Long(value));
        }
        return objectToReturn;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object != null) {
            Long code = ((ItemProductBacklog) object).getId();
            String objectToReturn = (code == null ? null : code.toString());
            return objectToReturn;
        }
        return "";
    }

}
