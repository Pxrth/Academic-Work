#ifndef _FUNCTION_
#define _FUNCTION_


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "HashTableAPI.h"



int hashNode(size_t tableSize, int key);

void destroyNodeData(void *data);

void printNodeData(void *toBePrinted);





#endif
