package swe6733.team2.gbms;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class DB_UserProfile {

    //Class Values
    public String firstName;
    public String lastName;
    public String userName;

    public int age;

    public boolean matchMakingStatus;
    public int matchMakingGroup;

    public int communicationStyle;

    //Default Constructor required
    public DB_UserProfile() {

    }

    //Overloaded Default Constructor for Account Buildings
    public DB_UserProfile (String fName, String lName, String uName, int age) {

        //Set Values
        this.firstName = fName;
        this.lastName = lName;
        this.userName = uName;
        this.age = age;

    }
}
