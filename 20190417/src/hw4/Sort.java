package hw4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.parseInt;

class Student implements Comparable<Student>{
    String name;  boolean isMale;  int hak;  int ban;  int score;

    Student(String name, boolean isMale, int hak, int ban, int score) {
        this.name       = name;
        this.isMale     = isMale;
        this.hak        = hak;
        this.ban        = ban;
        this.score  = score;
    }

    String getName() { return name;}
    boolean isMale() { return isMale;}
    int getHak()     { return hak;}
    int getBan()     { return ban;}
    int getScore()   { return score;}

    public String toString() {
        return String.format("[%s, %s, %d학년 %d반, %3d점]", name,
                isMale ? "남":"여", hak, ban, score);
    }

    @Override
    public int compareTo(Student s) {
        return this.score - s.score;
    }

    enum Level { HIGH, MID, LOW }
}
class ScoreCompare implements Comparator<Student> {
    public int compare(Student s1,Student s2){
        if(s1.score<s2.score)return -1;
        else if(s1.score>s2.score)return 1;
        else return 0;
    }
}

public class Sort {
    public static List<Student> createArrayStudent(String file) {
        String line = "";
        String cvsSplitBy = ",";
        //이걸 뭐로 해야하나?
        List<Student> studArr = new ArrayList<Student>();
        String name; boolean isMale; int hak; int ban; int score;


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] studcount = line.split(cvsSplitBy);

                name=studcount[0];
                isMale=studcount[1]=="true" ? true:false;
                hak=parseInt(studcount[2]);
                ban=parseInt(studcount[3]);
                score=parseInt(studcount[4]);

                studArr.add(new Student(name,isMale,hak,ban,score));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return studArr;
    }

    public static void main(String args[]){
        String csvFile = "stud.csv";
        List<Student> stud1 = createArrayStudent(csvFile);
        List<Student> stud2 = createArrayStudent(csvFile);

        Collections.sort(stud1);
        ScoreCompare scoreCompare = new ScoreCompare();
        Collections.sort(stud2,scoreCompare);

        System.out.println("stud1 sorting result");
        for(Student s : stud1){
            System.out.println(s);
        }

        System.out.println("\nstud2 sorting result");
        for(Student s : stud2){
            System.out.println(s);
        }

    }
}
