import { readFile } from "fs";
console.log("reading a file");
// writeFile("./yo.js", "hello", (err) => {
//   if (err) throw err;
//   console.log("data wriiten");
// });
readFile("./files/ip.txt", (err, data) => {
    if (err)
        throw err;
    console.log(data.toLocaleString(), "\nSomjit");
});
