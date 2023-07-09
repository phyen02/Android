package com.trinhngocminh.model;

public class Book {
    private int codeBook;
    private String nameBook;
    private String publisher;
    private int timePublished;
    private double priceBook;

    public Book(int codeBook, String nameBook, String publisher, int timePublished, double priceBook) {
        this.codeBook = codeBook;
        this.nameBook = nameBook;
        this.publisher = publisher;
        this.timePublished = timePublished;
        this.priceBook = priceBook;
    }

    public int getCodeBook() {
        return codeBook;
    }

    public void setCodeBook(int codeBook) {
        this.codeBook = codeBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getTimePublished() {
        return timePublished;
    }

    public void setTimePublished(int timePublished) {
        this.timePublished = timePublished;
    }

    public double getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(double priceBook) {
        this.priceBook = priceBook;
    }
}
