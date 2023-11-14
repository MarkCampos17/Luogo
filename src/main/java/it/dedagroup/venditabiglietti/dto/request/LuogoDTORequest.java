package it.dedagroup.venditabiglietti.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LuogoDTORequest {
	
	@NotBlank(message = "La riga primaria non può essere vuota")
	private String riga1;
	
	private String riga2;
	
	@NotBlank(message = "La provincia non può essere vuota")
	private String provincia;
	
	@NotBlank(message = "Il cap non può essere vuoto")
	private String cap;
	
	@NotBlank(message = "Il comune non può essere vuoto")
	private String comune;
	
	@NotBlank(message = "La nazionalità non può essere vuota")
	private String nazionalita;
}
