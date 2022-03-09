package ru.inovus.mimi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Pair {
    private Long first;
    private Long second;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return (Objects.equals(first, pair.first) && Objects.equals(second, pair.second)) ||
                (Objects.equals(first, pair.second) && Objects.equals(second, pair.first));
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second) + Objects.hash(second, first);
    }
}
