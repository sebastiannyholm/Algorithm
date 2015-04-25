package weakestFriendshipChain;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Person {
	
	private int index;
	private String name;
	private List<Person> friends = new ArrayList<Person>(); 
	private List<Integer> weight = new ArrayList<Integer>();
	
	public Person(int index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public void addFriend(Person friend, Integer weight) {		// strength of the friendship = weight on graph edges
		this.friends.add(friend);
		this.weight.add(weight);
	}
	
	public List<Person> getFriends(){
		return friends;
	}

	public List<Integer> getWeight() {
		return weight;
	}

	public int getIndex() {
		return index;
	}

	public String getName(){
		return name;
	}
	
}

class FriendPath {		// constructs a tree, covering the branches of and in between friends
	
	private Map<Person, Person> map = new HashMap<Person, Person>();
	private String namesOnPath = "";
	private List<String> reversedNames = new ArrayList<String>();
	
	public void insert(Person friend, Person parent) {
		map.put(friend, parent);
	}
	
	public void changeParent(Person friend, Person newParent) {
		map.put(friend, newParent);
	}

	public String getShortestPath(Person friend) {		// friend is the end of the friend path
		
		reversedNames.add(friend.getName());
		
		if (map.get(friend) != null)
			return getShortestPath(map.get(friend));
		 
		for (int i = reversedNames.size() -1 ; i >= 0; i--){
			if (namesOnPath.equals(""))
				namesOnPath = reversedNames.get(i);
			else
				namesOnPath += " " + reversedNames.get(i);
		}
		
		return namesOnPath;
	}
}

class WeakestFriendshipChains {

	private Person start, end;
	private List<Person> incidenslist = new ArrayList<Person>();
	private int n;
	
	public WeakestFriendshipChains() throws IOException {
		setIncidenslist();
		System.out.println(checkForWeakestFriendshipChain());
	}

	public void setIncidenslist() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String line = in.readLine();
		long startTime = System.currentTimeMillis();
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
		
		st.nextToken();		// skips the "tvenner" token
		
		start = incidenslist.get(Integer.parseInt(st.nextToken()));		// from the person (token-index)
		end = incidenslist.get(Integer.parseInt(st.nextToken()));		// find the shortest path to this person
		

		long endTime = System.currentTimeMillis();
		System.out.println("Setting up the incidens list: " + (endTime - startTime) + " ms");
		
	}

	private String checkForWeakestFriendshipChain() {
		long startTime = System.currentTimeMillis();
		
		PriorityQueue<Person> priorityQueue = new PriorityQueue<Person>();
		List<Person> marked = new ArrayList<Person>();
		List<Person> used = new ArrayList<Person>();
		
		FriendPath path = new FriendPath();
		Integer[] distance = new Integer[n];
		
		Person friend;
		
		Person currentFriend = start;
		int currentDistance = 0;
		int weight = -1;		// random default value
		
		marked.add(currentFriend);
		used.add(currentFriend);
		path.insert(currentFriend, null);
		List<Person> friendList = new ArrayList<Person>();
		List<Integer> weightList = new ArrayList<Integer>();
		
		while (currentFriend != end) {
		
			used.add(currentFriend);
			friendList = currentFriend.getFriends();
			weightList = currentFriend.getWeight();
			
			for (int i = 0; i < friendList.size(); i++) {
				
				friend = friendList.get(i);
				weight = weightList.get(i);
				
				if (!used.contains(friend)) {
					if (!marked.contains(friend)) {
						priorityQueue.insert(friend, currentDistance + weight);
						distance[friend.getIndex()] = currentDistance + weight;
						marked.add(friend);
						path.insert(friend, currentFriend);
					} else {
						// Change if key is less then current key
						if (currentDistance + weight < distance[friend.getIndex()] ) {
							priorityQueue.changeKey(friend, currentDistance + weight);
							distance[friend.getIndex()] = currentDistance + weight;
							path.changeParent(friend, currentFriend);
						}
					}
				}
			}
			
			currentFriend = (Person) priorityQueue.extractMin();
			currentDistance = distance[currentFriend.getIndex()];
		}	
		
		long endTime = System.currentTimeMillis();
		System.out.println("Using the algorithm: " + (endTime - startTime) + " ms");
		return path.getShortestPath(end);
	}
	
	
}

public class WeakestFriendshipChainsDriver {
	public static void main(String[] args) throws IOException {
		new WeakestFriendshipChains();
	}
}
