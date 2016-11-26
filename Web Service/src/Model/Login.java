package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

	public boolean logar(String login, String senha) {

		boolean resultado = false;

		try {
			ConexaoDB conexao = new ConexaoDB();
			conexao.iniciarConexao();
			ResultSet rs = conexao.executarQuery("SELECT COUNT(1) AS LOGIN FROM USUARIO WHERE LOGIN='" + login + "' AND SENHA='" + senha + "'");
	
			if (rs.next()) {
				Integer bool = (Integer) rs.getInt("LOGIN");
				if (bool != null) {
					if (bool > 0) {
						resultado = true;
					}
				}
			}

			conexao.encerrarConexao();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado;
	}
}
