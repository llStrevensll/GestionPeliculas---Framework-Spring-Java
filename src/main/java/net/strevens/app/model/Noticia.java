package net.strevens.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="noticias")
public class Noticia {
	//Id, generar autoincrement valor -> GeneratedValue -- Mysql usa constante IDENTITY
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private Date fecha;
	private String detalle;
	private String estatus;
	
	public Noticia() {
		System.out.println("Constructor Noticia");
		this.fecha = new Date();
		this.estatus = "Activa";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {

		System.out.println("Set Titulo");
		this.titulo = titulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		System.out.println("Set detalle");
		this.detalle = detalle;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		System.out.println("Set status");
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "Noticia [id=" + id + ", titulo=" + titulo + ", fecha=" + fecha + ", detalle=" + detalle + ", estatus="
				+ estatus + "]";
	}
	
	
}
