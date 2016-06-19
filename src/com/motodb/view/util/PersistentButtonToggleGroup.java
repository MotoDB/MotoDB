package com.motodb.view.util;

import javafx.collections.ListChangeListener.Change;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class PersistentButtonToggleGroup extends ToggleGroup {
    public PersistentButtonToggleGroup() {
        super();
        getToggles().addListener((Change<? extends Toggle> c) -> {
            while (c.next()) {
                for (final Toggle addedToggle : c.getAddedSubList()) {
                    ((ToggleButton) addedToggle).addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
                        if (addedToggle.equals(getSelectedToggle()))
                            e.consume();
                    });
                }
            }
        });
    }
}
