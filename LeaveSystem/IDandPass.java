package LeaveSystem;

import java.util.*;

public class IDandPass {

  HashMap<String, String> logininfo = new HashMap<String, String>();

  public IDandPass() {
    logininfo.put("Admin", "Kanco2021");
    logininfo.put("admin", "Kanco2021");
    logininfo.put("ADMIN", "Kanco2021");
    logininfo.put("Aragi", "Kanco2021");
    logininfo.put("Joe", "Joe12345");
    logininfo.put("Aragi", "@kanco");
    logininfo.put("User", "Kanco2021");
    logininfo.put("USER", "Kanco2021");
    logininfo.put("user", "Kanco2021");
    logininfo.put("Joe", "0798260636");
  }

  protected HashMap getLogininfo() {
    return logininfo;
  }
}
