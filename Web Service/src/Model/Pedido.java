package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Pedido {

	private int id;
	private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
	private Double precoTotal;
	private String endereco;
	private Usuario usuario = new Usuario();
	BuscaIngrediente buscaIgrediente = new BuscaIngrediente();
	BuscaUsuario buscaUsuario = new BuscaUsuario();

	public void salvarPedido(JSONObject jsonPedido) {

		if (jsonPedido.has("idusuario")) {
			setUsuario(buscaUsuario.buscarUsuario(jsonPedido.get("idusuario").toString()));
			this.endereco = this.usuario.getEndereco();
		}

		JSONArray arrayPizzas = jsonPedido.getJSONArray("pizzas");

		for (int k = 0; k < arrayPizzas.length(); k++) {
			Pizza pizza = new Pizza();
			JSONObject jsonIngredientes = arrayPizzas.getJSONObject(k);
			String sIngredientes = jsonIngredientes.get("ingredientes").toString();
			String filtered = sIngredientes.replaceAll("[^0-9,]", "");
			List<String> ingredientes = new ArrayList<String>(Arrays.asList(filtered.split(",")));
			for (int l = 0; l < ingredientes.size(); l++) {
				pizza.adicionarIngrediente(buscaIgrediente.buscarIngrediente(ingredientes.get(l)));
			}
			pizza.salvarPizza();
			pizzas.add(pizza);
		}

		ConexaoDB conexao = new ConexaoDB();
		conexao.iniciarConexao();
		conexao.executarQuery("INSERT INTO PEDIDO VALUES (0,'" + this.endereco + "'," + getPrecoTotal() + ")");
		conexao.executarQuery("SELECT LAST_INSERT_ID() INTO @ID");

		ResultSet rs = conexao.executarQuery("select @ID");

		try {
			if (rs.next()) {
				setId(rs.getInt("@ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < pizzas.size(); i++) {
			conexao.executarQuery("INSERT INTO PEDIDOxPIZZA VALUES (" + getId() + "," + usuario.getId() + ","
					+ pizzas.get(i).getId() + ",'Pendente')");
		}

		conexao.encerrarConexao();

	}

	public ArrayList<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(ArrayList<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public Double getPrecoTotal() {
		for (int i = 0; i < pizzas.size(); i++) {
			if (this.precoTotal == null) {
				this.precoTotal = 0.0;
			}
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

	public void setId(int id) {
		this.id = id;
	}

	public void carregarPedidos() {

	}

}
