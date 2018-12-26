/**
 * 
 */

getObjectFromForm = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

getObjectFromDataRow = function(dataRow) {
	var o = {};
	$.each(dataRow.serializeArray(), function(index) {
		if (o[this['field']]) {
			o[this['field']] = o[this['field']] + "," + this['value'];
		} else {
			o[this['field']] = this['value'];
		}
	});
	return o;
};