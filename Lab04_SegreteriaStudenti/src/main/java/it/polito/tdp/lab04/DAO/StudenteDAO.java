package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	/*
	 * Ottengo nome e cognome di uno studente data una matricola
	 */
	public Studente getStudenteFromMatricola(int matricola) {

		final String sql = "SELECT * FROM studente WHERE matricola = ?";
		Studente studente;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();
			 
			rs.first();
			studente = new Studente(rs.getInt("matricola"), rs.getString("cognome"),rs.getString("nome"), rs.getString("CDS"));
			
			rs.close();
			st.close();
			conn.close();
			return studente;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	/*
	 * Ottengo i corsi che esegue uno studente data una matricola
	 */
	public List<Corso> getCorsiFromMatricola(Studente studente){
		
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd FROM iscrizione i, corso c "
								+ "WHERE c.codins = i.codins AND matricola = ?";
		LinkedList<Corso> corsi = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			
			ResultSet rs = st.executeQuery();
			 
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				corsi.add(c);
			}

			rs.close();
			st.close();
			conn.close();
			return corsi;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
}
