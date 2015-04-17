import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


class FriendshipChains {
	
	private int person, distance;
	private String[] names;
	private List<List<Integer>> incidenslist = new ArrayList<List<Integer>>();
	
	public FriendshipChains() throws IOException {
		
		setIncidenslist();
		System.out.println(friendshipChains());
		
	}
	
	public void setIncidenslist() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = st.countTokens();
		
		names = new String[n];
		
		for ( int i = 0; i < n; i++ ) {
			incidenslist.add(new ArrayList<Integer>());
			names[i] = st.nextToken();
		}
		
		String line = in.readLine();
		while (!line.contains("tvenner")) {
			
			st = new StringTokenizer(line);
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());

			incidenslist.get(i).add(j);
			incidenslist.get(j).add(i);

			line = in.readLine();
	    }
		
		st = new StringTokenizer(line);
		
		st.nextToken();
		
		person = Integer.parseInt(st.nextToken());
		distance = Integer.parseInt(st.nextToken());		
			
	}
	
	public String friendshipChains() {

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
		
		return friends;
	}
	
}

public class FriendshipChainsDriver {
	
	public static void main(String[] args) throws IOException {
		
		new FriendshipChains();
	
	}
}
