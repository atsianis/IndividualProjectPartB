
package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Course {
    private int cid;
    private String ctitle;
    private String ctype;
    private String cstream;
    private LocalDate csdate;
    private LocalDate cedate;

    public Course() {
    }

    public Course(int cid, String ctitle, String ctype, String cstream, LocalDate csdate, LocalDate cedate) {
        this.cid = cid;
        this.ctitle = ctitle;
        this.ctype = ctype;
        this.cstream = cstream;
        this.csdate = csdate;
        this.cedate = cedate;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getCstream() {
        return cstream;
    }

    public void setCstream(String cstream) {
        this.cstream = cstream;
    }

    public LocalDate getCsdate() {
        return csdate;
    }

    public void setCsdate(LocalDate csdate) {
        this.csdate = csdate;
    }

    public LocalDate getCedate() {
        return cedate;
    }

    public void setCedate(LocalDate cedate) {
        this.cedate = cedate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.cid;
        hash = 41 * hash + Objects.hashCode(this.ctitle);
        hash = 41 * hash + Objects.hashCode(this.ctype);
        hash = 41 * hash + Objects.hashCode(this.cstream);
        hash = 41 * hash + Objects.hashCode(this.csdate);
        hash = 41 * hash + Objects.hashCode(this.cedate);
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
        final Course other = (Course) obj;
        if (this.cid != other.cid) {
            return false;
        }
        if (!Objects.equals(this.ctitle, other.ctitle)) {
            return false;
        }
        if (!Objects.equals(this.ctype, other.ctype)) {
            return false;
        }
        if (!Objects.equals(this.cstream, other.cstream)) {
            return false;
        }
        if (!Objects.equals(this.csdate, other.csdate)) {
            return false;
        }
        if (!Objects.equals(this.cedate, other.cedate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CourseID: "+this.cid+" Title: "+this.ctitle+" Type: "+this.ctype+" Stream: "+this.cstream+"\n\tLessons start at "+this.csdate.toString()+" and ends at "+this.cedate.toString();
    }
    
    
    
}
