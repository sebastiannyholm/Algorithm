import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class FriendPath {		// constructs a tree, covering the branches of and in between friends
	
	private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	private List<Integer> reversedNames = new ArrayList<Integer>();
	
	public void insert(Integer friend, Integer parent) {
		map.put(friend, parent);
	}
	
	public void changeParent(Integer friend, Integer newParent) {
		map.put(friend, newParent);
	}

	public List<Integer> getShortestPath(Integer friend) {		// friend is the end of the friend path
		
		reversedNames.add(friend);
		
		if (map.get(friend) != null)
			return getShortestPath(map.get(friend));
		
		return reversedNames;
	}
}

class WeakestFriendshipChains {

	private int start, end;
	private String[] names;
	private List<List<Integer[]>> incidenslist = new ArrayList<List<Integer[]>>();
	private int n;
	
	long startTime;
	long endTime;
//	long totalTime;
	
	public WeakestFriendshipChains() throws IOException {
		setIncidenslist();
		System.out.println(checkForWeakestFriendshipChain());
	}

	public void setIncidenslist() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String line = in.readLine();
		
		startTime = System.currentTimeMillis();
		
		StringTokenizer st = new StringTokenizer(line);
		
		n = st.countTokens();
		
		names = new String[n];
		
		for ( int i = 0; i < n; i++ ) {
			incidenslist.add(new ArrayList<Integer[]>());
			names[i] = st.nextToken();
		} 	
		
		line = in.readLine();
		while (!line.contains("svagestevenskab")) {
			
			st = new StringTokenizer(line);
			
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			incidenslist.get(i).add(new Integer[] {j, weight});
			incidenslist.get(j).add(new Integer[] {i, weight});
			
			line = in.readLine();
	    }
		
		st = new StringTokenizer(line);
		
		st.nextToken();		// skips the "taetvenskab" token
		
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		endTime = System.currentTimeMillis();
		System.out.println("List: " + (endTime - startTime));
		
		
	}

	private String checkForWeakestFriendshipChain() {
		
		startTime = System.currentTimeMillis();
		
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
		List<Integer> marked = new ArrayList<Integer>();
		List<Integer> used = new ArrayList<Integer>();
		List<Integer[]> friendList = new ArrayList<Integer[]>();
		
		FriendPath friendPath = new FriendPath();
		Integer[] distance = new Integer[n];
		String namesOnPath = "";
		List<Integer> reversedNames = new ArrayList<Integer>();
		
		int friend, weight;
		
		int currentFriend = start;
		int currentDistance = 0, newDistance;
		
		marked.add(currentFriend);
		used.add(currentFriend);
		friendPath.insert(currentFriend, null);
	
		while (currentFriend != end) {
			used.add(currentFriend);
			
			friendList = incidenslist.get(currentFriend);
			
			for (int i = 0; i < friendList.size(); i++) {
				
				friend = friendList.get(i)[0];
				weight = friendList.get(i)[1];
				
				newDistance = currentDistance + weight;
				
				if (!used.contains(friend)) {
					if (!marked.contains(friend)) {
						priorityQueue.insert(friend, newDistance);
						distance[friend] = newDistance;
						marked.add(friend);
						friendPath.insert(friend, currentFriend);
					} else {
						// Change if key is less then current key
						if (newDistance < distance[friend] ) {
							priorityQueue.changeKey(friend, newDistance);
							distance[friend] = newDistance;
							friendPath.changeParent(friend, currentFriend);
						}
					}
				}
				
			}
			
			currentFriend = priorityQueue.extractMin();
			currentDistance = distance[currentFriend];
			
		}
	
		reversedNames = friendPath.getShortestPath(end);
		
		for (int i = reversedNames.size() -1 ; i >= 0; i--){
			if (namesOnPath.equals(""))
				namesOnPath = names[reversedNames.get(i)];
			else
				namesOnPath += " " + names[reversedNames.get(i)];;
		}
		
		endTime = System.currentTimeMillis();
		System.out.println("Algo: " + (endTime - startTime));

		return namesOnPath;

	}
	
}

public class WeakestFriendshipChainsDriverPrimitiv {
	public static void main(String[] args) throws IOException {
		new WeakestFriendshipChains();
	}
}
