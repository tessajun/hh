package objackie.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import objackie.dao.ComplainDao;
import objackie.dao.ComplainFileDao;
import objackie.service.ComplainService;
import objackie.util.FileUploadUtil;
import objackie.vo.Complain;
import objackie.vo.ComplainFile;

@Service 
public class DefaultComplainService implements ComplainService {
  @Autowired ComplainDao complainDao;
  @Autowired ComplainFileDao complainFileDao;
  
  public List<Complain> getComplainList(int pageNo, int length) throws Exception {
    HashMap<String,Object> map = new HashMap<>();
    map.put("startIndex", (pageNo - 1) * length);
    map.put("length", length);
    return complainDao.selectList(map);
  }
  
  public List<Complain> getComplainListbyRsvd0(int pageNo, int length, String email) throws Exception {
    HashMap<String,Object> map = new HashMap<>();
    map.put("startIndex", (pageNo - 1) * length);
    map.put("length", length);
    map.put("email", email);
    return complainDao.selectListbyRsvd0(map);
  }
 
  public List<Complain> getComplainListbyRsvd1(int pageNo, int length, String email) throws Exception {
    System.out.println("service : " + email);
    HashMap<String,Object> map = new HashMap<>();
    map.put("startIndex", (pageNo - 1) * length);
    map.put("length", length);
    map.put("email", email); 
    System.out.println(map.get("email"));
    return complainDao.selectListbyRsvd1(map);
  }
  
  public void insertComplain(Complain complain, 
      MultipartFile file, String uploadDir) throws Exception {
        
    complainDao.insert(complain);
    
    String newFilename = null;
    if (!file.isEmpty()) {
      newFilename = FileUploadUtil.getNewFilename(file.getOriginalFilename());
        file.transferTo(new File(uploadDir + newFilename));
        ComplainFile complainFile = new ComplainFile();
        complainFile.setFilename(newFilename);
        //complainFile.setComplainNo(complain.getNo());
        complainFile.setComplainNo(10200);
        complainFileDao.insert(complainFile);
       }
    
  }
  
  public Complain getComplain(int no) throws Exception {
    return complainDao.selectOne(no);
  }
  
  public void updateComplain(Complain complain) throws Exception {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", complain.getNo());
    paramMap.put("rsvd", complain.getRsvd());

    complainDao.update(complain);
  }
  
  public void deleteComplain(int no) throws Exception {
    complainDao.delete(no);
  }
  
  public int getTotalPage(int pageSize) throws Exception {
    int countAll = complainDao.countAll();
    
    int totalPage = countAll / pageSize;
    if ((countAll % pageSize) > 0) {
      totalPage++;
    }
    return totalPage;
  }

  public int getTotalPageRsvd0(int pageSize) throws Exception {
    int countAllRsvd0 = complainDao.countAllRsvd0();
    
    int totalPage = countAllRsvd0 / pageSize;
    if ((countAllRsvd0 % pageSize) > 0) {
      totalPage++;
    }
    return totalPage;
  }

  public int getTotalPageRsvd1(int pageSize) throws Exception {
    int countAllRsvd1 = complainDao.countAllRsvd1();
    
    int totalPage = countAllRsvd1 / pageSize;
    if ((countAllRsvd1 % pageSize) > 0) {
      totalPage++;
    }
    return totalPage;
  }


  
  

 
}




