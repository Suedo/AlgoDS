// return true if 'this' is prefix of the 'other' string 
String.prototype.isPrefixOf = function (other) {
  return other.indexOf(this) === 0
}

const allConstruct = (givenWord, chunkBank, memo = {}) => {

  // base cases
  if (givenWord === "") return [[]]  // positive basecase, str can be constructed
  let applicablePrefixes = chunkBank.filter(each => each.isPrefixOf(givenWord))
  if (applicablePrefixes.length === 0) return null; //  negative basecase, cannot be constructed
  // memo
  if (givenWord in memo) return memo[givenWord];

  console.log(`start>>> ${givenWord}, memo: ${JSON.stringify(memo)}, prefixes: ${applicablePrefixes}`);

  let result = [];
  // start work for this recursion step
  for (const prefix of applicablePrefixes) {
    // branch out
    console.log(`\tprefix: ${prefix}`);
    let remainderStr = givenWord.slice(prefix.length);
    let r = allConstruct(remainderStr, chunkBank, memo)
    console.log(`\tr: ${r}, ${r instanceof Array}`);
    if (r) { // r is not null
      result.push(// add result from each branch
        r.map(each => [prefix, ...each].join(",")) // combine branch result with current
      );
    }


  }

  if (result.length === 0) result = null; // this step recursed, and found nothing, so return same as negative basecase
  else console.log(`given: ${givenWord}, result: ${result}`);
  // return final result value
  memo[givenWord] = result
  console.log(`end<<< ${givenWord}, memo: ${JSON.stringify(memo)}`);
  return memo[givenWord];
}


console.log(allConstruct("purple", ["purp", "p", "ur", "le", "purpl"]));