import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Person {
	
	private int index;
	private String name;
	private List<Person> friends = new ArrayList<Person>(); 
	private List<Integer> strength = new ArrayList<Integer>();
	private Map<Person, Integer> friendList = new HashMap<Person, Integer>();
	
	public Person(int index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public void addFriend(Person friend, Integer strength) {
		this.friends.add(friend);
		this.strength.add(strength);
		friendList.put(friend, strength);
	}
	
}

class MyTree {
	
	private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	private String wayBack = "";
	
	public void insert(Integer friend, Integer parent) {
		map.put(friend, parent);
	}
	
	public void changeParent(Integer friend, Integer newParent) {
		map.put(friend, newParent);
	}
	
	public String getParent(Integer friend) {
		wayBack += friend + " ";
		if (map.get(friend) != null)
			return getParent(map.get(friend));
		 
		return wayBack;
	}
	
	public String getWayBack(Integer friend) {
		
		String chain =  "" + String.valueOf(friend) + " ";
		
		getParent(map.get(friend));
		for (Integer key : map.keySet()) {
			chain += " " + String.valueOf(map.get(key)) + " ";
		}
		
		return map.toString();
	}
	
}

class WeakestFriendshipChains {

	private int start, end;
	private String[] names;
	private List<Person> incidenslist = new ArrayList<Person>();
	private int n;
	
	public WeakestFriendshipChains() throws IOException {
		setIncidenslist();
		System.out.println(checkForWeakestFriendshipChain());
	}

	public void setIncidenslist() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String line = in.readLine();
		
		StringTokenizer st = new StringTokenizer(line);
		
		n = st.countTokens();
		
		for (int i = 0; i < n; i++) 
			incidenslist.add(new Person(i, st.nextToken()));
		
		line = in.readLine();
		while (!line.contains("svagestevenskab")) {
			
			st = new StringTokenizer(line);
			
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int strength = Integer.parseInt(st.nextToken());
			
			incidenslist.get(i).addFriend(incidenslist.get(j), strength);
			incidenslist.get(j).addFriend(incidenslist.get(i), strength);
			
			line = in.readLine();
	    }
		
		st = new StringTokenizer(line);
		
		st.nextToken();		// skips the "taetvenskab" token
		
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
	}

	private String checkForWeakestFriendshipChain() {
		
		PriorityQueue priorityQueue = new PriorityQueue();
		List<Person> marked = new ArrayList<Person>();
		List<Person> used = new ArrayList<Person>();
		
		MyTree myTree = new MyTree();
		Integer[] distance = new Integer[n];
		
		int friend, between;
		String friends = "Done";
		
		Integer[] currentStats = new Integer[2];
		int currentFriend = start;
		int currentDistance = 0;
		
		marked.add(currentFriend);
		used.add(currentFriend);
		myTree.insert(currentFriend, null);
		
		while (currentFriend != end) {
			System.out.println("Check: " + currentFriend);
			used.add(currentFriend);
			
			for (int i = 0; i < incidenslist.get(currentFriend).size(); i++) {
				
				friend = incidenslist.get(currentFriend).get(i)[0];
				between = incidenslist.get(currentFriend).get(i)[1];
				
				System.out.println("For: " + friend);
				
				if (!used.contains(friend)) {
					if (!marked.contains(friend)) {
						priorityQueue.insert(friend, currentDistance+ between);
						distance[friend] = currentDistance + between;
						marked.add(currentFriend);
						myTree.insert(friend, currentFriend);
					} else {
						// Change if key is less then current key
						if (currentDistance + between < distance[friend] ) {
							priorityQueue.changeKey(friend, currentFriend + between);
							myTree.changeParent(friend, currentFriend);
						}
					}
				}
				
			}
			
			currentStats = priorityQueue.extractMin();
			
			currentFriend = currentStats[0];
			currentDistance = currentStats[1];
			
		}
		
		return myTree.getParent(end);
	}
	
}

public class WeakestFriendshipChainsDriver {
	public static void main(String[] args) throws IOException {
		new WeakestFriendshipChains();
	}
}
