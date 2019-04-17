import java.util.Map;
// import java.util.Set;
import java.util.stream.*;
// import static java.util.stream.Collectors.*; // Collectors.goupingBy -> groupingBy
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.List;

class Stud {
    String name; Sex sex; int id; int score;
    enum Sex { M, F }

    Stud (String name, Sex sex, int id, int score) {
	this.name = name; this.sex = sex; this.id = id; this.score = score; 
    }

    String  getName ()    { return this.name;  }
    Sex     getSex ()     { return this.sex;   }
    int     getId ()      { return this.id;    }
    int     getScore ()   { return this.score; }

    public String toString () {
	return String.format (
	      "[Name:%s,  Sex:%s,  ID:%2d,  Score:%3d]",
	      this.name, (this.sex == Sex.M) ? "M":"F", this.id, this.score );
    }
      
}

public class MapDemo {
    public static void main(String[] args) {
	Stud[] studs = {
	    new Stud("A1", Stud.Sex.M, 1, 89),
	    new Stud("A2", Stud.Sex.F, 2, 80),
	    new Stud("A3", Stud.Sex.M, 3, 90),
	    new Stud("A1", Stud.Sex.M, 4, 80),
	    new Stud("A4", Stud.Sex.F, 5, 77)
	};

	for(Stud s : studs)            // confirm input
	    System.out.println(s);

	// Translate into Map <Name(String), List<Stud>>
	Map<String, List<Stud>> studByName =
	    Stream.of(studs)
	    .collect(Collectors.groupingBy(Stud::getName));
	
	/*      studByName :
	 *  < Key  :   Value >
	 *  <"A1"  :  [["A1",M,1,89]. ["A1",M,4,80]]
	 *   "A2"  :  [["A2",F,2,80]]
	 *   "A3"  :  [["A3",M,3,90]]
	 *   "A4"  :  [["A4",F,5,77]]
	 */

	for(String k : studByName.keySet())           // print keys of studName
		System.out.println(k);

	for(List<Stud> names : studByName.values())   // values() :: Collection<V> = Set<List<Stud>>
	    for(Stud s : names)
		System.out.println(s);

	
	Map<Integer, List<Stud>> studByScore =
	    Stream.of(studs)
	    .collect(Collectors.groupingBy(Stud::getScore));


	/*      studByScore :
	 *  < Key  :   Value >
	 *  < 80  :  [["A2",F,2,80]. ["A1",M,4,80]]
	 *    89  :  [["A1",M,1,89]]
	 *    90  :  [["A3",M,3,90]]
	 *    77  :  [["A4",F,5,77]]
	 */

	for(Integer k : studByScore.keySet())           // print keys of studName
		System.out.println(k);

	for(List<Stud> names : studByScore.values())   // values() :: Collection<V> = Set<List<Stud>>
	    for(Stud s : names)
		System.out.println(s);	

    }
}
