/**
 * Created by Lang on 2016/1/7.
 */

//TODO初始化页面(区分新增或编辑进入)
window.onload = function(){
	//获取所有单位
    $.ajax({
      url:"getAllDimUnits.do",
      type:"get",
      dataType:"json",
      success:function(allDimUnits){
    	  this.allDimUnits = [];
          var unitId;
          var unitName;
          for(var i=0;i<allDimUnits.length;i++) {
        	  unitId = allDimUnits[i].unitId;
        	  unitName = allDimUnits[i].unitName;
        	  this.allDimUnits.push({
        		  unitId : unitId,
        		  unitName : unitName
        	  });
        	  document.getElementById("allDimUnits").options.add(new Option(unitName, unitId));
          }
          
      },
      error:function(){
        alert("system error");
      }
    });
    
    //获取区域
    $.ajax({
        url:"getAllDimRegions.do",
        type:"get",
        dataType:"json",
        success:function(allDimRegions){
        	this.allDimRegions = [];
            var regionId;
            var regionName;
            for(var i=0;i<allDimRegions.length;i++) {
            	regionId = allDimRegions[i].regionId;
            	regionName = allDimRegions[i].regionName;
          	  this.allDimRegions.push({
          		regionId : regionId,
          		regionName : regionName
          	  });
          	  document.getElementById("allDimRegions").options.add(new Option(regionName, regionId));
            }
        },
        error:function(){
          alert("system error");
        }
    });
    
    //获取行业
    $.ajax({
        url:"getAllDimIndustrys.do",
        type:"get",
        dataType:"json",
        success:function(allDimIndustrys){
          this.allDimIndustrys = [];
          var industryId;
          var industryName;
          for(var i=0;i<allDimIndustrys.length;i++) {
        	  industryId = allDimIndustrys[i].industryId;
        	  industryName = allDimIndustrys[i].industryName;
        	  this.allDimIndustrys.push({
        		  industryId : industryId,
        		  industryName : industryName
        	  });
        	  document.getElementById("allDimIndustry").options.add(new Option(industryName, industryId));
          }
        },
        error:function(){
          alert("system error");
        }
    });
    
    //获取指标
    $.ajax({
        url:"getAllDimBaseclass.do",
        type:"get",
        dataType:"json",
        success:function(allDimBaseclass){
          this.allDimBaseclass = [];
          var baseclassId;
          var baseclassName;
          for(var i=0;i<allDimBaseclass.length;i++) {
        	  baseclassId = allDimBaseclass[i].baseclassId;
        	  baseclassName = allDimBaseclass[i].baseclassName;
        	  this.allDimBaseclass.push({
        		  baseclassId : baseclassId,
        		  baseclassName : baseclassName
        	  });
        	  document.getElementById("allDimBaseclass").options.add(new Option(baseclassName, baseclassId));
          }
        },
        error:function(){
          alert("system error");
        }
    });
    
    //数据load事件
    $(function(){
    	var itemid = GetUrlItemid();  //判断url是否有参数
        if(itemid){ 
		    $.ajax({
		    	url:"queryCollectDataByItemId.do?itemid=" + itemid,
		    	type:"get",
		    	dataType:"json",
		    	success:function(data){
		    		// 将data放入表格中
					$("#righttable").bootstrapTable("load", data);
		    	},
		        error:function(){
		            alert("system error");
		        }
		    });
		    
		    $.ajax({
		    	url:"queryCollectItemByCollectItemId.do?collectItemId=" + itemid,
		    	type:"get",
		    	dataType:"json",
		    	success:function(data){
		    		// 将data放入各个控件中
		    		putValue(data);
		    	},
		        error:function(){
		            alert("system error");
		        }
		    });
        }
    });
    
};

//判断url是否有参数
function GetUrlItemid() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    var itemid;
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        itemid = str.split("=")[1];
        this.id = itemid;
    } 
    return itemid;
}

