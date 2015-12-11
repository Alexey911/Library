package com.zhytnik.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book extends DomainObject implements Serializable {
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotNull
    @Column(name = "page_count", nullable = false)
    private Integer pageCount;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "authors", nullable = false, length = 100)
    private String authors;

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Size(min = 0, max = 255)
    @Column(name = "annotation", nullable = true, length = 255)
    private String annotation;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_categories",
            joinColumns = {@JoinColumn(name = "book_id", nullable = true)},
            inverseJoinColumns = {@JoinColumn(name = "category_id", nullable = true)})
    private Set<Category> categories;

    @Column(name = "year", nullable = true)
    private Integer publishingYear;

    @Column(name = "weight", nullable = true)
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
