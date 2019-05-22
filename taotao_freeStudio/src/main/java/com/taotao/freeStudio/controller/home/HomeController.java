package com.taotao.freeStudio.controller.home;

import com.sun.tools.internal.jxc.ap.Const;
import com.taotao.common.utils.IDUtils;
import com.taotao.freeStudio.socket.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("home")
public class HomeController {

    @RequestMapping("")
    public ModelAndView home(){
        ModelAndView home = new ModelAndView("home");
        home.addObject(Constants.JSP_SESSION_ID, IDUtils.getRandomId());
        return home;
    }
}
