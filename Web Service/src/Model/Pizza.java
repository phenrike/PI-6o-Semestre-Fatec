package Model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PIZZA")
public class Pizza {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private int id;

	@OneToMany
	@JoinTable(name = "PIZZAxINGREDIENTE", joinColumns = {
			@JoinColumn(name = "IDFK_PIZZA", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "IDFK_INGREDIENTE", referencedColumnName = "ID") })
	private List<Ingrediente> ingredientes = new ArrayList<>();

	private Double preco;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void calcularPreco() {
		for (Ingrediente i : ingredientes) {
			if (this.preco == null) {
				this.preco = 0.0;
			}
			setPreco(this.preco += i.getPrecoPorGrama());
		}
	}

	public Double getPreco() {
		return preco;
	}

	private void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public void adicionarIngrediente(Ingrediente ingrediente) {
		ingredientes.add(ingrediente);
	}
}
