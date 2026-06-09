package com.pranav.blog.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "blog_tags",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"blog_id", "tag_id"})
        }
)
public class BlogTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    public BlogTag() {
    }

    public Long getId() {
        return id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void setId(Long id) {
        this.id = id;
    }
}