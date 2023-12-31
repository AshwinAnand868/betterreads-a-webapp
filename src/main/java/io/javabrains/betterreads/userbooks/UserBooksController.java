package io.javabrains.betterreads.userbooks;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import io.javabrains.betterreads.book.Book;
import io.javabrains.betterreads.book.BookRepository;
import io.javabrains.betterreads.user.BooksByUser;
import io.javabrains.betterreads.user.BooksByUserRepository;

@Controller
public class UserBooksController {

    @Autowired
    private UserBooksRepository userBooksRepository;

    @Autowired
    private BooksByUserRepository booksByUserRepository;

    @Autowired
    private BookRepository bookRepository;
    
    @PostMapping("/addUserBook")
    public ModelAndView addBookForUser(
        @RequestBody MultiValueMap<String, String> formData,
        @AuthenticationPrincipal OAuth2User principal){

            if(principal == null || principal.getAttribute("login") == null){
                return null;
            }

            String bookId = formData.getFirst("bookId");

            Optional<Book> optionalBook = bookRepository.findById(bookId);

            if(!optionalBook.isPresent()){
                return new ModelAndView("redirect:/");
            }

            Book book = optionalBook.get();

            UserBooks userBooks = new UserBooks();
            UserBooksPrimaryKey userBooksPrimaryKey = new UserBooksPrimaryKey();
            String userId = principal.getAttribute("login");
            userBooksPrimaryKey.setUserId(userId);
            userBooksPrimaryKey.setBookId(bookId);

            userBooks.setKey(userBooksPrimaryKey);
            
            int rating = Integer.parseInt(formData.getFirst("rating"));

            if(formData.getFirst("startDate") != ""){
                LocalDate startDate = LocalDate.parse(formData.getFirst("startDate"));
                if(startDate != null)
                    userBooks.setStartedDate(startDate);
            }

            if(formData.getFirst("completedDate") != ""){
                LocalDate completedDate = LocalDate.parse(formData.getFirst("completedDate"));
                if(completedDate != null)
                    userBooks.setCompletedDate(completedDate);
            }
            

            userBooks.setRating(rating);


            userBooks.setReadingStatus(formData.getFirst("readingStatus"));

            userBooksRepository.save(userBooks);


            BooksByUser booksByUser = new BooksByUser();
            booksByUser.setId(userId);
            booksByUser.setBookId(bookId);
            booksByUser.setBookName(book.getName());
            booksByUser.setCoverIds(book.getCoverIds());
            booksByUser.setAuthorNames(book.getAuthorNames());
            booksByUser.setRating(rating);
            booksByUser.setReadingStatus(formData.getFirst("readingStatus"));

            booksByUserRepository.save(booksByUser);

            return new ModelAndView("redirect:/books/" + bookId);
    }

}
