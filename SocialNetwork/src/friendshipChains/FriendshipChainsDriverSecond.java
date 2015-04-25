package friendshipChains;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


class FriendshipChainsSecond {
	
	private int person, distance;
	private String[] names;
	private List<List<Integer>> incidenslist = new ArrayList<List<Integer>>();
	private int n;
	
//	long startTime;
//	long endTime;
//	long totalTime;
	
	public FriendshipChainsSecond() throws IOException {
		
		setIncidenslist();
		System.out.println(friendshipChains2());
		
	}
	
	public void setIncidenslist() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String line = in.readLine();
		StringTokenizer st = new StringTokenizer(line);
		
		n = st.countTokens();
		
		names = new String[n];
		
		for ( int i = 0; i < n; i++ ) {
			incidenslist.add(new ArrayList<Integer>());
			names[i] = st.nextToken();
		}
		
		line = in.readLine();
		while (!line.contains("tvenner")) {
			
			st = new StringTokenizer(line);
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());

			incidenslist.get(i).add(j);
			incidenslist.get(j).add(i);

			line = in.readLine();
	    }
		
		st = new StringTokenizer(line);
		
		st.nextToken();		// skips the "taetvenskab" token
		
		person = Integer.parseInt(st.nextToken());
		distance = Integer.parseInt(st.nextToken());		
			
	}
	
	public String friendshipChains() {

//		startTime = System.currentTimeMillis();
		
		String friends = "";
		
		List<Integer[]> queue = new ArrayList<Integer[]>();
		List<Integer> marked = new ArrayList<Integer>();
		
		queue.add(new Integer[] {person,0});
		marked.add(person);
		
		while (queue.get(0)[1] < distance) {
			for (int i = 0; i < incidenslist.get(queue.get(0)[0]).size(); i++) {
				
				Integer[] friend = new Integer[] {incidenslist.get(queue.get(0)[0]).get(i),queue.get(0)[1]};
				
				if (!marked.contains(friend[0])) {
					queue.add(new Integer[] {friend[0], friend[1] + 1});
					marked.add(friend[0]);
				}
				
			}
			
			queue.remove(0);
			if (queue.size() == 0)
				break;
		}
		
		for (int i = 0; i < marked.size(); i++) {
			int personNumber = marked.get(i);
			friends += names[personNumber];
			if (i < marked.size() - 1) {
				friends += " ";
			}
		}
		
//		endTime = System.currentTimeMillis();
//    	totalTime = endTime - startTime;
//    	System.out.println("\n-------------------------");
//    	System.out.println("Tid:");
//    	System.out.println(totalTime);
		
		return friends;
	}
	
	public String friendshipChains2() {

//		startTime = System.currentTimeMillis();
		
		String friends = "";
		
		int[][] chain = new int[distance+1][n];
		
		chain[0][person] = 1;
		
		for (int i = 0; i < distance; i++) {
			for (int j = 0; j < n; j++) {
				if (chain[i][j] == 1) {
					chain[i+1][j] = 1;
					for (int l = 0; l < incidenslist.get(j).size(); l++) {
						chain[i+1][incidenslist.get(j).get(l)] = 1;
					}
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			
			if (chain[distance][i] == 1) {
				if (i != 0 && !friends.equals(""))
					friends += " ";
				friends += names[i];
			}
				
		}
		
//		endTime = System.currentTimeMillis();
//    	totalTime = endTime - startTime;
//    	System.out.println("\n-------------------------");
//    	System.out.println("Tid:");
//    	System.out.println(totalTime);
		
		return friends;
	}
	
}

public class FriendshipChainsDriverSecond {
	
	public static void main(String[] args) throws IOException {
		
		new FriendshipChainsSecond();
	
	}
}
