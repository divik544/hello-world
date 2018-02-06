//AC
#include<bits/stdc++.h>
using namespace std;
int dp[2005][2005];
int main()
{
	//all arrays are 1 indexed
	int n;
	cin >> n;
	vector<int> v(n+1);
	for(int i = 1; i < n+1; i++)
		cin >> v[i];
	for(int i = 1; i <= n; i++)
		dp[i][i] = v[i]*n;
	for(int k = 1; k < n; k++)
	{
		for(int i = 1; i < n-k+1; i++)
		{
			int j = i+k;
			dp[i][j] = max(dp[i][j-1] + v[j]*(n-j+i),dp[i+1][j] + v[i]*(n-j+i));
		}
	}
	
	cout << dp[1][n];
}
