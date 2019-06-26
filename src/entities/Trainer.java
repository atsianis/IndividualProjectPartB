
package entities;

import java.util.Objects;

public class Trainer {
    private int tid;
    private String tfname;
    private String tlname;

    public Trainer() {
    }

    public Trainer(int tid, String tfname, String tlname) {
        this.tid = tid;
        this.tfname = tfname;
        this.tlname = tlname;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTfname() {
        return tfname;
    }

    public void setTfname(String tfname) {
        this.tfname = tfname;
    }

    public String getTlname() {
        return tlname;
    }

    public void setTlname(String tlname) {
        this.tlname = tlname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.tid;
        hash = 71 * hash + Objects.hashCode(this.tfname);
        hash = 71 * hash + Objects.hashCode(this.tlname);
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
        final Trainer other = (Trainer) obj;
        if (this.tid != other.tid) {
            return false;
        }
        if (!Objects.equals(this.tfname, other.tfname)) {
            return false;
        }
        if (!Objects.equals(this.tlname, other.tlname)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tfname+" "+this.tlname+" ID: "+this.tid;
    }
    
    
    
    
    
}
