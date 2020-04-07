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
	public List<Riunione> findRiunioniByUser(List<RiunionePartecipanti> r) throws SQLException {
		List<Riunione> invitiRiunioni= new ArrayList<Riunione>();
		String query = "SELECT * FROM riunione WHERE id = ?";
		ResultSet result = null;
		PreparedStatement pstatement = null;
		for(int j=0;j<r.size();j++) {
		try {
			
				
				pstatement = con.prepareStatement(query);
				
				pstatement.setInt(1, r.get(j).getIdRiunione());
				
				result = pstatement.executeQuery();
			
				
				while (result.next()) {
					Riunione r1 = new Riunione();
					r1.setId(result.getInt("id"));
					r1.setTitolo(result.getString("titolo"));
					r1.setAnno(result.getInt("anno"));
					r1.setMese(result.getInt("mese"));
					r1.setGiorno(result.getInt("giorno"));
					r1.setOra(result.getInt("ora"));
					r1.setDurata(result.getInt("durata"));
					r1.setMaxPart(result.getInt("maxPart"));
					r1.setIdCreatore(result.getInt("creatore"));
					invitiRiunioni.add(r1);
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
		}
		return invitiRiunioni; 
	}
	
	
	
public void addRiunione(Riunione r) throws SQLException {
		
		String query = "INSERT into riunione (id, titolo, anno, mese, giorno, ora, durata, maxPart, creatore)   VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstatement = null;
		try {
			pstatement = con.prepareStatement(query);
			pstatement.setInt(1, r.getId()); 
			pstatement.setString(2, r.getTitolo());
			pstatement.setInt(3, r.getAnno()); 
			pstatement.setInt(4, r.getMese()); 
			pstatement.setInt(5, r.getGiorno()); 
			pstatement.setInt(6, r.getOra());
			pstatement.setInt(7, r.getDurata());
			pstatement.setInt(8, r.getMaxPart());
			pstatement.setInt(9, r.getIdCreatore());
			
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
public int getMaxId() throws SQLException {
	String query = "SELECT id FROM riunione WHERE id = (SELECT max(id) FROM riunione)";
	ResultSet result=null;
	int id=0;
	PreparedStatement pstatement = null;
	try {
		pstatement = con.prepareStatement(query);
		result = pstatement.executeQuery();
		while(result.next())
			id=result.getInt("id");
		
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
	return id;
}

	
	
}



