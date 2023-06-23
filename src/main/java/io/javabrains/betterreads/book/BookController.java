package io.javabrains.betterreads.book;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.javabrains.betterreads.userbooks.UserBooks;
import io.javabrains.betterreads.userbooks.UserBooksPrimaryKey;
import io.javabrains.betterreads.userbooks.UserBooksRepository;

@Controller
public class BookController {

    private final String COVER_IMAGE_ROOT = "https://covers.openlibrary.org/b/id/";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserBooksRepository userBooksRepository;

    @GetMapping(value = "/books/{bookId}")
    /***
     * 
     * @param bookId Id of the book to be found
     * @param model a Model instance to be referred in the rendered view
     * @return an html view page
     */
    public String getBook(@PathVariable String bookId, Model model, @AuthenticationPrincipal OAuth2User principal){ 

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            String coverImageUrl = "/images/no-image.png";
            if(book.getCoverIds() != null && book.getCoverIds().size() > 0){
                coverImageUrl = COVER_IMAGE_ROOT + book.getCoverIds().get(0) + "-L.jpg";
            } 
            model.addAttribute("coverImage", coverImageUrl);
            model.addAttribute("book", book);

            
            if(principal != null && principal.getAttribute("login") != null){
                String login = principal.getAttribute("login");
                model.addAttribute("loginId", login);

                UserBooksPrimaryKey userBooksPrimaryKey = new UserBooksPrimaryKey();
                userBooksPrimaryKey.setBookId(bookId);
                userBooksPrimaryKey.setUserId(login);
                Optional<UserBooks> optionalUserBook = userBooksRepository.findById(userBooksPrimaryKey);

                if(optionalUserBook.isPresent()){
                    model.addAttribute("userBooks", optionalUserBook.get());
                } else {
                    model.addAttribute("userBooks", new UserBooks());
                }
            }

            return "book"; // Thymeleaf renders the the html page with this name
        }

        return "book-not-found"; 
    }
}
