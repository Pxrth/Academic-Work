#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include "BinarySearchTreeAPI.h"


int main(int argc, char* argv[]){


// Reading in file information

if(argc <= 1){
  printf("file does not exist\n");
  return 0;
}

char const* const filename = argv[1];
FILE *file = fopen(filename, "r");

  if(file==NULL){
    printf("Error, can't open file\n");
    return 0;
  }


Tree * theTree = createBinTree(compare, del);

char buffer[1000];
char keyword[100];
char line[1000];

  while(fgets(buffer, 1000, file) != NULL){


    strcpy(keyword, buffer);
    keyword[strcspn(keyword, "\r\n")] = '\0';

    fgets(line, 1000, file);
    line[strcspn(line, "\r\n")] = '\0';

    Information *i = createInfoNode(keyword, line, 0.5, 0.5, 0.05, 0);

    addToTree(theTree, i);

  }
  fclose(file);

  printInOrder(theTree, printNode);

  printf("\n");

  int selection = 0;

  do{

    printf("1. Add a new rule\n");
    printf("2. Remove a rule\n");
    printf("3. Display rule\n");
    printf("4. Modify rule rating\n");
    printf("5. Discuss\n");
    printf("9. Quit \n");

    scanf("%d", &selection);


    if(selection < 1 || (5 < selection && selection < 9) || selection > 9){
      printf("Invalid selection, please try again\n");
    }
    else if(selection == 1){

      char word[100];
      char sentence[256];
      double userrating = 0;

      printf("Here you can teach Eliza a new rule\n");
      printf("Please input a keyword:\n");
      scanf("%s", word);
      getchar();

      printf("Please input the sentence:\n");
      scanf("%[^\r\n]s", sentence);
      getchar();

      do{
        printf("Please input the importance of the rule from [0 - 1.0]:\n");
        scanf("%lf", &userrating);
        getchar();
      }while(userrating > 1 && userrating < 0);

      Information *addword = createInfoNode(word, sentence, userrating, userrating, 0.05, 0);

      addToTree(theTree, addword);

      printf("New rule learnt\n");

    }
    else if(selection == 2){

      char removeword[100];

      printf("Please input the keyword for the rule you would like to remove\n");
      scanf("%s", removeword);

      if(findInTree(theTree, removeword) == NULL){
        printf("This rule doesn't exist\n");
      }
      else{
        removeFromTree(theTree, removeword);

        printf("Rule was forgotten\n");
      }

    }
    else if(selection == 3){

      printf("Here are all the rules\n");

      printInOrder(theTree, printNode);

    }
    else if(selection == 4){


      char word[100];
      double userrating = 0;

      printf("Please input a keyword:\n");
      scanf("%s", word);
      getchar();


      if(findInTree(theTree, word) == NULL){
        printf("This rule doesn't exist\n");
      }
      else{
        do{
          printf("Please input the new importance of the rule:\n");
          scanf("%lf", &userrating);
          getchar();
        }while(userrating > 1 && userrating < 0);


        ((Information*)(findInTree(theTree, word)))->userrating = userrating;

        printf("Rule Modified\n");
      }


    }
    else if(selection == 5){

      char question[256];
      char searcharray[50][50];
      double rate[50];
      double systemrate[50];
      double Lrate[50];
      int Occurrences[50];
      char line[50][50];
      bool flag = false;
      int size = 0;
      int newsrate = 0;
      int use = 0;
      int i = 0;

      printf("Hello, I'm Eliza! Ask me anything...\n");
      printf("To stop the converstion please input Quit\n");

      do{
      printf("User: ");
      scanf(" %[^\r\n]s", question);
      getchar();


      char *words = strtok(question, " ");

      while(words != NULL){

        if(findInTree(theTree, words) != NULL){

          strcpy(searcharray[size], ((Information*)(findInTree(theTree, words)))->keyword);
          rate[size] = ((Information*)(findInTree(theTree, words)))->userrating;
          systemrate[size] = ((Information*)(findInTree(theTree, words)))->systemrating;
          Lrate[size] = ((Information*)(findInTree(theTree, words)))->learningrating;
          Occurrences[size] = ((Information*)(findInTree(theTree, words)))->used;
          strcpy(line[size],((Information*)(findInTree(theTree, words)))->line);

          size++;
        }else{

          if(strcmp(words, "Quit") == 0){

            flag = true;

          }
        }

        words = strtok(NULL, " ");

      }

      int maxindex = 0;
      int maxrate = 0;

      for(i = 0; i < size; i++){

        if(rate[i] + systemrate[i] > maxrate){

          maxrate = rate[i] + systemrate[i];
          maxindex = i;
        }

      }

      printf("Personal Assistant: %s\n", line[maxindex]);

      printf("Was this feedback helpful? 'y' or 'n'\n");
      int feedback = getchar();

      if(feedback == 'y'){

        use = Occurrences[maxindex] + 1;

        newsrate = systemrate[maxindex] + (systemrate[maxindex] * Lrate[maxindex]/Occurrences[maxindex]);

        ((Information*)(findInTree(theTree, searcharray[maxindex])))->used = use;

        ((Information*)(findInTree(theTree, searcharray[maxindex])))->systemrating = newsrate;

        printf("Thank you for your feedback\n");

      }else{

        use = Occurrences[maxindex] + 1;

        newsrate = systemrate[maxindex] - (systemrate[maxindex] * Lrate[maxindex]/Occurrences[maxindex]);

        ((Information*)(findInTree(theTree, searcharray[maxindex])))->used = use;

        ((Information*)(findInTree(theTree, searcharray[maxindex])))->systemrating = newsrate;

        printf("Thank you for your feedback\n");

      }


    }while(flag != true);

    }




  }while(selection != 9);

  printf("Goodbye User!\n");


}
