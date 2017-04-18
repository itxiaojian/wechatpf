jQuery(function(a) {
	a(".admin_checkbox").click(function() {
		var c = a(".admin_checkbox"), b = a(".admin_checkbox:checked");
		if (c.length == b.length) {
			a("#admin_checkall").attr("checked", "checked")
		} else {
			a("#admin_checkall").removeAttr("checked")
		}
	});
	a("#admin_checkall").click(function() {
		var b = a(this).is(":checked");
		if (b) {
			a(".admin_checkbox").attr("checked", "checked")
		} else {
			a(".admin_checkbox").removeAttr("checked")
		}
	})
});