package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
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
			if(this.preco == null){
				this.preco = 0.0;
			}
			
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

	public void salvarPizza() {
		ConexaoDB conexao = new ConexaoDB();
		conexao.iniciarConexao();
		conexao.executarQuery("INSERT INTO PIZZA VALUES (0," + getPreco() + ")");
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

		for (int i = 0; i < ingredientes.size(); i++) {
			conexao.executarQuery("INSERT INTO PIZZAxINGREDIENTE VALUES ("+getId()+","+ingredientes.get(i).getId()+")");
		}
		conexao.encerrarConexao();
	}
}
