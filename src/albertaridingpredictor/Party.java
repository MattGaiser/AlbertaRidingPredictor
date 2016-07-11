/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package albertaridingpredictor;

/**
 *
 * @author Matthew
 */
public class Party {
    String name;
    double percent;
    int totalVotes;
    
    public Party(String name, double initialPercent)
    {
        this.name = name;
        this.percent = initialPercent; 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
    
    
}
