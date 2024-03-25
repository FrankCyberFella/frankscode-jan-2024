import { Injectable, NgModule } from '@angular/core';
import { MoviesInfo } from './moviesInfo';
import { NgModel } from '@angular/forms';
import { MovieListComponent } from './movie-list/movie-list.component';


@Injectable({
  providedIn: 'root'
})



export class MoviesService {

  private listOfMovies : MoviesInfo[] = []

  constructor() { 
    this.listOfMovies.push({title: "Godfather"                         , releaseYear: 1972, director: "Francis Ford Coppola"})
    this.listOfMovies.push({title: "Godfather II"                      , releaseYear: 1974, director: "Francis Ford Coppola"})
    this.listOfMovies.push({title: "Star Trek: The Wrath of Khan"      , releaseYear: 1982, director: "Nicholas Meyer"})
    this.listOfMovies.push({title: "Star Wars: The Empire Strikes Back", releaseYear: 1980, director: "Irvin Kershner"})
  }

  getMoviesList() : MoviesInfo[] {
    return this.listOfMovies;
  }
  addMovies(newMovie : MoviesInfo) {
    console.log(`in addMovies in movies.service.ts`)
    console.table(newMovie)
    this.listOfMovies.push(newMovie)
    console.table(this.listOfMovies)
  }
 
}