//回填参数
function putValue(data){
	document.getElementById("name").value = data.name;
	document.getElementById("keyword").value = data.keyword;
	document.getElementById("url").value = data.url;
	document.getElementById("baseurl").value = data.baseurl;
	document.getElementById("dateformat").value = data.dateFormat;
	document.getElementById("daterange").value = data.dateRange;
	document.getElementById("valueformat").value = data.valueFormat;
	document.getElementById("valuerange").value = data.valueRange;
    $("#allDimUnits").val(data.unitId);
    $("#allDimIndustry").val(data.industryId);
    $("#allDimRegions").val(data.regionId);
    $("#allDimBaseclass").val(data.baseclassId);
    $("#method").val(data.method);
    $("#dateType").val(data.dateTypeID);
    $("#codrule").val(data.charset); 
    var backMap = data.steplist;
    if (backMap && backMap.length >= 1) {
    	document.getElementById("infotype").value = backMap[0][0].infotype;
    	document.getElementById("crawltype").value = backMap[0][0].crawltype;
    	document.getElementById("crawlrule").value = backMap[0][0].crawlrule;
    	for(var i = 1; i<backMap[0].length; i++) {
    		baseOnplus();
    		document.getElementById("infotype_"+i).value = backMap[0][i].infotype;
    		document.getElementById("crawltype_"+i).value = backMap[0][i].crawltype;
        	document.getElementById("crawlrule_"+i).value = backMap[0][i].crawlrule;
    	}
    	for (var e=1;e<backMap.length;e++) {
    		creatStep();
    		document.getElementById("infotypeStep_"+e).value = backMap[e][0].infotype;
    		document.getElementById("crawltypeStep_"+e).value = backMap[e][0].crawltype;
    		document.getElementById("crawlruleStep_"+e).value = backMap[e][0].crawlrule;
    		for (var h=1;h<backMap[e].length;h++) {
    			onplus();
    			document.getElementById("infotypeAddStep_"+h).value = backMap[e][h].infotype;
    			document.getElementById("crawltypeAddStep_"+h).value = backMap[e][h].crawltype;
    			document.getElementById("crawlruleAddStep_"+h).value = backMap[e][h].crawlrule;
    		}
    	}
    }
}

// 日期控件
$(function () {
    $("#datepicker").datepicker({
        showOtherMonths: true,
        selectOtherMonths: true,
        changeMonth: true,
        changeYear: true
    });
});

//重置抓取事件
function clearCap(){
	$("#method").val("1");
	$('#url').val("");
	$('#baseurl').val("");
	$("#dateType").val("4"); 
	$("#codrule").val("1"); 
	$("#infotype").val("1"); 
	$("#crawltype").val("1"); 
	$('#crawlrule').val("");
	var basesize = $(".basediv").size();
	for (var j=0;j<basesize;j++){
		baseDelstep();
	}
	var size = $(".hangdiv").size();
	for (var i=0; i< size; i++) {
		delStep();
	}
}

//重置检测事件
function clearDet(){
	$("#dateformatSel").val("1"); 
	$("#daterangeSel").val("1"); 
	$("#valueformatSel").val("1"); 
	$("#valuerangeSel").val("1"); 
	$('#dateformat').val("");
	$('#daterange').val("");
	$('#valueformat').val("");
	$('#valuerange').val("");
}

//抓取测试
function testCap(){
	var testCapMap = toJson();
	$.ajax({
	      url:"testCrawlData.do?params=" + JSON.stringify(testCapMap).replace(/\+/g, '%2B').replace(/\&/g,"%26"),
	      type:"post",
	      success:function(data){ 
	    	  document.getElementById("testCapView").value = data.toString();
	      },
			error:function(){
		        alert("system error");
		      }
	});
}

//检测测试
function testDet(){
	var testDetMap = toJson();
	$.ajax({
	      url:"checkData.do?params=" + JSON.stringify(testDetMap).replace(/\+/g, '%2B').replace(/\&/g,"%26"),
	      type:"post",
	      success:function(data){ 
	    	  document.getElementById("testDetView").value = data.toString();
	      },
			error:function(){
		        alert("system error");
		      }
	});
}

//保存
function save(){
	var saveMap = toJson();
	$.ajax({
	      url:"saveCollectItem.do?params=" + JSON.stringify(saveMap).replace(/\+/g, '%2B').replace(/\&/g,"%26"),
	      type:"post",
	      dataType:"json",
	      success:function(data){
	    	  window.location = 'allPages.jsp';
	      },
			error:function(){
		        alert("system error");
		      }
	});
}

