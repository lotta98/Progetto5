package it.polimi.tiw.riunioni.beans;


public class Riunione {
	private int id;
	private String titolo;
	private int anno;
	private int mese;
	private int giorno;
	private int ora;
	private int durata;
	private int maxPart;
	private int idCreatore;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo= titolo;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public int getMese() {
		return mese;
	}
	public void setMese(int mese) {
		this.mese = mese;
	}
	public int getGiorno() {
		return giorno;
	}
	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata= durata;
	}
	public int getOra() {
		return ora;
	}
	public void setOra(int ora) {
		this.ora= ora;
	}
	public int getMaxPart() {
		return maxPart;
	}
	public void setMaxPart(int mp) {
		this.maxPart= mp;
	}
	public int getIdCreatore() {
		return idCreatore;
	}
	public void setIdCreatore(int idc) {
		this.idCreatore= idc;
	}
}
