#include <cstdio>
#include<vector>
#include<queue>

#define MAX 2005
#define INF 1000000000

using namespace std;

int c[MAX][MAX],f[MAX][MAX],d[MAX][MAX];
vector<int> edge[MAX];

void addLine(int u,int v,int cap,int cost){
	edge[u].push_back(v);
	edge[v].push_back(u);
	c[u][v] = cap;
	d[u][v] = cost;
	d[v][u] = -cost;
}

int mcmf(int source,int sink){
	int sum = 0;
	while(true){
		int prev[MAX],cost[MAX];
		fill(prev,prev+MAX,-1);
		fill(cost,cost+MAX,INF);
		cost[source] = 0;

		queue<int> q;
		q.push(source);

		bool inQ[MAX];
		fill(inQ,inQ+MAX,false);
		inQ[source] = true;

		while(!q.empty()){
			int cur = q.front();
			q.pop();

			inQ[cur]=false;
			for(int i=0;i<edge[cur].size();i++){
				int next = edge[cur][i];
				if(c[cur][next] - f[cur][next] > 0
				  && cost[cur] + d[cur][next] < cost[next]){
					cost[next] = cost[cur] + d[cur][next];
					prev[next] = cur;

					if(!inQ[next]){
						inQ[next] = true;
						q.push(next);
					}
				}
			}
		}
		if(prev[sink] == -1 ) break;

		for(int i=sink;i!=source;i = prev[i]){
			sum+=d[prev[i]][i];
			f[prev[i]][i]++;
			f[i][prev[i]]--;
		}
	}
	return sum;
}

int main() {
	int N,M,from,to,cost;
	while(scanf("%d",&N) !=EOF){
		scanf("%d",&M);

		fill(&c[0][0],&c[MAX-1][MAX], 0);
		fill(&f[0][0],&f[MAX-1][MAX],0);
		fill(&d[0][0],&d[MAX-1][MAX],INF);
		for(int i=0;i<MAX;i++){
			vector<int>().swap(edge[i]);
		}

		int source = N*2+2, sink = N*2+3;

		addLine(source,1*2,2,0);
		addLine(N*2+1,sink,2,0);
		addLine(1*2,1*2+1,2,0);
		addLine(N*2,N*2+1,2,0);
		for(int i=2;i<N;i++){
			addLine(i*2,i*2+1,1,0);
		}
		for(int i=0;i<M;i++){
			scanf("%d %d %d",&from,&to,&cost);
			addLine(from*2+1,to*2,1,cost);
		}

		printf("%d\n",mcmf(source,sink));
	}

	return 0;
}