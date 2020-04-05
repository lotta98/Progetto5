package it.polimi.tiw.riunioni.DAO;

import java.io.InputStream;
import it.polimi.tiw.posts.beans.Post;
import it.polimi.tiw.riunioni.beans.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;;
import java.util.TimeZone;
import java.util.List;


public class RiunioniDAO {
	private Connection con;

	public RiunioniDAO (Connection connection) {
		this.con = connection;
	}


	public List<Riunione> findRiunioniByUser(int idUtente) throws SQLException {

		List<Riunione> riunioni = new ArrayList<Riunione>();
		String query = "SELECT * FROM riunione_partecipanti WHERE idPart = ?";
		ResultSet result = null;
		PreparedStatement pstatement = null;
		try {
			pstatement = con.prepareStatement(query);
			pstatement.setInt(1, idUtente);
			result = pstatement.executeQuery();
			while (result.next()) {
				Riunione riunione = new Riunione();

				riunione.setId(result.getId("id"));
				riunione.setTitolo(result.getString("titolo"));

				riunione.setData(result.getData("data"));
				riunione.setOra(result.getOra("ora"));

				riunione.setMaxPart(result.getMaxPart("maxPart"));
				riunione.setIdCreatore(result.getIdCreatore("idCreatore"));

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

	public List<Riunione> findRiunioniCreate(int idUtente) throws SQLException {

		List<Riunione> riunioni = new ArrayList<Riunione>();
		String query = "SELECT * FROM riunione WHERE idCreatore = ?";
		ResultSet result = null;
		PreparedStatement pstatement = null;
		try {
			pstatement = con.prepareStatement(query);
			pstatement.setInt(1, idUtente);
			result = pstatement.executeQuery();
			while (result.next()) {
				Riunione riunione = new Riunione();

				riunione.setId(result.getId("id"));
				riunione.setTitolo(result.getString("titolo"));

				riunione.setData(result.getData("data"));
				riunione.setOra(result.getOra("ora"));

				riunione.setMaxPart(result.getMaxPart("maxPart"));
				riunione.setIdCreatore(result.getIdCreatore("idCreatore"));

				riunioni.add(riunione);
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
		return riunioni; 
	}
}



