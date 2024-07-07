import java.util.*;
import java.io.*;

public class bestrelayteam {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int noOfRunners = Integer.parseInt(br.readLine());

        ArrayList<Runner> runners = new ArrayList<>();

        for (int r = 0; r<noOfRunners; r++){
            String nameAndTiming [] = br.readLine().split(" ");
            String name = nameAndTiming[0];
            double timingA = Double.parseDouble(nameAndTiming[1]);
            double timingB = Double.parseDouble(nameAndTiming[2]);

            runners.add(new Runner (name, timingA, timingB));
        }

        ArrayList <RelayTeam> bestRelayTeams = new ArrayList<>();

        for (int r = 0; r<noOfRunners; r++){
            List <Runner> copyOfRunners = new ArrayList<>(runners);
            Runner firstRunner = runners.get(r);

            Collections.sort(copyOfRunners, Comparator.comparingDouble(Runner -> Runner.getTimingB()));

            copyOfRunners.remove(firstRunner);

            List<Runner> lastThreeRunners = copyOfRunners.subList(0, 3);

            double teamTiming = firstRunner.getTimingA();
            for (Runner runner : lastThreeRunners){
                teamTiming += runner.getTimingB();
            }

            bestRelayTeams.add(new RelayTeam (teamTiming, firstRunner, lastThreeRunners));
        }
        RelayTeam bestTeam = Collections.min(bestRelayTeams, Comparator.comparingDouble(RelayTeam -> RelayTeam.getTeamTiming()));
        
        System.out.println(bestTeam.getTeamTiming());
        System.out.println(bestTeam.getFirstRunner().getName());
        System.out.println(bestTeam.getLastThreeRunners());
    }
}


class Runner {
    private String name; 
    private double timingA; 
    private double timingB;

    public Runner(String n, double a, double b){
        name = n;
        timingA = a;
        timingB = b;
    }

    public String getName() {
        return name;
    }

    public double getTimingA() {
        return timingA;
    }

    public double getTimingB() {
        return timingB;
    }

}

class RelayTeam {
    private Runner firstRunner;
    private List <Runner> lastThreeRunners;
    private double teamTiming;

    public RelayTeam(double totalTiming, Runner firstLeg, List<Runner> lastThreeLegs){
        firstRunner = firstLeg;
        lastThreeRunners = lastThreeLegs;
        teamTiming = totalTiming;
    }

    public Runner getFirstRunner(){
        return firstRunner;
    }

    public String getLastThreeRunners(){
        StringBuilder lastThree = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            lastThree.append(lastThreeRunners.get(i).getName()).append("\n");
        }
        return lastThree.toString();
    }

    public double getTeamTiming(){
        return teamTiming;
    }
}