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

	public Luogo(@NonNull String riga1, String riga2, @NonNull String provincia, @NonNull String cap, @NonNull String comune, @NonNull String nazionalita) {
		this.riga1 = riga1;
		this.riga2 = riga2;
		this.provincia = provincia;
		this.cap = cap;
		this.comune = comune;
		this.nazionalita = nazionalita;
	}
}
