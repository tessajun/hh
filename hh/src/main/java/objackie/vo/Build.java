package objackie.vo;

import java.io.Serializable;

public class Build implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int buildNo;              // 임대건물번호 [Primary-key / Auto-increment]
  protected String email;             // 소유주이메일 [Foreign-key]
  protected String reID;              // 부동산아이디
  protected String postNo;            // 우편번호
  protected String basicAddr;         // 기본주소
  protected String detailAddr;        // 상세주소
  protected int reType;               // 부동산유형
  protected int park;                 // 주차가능유형
  
  public int getBuildNo() {
    return buildNo;
  }
  
  public void setBuildNo(int buildNo) {
    this.buildNo = buildNo;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getReID() {
    return reID;
  }
  
  public void setReID(String reID) {
    this.reID = reID;
  }
  
  public String getPostNo() {
    return postNo;
  }
  
  public void setPostNo(String postNo) {
    this.postNo = postNo;
  }
  
  public String getBasicAddr() {
    return basicAddr;
  }
  
  public void setBasicAddr(String basicAddr) {
    this.basicAddr = basicAddr;
  }
  
  public String getDetailAddr() {
    return detailAddr;
  }
  
  public void setDetailAddr(String detailAddr) {
    this.detailAddr = detailAddr;
  }
  
  public int getReType() {
    return reType;
  }
  
  public void setReType(int reType) {
    this.reType = reType;
  }
  
  public int getPark() {
    return park;
  }
  
  public void setPark(int park) {
    this.park = park;
  }

  @Override
  public String toString() {
    return "Build [buildNo=" + buildNo + ", email=" + email + ", reID=" + reID + ", postNo=" + postNo + ", basicAddr="
        + basicAddr + ", detailAddr=" + detailAddr + ", reType=" + reType + ", park=" + park + "]";
  }

}