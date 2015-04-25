package friendshipChains;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
class Person {
	
	private int index, distance;
	private String name;
	private boolean marked = false;
	private List<Person> friends = new ArrayList<Person>();
	
	public Person(int index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public void addFriend(Person friend) {		// strength of the friendship = weight on graph edges
		this.friends.add(friend);
	}
	
	public List<Person> getFriends(){
		return friends;
	}

	public int getIndex() {
		return index;
	}

	public String getName(){
		return name;
	}
	
	public void setMarked() {
		marked = true;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public void setDistance(int newDistance) {
		this.distance = newDistance;
	}
	
	public int getDistance() {
		return distance;
	}
	
}

class FriendshipChains {
     
    private Person currentPerson;
	private int distance;
	private Person[] incidenslist;
    private int n;
     
    public FriendshipChains() throws IOException {
         
        setIncidenslist();
        System.out.println(friendshipChains());
         
    }
     
    public void setIncidenslist() throws IOException {
         
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         
        String line = in.readLine();
        StringTokenizer st = new StringTokenizer(line);
         
        n = st.countTokens();
        
        incidenslist = new Person[n];
        
        for ( int i = 0; i < n; i++ ) {
            incidenslist[i] = new Person(i, st.nextToken());
        }
         
        line = in.readLine();
        while (!line.contains("tvenner")) {
             
            st = new StringTokenizer(line);
            int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			incidenslist[i].addFriend(incidenslist[j]);
			incidenslist[j].addFriend(incidenslist[i]);
 
            line = in.readLine();
        }
         
        st = new StringTokenizer(line);
         
        st.nextToken();
         
        currentPerson = incidenslist[Integer.parseInt(st.nextToken())];
        distance = Integer.parseInt(st.nextToken());        
             
    }
     
    public String friendshipChains() {
        
        List<Person> queue = new ArrayList<Person>();
        
        String friends = currentPerson.getName();
        
        currentPerson.setMarked();
        currentPerson.setDistance(0);
        
        while (currentPerson.getDistance() < distance) {
        	
        	for (Person friend : currentPerson.getFriends()) {
                
                if (!friend.isMarked()) {
                    queue.add(friend);
                    friend.setMarked();
                    friend.setDistance(currentPerson.getDistance() + 1);
                    friends += " " + friend.getName();
                }
                 
            }
            
            if (queue.size() == 0)
                break;
            currentPerson = queue.remove(0);
            
        }
         
        return friends;
    }
     
}
 
public class FriendshipChainsDriver {
     
    public static void main(String[] args) throws IOException {
         
        new FriendshipChains();
     
    }
}
