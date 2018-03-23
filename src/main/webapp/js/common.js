/**
 * 
 */
$(function() {
	// 鼠标移入菜单会有显示
	$(".rowData").mouseover(function() {
		$(this).removeClass("rowData");
		$(this).addClass("rowData1");
	});
	
	$(".rowData").mouseout(function() {
		$(this).removeClass("rowData1");
		$(this).addClass("rowData");
	});
	

});