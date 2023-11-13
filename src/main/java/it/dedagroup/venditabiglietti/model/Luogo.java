package it.dedagroup.venditabiglietti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Luogo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	@NonNull
	private String riga1;
	@Column
	private String riga2;
	@Column(nullable = false)
	@NonNull
	private String provincia;
	@Column(nullable = false)
	@NonNull
	private String cap;
	@Column(nullable = false)
	@NonNull
	private String comune;
	@Column(nullable = false)
	@NonNull
	private String nazionalita;
	@Column(columnDefinition = "boolean default false")
	private boolean cancellato;
	@Version
	private long version;
	
	

}
