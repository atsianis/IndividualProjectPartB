
package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Assignment {
    private int aid;
    private Course cid;
    private String atitle;
    private String adescr;
    private LocalDate adate;
    private int aomark;
    private int atmark;
   

    public Assignment() {
    }

    public Assignment( int aid, Course cid, String atitle, String adescr, LocalDate adate, int aomark, int atmark) {
        this.aid = aid;
        this.cid = cid;
        this.atitle = atitle;
        this.adescr = adescr;
        this.adate = adate;
        this.aomark = aomark;
        this.atmark = atmark;
        
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle;
    }

    public String getAdescr() {
        return adescr;
    }

    public void setAdescr(String adescr) {
        this.adescr = adescr;
    }

    public LocalDate getAdate() {
        return adate;
    }

    public void setAdate(LocalDate adate) {
        this.adate = adate;
    }

    public int getAomark() {
        return aomark;
    }

    public void setAomark(int aomark) {
        this.aomark = aomark;
    }

    public int getAtmark() {
        return atmark;
    }

    public void setAtmark(int atmark) {
        this.atmark = atmark;
    }

    public Course getCid() {
        return cid;
    }

    public void setCid(Course cid) {
        this.cid = cid;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.aid;
        hash = 59 * hash + Objects.hashCode(this.atitle);
        hash = 59 * hash + Objects.hashCode(this.adescr);
        hash = 59 * hash + Objects.hashCode(this.adate);
        hash = 59 * hash + this.aomark;
        hash = 59 * hash + this.atmark;
        hash = 59 * hash + Objects.hashCode(this.cid);
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
        final Assignment other = (Assignment) obj;
        if (this.aid != other.aid) {
            return false;
        }
        if (this.aomark != other.aomark) {
            return false;
        }
        if (this.atmark != other.atmark) {
            return false;
        }
        if (!Objects.equals(this.atitle, other.atitle)) {
            return false;
        }
        if (!Objects.equals(this.adescr, other.adescr)) {
            return false;
        }
        if (!Objects.equals(this.cid, other.cid)) {
            return false;
        }
        if (!Objects.equals(this.adate, other.adate)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "assignmentID: "+this.aid+" of course: "+this.cid.getCtitle()+": Title:"+this.atitle+" Description: "+this.adescr+"\n\t\thas to be submitted until "+this.adate.toString()+" and has the following maximum marks(Oral Mark,Total Mark)= "+ "("+this.aomark+","+this.atmark+")";
    }
    
    
    
}
