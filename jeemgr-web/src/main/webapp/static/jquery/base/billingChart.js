//自适应iframe的高度
$(document).ready(function() {
	/* alert("ok!"); */
	var hDoc = getBodyHeight(window.parent.document);
	/* var tblmain = $(window.parent.document).find("#mainContent");  */
	var mfraim = window.parent.mainContent;
	var botmheight = getFrameHeight(mfraim);
	var reporttbl = document.getElementById('bottom');
	if(reporttbl != null){
		var hretbl = hDoc - 40;
		if (botmheight < hretbl) {
			reporttbl.height = hretbl;
		}
	}
});
/** * 获取当前页面的高度 */
function getBodyHeight(doc) {
	var yScroll;
	if (window.innerHeight && window.scrollMaxY) {
		yScroll = window.innerHeight + window.scrollMaxY;
	} else if (document.body.scrollHeight > document.body.offsetHeight) {
		yScroll = document.body.scrollHeight;
	} else {
		yScroll = document.body.offsetHeight;
	}

	var windowHeight;
	if (self.innerHeight) {
		windowHeight = self.innerHeight;
	} else if (document.documentElement
			&& document.documentElement.clientHeight) {
		windowHeight = document.documentElement.clientHeight;
	} else if (document.body) {
		windowHeight = document.body.clientHeight;
	}

	var pageHeight;
	if (yScroll < windowHeight) {
		pageHeight = windowHeight;
	} else {
		pageHeight = yScroll;
	}

	return pageHeight;
	/* return doc.body.clientHeight; */
	/* if (doc.all)
		return doc.body.offsetHeight;
	else
		return doc.body.scrollHeight; */
}
/** * 获取内嵌页中的高度. * 若另含子内嵌(moduleRight)页时,应考虑该页面的高度. */
function getFrameHeight(tblmain) {
	var h1 = tblmain.document.body.offsetHeight;
	var h2 = tblmain.document.body.scrollHeight;
	if (tblmain.moduleRight != null) {
		var h3 = tblmain.moduleRight.document.body.scrollHeight;
		if (h3 > h2)
			h2 = h3;
	}
	return h2;
}