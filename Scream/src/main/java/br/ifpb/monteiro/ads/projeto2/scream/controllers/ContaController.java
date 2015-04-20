/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.ads.projeto2.scream.controllers;

import br.ifpb.monteiro.ads.projeto2.scream.beans.facade.ContaBeanIF;
import br.ifpb.monteiro.ads.projeto2.scream.beans.facade.GenericBeanIF;
import br.ifpb.monteiro.ads.projeto2.scream.controllers.facade.ContaControllerIF;
import br.ifpb.monteiro.ads.projeto2.scream.entities.Conta;
import br.ifpb.monteiro.ads.projeto2.scream.qualifiers.ContaQlf;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Mauricio
 */
@ManagedBean(name="contaController")
@RequestScoped
public class ContaController extends GenericController<Conta> implements ContaControllerIF{
    
    @Inject
    private ContaBeanIF contaBean;
    
    @Inject
    @ContaQlf
    private Conta selected;

    @Override
    public Conta getSelected() {
        return selected;
    }

    @Override
    public void setSelected(Conta selected) {
        this.selected = selected;
    }

    @Override
    protected GenericBeanIF getBeans() {
        return contaBean;
    }

    @FacesConverter(forClass = Conta.class)
    public static class ContaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContaController controller = (ContaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contaController");
            return controller.getItem(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Conta) {
                Conta o = (Conta) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Conta.class.getName()});
                return null;
            }
        }

    }

}
