package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENTE")
public class Ingrediente {
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private int id;
	
	private String nome;
	private Double precoPorGrama;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getPrecoPorGrama() {
		return precoPorGrama;
	}

	public void setPrecoPorGrama(Double precoPorKg) {
		this.precoPorGrama = precoPorKg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
