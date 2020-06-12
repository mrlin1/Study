package com.example.demo.jgit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlameEntity {

    private String content;
    private Integer lineNo;
    private LogEntity log;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BlameEntity base = (BlameEntity) obj;
        return log.getRelationId().equals(base.getLog().getRelationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(log.getRelationId());
    }
}
