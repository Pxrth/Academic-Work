#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "HashTableAPI.h"


int main(int argc, char* argv[]){



  HTable * hashtable = createTable(26, hashNode, destroyNodeData, printNodeData);


  insertData(hashtable, makekey("test"), "test");
  insertData(hashtable, makekey("Parth"), "Parth");
  insertData(hashtable, makekey("Coding"), "Coding");
  insertData(hashtable, makekey("laptop"), "laptop");
  printall(hashtable);

  printf("Removing Coding from list\n");
  removeData(hashtable, makekey("Coding"));
  printall(hashtable);

  printf("Checking to see if test is in the list\n");
  lookupData(hashtable, "test");

  printf("Checking to see if purple is in the list\n");
  lookupData(hashtable, "purple");

  printf("Deleting entire table\n");
  destroyTable(hashtable);


}