//页面值转map
function toJson(){
	var map = {};
	var that = this;
	if (that.id){
		map.id = that.id;
		map.charset = $('#codrule option:selected') .text();
	} else {
		map.charset = $('#codrule option:selected') .val();
	}
	map.name = document.getElementById('name').value;
	map.keyword = document.getElementById('keyword').value;
	map.baseclassId = $('#allDimBaseclass option:selected') .val();
	map.industryId = $('#allDimIndustry option:selected') .val();
	map.unitId = $('#allDimUnits option:selected') .val();
	map.regionId = $('#allDimRegions option:selected') .val();
	map.dateTypeID = $('#dateType option:selected') .val();
	map.method = $('#method option:selected') .val();
	map.url = document.getElementById('url').value;
	map.baseurl = document.getElementById('baseurl').value;
	map.dateFormat = document.getElementById('dateformat').value;
	map.dateRange = document.getElementById('daterange').value;
	map.valueFormat = document.getElementById('valueformat').value;
	map.valueRange = document.getElementById('valuerange').value;
	var Array = [];
	var sizeBaseStep = $("#baseStep > .basediv").size();
	var baseArray = [];
	var baseMap = {};
	baseMap.infotype = $('#infotype option:selected') .val();
	baseMap.crawltype = $('#crawltype option:selected') .val();
	baseMap.crawlrule = document.getElementById('crawlrule').value;
	baseArray.push(baseMap);
	for (var i=1;i<=sizeBaseStep;i++) {
		var baseMapAdd = {};
		baseMapAdd.infotype = $('#infotype_'+ i +' option:selected') .val();
		baseMapAdd.crawltype = $('#crawltype_'+ i +' option:selected') .val();
		baseMapAdd.crawlrule = document.getElementById('crawlrule_'+i).value;
		baseArray.push(baseMapAdd);
	}
	Array.push(baseArray);
	var steplist = $(".hangdiv").size();
	var addStep = $(".addstepdiv").size();
	for (var k=1;k<=steplist;k++) {
		var addArray = [];
		var addBaseMap = {};
		addBaseMap.infotype = $('#infotypeStep_'+ k +' option:selected') .val();
		addBaseMap.crawltype = $('#crawltypeStep_'+ k +' option:selected') .val();
		addBaseMap.crawlrule = document.getElementById('crawlruleStep_'+k).value;
		addArray.push(addBaseMap);
		for (var j=1;j<=addStep;j++) {
			var addMap = {};
			addMap.infotype = $('#infotypeAddStep_'+ j +' option:selected') .val();
			addMap.crawltype = $('#crawltypeAddStep_'+ j +' option:selected') .val();
			addMap.crawlrule = document.getElementById('crawlruleAddStep_'+j).value;
			addArray.push(addMap);
		}
	Array.push(addArray);
	}
	map.steplist = Array;
	return map;
}

// 复选框点击事件
showOrhide = function () {
    var style = $('.xlarge').css('display');
    if (style == "none") {
        $('.xlarge').css('display', 'block');
    } else {
        $('.xlarge').css('display', 'none');
    }
}

//步骤新增
onplus = function () {
	var index;
	if ( $(".addstepdiv").length == 0 ) { 
		index = 1;
	} else {
		var lastId = $(".addstepdiv").length;
		index = lastId + 1;
	}
	$('.hangdiv').append('<div class="addstepdiv">' +
	        '<div class="btn-group" style="padding-left:30px;">' +
	        '<select class="selectpicker" style="width: 135px;height:33px; "id="infotypeAddStep_' + index + '">'  +
	        '<option value="url">url</option>' +
	        '<option value="date">日期</option>' +
	        '<option value="value">数值</option>' +
	        '</select>' +
	        '</div>' + '<div class="btn-group" style="padding-left:4px;">' +
	        '<select class="selectpicker" style="width: 135px;height:33px;" id="crawltypeAddStep_' + index + '">' +
	        '<option value="dom">dom结构</option>' +
	        '<option value="regex">regex</option>' +
	        '<option value="condition">筛选条件</option>' +
	        '</select>' +
	        '</div>' +
	        '<input type="text"  style="width:209px;height:30px" id="crawlruleAddStep_' + index + '">'  +
	       // '<div class="glyphicon glyphicon-plus" id="plusStep" onclick="onplus()"></div>' +
	       // '<div class="glyphicon glyphicon-minus" onclick="minDelstep()"></div>' +
	        '<input type="checkbox" onclick="showOrhide()">' +
	        '<div  class="xlarge" style="padding-left:30px;padding-top:8px;display:none;">' +
	        '<input type="text" placeholder="" class="input-xlarge" style="width:535px; height: 40px;">' +
	        '</div>' +
	        '</div>');
}

