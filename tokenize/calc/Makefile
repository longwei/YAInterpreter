calc: count.o
	cc -o calc count.o -ll

count.o: count.c
	cc -c count.c -o count.o

count.c: count.l
	flex -o count.c count.l

clean:
	rm count.c
	rm *.o
	rm calc
