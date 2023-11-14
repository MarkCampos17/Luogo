package it.dedagroup.venditabiglietti.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import it.dedagroup.venditabiglietti.dto.request.LuogoDTORequest;
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
	
	
	public Luogo fromRequesttoDto(LuogoDTORequest l){
		if(l==null) throw new RuntimeException();
		Luogo luogo2=new Luogo();
		luogo2.setRiga1(l.getRiga1());
		luogo2.setRiga2(l.getRiga2());
		luogo2.setProvincia(l.getProvincia());
		luogo2.setCap(l.getCap());
		luogo2.setComune(l.getComune());
		luogo2.setNazionalita(l.getNazionalita());
		return luogo2;
	}
	
	public List<LuogoDTOResponse> toListDto(List<Luogo> l){
		if(l==null) throw new RuntimeException();
		return l.stream().map(this::toDto).toList();
	}
}
