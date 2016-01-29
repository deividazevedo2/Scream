package br.ifpb.monteiro.scream.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


import br.edu.ifpb.scream.projects.Product;
import br.edu.ifpb.scream.projects.ProductController;

/**
 * 
 * @author Maurício
 * This class is responsible to converter a product to a SelectItems type
 * to work with selectOneMenu function
 *
 */
@FacesConverter(forClass=Product.class)
public class ProductConverter implements Converter {

	/**
	 * method recover a object from a object string key on context 
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("Passou aqui getAsObject");
	       if (value == null) {
	            return null;
	        }

	        ProductController produtoController = context.getApplication().evaluateExpressionGet(context, "#{productController}", ProductController.class);

	        for (Product product : produtoController.getListProduto()) {
	            if (product.getId().toString().equals(value)) {
	                return product;
	            }
	        }

	        throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to Country", value)));
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
