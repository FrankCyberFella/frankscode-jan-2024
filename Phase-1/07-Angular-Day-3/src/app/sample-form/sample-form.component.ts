import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'

@Component({
  selector: 'sample-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './sample-form.component.html',
  styleUrl: './sample-form.component.css'
})
export class SampleFormComponent {
  
    onSubmit() {
      console.log(`-----------------------------------------------`)
      console.log(` First name: ${this.fname}`)
      console.log(`  Last name: ${this.lname}`)
      console.log(` Birth Date: ${this.birthDate}`)
      console.log(` Cuisine(s): ${this.foodPrefs}`)
      console.log(`      email: ${this.email}`)
      console.log(`allow email: ${this.email_preference}`)
    }

      public fname = "";
      public lname = "";
      public birthDate = "";
      public foodPrefs = "";
      public email = "";
      public email_preference = "";

}
