const sequenceToDAG = (arr) => {
  let idxPos = {}
  for (let i = 0; i < arr.length; i++) {
    const element = arr[i];
    if (idxPos[element]) idxPos[element].push(i)
    else idxPos[element] = [i];
  }

  let sortedCopy = [...arr].sort()

  console.log(`original array: ${arr}`);
  console.log(`idxPos:`);
  console.log(idxPos);
  console.log(`sortedCopy: ${sortedCopy}\n`);


  // fer each elem in sorted arr:
  // find from sortedCopy all elems larger than it
  // then filter elems whose idxPos is greater than this one
  // create an index position based adjacency list
  // index_this => [... filtered elem indexes ]
  // substitute indexes for values if needed

  let adjList = {}

  for (let i = 0; i < sortedCopy.length; i++) {
    const elem = sortedCopy[i];
    const allLargerElems = sortedCopy.slice(i + 1)
    const elemOriginalPos = idxPos[elem];

    console.log(`i:${i}, elem:${elem} elemOriginalPos:${elemOriginalPos}, allLargerElems: ${allLargerElems}`);

    // elemOriginalPos may have more than one entry
    for (let j = 0; j < elemOriginalPos.length; j++) {
      const thisIdx = elemOriginalPos[j];

      // find from allLargerElems which all have greate index position 
      // to signify they fall to the right of this number in the actual array
      // note: to indicate duplicates in original arr, idxPos has array of indexes
      // so, we must remove duplicates from "allLargerElems" before mapping idxPos from it
      let rightElems = allLargerElems.flatMap(e => idxPos[e]); // we use flatMap because idxPos[e] already is an Array, using only map will give [[...]]
      let rightElemsIdxes = [... new Set(rightElems)].filter(pos => pos > thisIdx)


      // there can be edges from thisIdx to all elems in rightElemsIdxes
      adjList[arr[thisIdx]] = rightElemsIdxes.map(idx => arr[idx])
      console.log(`\t ${arr[thisIdx]} : ${JSON.stringify(adjList[arr[thisIdx]])} :: rightElems: ${JSON.stringify(rightElems)} rightElemsIdxes: ${JSON.stringify(rightElemsIdxes)}`);

    }

  }
  return adjList
}


let arr = [5, 2, 8, 6, 3, 6, 9, 7]
let adjList = sequenceToDAG(arr)
console.log(adjList);

module.exports = sequenceToDAG

