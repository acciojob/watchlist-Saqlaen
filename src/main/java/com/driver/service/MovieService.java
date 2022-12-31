package com.driver.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.dao.MovieRepository;
import com.driver.model.Director;
import com.driver.model.Movie;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movierepository;



    public String addMovies(Movie mv ){
        return this.movierepository.addMovieRepo(mv);
    }

    public String addDir(Director dr ){
        return this.movierepository.addDirRepo(dr);
    }

    public String addPair( String mv, String dr ){
        return this.movierepository.addPairRepo( mv, dr );
    }

    public Movie getMovie( String name ){
        return this.movierepository.getMovieRepo(name );
    }

    public Director getDirector( String name ){
        return this.movierepository.getDirectorRepo(name);
    }

    public List<Movie> getMovieByDir(String name ){
        return this.movierepository.getMovieList(name);
    }

    public List<Movie> getAllMovieService(){
        return this.movierepository.getAllMovieRepo();
    }


    public String deleteDirWork( String name ){
        return this.movierepository.deleteDirWorkRepo(name);
    }


    public String deleteEverythingRelatedtoPair(){
        return this.movierepository.deleteAllMovieRelatedToDirector();
    }




    public MovieRepository getMovierepository() {
        return movierepository;
    }

    public void setMovierepository(MovieRepository movierepository) {
        this.movierepository = movierepository;
    }

}
