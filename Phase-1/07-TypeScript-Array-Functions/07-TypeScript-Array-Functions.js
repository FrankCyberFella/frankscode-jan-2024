/**
 * Create a string from the parameters and elements in an array passed as arguments
 *
 * .join() will create a string from an array with separater specified
 *               (or , if no separators)
 *
 * @param {*} name
 * @param {*} age
 * @param {*} listOfQuirks
 * @param {*} separator
 * @returns
 */
//                                         receive an array
function createSentenceFromUser(name, age, listOfQuirks, separator) {
    if (listOfQuirks === void 0) { listOfQuirks = []; }
    if (separator === void 0) { separator = ', '; }
    var description = "".concat(name, " is currently ").concat(age, " years old. Their quirks are: ");
    return description + listOfQuirks.join(separator); // Create a string from listOfQuirks                                                      // separator specified
}

/**
 * Takes an array and, using an arrow function, generates their sum.
 *
 * Convert the values in an to a sum
 * Produce the sum of all the values in teh array
 * Reduce the array to the sum of the values
 *
 * A single value out the array - This is job for reduce()!
 *
 * .reduce() - takes an arrow-func which receives two parameters
 *             receives a parameter to hold the result of each anon-func call (reducer)
 *                      a parameter that is the current element in the array
 *
 * general syntax: array.reduce(anon-func)
 *
 * more specific syntax: array.reduce((reducer, anElem) => { })
 *
 * @param {number[]} numbersToSum numbers to add up
 * @returns {number} sum of all the numbers
 */
function sumAllNumbers(numbersToSum) {
    // in this arrow-func we will call the reducer sum since it will hold the sum of the array elems
    //                    we will call the current element of the array aNumber
    // when .reduce() is done, it returns the final value in reducer (sum)
    //      which we will return from this function
    return numbersToSum.reduce(function (sum, aNumber) {
        sum += aNumber; // add the current elem to sum
        return sum;
    });
    console.log('---- .reduce examples ----');
    console.log(numbersToSum(10, 20, 30));
    /* alternate solutions
        return numbersToSum.reduce((sum, aNumber) => { return sum += aNumber; });
    */
    /* to accomplish the same thing with a for-loop
          let sum = 0;
          for(let i=0; i < numbersToSum.length; i++){
            sum += numbersToSum[i]
          }
          return sum;
    */
}
/**
 * Takes an array and returns a new array of only numbers that are
 * multiples of 3
 *
 * Create a new array from selected elements in another array
 * (this is what .filter() does!)
 *
 * .filter() uses an anon-func that takes the current element as a parameter
 * the arrow-func determines if the element it is sent meets the conditions to be included in new array
 *     it returns true if it does, false it it doesn't
 *
 * .filter() will add the current element to the new array if the anon-func returns true
 *
 * when all elements in the array are processed, .filter() returns the new array
 *
 * @param {number[]} numbersToFilter numbers to filter through
 * @returns {number[]} a new array with only those numbers that are
 *   multiples of 3
 */
function allDivisibleByThree(numbersToFilter) {
    return numbersToFilter.filter(function (currElem) {
        if (currElem % 3 == 0) { // is currElem a multiple of 3
            return true; // it should be in new array
        }
        return false; // if not a multiple of 3 return false
    });
    /* alternate solutions
      return numbersToFilter.filter((currElem) => {return currElem % 3 == 0)})
    
      return numbersToFilter.filter((currElem) => {return !currElem % 3})  // Uses truthy/falsy concept
                                                              3     % 3
                                                                   0 - false
                                                                  !0 - true
                                                              7     % 3                                                     3     % 3
                                                                   1 - true
                                                                  !1 - false
    */
}
/**
 *
 *  A Create a new array containing the squares of the corresponding elements in an array
 *
 *  Create a new array from all the elements in another array
 *  (.map() does this)
 *
 *
 * The map function will return an array created from the elements passed to it
 *
 * @param - None
 * @returns {array created from values passed to it}
 */
function mapArrayFunctionExample() {
    var numbersToSquare = [1, 2, 3, 4];
    console.log("Array calling map to create new array with values squared: ");
    console.table(numbersToSquare);
    /*
       .map() takes an arrow-func that receives the current element
       the arrow-func performs a process on the current element and returns it
       .map() adds the value returned from teh anon-func to the new array
    */
    var squaredNumbers = numbersToSquare.map(function (number) {
        return number * number; // square the current element and return it
    });
    console.log("Array returned from map with values in passed array squared: ");
    console.table(squaredNumbers);
}
/**
  * The forEach() is the JavaScript/TypeScript version of the for-each loop in Java
 *
 * @param - An array generated as a parameter
 * @returns {array created from values passed to it}
 */
function forEachExample(anArray) {
    if (anArray === void 0) { anArray = ["John", "Alex", "Jared", "Agnes", "Brian", "Josh", "Nia",
        "JC", "Daniel", "Amber", "Dana", "Jess", "Vanese", "Ruben",
        "Lindsay", "Anthony", "Lorenzo"]; }
    // forEach() is the JavaScript version of the for-each look in Java
    anArray.forEach(function (anElement) {
        console.log(anElement);
    });
    anArray.sort(); // sort function sorts an array and replace original
    console.table(anArray);
    anArray.reverse(); // reverse elements in an array and replace original
    console.table(anArray);
}
