package Model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDO")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "IDFK_USUARIO")
	private int idUsuario;
	private String endereco;
	private Double preco;

	@OneToMany
	@JoinTable(name = "PEDIDOxPIZZA", joinColumns = {
			@JoinColumn(name = "IDFK_PEDIDO", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "IDFK_PIZZA", referencedColumnName = "ID") })
	private List<Pizza> pizzas = new ArrayList<Pizza>();

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public void CalcularPrecoTotal() {
		for (Pizza p : pizzas) {
			if (this.preco == null) {
				this.preco = 0.0;
			}
			setPrecoTotal(this.preco + p.getPreco());
		}
	}

	public Double getPrecoTotal() {
		return preco;
	}

	private void setPrecoTotal(Double precoTotal) {
		this.preco = precoTotal;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void salvarPizza(Pizza pizza) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicePizzaJustInTime");
		EntityManager entityManager = factory.createEntityManager();

		pizza.calcularPreco();

		entityManager.getTransaction().begin();
		entityManager.persist(pizza);
		entityManager.getTransaction().commit();
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}
