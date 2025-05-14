package com.java360.pmanager.infrastructure.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;

public final class PaginationHelper  {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private  PaginationHelper() {}

    public  static Pageable createPageable(
            Integer page,
            Integer pageSize,
            String directionStr,
            List<String> properties
    ) {
        return PageRequest.of(
                Optional.ofNullable(page).orElse(0),
                Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE),
                createSort(directionStr, properties)
        );
    }

    private static Sort createSort(String directionStr, List<String> properties) {
        if (!properties.isEmpty()) {
            Direction direction = Direction.fromOptionalString(directionStr).orElse(ASC);
            return  Sort.by(direction, properties.toArray(new String[0]));
        }

        return Sort.unsorted();
    }
}
