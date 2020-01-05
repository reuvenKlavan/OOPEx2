# Graphs

### This program represents a directional weighted graph. The program has a road-system or communication network in mind - and should support a large number of nodes (over 1,000,000). The implementation is based on an efficient compact representation (Array list and Linked List).

## Algorithims

After creating the graph, there are a few algorithms that are optional to run on the graph. The algorithms check and change the graphs diffrent values.

### The diffrent algorithms are:

* init (graph g) - initiates the set of algorithm for graph with the name g the graph we want to create on him those algorithm 

* init (String file_name) - we get a file that has been saved by the next method and initiates those set of algorithm name of file that we convert it to a graph for perform on him those algorithm 

* save (String file_name) - The file gets a string and names the file as the geven string. The funnction saves the graph to a text file, the function saves in the text file all of the verteceis as three integers in parentheses that there is id, location on the x axis and the location on the y axis (ID, X Axis, Y Axis). The Edges are saved in the file as two integers in parentheses that there is destination and whight (destination, whight).

* isConnected - This is a boolean algorithm that check if the graph is strongly connected (all of the vertecies are connected to eac other one way or another). The algorithm uses a private function that check if the verteceis are connected. the algorithm goes over all of the verteceis of the graph and checks if is possible to get from source to destination and if true, it checks the opposite graph (the mirror graph) in the way that the source becomes the destanation and vice versa, and checks again if it is possible to get from the source to the destination. 

* shortestPathDist - This is an algorithm that calculates the shortest path between geven Vertecies. this algorithm is part of a known problem that is called "TSP" - Traveling Salesman Problem, the problem is to find all of the possible ways to pass through geven Verteceis and findig the shortest one in exponentual runtime. we implemented this algorithem by srtarting from the source Vertex and checking all of the verteceis that are connected to it with one Edge and marking them as visited(coloring them black) and saving the "cost" it took to get to that particular Vrtex, and so recursevly to all Vertecies. in case the vertex does not have anyware to go to it goes back to privous vertex, and colors it white (not visited). in case we visit a "white" Vertix that we visited alredy and his wheight is lower so we pass to it without changing its wheight. the recursion is finished when the source is equal to the destanation and then return the wheight.

* List<node_data> shortestPath - this algorithm returns the Verteceis that it will be the shortest way to go through, this algorithm uses the shortestPathDist to chek wich path is shortest. if there is no path it will return null.

* List<node_data> TSP - check if there is a path between all the verteceis that there ID are in the targets list like shortestPath targets a list of integer that represent the keys of the nodes we need to pass in any kind of way, if there is a path between all those verticeis it will return a list of verteceis that represent the path else it will return null.

* graph copy - creats a new graph by copying the geven graph by deep copy.

## Data structure

The requiremnts where to implement the graph in a way that it is possible to do a few functions in linear runtime.
therefor we implemented as described.

The graph is implemented with the fallowing data strutures:

The vertex set in a LinkedList.

The Edges are saved in a hash map indie a hash map in order to be able to return a requested Edge in linear run time.

## GUI

The GUI window can run graphs and set them using the algorithms.

Using the GUI graph there is a menue  bar that has two options:

* File: - save
        - Load
        
* Algorithms: - Is Connected
              - Shortest Path distance
              - Shortest Path
              - TSP
              
              
