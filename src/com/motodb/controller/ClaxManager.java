package com.motodb.controller;

import com.motodb.model.Clax;

import javafx.collections.ObservableList;

public interface ClaxManager {

    ObservableList<String> getClassesNames();
    
    ObservableList<Clax> getClasses();

    void addClass(String name, String rules, Integer index);

}