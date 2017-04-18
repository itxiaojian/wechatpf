(function() {
	var g, a, h, c, e, i, b, f, d;
	g = new Audio();
	b = 68;
	i = window.absolute_url;
	f = function(j) {
		return j.replace(/[^\x00-\xff]/g, "**").length
	};
	d = function(k) {
		var j = k.match(/[a-zA-Z0-9]/g);
		j = j ? j : 0;
		if (j) {
			return k.match(/[a-zA-Z0-9]/g).length
		} else {
			return 0
		}
	};
	h = {
		alert : function(k, j) {
			k.$data.is_show_notify = true;
			k.$data.notify_text = j;
			setTimeout(function() {
				k.$data.is_show_notify = false
			}, 1000)
		}
	};
	a = {
		data : [],
		get_data : function() {
			$.getJSON(i + "get_musics", function(l) {
				var k, m;
				for (k in l) {
					m = l[k];
					a.data.push(m)
				}
				c.$data.music_data = a.data;
				if (window.poster_music) {
					var j;
					c.$data.music_data.some(function(o, n) {
						if (o.href == window.poster_music) {
							c.$data.music_choose = "music_" + n;
							c.$data.music.href = window.poster_music;
							c.$data.music.name = o.name;
							$(".switch").addClass("sw_open");
							j = o.name;
							return true
						}
					})
				}
			})
		}
	};
	e = function(j) {
		document.getElementById("poster_inf").submit()
	};
	Vue.filter("get_type", function(j) {
		switch (j) {
		case "1":
			j = "舒缓";
			break;
		case "2":
			j = "动漫";
			break;
		case "3":
			j = "摇滚";
			break
		}
		return j
	});
	Vue.filter("limit_string_length", function(k) {
		var l = f(k), j;
		if (l > b) {
			j = 34 + Math.ceil(d(k) / 2);
			k = k.slice(0, j);
			this.$data.over_limit = true
		} else {
			this.$data.over_limit = false
		}
		return k
	});
	c = new Vue({
		el : "#poster_inf",
		data : {
			music_box : false,
			w_music : {
				href : null,
				name : null
			},
			music : {
				href : null,
				name : null
			},
			music_choose : false,
			is_create : false,
			music_data : null,
			notify_text : null,
			is_show_notify : false,
			music_playing : false,
			cache_choose : false,
			poster_name : window.poster_name,
			over_limit : false
		},
		ready : function() {
			a.get_data()
		},
		methods : {
			open_music : function(k) {
				var j;
				j = $(k.target);
				if (!this.$data.music_choose) {
					this.$data.music_box = true
				} else {
					this.$data.music_choose = false;
					this.$data.w_music.href = null;
					this.$data.w_music.name = null;
					this.$data.music.href = null;
					this.$data.music.name = null;
					if (j.hasClass("switch_st")) {
						j.parent().removeClass("sw_open")
					} else {
						j.removeClass("sw_open")
					}
				}
			},
			hide_choose_music : function(j) {
				this.$data.music_box = false
			},
			show_choose_music : function(j) {
				var k = this;
				this.$data.music_box = true;
				if (this.$data.music_choose) {
					setTimeout(function() {
						var l = k.$data.music_choose;
						document.getElementById(l).click()
					}, 100)
				}
			},
			save_choose_music : function(j) {
				if (this.$data.music_playing) {
					this.$data.music_playing = false;
					g.pause()
				}
				this.$data.music.href = this.$data.w_music.href;
				this.$data.music.name = this.$data.w_music.name;
				if (this.$data.music.href) {
					this.$data.music_box = false;
					this.$data.music_choose = this.$data.cache_choose;
					this.$data.cache_choose = null;
					$(".switch").addClass("sw_open")
				} else {
					h.alert(this, "请选择音乐")
				}
			},
			set_music_information : function(j) {
				this.$data.w_music.href = j.target.value;
				this.$data.w_music.name = $(j.target).attr("data-name");
				this.$data.cache_choose = j.target.id
			},
			play_music : function(k) {
				var j;
				j = $(k.target);
				if (!this.$data.music_playing) {
					this.$data.music_playing = true;
					g.src = j.attr("data-href");
					g.play();
					last_music = k.target
				} else {
					this.$data.music_playing = false;
					g.pause()
				}
				if (k.target !== last_music) {
					this.$data.music_playing = true;
					g.src = j.attr("data-href");
					g.play()
				}
			},
			forbid : function(j) {
				j.preventDefault();
				this.validate_data()
			},
			validate_data : function(k) {
				var l, j;
				l = $("#poster_name")[0];
				j = l.value;
				j = $.trim(j);
				if (j.length === 0) {
					l.focus();
					h.alert(this, "请输入海报名")
				} else {
					e()
				}
			}
		}
	});
	window.notify = h;
	window.vue_el = c
}).call(this);