package Model;

import java.util.ArrayList;

public class Pizza {

	private int id;
	private ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
	private Double preco;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getPreco() {
		for (int i = 0; i < ingredientes.size(); i++) {
			setPreco(this.preco + ingredientes.get(i).getPrecoPorGrama());
		}

		return preco;
	}

	private void setPreco(Double preco) {
		this.preco = preco;
	}

	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public void adicionarIngrediente(Ingrediente ingrediente) {
		ingredientes.add(ingrediente);
	}
}
