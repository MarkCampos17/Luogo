package it.dedagroup.venditabiglietti.service.def;

import java.util.List;
import java.util.Map;

import it.dedagroup.venditabiglietti.dto.request.FiltroLuogoDTORequest;
import it.dedagroup.venditabiglietti.model.Luogo;

public interface LuogoServiceDef {

	public Luogo save(Luogo luogo);
	public Luogo modify(Luogo luogo);
	public void deleteLuogoById(long id);
	public List<Luogo> findAll();
	public List<Luogo> findAllByIds(List<Long> ids);
	public Luogo findLuogoById(long id);
	public List<Luogo> findAllLuogoByRiga1AndComune(String riga1, String comune);
	public List<Luogo> findAllLuogoByRiga1(String riga1);
	public List<Luogo> findAllByRiga1AndRiga2AndComune(String riga1, String riga2, String comune);
	public List<Luogo> findAllByRiga1AndRiga2(String riga1, String riga2);
	public List<Luogo> findAllLuogoByCap(String cap);
	public List<Luogo> findAllLuogoByComune(String comune);
	public List<Luogo> findAllLuogoByProvincia(String provincia);
	public List<Luogo> findAllLuogoByNazionalita(String nazionalita);
	public List<Luogo> findAllLuogoByNazionalitaAndComune(String nazionalita, String comune);

	public List<Luogo> filtraLuoghi(FiltroLuogoDTORequest request);
}
