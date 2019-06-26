
package entities;

import java.util.Objects;

public class Homework {
    private int hwid;
    private Student sid;
    private Assignment aid;
    private int omark;
    private int tmark;

    public Homework() {
    }

    public Homework(int hwid, Student sid, Assignment aid, int omark, int tmark) {
        this.hwid = hwid;
        this.sid = sid;
        this.aid = aid;
        this.omark = omark;
        this.tmark = tmark;
    }

    public int getHwid() {
        return hwid;
    }

    public void setHwid(int hwid) {
        this.hwid = hwid;
    }

    public Student getSid() {
        return sid;
    }

    public void setSid(Student sid) {
        this.sid = sid;
    }

    public Assignment getAid() {
        return aid;
    }

    public void setAid(Assignment aid) {
        this.aid = aid;
    }

    public int getOmark() {
        return omark;
    }

    public void setOmark(int omark) {
        this.omark = omark;
    }

    public int getTmark() {
        return tmark;
    }

    public void setTmark(int tmark) {
        this.tmark = tmark;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.hwid;
        hash = 37 * hash + Objects.hashCode(this.sid);
        hash = 37 * hash + Objects.hashCode(this.aid);
        hash = 37 * hash + this.omark;
        hash = 37 * hash + this.tmark;
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
        final Homework other = (Homework) obj;
        if (this.hwid != other.hwid) {
            return false;
        }
        if (this.omark != other.omark) {
            return false;
        }
        if (this.tmark != other.tmark) {
            return false;
        }
        if (!Objects.equals(this.sid, other.sid)) {
            return false;
        }
        if (!Objects.equals(this.aid, other.aid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student "+this.sid.getSfname()+" "+this.sid.getSlname()+"(sid="+this.sid.getSid()+")"+" has the assignment "+this.aid.getAtitle()+" (aid:"+this.aid.getAid()+ "of course: "+this.aid.getCid().getCtitle()+")\n\twith oral mark "+this.omark+" and total mark "+this.tmark;
    }
    
    
    
}
