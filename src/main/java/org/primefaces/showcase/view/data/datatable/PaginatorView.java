package org.primefaces.showcase.view.data.datatable;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.showcase.service.Car;
import org.primefaces.showcase.service.CarService;
 
@ManagedBean(name="dtPaginatorView")
@ViewScoped
public class PaginatorView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Car> cars;
     
    @ManagedProperty("#{carService}")
    private CarService service;
 
    @PostConstruct
    public void init() {
        cars = service.createCars(50);
    }
     
    public List<Car> getCars() {
        return cars;
    }
 
    public void setService(CarService service) {
        this.service = service;
    }
    
    public void onRowEdit(RowEditEvent event) throws InterruptedException {
    	Car car = ((Car) event.getObject());
    	System.out.println("Car Edited");
    	 System.out.println("id"+car.getId());
         System.out.println("year"+car.getYear());
         System.out.println("brand"+car.getBrand());
         System.out.println("color"+car.getColor());
         Thread.sleep(10000);
        FacesMessage msg = new FacesMessage("Car Edited", car.getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) throws InterruptedException {
    	Car car = ((Car) event.getObject());
    	System.out.println("Edit Cancelled");
    	 System.out.println("id"+car.getId());
         System.out.println("year"+car.getYear());
         System.out.println("brand"+car.getBrand());
         System.out.println("color"+car.getColor());
         Thread.sleep(10000);
        FacesMessage msg = new FacesMessage("Edit Cancelled", car.getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}