package it.dedagroup.venditabiglietti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.venditabiglietti.model.Luogo;

public interface LuogoRepository extends JpaRepository<Luogo, Long> {
	
	public List<Luogo> findAllLuogoByRiga1AndComune(String riga1, String comune);
	public List<Luogo> findAllLuogoByRiga1(String riga1);
	public List<Luogo> findAllByRiga1AndRiga2AndComune(String riga1, String riga2, String comune);
	public List<Luogo> findAllByRiga1AndRiga2(String riga1, String riga2);
	public List<Luogo> findAllLuogoByCap(String cap);
	public List<Luogo> findAllLuogoByComune(String comune);
	public List<Luogo> findAllLuogoByProvincia(String provincia);
	public List<Luogo> findAllLuogoByNazionalita(String nazionalita);
	public List<Luogo> findAllLuogoByNazionalitaAndComune(String nazionalita, String comune);
}
