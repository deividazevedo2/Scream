package br.ifpb.monteiro.scream.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.scream.projects.Product;
import br.edu.ifpb.scream.projects.ProductController;
import br.edu.ifpb.scream.projects.ProdutoService;
import br.edu.ifpb.scream.projects.dao.ProductDAO;

/**
 * 
 * @author Maurício
 * This class is responsible to converter a product to a SelectItems type
 * to work with selectOneMenu function
 *
 */
//@FacesConverter(forClass=Product.class)
@Named
@ApplicationScoped
public class ProductConverter implements Converter {

	
	@Inject
	private ProdutoService service;
	
	/**
	 * method recover a object from a object string key on context 
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("Passou aqui getAsObject");
		System.out.println(value);
		
	       if (value == null) {
	            return null;
	            
	       }try{
	    	   Product p = service.find(Long.valueOf(value));
	    	   return p;
	    	   
	    	   
	       }catch(NumberFormatException e){
	    	   throw new ConverterException(new FacesMessage(String.format("%s is not a valid Product ID", value)), e);
	       }
	       
	    }
	
	/**
	 * method convert a object in a unique String
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		System.out.println("Passou aqui getAsString");
		return (value instanceof Product) ? ((Product) value).getId().toString() : null;
	}

}
