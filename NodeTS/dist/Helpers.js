export const rank = (arr, item) => {
    let lo = 0, hi = arr.length - 1, mid = 0;
    while (lo <= hi) {
        // show(lo, hi, arr);
        mid = Math.floor((lo + hi) / 2);
        if (item < arr[mid])
            hi = mid - 1;
        else if (item > arr[mid])
            lo = mid + 1;
        else
            return [mid, mid];
    }
    return [undefined, mid]; // element not found;
};
/**
 * Shows a subarray of given array between indices [lo, hi], both inclusive
 * @param lo  start of subarray
 * @param hi  end of subarray
 * @param arr array whose subarray to print
 */
export const show = (lo, hi, arr) => {
    const mid = Math.floor((lo + hi) / 2);
    console.log(`${lo}, ${mid}, ${hi} | ${arr.filter((val, idx) => lo <= idx && idx <= hi)}`);
};
/**
 * Find the floor, ie, biggest value smaller than provided input
 * @param arr array to search
 * @param elem elem whose floor value to find
 */
export const floor = (arr, elem) => {
    // base cases
    if (elem <= arr[0])
        return undefined;
    if (elem > arr[arr.length - 1])
        return arr[arr.length - 1];
    const [rankofelem, lastCheckedIdx] = rank(arr, elem);
    const lastChecked = arr[lastCheckedIdx];
    console.log("> ", rankofelem, lastCheckedIdx, `$`, elem);
    if (lastChecked < elem)
        return lastChecked;
    else
        return arr[lastCheckedIdx - 1];
};
/**
 * Find the ceiling, ie, next biggest value
 * @param arr array to search
 * @param elem elem whose ceiling value to find
 */
export const ceil = (arr, elem) => {
    const len = arr.length;
    if (elem >= arr[len - 1])
        return undefined;
    if (elem < arr[0])
        return arr[0];
    const [rankofelem, lastCheckedIdx] = rank(arr, elem);
    const lastChecked = arr[lastCheckedIdx];
    console.log("> ", rankofelem, lastCheckedIdx, `$`, elem);
    if (lastChecked > elem)
        return lastChecked;
    else
        return arr[lastCheckedIdx + 1];
};
/**
 * generates an array with the specified range values
 * @param start range start
 * @param stop range stop (inclusive)
 * @param inc increments
 */
export const rangeArray = (start, stop, inc) => {
    const arr = [];
    for (const elem of range(start, stop, inc)) {
        arr.push(elem);
    }
    return arr;
};
/**
 * A range function that yeilds numbers from `start` to `stop` (inclusive) with increments of `inc`
 * @param start range start
 * @param stop range stop (inclusive)
 * @param inc increments
 */
export const range = (start, stop, inc) => {
    return {
        *[Symbol.iterator]() {
            for (let i = start; i <= stop; i = i + inc) {
                yield i;
            }
        },
    };
};
// const arr = rangeArray(0, 100, 5);
// console.log(floor(arr, 12));
// console.log(floor(arr, 2));
// console.log(floor(arr, 0));
// console.log(floor(arr, 100));
// console.log(floor(arr, 32));
// console.log(floor(arr, 25));
// console.log(floor(arr, -1));
// console.log(floor(arr, 102));
// console.log(ceil(arr, 12));
// console.log(ceil(arr, 2));
// console.log(ceil(arr, 0));
// console.log(ceil(arr, 100));
// console.log(ceil(arr, 32));
// console.log(ceil(arr, 25));
// console.log(ceil(arr, -1));
// console.log(ceil(arr, 102));
