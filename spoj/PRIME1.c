/**
 * http://www.spoj.com/problems/PRIME1/
 * @author aka_lone_wolf <ankittripathi0000@gmail.com>
 */
#include<stdio.h>
#include<string.h>

#define LIM 100001
#define SLIM 31624
#define SSLIM 178

char flag[SLIM] = {0};
char map[LIM] = {0};

int main() {
	unsigned long long int point[10][2];
	unsigned long long int n, k, temp, newStart;
	unsigned long long int i, j;

	flag[0] = flag[1] = 1;
	for (i = 2; i < SSLIM; i++) {
		if (flag[i] == 0) {
			for (j = i + i; j <= SLIM; j += i) {
				flag[j] = 1;
			}
		}
	}

	scanf("%llu", &n);

	for (k = 0; k < n; k++) {
		memset(map, 0, LIM);
		scanf("%llu %llu", &point[k][0], &point[k][1]);

		if (point[k][1] > SLIM) {
			if (point[k][0] <= SLIM) {
				newStart = SLIM + 1;

				for (j = point[k][0]; j <= SLIM; j++) {
					if (flag[j] == 0) {
						printf("%llu\n", j);
					}
				}
				point[k][0] = newStart;
			} else {
				newStart = point[k][0];
			}

			for (i = 2; i <= SLIM; i++) {
				if (flag[i] == 0) {
					temp = newStart % i;
					j = (temp == 0) ? newStart : newStart + (i - temp);
					for (j; j <= point[k][1]; j += i) {
						map[j - point[k][0]] = 1;
					}
				}
			}
			
			for (j = newStart; j <= point[k][1]; j++) {
				if (map[j-newStart] == 0) {
					printf("%llu\n", (j));
				}
			}
		} else {
			for (j = point[k][0]; j <= point[k][1]; j++) {
				if (flag[j] == 0) {
					printf("%llu\n", j);
				}
			}
		}
	}

	return 0;
}