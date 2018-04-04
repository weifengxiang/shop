//默认参数
var defaultSetting = {
	ajax : true,
	requestHandler : function(request) {
		if($("#" + this.id).prev().find("button").length == 0) {
			hideDefaultSearch();
			createGridSearch();
			initSearchItem();
		}
		var sortStr = createSortStr(request.sort);
		request.sort = JSON.stringify(request.sort);
//		request.sort = sortStr;
//		request.sortStr = sortStr;
		request.searchPhrase = createSearchStr();
		request["gridInfo"] = JSON.stringify(request);
		return request;
	},
	formatters : {
		"seq" : function(column, row, index, current, rowCount) {
			if(rowCount == -1) 
				return parseInt(index) + 1;
			else
				return (parseInt(current) - 1) * parseInt(rowCount) + (parseInt(index) + 1);
		}
	}
};

//查询方式对应的查询表达式
var like = " like ",
	eq	 = " = ",
	gt	 = " > ";
	lt	 = " < ",
	gteq = " >= ",
	lteq = " <= ",
	and	 = " and ",
	yearlike = " like ";
	
	
//初始化grid
function createGrid(userSetting) {
	var setting = $.extend(true, defaultSetting, userSetting);
	var grid = $("#" + userSetting.id).bootgrid(setting);
	return grid;
}

//创建搜索字符�
function createSearchStr() {
	var searchStr = "";
	$("div[class='search form-group']").find(":input").each(function() {
		if(!$(this).val()) return;
		var searchCol = $(this).attr("data-for");
		if(!searchCol) return;
		var searchType = $(this).attr("search-type");
		if(!searchType) searchType = eq;
		var searchVal = $(this).val();
		switch(searchType) {
			case "like":
				searchStr += and + searchCol + like + "'%'||" + "'" + searchVal + "'" + "||'%'";
				break;
			case "eq":
				searchStr += and + searchCol + eq + "'" + searchVal + "'";
				break;
			case "gt":
				searchStr += and + searchCol + gt + "'" + searchVal + "'";
				break;
			case "lt":
				searchStr += and + searchCol + lt + "'" + searchVal + "'";
				break;
			case "gteq":
				searchStr += and + searchCol + gteq + "'" + searchVal + "'";
				break;
			case "lteq":
				searchStr += and + searchCol + lteq + "'" + searchVal + "'";
				break;
			case "yearlike":
				searchStr += and + "to_char(" + searchCol + ", 'yyyy')" + yearlike + "'%'||" + "'" + searchVal + "'" + "||'%'";
				break;
			default:
				break;
		}
	});
	return searchStr;
}

//搜索按钮
function search() {
	grid.bootgrid("reload");
}

//隐藏grid 默认的搜索框
function hideDefaultSearch() {
	$("div[class='search-group']").empty();
//	var searchBtn = "<button type=\"button\" name=\"btnSearch\" class=\"btn btn-sm btn-info pull-left\" onclick=\"search()\">搜索</button>";
//	$("div[class='input-group']").append(searchBtn);
}

//创建grid的搜索项�
function createGridSearch() {
	$("div[class='search-group']").append($("#gridSearch").html());
	$("#gridSearch").remove();
}

//生成sort 字符�
function createSortStr(sortObj) {
	var sortStr = "";
	for(var key in sortObj) {
		if(sortStr) sortStr += "," + key + " " + sortObj[key];
		else sortStr += key + " " + sortObj[key];
	}
	return sortStr;
}

//点击搜索�
function initSearchItem() {
	$("div[class='bootgrid-header container-fluid'] ul").each(function() {
		$(this).find("li").each(function(){
			$(this).bind("click", function(){
				$(this).addClass("li-active");
				$(this).siblings().removeClass("li-active");
				var ulid = $(this).parent().attr("id");
				$("#search_" + ulid).val($(this).attr("value"));
				search();
			});
		});
	});
}