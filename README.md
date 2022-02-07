# Groupfinder
Tradeshift recruitment Test

#Problem statement:
We in Amazing Co need to model how our company is structured so we can do awesome stuff.We have a root node (only one) and several children nodes, each one with its own children as well. It's a tree-based structure. Something like:

1         root
2        /    \
3       a      b
4       |
5       c
We need two HTTP APIs that will serve the two basic operations: 
Get all descendant nodes of a given node (the given node can be anyone in the tree structure).
Change the parent node of a given node (the given node can be anyone in the tree structure).
They need to answer quickly, even with tons of nodes. Also, we can't afford to lose this information, so some sort of persistence is required.
Each node should have the following info:
node identification
who is the parent node
who is the root node
the height of the node. In the above example, height(root) = 0 and height(a) == 1. 
In Amazing Co our services are generally written in Java, built with Maven or Gradle, deployed as Docker images, and using Postgres for persistence. These are not hard requirements, but please document your decisions and tradeoffs.
It should be fast and easy to get your solution up-and-running for a developer that is new to your codebase.
Given the time constraints of the challenge, consider how you focus your efforts. The goal is getting an impression of your problem-solving process.

#Solution Brief:
The key thought behind API search is how we design the database. The idea is to add a key in descendent node which reflects it ancestor. To give a visual example.

		    0
2       /      \
3        01      02
4     /  |  \    |  \
5   011 012 013  021 022
   /  \	
 0111 0112
 
 If you see in above example, the number indicates the node ID of each node. Let's take 01 for example. If search the graph for all node-id which contains 01, it will return us
 011,012,013,0111 and 0112. These are ultimately the descendants of that group.
 
 The idea was to have common indicator just like family names :)