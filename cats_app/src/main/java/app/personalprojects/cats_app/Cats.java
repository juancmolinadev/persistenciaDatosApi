/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.personalprojects.cats_app;

/**
 *
 * @author MolinAnimation
 */
public class Cats {
    
    ConfigProperties properties = ConfigProperties.getInstance();
    
    String id;
    String url;
    String apikey = properties.getCatApiKey();
    String nickName;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApikey() {
        return this.apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public ConfigProperties getProperties() {
        return properties;
    }

    public void setProperties(ConfigProperties properties) {
        this.properties = properties;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    

}
