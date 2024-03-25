/***************************************************************
 This is service

 A service is a set of processing to facilitate the retrieval
 and storage of data - typically in a persistent source (saved)

 May do other processing as well

 This service DOES not save the data to a persitent source

 The data from this service is just an array in memory

*******************************************************************/

import { Injectable}   from '@angular/core'; // Access Angular Dependency Injection
import { MoviesInfo }  from './moviesInfo';  // Using the MoviesInfo interface

// @Injectable tells Angular that this service may be Dependency Injection
@Injectable({
  providedIn: 'root'
})

export class MoviesService {

  // A module contains data and methods/functions for processin that daat

  // Source of data for calls to retrieve the data - initialized in the code
  private listOfMovies : MoviesInfo[] = []  // An array of MovieInfo objects

  // a contructor is method to initialized data defined in the module
  // it is executed automatically when the service is loaded
  // Here we use the constructor to initialize our data source
  constructor() { 
    this.listOfMovies.push({title: "Godfather"                         , releaseYear: 1972, director: "Francis Ford Coppola"})
    this.listOfMovies.push({title: "Godfather II"                      , releaseYear: 1974, director: "Francis Ford Coppola"})
    this.listOfMovies.push({title: "Star Trek: The Wrath of Khan"      , releaseYear: 1982, director: "Nicholas Meyer"})
    this.listOfMovies.push({title: "Star Wars: The Empire Strikes Back", releaseYear: 1980, director: "Irvin Kershner"})
  }

  // methods others may use to interact with our service

  // This method will return the current data in our data source (listOfMovies)
  //  name(parameters) : return-type-of-data-returned
      getMoviesList()  : MoviesInfo[] {  // this function returns a MoviesInfo array
        return this.listOfMovies;
      }
  // This method will receive a MoviesInfo object and add it to our data source (listOfMovies)
      addMovies(newMovie : MoviesInfo) {      
        console.table(newMovie)          // optional - verify new movie data
        this.listOfMovies.push(newMovie) // store the movie in the data source
        console.table(this.listOfMovies) // optional - verify new movie was added to the array
      }
    
}
