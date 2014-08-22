#include<stdio.h>

/**
 * Created by Ankit Tripathi on 22-08-2014.
 * For further queries/suggestions please mail at ankittripathi0000@gmail.com.
 * http://www.codechef.com/problems/PRGIFT
 */
int main() {
	int n, k, val, j, t, i;

	scanf("%d", &t);

	while (t-- > 0) {
		scanf("%d", &n);
		scanf("%d", &k);
		j = 0;

		for (i = 0; i < n; i++) {
			scanf("%d", &val);
			(val & 1) ? j : j++;
		}

		if (j >= k) {
			if ((k == 0) && (j == n)) {
				printf("NO\n");
			}
			else {
				printf("YES\n");
			}			
		} else {
			printf("NO\n");
		}
	}

	return 0;
}
