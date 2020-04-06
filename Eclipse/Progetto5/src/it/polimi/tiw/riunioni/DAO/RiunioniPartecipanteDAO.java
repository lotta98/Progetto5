package it.polimi.tiw.riunioni.DAO;

import it.polimi.tiw.riunioni.beans.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.tiw.riunioni.beans.Riunione;
import it.polimi.tiw.riunioni.beans.RiunionePartecipanti;

public class RiunioniPartecipanteDAO {
	private Connection con;
	public RiunioniPartecipanteDAO (Connection connection) {
		this.con = connection;
	}


	public List<RiunionePartecipanti> findRiunioniPartByUser(int idUtente) throws SQLException {
		
		List<RiunionePartecipanti> riunioni = new ArrayList<RiunionePartecipanti>();
		String query = "SELECT * FROM riunionepartecipanti WHERE idPart = ?";
		ResultSet result = null;
		PreparedStatement pstatement = null;
		try {
			
			pstatement = con.prepareStatement(query);
			pstatement.setInt(1, idUtente);
			result = pstatement.executeQuery();
			while (result.next()) {
				RiunionePartecipanti riunione = new RiunionePartecipanti();

				riunione.setIdRiunione(result.getInt("idRiunione"));
				riunione.setIdPart(result.getInt("idPart"));
				riunioni.add(riunione);
			}
		}  catch (SQLException e) {
			throw new SQLException(e);
		}finally {
			try {
				result.close();
			} catch (Exception e1) {
				throw new SQLException("Cannot close result");
			}
			try {
				pstatement.close();
			} catch (Exception e1) {
				throw new SQLException("Cannot close statement");
			}
		}
		return riunioni;
	}
	
	public List<Riunione> findRiunioniByUser(List<RiunionePartecipanti> r) throws SQLException {
		List<Riunione> invitiRiunioni= new ArrayList<Riunione>();
		String query = "SELECT * FROM riunione WHERE id = ?";
		ResultSet result = null;
		PreparedStatement pstatement = null;
		try {
			for(int j=0;j<r.size();++j) {
				
				pstatement = con.prepareStatement(query);
				
				pstatement.setInt(1, r.get(j).getIdRiunione());
				
				result = pstatement.executeQuery();
				
				
				while (result.next()) {
					Riunione r1 = new Riunione();
					r1.setId(result.getInt("id"));
					r1.setTitolo(result.getString("titolo"));
					r1.setData(result.getDate("data"));
					r1.setOra(result.getInt("ora"));
					r1.setDurata(result.getInt("durata"));
					r1.setMaxPart(result.getInt("maxPart"));
					r1.setIdCreatore(result.getInt("creatore"));
					invitiRiunioni.add(r1);
				}
				
			

			}

		} catch (SQLException e) {
			throw new SQLException(e);

		}finally {
			try {
				result.close();
			} catch (Exception e1) {
				throw new SQLException("Cannot close result");
			}
			try {
				pstatement.close();
			} catch (Exception e1) {
				throw new SQLException("Cannot close statement");
			}
		}
		return invitiRiunioni; 
	}
	
	public void addRiunionePartecipante(int id, List <Utente> utenti) throws SQLException {
		String query = "INSERT into riunione (id, utente)   VALUES(?, ?)";

		int code = 0;
		PreparedStatement pstatement = null;
		
		try {
			
			for(int j=0;j<utenti.size();++j) {
				
				pstatement = con.prepareStatement(query);
				pstatement.setInt(1, id);
				pstatement.setInt(2, utenti.get(j).getId());
				code = pstatement.executeUpdate();
			}
			
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			try {
				pstatement.close();
			} catch (Exception e1) {

			}
		}
	}
	
	
}

