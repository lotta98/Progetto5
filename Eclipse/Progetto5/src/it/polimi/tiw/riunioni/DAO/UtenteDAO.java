package it.polimi.tiw.riunioni.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.tiw.riunioni.beans.Utente;

public class UtenteDAO {
	private Connection con;

	public UtenteDAO(Connection connection) {
		this.con = connection;
	}
	public Utente checkUser(String username, String password) throws SQLException {
		Utente user = null;
		String query = "SELECT * FROM utente WHERE username = ? and password = ?";
		ResultSet result = null;
		PreparedStatement pstatement = null;
		
		try {
			pstatement = con.prepareStatement(query);
			pstatement.setString(1, username);
			pstatement.setString(2, password);
			result = pstatement.executeQuery();
			while (result.next()) {
				user = new Utente();
				user.setId(result.getInt("id"));
				user.setUser(result.getString("username"));
			}
		} catch (SQLException e) {
		    e.printStackTrace();
			throw new SQLException(e);

		} finally {
			try {
				result.close();
			} catch (Exception e1) {
				throw new SQLException(e1);
			}
			try {
				pstatement.close();
			} catch (Exception e2) {
				throw new SQLException(e2);
			}
		}		
		return user;
	}
	
	
	public List<Utente> utentiRegistrati(int idCreatore) throws SQLException {
		List<Utente> utenti = new ArrayList<Utente>();	
		String query = "SELECT * FROM utente WHERE id <> ?";
		ResultSet result = null;
		PreparedStatement pstatement = null;

		try {
			pstatement = con.prepareStatement(query);
			pstatement.setInt(1, idCreatore);
			result = pstatement.executeQuery();
			while (result.next()) {
				Utente ut = new Utente();
				ut.setId(result.getInt("id"));
				ut.setUser(result.getString("username"));
				ut.setNome(result.getString("nome"));
				ut.setCognome(result.getString("cognome"));
				utenti.add(ut);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);

		} finally {
			try {
				result.close();
			} catch (Exception e1) {
				throw new SQLException(e1);
			}
			try {
				pstatement.close();
			} catch (Exception e2) {
				throw new SQLException(e2);
			}
		}		
		return utenti;
	}
	
}