package pl.hansonq.dao;

import pl.hansonq.models.CartModel;

import java.util.Map;

public interface CartDao {
    boolean AddCart(CartModel Kartoteka);
    boolean DeleteCart(int id_kartoteka);
    boolean UpdateCart(CartModel Kartoteka);
    boolean Add(int id_kart, int id_opis, String opis);
    boolean AddProperties(int id_kartoteka,Map<Integer,String>cecha);

}
