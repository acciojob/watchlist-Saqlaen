package com.driver.dao;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.driver.model.Director;
import com.driver.model.Movie;

@Repository
public class MovieRepository {
    
    private HashMap<String,Movie> moviemap = new HashMap<>();
    private HashMap<String,Director> dirmap = new HashMap<>();
    private HashMap<String, List<Movie> > pair = new HashMap<>();


    public String addMovieRepo(Movie m ){
        // System.out.println( m.toString() );
        String name = m.getName();
        moviemap.put( name, m );
        return "success";
    }

    public String addDirRepo(Director d){
        // System.out.println( d.toString() );
        String name = d.getName();
        dirmap.put( name , d );
        return "success";
    }
    
    public String addPairRepo( String movieName, String directorName ){
        List<Movie> li = null;
        if( moviemap.containsKey( movieName ) && dirmap.containsKey( directorName ) ){    
            Movie movie = moviemap.get( movieName );
            if( pair.containsKey( directorName) ){
                li = pair.get(directorName);
                li.add( movie );
                pair.put( directorName, li );
            }
            else{
                li = new ArrayList<>();
                li.add(movie);
                pair.put(directorName, li);
            }
        }

        return "success";
    }
    
    public Movie getMovieRepo(String name ){
        if( moviemap.containsKey(name)){
            return moviemap.get(name);
        }
        return null;
    }

    public Director getDirectorRepo( String name ){
        if( dirmap.containsKey(name)){
            return dirmap.get(name);
        }
        return null;
    }

    public List<Movie> getMovieList( String directorName ){
        if( pair.containsKey(directorName) ){
            return pair.get( directorName );
        }
        return new LinkedList<>();
    }

    public List<Movie> getAllMovieRepo(){
        List<Movie> movieList  = new LinkedList<>();
        for( String str : moviemap.keySet() ){
            movieList.add( moviemap.get(str) );
        }
        return movieList;
    }

    public String deleteDirWorkRepo(String name ){
        // get dir movies 
        if( pair.containsKey(name) ){
            List<Movie> dirMovies = pair.get(name);
            pair.remove(name);
            dirmap.remove( name );
            for( Movie m : dirMovies ){
                if( moviemap.containsKey( m.getName() ) ){
                    moviemap.remove( m.getName() );
                }
            }
        }
        // remove dir from the pair
        // remove dir from hashmap
        // remove all movies of dir from moviemap 
        return "success";
    }

    public String deleteAllMovieRelatedToDirector(){
        for( Map.Entry< String, List<Movie> > entry : pair.entrySet() ){
            String dirName = entry.getKey();
            List<Movie> list = entry.getValue();
            for( Movie name : list  ){
                if( moviemap.containsKey( name.getName()) ){
                    moviemap.remove( name.getName() );
                }
            }
            pair.remove( dirName );
            if( dirmap.containsKey(dirName)){
                dirmap.remove( dirName );
            }
        }
        return "success";
    }


}
