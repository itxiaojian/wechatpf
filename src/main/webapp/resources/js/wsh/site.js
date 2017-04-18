$(document).ready(function () {
    $(".head_menu").click(function () {
        $(".menu_child").toggle(200);
    });
    /////////////////////////////////查询
    $("#searaniu").click(function () {
        var line = $("#line").val().replace("路", "");

        if (line == "" || line.match(/[\x01-\xFF]*/) == false) {
            alert('请输入线路编号')
        }
        else {
            $('#lineform').submit();
        }
    });
    ///////////////////////////////位置
    $("ul").click(function () {
        $("ul").each(function () {
            $(this).attr("style", "color:#333333");
            $(this).children("div").attr("style", "border-color:#929292");
        });
        $(this).attr("style", "color:#ff7e00");
        $(this).children("div").attr("style", "border-color:#ff7e00");
        var g = $(this).children("p").text();

        $.ajax({
            type: 'post',
            url: '/m/station',
            data: { 'id': $("#line").val(), 'g': g },
            success: function (data) {
                $("p").each(function (i, n) {
                    var ul = $(this).parents("ul");
                    var li = ul.find("li:eq(1)");
                    li.removeClass("busicon");
                    li.text("");
                    var count = data.busList.length;
                    for (i = 0; i < count; i++) {
                        if ($(this).text() == data.busList[i].currentStationGPS) {

                            li.addClass("busicon");
                            if (data.busList[i].lineDist == 0) {
                                li.text("到站");
                            }
                            else {
                                li.text("距 " + data.busList[i].lineDist + " 站");
                            }

                        }
                    }
                })

            }
        });

    });
    ////////////////////////////////////换向
 /*   $(".busline").click(function () {
        window.location.href = "/m/line?id=" + $("#line").val() + "&t=" + $("#t").val();
    });
*/
    ////////////////////////////////////
});