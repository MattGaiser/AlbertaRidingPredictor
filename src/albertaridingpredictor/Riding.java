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
public class Riding {
    String name;
    String winnerOfRiding;
    int total;
    int lib;
    int ap;
    int ndp;
    int gpa;
    int pc;
    int wra;
    
    
    public Riding (String name, int liberal, int albertaParty,int greenParty, int newDemocrats,  int conservatives, int wildrose)
    {
        this.name= name;
        this.lib = liberal;
        this.ap = albertaParty;
        this.ndp = newDemocrats;
        this.gpa = greenParty;
        this.pc = conservatives;
        this.wra = wildrose;
        
        this.total = lib + ap + ndp + gpa+ pc + wra;
        this.winnerOfRiding = winnerOfRiding();
        
    }
    
    private String winnerOfRiding()
    {
        
        // return the String of the largest party name
        if (lib > ap && lib > ndp && lib > gpa && lib > pc && lib > wra )
        {
            return "LIBERAL";
        }
        if (ap > lib && ap > ndp && ap > gpa && ap > pc && ap > wra )
        {
            return "ALBERTA";
        }
        if (ndp > ap && ndp > lib && ndp > gpa && ndp > pc && ndp > wra )
        {
            return "NEW DEMOCRATS";
        }
        if (gpa > ap && gpa > ndp && gpa > lib && gpa > pc && gpa > wra )
        {
            return "GREEN";
        }
        if (pc > ap && pc > ndp && pc > gpa && pc > lib && pc > wra )
        {
            return "CONSERVATIVES";
        }
        if (wra > ap && wra > ndp && wra > gpa && wra > pc && wra > lib )
        {
            return "WILDROSE";
        }
        
        return null;
    }
    
    public void adjustLib(double percentage)
    {
        // .7 NDP .5 CON .1 WRP
       
    }
    
    public void adjustNDP(double percentage)
    {
       // 0.8 LIB 0.5 CON 0.3WRP 
    }
    public void adjustWRP(double percentage)
    {
        // 
    }
    
    public void adjustCon(double percentage)
    {
        
    }
    public void adjustAP(double percentage)
    {
        
    }
    
    public void adjustGPA(double percentage)
    {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLib() {
        return lib;
    }

    public void setLib(int lib) {
        this.lib = lib;
    }

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getNdp() {
        return ndp;
    }

    public void setNdp(int ndp) {
        this.ndp = ndp;
    }

    public int getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getWra() {
        return wra;
    }

    public void setWra(int wra) {
        this.wra = wra;
    }

    public String getWinnerOfRiding() {
        return winnerOfRiding;
    }

    public void setWinnerOfRiding(String winnerOfRiding) {
        this.winnerOfRiding = winnerOfRiding;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}
