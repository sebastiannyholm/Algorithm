
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SizeOfNetwork {
	
	private int persons, relations;
	
	public SizeOfNetwork() throws IOException {
		this.persons = 0;
		this.relations = 0;
		
		count();
		
		System.out.println(persons + " " + relations);
		
	}
	
	public void count() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		persons = st.countTokens(); 
		
		String line = in.readLine();
		while (!line.contains("stoerrelse")) {
			relations++;   
			
			line = in.readLine();
	    }
		
	}
	
}

public class SizeOfNetworkDriver {

	public static void main(String[] args) throws IOException {
		
		new SizeOfNetwork();
		
	}
	
}
