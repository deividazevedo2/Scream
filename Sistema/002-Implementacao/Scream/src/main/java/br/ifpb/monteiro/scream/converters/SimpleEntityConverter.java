package br.ifpb.monteiro.scream.converters;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ifpb.monteiro.scream.entities.Identifiable;

/**
 * 
 * @author RPonte
 * www.rponte.com.br
 *
 */
@FacesConverter(value= "simpleEntityConverter")
public class SimpleEntityConverter implements Converter {  
	  
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {  
        if (value != null) {  
            return this.getAttributesFrom(component).get(value);  
        }  
        return null;
    }  
  
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {  
  
        if (value != null  
                && !"".equals(value)) {  
  
            Identifiable entity = (Identifiable) value;  
  
            // adiciona item como atributo do componente  
            this.addAttribute(component, entity);  
  
            Long codigo = entity.getId();  
            if (codigo != null) {  
                return String.valueOf(codigo);  
            }  
        }  
  
        return "";  
    }  
  
    protected void addAttribute(UIComponent component, Identifiable o) {  
        String key = o.getId().toString(); // codigo da empresa como chave neste caso  
        this.getAttributesFrom(component).put(key, o);  
    }  
  
    protected Map<String, Object> getAttributesFrom(UIComponent component) {  
        return component.getAttributes();  
    }  
  
}  
