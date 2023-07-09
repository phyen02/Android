package com.android.prepareforfinal.Model;

public class Book {
    private int codeBook;
    private String nameBook;
    private String publisher;
    private double priceBook;

    public Book(int codeBook, String nameBook, String publisher, double priceBook) {
        this.codeBook = codeBook;
        this.nameBook = nameBook;
        this.publisher = publisher;
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

    public double getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(double priceBook) {
        this.priceBook = priceBook;
    }
}
