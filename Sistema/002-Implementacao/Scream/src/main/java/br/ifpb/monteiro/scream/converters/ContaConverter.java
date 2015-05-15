package br.ifpb.monteiro.scream.converters;

import br.ifpb.monteiro.scream.entities.Conta;
import br.ifpb.monteiro.scream.services.facade.ContaServiceIF;
import br.ifpb.monteiro.scream.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Conta.class)
public class ContaConverter implements Converter {

    private final ContaServiceIF contaServiceIF;

    public ContaConverter() {
        this.contaServiceIF = CDIServiceLocator.getBean(ContaServiceIF.class);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

        Conta objectToReturn = null;

        if (value != null) {
            objectToReturn = (Conta) this.contaServiceIF.find(new Long(value));
        }
        return objectToReturn;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object != null) {
            Long code = ((Conta) object).getId();
            String objectToReturn = (code == null ? null : code.toString());
            return objectToReturn;
        }
        return "";
    }

}
