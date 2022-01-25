package it.production;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.production.dao.ProductDao;

@RestController
@RequestMapping(value = "/api/shop")
public class ShopController {
	private final ProductDao productDao; // pridedame DAO
	
	@Autowired
	public ShopController(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	/* Apdoros užklausas: GET /api/shop */
	@RequestMapping(method = RequestMethod.GET)
		public List<Product> getProducts() {
		return productDao.getProducts(); // skaitome per DAO
	}
	
	/* Sukurs produktą ir grąžins atsakymą su HTTP statusu 201 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
		public void createProduct (@RequestBody final CreateProductCommand cmd) { 
			int size = productDao.getProducts().size();
			
			Product product = new Product();
			product.setId(cmd.getId()); 
			product.setTitle(cmd.getTitle());
			product.setImageUrl(cmd.getImageUrl());
			product.setDescription(cmd.getDescription());
			product.setPrice(cmd.getPrice());
			product.setQuantity(cmd.getQuantity());
			productDao.createProduct(product); 
			
			if(productDao.getProducts().size() > size)
				System.out.println("New product: " + cmd.getTitle() + " is created.");
			    System.out.println(cmd);
        }
	
	/* Apdoros užklausas: DELETE /api/shop/<produktas> */
	@RequestMapping(path = "/{title}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct( @PathVariable final String title ) {
		int size = productDao.getProducts().size();
		productDao.deleteProduct(title);
		
		if(productDao.getProducts().size() < size)
			System.out.println(title + " is deleted.");
	}
}
	

