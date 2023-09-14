package com.avanade.rpg.exceptions;

public class HistoryDoesntExistsException extends RuntimeException {
    public HistoryDoesntExistsException(Object id) {
        super("History doesnt exists: " + id);
    }
}
