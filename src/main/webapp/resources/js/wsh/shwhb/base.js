(function() {
    var a;
    a = (function() {
        var c;
        function b() {}
        c = document;
        b.set_data = function(f, d, e) {
            return f.dataset[d] = e
        };
        b.get_data = function(e, d) {
            return e.dataset[d]
        };
        b.removeSelf = function(d) {
            var e = d.parentNode;
            e.removeChild(d)
        };
        b.get_ele_by_id = function(d, e) {
            if (!e) {
                e = d;
                d = c
            }
            return d.getElementById(e)
        };
        b.get_ele_by_class = function(e, d) {
            if (!d) {
                d = e;
                e = c
            }
            return e.getElementsByClassName(d)
        };
        b.add_class = function(e, d) {
            return e.classList.add(d)
        };
        b.remove_class = function(e, d) {
            return e.classList.remove(d)
        };
        b.has_class = function(e, d) {
            return e.classList.contains(d)
        };
        b.toggle_class = function(e, d) {
            return e.classList.toggle(d)
        };
        b.item_class = function(e, d) {
            return e.classList.item(d)
        };
        b.getStyle = function(e, d) {
            return window.getComputedStyle(e, null)[d]
        };
        return b
    })();
    window.SX = {};
    SX.Dom = a
}).call(this); (function() {
    var a;
    a = (function() {
        function b() {}
        b.ajax = function(d, e) {
            var c;
            c = new XMLHttpRequest();
            c.open(d.method, d.url, isNaN(d.async));
            c.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            c.send(d.data);
            c.addEventListener("readystatechange",
            function() {
                if (c.readyState === 4 && c.status === 200) {
                    return e(c.responseText)
                }
            },
            false);
            if (!d.async) {
                e(c.responseText)
            }
        };
        return b
    })();
    SX.Ajax = a
}).call(this); (function() {
    var a;
    a = (function() {
        var c;
        c = /^(\w*)=*(.*)/;
        function b(k) {
            var g, d, j, f, i, h, e;
            j = {};
            g = k.replace("?", "");
            d = g.split("&");
            for (h = 0, e = d.length; h < e; h++) {
                i = d[h];
                f = i.match(c);
                j[f[1]] = f[2]
            }
            this.get = function(l) {
                console.log(g);
                if (!l) {
                    return g
                } else {
                    return j[l]
                }
            }
        }
        return b
    })();
    SX.Query = function(b) {
        return new a(b)
    }
}).call(this); (function() {
    var a;
    a = (function() {
        function b(o, c, n) {
            var p, m, l, k, j, i, e, d, h, g, f;
            h = null;
            k = null;
            g = null;
            f = null;
            j = null;
            i = null;
            e = null;
            d = null;
            l = function(r) {
                r.preventDefault();
                var q;
                h = +(new Date());
                q = r.touches[0];
                g = q.screenX;
                f = q.screenY
            };
            m = function(t) {
                t.preventDefault();
                var r, q, s;
                s = t.touches[0];
                e = s.screenX;
                d = s.screenY;
                r = Math.abs(e - g);
                q = Math.abs(d - f);
                if (o.touch_handle.dragLeft) {
                    if (e > g && r > q) {
                        o.touch_handle.dragLeft(t, r, g);
                        return
                    }
                }
                if (o.touch_handle.dragRight) {
                    if (e < g && r > q) {
                        o.touch_handle.dragRight(t, r, g);
                        return
                    }
                }
                if (o.touch_handle.dragUp) {
                    if (d < f && q > r) {
                        o.touch_handle.dragUp(t, q, f);
                        return
                    }
                }
                if (o.touch_handle.dragDown) {
                    if (d > f && q > r) {
                        o.touch_handle.dragDown(t, q, f)
                    }
                }
            };
            p = function(t) {
                t.preventDefault();
                var r, q, s;
                s = t.changedTouches[0];
                j = s.screenX;
                i = s.screenY;
                r = Math.abs(j - g);
                q = Math.abs(i - f);
                if (o.touch_handle.tap) {
                    k = +(new Date());
                    if (k - h < 500 && r <= 5 && q <= 5) {
                        o.touch_handle.tap(t)
                    }
                }
            };
            if (!o.touch_handle) {
                o.touch_handle = {}
            }
            if (!o.touch_handle.has_bind) {
                o.touch_handle.has_bind = true;
                o.addEventListener("touchstart", l, false);
                o.addEventListener("touchmove", m, false);
                o.addEventListener("touchend", p, false)
            }
            o.touch_handle[c] = n
        }
        return b
    })();
    SX.Touch = function(c, b, d) {
        return new a(c, b, d)
    }
}).call(this); (function() {
    var a;
    a = (function() {
        var b;
        b = document.body.style;
        c.set = function(f, d, e) {
            return f.style[d] = e
        };
        c.get_prefix_attr = function(d) {
            if (b["webkit" + d] !== void 0) {
                return d = "webkit" + d
            }
            d = d.toLocaleString();
            if (b[d]) {
                return d
            }
        };
        c.support3D = function() {
            var d;
            d = b.perspective || b.webkitPerspective;
            return d !== void 0
        };
        c.to_3D_value = function(e) {
            var d;
            d = c.support3D();
            if (d) {
                e = e + "3d"
            }
            return e
        };
        c.transform_change = function(e) {
            var d;
            e = c.to_3D_value(e);
            d = c.get_prefix_attr("Transform");
            return function(f, k, j) {
                var h, g, i;
                b = "";
                this._transform_val[e] = e + "(" + f + "," + k + "," + j + ")";
                i = this._transform_val;
                for (h in i) {
                    g = i[h];
                    b += g + " "
                }
                c.set(this._ele, d, b);
                return this
            }
        };
        c.transform_change_rotate = function() {
            var d, e;
            e = c.to_3D_value("rotate");
            d = c.get_prefix_attr("Transform");
            return function(f, l, k, i) {
                var h, g, j;
                b = "";
                this._transform_val[e] = e + "(" + f + "," + l + "," + k + "," + i + ")";
                j = this._transform_val;
                for (h in j) {
                    g = j[h];
                    b += g + " "
                }
                c.set(this._ele, d, b);
                return this
            }
        };
        function c(d) {
            var e;
            this._ele = d;
            this._transform_val = {};
            this.loop_callbak = {};
            this._attr = c.get_prefix_attr("Transform");
            this.loop_callbak[this._attr] = {};
            e = this;
            this._ele.addEventListener("webkitTransitionEnd",
            function(f) {
                f.stopPropagation();
                if (f.propertyName === "opacity") {
                    if (e.loop_callbak.opacity) {
                        e.loop_callbak.opacity()
                    }
                }
                if (f.propertyName === "transform" || f.propertyName === "-webkit-transform") {
                    if (e.loop_callbak[e._attr]["translate3d"]) {
                        e.loop_callbak[e._attr]["translate3d"]()
                    }
                    if (e.loop_callbak[e._attr]["rotate3d"]) {
                        e.loop_callbak[e._attr]["rotate3d"]()
                    }
                    if (e.loop_callbak[e._attr]["scale3d"]) {
                        e.loop_callbak[e._attr]["scale3d"]()
                    }
                    if (e.loop_callbak[e._attr]["translate"]) {
                        e.loop_callbak[e._attr]["translate"]()
                    }
                    if (e.loop_callbak[e._attr]["rotate"]) {
                        e.loop_callbak[e._attr]["rotate"]()
                    }
                    if (e.loop_callbak[e._attr]["scale"]) {
                        return e.loop_callbak[e._attr]["scale"]()
                    }
                }
            },
            false);
            return this
        }
        c.prototype.opacity = function(d) {
            c.set(this._ele, "opacity", d);
            return this
        };
        c.prototype.opacity_loop = function(g, d) {
            var e, f;
            f = this;
            e = 0;
            this.loop_callbak.opacity = function() {
                if (e % 2 === 0) {
                    f.opacity(g)
                } else {
                    f.opacity(d)
                }
                return e++
            };
            return this
        };
        c.prototype.to = c.transform_change("translate");
        c.prototype.to_loop = function(d, k, i, e, g, h) {
            var f, j;
            j = this;
            f = 0;
            b = c.to_3D_value("translate");
            this.loop_callbak[this._attr][b] = function() {
                if (f % 2 === 0) {
                    j.to(d, k, i)
                } else {
                    j.to(e, g, h)
                }
                return f++
            };
            return this
        };
        c.prototype.scale = c.transform_change("scale");
        c.prototype.scale_loop = function(d, k, i, e, g, h) {
            var f, j;
            j = this;
            f = 0;
            b = c.to_3D_value("scale");
            this.loop_callbak[this._attr][b] = function() {
                if (f % 2 === 0) {
                    j.scale(d, k, i)
                } else {
                    j.scale(e, g, h)
                }
                return f++
            };
            return this
        };
        c.prototype.rotate = c.transform_change_rotate();
        c.prototype.rotate_loop = function(l, j, i, d, e, k, f, m) {
            var g, h;
            h = this;
            g = 0;
            b = c.to_3D_value(value);
            this.loop_callbak[this._attr][b] = function() {
                if (g % 2 !== 0) {
                    h.rotate(l, j, i, d)
                } else {
                    h.rotate(e, k, f, m)
                }
                return g++
            };
            return this
        };
        return c
    })();
    SX.Animation = a;
    SX.Animation.init = function(b) {
        return new a(b)
    }
}).call(this); (function() {
    var a, d, c, e, b;
    b = SX.Touch;
    c = SX.Animation;
    d = SX.Animation.init;
    e = SX.Dom;
    a = (function() {
        var f;
        f = window.innerHeight;
        g.prototype.transitionEnd = function(h) {
            return this.callback = h
        };
        function g(m) {
            var z, p, w, q, j, h, i, v, t, l, k, y, n, x, o, u, s, r;
            this.callback = null;
            r = this;
            u = e.get_ele_by_class(m, "section_drag");
            h = [];
            o = u.length - 1;
            v = 0;
            n = 0;
            s = 0;
            k = false;
            t = false;
            y = false;
            l = null;
            j = null;
            w = function(A, B) {
                y = true;
                t = true;
                h[A].to(0, B + "px", 0)
            };
            p = function() {
                k = true;
                t = true;
                return h[v].to(0, 0, 0)
            };
            for (z = i = 0, x = u.length; i < x; z = ++i) {
                q = u[z];
                e.set_data(q, "index", z);
                h[z] = d(q);
                q.addEventListener("webkitTransitionEnd",
                function(A) {
                    A.stopPropagation();
                    if (y) {
                        y = false;
                        e.remove_class(u[s], "translate_animation");
                        e.remove_class(u[s], "section_will_show")
                    }
                    if (k) {
                        k = false;
                        e.remove_class(u[v], "translate_animation");
                        e.remove_class(u[v], "section_will_show");
                        e.add_class(u[v], "section_showed");
                        e.remove_class(u[n], "section_showed")
                    }
                    setTimeout(function() {
                        return t = false
                    },
                    300);
                    if (r.callback) {
                        return r.callback(A)
                    }
                },
                false)
            }
            e.add_class(u[0], "section_showed");
            b(m, "dragUp",
            function(A, B) {
                if (v === o || t) {
                    return false
                }
                l = "dragUp";
                e.add_class(u[v + 1], "section_will_show");
                h[v + 1].to(0, f - B + "px", 0);
                return j = B
            });
            b(m, "dragDown",
            function(A, B) {
                if (v === 0 || t) {
                    return false
                }
                l = "dragDown";
                e.add_class(u[v - 1], "section_will_show");
                h[v - 1].to(0, B - f + "px", 0);
                j = B
            });
            m.addEventListener("touchend",
            function(A) {
                if (t) {
                    return false
                }
                A.preventDefault();
                if (j < 70) {
                    if (l === "dragUp" && v >= 0 && v < o) {
                        s = v + 1;
                        e.add_class(u[s], "translate_animation");
                        w(s, f)
                    }
                    if (l === "dragDown" && v > 0 && v <= o) {
                        s = v - 1;
                        e.add_class(u[s], "translate_animation");
                        w(s, -f)
                    }
                    return l = null
                } else {
                    if (j >= 70) {
                        n = v;
                        if (l === "dragUp" && v >= 0 && v < o) {
                            v++;
                            e.add_class(u[v], "translate_animation");
                            p()
                        }
                        if (l === "dragDown" && v > 0 && v <= o) {
                            v--;
                            e.add_class(u[v], "translate_animation");
                            p()
                        }
                        l = null
                    }
                }
            },
            false)
        }
        return g
    })();
    SX.fullpage = function(f) {
        return new a(f)
    }
}).call(this); (function() {
    var a;
    a = (function() {
        function b(c, f) {
            var e, d;
            this._ele = new Audio(c);
            this._ele.loop = true;
            this._ele.autoplay = true;
            this._callback = f;
            this._ele.src = c;
            d = this
        }
        b.prototype.control = function(c) {
            var d;
            d = this;
            c.addEventListener("touchend",
            function() {
                if (!d._ele.paused) {
                    d._ele.pause();
                    d._callback && d._callback(d._ele.paused)
                } else {
                    d._ele.play();
                    d._callback && d._callback(d._ele.paused)
                }
            },
            false);
            return this
        };
        b.prototype.loop = function() {
            this._ele.addEventListener("ended",
            function() {
                this.play()
            },
            false);
            return this
        };
        b.prototype.autoPlay = function(e) {
            var c, f, d;
            f = this;
            d = function(g) {
                g.preventDefault();
                f._ele.play();
                f._callback && f._callback(status);
                if (e) {
                    e.removeEventListener("touchstart", d, false)
                } else {
                    document.documentElement.removeEventListener("touchstart", d, false)
                }
            };
            c = function(g) {
                if (g) {
                    if (e) {
                        e.addEventListener("touchstart", d, false)
                    } else {
                        document.documentElement.addEventListener("touchstart", d, false)
                    }
                } else {
                    f._callback && f._callback(g)
                }
            };
            if (window.system === "Android") {
                this._ele.addEventListener("canplay",
                function() {
                    this.play();
                    c(this.paused)
                },
                false)
            }
            if (window.system === "iPhone") {
                this._ele.addEventListener("audio",
                function(g) {
                    this.play();
                    c(this.paused)
                },
                false);
                _ios_audio_event = document.createEvent("HTMLEvents");
                _ios_audio_event.initEvent("audio", true, true);
                this._ele.dispatchEvent(_ios_audio_event)
            }
            return this
        };
        return b
    })();
    SX.AudioSelf = function(b, c) {
        return new a(b, c)
    }
}).call(this); (function() {
    var a;
    a = (function() {
        var c;
        c = document;
        function b(e) {
            var d;
            d = this;
            this.in_weixin = false;
            c.addEventListener("WeixinJSBridgeReady",
            function() {
                this.in_weixin = true;
                if (e) {
                    return e(d)
                }
            },
            false)
        }
        b.prototype.share_to_friend = function(d, e) {
            return WeixinJSBridge.on("menu:share:appmessage",
            function() {
                return WeixinJSBridge.invoke("sendAppMessage", d, e)
            })
        };
        b.prototype.share_to_timeline = function(d, e) {
            return WeixinJSBridge.on("menu:share:timeline",
            function() {
                return WeixinJSBridge.invoke("shareTimeline", d, e)
            })
        };
        return b
    })();
    SX.Weixin = function(b) {
        return new a(b)
    }
}).call(this); (function() {
    var a;
    a = function() {};
    a.loading = function(c, f) {
        var d, e, b;
        d = c.map(function(g) {
            return {
                url: g,
                isLoading: false
            }
        });
        d.forEach(function(h) {
            var g = document.createElement("img");
            g.src = h.url;
            g.addEventListener("load",
            function() {
                h.isLoading = true
            },
            false)
        });
        b = setInterval(function() {
            e = d.every(function(g) {
                return g.isLoading === true
            });
            if (e) {
                clearInterval(b);
                f && f()
            }
        },
        16)
    };
    SX.ImageLoad = a
}).call(this); (function() {
    var f = Array.prototype.forEach;
    window.init_music = function(j) {
        console.log(j);
        var i = document.createElement("div"),
        h = document.createElement("i");
        h.classList.add("iconcommon");
        i.classList.add("music");
        h.innerHTML = "&#xe601;";
        i.appendChild(h);
        document.body.appendChild(i);
        console.log(i);
        SX.AudioSelf(j,
        function(k) {
            if (!k) {
                SX.Dom.add_class(h, "music_play")
            } else {
                SX.Dom.remove_class(h, "music_play")
            }
        }).autoPlay(b).control(i)
    };
    window.in_weixin = (function() {
        var h = SX.Weixin(function(i) {
            window.in_weixin = i;
            i.share_to_friend({
                img_url: window.poster_img,
                img_width: "200",
                img_height: "200",
                link: window.location.href,
                desc: window.poster_name,
                title: window.poster_name
            },
            function() {});
            i.share_to_timeline({
                img_url: window.poster_img,
                img_width: "200",
                img_height: "200",
                link: window.location.href,
                desc: window.poster_name,
                title: window.poster_name
            },
            function() {})
        });
        if (!h.in_weixin) {
            return false
        }
    })();
    var g = document.getElementById("div_share"),
    d = document.getElementById("share_notify");
    if (g && d) {
        SX.Touch(g, "tap",
        function() {
            d.style.display = "block"
        });
        SX.Touch(d, "tap",
        function() {
            d.style.display = "none"
        })
    }
    var a = document.getElementsByClassName("section_drag")[0];
    if (a.className.match("section_showed") && a) {
        //window.__animation.get_animation_eles(a)
    }
    if (window.poster_name) {
        document.title = window.poster_name
    }
    window.poster_music && window.init_music(window.poster_music);
    function e(h) {
        c.fadeIn(SX.Dom.get_ele_by_class(h, "fade"), "fade_in_animation");
        c.fadeFromUpDown(SX.Dom.get_ele_by_class(h, "fade_from_down"), "fade_from_down_animation");
        c.fadeFromUpDown(SX.Dom.get_ele_by_class(h, "fade_from_up"), "fade_from_up_animation");
        c.fly(SX.Dom.get_ele_by_class(h, "fly_left"), "fly_left_animation");
        c.fly(SX.Dom.get_ele_by_class(h, "fly_right"), "fly_right_animation");
        c.fly(SX.Dom.get_ele_by_class(h, "fly_up"), "fly_up_animation");
        c.fly(SX.Dom.get_ele_by_class(h, "fly_down"), "fly_down_animation")
    }
    var b = SX.Dom.get_ele_by_id("full_drag");
    if (b) {
        SX.fullpage(b).transitionEnd(function(i) {
            var h = i.target;
            if (h.className.match("section_showed")) {
                e(h)
            }
        })
    }
    var c = {
        fadeIn: function(h, i) {
            f.call(h,
            function(j) {
                j.classList.add(i)
            });
            return this
        },
        fadeFromUpDown: function(h, i) {
            f.call(h,
            function(j) {
                j.classList.add(i)
            });
            return this
        },
        fly: function(h, i) {
            f.call(h,
            function(j) {
                j.classList.add(i)
            });
            return this
        }
    };
    SX.ImageLoad.loading(window.img_array,
    function() {
        var h = SX.Dom.get_ele_by_class("loading")[0];
        h.classList.add("fade_in");
        h.addEventListener("webkitTransitionEnd",
        function() {
            var i = document.getElementsByClassName("section_drag")[0];
            this.parentNode.removeChild(this);
            if (i.className.match("section_showed") && i) {
                e(i)
            }
        })
    })
}).call(this);