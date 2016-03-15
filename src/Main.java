import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static ArrayList<String[]> lines = new ArrayList<String[]>();
	private static ArrayList<Team> teams = new ArrayList<Team>();
	static ArrayList<String[]> matchup = new ArrayList<String[]>();
	
	public static void main(String[] args){
		String filename = "ncaa_data_2016.csv";
		String filename2 = "matchup.txt";
		try{
			File file = new File(filename);
			File file2 = new File(filename2);
			Scanner input = new Scanner(file);
			Scanner input2 = new Scanner(file2);
			input.nextLine();
			while(input.hasNextLine()){		
				String line = input.nextLine();
				String[] temp = new String[4];
				temp = line.split(",");
				teams.add(new Team(temp[0], Float.parseFloat(temp[1]), temp[2], Integer.parseInt(temp[3])));	
			}
			teams.remove(0);
			
			while(input2.hasNextLine()){
				String line = input2.nextLine();
				matchup.add(line.split(","));
			}
			input.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		ArrayList<Team> teamss = firstFaceOff();
		
	}
	
	private static ArrayList<Team> firstFaceOff(){
		ArrayList<Team> east = new ArrayList<Team>();
		ArrayList<Team> mideast = new ArrayList<Team>();
		ArrayList<Team> south = new ArrayList<Team>();
		ArrayList<Team> west = new ArrayList<Team>();
		ArrayList<Team> finalteams = new ArrayList<Team>();
		
		for(int i = 0; i < teams.size(); i++){
			if(teams.get(i).getRegion() == "East"){
				east.add(teams.get(i));
			}else if(teams.get(i).getRegion() == "Mideast"){
				mideast.add(teams.get(i));
			}else if(teams.get(i).getRegion() == "South"){
				south.add(teams.get(i));
			}else{
				west.add(teams.get(i));
			}
		}
		
		for(int i = 0; i < matchup.size(); i++){
			float pa = east.get(Integer.parseInt(matchup.get(i)[0])).getPercentage() / 100;
			float pb = east.get(Integer.parseInt(matchup.get(i)[1])).getPercentage() / 100;
			float p = (pa - pa * pb) / (pa + pb - 2 * pa * pb);
			float rand = (float) Math.random();
			if(rand <= p){
				if(pa > pb){
					finalteams.add(east.get(Integer.parseInt(matchup.get(i)[0])));
				}else{
					finalteams.add(east.get(Integer.parseInt(matchup.get(i)[1])));
				}
			}
		}
		
		return finalteams;
		
	}
	
}
