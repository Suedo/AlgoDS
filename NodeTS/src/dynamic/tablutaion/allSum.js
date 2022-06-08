// all possible ways to get target as a sum of any numbers from the array (duplication allowed)
const allSum = (targetSum, numbers) => {

  const table = new Array(targetSum + 1).fill()
  table[0] = []; // seed value, we can always get a target of zero, by simply not choosing anything from the given array
  console.log(table);

  for (let i = 0; i < table.length; i++) {

    if (table[i]) { // only if this is either basecase, or reachable from basecase
      console.log("\n>>> " + printTable(table));

      for (let j = 0; j < numbers.length; j++) { // calculate each forward step/jump
        let eachNumber = numbers[j];
        let newTablePosition = i + eachNumber; // going forward 'number' steps in the table

        // calcualte this step
        let result; // result alwats [[]] format
        if (table[i].length === 0) result = [[eachNumber]]  // basecase [] empty array
        else result = table[i].map(eachSumCombo => [...eachSumCombo, eachNumber])

        // populate calculated result in table
        if (newTablePosition < table.length) { // stay in bounds
          console.log(`i: ${i}, j: ${j}, result: ${JSON.stringify(result)}, eachNumber: ${eachNumber}, newTablePosition: ${newTablePosition}`);

          if (table[newTablePosition]) table[newTablePosition] = [...table[newTablePosition], ...result]
          else table[newTablePosition] = result // always in [[ ... ]] format
        }
      }
    }
  }

  return table[targetSum];

}

const printTable = table => table.map(each => each ? JSON.stringify(each) : "null").join(" - ")

// console.log(allSum(7, [5, 3, 4]));
console.log(JSON.stringify(allSum(8, [2, 4, 5])));