(function() {
	var h, d, p, a, l, j, r, v, q, k, e, i, g, y, t, m, o, s, x, f, n, u, c, w, b;
	g = null;
	y = null;
	n = window.absolute_url;
	i = null;
	a = null;
	r = null;
	s = '<div class="edit"><input type="text" v-model="__data__.content" /></div>';
	e = '<textarea class="edit_text" v-model="__data__.content" ></textarea>';
	d = '<section class="upload_image"><div class="notify_img">图片<span>*</span></div><div class="notify_image"><img src="{{__data__.content}}" /></div><div id=uploader{{index}} class="uploader"></div><div class="notify_text">建议图片尺寸：640*1136</div></section><input type="hidden" v-model="__data__.content" /> ';
	o = {};
	j = {
		alert : function(A, z) {
			A.$data.is_show_notify = true;
			A.$data.notify_text = z;
			setTimeout(function() {
				A.$data.is_show_notify = false
			}, 1000)
		},
		success : function(A, z) {
			A.$data.is_show_success = true;
			A.$data.notify_text = z;
			setTimeout(function() {
				A.$data.is_show_success = false
			}, 1000)
		}
	};
	h = document.getElementsByClassName("page_edit");
	t = document.getElementsByClassName("phone-page");
	c = function(z) {
		g = document.getElementsByClassName("box")[z];
		if (g) {
			g.classList.add("box_active")
		}
		i = h[z];
		if (i) {
			i.style.display = "block"
		}
		y = t[z];
		if (y) {
			y.style.display = "block"
		}
	};
	x = function(z, B) {
		var A, C = "sps[" + B + "].content";
		A = z.replace(/__data__/g, C);
		f.$addChild({
			inherit : true,
			template : A
		}).$mount(t[B]);
		if (document.getElementsByClassName("section_drag")[B]) {
			if (f.$data.sps[B].content.background) {
				document.getElementsByClassName("section_drag")[B].style.backgroundImage = "url('"
						+ f.$data.sps[B].content.background.content + "')"
			}
		}
	};
	m = function(z) {
		z.forEach(function(A) {
			x(o[A.poster_template_section_name].res, A.index)
		})
	};
	u = function() {
		$
				.ajax({
					type : "GET",
					url : n + "get_poster_section_values",
					data : {
						poster_id : window.poster_id
					},
					success : function(C) {
						var A = C.section_values, z = [], F, D;
						for (var B = 0, E = A.length; B < E; B++) {
							F = parseInt(A[B].index);
							D = A[B].class_name;
							if (!z[F]) {
								z[F] = {};
								z[F].index = F;
								z[F].poster_template_section_name = A[B].poster_template_section_name;
								z[F].content = {};
								z[F].edit = [];
								z[F].allow_add = o[A[B].poster_template_section_name].allow_add
							}
							if (!z[F].content[D]) {
								z[F].content[D] = {}
							}
							z[F].content[D].content = A[B].content;
							z[F].content[D].type = A[B].type;
							z[F].content[D].allow_modify = A[B].allow_modify;
							z[F].edit.push({
								class_name : D,
								type : A[B].type,
								allow_modify : A[B].allow_modify,
								content : A[B].content
							})
						}
						f.$data.sps = z;
						m(z)
					}
				})
	};
	v = function() {
		$.ajax({
			type : "POST",
			url : n + "get_poster_template_section_html",
			data : {
				poster_id : window.poster_id,
				template_id : window.template_id
			},
			success : function(z) {
				q(z)
			}
		})
	};
	q = function(A) {
		var C, B, z;
		for (B = 0, z = A.length; B < z; B++) {
			C = A[B];
			$.ajax({
				type : "GET",
				dataType : "text",
				async : false,
				url : C.html,
				success : function(D) {
					o[C.name] = {};
					o[C.name].res = D;
					o[C.name].allow_add = C.allow_add
				}
			})
		}
		u()
	};
	v();
	k = function() {
		$.ajax({
			type : "GET",
			dataType : "json",
			data : {
				poster_template_id : window.template_id
			},
			url : n + "get_poster_template_sections",
			success : function(A) {
				var z = A.poster_template_sections;
				if (z[0]) {
					f.$data.add_sps.datas = z
				}
			}
		})
	};
	k();
	l = function(A, C, z, B) {
		$.ajax({
			type : "POST",
			url : n + "save_poster",
			data : {
				poster_id : window.poster_id,
				poster_json : A
			},
			success : function(D) {
				if (D.code == 0) {
					j.success(C, z);
					if (B && B.success) {
						B.success()
					}
				} else {
					if (B && B.error) {
						B.error()
					}
				}
			}
		})
	};
	p = function() {
		setTimeout(
				function() {
					$(".uploader")
							.uploadify(
									{
										buttonText : "上传背景图片",
										width : 120,
										multi : false,
										swf : "/js/uploadify/uploadify.swf",
										uploader : "/upload/upload_img",
										fileExt : "*.jpg;*.png",
										formData : {
											min_width : 320,
											min_height : 568,
											aspect_ratio : "320-568",
											timestamp : window.timestamp,
											token : window.token
										},
										onUploadSuccess : function(B, C, A) {
											C = jQuery.parseJSON(C);
											var z = C.result[0], D;
											if (z.code === 0) {
												this.wrapper
														.find(
																".uploadify-button-text")
														.text("上传背景图片");
												D = this.wrapper.parent()
														.parent()[0]._upload_index;
												f.$data.sps[D].content.background.content = z.img_url;
												document
														.getElementsByClassName("section_drag")[D].style.backgroundImage = "url("
														+ z.img_url + ")"
											} else {
												j.alert(f, z.message)
											}
										},
										onUploadError : function(z, C, B, A) {
											p(_this, "上传背景图片失败,重新上传")
										},
										onInit : function(z) {
											z.button.removeClass(
													"uploadify-button").attr(
													"style", "")
										}
									})
				}, 100)
	};
	Vue.filter("show_title_text", function(z) {
		if (z[0].type == "1" || z[0].type == "2") {
			return "文字"
		}
	});
	Vue.filter("edit", function(A) {
		var D, B, z, E, C;
		D = this.$parent.$index;
		E = "sps[" + D + "].content." + A.class_name;
		if (A.type == 1) {
			z = s.replace(/__data__/g, E);
			this.$addChild({
				inherit : true,
				template : z
			}).$mount(this.$el)
		}
		if (A.type == 2) {
			B = e.replace(/__data__/g, E);
			this.$addChild({
				inherit : true,
				template : B
			}).$mount(this.$el)
		}
		if (A.type == 3) {
			C = d.replace(/__data__/g, E);
			this.$addChild({
				inherit : true,
				template : C
			}).$mount(this.$el);
			this.$el._upload_index = D;
			p()
		}
		return void 0
	});
	f = new Vue(
			{
				el : "#vue_edit",
				data : {
					show_pre : false,
					sps : [],
					is_show_notify : false,
					is_show_success : false,
					notify_text : null,
					add_sps : {
						show : false,
						datas : [],
						choose_data : null
					},
					no_sps : false
				},
				methods : {
					pg_checked : function(A) {
						var B, z;
						z = A.srcElement || A.target;
						if (g) {
							g.classList.toggle("box_active")
						}
						z.classList.toggle("box_active");
						g = z;
						B = z.dataset.page;
						if (y) {
							y.style.display = "none"
						}
						t[B].style.display = "block";
						y = t[B];
						if (i) {
							i.style.display = "none"
						}
						h[B].style.display = "block";
						i = h[B]
					},
					update_data : function(z) {
						l(this.$data.sps, this, "保存成功")
					},
					pre_view : function(z) {
						var A = this;
						if (!this.$data.show_pre) {
							l(
									this.$data.sps,
									this,
									"预览页面生成中....",
									{
										success : function() {
											$
													.ajax({
														type : "POST",
														url : n
																+ "publish_poster/preview",
														data : {
															poster_id : window.poster_id
														},
														dataType : "json",
														success : function(B) {
															A.$data.show_pre = true;
															document
																	.getElementById("preview_text").innerText = "关闭二维码";
															document
																	.getElementById("pre_qrcode").innerHTML = "";
															document
																	.getElementById("qrcode").className += " show";
															new QRCode(
																	"pre_qrcode",
																	{
																		text : B.poster_url,
																		width : 150,
																		height : 150
																	})
														}
													})
										}
									})
						} else {
							this.$data.show_pre = false;
							document.getElementById("qrcode").className = "qrcode";
							document.getElementById("preview_text").innerText = "手机页面预览"
						}
					},
					publish_poster : function(z) {
						if (window.confirm("微海报发布后将无法修改，确定要发布吗？")) {
							if (f.$data.sps.length == 0) {
								j.alert(f, "请至少添加一个页面后再发布海报");
								return
							}
							if (f.$data.sps.length > 11) {
								j.alert(f, "微海报页面数不超过11页");
								return
							}
							l(this.$data.sps, this, "微海报发布中······", {
								success : function() {
									document.getElementById("publish_form")
											.submit()
								}
							})
						}
					},
					hidden_add_page_sections : function() {
						this.$data.add_sps.show = false
					},
					show_add_page_sections : function() {
						if (f.$data.sps.length >= 11) {
							j.alert(f, "微海报页面数不超过11页");
							return
						}
						this.$data.add_sps.show = true
					},
					delete_sections : function(D) {
						var z = parseInt(D.targetVM.$index, 10), E = this.$data.sps.length - 1, C, F = this;
						if (window.confirm("确定删除页面吗?")) {
							F.$data.sps.splice(z, 1);
							C = E - z;
							for (var B = this.$data.sps.length - 1, A = 0; A < C; B--, A++) {
								this.$data.sps[B].index = parseInt(
										this.$data.sps[B].index, 10) - 1
							}
							l(this.$data.sps, this, "删除成功", {
								success : function() {
									$(".page_edit").hide();
									$(".phone-page").hide();
									$(".box").removeClass("box_active");
									c(0)
								}
							})
						}
					},
					choose_add_sections : (function() {
						var z = null, A;
						return function(B) {
							A = B.targetVM.$el;
							if (!z) {
								z = A;
								z.getElementsByClassName("add_sure")[0].style.display = "block";
								this.$data.add_sps.choose_data = A.dataset.poster_template_section_id
							} else {
								if (z != A) {
									z.getElementsByClassName("add_sure")[0].style.display = "none";
									A.getElementsByClassName("add_sure")[0].style.display = "block";
									this.$data.add_sps.choose_data = A.dataset.poster_template_section_id;
									z = A
								} else {
									if (A == z) {
										z = null;
										this.$data.add_sps.choose_data = null;
										A.getElementsByClassName("add_sure")[0].style.display = "none"
									}
								}
							}
						}
					})(),
					submit_add_sections : function() {
						var z = this;
						if (!this.$data.add_sps.choose_data) {
							j.alert(this, "请选择一个模板")
						} else {
							$
									.ajax({
										type : "GET",
										url : n
												+ "get_poster_template_section_values",
										data : {
											poster_template_section_id : this.$data.add_sps.choose_data,
											poster_id : window.poster_id
										},
										success : function(D) {
											var E, B, A;
											if (D.code == 0) {
												z.$data.add_sps.show = false;
												B = D.message;
												E = {};
												for ( var C in B) {
													A = B[C];
													if (!E.poster_template_section_name) {
														A = B[C];
														E.poster_template_section_name = A.poster_template_section_name
													}
													if (!E.content) {
														E.content = {}
													}
													if (!E.edit) {
														E.edit = []
													}
													E.content[A.class_name] = {};
													E.content[A.class_name].content = A.content;
													E.content[A.class_name].type = A.type;
													E.content[A.class_name].allow_modify = A.allow_modify;
													E.edit
															.push({
																class_name : A.class_name,
																type : A.type,
																allow_modify : A.allow_modify
															})
												}
												if (o[A.poster_template_section_name].allow_add) {
													E.allow_add = o[A.poster_template_section_name].allow_add
												}
												if (window.general == 1) {
													b(E)
												} else {
													if (window.general == 0) {
														w(E)
													}
												}
											}
										}
									})
						}
					}
				}
			});
	w = function(A) {
		var B = f.$data.sps.length - 1, z;
		A.index = B;
		f.$data.sps.splice(B, 0, A);
		z = f.$data.sps.length - 1;
		f.$data.sps[z].index = f.$data.sps[z].index + 1;
		l(f.$data.sps, f, "增加页面成功", {
			success : function() {
				f.$data.add_sps.choose_data = null;
				$(".page_edit").hide();
				$(".phone-page").hide();
				$(".box").removeClass("box_active");
				c(z - 1)
			}
		})
	};
	b = function(z) {
		f.$data.sps.push(z);
		z.index = f.$data.sps.length - 1;
		l(f.$data.sps, f, "增加页面成功", {
			success : function() {
				f.$data.add_sps.choose_data = null;
				$(".page_edit").hide();
				$(".phone-page").hide();
				$(".box").removeClass("box_active");
				c(z.index)
			}
		})
	};
	(function() {
		var z = false;
		f.$watch("sps", function(A) {
			if (A.length == 0) {
				this.$data.no_sps = true
			} else {
				this.$data.no_sps = false
			}
			m(A);
			if (!z) {
				z = true;
				c(0)
			}
		})
	})()
}).call(this);