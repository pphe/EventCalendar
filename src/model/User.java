package model;

/**
 * Simple class to simulate a user.
 * 
 * @author Peter Phe
 * @version 1.0
 */
public class User
{
	/** String AccountID of user */	 
    private String myAccountID;
    
    /** String Password of user */
    private String myPassword;
    
    /** String Organization name of user */
    private String myOrganization;
    
    /** String Password of user */
    private String myPhone;
    
    /** String Email of user */
    private String myEmail;
    
    /** String Role of user */
    private String myRole;
    
    /**
     * Constructor.
     */
    public User()
    {
        super();
    }
    
    /**
     * returns the user accountId.
     * 
     * @return String accountID.
     */
    public String getAccountID() 
    {
        return myAccountID;
    }

    /**
     * Set the user accountID.
     * 
     * @param myAccountID String ID to be set
     */
    public void setAccountID(String myAccountID)
    {
        this.myAccountID = myAccountID;
    }
    
    /**
     * returns the User password.
     * 
     * @return String password.
     */
    public String getPassword()
    {
        return myPassword;
    }
    
    /**
     * Set the user password.
     * 
     * @param myPassword String password to be set
     */
    public void setPassword(String myPassword)
    {
        this.myPassword = myPassword;
    }
    
    /**
     * return the Name of the Organization.
     * 
     * @return String Organization
     */
    public String getOrganization()
    {
        return myOrganization;
    }

    /**
     * Set the user Name of the Organization.
     * 
     * @param myOrganization String Organization name
     */
    public void setOrganization(String myOrganization)
    {
        this.myOrganization = myOrganization;
    }

    /**
     * return the Phone number of the User.
     * 
     * @return String Phone number
     */
    public String getPhone()
    {
        return myPhone;
    }
    
    /**
     * Set the user Phone number.
     * 
     * @param myPhone String Phone number to be set     * 
     */
    public void setPhone(String myPhone)
    {
        this.myPhone = myPhone;
    }
    
    /**
     * returns the user Email address
     * 
     * @return String Email
     */
    public String getEmail()
    {
        return myEmail;
    }

    /**
     * Set the user Email address.
     * @param myEmail
     */
    public void setEmail(String myEmail)
    {
        this.myEmail = myEmail;
    }
    
    /**
     * returns the User Role.
     * 
     * @return String Role
     */
    public String getRole()
    {
        return myRole;
    }

    /**
     * Set the user Role
     * 
     * @param myRole String Role
     */
    public void setRole(String myRole)
    {
        this.myRole = myRole;
    }
}
