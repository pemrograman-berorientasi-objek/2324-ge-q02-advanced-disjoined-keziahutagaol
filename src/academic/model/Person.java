package academic.model;

/**
 * @author 12S22014 Kezia Hutagaol
 * @author 12S22034 Mulyadi Siahaan
 */

public class Person {

    public String id;
    public String name;

    // constructor
    public Person(String id, String _name) {
        this.id = id;
        this.name = _name;
    }

    public String getId() {
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    
}
