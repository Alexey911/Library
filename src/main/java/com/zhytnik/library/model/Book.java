package com.zhytnik.library.model;

import java.io.Serializable;
import java.util.Set;

public class Book extends DomainObject implements Serializable {
    private String name;
    private String annotation;
    private Integer pageCount;
    private String authors;
    private Set<Category> categories;
    private Publisher publisher;
    private Integer publishingYear;
    private Integer weight;

    public Book() {

    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Integer getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(Integer publishingYear) {
        this.publishingYear = publishingYear;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        //noinspection StringBufferReplaceableByString
        return new StringBuilder().append("Book [name = ").append(name).
                append(", authors = ").append(authors).
                append(", publisher = ").append(publisher).
                append(", categories = ").append(categories).append("]").toString();
    }
}