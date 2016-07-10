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
    
    
}
