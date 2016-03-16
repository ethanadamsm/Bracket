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
			
			while(input2.hasNextLine()){
				String line = input2.nextLine();
				matchup.add(line.split(","));
			}
			input.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		for(int i = 0; i < 1; i++){
			Team winner = simulate();
			System.out.println(winner.getName());
			for(int x = 0; x < teams.size(); x++){
				if(winner.getName() == teams.get(x).getName()){
					System.out.println(teams.get(i).getName() + " is the winner");
					teams.get(x).addWin();
				}
			}
		}
//		for(int i = 0; i < teams.size(); i++){
//			System.out.println(teams.get(i).getName() + " " + teams.get(i).getWins());
//		}
		
	}
	
	private static Team simulate(){
		ArrayList<Team> teamss = firstFaceOff();
		findWinner(teamss);
		return teamss.get(0);
	}
	
	private static void findWinner(ArrayList<Team> format){
		while(format.size() > 1){
			ArrayList<Team> temp = new ArrayList<Team>();
			System.out.println("There are " + format.size() + " teams");
			for(int i = 0; i < format.size(); i+=2){
				float pa = format.get(i).getPercentage() / 100;
				float pb = format.get(i + 1).getPercentage() / 100;
				float p = (pa - (pa * pb)) / (pa + pb - (2 * pa * pb));
				float rand = (float) Math.random();
				System.out.println(rand + " " + p + " " + pa + " " + pb + " " + format.get(i).getName() + ": " + format.get(i + 1).getName());
				if(rand <= p){
					System.out.println(format.get(i).getName());
					temp.add(format.get(i));
				}else{
					System.out.println(format.get(i + 1).getName());
					temp.add(format.get(i + 1));	
				}
			}
			System.out.println("--------------");
			format = temp;
		}
	}
	
	private static ArrayList<Team> firstFaceOff(){
		ArrayList<Team> east = new ArrayList<Team>();
		ArrayList<Team> midwest = new ArrayList<Team>();
		ArrayList<Team> south = new ArrayList<Team>();
		ArrayList<Team> west = new ArrayList<Team>();
		ArrayList<Team> finalteams = new ArrayList<Team>();

		for(int i = 0; i < teams.size(); i++){
			if(teams.get(i).getRegion().equals("East")){
				east.add(teams.get(i));
			}else if(teams.get(i).getRegion().equals("Midwest")){
				midwest.add(teams.get(i));
			}else if(teams.get(i).getRegion().equals("South")){
				south.add(teams.get(i));
			}else{
				west.add(teams.get(i));
			}
		}
		
		finalteams.addAll(matchRegion(east));
		finalteams.addAll(matchRegion(midwest));
		finalteams.addAll(matchRegion(south));
		finalteams.addAll(matchRegion(west));
		
		return finalteams;
		
	}
	
	private static ArrayList<Team> matchRegion(ArrayList<Team> teamregion){
		ArrayList<Team> tempteam = new ArrayList<Team>();
		for(int i = 0; i < matchup.size(); i++){
			float pa = teamregion.get(Integer.parseInt(matchup.get(i)[0]) - 1).getPercentage() / 100;
			float pb = teamregion.get(Integer.parseInt(matchup.get(i)[1]) - 1).getPercentage() / 100;
			float p = (pa - pa * pb) / (pa + pb - 2 * pa * pb);
			float rand = (float) Math.random();
			if(rand <= p){
				tempteam.add(teamregion.get(Integer.parseInt(matchup.get(i)[0]) - 1));
			}else{
				tempteam.add(teamregion.get(Integer.parseInt(matchup.get(i)[1]) - 1));	
			}
		}
		return tempteam;
	}
	
}
