package hw2;
import java.util.*;
import java.util.stream.*;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
class Student {
    String name; boolean isMale; int hak; int ban; int score;
    Student(String name, boolean isMale, int hak, int ban, int score) {
        this.name = name;
        this.isMale = isMale;
        this.hak = hak;
        this.ban = ban;
        this.score = score;
    }
    String getName() { return name;}
    boolean isMale() { return isMale;}
    int getHak() { return hak;}
    int getBan() { return ban;}
    int getScore() { return score;}
    public String toString() {
        return String.format("[%s, %s, %d�г� %d��, %3d��]", name,
                isMale ? "��":"��", hak, ban, score);
    }
    enum Level { HIGH, MID, LOW }
}
public class StreamEx8excelArrayList {
    public static ArrayList<Student> createArrayStudent(String file) {
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<Student> studArr = new ArrayList<Student>();
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

    public static void main(String[] args) {
        String cvsFile = "stud.csv";
        ArrayList<Student> stuArr = createArrayStudent(cvsFile);


        System.out.printf("1. �ܼ��׷�ȭ(�ݺ��� �׷�ȭ)%n");
        Map<Integer, List<Student>> stuByBan =
                //�̷��� ���°ǵ� ����

                stuArr.stream().collect(groupingBy(Student::getBan));


        for(List<Student> ban : stuByBan.values()) {
            for(Student s : ban)
                System.out.println(s);
        }

        System.out.printf("%n2. �ܼ��׷�ȭ(�������� �׷�ȭ)%n");
        Map<Student.Level, List<Student>> stuByLevel =
                stuArr.stream().collect(groupingBy(s-> {
                    if(s.getScore() >= 200) return Student.Level.HIGH;
                    else if(s.getScore() >= 100) return Student.Level.MID;
                    else return Student.Level.LOW;
                }));

        TreeSet<Student.Level> keySet = new TreeSet<>(stuByLevel.keySet());

        for(Student.Level key : keySet) {
            System.out.println("["+key+"]");

            for(Student s : stuByLevel.get(key))
                System.out.println(s);
            System.out.println();
        }

        System.out.printf("%n3. �ܼ��׷�ȭ + ���(������ �л���)%n");
        Map<Student.Level, Long> stuCntByLevel = stuArr.stream()
                .collect(groupingBy(s-> {
                    if(s.getScore() >= 200) return Student.Level.HIGH;
                    else if(s.getScore() >= 100) return Student.Level.MID;
                    else                         return Student.Level.LOW;
                }, counting()));

        for(Student.Level key : stuCntByLevel.keySet())
            System.out.printf("[%s] - %d��, ", key, stuCntByLevel.get(key));
        System.out.println();
/*
                for(List<Student> level : stuByLevel.values()) {
                        System.out.println();
                        for(Student s : level) {
                                System.out.println(s);
                        }
                }
*/
        System.out.printf("%n4. ���߱׷�ȭ(�г⺰, �ݺ�)%n");
        Map<Integer, Map<Integer, List<Student>>> stuByHakAndBan =
                stuArr.stream()
                        .collect(groupingBy(Student::getHak,
                                groupingBy(Student::getBan)
                        ));

        for(Map<Integer, List<Student>> hak : stuByHakAndBan.values()) {
            for(List<Student> ban : hak.values()) {
                System.out.println();
                for(Student s : ban)
                    System.out.println(s);
            }
        }

        System.out.printf("%n5. ���߱׷�ȭ + ���(�г⺰, �ݺ� 1��)%n");
        Map<Integer, Map<Integer, Student>> topStuByHakAndBan = stuArr.stream()
                .collect(groupingBy(Student::getHak,
                        groupingBy(Student::getBan,
                                collectingAndThen(
                                        maxBy(comparingInt(Student::getScore)),
                                        Optional::get
                                )
                        )
                ));

        for(Map<Integer, Student> ban : topStuByHakAndBan.values())
            for(Student s : ban.values())
                System.out.println(s);

        System.out.printf("%n6. ���߱׷�ȭ + ���(�г⺰, �ݺ� �����׷�)%n");
        Map<String, Set<Student.Level>> stuByScoreGroup = stuArr.stream()
                .collect(groupingBy(s-> s.getHak() + "-" + s.getBan(),
                        mapping(s-> {
                            if(s.getScore() >= 200) return Student.Level.HIGH;
                            else if(s.getScore() >= 100) return Student.Level.MID;
                            else                    return Student.Level.LOW;
                        } , toSet())
                ));

        Set<String> keySet2 = stuByScoreGroup.keySet();

        for(String key : keySet2) {
            System.out.println("["+key+"]" + stuByScoreGroup.get(key));
        }

    }
}
