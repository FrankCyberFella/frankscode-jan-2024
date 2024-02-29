/*
    Example of a multi-line comment just like in CSS/C#/Java
*/

// Single line comment just like C#/Java

// semi-colons at the end of a statement are usually optional in JavaScript

//********************************************************************************************
// Call functions defined below to execute them  
//********************************************************************************************

// comment out code if you don't it to run
//variables();  // Call the function called variables() to execute its code

// Call the printParameters function with various types of parameters
/*
printParameters('Yoda', "Obi-Wan")
printParameters()
printParameters("Vito Corleone")
printParameters(1, 12345)
*/

// the /n character causes the display to go to a new line (used for output line spacing)

// console.log() - console is a keyword defined by JavaScript to represent the screen
//                           (its actually an object defined by JavaScript)
//                 .log() is a method defined in the console object0
//                           (a method is function associated with an object)
//
// Object-Oriented programming means we use objects to do our programming.
//
// In Object-Oriented Programming we code: objectName.methodName(parameters)
//
/*
console.log('\n----- compare a string value 1 to a numeric value 1 -----\n')
equality('1',1)  

console.log('\n----- compare two strings that sound the same -----\n')
equality('two', 'too') 

console.log('\n ----- compare a whole number value 1 to a decimal value 1 -----\n')
equality(1, 1.0);   

console.log('\n ----- call falsy with various values -----\n')

falsy("frank")
falsy(42)
falsy(42 / "frank")  // value sent is Nan - false
falsy(42 / 0)        // value sent is infinity - true
falsy(null)
falsy(3.14)
*/
console.log('\n ----- call objects function-----\n')
objects() // call the objects function

console.log('\n ----- process all elements in an array from start to end -----\n')

// Define an array of numbers
let numbers = [10, 30, 14.2, -76, 1776, 1.98, 32, -1492, 100]

// Process each element in the array
//   1. Display each element with it's position in the array
//   2. Determine the sum all the elements
//   3. Determine the average of all the elemets
//   4. Display the sum and average after all elements have been processed

// Define a variable to hold the sum of the elements
let sum = 0;

for(let i=0; i < numbers.length; i++) {
  console.log(`Element #${i} is: ${numbers[i]}`)
  sum = sum + numbers[i]  // add the current element value to sum
                          // sum += numbers[i] - shorthand to add to a variable
}
// We cannot determine the average until all elements have been processed (added to sum)
// So we calculate the average after the for loop processing is done
/*
let avg = sum / numbers.length // divide the sum by the number of elements in the array
console.log(`The sum is: ${sum} and the average is: ${avg}`)
*/
// Unless we need the average later on, there is no need to store it in a variable
console.log(`The sum is: ${sum} and the average is: ${sum / numbers.length}`)

// Alternate for-loop
//
// for(elementName of arrayName) - loop through the entire array 
//                                 giving each element the elementName
//                                 Use the elementName n the loop body 
//                                 to reference the current element
//                                 This for-loop does not make the index available

// Process each element in the array
//   1. Display each element 
//   2. Determine the sum all the elements
//   3. Determine the average of all the elemets
//   4. Display the sum and average after all elements have been processed

// set the sum variable back to 0 so we can reuse it
sum = 0;  // note: no let is coded as sum is already defined

for(aNumber of numbers) {
  console.log(`an element is: ${aNumber}`)
  sum = sum + aNumber
}
console.log(`The sum is: ${sum} and the average is: ${sum / numbers.length}`)

stringFunctions("Hello World, welcome to JavaScript");
stringFunctions("1,2,3,4,5,6,7,8,9");
stringFunctions('     Programming is fun!      ')

arrayFunctions();  // Call the function call arrayFunctions() with no parameters

console.log('\n\nThanks for using our app!\n' )

//********************************************************************************************
// End of main application processing
//********************************************************************************************
// Functions used to demonstrate various JavaScript features
// These must be called in order to execute 
//********************************************************************************************

/**
 * Illustrate various variable definition options in JavaScript
 */
