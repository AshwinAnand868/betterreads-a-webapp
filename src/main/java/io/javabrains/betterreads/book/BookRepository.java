package io.javabrains.betterreads.book;

import org.springframework.data.cassandra.repository.CassandraRepository;


public interface BookRepository extends CassandraRepository<Book, String> {
    
}
