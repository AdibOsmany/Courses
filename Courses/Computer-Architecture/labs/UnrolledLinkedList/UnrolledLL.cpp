#include "UnrolledLL.h"
/*
	Name: Adib Osmany
	Pledge: I pledge my honor that I have abided by the stevens honors system. 
*/

/**
 * Constructor for a node in the unrolled linked list.
 * This should create a node with the given block size.
 * @param size Size of the list
 * @param blksize Size of each block
 */
uNode::uNode(uNode* prev, u_int64_t blksize) : blksize(blksize) {
	/* Implement me! */
	datagrp = new int[blksize];
	for (u_int64_t i = 0; i < blksize; i++) {
		datagrp[i] = rand() % 100;
	}
	
	next = nullptr;
	if (prev == nullptr)
		prev = this;
	else
		prev->next = this;

}

/**
 * Destructor for a node in the unrolled linked list.
 * This should deallocate all memory associated with the uNode.
 */
uNode::~uNode() {
	delete[] datagrp;
}

/**
 * Constructor for the unrolled linked list.
 * This should create a linked list of uNodes.
 * @param size Size of the list
 * @param blksize Size of each block
 */
UnrolledLL::UnrolledLL(u_int64_t size, u_int64_t blksize) {
	/* Implement me! */
	head = nullptr;
	len = size/blksize;
	uNode* temp;
	for (u_int64_t i = 0; i < len; i++) {
		if (i == 0){
			temp = new uNode(head, blksize);
			head=temp;
		}
		else{
			temp = new uNode(temp,blksize);
		}
	}

}

/**
 * Destructor for the unrolled linked list.
 * This should deallocate all memory associated with the unrolled linked list.
 */
UnrolledLL::~UnrolledLL() {
	uNode* current = head;
	uNode* next;

	while (current != nullptr) {
		next = current->next;
		delete current;
		current = next;
	}

	head = nullptr;
}

/**
 * Iterate through the unrolled linked list.
 * Simply iterate through the unrolled linked list and access each element.
 */
void UnrolledLL::iterate_ullist() {
	/* Implement me! */
	uNode* iter = head;
	while (iter != nullptr) {
		int size=iter->blksize;
		for(int i=0; i<size;i++){
			int num = iter->datagrp[i];
		}
		iter = iter->next;
	}
}
