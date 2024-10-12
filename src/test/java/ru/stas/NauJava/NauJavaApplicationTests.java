package ru.stas.NauJava;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.stas.NauJava.CustomRepository.ProductRepositoryImpl;
import ru.stas.NauJava.Dao.ProductRepository;
import ru.stas.NauJava.Dao.SubstanceRepository;
import ru.stas.NauJava.Entity.Product;
import ru.stas.NauJava.Entity.Substance;
import ru.stas.NauJava.Service.SubstanceServiceImpl;

import java.util.*;

@SpringBootTest
class NauJavaApplicationTests {

	private final ProductRepository productRepository;
	private final SubstanceRepository substanceRepository;
	private final ProductRepositoryImpl productRepositoryImpl;
	private final SubstanceServiceImpl substanceServiceImpl;
	@Autowired
	NauJavaApplicationTests(ProductRepository productRepository,
							SubstanceRepository substanceRepository,
							ProductRepositoryImpl productRepositoryImpl,
							SubstanceServiceImpl substanceServiceImpl)
	{
		this.productRepository = productRepository;
		this.substanceRepository = substanceRepository;
		this.productRepositoryImpl = productRepositoryImpl;
		this.substanceServiceImpl = substanceServiceImpl;
	}

	@Test
	@Transactional
	@Rollback
	void testFindUserByName()
	{
		String productName = UUID.randomUUID().toString();
		Product product = new Product();
		product.setName(productName);
		product.setPrice(1.0);
		productRepository.save(product);
		Product foundProduct = productRepository.findByNameAndPriceBetween(
				productName, 0.0, 10.0).getFirst();

		Assertions.assertNotNull(foundProduct);
		Assertions.assertEquals(product.getId(), product.getId());
		Assertions.assertEquals(product.getName(), foundProduct.getName());
		Assertions.assertEquals(product.getPrice(), foundProduct.getPrice());
	}

	@Test
	@Transactional
	@Rollback
	void testFindUserByNameBad()
	{
		String productName = UUID.randomUUID().toString();

		List<Product> foundProduct = productRepository.findByNameAndPriceBetween(
				productName, 0.0, 10.0);

		Assertions.assertEquals(0, foundProduct.size());
	}

	@Test
	@Transactional
	@Rollback
	void testFindUserByNameSubstances() {
		String productName = UUID.randomUUID().toString();
		String substanceName = UUID.randomUUID().toString();

		Substance substance = new Substance();
		substance.setName(substanceName);

		Product product = new Product();
		product.setName(productName);
		product.setPrice(2.0);

		Set<Substance> substances = new HashSet<>();
		substances.add(substance);
		product.setSubstance(substances);

		Set<Product> products = new HashSet<>();
		products.add(product);
		substance.setProducts(products);

		productRepository.save(product);
		substanceRepository.save(substance);

		Product foundProduct = productRepository.findBySubstancesName(substanceName).getFirst();

		Assertions.assertNotNull(foundProduct);
		Assertions.assertEquals(product.getId(), foundProduct.getId());
		Assertions.assertEquals(product.getName(), foundProduct.getName());
		Assertions.assertEquals(product.getPrice(), foundProduct.getPrice());
	}


	@Test
	@Transactional
	@Rollback
	void testFindUserByNameSubstancesBad() {
		String productName = UUID.randomUUID().toString();
		String substanceName = UUID.randomUUID().toString();

		Substance substance = new Substance();
		substance.setName(substanceName);

		Product product = new Product();
		product.setName(productName);
		product.setPrice(2.0);

		Set<Substance> substances = new HashSet<>();
		substances.add(substance);
		product.setSubstance(substances);

		Set<Product> products = new HashSet<>();
		products.add(product);
		substance.setProducts(products);

		//productRepository.save(product);
		substanceRepository.save(substance);

		List<Product> foundProduct = productRepository.findBySubstancesName(substanceName);

		Assertions.assertEquals(0, foundProduct.size());
	}

