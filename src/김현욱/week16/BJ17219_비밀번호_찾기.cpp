#include<iostream>
#include<algorithm>
#include<map>
#include<string>
using namespace std;

map<string, string> ma;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int n, m;
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		string add, pass;
		cin >> add >> pass;
		ma[add] = pass;
	}
	for (int i = 0; i < m; i++) {
		string find;
		cin >> find;
		cout << ma[find] << "\n";
	}
	return 0;
}
