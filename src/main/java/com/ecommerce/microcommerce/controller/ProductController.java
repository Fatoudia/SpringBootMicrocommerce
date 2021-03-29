package com.ecommerce.microcommerce.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.microcommerce.dao.ProductDAO;
import com.ecommerce.microcommerce.exceptions.ProduitIntrouvableException;
import com.ecommerce.microcommerce.model.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description= "Gestion des produits")
@RestController
public class ProductController {
	
	//Repository (couche d'accès aux données à instancier automatiquement)
	@Autowired
	private ProductDAO productDao;
	
	//List de tous les produits
	@GetMapping(value = "Produits")
	public List<Product> ListeProduits(){
		
		return productDao.findAll();
	}
	
	
	//produits/{id}
	@ApiOperation(value = "Récupération d'un produit à partir de son ID")
	@GetMapping(value = "Produits/{id}")
	public Product afficherUnProduit(@PathVariable int id) throws ProduitIntrouvableException{
		
		//Product product = new Product(id, "aspirateur", 100);
		
		Product produit = productDao.findById(id);
		if(produit == null) 
		
			throw new ProduitIntrouvableException("Le produit avec l'id " + id + "n'existe pas");
		
		return produit;
	}
	
	//Postman(Methode post de l'api rest
	@PostMapping(value = "/Produits")
	public ResponseEntity<Void> ajouterProduit(@Valid @RequestBody Product product) {
	
		Product product1 = productDao.save(product);
		
		if(product1 == null) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(product1.getId())
				.toUri();
					
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(value="test/produits/{prixLimit}")
	public List<Product> testDeRequetes(@PathVariable int prixLimit){
		
		return productDao.findByPrixGreaterThan(prixLimit);
	}
}
