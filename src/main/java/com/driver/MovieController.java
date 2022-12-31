package com.driver;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/movies")
public class MovieController {

    /*

Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
Pass director’s name as request parameter
Return success message wrapped in a ResponseEntity object
Controller Name - deleteDirectorByName

Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
No params or body required
Return success message wrapped in a ResponseEntity object
Controller Name - deleteAllDirectors
(Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.) */

    @Autowired
    private MovieService movieService; 
    
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        String msg = this.movieService.addMovies(movie);
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director dir ){
        return ResponseEntity.ok(this.movieService.addDir(dir));
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movieName, @RequestParam String directorName ){
        return ResponseEntity.ok( this.movieService.addPair(movieName, directorName) );
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name ){
        Movie obj = null;
        try {
                obj = this.movieService.getMovie(name);
                return ResponseEntity.of( Optional.of( obj ));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name ){
        Director obj = null;
        try {
                obj = this.movieService.getDirector(name);
                return ResponseEntity.of( Optional.of( obj ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity< List< Movie > > getMoviesByDirectorName(@PathVariable("director") String name ){
        List<Movie> li = null;
        try {
            li = this.movieService.getMovieByDir(name);
            return ResponseEntity.of( Optional.of(li) );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity< List<Movie> > getAllMovies(){
        List<Movie> li = null;
        try {
            li = this.movieService.getAllMovieService();
            if( li.size() != 0 ){
                return ResponseEntity.of( Optional.of(li) );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String name ){

        return ResponseEntity.ok().body(this.movieService.deleteDirWork( name ) );
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        String msg = this.movieService.deleteEverythingRelatedtoPair();
        if( msg.equals( "success") ){
            return ResponseEntity.ok(msg);
        }
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
    }
    


}
