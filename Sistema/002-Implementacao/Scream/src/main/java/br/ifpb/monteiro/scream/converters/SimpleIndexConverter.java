package br.ifpb.monteiro.scream.converters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;


/**
* Class is responsible to Convert a Object on SelectedItem
* type to in order to be used in the manipulation of one or
* multiple items in the selectOneMenu JSF, using index for handling the values.
*/
@FacesConverter(value= "simpleIndexConverter")
public class SimpleIndexConverter implements Converter {  
	  
    private int index = -1;  
  
    /** 
     * Method get a key on a String value, recovery the Object correspondent
	 * from UIComponent and return a Object on SelectItem type
	 *
	 *@return selectedItem
     */  
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {  
  
        SelectItem selectedItem = this.getSelectedItemByIndex(component, Integer.parseInt(value));  
        if (selectedItem != null) { 
        	System.out.println(selectedItem.getValue());
            return selectedItem.getValue();  }
  
        return null;  
    }  
  
    /** 
     * Method get a key on a Object value and return a String correspondent of this object.
     */  
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {  
        index++;  
        return String.valueOf(index);
    }  
  
    /** 
     * Method get SelectItem according to the option selected by the user
     */  
    protected SelectItem getSelectedItemByIndex(UIComponent component, int index) {  
  
        List<SelectItem> items = this.getSelectItems(component);  
        int size = items.size();  
  
        if (index > -1  
                && size > index) {  
            return items.get(index);  
        }  
  
        return null;  
    }  
  
	/** 
     * Method return a list of SelectItem from a list of Object contained on UIComponent
     */
    protected List<SelectItem> getSelectItems(UIComponent component) {  
  
        List<SelectItem> items = new ArrayList<SelectItem>();  
  
        int childCount = component.getChildCount();  
        if (childCount == 0)  
          return items;  
  
        List<UIComponent> children = component.getChildren();  
        for (UIComponent child : children) {  
            if (child instanceof UISelectItem) {  
                this.addSelectItem((UISelectItem) child, items);  
            } else if (child instanceof UISelectItems) {  
                this.addSelectItems((UISelectItems) child, items);  
            }  
        }  
  
        return items;  
    }  
  
	/** 
     * Method adds a selectItem to a List<SelectItem> from getSelectItems method 
     */
    protected void addSelectItem(UISelectItem uiItem, List<SelectItem> items) {  
  
        boolean isRendered = uiItem.isRendered();  
        if (!isRendered) {  
            items.add(null);  
            return;  
        }  
  
        Object value = uiItem.getValue();  
        SelectItem item;  
  
        if (value instanceof SelectItem) {  
            item = (SelectItem) value;  
        } else {  
            Object itemValue = uiItem.getItemValue();  
            String itemLabel = uiItem.getItemLabel();  
            // JSF throws a null pointer exception for null values and labels,  
            // which is a serious problem at design-time.  
            item = new SelectItem(itemValue == null ? "" : itemValue,  
                    itemLabel == null ? "" : itemLabel, uiItem  
                            .getItemDescription(), uiItem.isItemDisabled());  
        }  
  
        items.add(item);  
    }  
  
  	/** 
     * Method adds a selectItem to a List<SelectItem> from getSelectItems method 
     */
    @SuppressWarnings("unchecked")  
    protected void addSelectItems(UISelectItems uiItems, List<SelectItem> items) {  
  
        boolean isRendered = uiItems.isRendered();  
        if (!isRendered) {  
            items.add(null);  
            return;  
        }  
  
        Object value = uiItems.getValue();  
        if (value instanceof SelectItem) {  
            items.add((SelectItem) value);  
        } else if (value instanceof Object[]) {  
            Object[] array = (Object[]) value;  
            for (int i = 0; i < array.length; i++) {  
                // TODO test - this section is untested  
                if (array[i] instanceof SelectItemGroup) {  
                    resolveAndAddItems((SelectItemGroup) array[i], items);  
                } else {  
                    items.add((SelectItem) array[i]);  
                }  
            }  
        } else if (value instanceof Collection) {  
            Iterator<SelectItem> iter = ((Collection<SelectItem>) value)  
                    .iterator();  
            SelectItem item;  
            while (iter.hasNext()) {  
                item = iter.next();  
                if (item instanceof SelectItemGroup) {  
                    resolveAndAddItems((SelectItemGroup) item, items);  
                } else {  
                    items.add(item);  
                }  
            }  
        } else if (value instanceof Map) {  
            for (Map.Entry<Object, Object> entry : ((Map<Object, Object>) value).entrySet()) {  
                Object label = entry.getKey();  
                SelectItem item = new SelectItem(entry.getValue(),  
                        label == null ? (String) null : label.toString());  
                // TODO test - this section is untested  
                if (item instanceof SelectItemGroup) {  
                    resolveAndAddItems((SelectItemGroup) item, items);  
                } else {  
                    items.add(item);  
                }  
            }  
        }  
    }  
  
	/** 
     * Method add a item to a method before solve 
     */
    protected void resolveAndAddItems(SelectItemGroup group, List<SelectItem> items) {  
        for (SelectItem item : group.getSelectItems()) {  
            if (item instanceof SelectItemGroup) {  
                resolveAndAddItems((SelectItemGroup) item, items);  
            } else {  
                items.add(item);  
            }  
        }  
    }  
  
} 