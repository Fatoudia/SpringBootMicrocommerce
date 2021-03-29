package com.ecommerce.microcommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.microcommerce.model.Product;
@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{
	
//Ces methodes sont generées authomatique par JpaRepository	< entity,type de son id>
//	public List<Product> findAll();
//	//	
//	public Product save(Product product);
Product findById(int id); //Recherche à partir de l'id
 List<Product> findByPrixGreaterThan(int prixLimit); // Methode avec modification dans console-h2 Liste des produits dont le prix est superieur prixLimit 

 //Methode manuelle Liste des produits dont le prix est superieur prixLimit
 @Query("select id, nom, prix from Product p where p.prix > :prixLimit")
 List<Product> chercherUnProduitCher(@Param("prixLimit") int prix);
	
	

}
