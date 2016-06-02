package com.motodb.controller;

import javafx.collections.ObservableList;

public interface ClassesManager {

    ObservableList<String> getClassesNames();

    void addClass(String name, String rules);

}