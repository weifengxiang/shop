$(document).ready(function(){
	setLeftMenuHeight();
	setLeftMenuActive();
});

/**
 * 设置高度与内容高度相等
 * @author 
 */
function setLeftMenuHeight() {
	var rightHeight=$("#rightcontent").height();
	if ($("#leftmenu").length > 0) $("#leftmenu").height(rightHeight);
	if(rightHeight  > 479)
		$(".body").height(rightHeight);
	$(".menuitem").click(function(){
		$(".menuitem").removeClass("menucheck");
		$(this).addClass("menucheck");
	})
}

/**
 * 设置左菜单选中
 * @author 
 */
function setLeftMenuActive() {
	var nowurl=window.location.href;
	$(".nav-list").find("a").each(function(){
		if(nowurl.indexOf($(this).attr("href"))>=0){
			$(".nav-list").find("li").removeClass("active");
			 $(this).parent().addClass("active");
		}
	})
}

/**
 * 时间对象format
 * @author mengql
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds() // millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

/**
 * 错误信息提示框
 * @author mengql
 */
function showErrorMsg(msgContent) {
    art.dialog({
        title: '错误',
        content: msgContent,
        icon: 'error',
        lock: true,
        okVal: "确定",
        ok: function () { return true; },
        cancel: false
    });
}

/**
 * 提示信息提示框
 * @author mengql
 */
function showInfoMsg(msgContent, okCallback) {
    art.dialog({
        title: '信息',
        content: msgContent,
      icon: 'succeed',
      lock: true,
      okVal: "确定",
      ok: function () { 
      	if(typeof (okCallback) == "function") okCallback();
      },
      cancel: false
  });
}

/**
 * 警告信息提示框
 * @author mengql
 */
function showWarningMsg(msgContent, okCallback) {
    art.dialog({
        title: '警告',
        content: msgContent,
        icon: 'warning',
        lock: true,
        okVal: "确定",
        ok: function () { 
        	if(typeof (okCallback) == "function") okCallback();
        },
        cancel: false
    });
}

/**
 * 确认信息提示框
 * @author mengql
 */
function showConfirm(content, okCallback, param) {
    art.dialog({
        title: '提示',
        content: content,
        icon: 'warning',
        lock: true,
        okVal: "确定",
        cancelVal: "取消",
        ok: function () {
            if (typeof (okCallback) == "function") okCallback(param);
        },
        cancel: true
    });
}

/**
 * 提示框模板
 * @author mengql
 */
function pop(title, dialogId, initCallback, okCallback, param) {
    art.dialog({
        title: title,
        content: document.getElementById(dialogId),
        lock: true,
        window: "top",
        okVal: "确定",
        cancelVal: "取消",
        top:"105px",
        init: function () {
        	clearPopInput();
            if (typeof (initCallback) == "function") initCallback(param);
        },
        ok: function () {
            if (typeof (okCallback) == "function") {
                return okCallback(param);
            }
        },
        cancel: function () {
        	clearPopInput();
        }
    });
}

/**
 * 警告信息提示框
 * @author mengql
 */
function clearPopInput() {
	$("table[class='aui_dialog']").find("textarea").each(function(){
		$(this).val("");
	});
	$("table[class='aui_dialog']").find("input[type='input']").each(function(){
		$(this).val("");
	});
}

/**
 * 初始化日期控件
 * @author mengql
 */
function initDatetimePicker(id) {
	if(!$("#" + id).val()) $("#" + id).val(new Date().format("yyyy-MM-dd"));
	$("#" + id).datetimepicker({
		format: "yyyy-mm-dd",
		minView: "month",
		todayHighlight: true,
		initialDate: new Date(),
		keyboardNavigation: true,
		autoclose:true
	});
}


/**
 * 步骤导航栏
 * @author cuiml
 */
var eventFun={
    setStep:function(index){                
        for(var i=2;i<=index;i++){
            $("#step"+i+"Li").addClass("blue").removeClass("gray");
            $("#step"+i+"Img").attr("src",ctx+"/images/blue_blue.png");
        }
        for(var i=index+1;i<=4;i++){
            $("#step"+i+"Li").addClass("gray").removeClass("blue");
            $("#step"+i+"Img").attr("src",ctx+"/images/gray_gray.png");
        }
        $("#step"+(index+1)+"Img").attr("src",ctx+"/images/blue_gray.png");
    }
};

/**
 * 切换步骤控件
 * @author cuiml
 */
function changeTab(index){
	$('#myTab li:eq('+index+') a').tab('show');
	eventFun.setStep(index+1);
   	setLeftMenuHeight();
}

/**
 * 初始化步骤控件
 * @author cuiml
 */
function initStep() {
	 //绑定标签页事件
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        //生成第三步的表格
   	 if($(e.relatedTarget).attr('href') == "#step2"){
   		 $("#fieldBox").empty();
   		 var href = $(this).attr('href');
   		 if(href == "#step3"){
            }
   	 } else if($(e.relatedTarget).attr('href') == "#step1"){
   		 $("#dataSourceTableBox").empty();
   		 var href = $(this).attr('href');
   		 if(href == "#step2"){
            }
   	 }
   	//当标签页显示时，重新计算页面高度
   	setLeftMenuHeight();
    });
}