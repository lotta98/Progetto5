package it.polimi.tiw.riunioni.beans;


public class Utente {
	private int id;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return username;
	}
	public void setUser(String user) {
		this.username= user;
	}
	public String getPass() {
		return password;
	}
	public void setPassword(String pwd) {
		this.password = pwd;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome= nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome= cognome;
	}
}
