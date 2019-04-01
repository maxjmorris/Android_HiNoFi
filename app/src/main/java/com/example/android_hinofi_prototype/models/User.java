package com.example.android_hinofi_prototype.models;

public class User {

    //fields
    private int userID;
    private String emailAddress;
    private String username;
    private String password;
    private String dateofBirth;





    /***
     *Get set for User ID
     */
    public int getUserID(  )
    {
        return this.userID;
    }
    public void setUserID(int id)
    {
        this.userID = id;
    }
    /***
     *Get set for Username
     */
    public String getUsername()
    {
        return this.username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    /***
     *Get set for password
     */
    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    /***
     *Get set for Email address
     */
    public  String getEmailAddress()
    {
        return this.emailAddress;
    }
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    /***
     *Get set for date of birth
     */

    public String getDateofBirth() {
        return this.dateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        this.dateofBirth = dateofBirth;
    }
}
