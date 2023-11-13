package it.dedagroup.venditabiglietti.dto.response;

import it.dedagroup.venditabiglietti.model.Luogo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LuogoDTOResponse {

	private String riga1;
	private String riga2;
	private String provincia;
	private String cap;
	private String comune;
	private String nazionalita;
	private long version;
	
	public LuogoDTOResponse(Luogo luogo) {
		this.riga1=luogo.getRiga1();
		this.riga2=luogo.getRiga2();
		this.provincia=luogo.getProvincia();
		this.cap=luogo.getCap();
		this.comune=luogo.getComune();
		this.nazionalita=luogo.getNazionalita();
		this.version=luogo.getVersion();
	}

}
