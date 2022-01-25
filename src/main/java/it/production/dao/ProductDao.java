package it.production.dao;

import java.util.List;

import it.production.Product;

/*
* DAO - Data Access Object. Darbo su Product objektais API.
*/
public interface ProductDao {
	List<Product> getProducts();
	void createProduct(Product user);
	void deleteProduct(String username);
}
