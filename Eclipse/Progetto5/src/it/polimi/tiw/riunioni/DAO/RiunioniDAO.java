package it.polimi.tiw.riunioni.DAO;



import it.polimi.tiw.riunioni.beans.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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

				riunione.setData(result.getDate("data"));
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
}



