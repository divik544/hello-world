#include<bits/stdc++.h>
#define ull unsigned long long int
#define pb push_back
#define mp make_pair
#define fi first
#define se second
using namespace std;
typedef pair<int,int> pii;
typedef vector<int> vi;
int sz= (int)1e5+5;
vi v(sz);
vector< pii > seg(4*sz);	//first contains minimum of some range and second contains max of some range

void buildtree(int low, int high, int pos)
{
	if(low > high)	return;
	if(low == high)
	{
		seg[pos] = mp(v[low],v[low]);
		return;
	}
	int mid=(low+high)/2;
	buildtree(low,mid,2*pos+1);
	buildtree(mid+1,high,2*pos+2);
	seg[pos].fi = min(seg[2*pos+1].fi, seg[2*pos+2].fi);
	seg[pos].se = max(seg[2*pos+1].se, seg[2*pos+2].se);
}

pii query(int qst,int qen,int low,int high,int pos)
{
	if(low>high) return mp(INT_MAX,INT_MIN);

	if(qst<=low && qen>=high)	//total overlap
		return seg[pos];

	if(qst>high || qen<low)
		return mp(INT_MAX,INT_MIN);

	int mid= (low+high)/2;
	pii p1=query(qst, qen, low, mid, 2*pos+1);
	pii p2=query(qst, qen, mid+1, high, 2*pos+2);
	return mp(min(p1.fi, p2.fi), max(p1.se, p2.se));
}

void pointupdate(int k,int val,int lim)
{
	int low=0,high=lim,pos=0;
	while(low < high)
	{
		int mid=(low+high)/2;
		if(mid<=k)
		{
			high=mid;
			pos = 2*pos+1;
		}
		else
		{
			low = mid+1;
			pos=2*pos+2;
		}
	}
	cout << "value will be updated at "<<pos<<endl;
	seg[pos]=mp(val,val);
	while(pos>0)
	{
		pos=(pos-1)/2;
		seg[pos].fi = min(seg[2*pos+1].fi, seg[2*pos+2].fi);
		seg[pos].se = max(seg[2*pos+1].se, seg[2*pos+2].se);
	}
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	int test;
	cin >> test;
	while(test--)
	{
		int n;	cin >> n;

		for(int i = 0; i < n; i++)
			cin >> v[i];
		buildtree(0,n-1,0);
		/*cout<<"segtree is: "<<endl;
		for(int i=0;i<seg.size();i++)
			if(seg[i].fi!=0&&seg[i].se!=0)
			cout<< i << '+' << seg[i].fi << "#" << seg[i].se <<" ";
		cout<<endl;*/
		int q;	cin >> q;
		int l, r,type;
		while(q--)
		{
			//0 based indexing
			cin >> type >> l >> r;
			if(type==0)
			{
				pii ans=query(l,r,0,n-1,0);
				cout << ans.fi << " " << ans.se << endl;
			}
			else if(type==1)
			{
				pointupdate(l,r,n-1);
				cout<<"segtree is: "<<endl;
				for(int i=0;i<seg.size();i++)
					if(seg[i].fi!=0&&seg[i].se!=0)
						cout<< i << "->" << seg[i].fi << "#" << seg[i].se <<" ";
				cout<<endl;
			}
			
		}
	}
}