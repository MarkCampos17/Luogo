package it.dedagroup.venditabiglietti.service.impl;

import java.util.List;
import java.util.Map;

import it.dedagroup.venditabiglietti.dto.request.FiltroLuogoDTORequest;
import it.dedagroup.venditabiglietti.mapper.LuogoMapper;
import it.dedagroup.venditabiglietti.repository.LuogoCriteriaQuery;
import it.dedagroup.venditabiglietti.repository.LuogoCriteriaQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.*;

import org.springframework.dao.OptimisticLockingFailureException;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.dedagroup.venditabiglietti.model.Luogo;
import it.dedagroup.venditabiglietti.repository.LuogoRepository;
import it.dedagroup.venditabiglietti.service.def.LuogoServiceDef;

@Service
@AllArgsConstructor
public class LuogoServiceImpl implements LuogoServiceDef {

	private final LuogoRepository luogoRepo;
	private final LuogoMapper luogoMapper;
	private final LuogoCriteriaQuery luogoCriteriaQuery;

	@Transactional(rollbackOn = ResponseStatusException.class)
	@Override
	public Luogo save(Luogo luogo) {
		try{
			return luogoRepo.save(luogo);
		}catch (OptimisticLockingFailureException e){
			throw new OptimisticLockingFailureException("Questo oggetto è stato modificato!");
		}

	}
	@Transactional(rollbackOn = ResponseStatusException.class)
	@Override
	public Luogo modify(Luogo luogo) {
		try {
			Luogo luogoModify = findLuogoById(luogo.getId());
			return save(luogoMapper.modifyLuogo(luogoModify,luogo));
		}catch (OptimisticLockingFailureException e){
			throw new OptimisticLockingFailureException("Questo oggetto è stato modificato!");
		}
	}

	@Transactional(rollbackOn = ResponseStatusException.class)
	@Override
	public void deleteLuogoById(long id) {
		try{
			Luogo luogo=findLuogoById(id);
			luogo.setCancellato(true);
			save(luogo);
		}catch (OptimisticLockingFailureException e){
			throw new OptimisticLockingFailureException("Questo oggetto è stato modificato!");
		}
	}

	@Override
	public List<Luogo> findAll() {
		return luogoRepo.findAll();
	}

	@Override
	public List<Luogo> findAllByIds(List<Long> ids) {
		return luogoRepo.findAllById(ids);
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
	public List<Luogo> findAllByRiga1AndRiga2AndComune(String riga1, String riga2, String comune) {
		return luogoRepo.findAllByRiga1AndRiga2AndComune(riga1, riga2, comune);
	}

	@Override
	public List<Luogo> findAllByRiga1AndRiga2(String riga1, String riga2) {
		return luogoRepo.findAllByRiga1AndRiga2(riga1, riga2);
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

	@Override
	public List<Luogo> filtraLuoghi(FiltroLuogoDTORequest request) {
		return luogoCriteriaQuery.findFiltrati(request);
	}
	@Override
	public List<Luogo> filtraLuoghiMap(Map<String, String> mapLuogo) {
		return luogoCriteriaQuery.filtraLuoghiMap(mapLuogo);
	}

}
