function queryParams(params) {
	return params;
};
function getTab() {
	var that = this;
	var unit;
	var region;
	var industry;
	var baseclass;
	if (that.unitsValue) {
		unit = that.unitsValue;
	} else {
		unit = $('#allDimUnits option:selected').val() == undefined ? '' : $(
				'#allDimUnits option:selected').val();
	}
	if (that.regionsValue) {
		region = that.regionsValue;
	} else {
		region = $('#allDimRegions option:selected').val() == undefined ? ''
				: $('#allDimRegions option:selected').val();
	}
	if (that.industryValue) {
		industry = that.industryValue;
	} else {
		industry = $('#allDimIndustry option:selected').val() == undefined ? ''
				: $('#allDimIndustry option:selected').val();
	}
	if (that.baseclassValue) {
		baseclass = that.baseclassValue;
	} else {
		baseclass = $('#allDimBaseclass option:selected').val() == undefined ? ''
				: $('#allDimBaseclass option:selected').val();
	}
	var keywords = document.getElementById('itemid').value;

	var url = "queryCollectItems.do?name=" + keywords + "&unit=" + unit
			+ "&region=" + region + "&industry=" + industry + "&baseclass="
			+ baseclass;
	$('#maintable').bootstrapTable('destroy');
	$('#maintable').bootstrapTable({
		method : 'get', // 这里要设置为get，不知道为什么 设置post获取不了
		url : url,
		pagination : true,
		pageList : [ 10, 25, 50, 100 ],
		pageSize : 10,
		pageNumber : 1,
		sidePagination : 'server',// 设置为服务器端分页
		queryParams : queryParams
	// 参数
	});
};

window.onload = function() {
	// 获取所有单位
	$.ajax({
		url : "getAllDimUnits.do",
		type : "get",
		dataType : "json",
		success : function(allDimUnits) {
			this.allDimUnits = [];
			var unitId;
			var unitName;
			document.getElementById("allDimUnits").options.add(new Option("",
					""));
			document.getElementById("allDimUnits").options.add(new Option("其他",
					""));
			for ( var i = 0; i < allDimUnits.length; i++) {
				unitId = allDimUnits[i].unitId;
				unitName = allDimUnits[i].unitName;
				this.allDimUnits.push({
					unitId : unitId,
					unitName : unitName
				});
				document.getElementById("allDimUnits").options.add(new Option(
						unitName, unitId));
			}
		},
		error : function() {
			alert("system error");
		}
	});
	// 获取区域
	$.ajax({
		url : "getAllDimRegions.do",
		type : "get",
		dataType : "json",
		success : function(allDimRegions) {
			this.allDimRegions = [];
			var regionId;
			var regionName;
			document.getElementById("allDimRegions").options.add(new Option("",
					""));
			document.getElementById("allDimRegions").options.add(new Option(
					"其他", ""));
			for ( var i = 0; i < allDimRegions.length; i++) {
				regionId = allDimRegions[i].regionId;
				regionName = allDimRegions[i].regionName;
				this.allDimRegions.push({
					regionId : regionId,
					regionName : regionName
				});
				document.getElementById("allDimRegions").options
						.add(new Option(regionName, regionId));
			}
		},
		error : function() {
			alert("system error");
		}
	});
	// 获取行业
	$.ajax({
		url : "getAllDimIndustrys.do",
		type : "get",
		dataType : "json",
		success : function(allDimIndustrys) {
			this.allDimIndustrys = [];
			var industryId;
			var industryName;
			document.getElementById("allDimIndustry").options.add(new Option(
					"", ""));
			document.getElementById("allDimIndustry").options.add(new Option(
					"其他", ""));
			for ( var i = 0; i < allDimIndustrys.length; i++) {
				industryId = allDimIndustrys[i].industryId;
				industryName = allDimIndustrys[i].industryName;
				this.allDimIndustrys.push({
					industryId : industryId,
					industryName : industryName
				});
				document.getElementById("allDimIndustry").options
						.add(new Option(industryName, industryId));
			}
		},
		error : function() {
			alert("system error");
		}
	});
	// 获取指标
	$.ajax({
		url : "getAllDimBaseclass.do",
		type : "get",
		dataType : "json",
		success : function(allDimBaseclass) {
			this.allDimBaseclass = [];
			var baseclassId;
			var baseclassName;
			document.getElementById("allDimBaseclass").options.add(new Option(
					"", ""));
			document.getElementById("allDimBaseclass").options.add(new Option(
					"其他", ""));
			for ( var i = 0; i < allDimBaseclass.length; i++) {
				baseclassId = allDimBaseclass[i].baseclassId;
				baseclassName = allDimBaseclass[i].baseclassName;
				this.allDimBaseclass.push({
					baseclassId : baseclassId,
					baseclassName : baseclassName
				});
				document.getElementById("allDimBaseclass").options
						.add(new Option(baseclassName, baseclassId));
			}
		},
		error : function() {
			alert("system error");
		}
	});

	/**
	 * $.ajax({ type : "post", url :
	 * "queryCollectItems.do?name=&unit=&region=&industry=&baseclass=", dataType :
	 * "json", success : function(data) { // 将data放入表格中
	 * $("#maintable").bootstrapTable("load", data); }, error : function() { }
	 * });
	 */
	getTab();
};

