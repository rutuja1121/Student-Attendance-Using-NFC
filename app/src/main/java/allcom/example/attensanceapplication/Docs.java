package allcom.example.attensanceapplication;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;

public class Docs {
    private String branch;
    private String documentId;


    public List<String> getClass1() {
        return class1;
    }

    public void setClass1(List<String> class1) {
        this.class1 = class1;
    }

    private List<String> class1=new ArrayList<>();
    public int getClassnumber() {
        return classnumber;
    }

    public void setClassnumber(int classnumber) {
        this.classnumber = classnumber;
    }

    private int classnumber;
    public Docs(){
        //public no-arg constructor needed
    }
    @Exclude
    public String getDocumentId() {
        return documentId;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    public Docs(String branch, int classnumber,List<String> class1) {
        this.branch = branch;
        this.classnumber=classnumber;
        this.class1=class1;
    }
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}