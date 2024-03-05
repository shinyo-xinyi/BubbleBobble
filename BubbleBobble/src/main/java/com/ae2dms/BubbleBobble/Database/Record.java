package com.ae2dms.BubbleBobble.Database;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The {@link com.ae2dms.BubbleBobble.Database.Record} class stores a record.
 * It contains the score, level in data manager and name of the player in game controller.
 * @see DataManager
 * @see com.ae2dms.BubbleBobble.Controller.GameController
 */
public class Record {
    private IntegerProperty score;
    private IntegerProperty level;
    private StringProperty name;
    private final ObservableList<Record> records = FXCollections.observableArrayList();

    public Record(int score, int level, String name) {
        setScore(score);
        setLevel(level);
        setName(name);
    }

    public final int getScore() {
        return scoreProperty().get();
    }
    public final void setScore(int value) {
        scoreProperty().set(value);
    }
    public final int getLevel() {
        return levelProperty().get();
    }
    public final void setLevel(int value) {
        levelProperty().set(value);
    }
    public final String getName() {
        return nameProperty().get();
    }
    public final void setName(String value) {
        nameProperty().set(value);
    }

    public IntegerProperty scoreProperty() {
        if (score == null) {
            score = new SimpleIntegerProperty();
        }
        return score;
    }
    public IntegerProperty levelProperty() {
        if (level == null) {
            level = new SimpleIntegerProperty();
        }
        return level;
    }
    public StringProperty nameProperty() {
        if (name == null) {
            name = new SimpleStringProperty();
        }
        return name;
    }

    public ObservableList<Record> recordProperty() {
        return records;
    }
}