function variables() {
  // name() - function call
  // word.name() - method call
  //
  // a method is function associated with an object
  //   an object a group of JavaScript variables
  //
  // console.log() is the JavaScript version of System.out.println() in Java
  // 
  // If what you want to display using console.log() contains a variable:
  //
  //      1. Enclose what you want to display in back-ticks (`)
  //      2. Put the variable you want displayed inside ${}
  //
  console.log('-'.repeat(50));             // display 50 '-' on the screen
  console.log('variable definition demo')  // display the words betweeb the ()
  console.log('-'.repeat(50));             // display 50 '-' on the screen

  // Declare a variable where the value cannot be changed (a constant)
  // By convention, constant names are all UPPERCASE with '_'
  const DAYS_IN_FEBRUARY = 29;  // const indicates a variable that cannot be changed

  // Display the value in the variable daysInFebruary
  console.log(`The value in daysInFebruary is: ${DAYS_IN_FEBRUARY}`)

  // try to change the value in the constant - generate a runtime error
  // DAYS_IN_FEBRUARY = 28

  // Declares a variable those value can be changed
  //
  // There are two ways to define variables
  //
  //    let variableName;   - safer and less prone to errors
  //    var variableName;   - less safe and can lead to errors 
  //                          or hard to understand code
  //
  //    let is the preferred way to define variables in modern JavaScript
  //
  let daysInYear = 366  // 2024 is a leap year
  console.log(`There are ${daysInYear} in 2024`);

  daysInYear = 365; // change the value in a variable
  console.log(`There are ${daysInYear} in 2025`);
 
  // Declares a variable that will always be an array
  // To declare an array in JavaScript code name = []

  let monthsOfYear = ["January",
                      "February",
                      "March",
                      "April",
                      "May",
                      "June",
                      "July",
                      "August",
                      "September",
                      "October",
                      "November",
                      "December"
                    ]
   console.log(`${monthsOfYear}`)
   console.table(monthsOfYear)    // Display an array in table format - note no `` or ${}
}  // end of the variables() function

/**
 * Functions can also accept parameters.
 * Notice the parameters do not have types.
 * 
 *  JavaDoc is a standard way of documenting code.
 *  JavaDoc is comments placed before a function to describe it
 *  annotations in JavDoc to de // falsescribes parts of a function
 * 
 *  @param - identifies parameters passed to the function in JavaDoc notation
 * 
 * @param {Number} param1 The first number to display
 * @param {Number} param2 The second number to display
 */
function printParameters(param1, param2) {
  console.log('-'.repeat(50));
  console.log('printParameter function - parameter example')
  console.log('-'.repeat(50));

  console.log(`The value of param1 is ${param1}`);
  console.log(`The value of param2 is ${param2}`);
}

/**
 * JavaScript has TWO types of equality
 * 
 * Compares two values x and y.
 * ==  is loose equality  - ignores the type of data - only looks at value
 * === is strict equality - requires both the type and value be the same
 * 
 * 1 ==  '1' - true  - values represents the value one
 * 1 === '1' - false - types don't match (Number and String)
 * 
 * @param {Object} x
 * @param {Object} y
 */
function equality(x, y) {
  console.log(`x is ${typeof x}`);  // typeof returns the type of the value it is given
  console.log(`y is ${typeof y}`);

  console.log(`x == y : ${x == y}`);  // Note: use of expression (x==y) in the console.log
  console.log(`x === y : ${x === y}`);
}

/**
 * Each value is inherently truthy or falsy.
 * 
 * Every value in JavaScript can be evaluated to true or false 
 * when used in a conditional statement
 * 
 * NaN - Not-a-Number - usually the result of arithmetic with non-numeric data
 * 
 * false, 0, '', null, undefined, and NaN are always falsy (false)
 * everything else is always truthy (true)
 * 
 * @param {Object} x The object to check for truthy or falsy,
 */
