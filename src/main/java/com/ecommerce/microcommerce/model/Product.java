package com.ecommerce.microcommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

//Pour cacher plusieurs attributs
//@JsonIgnoreProperties(value = { "id","prixAchat"})
//Transforme le bean en entity avec @Entity

@Entity
public class Product {
	//id notre clé unique
	//Generation automatique de id avec GeneratedValue
	//En fin reconnu par le JPA
	@Id
	@GeneratedValue()
	private int id;
	
	//Condition sur le nombre de caractère pour le nom
    @NotNull
	@Size(min=3, max=10, message = "Ici votre message")
	private String nom;
	private int prix;
	
	//A ne pas afficher
	//@JsonIgnore
	private int prixAchat;
	
	public Product() {
		super();
	}
	
	
	public Product(int id, String nom, int prix, int prixAchat) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.prixAchat = prixAchat;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	
	public int getPrixAchat() {
		return prixAchat;
	}


	public void setPrixAchat(int prixAchat) {
		this.prixAchat = prixAchat;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", nom=" + nom + ", prix=" + prix + ", prixAchat=" + prixAchat + "]";
	}


	
	

}
