package com.lambo.upms.server.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.validator.LengthValidator;
import com.lambo.upms.common.constant.UpmsResult;
import com.lambo.upms.common.constant.UpmsResultConstant;
import com.lambo.upms.server.dao.model.*;
import com.lambo.upms.server.service.api.UpmsSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统controller
 * Created by lambo on 2016/12/18.
 */
@Controller
@Api(value = "系统管理", description = "系统管理")
@RequestMapping("/manage/system")
public class UpmsSystemController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UpmsSystemController.class);

	@Autowired
	private UpmsSystemService upmsSystemService;

	@ApiOperation(value = "系统列表")
	@RequiresPermissions("upms:system:read")
	@RequestMapping(value = "/list")
	@ResponseBody
	@LogAround("系统列表")
	@EnableExportTable
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, defaultValue = "", value = "search") String search,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {

        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			upmsSystemExample.setOrderByClause(sort + " " + order);
		}
		if (StringUtils.isNotBlank(search)) {
			upmsSystemExample.or().andTitleLike("%" + search + "%");
		}

		PageHelper.startPage(offset, limit);
		List<UpmsSystem> data = upmsSystemService.selectByExample(upmsSystemExample);
		PageInfo page = new PageInfo(data);

		Map<String, Object> result = new HashMap<>();
		result.put("rows", page.getList());
		result.put("total", page.getTotal());
		return result;
	}

	@ApiOperation(value = "新增系统")
	@RequiresPermissions("upms:system:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(
		    @RequestParam(required = false, value = "icon") String icon,
		    @RequestParam(required = true, value = "basepath") String basepath,
			@RequestParam(required = true, value = "status") String status,
		    @RequestParam(required = true, value = "name") String name) {

		UpmsSystem upmsSystem = new UpmsSystem();
		upmsSystem.setName(name);
		upmsSystem.setBasepath(basepath);
		upmsSystem.setIcon(icon);
		upmsSystem.setStatus(NumberUtils.toByte(status));
		upmsSystem.setTitle(name);

		ComplexResult result = FluentValidator.checkAll()
				.on(upmsSystem.getTitle(), new LengthValidator(1, 20, "标题"))
				.on(upmsSystem.getName(), new LengthValidator(1, 20, "名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		long time = System.currentTimeMillis();
		upmsSystem.setCtime(time);
		upmsSystem.setOrders(time);
		int count = upmsSystemService.insertSelective(upmsSystem);
		return new UpmsResult(UpmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "删除系统")
	@RequiresPermissions("upms:system:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = upmsSystemService.deleteByPrimaryKeys(ids);
		return new UpmsResult(UpmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "根据ID查询系统")
	@RequiresPermissions("upms:system:read")
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@PathVariable("id") int id) {
		UpmsSystem system = upmsSystemService.selectByPrimaryKey(id);
		return new UpmsResult(UpmsResultConstant.SUCCESS, system);
	}

	@ApiOperation(value = "修改系统")
	@RequiresPermissions("upms:system:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(
			@PathVariable("id") int id,
			@RequestParam(required = false, value = "icon") String icon,
			@RequestParam(required = true, value = "basepath") String basepath,
			@RequestParam(required = true, value = "status") String status,
			@RequestParam(required = true, value = "name") String name) {

		UpmsSystem upmsSystem = new UpmsSystem();
		upmsSystem.setName(name);
		upmsSystem.setBasepath(basepath);
		upmsSystem.setIcon(icon);
		upmsSystem.setStatus(NumberUtils.toByte(status));
		upmsSystem.setTitle(name);

		ComplexResult result = FluentValidator.checkAll()
				.on(upmsSystem.getTitle(), new LengthValidator(1, 20, "标题"))
				.on(upmsSystem.getName(), new LengthValidator(1, 20, "名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		upmsSystem.setSystemId(id);
		int count = upmsSystemService.updateByPrimaryKeySelective(upmsSystem);
		return new UpmsResult(UpmsResultConstant.SUCCESS, count);
	}

}