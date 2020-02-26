package com.bps.productsurvey.model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PageFilter {
    private int page;
    private int size;
    private String sortBy;

    public PageFilter(){
        this(1,20);
    }
    public PageFilter(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return this.page;
    }

    public int getSize(){
        return this.size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Sort.Order getSortOrder(){
        if (this.sortBy == null)
            return null;
        String[] params = this.sortBy.split(",");
        if (params.length == 2 && params[1].equalsIgnoreCase(Sort.Direction.DESC.name()))
            return new Sort.Order(Sort.Direction.DESC, params[0]);
        return new Sort.Order(Sort.Direction.ASC, params[0]);
    }

    public String getClause() {
        String clause = " LIMIT " + this.size + " OFFSET " + (this.page-1)*this.size;

        Sort.Order sortOrder = this.getSortOrder();
        if (sortOrder != null) {
            clause = " ORDER BY " + sortOrder.getProperty() + " " + sortOrder.getDirection().name() + clause;
        }

        return clause;
    }

    public Pageable getPageable(){
        Sort.Order sortOrder = this.getSortOrder();
        Sort sort = new Sort(sortOrder);
        return new PageRequest(this.page, this.size, sort);
    }

}
