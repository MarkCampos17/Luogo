package it.dedagroup.venditabiglietti.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class FiltroLuogoDTORequest {
    String provincia;
    String comune;
}
