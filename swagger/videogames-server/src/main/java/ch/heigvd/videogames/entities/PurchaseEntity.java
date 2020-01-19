package ch.heigvd.videogames.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class PurchaseEntity implements Serializable {
    @Id
    private int purchase_id;

    private String user_id;

    private int videogames_id;


    public int getPurchase_id(){
        return purchase_id;
    }
    public String getUser_id() {
        return user_id;
    }
    public int getVideoGames_id(){return videogames_id;}

    public void setUser_id(String email){
        user_id = email;
    }

    public void setVideoGames_id(int id){
        videogames_id = id;
    }

}
