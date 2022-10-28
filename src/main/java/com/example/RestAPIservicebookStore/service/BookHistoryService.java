package com.example.RestAPIservicebookStore.service;

import com.example.RestAPIservicebookStore.entity.BookHistory;
import com.example.RestAPIservicebookStore.repository.BookHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;


@Service
public class BookHistoryService {
    BookHistoryRepository bookHistoryRepository;
    @Autowired
    public void setBookHistoryRepository(BookHistoryRepository bookHistoryRepository) {
        this.bookHistoryRepository = bookHistoryRepository;
    }

    public BookHistory setPrice(BookHistory bookHistory){
        bookHistory.setStartDate(Calendar.getInstance().getTime());
        return bookHistoryRepository.save(bookHistory);
    }

    public BookHistory updatePrice(BookHistory bookHistory, Long bookId){
        BookHistory b = bookHistoryRepository.findByBookIdAndEndDateIsNull(bookId);
        b.setEndDate(Calendar.getInstance().getTime());
        bookHistoryRepository.save(b);

        System.out.println(b.getPrice());

        return setPrice(bookHistory);
    }


}
