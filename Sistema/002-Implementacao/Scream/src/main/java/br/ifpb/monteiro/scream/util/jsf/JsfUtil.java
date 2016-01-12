package br.ifpb.monteiro.scream.util.jsf;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;


/**
 *	
 *	This class manages the messages that will be displayed
 * 	on view through FacesContext to recover the current context.
 */
public class JsfUtil {

	/**
	*
	*/
    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

	/**
	*	This method returns a boolean indicating whether failed validation
	*/
    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

	/**
	*	This method takes an exception and an error message to be displayed in jsf page
	*	@Param Exception ex, String defaultMsg
	*/
    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

	/**
	*	This method takes a list of error messages to be displayed in jsf page
	*	@Param List<String> messages
	*/
    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

	/**
	*	This method takes an error message to be displayed in jsf page
	*	@Param String msg
	*/
    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

	/**
	*	This method takes a successful message to be displayed in jsf page
	*	@Param String msg
	*/
    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

	/**
	*	This method returns a parameter requested by matching key
	*	@Param String key
	*/
    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

	/**
	*	This method returns a object from a requested parameter
	*	@Param String requestParameterName, Converter converter, UIComponent component
	*/
    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

	/**
	*	This Enum corresponding to a persistence action allowed
	*/
    public static enum PersistAction {

        CREATE,
        DELETE,
        UPDATE
    }
}