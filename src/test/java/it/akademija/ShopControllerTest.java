package it.akademija;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import it.production.CreateProductCommand;
import it.production.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { App.class })
public class ShopControllerTest {
	private static final String URI = "/api/shop";
	@Autowired 
	private TestRestTemplate rest;
	
	@Test 
	public void testTruemethod() {
		assertTrue(true);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void createsProductRetrievesProductsListAndDeletesProduct() {
		final String title = "Obuolys";
		final CreateProductCommand createProduct = new CreateProductCommand();
		
		createProduct.setTitle(title);
		rest.postForLocation(URI, createProduct);
		List<Product> products = rest.getForEntity(URI, List.class).getBody();
		Assert.assertThat(products.size(), CoreMatchers.is(1));
		rest.delete(URI + "/" + title);
		products = rest.getForEntity(URI, List.class).getBody();
		Assert.assertThat(products.size(), CoreMatchers.is(0));
	}
}
