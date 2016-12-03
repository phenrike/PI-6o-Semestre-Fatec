package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import Model.BuscaIngrediente;
import Model.BuscaUsuario;
import Model.Pedido;
import Model.Pizza;
import Model.Usuario;

@RestController
public class PedidoController {

	@RequestMapping(value = "/pedidos/{json}", method = RequestMethod.GET)
	public void fazerPedido(@PathVariable("json") JSONObject json) {
		BuscaIngrediente buscaIgrediente = new BuscaIngrediente();
		BuscaUsuario buscaUsuario = new BuscaUsuario();
		Pedido pedido = new Pedido();

		if (json.has("idusuario")) {
			Usuario usuario = new Usuario();
			usuario = buscaUsuario.buscarUsuario(json.get("idusuario").toString());
			pedido.setIdUsuario(usuario.getId());
			pedido.setEndereco(usuario.getEndereco());
		}

		JSONArray arrayPizzas = json.getJSONArray("pizzas");

		for (int k = 0; k < arrayPizzas.length(); k++) {
			Pizza pizza = new Pizza();
			JSONObject jsonIngredientes = arrayPizzas.getJSONObject(k);
			String sIngredientes = jsonIngredientes.get("ingredientes").toString();
			String filtered = sIngredientes.replaceAll("[^0-9,]", "");
			List<String> ingredientes = new ArrayList<String>(Arrays.asList(filtered.split(",")));
			for (int l = 0; l < ingredientes.size(); l++) {
				pizza.adicionarIngrediente(buscaIgrediente.buscarIngrediente(ingredientes.get(l)));
			}
			pedido.salvarPizza(pizza);
			pedido.getPizzas().add(pizza);
		}

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicePizzaJustInTime");
		EntityManager entityManager = factory.createEntityManager();

		pedido.CalcularPrecoTotal();

		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
	}

	@RequestMapping(value = "/pedidos", method = RequestMethod.GET)
	public List<Pedido> carregarPedidos() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicePizzaJustInTime");
		EntityManager entityManager = factory.createEntityManager();

		List<Pedido> listaPedidos = (entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class)).getResultList();

		entityManager.close();
		factory.close();

		return listaPedidos;

	}

	@RequestMapping(value = "/pedidos/{id}", method = RequestMethod.POST)
	public Pedido carregarPedidoPorId(@PathVariable("id") int id) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicePizzaJustInTime");
		EntityManager entityManager = factory.createEntityManager();

		Pedido pedido = (entityManager.createQuery("SELECT p FROM Pedido p WHERE p.id=" + id, Pedido.class))
				.getSingleResult();

		entityManager.close();
		factory.close();

		return pedido;
	}

}
