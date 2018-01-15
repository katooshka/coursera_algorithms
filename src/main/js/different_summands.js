//TODO: () => {} vs function() {}. Тупое поведение this у function
//TODO: StringBuilder в java и как такое сделать в js
//TODO: след задачку с async await
//TODO: map() и join() функции ма

const readline = require('readline');

function readInput() {
    return new Promise((resolve, reject) => {
        let number;
 
        let rl = readline.createInterface({
        
            input: process.stdin,
            output: process.stdout
        });
     
        rl.on('line', (line) => {
            number = line;
        });
        
        rl.on('close', function () {
            resolve(number);
        });
    });
}
 
function main() {
    readInput().then((number) => {        
        const initialNumber = number;
        let count = 1;
        let currentDeduction = 1;
        let deductions = [];
        let sum = number;
        while (number > 2 * currentDeduction) {
            count++;
            sum -= currentDeduction;
            number -= currentDeduction;
            deductions.push(currentDeduction);
            currentDeduction++;
        }
        deductions.push(sum);
        console.log(count);
        let currentDeductionString = ""; // const currentDeductionString = deductions.map((num) => String(num)).join(' ');
        for (let i = 0; i < deductions.length; i++) {
            currentDeductionString += deductions[i] + " ";
        }
        console.log(currentDeductionString);
    });
};

// async - значит все что ты вернешь как return x будет превращено в промис
async function main() {
    const number = await readInput(); // и внутри такой функции можно "ждать" пока промис не разрешится, почти как джаве.
         
    const initialNumber = number;
    let count = 1;
    let currentDeduction = 1;
    let deductions = [];
    let sum = number;
    while (number > 2 * currentDeduction) {
        count++;
        sum -= currentDeduction;
        number -= currentDeduction;
        deductions.push(currentDeduction);
        currentDeduction++;
    }
    deductions.push(sum);
    console.log(count);
    let currentDeductionString = ""; // const currentDeductionString = deductions.map((num) => String(num)).join(' ');
    for (let i = 0; i < deductions.length; i++) {
        currentDeductionString += deductions[i] + " ";
    }
    console.log(currentDeductionString); 
    // неявно возвращаем undefined, что делает всю функцию возвращать Promise на undefined.
};
 
 
main();