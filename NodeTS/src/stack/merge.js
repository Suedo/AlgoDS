// -------------------------------------------------------------------
// Merge two arrays
// -------------------------------------------------------------------
// descending order merge
const mergeArr = (arr1, arr2) => {
  let result = new Array(arr1.length + arr2.length), idx1 = 0, idx2 = 0;

  for (let idxR = 0; idxR < result.length; idxR++) {
    if (idx1 >= arr1.length) result[idxR] = arr2[idx2++] // arr1 done, merge from arr2
    else if (idx2 >= arr2.length) result[idxR] = arr1[idx1++] // arr2 done, merge from arr1

    // we want highest number from merge, so descending order
    else if (arr1[idx1] >= arr2[idx2]) result[idxR] = arr1[idx1++]
    else result[idxR] = arr2[idx2++]
  }

  console.log(`received: [${arr1}] and [${arr2}], Merged: [${result}]`);
  return result;
}

// -------------------------------------------------------------------
// Merge two sequences keeping relative order
// -------------------------------------------------------------------

// is seqA greater than seqB
const isGreater = (seqA, seqB) => {

  let idxA = 0, idxB = 0;
  while (idxA < seqA.length || idxB < seqB.length) {
    if (idxB >= seqB.length) return true; // seqB over, but seqA still going
    if (idxA >= seqA.length) return false; // seqA over, but seqB still going
    if (seqA[idxA] > seqB[idxB]) return true;
    if (seqA[idxA] < seqB[idxB]) return false;
    idxA++, idxB++; // when both equal, simply continue to next
  }
}

const mergeSeq = (arr1, arr2) => {
  let result = new Array(arr1.length + arr2.length), idx1 = 0, idx2 = 0;

  for (let idxR = 0; idxR < result.length; idxR++) {
    if (idx1 >= arr1.length) result[idxR] = arr2[idx2++] // arr1 done, merge from arr2
    else if (idx2 >= arr2.length) result[idxR] = arr1[idx1++] // arr2 done, merge from arr1

    // we want highest number from merge, so descending order
    else if (arr1[idx1] > arr2[idx2]) result[idxR] = arr1[idx1++]
    else if (arr1[idx1] < arr2[idx2]) result[idxR] = arr2[idx2++]
    else if (isGreater(arr1.slice(idx1), arr2.slice(idx2))) result[idxR] = arr1[idx1++]
    else result[idxR] = arr2[idx2++]
  }

  console.log(`received: [${arr1}] and [${arr2}], Merged: [${result}]`);
  return result;
}



  // allPossibleK.sort((a, b) => +(b.join("")) - +(a.join(""))) // desc order sort
