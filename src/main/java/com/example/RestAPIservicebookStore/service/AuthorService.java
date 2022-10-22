package com.example.RestAPIservicebookStore.service;

import com.example.RestAPIservicebookStore.entity.Author;
import com.example.RestAPIservicebookStore.exception.AuthorAlreadyExistException;
import com.example.RestAPIservicebookStore.exception.AuthorDoesntExistException;
import com.example.RestAPIservicebookStore.exception.BookDoesntExistException;
import com.example.RestAPIservicebookStore.model.AuthorModel;
import com.example.RestAPIservicebookStore.model.BookModel;
import com.example.RestAPIservicebookStore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    AuthorRepository authorRepository;

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author addNewAuthor(Author author) throws AuthorAlreadyExistException {
        return authorRepository.save(author);
    }

    public AuthorModel getAuthorById(Long id) throws AuthorDoesntExistException {
        Author author = authorRepository.findById(id).get();
//      Optional<Author> ??
        if(author == null){
            throw new AuthorDoesntExistException("Писателя с данным id не существует.");
        }
        return AuthorModel.toModel(author);
    }

    public Author getAuthorByIdNotModel(Long id) throws AuthorDoesntExistException {
        Author author = authorRepository.findById(id).get();
        if(author == null){
            throw new AuthorDoesntExistException("Писателя с данным id не существует.");
        }
        return author;
    }


    public AuthorModel updateAuthor(Long id, Author updatedAuthor) throws AuthorDoesntExistException {
        Author author = authorRepository.findById(id).get();
        if(author == null){
            throw new AuthorDoesntExistException("Писателя с id = ." + id + " нет в БД.");
        }
        author.setFirstName(updatedAuthor.getFirstName());
        author.setLastName(updatedAuthor.getLastName());
        author.setDateOfBirth(updatedAuthor.getDateOfBirth());

        authorRepository.save(author);

        return AuthorModel.toModel(author);
    }

    public Long deleteAuthorById(Long id) throws AuthorDoesntExistException {
        if (authorRepository.findById(id) == null){
            throw new AuthorDoesntExistException("Писателя с таким id нет в коллекции.");
        }
        authorRepository.deleteById(id);
        return id;
    }



}
