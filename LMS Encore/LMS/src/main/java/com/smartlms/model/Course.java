package com.smartlms.model;

public class Course {
    private long id;
    private String title;
    private String description;
    private String instructor;
    private boolean enrolled;
    public Course(long id, String title, String description, String instructor, boolean enrolled) {
        this.id=id; this.title=title; this.description=description; this.instructor=instructor; this.enrolled=enrolled;
    }
    public long getId(){return id;}
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public String getInstructor(){return instructor;}
    public boolean isEnrolled(){return enrolled;}
}
