// Define the modules and other component or features used by the component
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-learner-list',  // tag used in html to reference the component
  standalone: true,  // added in Angular 17 to make data binding [ngModel] and routing easier
  imports: [CommonModule],  // external feature you use in the component
  templateUrl: './learner-list.component.html',
  styleUrl: './learner-list.component.css'
})
// Defines the module for the component 
//     and makes it available externally
// Where data and methods for the component are defined
export class LearnerListComponent {
  // Define the list of Learner names we want to display
  // For today it will be coded as literals
  // Typically the data will come from a service

  // public says the data is accessible by anyone with access to module
  // private says the data is accesible only by methods in the component
  public learnerNames = ["Bertram", "Elena", "Guillermo", "Jakob", "Kris"]

}
