package it.production.dao;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import it.production.Product;

@Repository
public class InMemoryProductDAO implements ProductDao {
	private final List<Product> products = new CopyOnWriteArrayList<>();

	@Override
	public List<Product> getProducts() {
		return Collections.unmodifiableList(products);
	}

	@Override
	public void createProduct(Product product) {
		products.add(product);
	}

	@Override
	public void deleteProduct(String pr_name) {
		for (Product product: products) {
			if (pr_name.equals(product.getTitle())) {
				products.remove(product);
				break;
			}
		}
		
	}



}
