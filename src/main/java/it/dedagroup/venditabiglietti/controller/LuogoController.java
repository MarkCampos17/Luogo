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
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Luogo.class))),
            @ApiResponse(responseCode = "400", description = "Dopo aver passato l'oggetto tramite la RequestBody, ci viene restituito come risposta un errore")})
    @PutMapping("/update")
    public ResponseEntity<Luogo> aggiornaLuogo(@RequestBody Luogo luogo){
        return ResponseEntity.ok(luogoService.update(luogo));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Luogo>> findAll(){
        return ResponseEntity.ok(luogoService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Luogo> findById(@PathVariable long id){
        return ResponseEntity.ok(luogoService.findLuogoById(id));
    }

    @GetMapping("/findByRiga1AndComune/{riga1}/{comune}")
    public ResponseEntity<List<Luogo>> findAllLuogoByRiga1AndComune(@PathVariable String riga1, @PathVariable String comune){
        return ResponseEntity.ok(luogoService.findAllLuogoByRiga1AndComune(riga1, comune));
    }

    @GetMapping("/findByRiga1/{riga1}")
    public ResponseEntity<List<Luogo>> findAllLuogoByRiga1(@PathVariable String riga1){
        return ResponseEntity.ok(luogoService.findAllLuogoByRiga1(riga1));
    }

    @GetMapping("/findByRiga1AndRiga2AndComune/{riga1}/{riga2}/{comune}")
    public ResponseEntity<Luogo> findLuogoByRiga1AndRiga2AndComune(@PathVariable String riga1, @PathVariable String riga2, @PathVariable String comune){
        return ResponseEntity.ok(luogoService.findLuogoByRiga1AndRiga2AndComune(riga1, riga2, comune));
    }

    @GetMapping("/findByRiga1AndRiga2/{riga1}/{riga2}")
    public ResponseEntity<List<Luogo>> findLuogoByRiga1AndRiga2(@PathVariable String riga1, @PathVariable String riga2){
        return ResponseEntity.ok(luogoService.findLuogoByRiga1AndRiga2(riga1,riga2));
    }

    @GetMapping("/findByCap/{cap}")
    public ResponseEntity<List<Luogo>> findAllLuogoByCap(@PathVariable String cap){
        return ResponseEntity.ok(luogoService.findAllLuogoByCap(cap));
    }

    @GetMapping("/findByComune/{comune}")
    public ResponseEntity<List<Luogo>> indAllLuogoByComune(@PathVariable String comune){
        return ResponseEntity.ok(luogoService.findAllLuogoByComune(comune));
    }

    @GetMapping("/findByProvincia/{provincia}")
    public ResponseEntity<List<Luogo>> findAllLuogoByProvincia(@PathVariable String provincia){
        return ResponseEntity.ok(luogoService.findAllLuogoByProvincia(provincia));
    }

    @GetMapping("/findByNazionalita/{nazionalita}")
    public ResponseEntity<List<Luogo>> findAllLuogoByNazionalita(@PathVariable String nazionalita){
        return ResponseEntity.ok(luogoService.findAllLuogoByNazionalita(nazionalita));
    }

    @GetMapping("/findByNazionalitaAndComune/{nazionalita}/{comune}")
    public ResponseEntity<List<Luogo>> findAllLuogoByNazionalitaAndComune(@PathVariable String nazionalita, @PathVariable String comune){
        return ResponseEntity.ok(luogoService.findAllLuogoByNazionalitaAndComune(nazionalita, comune));
    }
}
