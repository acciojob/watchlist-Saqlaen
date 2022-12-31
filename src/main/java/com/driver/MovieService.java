package com.driver;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<String> getMovieByDir(String name ){
        return this.movierepository.getMovieList(name);
    }

    public List<String> getAllMovieService(){
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
