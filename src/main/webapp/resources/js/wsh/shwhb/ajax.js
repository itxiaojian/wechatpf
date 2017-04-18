(function() {
	window.SX = {};
	SX.ajax = function(b, c) {
		var a;
		a = new XMLHttpRequest();
		a.open(b.method, b.url, isNaN(b.async));
		a.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		a.send(b.data);
		a.addEventListener("readystatechange", function(d) {
			if (a.readyState === 4 && a.status === 200) {
				return c(a.responseText)
			}
		}, false);
		if (!b.async) {
			return c(a.responseText)
		}
	};
	SX.ajax.sync = function(c, f) {
		var d, b, e, a;
		b = [];
		for (e = 0, a = c.length; e < a; e++) {
			d = c[e];
			SX.ajax({
				url : d,
				method : "GET",
				async : false
			}, function(g) {
				return b.push(g)
			})
		}
		return b && f(b)
	};
	window.SX.ajax = SX.ajax
}).call(this);