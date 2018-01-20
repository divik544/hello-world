import java.util.*;
public class Graphs
{
	int V;
	ArrayList< ArrayList<Integer> > graph = new ArrayList< ArrayList<Integer> >();
	Graphs(int V)
	{
		this.V = V;
		for(int i=0;i<V;i++)
		{
			graph.add(new ArrayList<Integer>());
		}
	}
	void addEdge(int u,int v)
	{
		graph.get(u).add(v);
		graph.get(v).add(u);
	}

	void print()
	{
		for (int i=0;i<V;i++)
		{
			System.out.println("Vertices adjacent to "+i);
			for(int k:graph.get(i))
				System.out.print(k+" ");
			System.out.println();
		}
	}

	void dfs()
	{
		boolean[] vis = new boolean[V];		//by default initialized with false
		for(int i=0;i<V;i++)
			if(!vis[i])
				dfs_inner(vis,i);
	}

	void dfs_inner(boolean[] vis,int u)
	{
		vis[u] = true;
		System.out.print(u+" ");
		for(int i:graph.get(u))
			if(!vis[i])
				dfs_inner(vis,i);
	}

	void bfs(int src)
	{
		boolean[] vis = new boolean[V];
		Deque<Integer> q = new LinkedList<Integer>();
		vis[src] = true;
		q.addLast(src);
		System.out.print(src+" ");
		while(!q.isEmpty())
		{
			int u = q.removeFirst();
			for(int k:graph.get(u))
				if(!vis[k])
				{
					vis[k] = true;
					q.addLast(k);
					System.out.print(k+" ");
				}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no of vertices: ");
		int V = sc.nextInt();
		Graphs g = new Graphs(V);
		System.out.println("Enter Edges(-1 -1 to break): ");
		int u,v;
		while(true)
		{
			u = sc.nextInt();
			v = sc.nextInt();
			if(u == -1 && v == -1)
				break;
			g.addEdge(u,v);
		}
		g.print();
		g.dfs();
		System.out.println();
		g.bfs(0);
	}
}