// 时间控件
$(function() {
	$("#datepicker").datepicker({
		showOtherMonths : true,
		selectOtherMonths : true,
		changeMonth : true,
		changeYear : true
	});
});

// 单位选中事件
function unitsChange() {
	var objS = document.getElementById("allDimUnits");
	var select = objS.options[objS.selectedIndex];
	if (select != "" && select != null) {
		this.unitsValue = parseInt(select.value);
		if (select.text == "其他" && select.value == "") {
			document.getElementById("dimunit").style.display = "block";
		} else {
			document.getElementById("dimunit").style.display = "none";
		}
	}
}

function saveDimunit() {
	var dimUnitName = $("#newDimunits").val();
	$.ajax({
		url : "saveDimUnit.do?dimUnitName=" + dimUnitName,
		type : "get",
		dataType : "json",
		success : function(dimUnit) {
			var unitId = dimUnit.unitId;
			var unitName = dimUnit.unitName;
			var select = document.getElementById("allDimUnits").options;
			select.add(new Option(unitName, unitId));
			select.selectedIndex = select.length - 1;
			unitsChange();
		},
		error : function() {
			alert("system error");
		}
	});
};

// 行业选中事件
function industryChange() {
	var objS = document.getElementById("allDimIndustry");
	var select = objS.options[objS.selectedIndex];
	if (select != "" && select != null) {
		this.industryValue = parseInt(select.value);
		if (select.text == "其他" && select.value == "") {
			document.getElementById("dimIndustry").style.display = "block";
		} else {
			document.getElementById("dimIndustry").style.display = "none";
		}
	}
}

function saveDimIndustry() {
	var dimIndustryName = $("#newDimIndustry").val();
	$.ajax({
		url : "saveDimIndustry.do?dimIndustryName=" + dimIndustryName,
		type : "get",
		dataType : "json",
		success : function(dimIndustry) {
			var industryId = dimIndustry.industryId;
			var industryName = dimIndustry.industryName;
			var select = document.getElementById("allDimIndustry").options;
			select.add(new Option(industryName, industryId));
			select.selectedIndex = select.length - 1;
			industryChange();
		},
		error : function() {
			alert("system error");
		}
	});
};

// 区域选中事件
function regionsChange() {
	var objS = document.getElementById("allDimRegions");
	var select = objS.options[objS.selectedIndex];
	if (select != "" && select != null) {
		this.regionsValue = parseInt(select.value);
		if (select.text == "其他" && select.value == "") {
			document.getElementById("dimRegions").style.display = "block";
		} else {
			document.getElementById("dimRegions").style.display = "none";
		}
	}
}

function saveDimRegion() {
	var dimUnitName = $("#newDimRegions").val();
	$.ajax({
		url : "saveDimUnit.do?dimUnitName=" + dimUnitName,
		type : "get",
		dataType : "json",
		success : function(dimUnit) {
			var regionId = dimUnit.regionId;
			var regionName = dimUnit.regionName;
			var select = document.getElementById("allDimRegions").options;
			select.add(new Option(regionName, regionId));
			select.selectedIndex = select.length - 1;
			regionsChange();
		},
		error : function() {
			alert("system error");
		}
	});
};

// 指标选中事件
function baseclassChange() {
	var objS = document.getElementById("allDimBaseclass");
	var select = objS.options[objS.selectedIndex];
	if (select != "" && select != null) {
		this.baseclassValue = parseInt(select.value);
		if (select.text == "其他" && select.value == "") {
			document.getElementById("dimBaseclass").style.display = "block";
		} else {
			document.getElementById("dimBaseclass").style.display = "none";
		}
	}
}

