package br.ifpb.monteiro.scream.converters;

import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.services.ProjetoService;
import br.ifpb.monteiro.scream.services.facade.ItemProductBacklogServiceIF;
import br.ifpb.monteiro.scream.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


//@FacesConverter(value = "classeConverter")    
@FacesConverter(forClass = Projeto.class)
public class ProjetoConverter implements Converter {
	
	private final ProjetoService projetoService;

    public ProjetoConverter() {
        this.projetoService = CDIServiceLocator.getBean(ProjetoService.class);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

    	Projeto objectToReturn = null;

        if (value != null) {
            objectToReturn = (Projeto) this.projetoService.find(new Long(value));
        }
        return objectToReturn;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object != null) {
            Long code = ((Projeto) object).getId();
            String objectToReturn = (code == null ? null : code.toString());
            return objectToReturn;
        }
        return "";
    }

}