	@Test
	@Transactional
	@Rollback
	void testFindUserByName2()
	{
		String productName = UUID.randomUUID().toString();
		Product product = new Product();
		product.setName(productName);
		product.setPrice(1.0);
		productRepository.save(product);
		Product foundProduct = productRepositoryImpl.findByNameAndPriceBetween(
				productName, 0.0, 10.0).getFirst();

		Assertions.assertNotNull(foundProduct);
		Assertions.assertEquals(product.getId(), product.getId());
		Assertions.assertEquals(product.getName(), foundProduct.getName());
		Assertions.assertEquals(product.getPrice(), foundProduct.getPrice());
	}

	@Test
	@Transactional
	@Rollback
	void testFindUserByName2Bad()
	{
		String productName = UUID.randomUUID().toString();

		List<Product> foundProduct = productRepositoryImpl.findByNameAndPriceBetween(
				productName, 0.0, 10.0);

		Assertions.assertEquals(0, foundProduct.size());
	}

	@Test
	@Transactional
	@Rollback
	void testFindUserByNameSubstances2() {
		String productName = UUID.randomUUID().toString();
		String substanceName = UUID.randomUUID().toString();

		Substance substance = new Substance();
		substance.setName(substanceName);

		Product product = new Product();
		product.setName(productName);
		product.setPrice(2.0);

		Set<Substance> substances = new HashSet<>();
		substances.add(substance);
		product.setSubstance(substances);

		Set<Product> products = new HashSet<>();
		products.add(product);
		substance.setProducts(products);

		productRepository.save(product);
		substanceRepository.save(substance);

		Product foundProduct = productRepositoryImpl.findByNameSubstances(substanceName).getFirst();

		Assertions.assertNotNull(foundProduct);
		Assertions.assertEquals(product.getId(), foundProduct.getId());
		Assertions.assertEquals(product.getName(), foundProduct.getName());
		Assertions.assertEquals(product.getPrice(), foundProduct.getPrice());
	}

	@Test
	@Transactional
	@Rollback
	void testFindUserByNameSubstances2Bad() {
		String productName = UUID.randomUUID().toString();
		String substanceName = UUID.randomUUID().toString();

		Substance substance = new Substance();
		substance.setName(substanceName);

		Product product = new Product();
		product.setName(productName);
		product.setPrice(2.0);

		Set<Substance> substances = new HashSet<>();
		substances.add(substance);
		//product.setSubstance(substances);

		Set<Product> products = new HashSet<>();
		products.add(product);
		substance.setProducts(products);

		productRepository.save(product);
		substanceRepository.save(substance);

		List<Product> foundProduct = productRepositoryImpl.findByNameSubstances(substanceName);

		Assertions.assertEquals(0, foundProduct.size());
	}

	@Test
	@Transactional
	@Rollback
	void testTransaction() {
		String productName = UUID.randomUUID().toString();
		String substanceName = UUID.randomUUID().toString();

		Substance substance = new Substance();
		substance.setName(substanceName);

		Product product = new Product();
		product.setName(productName);
		product.setPrice(2.0);

		Set<Substance> substances = new HashSet<>();
		substances.add(substance);
		product.setSubstance(substances);

		Set<Product> products = new HashSet<>();
		products.add(product);
		substance.setProducts(products);

		productRepository.save(product);
		substanceRepository.save(substance);

		substanceServiceImpl.deleteSubstance(substanceName);

		List<Product> foundProduct = productRepositoryImpl.findByNameAndPriceBetween(productName, 0.0, 10.0);

		Assertions.assertEquals(0, foundProduct.size());
	}

	@Test
	@Transactional
	@Rollback
	void testTransactionBad() {
		String productName = UUID.randomUUID().toString();
		String substanceName = UUID.randomUUID().toString();

		Substance substance = new Substance();
		substance.setName(substanceName);

		Product product = new Product();
		product.setName(productName);
		product.setPrice(2.0);

		Set<Substance> substances = new HashSet<>();
		substances.add(substance);
		product.setSubstance(substances);

		Set<Product> products = new HashSet<>();
		products.add(product);
		substance.setProducts(products);

		productRepository.save(product);
		substanceRepository.save(substance);

		substanceServiceImpl.deleteSubstance("");

		List<Product> foundProduct = productRepositoryImpl.findByNameAndPriceBetween(productName, 0.0, 10.0);

		Assertions.assertEquals(1, foundProduct.size());
	}



}
