package pl.hansonq.dao;

import pl.hansonq.models.CartModel;


import java.util.Map;

public interface CartDao {
    boolean UpdatePrice(CartModel Kartoteka);
    boolean DeleteCart(CartModel cartModel);
    boolean UpdateCart(CartModel Kartoteka);
    boolean UpdateCart3(CartModel Kartoteka);

 //   boolean AddProperties(PropertyModel propertyModel,int punkt);
    boolean AddCart(CartModel cartModel);
    boolean AddCart4(CartModel cartModel,int punkt);
   int SelectCart(CartModel cartModel);

}
