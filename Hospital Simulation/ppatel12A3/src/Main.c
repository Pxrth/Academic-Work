#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "HashTableAPI.h"


int main(int argc, char* argv[]){

  int correct = 0;
  int incorrect = 0;

//Reading in the file

if(argc <= 1){
  printf("File does not exist\n");
  return 0;
}

char const* const filename = argv[1];
FILE *file = fopen(filename, "r");

  if(file==NULL){
    printf("Error, can't open file\n");
    return 0;
  }


HTable * hashtable = createTable(26, hashNode, destroyNodeData, printNodeData);


char line[1000];
char buffer[100];

  while(fgets(line, 1000, file) != NULL){

    sscanf(line,"%s", buffer);

    insertData(hashtable, makekey(buffer), buffer);

  }


fclose(file);


//printall(hashtable);
printf("\n");

int selection = 0;


  do{

    printf("1) Add a word to Dictionary\n");
    printf("2) Remove a word from Dictionary\n");
    printf("3) Spell Check a file\n");
    printf("4) Show Dictionary Words\n");
    printf("5) Quit\n");

    scanf("%d", &selection);

    if(selection < 1 || selection > 5){
      printf("Invalid selection, please try again\n");
    }
    else if(selection == 1){

      char addword[100];

      printf("Which word would you like to add to the dictionary?\n");
      scanf("%s", addword);

      insertData(hashtable, makekey(addword), addword);

    }
    else if(selection == 2){

      char removeword[100];
      printf("Which word would you like to remove from the dictionary\n");
      scanf("%s", removeword);

      removeData(hashtable, makekey(removeword));
    }
    else if(selection == 3){
      correct = 0;
      incorrect = 0;

      char filechecker[1000];

      printf("Please enter the file you would like to spell check:\n");
      scanf("%s", filechecker);


      FILE *checkfile = fopen(filechecker, "r");

        if(checkfile==NULL){
          printf("Error, can't open file\n");
          return 0;
        }

        char line[1000];
        char buffer[1000];
        int i;
          while(fgets(line, 1000, checkfile) != NULL){
            sscanf(line,"%s", buffer);
            //printf("%s\n", buffer);

            for(i = 0; i < 100; i++){
              buffer[i] = (tolower(buffer[i]));
            }

            if (lookupData(hashtable, buffer) == NULL) {
              printf("Not found in dictionary : %s", line);
              incorrect++;
            }
            else {
              correct++;
            }

          }
          printf("\n");
          printf("Summary:\n");
          printf("Correctly spelt words: %d\n", correct);
          printf("Incorrectly spelt words: %d\n", incorrect);
          printf("\n");

        fclose(checkfile);

    }
    else if(selection == 4){

      printf("Personal Dictionary\n");

      printall(hashtable);

    }

  }while(selection != 5);




  return 0;
}