function falsy(x) {
  console.log('-'.repeat(50));
  console.log('falsy() to demonstrate truthy and falsey concept')
  console.log('-'.repeat(50));

  if (x) {  // Use whatever is passed a the condition in an if statement
    console.log(`${x} is truthy/true`);
  } else {
    console.log(`${x} is falsy/false`);
  }
}

/**
 *  Objects are simple key-value pairs (NOT instance of a class like in Java)
    JavaScript objects are JSON objects - JavaScriptObjectNotation

    an element in a JavaScript object: 
          attribute-name : value

    - values can be primitive data types
    - values can be arrays
    - or they can be functions
*/
function objects() {
  console.log('-'.repeat(50));
  console.log('objects() to demostrate Javascript objects')
  console.log('-'.repeat(50));

  // Define an object - a group of key/value pairs key : value
  let contactInfo  = {  // define an object called contact
                      firstName : "Frank",
                      lastName  : "Fella",
                      city: "Phoenix",
                      favorites : [ // start of an array in this object
                        {  // element in the array
                          sport: "football",
                          favoriteTeam: "Packers"
                        }, // end of array element
                        {
                          sport: "hockey",
                          favoriteTeam: "Chicago Blackhawks"
                        },
                        {
                          sport: "baseball",
                          favoriteTeam :"New York Yankees"
                        }
                      ], // end of the array in this object
                      // Define a method to be used with an object
                      // a function in a JavaScript object:  function-name : function {}
                      showNameCity : function() { 
                      // this.  references the current object 
                      return `${this.lastName}, ${this.firstName}, ${this.city}`
                      // `` are used to Stringify variables 
                    }
                  };                
                    

  // Log the entire  object
  console.log(contactInfo)  // because it is a group of values, no `` and no ${}
  console.table(contactInfo)

  // Log individual elements/properties of  the object
  // To access an individual element of an object: objectName.elementName

  console.log(`\nFirst name is: ${contactInfo.firstName}`)
  console.log(`\nFull name is: ${contactInfo.firstName} ${contactInfo.lastName}`)

  // call the function in the object & display what it returns
  console.log(contactInfo.showNameCity())  
                                           
   
  // Log each element of an array in an object
  
  for(let i=0; i < contactInfo.favorites; i++) {
    console.log(`${contactInfo.firstName} likes the sport: ${contactInfo.favorites[i].favoriteTeam}`)
  }
}


/*
########################
Function Overloading
########################

Function Overloading is not available in Javascript.

If you declare a function with the same name, more than one time in a script file,
the earlier ones are overriden/replaced and the most recent one will be used.
*/

function Add(num1, num2) {
  console.log('-'.repeat(50));
  console.log('Add(num1, num2) functio called')
  console.log('-'.repeat(50));
  return num1 + num2;
}
// This version of Add replaces the earlier version
function Add(num1, num2, num3) {
  console.log('-'.repeat(50));
  console.log('Add(num1, num2, num3) function called')
  console.log('-'.repeat(50));
  return num1 + num2 + num3;
}

/*
########################
Math Library
########################

A built-in `Math` object has properties and methods for mathematical constants and functions.
They are all called: Math.xxxxx

A link to a summary of all of them will be put in the class Google Doc
*/

function mathFunctions() {
  console.log('-'.repeat(50));
  console.log('mathFunctions() called to demonstrate common Math functions')
  console.log('-'.repeat(50));

  console.log("Math.PI : " + Math.PI);
  console.log("Math.LOG10E : " + Math.LOG10E);
  console.log("Math.abs(-10) : " + Math.abs(-10));
  console.log("Math.floor(1.99) : " + Math.floor(1.99));  // Round down to an integer value
  console.log("Math.ceil(1.01) : " + Math.ceil(1.01));    // Round up to an integer value
  console.log("Math.random() : " + Math.random());
}

/*
########################
String Methods
########################

The string data type has a lot of properties and methods similar to strings in Java/C#
*/

