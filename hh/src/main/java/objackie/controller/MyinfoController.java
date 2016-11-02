package objackie.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import objackie.dao.MyinfoDao;
import objackie.vo.JsonResult;
import objackie.vo.Member;

@Controller
@RequestMapping("/auth/")
public class MyinfoController {

  @Autowired MyinfoDao myinfoDao;

  @RequestMapping(path="list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="5") int length) throws Exception {

    try {
      HashMap<String,Object> map = new HashMap<>();
      map.put("startIndex", (pageNo - 1) * length);
      map.put("length", length);

      return JsonResult.success(myinfoDao.selectList(map));

    } catch (Exception e) {
      return JsonResult.fail(e.getMessage());
    }
  }

  @RequestMapping(path="add")
  public Object add(Member member) throws Exception {
    try {
      myinfoDao.insert(member);
      return JsonResult.success();

    } catch (Exception e) {
      return JsonResult.fail(e.getMessage());
    }
  }
  
  @RequestMapping(path="update")
  public Object update(Member member, HttpSession session, SessionStatus sessionStatus) throws Exception {
    try {
     
      myinfoDao.update(member);
      sessionStatus.setComplete();
      session.setAttribute("member", member);
      return JsonResult.success();

    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.fail(e.getMessage());
    }
  }

//  @RequestMapping(path="delete")
//  public Object delete(String email, String password) throws Exception {
//    try {
//      HashMap<String,Object> paramMap = new HashMap<>();
//      paramMap.put("email", email);
//      paramMap.put("password", password);
//
//      if (myinfoDao.selectOneByEmailAndPassword(paramMap) == null) {
//        throw new Exception("해당 회원정보가 없거나 암호가 일치하지 않습니다.");
//      }
//      myinfoDao.delete(email);
//      return JsonResult.success();
//
//    } catch (Exception e) {
//      e.printStackTrace();
//      return JsonResult.fail(e.getMessage());
//    }
//  }
}
