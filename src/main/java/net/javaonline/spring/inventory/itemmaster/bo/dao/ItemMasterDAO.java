package net.javaonline.spring.inventory.itemmaster.bo.dao;
 
public interface ItemMasterDAO {
 
    public boolean add(String item_code,String item_name, float mrp, int stock);
 
}