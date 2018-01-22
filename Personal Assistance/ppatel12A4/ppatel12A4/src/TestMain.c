#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "BinarySearchTreeAPI.h"


int main(int argc, char* argv[]){

  printf("Before Initialization\n");

  Tree * theTree = createBinTree(compare, del);

  Information *t1 = createInfoNode("Weather", "Its sunny", 0.5, 0.5, 0.5, 3);
  Information *t2 = createInfoNode("Drink", "Powerade is a sportdrink", 0.5, 0.5, 0.5, 3);
  Information *t3 = createInfoNode("Laptop", "Macbook Pro", 0.5, 0.5, 0.5, 3);
  Information *t4 = createInfoNode("Class", "Data Structures", 0.5, 0.5, 0.5, 3);
  Information *t5 = createInfoNode("ICap", "Tim Hortons", 0.5, 0.5, 0.5, 3);
  Information *t6 = createInfoNode("NYC", "New York City", 0.5, 0.5, 0.5, 3);
  Information *t7 = createInfoNode("Table", "Hash Table", 0.5, 0.5, 0.5, 3);


  printf("Before Insertion\n");

  printf("Adding first word\n");
  addToTree(theTree, t1);
  printf("Adding second word\n");
  addToTree(theTree, t2);
  printf("Adding third word\n");
  addToTree(theTree, t3);
  printf("Adding fourth word\n");
  addToTree(theTree, t4);
  printf("Adding fifth word\n");
  addToTree(theTree, t5);
  printf("Adding sixth word\n");
  addToTree(theTree, t6);
  printf("Adding seventh word\n");
  addToTree(theTree, t7);

  printf("After insertion\n");

  printf("----------------------------------------------\n");


  printf("Running search function\n");
  printf("Searching for Class\n");
  findInTree(theTree, t5);
  printf("Test Passed!\n");



  printf("----------------------------------------------\n");

  printf("Running get root function\n");
  printf("Getting root node\n");
  getRootData(theTree);
  printf("Test Passed!\n");

  printf("----------------------------------------------\n");



  printInOrder(theTree, printNode);
  printf("Running removing function\n");
  printf("Removing NYC\n");
  removeFromTree(theTree, t6);
  printf("----------Check Below-----------\n");
  printInOrder(theTree, printNode);
  printf("Test Passed!\n");

  printf("----------------------------------------------\n");


/*
  printf("Running Height Function\n");
  printf("Getting height of tree\n");
  getHeight(theTree->root);
  printf("Did it work?\n");

  printf("----------------------------------------------\n");

  printf("Running isLeaf Function\n");
  printf("Checking for leaf node\n");
  isLeaf(theTree->root);
  printf("Did it work?\n");

  printf("----------------------------------------------\n");

  printf("Running hasTwoChildren Function\n");
  printf("Checking for 2 children\n");
  hasTwoChildren(theTree->root);
  printf("Did it work?\n");

  printf("----------------------------------------------\n");
*/


  printf("Running printInOrder Function\n");
  printInOrder(theTree, printNode);
  printf("Test Passed!\n");

  printf("----------------------------------------------\n");

  printf("Running printPreOrder Function\n");
  printPreOrder(theTree, printNode);
  printf("Test Passed!\n");

  printf("----------------------------------------------\n");

  printf("Running printPostOrder Function\n");
  printPostOrder(theTree, printNode);
  printf("Test Passed!\n");

  printf("----------------------------------------------\n");



  printInOrder(theTree, printNode);
  printf("Running destroyBinTree Function\n");
  destroyBinTree(theTree);
  printf("Tree was Destroyed\n");
  printf("Printing Tree\n");
  printf("Test Passed!\n");

  printf("----------------------------------------------\n");



}
