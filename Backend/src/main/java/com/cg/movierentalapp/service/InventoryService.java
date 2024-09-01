package com.cg.movierentalapp.service;

import java.util.List;
import java.util.Map;

import com.cg.movierentalapp.enitites.Film;
import com.cg.movierentalapp.enitites.Inventory;

public interface InventoryService {
	
	  void addFilmToStore(Inventory inventory);
	
	  List<Inventory> getAllInventory();
	  
	  int getInventoryCountForFilm(Film film);
	  
	  List<Map<String, Object>> getInventoryByStoreId(Byte storeId);
	    
	  List<Map<String, Object>> getInventoryCountByFilmIdWithStoreAddress(Short filmId);


	  //*****************************************************************************
	  List<Object[]> getInventoryCountByFilmAndStore(Short filmId, Byte storeId);
	  //*****************************************************************************
	  
}
