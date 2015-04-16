import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class FriendshipChains {
	
	private int 
	private List<String> names = new ArrayList<String>();
	private List<List<Integer>> incidenslist = new ArrayList<List<Integer>>();
	
	public Network() {
		
		setIncidenslist();
		
//		startTime = System.currentTimeMillis();
		
		if (operation.equals("stoerrelse"))
			System.out.println(countPersonAndRelations());
		else if (operation.equals("taetvenskab"))
			System.out.println(checkForCloseFriendship());
		else if (operation.equals("tvenner"))
			System.out.println(friendshipChains());
		
//		setIncidensmatrix();
//		
////		startTime = System.currentTimeMillis();
//		
//		if (operation.equals("stoerrelse"))
//			System.out.println(countPersonAndRelations2());
//		else if (operation.equals("taetvenskab"))
//			System.out.println(checkForCloseFriendship2());
//		else if (operation.equals("tvenner"))
//			System.out.println(friendshipChains2());
		
		
		endTime = System.currentTimeMillis();
    	totalTime = endTime - startTime;
    	System.out.println("\n-------------------------");
    	System.out.println("Tid:");
    	System.out.println(totalTime);
		
	}
	
	public void setIncidenslist() {
		
		Scanner in = new Scanner(System.in);
		
		String[] words;
		String line;
		
		line = in.nextLine();
		words = line.split("[^a-zA-Z0-9]+");
		
		startTime = System.currentTimeMillis();
		
		for ( int i = 0; i < words.length; i++ ) {
			incidenslist.add(new ArrayList<Integer>());
			names.add(words[i]);
			persons++;
		}
		
		while (in.hasNext()) {
			
			line = in.nextLine();
			words = line.split("[^a-zA-Z0-9]+");
			
			if ( words[0].equals("stoerrelse") || words[0].equals("taetvenskab") || words[0].equals("tvenner") ) {
				operation = words[0];
				nodules = new int[words.length - 1];
				for ( int i = 0; i < words.length - 1; i++)
					nodules[i] = Integer.parseInt(words[i + 1]);
				break;
			}
			
			int i = Integer.parseInt(words[0]);
			int j = Integer.parseInt(words[1]);
			
			incidenslist.get(i).add(j);
			incidenslist.get(j).add(i);
			
			relations++;
			
		}
		
//		for (int i = 0; i < incidenslist.size(); i++)
//			System.out.println("["+i+"] = " + incidenslist.get(i));
//		
//		System.out.println("\nResult:");
	}
	
	public void setIncidensmatrix() {
		
		Scanner in = new Scanner(System.in);
		
		String[] words;
		String line;
		
		line = in.nextLine();
		words = line.split("[^a-zA-Z0-9]+");
		
		startTime = System.currentTimeMillis();
		
		incidensmatrix = new int[words.length][words.length];
		
		for ( int i = 0; i < words.length; i++ ) {
			names.add(words[i]);
			persons++;
		}
		
		while (in.hasNext()) {
			
			line = in.nextLine();
			words = line.split("[^a-zA-Z0-9]+");
			
			if ( words[0].equals("stoerrelse") || words[0].equals("taetvenskab") || words[0].equals("tvenner") ) {
				operation = words[0];
				nodules = new int[words.length - 1];
				for ( int i = 0; i < words.length - 1; i++)
					nodules[i] = Integer.parseInt(words[i + 1]);
				break;
			}
			
			int i = Integer.parseInt(words[0]);
			int j = Integer.parseInt(words[1]);
			
			incidensmatrix[i][j] = 1;
			incidensmatrix[j][i] = 1;
			
			relations++;
			
		}
		
//		for (int i = 0; i < incidensmatrix.length; i++) {
//			for (int j = 0; j < incidensmatrix.length; j++)
//				System.out.print(incidensmatrix[i][j] + " ");
//			System.out.println();
//		}
//		System.out.println("\nResult:");
	}
	
	
	public String countPersonAndRelations() {
		
//		int persons = 0, relations = 0;
//		
//		persons = incidenslist.size();
//		
//		for (int i = 0; i < incidenslist.size(); i++)
//			relations += incidenslist.get(i).size();
//		
//		relations = relations / 2;
		
		return persons + " " + relations;
	}
	
	public String countPersonAndRelations2() {
		
		int persons = 0, relations = 0;
		
		persons = incidenslist.size();
		
		for (int i = 0; i < incidenslist.size(); i++)
			relations += incidenslist.get(i).size();
		
		relations = relations / 2;
		
		return persons + " " + relations;
	}
	
	public String checkForCloseFriendship() {
		
		for (int l = 0; l < nodules.length; l++) {
			int nodule = nodules[l];
			
			int count = 0;
			
			for ( int i = 0; i < incidenslist.get(nodule).size(); i++) {
				
				for (int j = 0; j < nodules.length; j++) {
					
					if (l == j)
						continue;
						
					if (incidenslist.get(nodule).get(i) == nodules[j]) {
						count++;
						break;
					}
				
					if (i+j == nodules.length - 1 + incidenslist.get(nodule).size() - 1)
						return "nej";
				}
				
				if (count == nodules.length - 1)
					break;
				
			}
			
		}
		
		return "ja";
		
	}
	
	public String checkForCloseFriendship2() {
		
		for (int i = 0; i < nodules.length; i++) {
			int nodule = nodules[i];
			
			for (int j = 0; j < nodules.length; j++) {
				if (i == j)
					continue;
				if (incidensmatrix[nodules[i]][nodules[j]] != 1)
					return "nej";
			}
			
			
		}
		
		return "ja";
		
	}
	
	public String friendshipChains() {

		String friends = "";
		Integer start = nodules[0], steps = nodules[1];
		
		List<Integer[]> queue = new ArrayList<Integer[]>();
		List<Integer> marked = new ArrayList<Integer>();
		List<Integer> used = new ArrayList<Integer>();
		
		queue.add(new Integer[] {start,0});
		marked.add(start);
		
		System.out.println("Begin chain --------------------");
		
		while (queue.get(0)[1] < steps) {
			
			for (int i = 0; i < incidenslist.get(queue.get(0)[0]).size(); i++) {
				
				Integer[] friend = new Integer[] {incidenslist.get(queue.get(0)[0]).get(i),queue.get(0)[1]};
				
				if (!marked.contains(friend[0])) {
					queue.add(new Integer[] {friend[0], friend[1] + 1});
					marked.add(friend[0]);
				}
				
			}
			
			used.add(queue.remove(0)[0]);
			
		}
		
		for (int i = 0; i < marked.size(); i++) {
			int personNumber = marked.get(i);
			friends += names.get(personNumber);
			if (i < marked.size() - 1) {
				friends += " ";
			}
		}
		
		return friends;
	}
	
	public String friendshipChains2() {
		
		String friends = "";
		Integer start = nodules[0], steps = nodules[1];
		
		List<Integer[]> queue = new ArrayList<Integer[]>();
		List<Integer> marked = new ArrayList<Integer>();
		List<Integer> used = new ArrayList<Integer>();
		
		queue.add(new Integer[] {start,0});
		marked.add(start);
		
		System.out.println("Begin chain --------------------");
		
		while (queue.get(0)[1] < steps) {
			
			for (int i = 0; i < incidenslist.get(queue.get(0)[0]).size(); i++) {
				
				Integer[] friend = new Integer[] {incidenslist.get(queue.get(0)[0]).get(i),queue.get(0)[1]};
				
				if (!marked.contains(friend[0])) {
					queue.add(new Integer[] {friend[0], friend[1] + 1});
					marked.add(friend[0]);
				}
				
			}
			
			used.add(queue.remove(0)[0]);
			
		}
		
		for (int i = 0; i < marked.size(); i++) {
			int personNumber = marked.get(i);
			friends += names.get(personNumber);
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
