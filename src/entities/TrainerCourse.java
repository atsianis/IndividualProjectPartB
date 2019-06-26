
package entities;

import java.util.Objects;

public class TrainerCourse {
    private int tcid;
    private Course cid;
    private Trainer tid;

    public TrainerCourse() {
    }

    public TrainerCourse(int tcid, Course cid, Trainer tid) {
        this.tcid = tcid;
        this.cid = cid;
        this.tid = tid;
    }

    public int getTcid() {
        return tcid;
    }

    public void setTcid(int tcid) {
        this.tcid = tcid;
    }

    public Course getCid() {
        return cid;
    }

    public void setCid(Course cid) {
        this.cid = cid;
    }

    public Trainer getTid() {
        return tid;
    }

    public void setTid(Trainer tid) {
        this.tid = tid;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.tcid;
        hash = 11 * hash + Objects.hashCode(this.cid);
        hash = 11 * hash + Objects.hashCode(this.tid);
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
        final TrainerCourse other = (TrainerCourse) obj;
        if (this.tcid != other.tcid) {
            return false;
        }
        if (!Objects.equals(this.cid, other.cid)) {
            return false;
        }
        if (!Objects.equals(this.tid, other.tid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tid.getTfname()+" "+this.tid.getTlname()+" (tid:"+this.tid.getTid()+") teaches "+this.cid.getCtitle()+" cid("+this.cid.getCid()+")";
    }
    
    
    
}
