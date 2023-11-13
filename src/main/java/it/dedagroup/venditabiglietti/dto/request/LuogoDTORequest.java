package it.dedagroup.venditabiglietti.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LuogoDTORequest {

	private long id;
	private String riga1;
	private String riga2;
	private String provincia;
	private String cap;
	private String comune;
	private String nazionalita;
}
