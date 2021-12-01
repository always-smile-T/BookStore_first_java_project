/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaontt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Thao
 */
public class CartObject implements Serializable{
    private Map<String, Integer> items;

    public Map<String, Integer> getItem() {
        return items;
    }
    
    public void addItemToCart(String item){
        // check item list existed
        if(this.items ==null){
            this.items = new HashMap<>();
        }//end if item is not existed
        //check item had existed in item
        int quantity = 1;
        if(this.items.containsKey(item)){
            quantity = this.items.get(item) + 1;
        }
        //drop item to item
        this.items.put(item, quantity);
    }
    public void removeItemFromCart(String item){
        //check item has existed
        if(this.items == null){
            return;
        }
        //check item had been existed in items
        if(this.items.containsKey(item)){
            this.items.remove(item);
        }
            if(this.items.isEmpty()){
                this.items = null;
            
        }
    }
}
