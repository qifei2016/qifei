<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增时间序列</title>
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="./resources/bootstrap/css/jquery-ui.min.css" />
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap-table.min.css">

<script type="text/javascript"
	src="./resources/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript"
	src="./resources/bootstrap/js/jquery-ui.js"></script>
<script type="text/javascript" src="./resources/js/newOrEdit.js"></script>
<script src="./resources/bootstrap/js/bootstrap.min.js"></script>
<script src="./resources/bootstrap/js/bootstrap-table.min.js"></script>
<script src="./resources/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
</head>

<body>
	<div class="top"
		style="border:1px solid #000; width:70%;float:left; padding-bottom: 8px; height:120px;">
		<div style="background-color:lightskyblue; width:100%; height:25px;">
			<button type="button" class="btn btn-default btn-xs" onclick="window.location='allPages.jsp'">返回一览</button>
			<span style="text-align:center">属性设置</span>
		</div>
		<div style="padding-top:8px;">
			<label class="control-label" style="padding-left:30px;">名称：</label>
			<div class="btn-group" style="padding-left:27px">
				<input type="text" style="width:205px;height:33px;" id="name">
			</div>
			<label class="control-label" style="padding-left:50px;">关键字：</label>
			<input type="text" placeholder="" class="input-xlarge"
				style="width:200px;height:33px;" id="keyword">
		</div>
		<div style="padding-top:8px;">
			<label class="control-label" style="padding-left:30px;">配置分类：</label>
			<div class="btn-group">
				<select class="selectpicker" style="width: 205px;height:33px;"
					id="allDimUnits">
				</select>
			</div>
			<div class="btn-group" style="padding-left:50px">
				<select class="selectpicker" style="width: 205px;height:33px;"
					id="allDimIndustry">
				</select>
			</div>
			<div class="btn-group" style="padding-left:50px">
				<select class="selectpicker" style="width: 205px;height:33px;"
					id="allDimRegions">
				</select>
			</div>
			<div class="btn-group" style="padding-left:50px">
				<select class="selectpicker" style="width: 205px;height:33px;"
					id="allDimBaseclass">
				</select>
			</div>
		</div>
	</div>

	<div class="right"
		style="border:1px solid #000; width:30%;float:right;height:930px;overflow :auto">
		<div style="background-color:lightskyblue; width:100%; height:25px;">
			<span style="text-align:center">当前时间序列的值</span>
		</div>
		<table class="table table-bordered table-striped table-hover"
			id="righttable" data-toggle="table" data-height="860">
			<thead>
				<tr>
					<th data-field="collectDate" data-width="400">时间</th>
					<th data-field="collectData" data-width="400">时间序列的值</th>
				</tr>
			</thead>
		</table>
		<button class="btn btn-info" type="button" onclick="saveCollectDatas()">保存</button>
	</div>

	<div class="left"
		style="border:1px solid #000; width:40%;float:left;height:810px;overflow :auto">
		<div style="background-color:lightskyblue; width:100%; height:25px;">
			<span style="text-align:center">抓取规则</span>
		</div>
		<label class="control-label"
			style="padding-left:30px;padding-top:8px;">抓取方法：</label>
		<div class="btn-group" style="padding-left:50px">
			<select class="selectpicker" style="width: 410px;height:33px;"
				id="method">
				<option value="1" text="方法一">方法一</option>
				<option value="2" text="方法二">方法二</option>
				<option value="3" text="方法三">方法三</option>
			</select>
		</div>
		<div style="padding-top:8px;">
			<label style="padding-left:70px;padding-top:8px;">url：</label>
			<div class="btn-group" style="padding-left:48px;padding-top:8px;">
				<input type="text" style="width:408px; height: 33px;" id="url">
			</div>
		</div>
		<div style="padding-top:8px;">
			<label class="control-label"
				style="padding-left:36px;padding-top:8px;">baseurl：</label>
			<div class="btn-group" style="padding-left:50px;padding-top:8px;">
				<input type="text" style="width:408px; height: 33px;" id="baseurl">
			</div>
		</div>
		<div>
			<label class="control-label"
				style="padding-left:30px;padding-top:15px;">抓取频率：</label>
			<div class="btn-group" style="padding-left:50px">
				<select class="selectpicker" style="width: 150px;height:33px;"
					id="dateType">
					<option value="4" text="日度">日度</option>
					<option value="5" text="旬">旬</option>
					<option value="3" text="月度">月度</option>
					<option value="2" text="季度">季度</option>
					<option value="1" text="年度">年度</option>
				</select>
			</div>
			<label class="control-label"
				style="padding-left:15px;padding-top:8px;">编码格式：</label>
			<div class="btn-group" style="padding-left:10px;">
				<select class="selectpicker" style="width: 155px;height:33px;"
					id="codrule">
					<option value="utf-8">utf-8</option>
					<option value="gb2312">gb2312</option>
					<option value="GBK">GBK</option>
				</select>
			</div>
		</div>
		<label class="control-label"
			style="padding-left:30px;padding-top:15px;">步骤：</label>

		<div class="baseStep" id="baseStep">
			<div>
				<div class="btn-group" style="padding-left:30px;">
					<select class="selectpicker infotype"
						style="width: 135px;height:33px;" id="infotype">
						<option value="url" text="url">url</option>
						<option value="date" text="日期">日期</option>
						<option value="value" text="数值">数值</option>
					</select>
				</div>
				<div class="btn-group">
					<select class="selectpicker"
						style="width: 135px;height:33px;" id="crawltype">
						<option value="dom" text="dom结构">dom结构</option>
						<option value="regex" text="regex">regex</option>
						<option value="condition" text="筛选条件">筛选条件</option>
					</select>
				</div>
				<input type="text" class="crawlrule" id="crawlrule"
					style="width:205px;height:30px;">
				<div class="glyphicon glyphicon-plus" id="plusStep"
					onclick="baseOnplus()"></div>
				<div class="glyphicon glyphicon-minus" onclick="baseDelstep()"></div>
				<input type="checkbox" onclick="showOrhide()">
				<div class="xlarge"
					style="padding-left:30px;padding-top:8px;display:none;">
					<input type="text" placeholder="" class="input-xlarge"
						style="width:535px; height: 40px;">
				</div>
			</div>
		</div>

		<div class="stepContainer" id="div"></div>

		<div class="choose" style="padding-top:8px;padding-left:30px;">
			<button class="btn btn-info" type="submit" id="creatStep"
				onclick="creatStep()">新增步骤</button>
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<button class="btn btn-info" type="submit" id="delStep"
				onclick="delStep()">删除步骤</button>
		</div>
		<label class="control-label"
			style="padding-left:30px;padding-top:8px;">结果：</label>
		<div style="padding-left:30px;padding-top:8px;padding-bottom: 8px;">
			<!-- <input type="text" placeholder="" class="input-xlarge"
				style="width:535px; height: 350px;"  id="testCapView"> -->
			<textarea rows="10" cols="80" id="testCapView"></textarea>
		</div>
		<div class="choose" style="padding-top:30px;padding-left:30px;">
			<button class="btn btn-info" type="submit" id="clearCap"
				onclick="clearCap()">重置</button>
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<button class="btn btn-info" type="submit" id="testCap"
				onclick="testCap()">测试</button>
		</div>
	</div>
	<div class="middle"
		style="border:1px solid #000; width:30%;float:right;height:810px;">
		<div style="background-color:lightskyblue; width:100%; height:25px;">
			<span style="text-align:center">检测规则</span>
		</div>
		<label class="control-label"
			style="padding-left:30px;padding-top:8px;">日期格式规则：</label>
		<div style="padding-left:30px;padding-top:8px;">
			<input type="text" placeholder="" class="input-xlarge"
				style="width:370px; height: 33px;" id="dateformat">
		</div>
		<label class="control-label"
			style="padding-left:30px; padding-top:15px;">日期阈值规则：</label>
		<div style="padding-left:30px;padding-top:8px;">
			<input type="text" placeholder="" class="input-xlarge"
				style="width:370px; height: 33px;" id="daterange">
		</div>
		<label class="control-label"
			style="padding-left:30px; padding-top:15px;">数值格式规则：</label>
		<div style="padding-left:30px;padding-top:8px;">
			<input type="text" placeholder="" class="input-xlarge"
				style="width:370px; height: 33px;" id="valueformat">
		</div>
		<label class="control-label"
			style="padding-left:30px; padding-top:15px;">数值阈值规则：</label>
		<div style="padding-left:30px;padding-top:8px;">
			<input type="text" placeholder="" class="input-xlarge"
				style="width:370px; height: 33px;" id="valuerange">
		</div>
		<label class="control-label"
			style="padding-left:30px; padding-top:15px;">测试结果：</label>
		<div style="padding-left:30px;padding-top:8px;padding-bottom: 8px;">
			<!-- <input type="text" placeholder="" class="input-xlarge"
				style="width:370px; height: 120px;"  id="testDetView"> -->
				<textarea rows="10" cols="50" id="testDetView"></textarea>
		</div>
		<div class="choose" style="padding-top:30px;padding-left:30px;">
			<button class="btn btn-info" type="submit" id="clearDet"
				onclick="clearDet()">重置</button>
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<button class="btn btn-info" type="submit" id="testDet"
				onclick="testDet()">测试</button>
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<button class="btn btn-info" type="submit" id="save" onclick="save()">保存</button>
		</div>
	</div>
</body>
</html>