package it.dedagroup.venditabiglietti.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import it.dedagroup.venditabiglietti.dto.response.LuogoDTOResponse;
import it.dedagroup.venditabiglietti.model.Luogo;

@Component
public class LuogoMapper {

	public LuogoDTOResponse toDto(Luogo l){
		if(l==null) throw new RuntimeException();
		LuogoDTOResponse luogo=new LuogoDTOResponse();
		luogo.setRiga1(l.getRiga1());
		luogo.setRiga2(l.getRiga2());
		luogo.setProvincia(l.getProvincia());
		luogo.setCap(l.getCap());
		luogo.setComune(l.getComune());
		luogo.setNazionalita(l.getNazionalita());
		return luogo;
	}
	
	public List<LuogoDTOResponse> toListDto(List<Luogo> l){
		if(l==null) throw new RuntimeException();
		return l.stream().map(this::toDto).toList();
	}
}
