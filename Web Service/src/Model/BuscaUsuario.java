package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscaUsuario {
	
	private Usuario usuario;
	
	public Usuario buscarUsuario(String id) {
		try {

			ConexaoDB conexao = new ConexaoDB();
			conexao.iniciarConexao();
			ResultSet rs = conexao.executarQuery("select * from usuario where id='" + id + "'");
			
			this.usuario = new Usuario();

			if (rs.next()) {
				this.usuario.setId(rs.getInt("ID"));
				this.usuario.setNome(rs.getString("NOME"));
				this.usuario.setEmail(rs.getString("EMAIL"));
				this.usuario.setLogin(rs.getString("LOGIN"));
				this.usuario.setSenha(rs.getString("SENHA"));
				this.usuario.setTelefone(rs.getString("TELEFONE"));
				this.usuario.setDadosCartao(("DADOSCARTAO"));
				this.usuario.setEndereco(rs.getString("ENDERECO"));
			}
			
			conexao.encerrarConexao();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.usuario;
	}

}
