import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MoviesInfo } from '../moviesInfo';
import { MoviesService } from '../movies.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './movie-list.component.html',
  styleUrl: './movie-list.component.css'
})
export class MovieListComponent {

public moviesList : MoviesInfo [] ;

constructor(private movieService  : MoviesService ) {
  this.moviesList = movieService.getMoviesList();
}
}
