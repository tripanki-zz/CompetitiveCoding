/**
 * Created by Ankit Tripathi on 24-11-2014.
 * For further queries/suggestions please mail at ankittripathi0000@gmail.com.
 * UVa Id : vgliderankit
 * UVa-10405 - Longest Common Subsequence
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1346
 */

#include<stdio.h>
#include<string.h>
#include<stdlib.h>

#define LIM 1002

int main() {
	int i, j, len1, len2;
	char c;
	char *str1 = (char *) calloc(LIM, sizeof(char));
	char *str2 = (char *) calloc(LIM, sizeof(char));
	int *first = (int *) calloc(LIM, sizeof(int));
	int *second = (int *) calloc(LIM, sizeof(int));
	
	while (gets(str1) != NULL) {
		gets(str2);
		len1 = strlen(str1);
		len2 = strlen(str2);
		
		memset(first, 0, (LIM) * sizeof(int));
		memset(second, 0, (LIM) * sizeof(int));
		
		for (i = 1; i <= len1; i++) {
			c = str1[i-1];
			first[0] = 0;
			second[0] = 0;
			if (i%2 == 0) {
				for (j = 1; j <= len2; j++) {
					if (c == str2[j-1]) {
						first[j] = second[j-1] + 1;
					} else {
						first[j] = (second[j] > first[j-1]) ? second[j]
															: first[j-1];
					}
				}
			} else {
				for (j = 1; j <= len2; j++) {
					if (c == str2[j-1]) {
						second[j] = first[j-1] + 1;
					} else {
						second[j] = (first[j] > second[j-1]) ? first[j]
															 : second[j-1];
					}
				}
			}
		}
	
		if (len1%2 == 0) {
			printf("%d\n", first[len2]);
		} else {
			printf("%d\n", second[len2]);
		}
		
		memset(str1, 0, (LIM) * sizeof(int));
		memset(str2, 0, (LIM) * sizeof(int));
	}
	
	return 0;
}