package org.study.classroom.controller;

import com.sun.istack.internal.Nullable;
import org.springframework.ui.Model;
import org.study.classroom.utils.BasePageInfo;
import org.study.classroom.utils.Constants;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2018/3/26.
 */
public class  PageHelper {

    /**
     * row 是要显示的页数，如果row==null，则取当前页
     * userList是要被修改的page中的数据
     * @param session HttpSession
     * @param model model
     * @param row the row number to be shown on page
     * @param list the list should be stored in the pageInfo
     */
    public static void getList(HttpSession session, Model model,
                         Integer row, List<?> list,
                         String attrTag) {
        BasePageInfo<?>  pageInfo = (BasePageInfo<?>) session.getAttribute(attrTag);
        // if list is not null then construct PageInfo
        if (row == null) {
            if (pageInfo != null) {
                row = pageInfo.getCurrPage();

            } else {
                row = 1;
            }
        }
        if (list != null || pageInfo == null) {

            pageInfo = new BasePageInfo<>(list);
            pageInfo.setEvePageNum(Constants.PAGE_NUM);
        }

        pageInfo.setCurrPage(row);
        // 将page info 放入model中，在jsp页面中获取
        model.addAttribute(attrTag, pageInfo);
        session.setAttribute(attrTag, pageInfo);
    }



}
