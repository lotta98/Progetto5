package it.polimi.tiw.riunioni.DAO;

import it.polimi.tiw.riunioni.beans.RiunionePartecipanti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class RiunioniPartecipanteDAO {
	private Connection con;
	public RiunioniPartecipanteDAO (Connection connection) {
		this.con = connection;
	}


	public List<RiunionePartecipanti> findRiunioniPartByUser(int idUtente) throws SQLException {
		
		List<RiunionePartecipanti> riunioni = new ArrayList<RiunionePartecipanti>();
		String query = "SELECT * FROM riunionepartecipante WHERE idPart = ?";
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

	
	public void addRiunionePartecipante(int id, int idU[]) throws SQLException {
		String query = "INSERT INTO riunionepartecipante (idRiunione, idPart) VALUES(?, ?)";
		System.out.println(id);
		
		PreparedStatement pstatement = null;
		for(int j=0;j<idU.length;j++) {
			try {
					pstatement = con.prepareStatement(query);
					pstatement.setInt(1, id);
					pstatement.setInt(2, idU[j]);
					pstatement.executeUpdate();
				
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
	
	
}

