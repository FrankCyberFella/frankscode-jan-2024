import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SampleFormComponent } from './sample-form/sample-form.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SampleFormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  pageTitle = 'Welcome to FSD - Cohort-1-2024 Angular App';
}
