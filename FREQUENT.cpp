#include<bits/stdc++.h>
#define ll long long
#define pb push_back
#define mp make_pair
#define fi first
#define se second
using namespace std;
typedef pair<int,int> pii;
typedef vector<int> vi;

struct node
{
	int maxf;
	int lfeq;
	int rfeq;
};

const int MAX=(int)1e5+5;
vi v(MAX);
vector< node > segtree(4*MAX);

void build(int low,int high, int pos)
{
	if(low > high)
		return;
	if(low == high)
	{
		segtree[pos].maxf=1;
		segtree[pos].lfeq=1;
		segtree[pos].rfeq=1;
		return;
	}
	int mid=(low+high)/2;
	build(low,mid,pos*2+1);
	build(mid+1, high, pos*2+2);
	segtree[pos].lfeq=segtree[2*pos+1].lfeq;
	segtree[pos].rfeq = segtree[2*pos+2].rfeq;
	segtree[pos].maxf = max(segtree[pos*2+1].maxf, segtree[pos*2+2].maxf);
	if(v[mid]==v[mid+1])
		segtree[pos].maxf = max(segtree[pos].maxf, segtree[pos*2+1].rfeq+segtree[pos*2+2].lfeq);
	return;
}

node query(int low, int high, int qst, int qen, int pos)
{
	if(low>high || qen<low || qst>high)		//no overlap
	{
		node n;
		n.maxf=n.lfeq=n.rfeq=-1;
		return n;
	}
	if(qst <= low && qen>=high)		//total overlap
		return segtree[pos];

	int mid=(low+high)/2;
	node a = query(low,mid,qst,qen,2*pos+1);
	node b = query(mid+1, high, qst, qen, 2*pos+2);
	node temp;
	temp.lfeq = a.lfeq;
	temp.rfeq = b.rfeq;
	temp.maxf = max(a.maxf,b.maxf);
	if(v[mid] == v[mid+1])
		temp.maxf = max(temp.maxf,a.rfeq+b.lfeq);
	return temp;

}

int main()
{
	ios::sync_with_stdio(false);
	// cin.tie(NULL);
	int l,r,n,q;
	while(1)
	{
		cin >> n;
		if(!n)
			break;
		cin >> q;
		for(int i=0;i<n;++i)
			cin >> v[i];
		build(0,n-1,0);
		
		while(q--)
		{
			cin >> l >> r;

			node te = query(0,n-1,l-1,r-1,0);
			cout << te.maxf << '\n';
		}
	}
}