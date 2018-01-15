const readline = require('readline');

function readInput() {
    return new Promise((resolve, reject) => {
        let numbers = [];
        
        let rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
     
        rl.on('line', (line) => {
            numbers.push(line);
        });
        
        rl.on('close', function () {
            resolve(numbers);
        });
    });
}

function main() {
    readInput().then((numbers) => {
        let result = "";
        numbers.splice(0, 1);
        numbers = numbers[0].split(" ");
        numbers.sort(sortByStrings);
        for (let i = numbers.length - 1; i >= 0; i--) {
            result = result + numbers[i];
        }
        console.log(result);
    });
}

function sortByStrings(firstString, secondString) {
    if (parseInt(firstString + secondString) < parseInt(secondString + firstString)) {
        return -1;
    } else if (parseInt(firstString + secondString) > parseInt(secondString + firstString)) {
        return 1;
    } else {
        return 0;
    }
}

main();