function stringFunctions(value) {
  console.log('-'.repeat(50));
  console.log('stringFunctions() called to demonstrate common string functions')
  console.log('-'.repeat(50));

  console.log(`\nValue passed was: ${value}\n`)

  console.log(`.length -  ${value.length}`);
  console.log(`.endsWith('World') - ${value.endsWith("World")}`);
  console.log(`.startsWith('Hello') - ${value.startsWith("Hello")}`);
  console.log(`.indexOf('Hello') - ${value.indexOf("Hello")}`);  // index of where the word starts in the string
  console.log(`.substr(2,3) - ${value.substr(2,3)}`)             // .substr(start-index, length)
  console.log(`.substring(2,3) - ${value.substring(2,3)}`)       // .substring(start-index, end-index)
                                                                 //     up to, but including the char at end-index
  console.log(`.toLowerCase() - ${value.toLowerCase()}`)         // Convert value to all lowercase
  console.log(`.toUpperCase() - ${value.toUpperCase()}`)         // convert value to all uppercase

  console.log(`.trim() \t- |${value.trim()}|`)                   // remove whitespace from both sides of a string
  console.log(`.trimStart() \t- |${value.trimStart()}|`)         // remove whitespace from start of a string
  console.log(`.trimEnd() \t- |${value.trimEnd()}|`)             // remove whitespace from end of a string
  
  // .split(delimiter) - split a string into an array of strings based on the delimiter
  console.log(`.split(' ')`) 
  let words = value.split(' ');
  console.table(words)

  // .split(delimiter) - split a string into an array of strings based on the delimiter
  console.log(`.split(',')`) 
  words = value.split(',');
  console.table(words)


  /*
    Other Methods
         - https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String
    */
}

 /*
  ########################
  Array Processing Examples
  ########################

  JavaScript has some great array processing functions
  */
  function arrayFunctions() {
  console.log('-'.repeat(50));             // display 50 '-' on the screen
  console.log('array functions demo')      // display the words betweeb the ()
  console.log('-'.repeat(50));             // display 50 '-' on the screen


    let stooges = ["Moe", "Larry", "Curly"]   // define an array
    
    console.table(stooges)       // display the array as a table

    console.log(`----- adding an elememnt to start of the array ane one to the end -----`)

    stooges.push("Shemp")        //  Add an element to end of the array
    stooges.unshift("Curly Joe") //  Add an element to start of the array
    console.table(stooges)

    console.log(`----- inserting new elements at element #3 -----`)

    stooges.splice(3,0,"Groucho", "Chico", "Harpo") // insert elements at element #3
                                                    // go to element #3 and don't remove anything
    console.table(stooges)

    console.log(`----- removing element #3 -----`)

    stooges.splice(3,1)  // go to element #3 and remove 1 element
    console.table(stooges)

    console.log(`----- removing two elements starting with #3 -----`)


    stooges.splice(3,2)  // go to element #3 and remove 2 elements
    console.table(stooges)

    console.log(`----- retrive the first element from the array and  remove it -----`)

    console.log(stooges.shift()) // shift returns the first element and removes it
    console.table(stooges)

    console.log(`----- retrieve the last element in the array and remove it -----`)

    console.log(stooges.pop())   // pop returns the last element and removes it
    console.table(stooges)

    console.log(`----- reverse the order of the elements in the array -----`)

    stooges.reverse();           // reverse the element order of the array
    console.table(stooges)

    console.log(`----- removing an element based on it's value ('Larry') -----`)

    stooges.splice(stooges.indexOf("Larry"),1) // Remove Larry (we don't know his position)
    console.table(stooges)
  
    console.log(`----- defining a new array -----`)

    let marxBrothers = ["Groucho", "Chico", "Harpo"]  // define new array
    console.table(marxBrothers);

    console.log(`----- concatenate (stick together) two arrays -----`)

    let oldFunnyGuys = stooges.concat(marxBrothers) // Concatenate two arrays
    console.table(oldFunnyGuys)
  
    /* Many other array processig functions exist and may be researched at:

      https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array

    */


    console.log(`That's all folks!`)
}
 
