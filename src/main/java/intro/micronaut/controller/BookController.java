package intro.micronaut.controller;

import java.util.List;

import javax.inject.Inject;

import intro.micronaut.model.Book;
import intro.micronaut.repo.BookManager;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Controller("/books")
public class BookController {

	@Inject
	private BookManager bookManager;
	
	@Get("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBook(String id) {
		Book book = bookManager.get(id);
		return book;
	}
	
	@Get
	@Produces(MediaType.APPLICATION_JSON)
	public Flowable<List<Book>> getAllBooks() {
		return Flowable.just(bookManager.getAll());
	}
	
	@Delete("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(String id) {
		bookManager.delete(bookManager.get(id));
	}

	@Post
	@Consumes(MediaType.APPLICATION_JSON)
	public Single<String> add(Book book) {
		String bookId = bookManager.add(book);
		return Single.just(bookId);
	}

}
