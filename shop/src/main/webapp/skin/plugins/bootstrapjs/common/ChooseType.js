$(document).ready(function() {
	changeItem();
	J_ToggleItems_click();
});

/*
 * 点击item事件
 */
function changeItem() {
	var items = $("div[class='items']").find("a[class='item']");
	items.push($("div[class='items']").find("a[class='item item-active']"));
	items.each(function() {
		$(this).click(function() {
			$(this).parent().find("a").not(this).removeClass("item-active");
			$(this).addClass("item-active");
		});
	});
}

/*
 *  更多和收起按钮切换
 */
function J_ToggleItems_click() {
	var J_ToggleItems = $("div[class='item-foot']").find("span");
	J_ToggleItems.each(function() {
		$(this).unbind("click");
		$(this).click(function() {
			$(this).addClass("hidden");
			//判断是显示更多则显示滚动条
			if($(this).hasClass("show-more")) {
				$(this).parent().parent().addClass("expand-all");
				$(this).parent().prev().height($(this).parent().prev().height() * 2);
				$(this).next().removeClass("hidden");
			}else {
				$(this).parent().prev().height($(this).parent().prev().height() / 2);
				$(this).parent().parent().removeClass("expand-all");
				$(this).parent().parent().find("div[class='items']").scrollTop(0);
				$(this).prev().removeClass("hidden");
			}
		});
	}) 
}