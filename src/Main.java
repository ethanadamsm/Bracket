import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static ArrayList<String[]> lines = new ArrayList<String[]>();
	ArrayList<Team> teams = new ArrayList<Team>();
	static ArrayList<String[]> matchup = new ArrayList<String[]>();
	
	public static void main(String[] args){
		String filename = "ncaa_data_2016.csv";
		String filename2 = "matchup.txt";
		try{
			File file = new File(filename);
			File file2 = new File(filename2);
			Scanner input = new Scanner(file);
			Scanner input2 = new Scanner(file2);
			while(input.hasNextLine()){
				String line = input.nextLine();
				String[] temp = new String[4];
				temp = line.split(",");
				teams.add(temp[0], Float.valueOf(temp[1]), temp[2], Integer.valueOf(temp[3]));
				lines.add(line.split(","));
				System.out.println(line);
			}
			
			while(input2.hasNextLine()){
				String line = input2.nextLine();
				matchup.add(line.split(","));
			}
			input.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	private ArrayList<Team> createTeams(ArrayList<String[]> array){
		ArrayList<Team> finalteams = new ArrayList<Team>();
		for(int i = 1; i < array.size(); i++){
			finalteams.add(array.get(i)[0], Float.valueOf(array.get(i)[1]), array.get(i)[2], Integer.valueOf(array.get(i)[3]));
		}
		return finalteams;
	}
	
}
