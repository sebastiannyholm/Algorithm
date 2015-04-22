import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


class WeakestFriendshipChains {
	
	private int start, end;
	private String[] names;
	private List<List<Integer[]>> incidenslist = new ArrayList<List<Integer[]>>();
	private int n;
	
	public WeakestFriendshipChains() throws IOException {
		setIncidenslist();
		System.out.println(checkForWeakestFriendshipChain());
	}

	public void setIncidenslist() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String line = in.readLine();
		
		StringTokenizer st = new StringTokenizer(line);
		
		int n = st.countTokens();
		
		for (int i = 0; i < n; i++) {
			incidenslist.add(new ArrayList<Integer[]>());
		}
		
		line = in.readLine();
		while (!line.contains("taetvenskab")) {
			
			st = new StringTokenizer(line);
			
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int strength = Integer.parseInt(st.nextToken());
			
			incidenslist.get(i).add(new Integer[] {j, strength});
			incidenslist.get(j).add(new Integer[] {i, strength});
			
			line = in.readLine();
	    }
		
		st = new StringTokenizer(line);
		
		st.nextToken();		// skips the "taetvenskab" token
		
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
	}

	private String checkForWeakestFriendshipChain() {
		return "";
	}
	
}

public class WeakestFriendshipChainsDriver {
	public static void main(String[] args) throws IOException {
		new WeakestFriendshipChains();
	}
}
