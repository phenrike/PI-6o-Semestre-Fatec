package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Pedido {

	private int id;
	private ArrayList<Pizza> pizzas;
	private Double precoTotal;
	private String endereco;
	private Usuario usuario;

	public void salvarPedido(JSONArray jsonpedido) {

		this.usuario = new Usuario();
		this.pizzas = new ArrayList<Pizza>();
		BuscaIngrediente buscaIgrediente = new BuscaIngrediente();
		BuscaUsuario buscaUsuario = new BuscaUsuario();

		for (int i = 0; i < jsonpedido.length(); i++) {
			JSONObject jsonobject = jsonpedido.getJSONObject(i);

			if (jsonobject.has("ENDERECO")) {
				setEndereco(jsonobject.get("ENDERECO").toString());
			}

			if (jsonobject.has("USUARIO_ID")) {
				setUsuario(buscaUsuario.buscarUsuario(jsonobject.get("USUARIO_ID").toString()));
			}

			if (jsonobject.has("PIZZA_INGREDIENTES")) {
				Pizza pizza = new Pizza();
				String sIngredientes = jsonobject.get("PIZZA_INGREDIENTES").toString();
				List<String> ingredientes = new ArrayList<String>(Arrays.asList(sIngredientes.split(",")));
				for (int j = 0; j < ingredientes.size(); j++) {
					pizza.adicionarIngrediente(buscaIgrediente.buscarIngrediente(ingredientes.get(j)));
				}
				pizzas.add(pizza);
			}
		}

	}

	public ArrayList<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(ArrayList<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public Double getPrecoTotal() {
		for (int i = 0; i < pizzas.size(); i++) {
			setPrecoTotal(this.precoTotal + pizzas.get(i).getPreco());
		}

		return precoTotal;
	}

	private void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

}
