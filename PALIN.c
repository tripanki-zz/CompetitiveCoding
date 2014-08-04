#include<stdio.h>

#define LIM 1000001
#define getchar_unlocked gc

char digit[LIM] = {0};

// This method returns 0 iff all digits are 9, otherwise it returns 0;
int getArray(unsigned int *i) {
	int c = gc();
	unsigned int k = 0;
	*i = 0;
	char map[10] = {0};

	while (! ((c > '0') && (c <= '9'))) {
		c = gc();
	}

	while ((c > '0') && (c <= '9')) {
		digit[*i] = (c-'0');
		*i = *i + 1;
		map[(c-'0')] = 1;
		c = gc();
	}

	for (k = 0; k < 9; k++) {
		if (map[k] == 1) {
			return 1;
		}
	}

	return 0;
}

int main() {
	unsigned int test, i, j, k, limit, mid, fwd, bck;

	scanf("%u", &test);

	for (i = 0; i < test; i++) {
		k = getArray(&limit);
		
		if (k) {
			mid = limit >> 1;
			if (mid % 2 == 0) {
				j = mid;
				k = mid - 1;
			} else {
				j = k = mid;
			}
			
			fwd = 0;
			bck = limit - 1;
			
			while (bck != j) {
				if (digit[bck] < digit[fwd]) {
					// There is an element where the left side number is smaller than the right sides number
				}
				bck--;
				fwd++;
			}
		} else {	// All elements present in the array are 9
			printf("1");
			printf("%0*d", (limit-1), 0);
			printf("1\n");
		}
	}

	return 0;
}

/**
	Currently i am assuming that the input number can never be 00000xyz, i.e., it can never start with leading zeros.
*/
