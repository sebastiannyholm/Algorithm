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
		
		int currentFriend, checked;
		
		for (int i = 0; i < closeFriends.length; i++) {
			currentFriend = closeFriends[i];
			checked = 0;
			
			for (int j = 0; j < incidenslist.get(currentFriend).size(); j++) {
				
				for (int l = 0; l < closeFriends.length; l++) {
				
					if (i == l)
						continue;
					
					if (incidenslist.get(currentFriend).get(j) == closeFriends[l]) {
						checked++;
						break;
					}
					
				}
				
				if (checked == closeFriends.length - 1)
					break;
				
			}
			
			if (checked < closeFriends.length - 1)
				return "nej";
			
		}
		
		return "ja";
		
	}
	
}

public class CloseFriendshipsDriver {

	public static void main(String[] args) throws IOException {
		
		new CloseFriendships();
		
	}
	
}
