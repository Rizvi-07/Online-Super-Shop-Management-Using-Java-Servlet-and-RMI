/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class Review implements java.io.Serializable{
    
    int pid;
    String user,review;
    
    public String getUser() {
        return user;
    }

    public void setUser(String email) {
        this.user = email;
    }
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
