package it.dedagroup.venditabiglietti.controller;

import it.dedagroup.venditabiglietti.model.Luogo;
import it.dedagroup.venditabiglietti.service.def.LuogoServiceDef;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/luogo")
public class LuogoController {

    private final LuogoServiceDef luogoService;

    @PostMapping("/save")
    public ResponseEntity<Luogo> aggiungiLuogo(@RequestBody Luogo luogo){
        return ResponseEntity.ok(luogoService.save(luogo));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> eliminaLuogo(@PathVariable long id){
        luogoService.deleteLuogoById(id);
        return ResponseEntity.ok("Luogo eliminato con successo!");
    }

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
