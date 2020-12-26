package ru.isaykin.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;

// Using for checking if list if filtered by id

@Data
@AllArgsConstructor
public class MarkerOfFilter {
    private boolean filtered;
    private Long id;

    public MarkerOfFilter() { }
    public MarkerOfFilter(boolean filtered) {
        this.filtered = filtered;
    }
}
