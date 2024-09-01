package com.cg.movierentalapp.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.movierentalapp.enitites.Film;
import com.cg.movierentalapp.enitites.Inventory;
import com.cg.movierentalapp.enitites.InventoryRequest;
import com.cg.movierentalapp.enitites.Store;
import com.cg.movierentalapp.repositories.FilmRepository;
import com.cg.movierentalapp.repositories.InventoryRepository;
import com.cg.movierentalapp.service.FilmService;
import com.cg.movierentalapp.service.InventoryService;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {
	
	 private final FilmService filmService;
	 private final InventoryService inventoryService;
	 
	 @Autowired
	 FilmRepository filmRepository;
	 @Autowired
	 InventoryRepository inventoryRepository;
	 

	 	@Autowired
	    public InventoryController(InventoryService inventoryService,FilmService filmService) {
	        this.inventoryService = inventoryService;
	        this.filmService=filmService;
	    }
	    //--------------------------------------------------------------
	 	
	 	
	 	@GetMapping("/admin/inventory/films")
	 	@PreAuthorize("hasAuthority('ADMIN')")
	 	public List<Map<String, Object>> getAllInventoryOfFilms() {
	 	    List<Inventory> inventoryList = inventoryService.getAllInventory();
	 	    List<Map<String, Object>> result = new ArrayList<>();
	 	    Map<Film, Integer> filmInventoryMap = new HashMap<>();

	 	    for (Inventory inventory : inventoryList) {
	 	        Film film = inventory.getFilm();
	 	        int numberOfCopies = inventoryService.getInventoryCountForFilm(film);
	 	        filmInventoryMap.put(film, filmInventoryMap.getOrDefault(film, 0) + numberOfCopies);
	 	    }

	 	    for (Map.Entry<Film, Integer> entry : filmInventoryMap.entrySet()) {
	 	        Film film = entry.getKey();
	 	        Integer totalCount = entry.getValue();

	 	        Map<String, Object> inventoryData = new HashMap<>();
	 	        inventoryData.put("title", film.getTitle());
	 	        inventoryData.put("totalCopies", totalCount);

	 	        result.add(inventoryData);
	 	    }

	 	    return result;
	 	}

	    
	   //-----------------------------------------------
	    @GetMapping("/public/inventory/store/{id}")
	    public ResponseEntity<List<Map<String, Object>>> getInventoryByStoreId(@PathVariable("id") Byte storeId) {
	        List<Map<String, Object>> inventoryData = inventoryService.getInventoryByStoreId(storeId);
	        return ResponseEntity.ok(inventoryData);
	    }
	 	
	    //---------------------------------------------------------
	    
	    @GetMapping("/public/inventory/film/{id}")
	    public ResponseEntity<List<Map<String, Object>>> getInventoryCountByFilmIdWithStoreAddress(
	            @PathVariable("id") Short filmId) {
	        List<Map<String, Object>> inventoryData = inventoryService.getInventoryCountByFilmIdWithStoreAddress(filmId);
	        return ResponseEntity.ok(inventoryData);
	    }

	    
	    //*****************************************************************************
	    @GetMapping("/admin/inventory")
	    @PreAuthorize("hasAuthority('ADMIN')")
	    public List<Inventory> getAllInventory() {
	        return inventoryService.getAllInventory();
	    }
	    
	 	@GetMapping("/public/inventory/film/{filmId}/store/{storeId}")
	 	public ResponseEntity<Map<String, String>> getInventoryCount(@PathVariable Short filmId, @PathVariable Byte storeId) {
	 	    List<Object[]> inventoryCount = inventoryService.getInventoryCountByFilmAndStore(filmId, storeId);
	 	    if (inventoryCount.isEmpty()) {
	 	        return ResponseEntity.notFound().build();
	 	    }
	 	    String storeAddress = inventoryCount.get(0)[0].toString();
	 	    String count = inventoryCount.get(0)[1].toString();
	 	    
	 	    Map<String, String> response = new HashMap<>();
	 	    response.put("storeAddress", storeAddress);
	 	    response.put("count", count);
	 	    
	 	    return ResponseEntity.ok(response);
	 	}
	    
	    //*****************************************************************************
	    
	  //add film to inventory  

	 	@PostMapping("/admin/inventory/add")
	    @PreAuthorize("hasAuthority('ADMIN')")
	    public ResponseEntity<String> addFilmToStore(@RequestBody Inventory inventory) {
	        inventoryService.addFilmToStore(inventory);
	        return ResponseEntity.ok("Record Created Successfully");
	    }
	 	
	 	//==================================================================================
	 	

	    
	    

}
