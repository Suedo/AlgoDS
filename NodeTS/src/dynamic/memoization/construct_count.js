// return true if 'this' is prefix of the 'other' string 
String.prototype.isPrefixOf = function (other) {
  return other.indexOf(this) === 0
}

const canConstruct = (givenWord, chunkBank, count = 0, memo = {}) => {

  console.log(`start>>> ${givenWord}, memo: ${JSON.stringify(memo)}`);

  if (givenWord === "") {
    count = count + 1
    return [count, true]
  } // positive basecase, str can be constructed

  let applicablePrefixes = chunkBank.filter(each => each.isPrefixOf(givenWord))
  if (applicablePrefixes.length === 0) return [0, false]; // cannot be constructed

  if (givenWord in memo) return memo[givenWord];
  // "abcd".slice("ab".length) == "cd"

  let result = false;
  let c = 0;
  // start work for this recursion step
  for (const prefix of applicablePrefixes) {
    // branch out
    let remainderStr = givenWord.slice(prefix.length);
    let [subCount, r] = canConstruct(remainderStr, chunkBank, count, memo)

    // combine result from each branch
    result = result || r;
    c = c + subCount;
  }

  // return final result value
  memo[givenWord] = [c, result];
  console.log(`end<<< ${givenWord}, memo: ${JSON.stringify(memo)}`);
  return memo[givenWord];
}


console.log(canConstruct("purple", ["purp", "p", "ur", "le", "purpl"]));