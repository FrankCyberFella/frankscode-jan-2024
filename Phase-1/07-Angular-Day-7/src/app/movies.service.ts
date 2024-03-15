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

  private titleSortAsc    : boolean = true;
  private dateSortAsc     : boolean = true;
  private directorSortAsc : boolean = true;

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


  // Sort List of movies by title
     sortByTitle() { 
        this.titleSortAsc ? this.listOfMovies.sort(this.compareTitleAsc)
                          : this.listOfMovies.sort(this.compareTitleDesc)

        this.titleSortAsc = !this.titleSortAsc                  
     }
 
  // Sort List of movies by director
    sortByDirector() { 
        this.directorSortAsc ? this.listOfMovies.sort(this.compareDirectorAsc)
                             : this.listOfMovies.sort(this.compareDirectorDesc);

        this.directorSortAsc = !this.directorSortAsc;                     
  }

    // Sort List of movies by director
    sortByReleaseYear() { 
        this.dateSortAsc ? this.listOfMovies.sort(this.compareReleaseYearAsc)
                         : this.listOfMovies.sort(this.compareReleaseYearDesc);

        this.dateSortAsc = !this.dateSortAsc                 
} 

     compareTitleAsc(a : MoviesInfo, b : MoviesInfo) {
        if (a.title < b.title) return -1;
        if (a.title > b.title) return 1;
        return 0;
    }  

    compareTitleDesc(a : MoviesInfo, b : MoviesInfo) {
      if (a.title > b.title) return -1;
      if (a.title < b.title) return 1;
      return 0;
  }  

    compareReleaseYearAsc(a : MoviesInfo, b : MoviesInfo) {
      if (a.releaseYear < b.releaseYear) return -1;
      if (a.releaseYear > b.releaseYear) return 1;
      return 0;
    }  

    compareReleaseYearDesc(a : MoviesInfo, b : MoviesInfo) {
      if (a.releaseYear > b.releaseYear) return -1;
      if (a.releaseYear < b.releaseYear) return 1;
      return 0;
    } 

    compareDirectorAsc(a : MoviesInfo, b : MoviesInfo) {
      if (a.director < b.director) return -1;
      if (a.director > b.director) return 1;
      return 0;
    }  
    compareDirectorDesc(a : MoviesInfo, b : MoviesInfo) {
      if (a.director > b.director) return -1;
      if (a.director < b.director) return 1;
      return 0;
    }  
}
