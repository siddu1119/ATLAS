package net.javaonline.spring.inventory.itemmaster.bo;
 
public interface ItemMasterBo {
 
    public boolean addItem(String item_code,String item_name, float mrp, int stock);
}