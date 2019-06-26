
package entities;

import java.util.Objects;

public class Enrollment {
    private int eid;
    private Student sid;
    private Course cid;

    public Enrollment(int eid, Student sid, Course cid) {
        this.eid = eid;
        this.sid = sid;
        this.cid = cid;
    }

    public Enrollment() {
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public Student getSid() {
        return sid;
    }

    public void setSid(Student sid) {
        this.sid = sid;
    }

    public Course getCid() {
        return cid;
    }

    public void setCid(Course cid) {
        this.cid = cid;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.eid;
        hash = 67 * hash + Objects.hashCode(this.sid);
        hash = 67 * hash + Objects.hashCode(this.cid);
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
        final Enrollment other = (Enrollment) obj;
        if (this.eid != other.eid) {
            return false;
        }
        if (!Objects.equals(this.sid, other.sid)) {
            return false;
        }
        if (!Objects.equals(this.cid, other.cid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Enrollment "+this.eid+" :"+" Student "+this.sid.getSfname()+" "+this.sid.getSlname()+"(sid="+this.sid.getSid()+")"+" attends "+this.cid.getCtitle()+" (cid:"+this.cid.getCid()+")";
    }
    
    
    
    
    
    
}
