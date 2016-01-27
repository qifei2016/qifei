/**
 * Created by Lang on 2016/1/7.
 */

//TODO初始化页面(区分新增或编辑进入)
window.onload = function(){
        $.ajax({
          url:"queryCollectItemByCollectItemId.do?collectItemId=12903",
          type:"post",
          dataType:"text",
          success:function(dimIndustrys){ //dimIndustrys当前为表格，但每个控件的值如何填进
            alert("");
          },
          error:function(){
            alert("system error");
          }
        });
    };

// 日期控件
$(function () {
    $("#datepicker").datepicker({
        showOtherMonths: true,
        selectOtherMonths: true,
        changeMonth: true,
        changeYear: true
    });
});

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
    $('.hangdiv').append('<div class="addstepdiv">' +
        '<div class="btn-group" style="padding-left:30px;">' +
        '<select class="selectpicker" style="width: 135px;height:33px;">' +
        '<option>url</option>' +
        '<option>日期</option>' +
        '<option>数值</option>' +
        '</select>' +
        '</div>' + '<div class="btn-group" style="padding-left:4px;">' +
        '<select class="selectpicker" style="width: 135px;height:33px;">' +
        '<option>dom结构</option>' +
        '<option>regex</option>' +
        '<option>筛选条件</option>' +
        '</select>' +
        '</div>' +
        '<input type="text" placeholder="xxx" class="input-xlarge" style="width:209px;height:30px">' +
        '<div class="glyphicon glyphicon-plus" id="plusStep" onclick="onplus()"></div>' +
        '<div class="glyphicon glyphicon-minus" onclick="minDelstep()"></div>' +
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

//新增按钮
creatStep = function () {
    $('.stepContainer').append('<div class="hangdiv" style="padding-top:15px;">' + '<hr/>' +
        '<div class="btn-group" style="padding-left:30px;">' +
        '<select class="selectpicker" style="width: 135px;height:33px;">' +
        '<option>url</option>' +
        '<option>日期</option>' +
        '<option>数值</option>' +
        '</select>' +
        '</div>' + '<div class="btn-group" style="padding-left:4px;">' +
        '<select class="selectpicker" style="width: 135px;height:33px;">' +
        '<option>dom结构</option>' +
        '<option>regex</option>' +
        '<option>筛选条件</option>' +
        '</select>' +
        '</div>' +
        '<input type="text" placeholder="xxx" class="input-xlarge" style="width:209px;height:30px">' +
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
    $('.baseStep').append('<div class="basediv">' +
        '<div class="btn-group" style="padding-left:30px;">' +
        '<select class="selectpicker" style="width: 135px;height:33px;">' +
        '<option>url</option>' +
        '<option>日期</option>' +
        '<option>数值</option>' +
        '</select>' +
        '</div>' + '<div class="btn-group" style="padding-left:4px;">' +
        '<select class="selectpicker" style="width: 135px;height:33px;">' +
        '<option>dom结构</option>' +
        '<option>regex</option>' +
        '<option>筛选条件</option>' +
        '</select>' +
        '</div>' +
        '<input type="text" placeholder="xxx" class="input-xlarge" style="width:209px;height:30px">' +
        '<div class="glyphicon glyphicon-plus" id="plusStep" onclick="baseOnplus()"></div>' +
        '<div class="glyphicon glyphicon-minus" onclick="baseDelstep()"></div>' +
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