function saveDimBaseclass() {
	var newDimBaseclass = $("#newDimBaseclass").val();
	// 获取指标
	$.ajax({
		url : "saveDimBaseclass.do?dimBaseclassName=" + newDimBaseclass,
		type : "get",
		dataType : "json",
		success : function(dimBaseclass) {
			var baseclassId = dimBaseclass.baseclassId;
			var baseclassName = dimBaseclass.baseclassName;
			var select = document.getElementById("allDimBaseclass").options;
			select.add(new Option(baseclassName, baseclassId));
			select.selectedIndex = select.length - 1;
			baseclassChange();
		},
		error : function() {
			alert("system error");
		}
	});
};

// 获取抓取状态
function captureState() {
	var captureState = $('#captureState option:selected').val();
	this.captureState = captureState;
}

// 查询事件
function search() {
	/**
	 * var that = this; var unit; var region; var industry; var baseclass; if
	 * (that.unitsValue) { unit = that.unitsValue; } else { unit =
	 * $('#allDimUnits option:selected') .val(); } if (that.regionsValue) {
	 * region = that.regionsValue; } else { region = $('#allDimRegions
	 * option:selected') .val(); } if (that.industryValue) { industry =
	 * that.industryValue; } else { industry = $('#allDimIndustry
	 * option:selected') .val(); } if (that.baseclassValue) { baseclass =
	 * that.baseclassValue; } else { baseclass = $('#allDimBaseclass
	 * option:selected') .val(); } var keywords =
	 * document.getElementById('itemid').value; var ttime = datepicker.value;
	 * var captureState = that.captureState; $.ajax({ type : "post", url :
	 * "queryCollectItems.do?name=" + keywords + "&unit=" + unit + "&region=" +
	 * region + "&industry=" + industry + "&baseclass=" + baseclass, dataType :
	 * "json", success : function(data) { // 将data放入表格中
	 * $("#maintable").bootstrapTable("load", data); }, error : function() { }
	 * });
	 */
	getTab();
}

// 删除按钮
del = function() {
	// 获取当前选中表格行，若没有弹出提示，若有提示是否删除，是则删除刷新，否则不作处理关闭提示框
	var ids = $.map($("#maintable").bootstrapTable('getSelections'), function(
			row) {
		return row.collectItemId;
	});
	var collectItemId = "";
	if (ids == undefined || ids.length == 0) {
		alert("请选择数据！");
	} else {
		collectItemId = ids[0];
	}
	if (collectItemId != null && collectItemId != "") {
		$.ajax({
			type : "get",
			url : "deleteCollectItem.do?collectItemId=" + collectItemId,
			dataType : "json",
			success : function(data) {
				alert('删除成功。');
				search();
			},
			error : function() {
				alert('删除失败。');
			}
		});
	}
};

// 编辑事件
function editPage() {
	var ids = $.map($("#maintable").bootstrapTable('getSelections'), function(
			row) {
		return row.collectItemId;
	});
	if (ids == undefined || ids.length == 0) {
		alert("请选择数据！");
	} else {
		var itemid = ids[0];
		window.location = 'newOrEdit.jsp?itemid=' + itemid;
	}
};

// 停用按钮
stop = function() {
	// 获取当前选中表格行，若没有弹出提示，若有提示是否停用，是则停用刷新，否则不作处理关闭提示框
	var ids = $.map($("#maintable").bootstrapTable('getSelections'), function(
			row) {
		return row.collectItemId;
	});
	var collectItemId = "";
	if (ids == undefined || ids.length == 0) {
		alert("请选择数据！");
	} else {
		collectItemId = ids[0];
	}
	if (collectItemId != null && collectItemId != "") {
		if (window.confirm('你确定要停用吗？')) {
			// alert("确定");
			$.ajax({
				type : "get",
				url : "updateItemStateByItemId.do?itemid=" + collectItemId
						+ "&statue=0",
				dataType : "json",
				success : function(data) {
					search();
				},
				error : function() {

				}
			});
			return true;
		} else {
			// alert("取消");
			return false;
		}
	}
};

// 启用按钮
openState = function() {
	// 获取当前选中表格行，若没有弹出提示，若有提示是否停用，是则停用刷新，否则不作处理关闭提示框
	var ids = $.map($("#maintable").bootstrapTable('getSelections'), function(
			row) {
		return row.collectItemId;
	});
	var collectItemId = "";
	if (ids == undefined || ids.length == 0) {
		alert("请选择数据！");
	} else {
		collectItemId = ids[0];
	}
	if (collectItemId != null && collectItemId != "") {
		if (window.confirm('你确定要启用吗？')) {
			// alert("确定");
			$.ajax({
				type : "get",
				url : "updateItemStateByItemId.do?itemid=" + collectItemId
						+ "&statue=1",
				dataType : "json",
				success : function(data) {
					search();
				},
				error : function() {

				}
			});
			return true;
		} else {
			// alert("取消");
			return false;
		}
	}
};