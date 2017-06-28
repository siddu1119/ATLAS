package net.javaonline.web.jsf.inventory.itemmaster.ui.bean;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
 
import net.javaonline.spring.inventory.itemmaster.bo.ItemMasterBo;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
@Component
@ManagedBean(name="itemMasterBean")
@RequestScoped
public class ItemMasterBean {
private String icode;
private String item_name;
private float mrp;
private int stock;
 
// to inject ItemMasterBOImpl object by Spring
@Autowired
ItemMasterBo itemMasterBo;
 
public void addItem()  {
 
    // Accessing the business object for adding item
    boolean success=itemMasterBo.addItem(icode,item_name, mrp, stock);
 
    FacesMessage msg;
 
    if (success)
             msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "item added to the master successfully", "success");
    else
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "error in adding item", "failure");
 
    FacesContext.getCurrentInstance().addMessage(null, msg);
}
 
public ItemMasterBo getItemMasterBo() {
    return itemMasterBo;
}
 
public void setItemMasterBo(ItemMasterBo itemMasterBo) {
    this.itemMasterBo = itemMasterBo;
}
 
public String getIcode() {
    return icode;
}
public void setIcode(String icode) {
    this.icode = icode;
}
 
public String getItem_name() {
    return item_name;
}
 
public void setItem_name(String item_name) {
    this.item_name = item_name;
}
 
public float getMrp() {
    return mrp;
}
 
public void setMrp(float mrp) {
    this.mrp = mrp;
}
 
public int getStock() {
    return stock;
}
 
public void setStock(int stock) {
    this.stock = stock;
}
 
}