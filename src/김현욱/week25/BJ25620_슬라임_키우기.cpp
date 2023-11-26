#include <bits/stdc++.h>
#define fastio cin.tie(0)->sync_with_stdio(0)
using namespace std;
using ll = long long;
int main(){
    int n,m;
    cin >> n >> m;
    priority_queue<ll,vector<ll>,greater<>> pq;
    vector<ll> result;
    int zero = 0;
    for(int i=0;i<n;i++){
        int slime;
        cin >> slime;
        if(!slime) zero++;
        else if(slime <=1e9) pq.push(slime);
        else result.push_back(slime);
    }

    while(m--){
        int x,y;
        cin >> x >> y;

        if(y == 1) continue;
        else if(y == 0){
            while(pq.size() && pq.top() <= x){
                pq.pop();
                zero++;
            }
        }
        else{
            vector<int> temp;
            while(pq.size() && pq.top() <= x){
                int value = pq.top();
                pq.pop();

                if(1ll * value * y <= 1e9) temp.push_back(value * y);
                else result.push_back(1ll * value * y);
            }
            for(int v : temp){
                pq.push(v);
            }
        }
    }

    sort(result.begin(),result.end());
    while(zero--){
        cout<<0<<' ';
    }
    while(pq.size()){
        cout<<pq.top()<<' ';
        pq.pop();
    }
    for(ll v : result){
        cout << v << ' ';
    }
}
