package net.javaonline.web.jsf.inventory.itemmaster.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = CellEditorBean.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class CellEditorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String BEAN_NAME = "cellEditorBean";
    public String getBeanName() { return BEAN_NAME; }
    
    public CellEditorBean() {
        cars = new ArrayList<Car>(DataTableData.getDefaultData());
    }
    
    private List<Car> cars;

    public List<Car> getCars() { return cars; }
    public void setCars(List<Car> cars) { this.cars = cars; }
}
