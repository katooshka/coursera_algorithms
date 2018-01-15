const readline = require('readline');

var strings = new Array();

let rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

// rl.prompt();

rl.on('line', function (line) {
  strings.push(line);
});

rl.on('close', function (line) {
  findPoints(strings);
  process.exit(0);
});

// findPoints(new Array("2", "1 3", "2 4"));

function findPoints(strings) {
  const segmentsCount = parseInt(strings[0], 10);
  let segments = new Array();
  for (let i = 1; i < strings.length; i++) {
    segments.push(new Segment(parseInt(strings[i].split(" ")[0], 10), 
                              parseInt(strings[i].split(" ")[1]), 10));
  }
  segments.sort(sortByStart);
  let countVisits = 0;
  let visits = new Array();
  let currentPoint = segments[0].start;
  let leastEnd = segments[0].end;

  for (let i = 1; i < segmentsCount; i++) {
    if (segments[i].start <= leastEnd) {
      currentPoint = segments[i].start;
      leastEnd = Math.min(leastEnd, segments[i].end);
    } else {
      visits.push(currentPoint);
      countVisits++;
      currentPoint = segments[i].start;
      leastEnd = segments[i].end;
    }
  }
  visits.push(currentPoint);
  countVisits++;

  console.log(countVisits);
  let visitsString = "";
  for (let i = 0; i < visits.length; i++) {
    visitsString += visits[i] + " ";
  }
  console.log(visitsString);

  function Segment(start, end) {
    this.start = start;
    this.end = end;
  }

  function sortByStart(first, second) {
    if (first.start < second.start) {
      return -1;
    }
    if (first.start > second.start) {
      return 1;
    }
    return 0;
  }
}

// const n = 3;

// let segments = new Array(
//   new Segment(1, 3), 
//   new Segment(2, 7), 
//   new Segment(0, 10));


