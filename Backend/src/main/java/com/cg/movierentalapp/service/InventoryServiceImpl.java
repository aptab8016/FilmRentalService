package com.cg.movierentalapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movierentalapp.enitites.Film;
import com.cg.movierentalapp.enitites.Inventory;
import com.cg.movierentalapp.exception.CustomException;
import com.cg.movierentalapp.repositories.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;

	@Autowired
	public InventoryServiceImpl(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	@Override
	public void addFilmToStore(Inventory inventory)throws CustomException {
		if (inventory == null) {
			throw new CustomException("Invalid inventory provided.");
		}
		inventoryRepository.save(inventory);
	}
	// ----------------------------------------------------

//    @Override
//    public int getInventoryCountForFilm(Film film) {
//        return inventoryRepository.countByFilm(film);
//    }
	// =====================================================================================================================
	@Override
	public List<Inventory> getAllInventory()throws CustomException {
		List<Inventory> list = inventoryRepository.findAll();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("inventory are not found.. ");
		}
	}

	@Override
	public int getInventoryCountForFilm(Film film)throws CustomException {
		int count = inventoryRepository.countByFilm(film);

		if (count == 0) {
			throw new CustomException("Failed to retrieve inventory count for film: " + film.getFilmId());
		}

		return count;
	}

//==============================================================================================================

	// -------------------------------------------------------
	@Override
	public List<Map<String, Object>> getInventoryByStoreId(Byte storeId)throws CustomException {
		List<Inventory> inventoryList = inventoryRepository.findByStoreStoreId(storeId);
		List<Map<String, Object>> result = new ArrayList<>();

		for (Inventory inventory : inventoryList) {
			Film film = inventory.getFilm();
			String filmTitle = film.getTitle();
			int numberOfCopies = inventoryList.size();

			Map<String, Object> inventoryData = new HashMap<>();
			inventoryData.put("title", filmTitle);
			inventoryData.put("copies", numberOfCopies);

			result.add(inventoryData);
		}

		List<Map<String, Object>> list = result;
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Inventory by store id are not found.. ");
		}
	}

	// -----------------------------------------------------------------------

	@Override
	public List<Map<String, Object>> getInventoryCountByFilmIdWithStoreAddress(Short filmId)throws CustomException {
		List<Map<String, Object>> list = inventoryRepository.getInventoryCountByFilmIdWithStoreAddress(filmId);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Inventory count by film id with store id are not found.. ");
		}
	}

	// -------------------------------------------------------------------------
	@Override
	public List<Object[]> getInventoryCountByFilmAndStore(Short filmId, Byte storeId)throws CustomException {
		List<Object[]> list = inventoryRepository.findInventoryCountByFilmAndStore(filmId, storeId);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("inventory count by film and store are not found.. ");
		}
	}

	// *****************************************************************************
}
