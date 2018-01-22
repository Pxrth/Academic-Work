#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "BinarySearchTreeAPI.h"


typedef int (*CompareFunc)(const void* a, const void* b);
typedef void (*DeleteFunc)(void* data);
typedef void (*PrintFunc)(void* data);

int compare(const void* a, const void* b){

  int ret;
  Information *i = (Information*)a;
  Information *j = (Information*)b;

  ret = strcmp(i->keyword, j->keyword);
  if(ret > 0){
    return 1;
  }
  else if(ret < 0){
    return -1;
  }

    return 0;
}


void del(void* data){

  free(data);

}


void printNode(void *data){

  //printf("%s\n", ((Information*)(root->data))->keyword);

}


TreeNode* createTreeNode(TreeDataPtr data){

  TreeNode *node = malloc(sizeof(TreeNode));

  node->data = data;
  node->right = NULL;
  node->left = NULL;

  return node;

}

Information *createInfoNode(char *keyword, char *line, double userrating, double systemrating, double learningrating, int used){

  Information *infoNode = malloc(sizeof(Information));

  strcpy(infoNode->keyword, keyword);
  strcpy(infoNode->line, line);
  infoNode->userrating = userrating;
  infoNode->systemrating = systemrating;
  infoNode->learningrating = learningrating;
  infoNode->used = used;

  return infoNode;

}

Tree * createBinTree(CompareFunc compare, DeleteFunc del){

  Tree *theTree = malloc(sizeof(Tree));

  theTree -> compareFunc = compare;
  theTree -> deleteFunc = del;


  return theTree;

}

void  destroyBinTree(Tree * toDestroy){

  if(toDestroy != NULL){
    del(toDestroy);
  }

  return;

}

void addToTree(Tree * theTree, TreeDataPtr data){

  CompareFunc comp = &compare;
  theTree->root = insert(theTree->root, data, comp);

}

TreeNode *insert(TreeNode *temp, TreeDataPtr Node, CompareFunc compare){
  TreeNode * root = temp;

  if(root == NULL){
    return createTreeNode(Node);
  }
  else if(compare(Node, root->data) < 0){
    root->left = insert(root->left, Node, compare);
  }
  else if(compare(Node, root->data) > 0){
    root->right = insert(root->right, Node, compare);
  }
  else if(compare(Node, root->data) == 0){
    return root;
  }

  return temp;
}


void removeFromTree(Tree * theTree, TreeDataPtr data){

  CompareFunc comp = &compare;
  theTree->root = delete(theTree->root, data, comp);

}

TreeNode *delete(TreeNode *temp, TreeDataPtr data, CompareFunc compare){

  TreeNode *root = temp;

  if(root == NULL){
    return root;
  }
  else if(compare(data, root->data) > 0){
    root->right = delete(root->right, data, compare);
  }
  else if(compare(data, root->data) < 0){
    root->left = delete(root->left, data, compare);
  }
  else{
    // Case 1 No Child
    if(root->left == NULL && root->right == NULL){
        root = NULL;
    }
    // Case 2 One Right Child
    else if(root->left == NULL){
      TreeNode *delete = root;
      root = root->right;
      del(delete);
      delete = NULL;
    }
    // Case 3 One Left Child
    else if(root->right == NULL){
      TreeNode *delete = root;
      root = root->left;
      del(delete);
      delete = NULL;
    }
    else{
      TreeNode *tNode = findMin(root->right);
      root->data = tNode->data;
      root->right = delete(root->right, tNode->data, compare);
    }
  }

  return root;

}


TreeNode *findMin(TreeNode *temp){

  if(temp->left == NULL){
    return temp;
  }

  return findMin(temp->left);
}


TreeDataPtr findInTree(Tree* theTree, TreeDataPtr data){

  TreeNode *temp = theTree->root;

  if(theTree != NULL && data != NULL && theTree->root != NULL){

    while(temp != NULL){

      if(compare(data, temp->data) > 0){
        temp = temp->right;
      }
      else if(compare(data, temp->data) < 0){
        temp = temp ->left;
      }
      else{
        return temp->data;
      }
    }

  }
  return NULL;

}

TreeDataPtr getRootData(Tree * theTree){

  return Root(theTree->root);

}

TreeNode *Root(TreeNode *root){

  TreeNode *temp = root;
  printf("%s\n", ((Information*)(root->data))->keyword);

  return temp;
}


void printInOrder(Tree * theTree, PrintFunc printData){

  if(theTree->root == NULL){
    return;
  }
  else{
    printNodeInOrder(theTree->root);
  }
}

void printNodeInOrder(TreeNode *root){

  if(root == NULL){
    return;
  }

  printNodeInOrder(root->left);
  printf("%s - Rating: %.1f - System: %.1f - Occurrences: %d\n", ((Information*)(root->data))->keyword, ((Information*)(root->data))->userrating, ((Information*)(root->data))->systemrating, ((Information*)(root->data))->used);
  printNodeInOrder(root->right);


}


void printPreOrder(Tree * theTree, PrintFunc printData){

  if(theTree->root == NULL){
    return;
  }
  else{
    printNodePreOrder(theTree->root);
  }


}

void printNodePreOrder(TreeNode *root){

  if(root == NULL){
    return;
  }

  printNodePreOrder(root->right);
  printf("%s\n", ((Information*)(root->data))->keyword);
  printNodePreOrder(root->left);

}


void printPostOrder(Tree * theTree, PrintFunc printData){

  if(theTree->root == NULL){
    return;
  }
  else{
    printNodePostOrder(theTree->root);
  }

}


void printNodePostOrder(TreeNode *root){

  if(root == NULL){
    return;
  }

  printf("%s\n", ((Information*)(root->data))->keyword);
  printNodePostOrder(root->right);
  printNodePostOrder(root->left);

}


int isTreeEmpty(Tree* theTree){

  TreeNode *tree = theTree->root;

  if(tree == NULL){
      printf("No tree exist\n");
      return -1;
  }
  else if(tree->right == NULL && tree->left == NULL)
  {
      printf("Tree is empty\n");
      return 1;
  }

  printf("Tree is not empty\n");
  return 0;

}


int isLeaf(TreeNode * treeNode){

  if(treeNode->right == NULL && treeNode->left == NULL){
    printf("Leaf Found\n");
    return 1;
  }
  else{
    printf("Not a Leaf\n");
    return 0;
  }

}


int hasTwoChildren( TreeNode *treeNode){

  if(treeNode->right != NULL && treeNode->left != NULL){
    printf("Has Two Children\n");
    return 1;
  }
  else{
    printf("Not Two Children\n");
    return 0;
  }

}

int getHeight( TreeNode* treeNode ){

  int lheight;
  int rheight;

  if(treeNode == NULL){
    return 0;
  }

    rheight = getHeight(treeNode->right);
    lheight = getHeight(treeNode->left);

  if(rheight > lheight){
    printf("The height is %d\n", (1 + rheight));
    return 1 + rheight;
  }
  else{
    printf("The height is %d\n", (1 + lheight));
    return 1 + lheight;
  }
}
