package it.dedagroup.venditabiglietti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.dedagroup.venditabiglietti.model.Luogo;
import it.dedagroup.venditabiglietti.service.def.LuogoServiceDef;
import lombok.AllArgsConstructor;

import static it.dedagroup.venditabiglietti.utils.UtilPath.*;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@Tag(name = "Luogo Controller", description = "In questo controller andremo ad " +
        "aggiungere, modificare, eliminare o cercare i vari luoghi tramite i propri attributi")
public class LuogoController {

    private final LuogoServiceDef luogoService;


    @Operation(summary = "Endpoint per salvare sul database un oggetto di tipo Luogo",
            description = "In questo Endpoint con una richiesta in Post, passiamo un oggetto di tipo Luogo," +
                    " tramite una RequestBody. Chiamando il metodo save del LuogoService, che comunica con " +
                    "la repository, facciamo si che superati i vari controlli l'oggetto luogo venga salvato sul database.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description ="Dopo aver passato l'oggetto tramite la RequestBody in formato json, ci viene restituito un 200 confermando che il salvataggio è andato a buon fine.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
            @ApiResponse(responseCode = "400", description ="Dopo aver passato l'oggetto tramite la RequestBody, ci viene restituito come risposta un errore",
            content = @Content(mediaType = MediaType.ALL_VALUE))})
    @PostMapping(INSERT_PATH)
    public ResponseEntity<Luogo> aggiungiLuogo(@RequestBody Luogo luogo){
        return ResponseEntity.status(CREATED).body(luogoService.save(luogo));
    }
    @Operation(summary = "Endpoint per modificare sul database un oggetto di tipo Luogo",
            description = "In questo Endpoint con una richiesta in Post, passiamo un oggetto di tipo Luogo," +
                    " tramite una RequestBody. Chiamando il metodo modify del LuogoService, che comunica con " +
                    "la repository, facciamo si che trovato l'oggetto luogo tramite id, esso venga modificato sul database.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description ="Dopo aver passato l'oggetto tramite la RequestBody in formato json, ci viene restituito un 200 confermando che la modifica è andato a buon fine.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
            @ApiResponse(responseCode = "400", description ="La modifica dell'oggetto Luogo non avviene, ci viene restituita una risposta di errore non avendo trovato l'oggetto Luogo da modificare tramite id",
                    content = @Content(mediaType = MediaType.ALL_VALUE))})
    @PostMapping(MODIFY_PATH)
    public ResponseEntity<Luogo> modificaLuogo(@RequestBody Luogo luogo){
        return ResponseEntity.status(OK).body(luogoService.modify(luogo));
    }
    @Operation(summary = "Endpoint per trovare un oggetto di tipo luogo tramite id e settare cancellato a true",
    description = "In questo Endpoint con una richiesta in Post, passiamo un id tramite PathVariable. " +
            "Chiamando il metodo deleteLuogoById del LuogoService, che comunica con la repository, facciamo si che superati i vari controlli," +
            " recuperiamo l'oggetto tramite la chiave univoca id e settiamo il parametro cancellato a true")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dopo aver passato l'id tramite PathVariable, ci viene restituito un 200 confermando che è andato tutto a buon fine.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)))
    })
    @PostMapping(DELETE_PATH+"/{id}")
    public ResponseEntity<String> eliminaLuogo(@PathVariable long id){
        luogoService.deleteLuogoById(id);
        return ResponseEntity.ok("Luogo eliminato con successo!");
    }
    
    @Operation(summary = "metodo per cercare tutti i Luoghi", description = "In questo EndPoint ritorniamo una lista di luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovati", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
			@ApiResponse(responseCode = "400", description = "Luoghi non trovati, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping(FIND_ALL_PATH)
    public ResponseEntity<List<Luogo>> findAll(){
        return ResponseEntity.ok(luogoService.findAll());
    }
    
    
    @Operation(summary = "metodo per cercare un Luogo inserendo L'ID", description = "In questo EndPoint cerchiamo il luogo, se presente, tramite L'id inserito nel "
			+ "PathVariable, per poi ritornare l'oggetto selezionato tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Luogo trovato tramite l'ID", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
			@ApiResponse(responseCode = "400", description = "Luogo non trovato, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping(FIND_BY_ID_PATH+"/{id}")
    public ResponseEntity<Luogo> findById(@PathVariable long id){
        return ResponseEntity.ok(luogoService.findLuogoById(id));
    }
    
    
    
    @Operation(summary = "metodo per cercare una lista di Luoghi inserendo riga1", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite la riga1 inserita nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite riga1", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping(FIND_ALL_BY_RIGA1_PATH+"/{riga1}")
    public ResponseEntity<List<Luogo>> findAllLuogoByRiga1(@PathVariable String riga1){
        return ResponseEntity.ok(luogoService.findAllLuogoByRiga1(riga1));
    }
    
    
    @Operation(summary = "metodo per cercare una lista di Luoghi inserendo riga1 e comune", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite la riga1 e il comune inseriti nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite riga1 e comune", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping(FIND_ALL_BY_RIGA1_AND_COMUNE+"/{riga1}/{comune}")
    public ResponseEntity<List<Luogo>> findAllLuogoByRiga1AndComune(@PathVariable String riga1, @PathVariable String comune){
        return ResponseEntity.ok(luogoService.findAllLuogoByRiga1AndComune(riga1, comune));
    }

    
    @Operation(summary = "metodo per cercare una lista di Luoghi inserendo riga1 e riga2", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite la riga1 e riga2 inseriti nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite riga1 e riga2", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping(FIND_ALL_BY_RIGA1_RIGA2_PATH+"/{riga1}/{riga2}")
    public ResponseEntity<List<Luogo>> findAllLuogoByRiga1AndRiga2(@PathVariable String riga1, @PathVariable String riga2){
        return ResponseEntity.ok(luogoService.findAllByRiga1AndRiga2(riga1,riga2));
    }
    
    
    @Operation(summary = "metodo per cercare una lista di Luoghi inserendo riga1, riga2 e comune", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite la riga1, la riga2 ed il comune inseriti nel "
			+ "PathVariable, per poi ritornare una Lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di luoghi trovata tramite riga1, riga2 e comune", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
			@ApiResponse(responseCode = "400", description = "Lista di luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping(FIND_ALL_BY_RIGA1_RIGA2_COMUNE_PATH+"/{riga1}/{riga2}/{comune}")
    public ResponseEntity<List<Luogo>> findAllLuogoByRiga1AndRiga2AndComune(@PathVariable String riga1, @PathVariable String riga2, @PathVariable String comune){
        return ResponseEntity.ok(luogoService.findAllByRiga1AndRiga2AndComune(riga1, riga2, comune));
    }
    
    
    @Operation(summary = "metodo per cercare una lista di Luoghi inserendo il cap", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite il cap inserito nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite cap", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping(FIND_ALL_BY_CAP_PATH+"/{cap}")
    public ResponseEntity<List<Luogo>> findAllLuogoByCap(@PathVariable String cap){
        return ResponseEntity.ok(luogoService.findAllLuogoByCap(cap));
    }

    
    @Operation(summary = "metodo per cercare una lista Luoghi inserendo il comune", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite il comune inserito nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite comune", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping(FIND_ALL_BY_COMUNE_PATH+"/{comune}")
    public ResponseEntity<List<Luogo>> findAllLuogoByComune(@PathVariable String comune){
        return ResponseEntity.ok(luogoService.findAllLuogoByComune(comune));
    }

    
    @Operation(summary = "metodo per cercare una lista Luoghi inserendo la provincia", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite la provincia inserita nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite provincia", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping(FIND_ALL_BY_PROVINCIA_PATH+"/{provincia}")
    public ResponseEntity<List<Luogo>> findAllLuogoByProvincia(@PathVariable String provincia){
        return ResponseEntity.ok(luogoService.findAllLuogoByProvincia(provincia));
    }

    
    @Operation(summary = "metodo per cercare una lista di Luoghi inserendo la nazionalità", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite la nazionalità inserita nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite nazionalità", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping(FIND_ALL_BY_NAZIONALITA_PATH+"/{nazionalita}")
    public ResponseEntity<List<Luogo>> findAllLuogoByNazionalita(@PathVariable String nazionalita){
        return ResponseEntity.ok(luogoService.findAllLuogoByNazionalita(nazionalita));
    }

    
    @Operation(summary = "metodo per cercare una lista di Luoghi inserendo la nazionalità ed il comune", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite la nazionalità ed il comune inseriti nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite nazionalità e comune", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping(FIND_ALL_BY_NAZIONALITA_AND_COMUNE+"/{nazionalita}/{comune}")
    public ResponseEntity<List<Luogo>> findAllLuogoByNazionalitaAndComune(@PathVariable String nazionalita, @PathVariable String comune){
        return ResponseEntity.ok(luogoService.findAllLuogoByNazionalitaAndComune(nazionalita, comune));
    }
}
