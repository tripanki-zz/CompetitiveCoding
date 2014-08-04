#include<stdio.h>

int main() {
	unsigned int n = 0;

	scanf("%u", &n);

	printf("%0*d\n", n, 0);

	return 0;
}
