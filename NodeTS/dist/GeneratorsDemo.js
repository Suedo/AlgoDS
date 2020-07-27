"use strict";
class IteratorOptions {
    constructor() {
        this.start = 0;
        this.stop = 100;
        this.inc = 1;
    }
}
let numbers = {
    // this is a generator, the for loop will call it and get an iterator
    *[Symbol.iterator](params = {}) {
        const { start = 0, stop = 100, inc = 1 } = params;
        for (let i = start; i <= stop; i = i + inc) {
            yield i;
        }
    },
};
// the for loop needs an iterator, and generators give us iterators
for (const num of numbers) {
    console.log(num);
}
let params = {
    start: 6,
    stop: 30,
    inc: 4,
};
for (const num of numbers[Symbol.iterator](params)) {
    console.log(num);
}
