CCFLAGS = -Wall

counter: count.o
	cc $(CXXFLAGS) -o counter count.o -ll

count.o: count.c
	cc $(CXXFLAGS) -c count.o count.c

count.c: count.l
	flex -o count.c count.l

clean:
	rm counter
	rm *.o
	rm count.c
