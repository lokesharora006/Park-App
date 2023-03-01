package com.example.park.model;


   
public class Images {

   private String credit;

   private String title;

   private String altText;

   private String caption;

   private String url;

   public void setCredit(String credit){
      this.credit = credit;
   }
   public String getCredit(){
      return this.credit;
   }
   public void setTitle(String title){
      this.title = title;
   }
   public String getTitle(){
      return this.title;
   }
   public void setAltText(String altText){
      this.altText = altText;
   }
   public String getAltText(){
      return this.altText;
   }
   public void setCaption(String caption){
      this.caption = caption;
   }
   public String getCaption(){
      return this.caption;
   }
   public void setUrl(String url){
      this.url = url;
   }
   public String getUrl(){
      return this.url;
   }
}