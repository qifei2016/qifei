<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>时间序列状态一览</title>
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="./resources/bootstrap/css/jquery-ui.min.css" />
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap-table.min.css">

<script type="text/javascript"
	src="./resources/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript"
	src="./resources/bootstrap/js/jquery-ui.js"></script>
<script type="text/javascript" src="./resources/js/main.js"></script>
<script src="./resources/bootstrap/js/bootstrap.min.js"></script>
<script src="./resources/bootstrap/js/bootstrap-table.min.js"></script>
<script src="./resources/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>

</head>

<body>
	<!--first-->
	<div class="container" style="padding-top: 15px">
		<div>
			<div class="btn-group">
				<select class="selectpicker" style="width: 205px;height:33px;"
					id="allDimUnits" onchange="unitsChange()">
				</select>
				<div id="dimunit" style="display:none;">
					<input id="newDimunits" style="width: 205px;height:33px;" />
					<button class="btn btn-info" onclick="saveDimunit()">保存</button>
				</div>
			</div>
			<div class="btn-group" style="padding-left:50px">
				<select class="selectpicker" style="width: 205px;height:33px;"
					id="allDimIndustry" onchange="industryChange()">
				</select>
				<div id="dimIndustry" style="display:none;">
					<input id="newDimIndustry" style="width: 205px;height:33px;" />
					<button class="btn btn-info" onclick="saveDimIndustry()">保存</button>
				</div>
			</div>

			<div class="btn-group" style="padding-left:80px">
				<input type="text" id="datepicker" style="width:205px; height:33px;">
			</div>

			<div class="btn-group" style="padding-left:30px">
				<select class="selectpicker" style="width: 135px;height:33px;"
					id="captureState" onchange="captureState()">
					<option value="1">抓取失败</option>
					<option value="2">抓取成功</option>
				</select>
			</div>
		</div>

		<div style="padding-top:15px">
			<div class="btn-group">
				<select class="selectpicker" style="width: 205px;height:33px;"
					id="allDimRegions" onchange="regionsChange()">
				</select>
				<div id="dimRegions" style="display:none;">
					<input id="newDimRegions" style="width: 205px;height:33px;" />
					<button class="btn btn-info" onclick="saveDimRegion()">保存</button>
				</div>
			</div>
			<div class="btn-group" style="padding-left:50px">
				<select class="selectpicker" style="width: 205px;height:33px;"
					id="allDimBaseclass" onchange="baseclassChange()">
				</select>
				<div id="dimBaseclass" style="display:none;">
					<input id="newDimBaseclass" style="width: 205px;height:33px;" />
					<button class="btn btn-info" onclick="saveDimBaseclass()">保存</button>
				</div>
			</div>
			<div class="btn-group" style="padding-left:80px; width:405px">
				<input type="text" class="form-control" placeholder="Search"
					id="itemid"
					onkeypress="if(event.keyCode==13) {search();return false;}">
			</div>
			<div class="btn-group">
				<button type="submit" class="btn btn-default" id="search"
					onclick="search()">搜索</button>
			</div>
		</div>

		<div class="btn-group" style="padding-top:15px;">
			<div class="choose" style="float:left; padding-left:0px">
				<button class="btn btn-info"
					onclick="window.location='newOrEdit.jsp'" id="newPage">新增</button>
				<button class="btn btn-info" onclick="editPage()" id="editPage">编辑</button>
				<button class="btn btn-info" onclick="del()">删除</button>
				<button class="btn btn-info" onclick="openState()">启用</button>
				<button class="btn btn-info" onclick="stop()">停用</button>
				<button class="btn btn-info">导入</button>
				<button class="btn btn-info">导出</button>
			</div>
		</div>

		<div class="table" style="padding-top:15px;">
			<table class="table table-bordered table-striped table-hover"
				id="maintable" data-toggle="table" data-click-to-select="true"
				data-row-style="rowStyle" data-single-select="true"
				data-height="660">
				<thead>
					<tr>
						<th data-checkbox="true" data-radio="true"></th>
						<th data-field="collectItemId" data-visible="false">ID</th>
						<th data-field="collectItemDesc" data-width="400">名称</th>
						<!-- <th data-field="sourceName" data-width="180">来源</th>  -->
						<th data-field="bassclassName" data-width="100">指标</th>
						<th data-field="industryName" data-width="100">行业</th>
						<th data-field="unitName" data-width="100">单位</th>
						<!-- <th data-field="dateTypeName" data-width="180">频度</th>  -->
						<th data-field="regionName" data-width="100">区域</th>
						<th data-field="lastUpdateTime" data-width="400">最后修改时间</th>
						<th data-field="status" data-width="100">状态</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>