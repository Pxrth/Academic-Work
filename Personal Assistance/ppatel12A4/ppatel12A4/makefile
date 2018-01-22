CC = gcc
CFLAGS = -Wall -g -std=c99

BIN = ./bin/
SRC = ./src/
INC = ./include/

program:
	$(CC) $(CFLAGS) $(SRC)Main.c $(SRC)BinarySearchTreeAPI.c -Iinclude -o $(BIN)program

	$(CC) $(CFLAGS) $(SRC)TestMain.c $(SRC)BinarySearchTreeAPI.c -Iinclude -o $(BIN)testprogram


clean:

	rm ./BIN/*
