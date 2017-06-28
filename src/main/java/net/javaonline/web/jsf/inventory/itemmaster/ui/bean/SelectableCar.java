package net.javaonline.web.jsf.inventory.itemmaster.ui.bean;

import java.io.Serializable;
public class SelectableCar extends Car implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean selected = false;
    
    public SelectableCar() {
        super();
    }
    
	public SelectableCar(int id,
	           String name, String chassis,
	           int weight, int acceleration, 
	           double mpg, double cost) {
	    super(id, name, chassis, weight, acceleration, mpg, cost);
    }
    
    public SelectableCar(Car base) {
        super(base.getId(),
              base.getName(),
              base.getChassis(),
              base.getWeight(),
              base.getAcceleration(),
              base.getMpg(),
              base.getCost());
    }
    
    public boolean isSelected() { return selected; }
    
    public void setSelected(boolean selected) { this.selected = selected; }
}
