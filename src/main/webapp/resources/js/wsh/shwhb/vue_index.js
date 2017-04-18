(function() {
	Vue.config = {
		debug : true
	};
	var b, c, e, h, a, g, d, f;
	d = window.absolute_url;
	b = null;
	g = function() {
		if (!e.$data.unpublished.exist && !e.$data.published.exist) {
			e.$data.none_both_poster = true
		}
		if (e.$data.unpublished.exist && !e.$data.published.exist) {
			e.$data.published.exist = true;
			e.$data.none_publish_poster = true
		}
	};
	a = {
		$el_edit_form : $("#edit_poster"),
		data : [],
		get_data : function() {
			$.getJSON(d + "get_unpublished", function(j) {
				if (j.code != 0) {
					var i, k;
					for (i in j) {
						k = j[i];
						a.data.push(k)
					}
					a.data.reverse();
					e.$data.unpublished.data = a.data;
					e.$data.unpublished.exist = true
				} else {
					e.$data.unpublished.exist = false
				}
				h.get_data()
			})
		},
		submit : function() {
			this.$el_edit_form[0].submit()
		}
	};
	h = {
		data : [],
		get_data : function() {
			$.getJSON(d + "get_published", function(j) {
				var i, k;
				if (j.code != 0) {
					for (i in j) {
						k = j[i];
						k.qrshow = false;
						k.linkshow = false;
						h.data.push(k)
					}
					h.data.reverse();
					e.$data.published.data = h.data;
					e.$data.published.exist = true
				} else {
					e.$data.published.exist = false
				}
				g()
			})
		}
	};
	c = {
		alert : function(j, i) {
			j.$data.is_show_notify = true;
			j.$data.notify_text = i;
			return setTimeout(function() {
				return j.$data.is_show_notify = false
			}, 1000)
		},
		success : function(j, i) {
			j.$data.is_show_success = true;
			j.$data.notify_text = i;
			return setTimeout(function() {
				return j.$data.is_show_success = false
			}, 1000)
		}
	};
	f = function(i, j) {
		$.ajax({
			type : "GET",
			data : {
				poster_id : i
			},
			url : d + "delete_poster",
			success : function(k) {
				if (k.code == 1) {
					j && j();
					c.success(e, "删除成功")
				} else {
					c.success(e, "删除失败,请重试")
				}
			}
		})
	};
	Vue.filter("cut_seconds", function(i) {
		i = i.substr(0, i.length - 3);
		return i
	});
	e = new Vue({
		el : "#poster_model",
		data : {
			is_ready : true,
			is_get_template : false,
			choose_template_data : [],
			is_choose_show : false,
			template_id : null,
			is_show_notify : false,
			is_show_success : false,
			none_publish_poster : false,
			none_both_poster : false,
			unpublished : {
				exist : false,
				data : null
			},
			published : {
				exist : false,
				data : null
			},
			edit : {
				poster_template_id : null,
				poster_id : null
			},
			edit_data : {
				poster_template_id : null,
				poster_id : null
			},
			notify_text : null
		},
		ready : function() {
			a.get_data()
		},
		methods : {
			show_choose_templates : function() {
				var i;
				i = this;
				$.getJSON(d + "limit_posters", function(j) {
					if (j.code == 1) {
						if (!i.$data.is_choose_show) {
							i.$data.is_choose_show = true;
							if (!i.$data.is_get_template) {
								i.$data.is_get_template = true;
								$.getJSON(d + "get_choose_poster_templates",
										function(l) {
											var k, m;
											for (k in l) {
												m = l[k];
												i.$data.choose_template_data
														.push(m)
											}
										})
							}
						}
					} else {
						c.alert(i, "最多只能创建4份未发布的海报")
					}
				})
			},
			hide_choose_templates : function() {
				this.$data.is_choose_show = false
			},
			choose_tem : function(j) {
				var i;
				i = j.targetVM.$el.getElementsByClassName("tem_sure")[0];
				if (i === b) {
					i.style.display = "none";
					b = null;
					this.$data.template_id = null;
					return
				}
				if (b) {
					b.style.display = "none"
				}
				this.$data.template_id = j.targetVM.$el.dataset.template_id;
				i.style.display = "block";
				return b = i
			},
			submit_choose : function() {
				var i;
				i = this;
				if (this.$data.template_id) {
					return document.getElementById("tem_inf").submit()
				} else {
					return c.alert(i, "请选择一个模板再提交")
				}
			},
			link : function(j) {
				var i = j.targetVM.$data.linkshow;
				if (i) {
					j.targetVM.$data.linkshow = false
				} else {
					j.targetVM.$data.linkshow = true
				}
				j.target.classList.toggle("link_active")
			},
			hide_link : function(i) {
				this.$data.is_show_link = false
			},
			show_qrcode : function(m) {
				var i, k, l, j;
				i = $(m.target);
				k = i.attr("data-index");
				l = m.targetVM.$data.href;
				j = document.getElementsByClassName("qrcode_poster")[k];
				console.log(l);
				if (!this.$data.published.data[k].qrshow) {
					this.$data.published.data[k].qrshow = true;
					setTimeout(function() {
						j.innerText = "";
						new QRCode(j, {
							text : l,
							width : 180,
							height : 170
						});
						j.style.display = "block"
					}, 100)
				} else {
					this.$data.published.data[k].qrshow = false;
					j.style.display = "none"
				}
				m.target.classList.toggle("qp_active")
			},
			delete_unpublished_poster : function(j) {
				var i, k;
				if (window.confirm("确定要删除此海报?")) {
					i = j.targetVM.$data.id;
					k = j.targetVM.$index;
					f(i, function() {
						e.$data.unpublished.data.splice(k, 1);
						if (!e.$data.unpublished.data.length) {
							e.$data.unpublished.exist = false;
							if (!e.$data.published.data
									|| !e.$data.published.data.length) {
								e.$data.published.exist = false
							}
							g()
						}
					})
				}
			},
			delete_published_poster : function(j) {
				var i, k;
				if (window.confirm("确定要删除此海报?")) {
					i = j.targetVM.$data.id;
					k = j.targetVM.$index;
					f(i, function() {
						e.$data.published.data.splice(k, 1);
						if (!e.$data.published.data.length) {
							e.$data.published.exist = false;
							if (e.$data.unpublished.data
									&& e.$data.unpublished.data.length) {
								e.$data.published.exis = true;
								e.$data.none_publish_poster = true;
								g();
								return
							}
						}
					})
				}
			}
		}
	})
}).call(this);