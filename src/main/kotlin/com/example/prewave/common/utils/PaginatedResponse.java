package com.example.prewave.common.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaginatedResponse<T> {
    private final List<T> content;
    private final int pageNumber;
    private final int pageSize;
    private final boolean hasNext;

    @JsonCreator
    public PaginatedResponse(@JsonProperty("content") List<T> content,@JsonProperty("pageNumber") int pageNumber,@JsonProperty("pageSize") int pageSize,@JsonProperty("hasNext") boolean hasNext) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.hasNext = hasNext;
    }

    public List<T> getContent() {
        return content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public boolean isHasNext() {
        return hasNext;
    }
}
