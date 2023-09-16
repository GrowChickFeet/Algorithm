#include <iostream>
#include<algorithm>
using namespace std;

int arr[100001];
int tree[100001 * 4];
int n,m;

void init(int node,int left,int right) {
	if (left == right) {
		tree[node] = left;
		return;
	}
	int mid = (left + right) / 2;
	init(node * 2, left, mid);
	init(node * 2 + 1, mid + 1, right);
	if (arr[tree[node * 2]] == arr[tree[node * 2 + 1]]) {
		tree[node] = (tree[node * 2] <= tree[node * 2 + 1] ? tree[node * 2] : tree[node * 2 + 1]);
	}
	else {
		tree[node] = (arr[tree[node * 2]] < arr[tree[node * 2 + 1]] ? tree[node * 2] : tree[node * 2 + 1]);
	}
	return;
}

int query(int node, int left, int right, int st, int end) {
	if (st <= left && right <= end) {
		return tree[node];
	}
	if (end < left || right < st) {
		return -1;
	}
	int mid = (left + right) / 2;
	int l = query(node * 2, left, mid, st, end);
	int r = query(node * 2 + 1, mid + 1, right, st, end);
	if (l == -1) {
		return r;
	}
	else if (r == -1) {
		return l;
	}
	else {
		if (arr[l] == arr[r]) {
			return l;
		}
		else {
			return (arr[l] < arr[r] ? l : r);
		}
	}
}

void update(int node, int left, int right,int target,int ind) {
	if (left == right) {
		return;
	}
	if (ind < left || right < ind) {
		return;
	}
	else {
		int mid = (left + right) / 2;
		update(node * 2, left, mid, target, ind);
		update(node * 2 + 1, mid + 1, right, target, ind);
		if (arr[tree[node * 2]] == arr[tree[node * 2 + 1]]) {
			tree[node] = (tree[node * 2] <= tree[node * 2 + 1] ? tree[node * 2] : tree[node * 2 + 1]);
		}
		else {
			tree[node] = (arr[tree[node * 2]] < arr[tree[node * 2 + 1]] ? tree[node * 2] : tree[node * 2 + 1]);
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	init(1, 0, n - 1);
	cin >> m;
	for (int i = 0; i < m; i++) {
		int op, a, b;
		cin >> op >> a >> b;
		if (op == 1) {
			arr[a - 1] = b;
			update(1, 0, n - 1,b, a - 1);

		}
		else {
			cout << query(1, 0, n - 1, a-1, b -1)+1 << "\n";
		}
	}

	return 0;
}