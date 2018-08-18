package com.bryansinqadu.eparking_app;

public class Bookings {

    private String Name;
    private String BookingId;
    private String Email;
    private String Phone;
    private String Department;
    private String Slot;
    private String Date;
    private String Time;
    private String Status;

    public Bookings(){

    }

    public Bookings(String name, String bookingId, String email, String phone, String department, String slot, String date, String time, String status) {
        Name = name;
        BookingId = bookingId;
        Email = email;
        Phone = phone;
        Department = department;
        Slot = slot;
        Date = date;
        Time = time;
        Status = status;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBookingId() {
        return BookingId;
    }

    public void setBookingId(String bookingId) {
        BookingId = bookingId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getSlot() {
        return Slot;
    }

    public void setSlot(String slot) {
        Slot = slot;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
