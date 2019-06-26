
package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Student {
    private int sid;
    private String sfname;
    private String slname;
    private LocalDate sdob;
    private int sfees;
    //needed for task #9 - convenience reasons
    private int numOfCourses;

    public Student() {
    }

    public Student( int sid ,String sfname, String slname, LocalDate sdob, int sfees) {
        this.sid = sid;
        this.sfname = sfname;
        this.slname = slname;
        this.sdob = sdob;
        this.sfees = sfees;
    }
    
    //Only use the following constructor for task #9 --> Busy students
    public Student(int sid, String sfname, String slname, LocalDate sdob, int sfees, int numOfCourses) {
        this.sid = sid;
        this.sfname = sfname;
        this.slname = slname;
        this.sdob = sdob;
        this.sfees = sfees;
        this.numOfCourses = numOfCourses;
    }
    
    

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSfname() {
        return sfname;
    }

    public void setSfname(String sfname) {
        this.sfname = sfname;
    }

    public String getSlname() {
        return slname;
    }

    public void setSlname(String slname) {
        this.slname = slname;
    }

    public LocalDate getSdob() {
        return sdob;
    }

    public void setSdob(LocalDate sdob) {
        this.sdob = sdob;
    }

    public int getSfees() {
        return sfees;
    }

    public void setSfees(int sfees) {
        this.sfees = sfees;
    }

    public int getNumOfCourses() {
        return numOfCourses;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.sid;
        hash = 19 * hash + Objects.hashCode(this.sfname);
        hash = 19 * hash + Objects.hashCode(this.slname);
        hash = 19 * hash + Objects.hashCode(this.sdob);
        hash = 19 * hash + this.sfees;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.sid != other.sid) {
            return false;
        }
        if (this.sfees != other.sfees) {
            return false;
        }
        if (!Objects.equals(this.sfname, other.sfname)) {
            return false;
        }
        if (!Objects.equals(this.slname, other.slname)) {
            return false;
        }
        if (!Objects.equals(this.sdob, other.sdob)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID: "+this.sid+" Name: "+this.sfname+" "+this.slname+" Birthday: "+this.sdob.toString()+" Fees: "+this.sfees;
    }
    
    
    
    
    
    
    
}
