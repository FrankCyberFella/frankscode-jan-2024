// This starts the angular application
// it is automatically run during the ng serve processing
// This is where you specify the "home" or starting component for the app
import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

// Start the Angular app with the AppComponent and appConfig
bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));
