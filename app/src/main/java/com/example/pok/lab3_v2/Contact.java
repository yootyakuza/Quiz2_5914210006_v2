package com.example.pok.lab3_v2;

/**
 * Created by pok on 24/2/2561.
 */

public class Contact {

    public int _id;
    public String _name;
    public String _phone_number;
    public String _img_path;

//    public Contact(String _name, String _phone_number, String _img_path) {
//        this._name = _name;
//        this._phone_number = _phone_number;
//        this._img_path = _img_path;
//    }
//
    public Contact() {} //for getAllContacts
//
    public Contact(int _id) {// for delete
        this._id = _id;
    } //for delete
}
