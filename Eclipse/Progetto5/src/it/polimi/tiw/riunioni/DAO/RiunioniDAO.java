package it.polimi.tiw.riunioni.DAO;



import it.polimi.tiw.riunioni.beans.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


import java.util.List;


public class RiunioniDAO {
	private Connection con;

	public RiunioniDAO (Connection connection) {
		this.con = connection;
	}


	public List<Riunione> findRiunioniCreate(int idUtente) throws SQLException {

		List<Riunione> riunioniCreate = new ArrayList<Riunione>();
		String query = "SELECT * FROM riunione WHERE creatore = ?";
		ResultSet result = null;
		PreparedStatement pstatement = null;
		
		try {
			pstatement = con.prepareStatement(query);
			pstatement.setInt(1, idUtente);
			result = pstatement.executeQuery();
			while (result.next()) {
				Riunione riunione = new Riunione();

				riunione.setId(result.getInt("id"));
				riunione.setTitolo(result.getString("titolo"));

				riunione.setGiorno(result.getInt("giorno"));
				riunione.setMese(result.getInt("mese"));
				riunione.setAnno(result.getInt("anno"));
				riunione.setOra(result.getInt("ora"));
				riunione.setDurata(result.getInt("durata"));
				riunione.setMaxPart(result.getInt("maxPart"));
				riunione.setIdCreatore(result.getInt("creatore"));

				riunioniCreate.add(riunione);
				
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
		return riunioniCreate; 
	}
	
	
public void addRiunione(Riunione r) throws SQLException {
		
		String query = "INSERT into riunione (titolo, giorno, mese, anno, ora, durata, maxPart, idCreatore)   VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		int code = 0;
		PreparedStatement pstatement = null;
		try {
			pstatement = con.prepareStatement(query);
			pstatement.setString(1, r.getTitolo());
			pstatement.setInt(2, r.getGiorno()); 
			pstatement.setInt(3, r.getMese()); 
			pstatement.setInt(4, r.getAnno()); 
			pstatement.setInt(5, r.getOra());
			pstatement.setInt(6, r.getDurata());
			pstatement.setInt(7, r.getMaxPart());
			pstatement.setInt(8, r.getIdCreatore());
			
			code = pstatement.executeUpdate();
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



