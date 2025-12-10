package com.azienda.jdbcEsercizio.model;

public class Automobile {
	private Integer id;
	private String marca;
	private String modello;
    private String colore;
    private Float cilindrata;
    private String targa;
	

	public Automobile() {
		super();
	}

	public Automobile(Integer id, String marca, String modello, String colore, Float cilindrata) {
		super();
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.colore = colore;
		this.cilindrata = cilindrata;
	}

	
	public Automobile(Integer id, String marca, String modello, String colore, Float cilindrata, String targa) {
		super();
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.colore = colore;
		this.cilindrata = cilindrata;
		this.targa = targa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public Float getCilindrata() {
		return cilindrata;
	}

	public void setCilindrata(Float cilindrata) {
		this.cilindrata = cilindrata;
	}
	
	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}
	@Override
	public String toString() {
		return "Automobile : id: " + id + ", marca: " + marca + ", modello: " + modello + ", colore: " + colore
				+ ", cilindrata: " + cilindrata + ", targa: " + targa;
	} 
	
    
}
