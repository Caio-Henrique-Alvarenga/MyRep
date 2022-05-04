package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Usuario {
	private int id;
	private String email;
	private String senhamestra;
	private String plano;
	
	public Usuario() {
		id = -1;
		email = "";
		senhamestra = "";
		plano = "";
	}

	public Usuario(int id, String email, String senhamestra, String plano) {
		setId(id);
		setEmail(email);
		setSenhamestra(senhamestra);
		setPlano(plano);
	}		
	
	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenhamestra() {
		return senhamestra;
	}

	public void setSenhamestra(String senhamestra) {
		this.senhamestra = senhamestra;
	}

	public String getPlano() {
		return plano;
	}
	
	public void setPlano(String plano) {
		this.plano = plano;
	}


	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "ID: " + id + "  E-mail" + email + "   senhamestra: " + senhamestra+ "   Plano: "
				+ plano;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((Usuario) obj).getID());
	}	
}