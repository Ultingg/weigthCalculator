package ru.isaykin.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/* Using for checking if list if filtered by id */

@Data
@Slf4j
@AllArgsConstructor
public class MarkerOfFilter {
    private boolean filtered;
    private Long id;

    public MarkerOfFilter() {
    }

    public MarkerOfFilter(boolean filtered) {
        this.filtered = filtered;
    }
}