//步骤删除
minDelstep = function() {
    var last = $("#div > .hangdiv .addstepdiv:last-child");
    if(last){
        last.remove();
    }
}

//右侧表格保存方法
function saveCollectDatas(){
	var rows = $.map($("#righttable").bootstrapTable('getData'), function(
			row) {
		return row;
	});
	$.ajax({
	      url:"saveCollectData.do",
	      type:"post",
	      data:{ "params": JSON.stringify(rows) },
	      dataType:"json",
	      success:function(data){ 
	      },
			error:function(){
		        alert("system error");
		      }
	});
};

//新增按钮
creatStep = function () {
	var index;
	if ( $(".hangdiv").length == 0 ) { 
		index = 1;
	} else {
		var lastId = $(".hangdiv").length;
		index = lastId + 1;
	}
	$('.stepContainer').append('<div class="hangdiv" style="padding-top:15px;">' + '<hr/>' +
	        '<div class="btn-group" style="padding-left:30px;">' +
	        '<select class="selectpicker" style="width: 135px;height:33px;" id="infotypeStep_' + index + '">'  +
	        '<option  value="url">url</option>' +
	        '<option value="date">日期</option>' +
	        '<option value="value">数值</option>' +
	        '</select>' +
	        '</div>' + '<div class="btn-group" style="padding-left:4px;">' +
	        '<select class="selectpicker" style="width: 135px;height:33px;" id="crawltypeStep_' + index + '">'  +
	        '<option value="dom">dom结构</option>' +
	        '<option value="regex">regex</option>' +
	        '<option value="condition">筛选条件</option>' +
	        '</select>' +
	        '</div>' +
	        '<input type="text" style="width:209px;height:30px" id="crawlruleStep_' + index + '">' +
	        '<div class="glyphicon glyphicon-plus" id="plusStep" onclick="onplus()"></div>' +
	        '<div class="glyphicon glyphicon-minus" onclick="minDelstep()"></div>' +
	        '<input type="checkbox" onclick="showOrhide()">' +
	        '<div  class="xlarge" style="padding-left:30px;padding-top:8px;display:none;">' +
	        '<input type="text" placeholder="" class="input-xlarge" style="width:535px; height: 40px;">' +
	        '</div>' +
	        '</div>');
}

//删除按钮
delStep = function() {
    var last = $("#div > .hangdiv:last-child");
    if(last){
        last.remove();
    }
}

//基础步骤新增
baseOnplus = function(){
	var index;
	if ( $(".basediv").length == 0 ) { 
		index = 1;
	} else {
		var lastId = $(".basediv").length;
//		var lastId = $(".basediv").last().attr('id');
//		index = parseInt(lastId.substring(lastId.length-1, lastId.length)) + 1;
		index = lastId + 1;
	}
    $('.baseStep').append('<div class="basediv">' +
        '<div class="btn-group" style="padding-left:30px;">' +
        '<select class="selectpicker infotype" style="width: 135px;height:33px;" id="infotype_' + index + '">' +
        '<option value="url">url</option>' +
        '<option value="date">日期</option>' +
        '<option value="value">数值</option>' +
        '</select>' +
        '</div>' + '<div class="btn-group" style="padding-left:4px;">' +
        '<select class="selectpicker" style="width: 135px;height:33px;" id="crawltype_' + index + '">' +
        '<option value="dom">dom结构</option>' +
        '<option value="regex">regex</option>' +
        '<option value="condition">筛选条件</option>' +
        '</select>' +
        '</div>' +
        '<input type="text" class="crawlrule" style="width:209px;height:30px" id="crawlrule_' + index + '">' +
        //'<div class="glyphicon glyphicon-plus" id="plusStep_' + index + '" onclick="baseOnplus()"></div>' +
        //'<div class="glyphicon glyphicon-minus" id="minusStep_' + index + '" onclick="baseDelstep()"></div>' +
        '<input type="checkbox" onclick="showOrhide()">' +
        '<div  class="xlarge" style="padding-left:30px;padding-top:8px;display:none;">' +
        '<input type="text" placeholder="" class="input-xlarge" style="width:535px; height: 40px;">' +
        '</div>' +
        '</div>');
}

//基础步骤删除
baseDelstep = function() {
    var last = $("#baseStep > .basediv:last-child");
    if(last){
        last.remove();
    }
}