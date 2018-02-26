package org.sky.dingding.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sky.sys.action.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * 钉钉业务Controller
 * @author weifx
 *
 */
@Controller
public class DDController extends BaseController {

	/**
	 * 显示盘点商品详情列表页面
	 * @param planCode 计划编号
	 * @param cateCode 商品门类编号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/dd/DDController/initBaseCheckDetailListPage/{planCode}/{cateCode}", method = { RequestMethod.GET })
	public ModelAndView initBaseCheckDetailListPage(
			@PathVariable String planCode,
			@PathVariable String cateCode,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("planCode", planCode);
		mv.addObject("cateCode", cateCode);
		mv.setViewName("jsp/dingding/commodity");
		return mv;
	}
}
