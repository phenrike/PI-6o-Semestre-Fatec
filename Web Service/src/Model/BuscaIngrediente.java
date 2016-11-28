package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscaIngrediente {
	
	private Ingrediente ingrediente = new Ingrediente();
	
	public Ingrediente buscarIngrediente(String id) {
		try {

			ConexaoDB conexao = new ConexaoDB();
			conexao.iniciarConexao();
			ResultSet rs = conexao.executarQuery("select * from ingrediente where id='" + id + "'");
			
			this.ingrediente = new Ingrediente();

			if (rs.next()) {
				this.ingrediente.setId(rs.getInt("ID"));
				this.ingrediente.setNome(rs.getString("NOME"));
				this.ingrediente.setPrecoPorGrama(rs.getDouble("PRECOPORGRAMA"));
			}
			
			conexao.encerrarConexao();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.ingrediente;
	}
}
