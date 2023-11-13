package it.dedagroup.venditabiglietti.service.impl;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.dedagroup.venditabiglietti.model.Luogo;
import it.dedagroup.venditabiglietti.repository.LuogoRepository;
import it.dedagroup.venditabiglietti.service.def.LuogoServiceDef;

@Service
public class LuogoServiceImpl implements LuogoServiceDef {
	
	@Autowired
	LuogoRepository luogoRepo;
	@Transactional(rollbackOn = ResponseStatusException.class)
	@Override
	public Luogo save(Luogo luogo) {
		return luogoRepo.save(luogo);
	}
	@Transactional(rollbackOn = ResponseStatusException.class)
	@Override
	public Luogo update(Luogo luogo) {
		return save(luogo);
	}
	@Transactional(rollbackOn = ResponseStatusException.class)
	@Override
	public void deleteLuogoById(long id) {
		Luogo luogo=findLuogoById(id);
		luogo.setCancellato(true);
		save(luogo);
	}

	@Override
	public List<Luogo> findAll() {
		return luogoRepo.findAll();
	}

	@Override
	public Luogo findLuogoById(long id) {
		return luogoRepo.findById(id)
				.orElseThrow(()->new ResponseStatusException(NOT_FOUND, "Errore, nessun luogo con id "+id+" trovato!"));
	}

	@Override
	public List<Luogo> findAllLuogoByRiga1AndComune(String riga1, String comune) {
		return luogoRepo.findAllLuogoByRiga1AndComune(riga1, comune);
	}

	@Override
	public List<Luogo> findAllLuogoByRiga1(String riga1) {
		return luogoRepo.findAllLuogoByRiga1(riga1);
	}

	@Override
	public Luogo findLuogoByRiga1AndRiga2AndComune(String riga1, String riga2, String comune) {
		return luogoRepo.findLuogoByRiga1AndRiga2AndComune(riga1, riga2, comune)
				.orElseThrow(()->
				new ResponseStatusException(NOT_FOUND, 
						"Errore, nessun luogo con indirizzo "+riga1+", "+riga2+", "+comune+" trovato!"));
	}

	@Override
	public List<Luogo> findLuogoByRiga1AndRiga2(String riga1, String riga2) {
		return luogoRepo.findLuogoByRiga1AndRiga2(riga1, riga2);
	}

	@Override
	public List<Luogo> findAllLuogoByCap(String cap) {
		return luogoRepo.findAllLuogoByCap(cap);
	}

	@Override
	public List<Luogo> findAllLuogoByComune(String comune) {
		return luogoRepo.findAllLuogoByComune(comune);
	}

	@Override
	public List<Luogo> findAllLuogoByProvincia(String provincia) {
		return luogoRepo.findAllLuogoByProvincia(provincia);
	}

	@Override
	public List<Luogo> findAllLuogoByNazionalita(String nazionalita) {
		return luogoRepo.findAllLuogoByNazionalita(nazionalita);
	}

	@Override
	public List<Luogo> findAllLuogoByNazionalitaAndComune(String nazionalita, String comune) {
		return luogoRepo.findAllLuogoByNazionalitaAndComune(nazionalita, comune);
	}

}
