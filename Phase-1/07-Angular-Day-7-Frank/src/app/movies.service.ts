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

  // These variable keep track if we should sort in ascending order or not
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
        // ! is the not operator - flips true to false and vice versa
        this.titleSortAsc = !this.titleSortAsc // reverse the sort ascending variable                  
     }
 
  // Sort List of movies by director
  // .sort() method will sort an array
  // it will walk through the array multiple times comparing two elements
  // it requires a callback method to tell how the two elements relate (GT, EQ, LT)
  // a "compare function" or "comparator" to tell it how elements related
  // the compare function returns 0 if they are equal, 
  //                      less than 0 if first is less than second
  //                      greater than 0 if the first is greater the second
  //
  // Note: Use of conditional operator ?
  //
  //       Conditional operator is a shorthand if-else
  //       
  //       condition ? process-if-true : process-if-false
  //
  // Primary use is to allow conditional processing when an if-else cannot be coded
  // ie. when conditional parameters are required by a method
  //
  // in the example below:
  //
  //   if (this.directorSortAsc === true) {
  //                                       this.listOfMovies.sort(this.compareDirectorAsc)
  //                                      }
  //   else {
  //         this.listOfMovies.sort(this.compareDirectorDesc)
  //        }

    sortByDirector() { 
        this.directorSortAsc ? this.listOfMovies.sort(this.compareDirectorAsc)
                             : this.listOfMovies.sort(this.compareDirectorDesc);

        this.directorSortAsc = !this.directorSortAsc; // reverse the sort ascending variable                     
  }

    // Sort List of movies by director
    sortByReleaseYear() { 
        this.dateSortAsc ? this.listOfMovies.sort(this.compareReleaseYearAsc)
                         : this.listOfMovies.sort(this.compareReleaseYearDesc);

        this.dateSortAsc = !this.dateSortAsc // reverse the sort ascending variable                 
} 
// this is a compare function used to sort 
// it receives two MoviesInfo objects
     compareTitleAsc(a : MoviesInfo, b : MoviesInfo) {
        if (a.title < b.title) return -1;  // first is less than second, return a negative
        if (a.title > b.title) return 1;   // first is greater than second, return a positive
        return 0;                          // otherwise 0 since they are equal
    }  

     compareTitleDesc(a : MoviesInfo, b : MoviesInfo) {
       if (a.title > b.title) return -1; // first is greater than second, return a negative
       if (a.title < b.title) return 1;  // first is less than second, return a positive
      return 0;                          // otherwise 0 since they are equal
   }  

    compareReleaseYearAsc(a : MoviesInfo, b : MoviesInfo) {
      if (a.releaseYear < b.releaseYear) return -1;
      if (a.releaseYear > b.releaseYear) return 1;
      return 0;
      // Alternate code: return a - b // if a > b result is positive, if < b result is negative
    }  

    compareReleaseYearDesc(a : MoviesInfo, b : MoviesInfo) {
      if (a.releaseYear > b.releaseYear) return -1;
      if (a.releaseYear < b.releaseYear) return 1;
      return 0;
      // Alternate code: return b - a // if b > a result is positive, if < a result is negative
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
