package closeFriendships;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CloseFriendships {
	
	private int[] closeFriends;
	private List<List<Integer>> incidenslist = new ArrayList<List<Integer>>();
	
	public CloseFriendships() throws IOException {
		
		setIncidenslist();
		System.out.println(checkForCloseFriendships());
		
	}
	
	public void setIncidenslist() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String line = in.readLine();
		
		StringTokenizer st = new StringTokenizer(line);
		
		int n = st.countTokens();
		
		for (int i = 0; i < n; i++) {
			incidenslist.add(new ArrayList<Integer>());
		}
		
		line = in.readLine();
		while (!line.contains("taetvenskab")) {
			
			st = new StringTokenizer(line);
			
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
		
			incidenslist.get(i).add(j);
			incidenslist.get(j).add(i);
			
			line = in.readLine();
	    }
		
		st = new StringTokenizer(line);
		
		closeFriends = new int[st.countTokens() - 1];
		
		st.nextToken();		// skips the "taetvenskab" token
		
		for (int i = 0; i < closeFriends.length; i++)
			closeFriends[i] = Integer.parseInt(st.nextToken());
		
	}

	public String checkForCloseFriendships() { 
		
		for (int i = 0; i < closeFriends.length; i++) {
			for (int j = i + 1; j < closeFriends.length; j++) {
				if (!areFriends(closeFriends[i], closeFriends[j]))
					return "nej";
			}
		}
		return "ja";
	}

	private boolean areFriends(int currentFriend, int checkFriend) { 
		
		for (Integer friend : incidenslist.get(currentFriend)) {
			if (friend == checkFriend) {
				return true;
			}
		}
		
		return false;
	}
	
}

public class CloseFriendshipsDriver {

	public static void main(String[] args) throws IOException {
		
		new CloseFriendships();
		
	}
	
}
