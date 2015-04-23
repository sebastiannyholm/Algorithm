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
		
		/*
		 * Run through the whole array with the closeFriends
		 * Set the currentFriend as the "i" friend in the closeFriends array
		 * Set checked close friends to 0, since we haven't checked any yet 
		 */
		for (int i = 0; i < closeFriends.length; i++) {
			currentFriend = closeFriends[i];
			checked = 0;
			
			/*
			 * Run through the whole currentFriend friend list
			 */
			for (int j = 0; j < incidenslist.get(currentFriend).size(); j++) {
				
				/*
				 * Run through the closeFriends array again, to check if all the closeFriends are in the currentFriend friend list
				 * If the friend is found, set checked++, to remember how many friends there has been found in the currentFriend friend list
				 */
				for (int l = 0; l < closeFriends.length; l++) {
				
					if (i == l)
						continue;
					
					if (incidenslist.get(currentFriend).get(j) == closeFriends[l]) {
						checked++;
						break;
					}
					
				}
				
				/*
				 * Check if all closeFriends had been found for the currentFriend and break it they have.
				 */
				if (checked == closeFriends.length - 1)
					break;
				
			}
			
			/*
			 * Check if all the closeFriends has been found for the currentFriend
			 * If not, return "nej", since they were not all friends
			 */
			if (checked < closeFriends.length - 1)
				return "nej";
			
		}
		
		/*
		 * Return "ja", because all the close friends were friends
		 */
		return "ja";
		
	}
	
}

public class CloseFriendshipsDriver {

	public static void main(String[] args) throws IOException {
		
		new CloseFriendships();
		
	}
	
}
