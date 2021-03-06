package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// Represents a warrior with some abilities
public class Warrior implements Writable {
    private String name;
    private int attack;
    private int defense;

    // REQUIRES: name has a non-zero length
    // EFFECTS: creates a warrior with the name given
    public Warrior(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    // REQUIRES: name has a non-zero length
    // MODIFIES: this
    // EFFECTS: rename the warrior with the name given
    public void rename(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: set warrior's attack to given value
    public void setAttack(int attack) {
        if (attack > 0) {
            this.attack = attack;
        } else {
            this.attack = 0;
        }
    }

    // MODIFIES: this
    // EFFECTS: set warrior's defense to given value
    public void setDefense(int defense) {
        if (defense > 0) {
            this.defense = defense;
        } else {
            this.defense = 0;
        }
    }

    // EFFECTS: add this warrior to the json file and returns the json file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("attack", attack);
        json.put("defense", defense);
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Warrior warrior = (Warrior) o;
        return Objects.equals(name, warrior.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

/*
 *Title:JsonSerializationDemo
 *Author:Paul Carter
 *Date:Oct 17, 2020
 *Availability:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */