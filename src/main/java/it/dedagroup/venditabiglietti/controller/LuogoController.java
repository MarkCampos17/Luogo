package it.dedagroup.venditabiglietti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.dedagroup.venditabiglietti.dto.request.LuogoDTORequest;
import it.dedagroup.venditabiglietti.model.Luogo;
import it.dedagroup.venditabiglietti.service.def.LuogoServiceDef;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/luogo")
@Tag(name = "Luogo Controller", description = "In questo controller andremo ad " +
        "aggiungere, modificare, eliminare o cercare i vari luoghi tramite i propri attributi")
public class LuogoController {

    private final LuogoServiceDef luogoService;


    /*@Operation(summary = "metodo per trovare tutte le presenze basate su un tipo di lavoro", description = "L'utente admin dopo aver effettuato un login trova una lista di presenze basate sul tipo di lavoro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'utente admin dopo aver inserito il tipo di lavoro trova tutte le presenze basate sul tipo di lavoro inserito",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PresenzaDTOResponse.class))),
            @ApiResponse(responseCode = "400", description = "L'utente admin inserisce un tipo di lavoro non valido",
                    content = @Content(mediaType = MediaType.ALL_VALUE))
    })*/


    @Operation(summary = "Endpoint per salvare sul database un oggetto di tipo Luogo",
            description = "In questo Endpoint con una richiesta in Post, passiamo un oggetto di tipo Luogo," +
                    " tramite una RequestBody. Chiamando il metodo save del LuogoService, che comunica con " +
                    "la repository, facciamo si che superati i vari controlli l'oggetto luogo venga salvato sul database.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description ="Dopo aver passato l'oggetto tramite la RequestBody in formato json, ci viene restituito un 200 confermando che il salvataggio è andato a buon fine.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
            @ApiResponse(responseCode = "400", description ="Dopo aver passato l'oggetto tramite la RequestBody, ci viene restituito come risposta un errore",
            content = @Content(mediaType = MediaType.ALL_VALUE))})
    @PostMapping("/save")
    public ResponseEntity<Luogo> aggiungiLuogo(@RequestBody Luogo luogo){
        return ResponseEntity.ok(luogoService.save(luogo));
    }
    
    
    @Operation(summary = "Endpoint per trovare un oggetto di tipo luogo tramite id e settare cancellato a true",
    description = "In questo Endpoint con una richiesta in Post, passiamo un id tramite PathVariable. " +
            "Chiamando il metodo deleteLuogoById del LuogoService, che comunica con la repository, facciamo si che superati i vari controlli," +
            " recuperiamo l'oggetto tramite la chiave univoca id e settiamo il parametro cancellato a true")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dopo aver passato l'id tramite PathVariable, ci viene restituito un 200 confermando che è andato tutto a buon fine.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> eliminaLuogo(@PathVariable long id){
        luogoService.deleteLuogoById(id);
        return ResponseEntity.ok("Luogo eliminato con successo!");
    }
    
    
    @Operation(summary = "Endpoint per modificare un oggetto già esistente",
    description = "In questo Enpoint con una richiesta in Put, passiamo un oggetto di tipo Luogo, tramite RequestBody." +
            "Chiamando il metodo update del LuogoService, che comunica con la repository, estraiamo dal database quello'oggetto" +
            "lo modifichiamo e lo risalviamo con i dati modificati")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dopo aver passato l'oggetto tramite la RequestBody in formato json, ci viene restituito un 200 confermando che le varie operazioni sono andate a buon fine.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LuogoDTORequest.class))),
            @ApiResponse(responseCode = "400", description = "Dopo aver passato l'oggetto tramite la RequestBody, ci viene restituito come risposta un errore")})
    @PutMapping("/update")
    public ResponseEntity<Luogo> aggiornaLuogo(@RequestBody Luogo luogo){
        return ResponseEntity.ok(luogoService.update(luogo));
    }
    
    
    @Operation(summary = "metodo cercare tutti i Luoghi", description = "In questo EndPoint ritorniamo una lista di luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovati", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LuogoDTORequest.class))),					
			@ApiResponse(responseCode = "400", description = "Luoghi non trovati, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    public ResponseEntity<List<Luogo>> findAll(){
        return ResponseEntity.ok(luogoService.findAll());
    }
    
    
    @Operation(summary = "metodo per cercare un Luogo inserendo L'ID", description = "In questo EndPoint cerchiamo il luogo(se esiste) tramite L'id inserito nel "
			+ "PathVariable, per poi ritornare l'oggetto selezionato tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Luogo trovato tramite l'ID", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LuogoDTORequest.class))),
			@ApiResponse(responseCode = "400", description = "Luogo non trovato, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping("/findById/{id}")
    public ResponseEntity<Luogo> findById(@PathVariable long id){
        return ResponseEntity.ok(luogoService.findLuogoById(id));
    }
    
    
    
    @Operation(summary = "metodo per cercare una lista Luoghi inserendo riga1", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite la riga1 inserita nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite riga1", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LuogoDTORequest.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping("/findByRiga1/{riga1}")
    public ResponseEntity<List<Luogo>> findAllLuogoByRiga1(@PathVariable String riga1){
        return ResponseEntity.ok(luogoService.findAllLuogoByRiga1(riga1));
    }
    
    
    @Operation(summary = "metodo per cercare una lista Luoghi inserendo riga1 e comune", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite la riga1 e il comune inseriti nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite riga1 e comune", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LuogoDTORequest.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping("/findByRiga1AndComune/{riga1}/{comune}")
    public ResponseEntity<List<Luogo>> findAllLuogoByRiga1AndComune(@PathVariable String riga1, @PathVariable String comune){
        return ResponseEntity.ok(luogoService.findAllLuogoByRiga1AndComune(riga1, comune));
    }

    
    @Operation(summary = "metodo per cercare una lista Luoghi inserendo riga1 e riga", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite la riga1 e riga2 inseriti nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite riga1 e riga2", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LuogoDTORequest.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping("/findByRiga1AndRiga2/{riga1}/{riga2}")
    public ResponseEntity<List<Luogo>> findLuogoByRiga1AndRiga2(@PathVariable String riga1, @PathVariable String riga2){
        return ResponseEntity.ok(luogoService.findLuogoByRiga1AndRiga2(riga1,riga2));
    }
    
    
    @Operation(summary = "metodo per cercare un Luogho inserendo riga1, riga2 e comune", description = "In questo EndPoint cerchiamo un Luogho tramite la riga1, la riga2 ed il comune inseriti nel "
			+ "PathVariable, per poi ritornare un Luogho tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Luogo trovato tramite riga1, riga2 e comune", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LuogoDTORequest.class))),
			@ApiResponse(responseCode = "400", description = "Luogo non trovato, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping("/findByRiga1AndRiga2AndComune/{riga1}/{riga2}/{comune}")
    public ResponseEntity<Luogo> findLuogoByRiga1AndRiga2AndComune(@PathVariable String riga1, @PathVariable String riga2, @PathVariable String comune){
        return ResponseEntity.ok(luogoService.findLuogoByRiga1AndRiga2AndComune(riga1, riga2, comune));
    }
    
    
    @Operation(summary = "metodo per cercare una lista Luoghi inserendo il cap", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite il cap inserito nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite cap", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LuogoDTORequest.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping("/findByCap/{cap}")
    public ResponseEntity<List<Luogo>> findAllLuogoByCap(@PathVariable String cap){
        return ResponseEntity.ok(luogoService.findAllLuogoByCap(cap));
    }

    
    @Operation(summary = "metodo per cercare una lista Luoghi inserendo il comune", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite il comune inserito nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite comune", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LuogoDTORequest.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping("/findByComune/{comune}")
    public ResponseEntity<List<Luogo>> indAllLuogoByComune(@PathVariable String comune){
        return ResponseEntity.ok(luogoService.findAllLuogoByComune(comune));
    }

    
    @Operation(summary = "metodo per cercare una lista Luoghi inserendo la provincia", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite la provincia inserita nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite provincia", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LuogoDTORequest.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping("/findByProvincia/{provincia}")
    public ResponseEntity<List<Luogo>> findAllLuogoByProvincia(@PathVariable String provincia){
        return ResponseEntity.ok(luogoService.findAllLuogoByProvincia(provincia));
    }

    
    @Operation(summary = "metodo per cercare una lista Luoghi inserendo la nazionalità", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite la nazionalità inserita nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite nazionalità", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LuogoDTORequest.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping("/findByNazionalita/{nazionalita}")
    public ResponseEntity<List<Luogo>> findAllLuogoByNazionalita(@PathVariable String nazionalita){
        return ResponseEntity.ok(luogoService.findAllLuogoByNazionalita(nazionalita));
    }

    
    @Operation(summary = "metodo per cercare una lista Luoghi inserendo la nazionalità ed il comune", description = "In questo EndPoint cerchiamo una lista di Luoghi tramite la nazionalità ed il comune inseriti nel "
			+ "PathVariable, per poi ritornare la lista di Luoghi tramite repository")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista di Luoghi trovata tramite nazionalità e comune", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LuogoDTORequest.class))),
			@ApiResponse(responseCode = "400", description = "Lista di Luoghi non trovata, errato inserimento del PathVariable, ci viene restituito come risposta un errore", content = @Content(mediaType = MediaType.ALL_VALUE))
				})
    @GetMapping("/findByNazionalitaAndComune/{nazionalita}/{comune}")
    public ResponseEntity<List<Luogo>> findAllLuogoByNazionalitaAndComune(@PathVariable String nazionalita, @PathVariable String comune){
        return ResponseEntity.ok(luogoService.findAllLuogoByNazionalitaAndComune(nazionalita, comune));
    }
}
