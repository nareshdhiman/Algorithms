/* A Queue object for queue-like functionality over JavaScript arrays. */
var Queue = function() {
    this.items = [];
};
Queue.prototype.enqueue = function(obj) {
    this.items.push(obj);
};
Queue.prototype.dequeue = function() {
    return this.items.shift();
};
Queue.prototype.isEmpty = function() {
    return this.items.length === 0;
};

function assertEqual(bsfA, bsfB, message)
{
    if (bsfA.distance != bsfB.distance ) throw new Error("vertax "+message+" distance not matched");
    if (bsfA.predecessor != bsfB.predecessor ) throw new Error("vertax "+message+" predecessor not matched");
}

/*
 * Performs a breadth-first search on a graph
 * @param {array} graph - Graph, represented as adjacency lists.
 * @param {number} source - The index of the source vertex.
 * @returns {array} Array of objects describing each vertex, like
 *     [{distance: _, predecessor: _ }]
 */
var doBFS = function(graph, source) {
    var bfsInfo = [];

    for (var i = 0; i < graph.length; i++) {
	    bfsInfo[i] = {
	        distance: null,
	        predecessor: null };
    }

    bfsInfo[source].distance = 0;

    var queue = new Queue();
    queue.enqueue(source);

    // Traverse the graph
    
    // As long as the queue is not empty:
    //  Repeatedly dequeue a vertex u from the queue.
    //  
    //  For each neighbor v of u that has not been visited:
    //     Set distance to 1 greater than u's distance
    //     Set predecessor to u
    //     Enqueue v
    //
    //  Hint:
    //  use graph to get the neighbors,
    //  use bfsInfo for distances and predecessors 
    while(!queue.isEmpty()) {
        var u = queue.dequeue();
        console.log("u= "+ graph[u]);
        for (var j=0; j < graph[u].length; j++) {
            var v = graph[u][j];
            console.log("v= "+ v);
            if (bfsInfo[v].distance == null ) {
                bfsInfo[v] = { distance: bfsInfo[u].distance+1, predecessor: u};
                queue.enqueue(v);
            }
        }
    }
    
    return bfsInfo;
};


var adjList = [
    [1],
    [0, 4, 5],
    [3, 4, 5],
    [2, 6],
    [1, 2],
    [1, 2, 6],
    [3, 5],
    []
    ];
var bfsInfo = doBFS(adjList, 3);
for (var i = 0; i < adjList.length; i++) {
    console.log("vertex " + i + ": distance = " + bfsInfo[i].distance + ", predecessor = " + bfsInfo[i].predecessor);
}


assertEqual(bfsInfo[0], {distance: 4, predecessor: 1}, "0");
assertEqual(bfsInfo[1], {distance: 3, predecessor: 4}, "1");
assertEqual(bfsInfo[2], {distance: 1, predecessor: 3}, "2");
assertEqual(bfsInfo[3], {distance: 0, predecessor: null}, "3");
assertEqual(bfsInfo[4], {distance: 2, predecessor: 2}, "4");
assertEqual(bfsInfo[5], {distance: 2, predecessor: 2}, "5");
assertEqual(bfsInfo[6], {distance: 1, predecessor: 3}, "6");
assertEqual(bfsInfo[7], {distance: null, predecessor: null}, "7");

