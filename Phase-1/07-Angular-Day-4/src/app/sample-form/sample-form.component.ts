import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
// FormsModule is required if the component uses html forms 
import { FormsModule } from '@angular/forms'

// @Component includes the Component in the imports array metadata
@Component({
  selector: 'sample-form', // this is the tag used inany page to include this component
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './sample-form.component.html',
  styleUrl: './sample-form.component.css'
})
// class defines module containing any data and processing for the component
// export allow outside processes to access this module
export class SampleFormComponent {
  
    // Data used by the component
    // public this variable may be accessed by anything with access to the module
    // public (Access Modifer) is used to define data in a module instead of let/var
    // private is another Access modifier that limits access to code in this module
    // public allows Angular to access the data

    // These variable are connect to input controls in a form via [(NgModel)]
    public fname = "";
    public lname = "";
    public birthDate = "";
    public foodPrefs = "";
    public email = "";
    public email_preference = "";

    checkboxes: any[] = [
      { name: 'American Football', value: 'Football' },
      { name: 'European Football', value: 'Soccer' },
      { name: 'Cricket', value: 'Cricket' },
      { name: 'Baseball', value: 'Baseball' },
      { name: 'Basketball', value: 'Basketball' },
      { name: 'Other', value: 'Other Sport' },
      { name: 'None', value: 'None' }
    ];

    // a method to process any data accessible to the component
    // this. tells Angular the data is in this component
    onSubmit() {
      console.log(`-----------------------------------------------`)
      console.log(` First name: ${this.fname}`)
      console.log(`  Last name: ${this.lname}`)
      console.log(` Birth Date: ${this.birthDate}`)
      console.log(` Cuisine(s): ${this.foodPrefs}`)
      console.log(`      email: ${this.email}`)
      console.log(`allow email: ${this.email_preference}`)
      console.log(this.checkBoxResult)
    }
    
      CheckAllOptions() {
        if (this.checkboxes.every((val) => val.checked == true))
          this.checkboxes.forEach((val) => {
            val.checked = false;
          });
        else
          this.checkboxes.forEach((val) => {
            val.checked = true;
          });
      }
    
      get checkBoxResult() {
        return this.checkboxes.filter((item) => item.checked);
      }

}
