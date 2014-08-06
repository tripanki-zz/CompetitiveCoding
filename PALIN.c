#include<stdio.h>

#define LIM 1000001

/*
	http://www.spoj.com/problems/PALIN/
	Ankit Tripathi (ankittripathi0000@gmail.com)
*/

char digit[LIM] = {0};

int getArray(unsigned int *i) {
	int c = getchar_unlocked();
	unsigned int k = 0;
	*i = 0;
	char map[10] = {0};

	while (! ((c >= '0') && (c <= '9'))) {
		c = getchar_unlocked();
	}

	while ((c >= '0') && (c <= '9')) {
		digit[*i] = (c-'0');
		*i = *i + 1;
		map[(c-'0')] = 1;
		c = getchar_unlocked();
	}

	for (k = 0; k < 9; k++) {
		if (map[k] == 1) {
			return 1;
		}
	}

	return 0;
}

int main() {
	int test, i, j, k, limit, mid, fwd, bck, index;

	scanf("%u", &test);

	for (i = 0; i < test; i++) {
		k = getArray(&limit);
		
		if (k) {
			if (limit == 1) {
				printf("%d\n", ((int)digit[0] + 1));
				continue;
			}
			mid = limit >> 1;
			if (limit % 2 == 0) {
				k = mid;
				j = mid - 1;
			} else {
				j = k = mid;
			}
			
			fwd = 0;
			bck = limit - 1;
			
			if (digit[j] > digit[k]) {
				digit[k] = digit[j];
				while (fwd != j) {
					digit[bck] = digit[fwd];
					bck--;
					fwd++;
				}
			} else {
				index = -1;
				while (fwd <= j) {
					if (digit[fwd] < digit[bck]) {
						fwd = -1;
						break;
					}
					if ((digit[fwd] == 9) && (digit[fwd] > digit[bck])) {
						index = fwd;
					}
					bck--;		fwd++;
				}
				
				if (fwd == -1) {	// break from 70.
					while (digit[j] == 9) {
						digit[j] = digit[k] = 0;
						k++;	j--;
					}
					digit[j] += 1;
					digit[k] = digit[j];
					k++;	j--;
				} else {
					fwd = fwd - 1;
					bck = bck + 1;					
					if (index == -1) {
						while ((digit[fwd] == digit[bck]) && (digit[fwd] == 9)) {
							digit[fwd] = digit[bck] = 0;
							bck++;	fwd--;
						}
						digit[fwd] += 1;
						digit[bck] = digit[fwd];
						bck++;	fwd--;
						j = fwd;
						k = bck;
					} else {
						digit[limit - index - 1] = digit[index];
					}
				}

				fwd = 0;
				bck = limit - 1;
				while (fwd <= j) {
					digit[bck] = digit[fwd];
					fwd++;		bck--;
				}
			}

			for (fwd = 0; fwd < limit; fwd++) {
				printf("%d", digit[fwd]);
			}
			printf("\n");
			
		} else {
			if (limit == 1) {
				printf("11\n");
			} else {
				printf("1");
				printf("%0*d", (limit-1), 0);
				printf("1\n");
			}
		}
	}

	return 0;
}
