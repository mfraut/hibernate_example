package algo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BooksManager {

	static EntityManagerFactory factory;
	static EntityManager entityManager;

	public static void main(String[] args) {


		begin();
		//create();
		//update();
		//find();
		//remove();
		query();
		close();

	}

	private static void begin() {
		factory = Persistence.createEntityManagerFactory("BookUnit");

		entityManager = factory.createEntityManager();

		entityManager.getTransaction().begin();
	}

	private static void close() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

	private static void update() {
		Book existBook = new Book();
		existBook.setBookid(1);
		existBook.setTitle("Manual Python");
		existBook.setAuthor("as");
		existBook.setPrice(40);
		entityManager.merge(existBook);
	}

	private static void create() {

		Book b = new Book();

		b.setTitle("El nombre del viento");
		b.setAuthor("Patrick Rothfuss");
		b.setPrice((float) 9.99);
		entityManager.persist(b);
	}

	private static void find() {
		int primaryKey = 2;
		Book b = entityManager.find(Book.class, primaryKey);
		System.out.println(b.getTitle());
		System.out.println(b.getAuthor());
		System.out.println(b.getPrice());
	}



	private static void query() { 
		String jpql = "SELECT b FROM book where b.price < 30"; 
		Query query = entityManager.createQuery(jpql);

		List<Book> listBook = query.getResultList();

		for (Book b: listBook) { System.out.println(b.getTitle()); } }


	private static void remove () {
		int primaryKey = 2;
		Book reference = entityManager.getReference(Book.class, primaryKey);
		entityManager.remove(reference);
	}
}
