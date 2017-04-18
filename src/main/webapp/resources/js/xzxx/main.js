
//鼠标经过的颜色
function mouseover(row,c) {

    if (row.x != "1") row.style.backgroundColor = c;
}
//鼠标离开还原
function mouseout(row,a,b) {

    if (row.x != "1") row.style.backgroundColor = (row.sectionRowIndex % 2 == 0) ?  a: b;
}
//单选效果
function SelectRadio(row,o,chk, a, b, d) {
    if (row.x != "1") {
        row.x = "1";
        row.style.backgroundColor = d;
        chk.checked = true;
       // checkSingle(chk, o,d);
    } else {
       row.x = "0";
       row.style.backgroundColor = (row.sectionRowIndex % 2 == 0) ? a : b;
       chk.checked = false;
      // checkSingle(chk, o,d);
    }
}

//全选
function SelectAll(o,a,b,d) {
   var grid = document.getElementById(o);
   if(grid != null)
   {
    if (checkflag == "false")
    {
     for(i = 1;i < grid.rows.length; i++) {

         var ckb = grid.rows[i].cells[0].getElementsByTagName("input")[0];
         if (ckb != null) {
             ckb.checked = true;
             grid.rows[i].x = "1"
             grid.rows[i].style.backgroundColor = d;
           }
        }
        checkflag = "true";
        return "img/quxiao.gif";
    }
    else
    {
     for(i = 1;i < grid.rows.length; i++) 
        {        
         var ckb = grid.rows[i].cells[0].getElementsByTagName("input")[0];
         if (ckb != null) {
             ckb.checked = false;
             grid.rows[i].x = "0"
             grid.rows[i].style.backgroundColor = (grid.rows[i].sectionRowIndex % 2 == 0) ? a : b;
         }
        }
        checkflag = "false";
        return "img/quanxuan.gif";
    }
  }
   else
       return "img/quanxuan.gif";
}

 function setMaxHeight(elementId, height) {

   var container = document.getElementById(elementId);

 container.style.height = (container.scrollHeight > (height - 1)) ? height + "px" : "auto";

}
 

/*杨代码
//表格鼠标经过效果
function senfe(o, a, b, c, d) {
    var t = document.getElementById(o).getElementsByTagName("tr");
    var chk = document.getElementsByName("Chk");
    for (var i = 0; i < t.length; i++) {
        t[i].style.backgroundColor = (t[i].sectionRowIndex % 2 == 0) ? a : b;
        t[i].onclick = function() {
            if (this.x != "1") {
                this.x = "1";
                this.style.backgroundColor = d;
                checkSingle(chk, o);
            } else {
                this.x = "0";
                this.style.backgroundColor = (this.sectionRowIndex % 2 == 0) ? a : b;
                checkSingle(chk, o);
            }
        }
        t[i].onmouseover = function() {
            if (this.x != "1") this.style.backgroundColor = c;
        }
        t[i].onmouseout = function() {
            if (this.x != "1") this.style.backgroundColor = (this.sectionRowIndex % 2 == 0) ? a : b;
        }
    }
}

function check(o, a, b, c, d) {
    
    var t = document.getElementById(o).getElementsByTagName("tr");
    var field = document.getElementsByName("ckb");
    if (checkflag == "false") {
        for (i = 0; i < field.length; i++) {
            field[i].checked = true;
            t[i + 1].x = "1";
            t[i + 1].style.backgroundColor = d;
        }
        checkflag = "true";
        return "img/quxiao.gif";
    }
    else {
        for (i = 0; i < field.length; i++) {
            field[i].checked = false;
            t[i + 1].x = "0";
            t[i + 1].style.backgroundColor = (t[i + 1].sectionRowIndex % 2 == 0) ? a : b;
        }
        checkflag = "false";
       return "img/quanxuan.gif";

    }
}

function checkSingle(field,o,d) {
    var t = document.getElementById(o).getElementsByTagName("tr");
   // var myControllerId = <%=check.ClientId%>

    for (var i = 0; i <= field.length; i++) {
        if (t[i].style.backgroundColor == d && i >= 1) {
            //  var a="<%=check(i-1,true)%>";
            field[i - 1].checked = true;
        }
        if (t[i].style.backgroundColor != d && i >= 1) {
            field[i - 1].checked = false;
            //   a="<%check(i-1,false)%>";
        }

    }
}
*/