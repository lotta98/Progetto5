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
	}/*
	public Utente checkUser(String usrn, String pwd) throws SQLException {
		String query = "SELECT * FROM utente  WHERE username = ? AND password =?";
		try (PreparedStatement pstatement = con.prepareStatement(query);) {
			pstatement.setString(1, usrn);
			pstatement.setString(2, pwd);
			try (ResultSet result = pstatement.executeQuery();) {
				if (!result.isBeforeFirst()) // no results, credential check failed
					return null;
				else {
					result.next();
					Utente user = new Utente();
					user.setId(result.getInt("id"));
					user.setUser(result.getString("username"));
					return user;
				}
			}
		}
	}*/
	public List<Utente> findUtenti(int idUtente) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Utente> utentiRegistrati(int idCreatore) throws SQLException {
		List <Utente> utenti = new ArrayList<Utente>();	
		String query = "SELECT * FROM utente WHERE idPartecipante <> idCreatore";
		ResultSet result = null;
		PreparedStatement pstatement = null;

		try {
			pstatement = con.prepareStatement(query);
			result = pstatement.executeQuery();
			while (result.next()) {
				Utente ut = new Utente();
				ut.setId(result.getInt("id"));
				ut.setUser(result.getString("username"));
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