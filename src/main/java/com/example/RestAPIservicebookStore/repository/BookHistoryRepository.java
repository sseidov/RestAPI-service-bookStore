package com.example.RestAPIservicebookStore.repository;

import com.example.RestAPIservicebookStore.entity.BookHistory;
import org.springframework.data.repository.CrudRepository;

public interface BookHistoryRepository extends CrudRepository<BookHistory, Long> {

    BookHistory findByBookIdAndEndDateIsNull(Long bookId);

}
