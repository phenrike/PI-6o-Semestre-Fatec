package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoDB {

	private Connection con;
	private java.sql.Statement st;
	private ResultSet rs;

	public void iniciarConexao(){
		try {

			this.con = DriverManager.getConnection(
					"jdbc:mysql://localhost/pizzajustintime?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false",
					"root", "1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet executarQuery(String query) {
		
		try {
			this.st = this.con.createStatement();
			this.st.executeQuery(query);
			this.rs = st.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.rs;	
	}

	public void encerrarConexao() {
		try {
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
