#include<stdio.h>

#define LIM 1000001

/**
 * Created by Ankit Tripathi.
 * For further queries/suggestions please mail at ankittripathi0000@gmail.com.
 *
 * SPOJ ID: aka_lone_wolf
 * http://www.spoj.com/problems/PALIN/
 *
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
			
			index = -3;
			while (fwd <= j) {
				if (digit[fwd] < digit[bck]) {
					index = -1;
				}
				if (digit[fwd] > digit[bck]) {
					index = -2;
				}
				bck--;		fwd++;
			}
			// If fwd is -1 iska matlab ye ki middle ke paas waala koi left position aisa h jiski value less than that of its
			// right hand value
			if (index == -1 || index == -3) {	// break from 70.
				while (digit[j] == 9) {
					digit[j] = digit[k] = 0;
					k++;	j--;
				}
				digit[j] += 1;
				digit[k] = digit[j];
				k++;	j--;
			}

			fwd = 0;
			bck = limit - 1;
			while (fwd <= j) {
				digit[bck] = digit[fwd];
				fwd++;		bck--;
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
