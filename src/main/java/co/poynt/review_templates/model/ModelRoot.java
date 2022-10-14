package co.poynt.review_templates.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ModelRoot
{
    String desc;
    Person person;
    Collection<Person> people;

    // first level map of field->obj
    public Map<String,Object> asMap() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("desc", desc);
        map.put("person", person);
        map.put("people", people);
        return map;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Collection<Person> getPeople() {
        return people;
    }

    public void setPeople(Collection<Person> people) {
        this.people = people;
    }
}
