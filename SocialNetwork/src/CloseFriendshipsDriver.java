import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class CloseFriendships {
	
	private int[] closeFriends;
	private int[][] incidensmatrix;
	
	public CloseFriendships() throws IOException {
		
		setIncidensmatrix();
		System.out.println(checkForCloseFriendships());
		
	}
	
	public void setIncidensmatrix() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = st.countTokens();
		
		incidensmatrix = new int[n][n];
		
		String line = in.readLine();
		while (!line.contains("taetvenskab")) {
			
			st = new StringTokenizer(line);
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());

			incidensmatrix[i][j] = 1;
			incidensmatrix[j][i] = 1;
			
			System.out.println(i);
			System.out.println(j);
			
			line = in.readLine();
	    }
		
		st = new StringTokenizer(line);
		
		closeFriends = new int[st.countTokens() - 1];
		
		st.nextToken();
		
		for (int i = 0; i < closeFriends.length; i++)
			closeFriends[i] = Integer.parseInt(st.nextToken());
		
	}

	public String checkForCloseFriendships() {
		
		for (int i = 0; i < closeFriends.length; i++) {

			for (int j = 0; j < closeFriends.length; j++) {
				if (i == j)
					continue;
				if (incidensmatrix[closeFriends[i]][closeFriends[j]] != 1)
					return "nej";
			}
			
		}
		
		return "ja";
		
	}
	
}

public class CloseFriendshipsDriver {

	public static void main(String[] args) throws IOException {
		
		new CloseFriendships();
		
	}
	
}
