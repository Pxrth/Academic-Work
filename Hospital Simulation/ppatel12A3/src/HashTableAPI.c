#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "HashTableAPI.h"



HTable *createTable(size_t size, int (*hashFunction)(size_t tableSize, int key),void (*destroyData)(void *data),void (*printNode)(void *toBePrinted)){


        HTable *table = malloc(sizeof(HTable));


        table->hashFunction = hashNode;
        table->destroyData= destroyNodeData;
        table->printNode= printNodeData;
        table->size = size;
        table->table = malloc(sizeof(Node*)*size);

        for(int i = 0; i < (int) size; i++){
          table->table[i] = NULL;
        }

        return table;
}


Node *createNode(int key, void *data){

        Node *node = malloc(sizeof(Node));
        node->data = malloc(sizeof(char) * 100);

        node->key = key;
        strcpy(node->data, data);
        node->next = NULL;


        return node;

}


void destroyTable(HTable *hashTable){


  Node *prev;
  Node *curr;
  int i;
  for(i = 0; i< hashTable->size; i++){
    prev = hashTable->table[i];
    curr = prev->next;

    if(curr == NULL){
        free(prev);
        break;
    }

    while(curr != NULL){
      free(prev);
      prev = curr;
      curr = prev->next;
    }
    free(prev);
  }

free(hashTable->table);

}



void insertData(HTable *hashTable, int key, void *data){

  Node *temp = createNode(key, data);

  int h = hashNode(hashTable->size, key);

  if((hashTable->table[h]) == NULL){

    hashTable->table[h] = temp;

    //printf("word added : %s\n", (char*)(hashTable->table[h]->data));

  }
  else if(hashTable->table[h] != NULL)
  {
    Node *line = hashTable->table[h];

    while(line->next != NULL){

      line=line->next;
    }

    line->next = temp;

    //printf("Word added: %s\n", (char*)temp->data);
  }

}



void removeData(HTable *hashTable, int key){

Node *removenode;

  int h = hashNode(hashTable->size, key);

    if(hashTable->table[h]->key == key && hashTable->table[h]->next != NULL){

      hashTable->table[h] = hashTable->table[h]->next;

    }
    else if(hashTable->table[h]->key == key && hashTable->table[h]->next == NULL){

      hashTable->table[h] = NULL;

    }
    else{

      Node *prev, *current;


      prev = hashTable->table[h];
      current = hashTable->table[h];

      while(current->next != NULL){

        prev = current;
        current = current->next;

        if(current->key == key){
          prev->next = current->next;
          removenode = current;
          free(removenode);
        }

      }

    }
}



void *lookupData(HTable *hashTable, char * data){

  int key = makekey(data);
  int h = hashNode(hashTable->size, key);

  Node * temp = hashTable->table[h];

  while(temp != NULL){
    if(strcmp(((char*)temp->data), data) == 0) {
      return temp->data;
    }

    temp = temp->next;
  }

return NULL;

}


int makekey(void *data){

  data = (char *) data;

  char * something = malloc(sizeof(data));
  something = data;

  int key = 0;
  int i;
  for(i = 0; i < strlen(data); i++) {
    key += something[i] * (i+1);

  }

  //printf("%d\n", key);

  return key;

}


int hashNode(size_t tableSize, int key){


  return (key % tableSize);

}


void destroyNodeData(void *data){

  printf("Delete this\n");

}


void printNodeData(void *toBePrinted){

  if(toBePrinted == NULL){

    printf("<%d> : <%s>\n",((Node*)toBePrinted)->key, (char*)((Node*)toBePrinted)->data);

  }

}


void printall(HTable *hashTable){


  for(int i = 0; i < hashTable->size; i++) {
    if ((hashTable->table[i]) != NULL) {

      Node * node = hashTable->table[i];

      while(node != NULL){
        printf("<%d> : <%d> : <%s>\n", i, (node->key), (char *)(node->data));
        node = node->next;
      }
    }
  }
  printf("\n");

}
