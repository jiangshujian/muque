package ${packageName}.controller.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.saber.easy.util.ConvertUtil;
import com.saber.easy.util.Pager;
import com.saber.easy.util.Result;
import com.saber.easy.util.transit.TransitPageParam;
import com.saber.easy.util.transit.TransitPageResult;
import com.saber.easy.util.transit.TransitResult;

import ${packageName}.common.BaseController;
import ${packageName}.dto.${tableJUName};
import ${packageName}.service.${tableJUName}Service;

/**
 * @author sj.jiang
 * 
 */
@Controller
public class ${tableJUName}Controller extends BaseController {
	@Resource
	private ${tableJUName}Service ${tableJName}Service;
	private static final String BASE_URI="/api/${tableJUName}/";

	/**
	 * url:http://localhost:8081/api/${tableJUName}/save.do?jsonData=
	 * 
	 * @return
	 */
	@RequestMapping(value = BASE_URI+"save")
	@ResponseBody
	public Result save() {
		String jsonData = this.getParameter("jsonData");
		${tableJUName} data = JSON.parseObject(jsonData, ${tableJUName}.class);
		return this.${tableJName}Service.save(data);
	}

	/**
	 * url:http://localhost:8081/api/${tableJUName}/update.do?jsonData=
	 * 
	 * @return
	 */
	@RequestMapping(value = BASE_URI+"update")
	@ResponseBody
	public Result update() {
		String jsonData = this.getParameter("jsonData");
		${tableJUName} data = JSON.parseObject(jsonData, ${tableJUName}.class);
		return this.${tableJName}Service.update(data);
	}

	/**
	 * url:http://localhost:8081/api/${tableJUName}/delete.do?id=
	 * 
	 * @return
	 */
	@RequestMapping(value = BASE_URI+"delete")
	@ResponseBody
	public Result delete() {
		Long id = ConvertUtil.toLong(this.getParameter("id"));
		return this.${tableJName}Service.delete(id);
	}

	/**
	 * url:http://localhost:8081/api/${tableJUName}/getById.do?id=
	 * 
	 * @return
	 */
	@RequestMapping(value = BASE_URI+"getById")
	@ResponseBody
	public TransitResult<${tableJUName}> getById() {
		Long id = ConvertUtil.toLong(this.getParameter("id"));
		return this.${tableJName}Service.getById(id);
	}

	/**
	 * url:http://localhost:8081/api/${tableJUName}/findByCondition.do?jsonData=
	 * 
	 * @return
	 */
	@RequestMapping(value = BASE_URI+"findByCondition")
	@ResponseBody
	public TransitResult<List<${tableJUName}>> findByCondition() {
		String jsonData = this.getParameter("jsonData");
		${tableJUName} param = JSON.parseObject(jsonData,
				new TypeReference<${tableJUName}>() {
				});
		return this.${tableJName}Service.findByCondition(param);
	}

	/**
	 * url:http://localhost:8081/api/${tableJUName}/findByConditionWithPage.do?jsonData=
	 * 
	 * @return
	 */
	@RequestMapping(value = BASE_URI+"findByConditionWithPage")
	@ResponseBody
	public TransitPageResult<List<${tableJUName}>> findByConditionWithPage() {
		String jsonData = this.getParameter("jsonData");
		JSONObject obj = JSON.parseObject(jsonData);
		TransitPageParam<${tableJUName}> param = new TransitPageParam<${tableJUName}>();
		param.setPager(JSON.parseObject(obj.getString("pager"), Pager.class));
		param.setData(JSON.parseObject(obj.getString("data"), ${tableJUName}.class));
		return this.${tableJName}Service.findByConditionWithPage(param);
	}
}
