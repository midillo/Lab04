package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
	}

	public Studente getStudenteFromMatricola(int matricola) {
		return studenteDao.getStudenteFromMatricola(matricola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(String codice){
		return corsoDao.getStudentiIscrittiAlCorso(codice);
	}
	
	public List<Corso> getTuttiICorsi() {
		return corsoDao.getTuttiICorsi();
	}
	
	public Corso getCorso(String codice) {
		return corsoDao.getCorso(codice);
	}
	
	public boolean inscriviStudenteACorso(int matricola, String codice) {
		return corsoDao.inscriviStudenteACorso(matricola, codice);
	}
	
	public String getCodiceByNomecorso(String nome) {
		return corsoDao.getCodiceByNomecorso(nome);
	}
	
	public List<Corso> getCorsiFromMatricola(int matricola){
		return studenteDao.getCorsiFromMatricola(new Studente(matricola, null, null, null));
	}
}
