package Model;

public class Ingrediente {

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
