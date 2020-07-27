export const rank = (arr: number[], item: number): number => {
  let lo = 0,
    hi = arr.length;

  while (lo <= hi) {
    let mid = Math.floor((lo + hi) / 2);
    if (item < arr[mid]) hi = mid - 1;
    else if (item > arr[mid]) lo = mid + 1;
    else return mid;
  }
  return -1; // element not found;
};
