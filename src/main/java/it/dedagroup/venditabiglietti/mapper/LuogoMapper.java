package it.dedagroup.venditabiglietti.mapper;

import org.springframework.stereotype.Component;
import it.dedagroup.venditabiglietti.model.Luogo;

@Component
public class LuogoMapper {
	public Luogo modifyLuogo(Luogo luogoToModify, Luogo luogo){
		luogoToModify.setId(luogo.getId());
		luogoToModify.setRiga1(luogo.getRiga1());
		luogoToModify.setRiga2(luogo.getRiga2());
		luogoToModify.setProvincia(luogo.getProvincia());
		luogoToModify.setCap(luogo.getCap());
		luogoToModify.setComune(luogo.getComune());
		luogoToModify.setNazionalita(luogo.getNazionalita());
		luogoToModify.setIsCancellato(luogo.isIsCancellato());
		luogoToModify.setVersion(luogo.getVersion());
		return luogoToModify;
	}
}
