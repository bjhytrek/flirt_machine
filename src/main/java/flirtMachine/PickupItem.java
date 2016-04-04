/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flirtMachine;

/**
 *
 * @author mac_lappy
 */
public class PickupItem {
    private int id;
    private String content;
    private int rating;
    private boolean isRated;

    public boolean isIsRated() {
        return isRated;
    }

    public PickupItem(int id, String content, int rating, boolean isRated) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.isRated = isRated;
    }

    public void setIsRated(boolean isRated) {
        this.isRated = isRated;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }    
}
