function get_pie_chart_base_options_value(question_id) {
  var options = {
    chart: {
      type: 'pie',
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    title: {
      text: 'Question Title',
      style:{
        fontFamily:'Microsoft YaHei'
      }
    },
    subtitle: {
      text:'',
      style:{
        fontFamily:'Microsoft YaHei'
      }
    },
    tooltip: {
      formatter: function () {
        if(this.point.name.indexOf('<img') != -1)
        {
          //html 图片标签格式
          return '<b>' + this.point.name + '</b>: ' + this.y;
        }
        else
          return '<b>' + this.point.name + '</b>: ' + this.y;
      },
      useHTML: true,
      percentageDecimals: 1,
      style:{
        color:'#888888',
        fontFamily:'Microsoft YaHei'
      }
    },
    plotOptions: {
      pie: {
        allowPointSelect: true,
        cursor: 'pointer',
        dataLabels: {
          enabled: true,
          color: '#888888',
          connectorColor: '#888888',
          useHTML: true,
          style:{
            fontFamily:'Microsoft YaHei'
          },
          formatter: function() {
            if(this.point.name.indexOf('<img') != -1)
            {
              //html 图片标签格式
              // if(this.point.name.split('<img')[1])
              if(this.point.name.split('<img')[1].split('/>')[1].length>5)
              {
                var tmp = this.point.name.split('<img')[1].split('/>')[1];
                return '<b class="chart_op_icon">'+this.point.name.replace(tmp, '')+this.point.name.split('<img')[1].split('/>')[1].substr(0,5) + '...</b> '  + this.point.y;
              }
              else
                return '<b class="chart_op_icon">'+this.point.name + '</b> '  + this.point.y;
            }
            else if(this.point.name.length>8)
              return '<b>' + this.point.name.substr(0,8) + '...</b>: '  + this.point.y;
            else
              return '<b>' + this.point.name + '</b>: '  + this.point.y;
          }
        }
      }
    },
    series: []
  };
  return options;
}

function get_pie_chart_base_options_percent(question_id) {
  var options = {
    chart: {
      type: 'pie',
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    title: {
      text: 'Question Title',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    subtitle: {
      text:'',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    tooltip: {
      formatter: function () {
        if(this.point.name.indexOf('<img') != -1)
        {
          //html 图片标签格式
          return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 2) + ' %';
        }
        else
          return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 2) + ' %';
      },
      percentageDecimals: 1,
      style:{
        color:'#888888',
        fontFamily:'Microsoft YaHei'
      }
    },
    plotOptions: {
      pie: {
        allowPointSelect: true,
        cursor: 'pointer',
        dataLabels: {
          enabled: true,
          color: '#888888',
          connectorColor: '#888888',
          useHTML: true,
          style:{
            fontFamily:'Microsoft YaHei'
          },
          formatter: function() {
            if(this.point.name.indexOf('<img') != -1)
            {
              //html 图片标签格式
              if(this.point.name.split('<img')[1].split('/>')[1].length>5)
              {
                var tmp = this.point.name.split('<img')[1].split('/>')[1];
                return '<b class="chart_op_icon">'+this.point.name.replace(tmp,'')+this.point.name.split('<img')[1].split('/>')[1].substr(0,5) + '...</b> '  + Highcharts.numberFormat(this.percentage, 2) + '%';
              }
              else
                return '<b class="chart_op_icon">'+this.point.name + '</b> '  + Highcharts.numberFormat(this.percentage, 2) + '%';
            }
            else if(this.point.name.length>8)
              return '<b>' + this.point.name.substr(0,8) + '...</b>: '  + Highcharts.numberFormat(this.percentage, 2) + '%';
            else
              return '<b>' + this.point.name + '</b>: '  + Highcharts.numberFormat(this.percentage, 2) + '%';
          }
        }
      }
    },
    series: []
  };
  return options;
}

function get_donut_chart_base_options_value(question_id) {
  var options = {
    chart: {
      type: 'pie',
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    title: {
      text: 'Question Title',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    subtitle: {
      text:'',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    tooltip: {
      formatter: function () {
        if(this.point.name.indexOf('<img') != -1)
        {
          //html 图片标签格式
          return '<b>' + this.point.name + '</b>: ' + this.y;
        }
        else
          return '<b>' + this.point.name + '</b>: ' + this.y;
      },
      percentageDecimals: 1,
      useHTML: true,
      style:{
        color:'#888888',
        fontFamily:'Microsoft YaHei'
      }
    },
    plotOptions: {
      pie: {
        allowPointSelect: true,
        cursor: 'pointer',
        dataLabels: {
          enabled: true,
          color: '#888888',
          style:{
            fontFamily:'Microsoft YaHei'
          },
          connectorColor: '#888888',
          useHTML: true,
          formatter: function() {
            if(this.point.name.indexOf('<img') != -1)
            {
              //html 图片标签格式
              if(this.point.name.split('<img')[1].split('/>')[1].length>5)
                return '<b class="chart_op_icon">'+this.point.name.split('<img')[1].split('/>')[1].substr(0,5) + '...</b> '  + this.point.y;
              else
                return '<b class="chart_op_icon">'+this.point.name + '</b> '  + this.point.y;
            }
            else if(this.point.name.length>5)
              return '<b>' + this.point.name.substr(0,5) + '...</b>: '  + this.point.y;
            else
              return '<b>' + this.point.name + '</b>: '  + this.point.y;
          }
        }
      }
    },
    series: [{
      name: 'Browsers',
      data: [],
      size: '60%',
      innerSize: '50%'
    }]
  };
  return options;
}

function get_donut_chart_base_options_percent(question_id) {
  var options = {
    chart: {
      type: 'pie',
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    title: {
      text: 'Question Title',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    subtitle: {
      text:'',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    tooltip: {
      formatter: function () {
        if(this.point.name.indexOf('<img') != -1)
          return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 2) + ' %';
        else
          return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 2) + ' %';
      },
      useHTML: true,
      percentageDecimals: 1,
      style:{
            color:'#888888',
            fontFamily:'Microsoft YaHei'
          }
    },
    plotOptions: {
      pie: {
        allowPointSelect: true,
        cursor: 'pointer',
        dataLabels: {
          enabled: true,
          color: '#888888',
          connectorColor: '#888888',
          style:{
            fontFamily:'Microsoft YaHei'
          },
          useHTML: true,
          formatter: function() {
            if(this.point.name.indexOf('<img') != -1)
            {
              //html 图片标签格式
              if(this.point.name.split('<img')[1].split('/>')[1].length>5)
                return '<b class="chart_op_icon">'+this.point.name.split('<img')[1].split('/>')[1].substr(0,5) + '...</b> '  + Highcharts.numberFormat(this.percentage, 2) + '%';
              else
                return '<b class="chart_op_icon">'+this.point.name + '</b> '  + Highcharts.numberFormat(this.percentage, 2) + '%';
            }
            else if(this.point.name.length>5)
              return '<b>' + this.point.name.substr(0,5) + '...</b>: '  + Highcharts.numberFormat(this.percentage, 2) + '%';
            else
              return '<b>' + this.point.name + '</b>: '  + Highcharts.numberFormat(this.percentage, 2) + '%';
          }
        }
      }
    },
    series: [{
      name: 'Browsers',
      data: [],
      size: '60%',
      innerSize: '50%'
    }]
  };
  return options;
}

function get_column_chart_base_options_value(question_id) {
  var options = {
    chart: {
      type: 'column',
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    title: {
      text: 'Question Title',
      style:{
            fontFamily:'Microsoft YaHei'
          },
      margin: 50
    },
    subtitle: {
      text:'',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    plotOptions: {
      column: {
        dataLabels: {
          enabled: true,
          style:{
            colors:'#888888',
            fontFamily:'Microsoft YaHei'
          },
          formatter: function(){
            if(this.y == 0)
              return '';
            else
              return this.y.toFixed(0);
          }
        }
      }
    },
    xAxis: {
      categories: ['Tokyo', 'Jakarta', 'New York', 'Tokyo', 'Jakarta', 'New York', 'Seoul', 'Manila', 'Mumbai', 'Sao Paulo', 'Mexico City', 'Dehli', 'Osaka', 'Cairo', 'Kolkata', 'Los Angeles', 'Shanghai', 'Moscow', 'Beijing', 'Buenos Aires', 'Guangzhou', 'Shenzhen', 'Istanbul'],
      labels: {
        align: 'center',
        style:{
          fontFamily:'Microsoft YaHei'
          },
          useHTML: true,
          formatter: function() {
            if(this.value.indexOf('<img') != -1)
            {
              var result = '<br><br><br>';
              var temp = this.value.split('</p>')[0].split('/>')[1];
              var img_str = this.value.replace(temp, '');
              temp = temp.replace(/<[^>]+>/g,"");
              for(var i=0;i<temp.length;i++)
              {
                result += temp[i];
                if(i==4)
                  break;
              }
              if(temp.length>4)
              {
                result += '...';
              }
              return '<b class="chart_op_icon" style="margin: 0 auto;width: 30px;display: block;">'+img_str+'</b><span title="'+temp+'">'+result+'</span>';
            }
            else if(this.value.length > 5)
              return '<span title="'+this.value+'">'+this.value.substr(0,5) + '...</span>';
            else
              return this.value;
          }
      }
    },
    yAxis: {
      allowDecimals: false,
      min: 0,
      title: {
        text: '',
        style:{
          fontFamily:'Microsoft YaHei'
          }
      }
    },
    legend: {
      enabled: false,
      useHTML: true,
      backgroundColor: '#FFFFFF',
      reversed: true,
      labelFormatter: function() {
        if(this.name.length>12)
          return '<b class="chart_op_icon">' + this.name.substr(0,12) + '...</b>';
        else
          return '<b class="chart_op_icon">' + this.name + '</b>';
            }
    },
    tooltip: {
      style:{
          colors:'#888888',
          fontSize: '13px',
          fontFamily:'Microsoft YaHei'
          },
      useHTML: true,
      formatter: function() {
        if(this.x.indexOf('<img') != -1)
          return '选中人数: ' + Highcharts.numberFormat(this.y, 0);
        else
          return '<b>' + this.x + '</b><br/>' + '选中人数: ' + Highcharts.numberFormat(this.y, 0);
      }
    },
    series: [{
      name: '选中人数',
      data: [34.4, 21.8, 20.1, 20, 19.6, 19.5, 19.1, 18.4, 18, 17.3, 16.8, 15, 14.7, 14.5, 13.3, 12.8, 12.4, 11.8, 11.7, 11.2]
    }]
  };
  return options;
}

function get_column_chart_base_options_percent(question_id) {
  var options = {
    chart: {
      type: 'column',
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    title: {
      text: 'Question Title',
      style:{
          fontFamily:'Microsoft YaHei'
          },
      margin: 50
    },
    subtitle: {
      text:'',
      style:{
          fontFamily:'Microsoft YaHei'
          }
    },
    plotOptions: {
      column: {
        dataLabels: {
          enabled: true,
          style:{
            colors:'#888888',
            fontFamily:'Microsoft YaHei'
          },
          formatter: function(){
            return this.y.toFixed(2) + '%';
          }
        }
      }
    },
    xAxis: {
      categories: ['Tokyo', 'Jakarta', 'New York', 'Tokyo', 'Jakarta', 'New York', 'Seoul', 'Manila', 'Mumbai', 'Sao Paulo', 'Mexico City', 'Dehli', 'Osaka', 'Cairo', 'Kolkata', 'Los Angeles', 'Shanghai', 'Moscow', 'Beijing', 'Buenos Aires', 'Guangzhou', 'Shenzhen', 'Istanbul'],
      labels: {
        align: 'center',
        style: {
          fontFamily: 'Microsoft YaHei'
        },
        useHTML: true,
        formatter: function() {
            if(this.value.indexOf('<img') != -1)
            {
              var result = '<br><br><br>';
              var temp = this.value.split('</p>')[0].split('/>')[1];
              var img_str = this.value.replace(temp, '');
              temp = temp.replace(/<[^>]+>/g,"");
              for(var i=0;i<temp.length;i++)
              {
                result += temp[i];
                if(i==4)
                  break;
              }
              if(temp.length>4)
              {
                result += '...';
              }
              return '<b class="chart_op_icon" style="margin: 0 auto;width: 30px;display: block;">'+img_str+'</b><span title="'+temp+'">'+result+'</span>';
            }
            else if(this.value.length > 5)
              return '<span title="'+this.value+'">'+this.value.substr(0,5) + '...</span>';
            else
              return this.value;
          }
      }
    },
    yAxis: {
      min: 0,
      title: {
        text: '',
        style:{
            fontFamily:'Microsoft YaHei'
          }
      }
    },
    legend: {
      enabled: false,
      useHTML: true,
      backgroundColor: '#FFFFFF',
      reversed: true,
      labelFormatter: function() {
        if(this.name.length>12)
          return '<b class="chart_op_icon">' + this.name.substr(0,12) + '...</b>';
        else
          return '<b class="chart_op_icon">' + this.name + '</b>';
            }
    },
    tooltip: {
      style:{
            colors:'#888888',
            fontFamily:'Microsoft YaHei'
          },
      useHTML: true,
      formatter: function() {
        if(this.x.indexOf('<img') != -1)
          return '选中人数: ' + Highcharts.numberFormat(this.y.toFixed(2), 2) + '%';
        else
          return '<b>' + this.x + '</b><br/>' + '选中人数: ' + Highcharts.numberFormat(this.y.toFixed(2), 2) + '%';
      }
    },
    series: [{
      name: '选中人数',
      data: [34.4, 21.8, 20.1, 20, 19.6, 19.5, 19.1, 18.4, 18, 17.3, 16.8, 15, 14.7, 14.5, 13.3, 12.8, 12.4, 11.8, 11.7, 11.2]
    }]
  };
  return options;
}

function get_bar_chart_base_options_value(question_id) {
  var options = {
    chart: {
      type: 'bar',
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    title: {
      text: 'Historic World Population by Region',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    subtitle: {
      text:'',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    xAxis: {
      categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
      title: {
        style:{
            fontFamily:'Microsoft YaHei'
          }
      },
      labels: {
        align: 'right',
        distance: -50,
        style: {
          fontFamily: 'Microsoft YaHei'
        },
        useHTML: true,
        formatter: function() {
            if(this.value.indexOf('<img') != -1)
              {
                var img_str;
                this.value.replace(/<img [^>]*src=['"]([^'"]+)[^>]*>/, function (match) {
                    img_str = match;
                });
                var result = '';
                // var temp = this.value.split('</p>')[0].split('/>')[1];
                var temp = this.value.replace(/<[^>]+>/g,"");
                for(var i=0;i<temp.length;i++)
                {
                  result += temp[i];
                  if(i==4)
                    break;
                }
                if(temp.length>4)
                {
                  result += '...';
                }
                return '<b class="chart_op_icon" style="margin: 0 0 0 0;width: 100%;display: inline-block;/* float: right; */text-align: right;">'+img_str+'</b><span style="display: inherit" title="'+temp+'">'+result+'</span>';
            }
            else if(this.value.length > 15)
              return '<span title="'+this.value+'">'+this.value.substr(0,15) + '...</span>';
            else
              return this.value;
          }
      }
    },
    yAxis: {
      allowDecimals: false,
      min: 0,
      title: {
        text: '',
        align: 'high',
        style:{
            fontFamily:'Microsoft YaHei'
          }
      },
      labels: {
        align: 'left',
        overflow: 'justify',
        style:{
            fontFamily:'Microsoft YaHei'
          }
      }
    },
    tooltip: {
      valueSuffix: '',
      style:{
            colors:'#888888',
            fontFamily:'Microsoft YaHei'
          },
    formatter: function() {
      if(this.x.indexOf('<img') != -1)
        return '选中人数: ' + this.y;
      else
        return '<b>' + this.x + '</b><br/>' + '选中人数: ' + this.y;
      }
    },
    plotOptions: {
      bar: {
        dataLabels: {
          style:{
            colors:'#888888',
            fontFamily:'Microsoft YaHei'
          },
          enabled: true
        }
      }
    },
    legend: {
      enabled: false,
      useHTML: true,
      backgroundColor: '#FFFFFF',
      reversed: true,
      labelFormatter: function() {
        if(this.name.length>12)
          return '<b class="chart_op_icon">' + this.name.substr(0,12) + '...</b>';
        else
          return '<b class="chart_op_icon">' + this.name + '</b>';
            }
    },
    credits: {
      enabled: false
    },
    series: [{
      data: [107, 31, 635, 203, 2]
    }]
  };
  return options;
}

function get_bar_chart_base_options_percent(question_id) {
  var options = {
    chart: {
      type: 'bar',
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    title: {
      text: 'Historic World Population by Region',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    subtitle: {
      text:'',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    xAxis: {
      categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
      title: {
        text: '',
        style:{
            fontFamily:'Microsoft YaHei'
          }
      },
      labels: {
        align: 'right',
        style: {
          fontFamily: 'Microsoft YaHei'
        },
        useHTML: true,
        formatter: function() {
            if(this.value.indexOf('<img') != -1)
              {
                var img_str;
                this.value.replace(/<img [^>]*src=['"]([^'"]+)[^>]*>/, function (match) {
                    img_str = match;
                });
                var result = '';
                // var temp = this.value.split('</p>')[0].split('/>')[1];
                var temp = this.value.replace(/<[^>]+>/g,"");
                for(var i=0;i<temp.length;i++)
                {
                  result += temp[i];
                  if(i==4)
                    break;
                }
                if(temp.length>4)
                {
                  result += '...';
                }
                return '<b class="chart_op_icon" style="margin: 0 0 0 0;width: 100%;display: inline-block;/* float: right; */text-align: right;">'+img_str+'</b><span style="display: inherit" title="'+temp+'">'+result+'</span>';
            }
            else if(this.value.length > 15)
              return '<span title="'+this.value+'">'+this.value.substr(0,15) + '...</span>';
            else
              return this.value;
          }
      }
    },
    yAxis: {
      min: 0,
      title: {
        text: '',
        align: 'high',
        style:{
            fontFamily:'Microsoft YaHei'
          }
      },
      labels: {
        overflow: 'justify',
        style:{
            fontFamily:'Microsoft YaHei'
          }
      }
    },
    tooltip: {
      style:{
            colors:'#888888',
            fontFamily:'Microsoft YaHei'
          },
      formatter: function() {
        return '<b>' + this.x + '</b><br/>' + '百分比: ' + Highcharts.numberFormat(this.y.toFixed(2), 2)+'%';
      }
    },
    plotOptions: {
      bar: {
        dataLabels: {
          enabled: true,
          style:{
            colors:'#888888',
            fontFamily:'Microsoft YaHei'
          },
          formatter:function(){
            return this.y.toFixed(2)+'%';
          }
        }
      }
    },
     legend: {
      enabled: false,
      useHTML: true,
      backgroundColor: '#FFFFFF',
      reversed: true,
      labelFormatter: function() {
        if(this.name.length>12)
          return '<b class="chart_op_icon">' + this.name.substr(0,12) + '...</b>';
        else
          return '<b class="chart_op_icon">' + this.name + '</b>';
            }
    },
    credits: {
      enabled: false
    },
    series: [{
      data: [107, 31, 635, 203, 2]
    }]
  };
  return options;
}

function get_line_chart_base_options_value(question_id) {
  var options = {
    chart: {
      type: 'line',
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    title: {
      text: 'Question Title',
      x: -20 ,
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    subtitle: {
      text:'',
      style:{
            fontFamily:'Microsoft YaHei'
          },
      x: -20
    },
    xAxis: {
      labels: {
        zIndex: 100,
        align: 'center',
        style:{
          fontFamily:'Microsoft YaHei'
          },
          useHTML:true,
          formatter: function() {
            if(this.value.indexOf('<img') != -1)
            {
              var result = '<br><br><br>';
              var temp = this.value.split('</p>')[0].split('/>')[1];
              var img_str = this.value.replace(temp, '');
              temp = temp.replace(/<[^>]+>/g,"");
              for(var i=0;i<temp.length;i++)
              {
                result += temp[i];
                if(i==4)
                  break;
              }
              if(temp.length>4)
              {
                result += '...';
              }
              return '<b class="chart_op_icon" style="margin: 0 auto;width: 30px;display: block;">'+img_str+'</b><span title="'+temp+'">'+result+'</span>';
            }
            else if(this.value.length > 5)
              return '<span title="'+this.value+'">'+this.value.substr(0,5) + '...</span>';
            else
              return this.value;
          }
      },
      categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    },
    yAxis: {
      allowDecimals: false,
      title: {
        style:{
            fontFamily:'Microsoft YaHei'
          },
        text: '选中人数'
      },
      plotLines: [{
        value: 0,
        width: 1,
        color: '#808080'
      }]
    },
    tooltip: {
      valueSuffix: '',
      style:{
            colors:'#888888',
            fontFamily:'Microsoft YaHei'
          },
      formatter: function(){
        if(this.x.indexOf('<img') != -1)
          return '<b>' + this.x + '</b><br/>' + '数值: ' + this.y;
        else
          return '<b>' + this.x + '</b><br/>' + '数值: ' + this.y;
      }
    },
    legend: {
      useHTML: true,
      backgroundColor: '#FFFFFF',
      reversed: true,
      labelFormatter: function() {
        if(this.name.length>12)
          return '<b class="chart_op_icon">' + this.name.substr(0,12) + '...</b>';
        else
          return '<b class="chart_op_icon">' + this.name + '</b>';
            }
    },
    series: [{}]
  };
  return options;
}

function get_line_chart_base_options_percent(question_id) {
  var options = {
    chart: {
      type: 'line',
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    plotOptions: {
      line: {
        dataLabels: {
          style:{
            colors:'#888888',
            fontFamily:'Microsoft YaHei'
          },
          enabled: false,
          formatter: function(){
            return this.y.toFixed(2) + '%';
          }
        }
      }
    },
    title: {
      text: 'Question Title',
      style:{
            fontFamily:'Microsoft YaHei'
          },
      x: -20 //center
    },
    subtitle: {
      text:'',
      style:{
            fontFamily:'Microsoft YaHei'
          },
      x: -20
    },
    xAxis: {
      labels: {
        align: 'center',
        style:{
          fontFamily:'Microsoft YaHei'
          },
        useHTML:true,
          formatter: function() {
            if(this.value.indexOf('<img') != -1)
            {
              var result = '<br><br><br>';
              var temp = this.value.split('</p>')[0].split('/>')[1];
              var img_str = this.value.replace(temp, '');
              temp = temp.replace(/<[^>]+>/g,"");
              for(var i=0;i<temp.length;i++)
              {
                result += temp[i];
                if(i==4)
                  break;
              }
              if(temp.length>4)
              {
                result += '...';
              }
              return '<b class="chart_op_icon" style="margin: 0 auto;width: 30px;display: block;">'+img_str+'</b><span title="'+temp+'">'+result+'</span>';
            }
            else if(this.value.length > 5)
              return '<span title="'+this.value+'">'+this.value.substr(0,5) + '...</span>';
            else
              return this.value;
          }
      },
      categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    },
    yAxis: {
      title: {
        text: '',
        style:{
            fontFamily:'Microsoft YaHei'
          }
      },
      plotLines: [{
        value: 0,
        width: 1,
        color: '#808080'
      }]
    },
    tooltip: {
      style:{
            colors:'#888888',
            fontFamily:'Microsoft YaHei'
          },
      formatter: function() {
        return '<b>' + this.x + '</b><br/>' + '百分比: ' + Highcharts.numberFormat(this.y.toFixed(2), 2)+'%';
      }
    },
    legend: {
      useHTML: true,
      backgroundColor: '#FFFFFF',
      reversed: true,
      labelFormatter: function() {
        if(this.name.length>12)
          return '<b class="chart_op_icon">' + this.name.substr(0,12) + '...</b>';
        else
          return '<b class="chart_op_icon">' + this.name + '</b>';
            }
    },
    series: [{}]
  };
  return options;
}

function get_radar_chart_base_options_value(question_id) {
  var options = {
    chart: {
      polar: true,
      type: 'line',
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    title: {
      text: 'Question Title',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    subtitle: {
      text:'',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    pane: {
      size: '80%'
    },
    legend: {
      useHTML: true,
      backgroundColor: '#FFFFFF',
      reversed: true,
      labelFormatter: function() {
        if(this.name.length>12)
          return '<b class="chart_op_icon">' + this.name.substr(0,12) + '...</b>';
        else
          return '<b class="chart_op_icon">' + this.name + '</b>';
            }
    },
    xAxis: {
      categories: ['Sales', 'Marketing', 'Development', 'Customer Support', 'Information Technology', 'Administration'],
      tickmarkPlacement: 'on',
      lineWidth: 0,
      labels:{
        style:{
            fontFamily:'Microsoft YaHei'
          },
        useHTML:true,
        formatter: function() {
          if(this.value.length>5)
            return '<b class="chart_op_icon">' + this.value.substr(0,5) + '...</b>';
          else
            return '<b class="chart_op_icon">' + this.value + '</b>';
        }
      }
    },
    yAxis: {
      gridLineInterpolation: 'polygon',
      lineWidth: 0,
      min: 0,
      labels:{
        style:{
            fontFamily:'Microsoft YaHei'
          }
      }
    },
    tooltip: {
      shared: true,
      pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y:,.2f}</b><br/>',
      style:{
            color:'#888888',
            fontFamily:'Microsoft YaHei'
          },
      formatter: function() {
        if(this.x.indexOf('<img') != -1)
          return this.y.toFixed(2);
        else
          return '<b>'+this.x+'<br>'+this.y.toFixed(2)+'</b>';
        }
    },
    plotOptions: {
      radar:{
        dataLabels:{
          enabled: true,
          style:{
            color:'#888888',
            fontFamily:'Microsoft YaHei'
          },
          color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
          formatter : function(){
            return point.y.toFixed(2);
          }
        }
      }
    },
    series: [{
      name: 'Allocated Budget',
      data: [1, 2, 3],
      pointPlacement: 'on'
    }]

  };
  return options;
}

function get_radar_chart_base_options_percent(question_id) {
  var options = {
    chart: {
      polar: true,
      type: 'line',
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    title: {
      text: 'Question Title',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    subtitle: {
      text:'',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    pane: {
      size: '80%'
    },
    legend: {
      useHTML: true,
      backgroundColor: '#FFFFFF',
      reversed: true,
      labelFormatter: function() {
        if(this.name.length>12)
          return '<b class="chart_op_icon">' + this.name.substr(0,12) + '...</b>';
        else
          return '<b class="chart_op_icon">' + this.name + '</b>';
            }
    },
    xAxis: {
      categories: ['Sales', 'Marketing', 'Development', 'Customer Support', 'Information Technology', 'Administration'],
      tickmarkPlacement: 'on',
      lineWidth: 0,
      labels:{
        style:{
            fontFamily:'Microsoft YaHei'
          },
        useHTML:true,
        formatter: function() {
          if(this.value.length>5)
            return '<b class="chart_op_icon">' + this.value.substr(0,5) + '...</b>';
          else
            return '<b class="chart_op_icon">' + this.value + '</b>';
        }
      }
    },
    yAxis: {
      gridLineInterpolation: 'polygon',
      lineWidth: 0,
      min: 0,
      labels:{
        style:{
            fontFamily:'Microsoft YaHei'
          }
      }
    },
    tooltip: {
      shared: true,
      pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y:,.2f}%</b><br/>',
      style:{
            color:'#888888',
            fontFamily:'Microsoft YaHei'
          },
      formatter: function() {
          return '<b>'+this.x+'<br>'+this.y.toFixed(2) + '%</b>';
        }
    },
    plotOptions: {
      radar:{
        dataLabels:{
          enabled: true,
          style:{
            color:'#888888',
            fontFamily:'Microsoft YaHei'
          },
          color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
          formatter : function(){
            return point.y.toFixed(2) + '%';
          }
        }
      }
    },
    series: [{
      name: 'Allocated Budget',
      data: [1, 2, 3],
      pointPlacement: 'on'
    }]

  };
  return options;
}

function get_stackcolumn_chart_base_options(question_id) {
  var options = {
    chart: {
      type: 'column',
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    title: {
      text: 'Stacked bar chart',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    subtitle: {
      text:'',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    xAxis: {
      categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas'],
      labels:{
        style:{
            fontFamily:'Microsoft YaHei'
          },
        useHTML: true,
        formatter: function() {
          if(this.value.indexOf('<img') != -1)
            return '<b class="chart_op_icon">'+this.value+'</b>';
          if(this.value.length > 5)
            return this.value.substr(0,5) + '...';
          else
            return this.value;
        }
      }
    },
    yAxis: {
      min: 0,
      title: {
        text: '',
        style:{
            fontFamily:'Microsoft YaHei'
          }
      }
    },
    legend: {
      useHTML: true,
      backgroundColor: '#FFFFFF',
      reversed: true,
      labelFormatter: function() {
        if(this.name.length>12)
          return '<b class="chart_op_icon">' + this.name.substr(0,12) + '...</b>';
        else
          return '<b class="chart_op_icon">' + this.name + '</b>';
            }
    },
    tooltip: {
      style:{
            color:'#888888',
            fontFamily:'Microsoft YaHei'
          },
      formatter: function() {
        return this.y.toFixed(2)+'%';
      }
    },
    plotOptions: {
      column:{
        stacking: 'normal',
        dataLabels:{
          style:{
            color:'#888888',
            fontFamily:'Microsoft YaHei'
          },
          enabled: true,
          color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
          formatter : function(){
            if(this.y.toFixed(0) == 0)
              return '';
            else
              return this.y.toFixed(2) + '%';
          }
        }
      }
    },
    series: [{
      name: 'John',
      data: [5, 3, 4, 7, 2]
    }, {
      name: 'Jane',
      data: [2, 2, 3, 2, 1]
    }, {
      name: 'Joe',
      data: [3, 4, 4, 2, 5]
    }]
  };
  return options;
}

function get_stackbar_chart_base_options(question_id) {
  var options = {
    chart: {
      type: 'bar',
      renderTo: 'container_' + question_id
    },
    exporting:{
      enabled:true
    },
    colors:['#22b5c3', '#a3be57', '#ff9c9c', '#48cfef', '#25bf6e', '#ea5f35', '#7e85e0', '#f2bd7c', '#bbbbba', '#7257a2'],
    title: {
      text: 'Stacked bar chart',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    subtitle: {
      text:'',
      style:{
            fontFamily:'Microsoft YaHei'
          }
    },
    xAxis: {
      categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas'],
      labels:{
        style:{
            fontFamily:'Microsoft YaHei'
          },
        useHTML: true,
        formatter: function() {
          if(this.value.indexOf('<img') != -1)
            return '<b class="chart_op_icon">'+this.value+'</b>';
          if(this.value.length > 5)
            return this.value.substr(0,5) + '...';
          else
            return this.value;
        }
      }
    },
    yAxis: {
      min: 0,
      title: {
        style:{
            fontFamily:'Microsoft YaHei'
          },
        text: ''
      }
    },
    legend: {
      useHTML: true,
      backgroundColor: '#FFFFFF',
      reversed: true,
      labelFormatter: function() {
        if(this.name.length>12)
          return '<b class="chart_op_icon">' + this.name.substr(0,12) + '...</b>';
        else
          return '<b class="chart_op_icon">' + this.name + '</b>';
            }
    },
    plotOptions: {
      bar:{
        stacking: 'normal',
        dataLabels:{
          style:{
            color:'#888888',
            fontFamily:'Microsoft YaHei'
          },
          enabled: true,
          color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
          formatter : function(){
            if(this.y.toFixed(0) == 0)
              return '';
            else
              return this.y.toFixed(2) + '%';
          }
        }
      }
    },
    tooltip: {
      style:{
            color:'#888888',
            fontFamily:'Microsoft YaHei'
          },
      formatter: function() {
        return this.y.toFixed(2)+'%';
      }
    },
    series: [{
      name: 'John',
      data: [5, 3, 4, 7, 2]
    }, {
      name: 'Jane',
      data: [2, 2, 3, 2, 1]
    }, {
      name: 'Joe',
      data: [3, 4, 4, 2, 5]
    }]
  };
  return options;
}

function get_chart_base_options(question_id, chart_type, display_type) {
  if(chart_type == 'pie') {
    if(display_type == 'show_value')
      return get_pie_chart_base_options_value(question_id);
    else if(display_type == 'show_percent')
      return get_pie_chart_base_options_percent(question_id);
  } else if(chart_type == 'donut') {
    if(display_type == 'show_value')
      return get_donut_chart_base_options_value(question_id);
    else if(display_type == 'show_percent')
      return get_donut_chart_base_options_percent(question_id);
  } else if(chart_type == 'line') {
    if(display_type == 'show_value')
      return get_line_chart_base_options_value(question_id);
    else if(display_type == 'show_percent')
      return get_line_chart_base_options_percent(question_id);
  } else if(chart_type == 'bar') {
    if(display_type == 'show_value')
      return get_bar_chart_base_options_value(question_id);
    else if(display_type == 'show_percent')
      return get_bar_chart_base_options_percent(question_id);
  } else if(chart_type == 'column') {
    if(display_type == 'show_value')
      return get_column_chart_base_options_value(question_id);
    else if(display_type == 'show_percent')
      return get_column_chart_base_options_percent(question_id);
  } else if(chart_type == 'radar') {
    if(display_type == 'show_value')
      return get_radar_chart_base_options_value(question_id);
    else if(display_type == 'show_percent')
      return get_radar_chart_base_options_percent(question_id);
  } else if(chart_type == 'stackcolumn') {
    return get_stackcolumn_chart_base_options(question_id);
  } else if(chart_type == 'stackbar') {
    return get_stackbar_chart_base_options(question_id);
  } else {
    var options = {
      chart: {
        renderTo: 'container_' + question_id,
        type: 'spline'
      },
      series: [{}]
    };
    return options;
  }
}
var chart_obj_dict = {};
function show_chart_and_table(question_id, chart_type, sort_type, display_type, chart_status, table_status) {
  var chart_options = get_chart_base_options(question_id, chart_type, display_type);
  //图表和数据表数据均从此入口
  var chart_info = get_chart_and_table_info(question_id, chart_type, sort_type, display_type, chart_status, table_status);
  if(chart_info == null) {
    return; //由chart_info_callback返回数据后重新调用show_chart
  }
  if(chart_info.rspd_count == 0||chart_info.rspd_count == '0')
  {
    $("#container_"+question_id).html("该题无答题数据");
    $("#container_"+question_id).css("height",'18px');
    return;
  }
  //展示数据表数据
  if(chart_info.table_data != null)
  {
    if(chart_info.question_type!=6&&chart_info.question_type!=8&&chart_info.question_type!=95&&chart_info.question_type!=100)
    {
      col_num = chart_info.table_data[0].length;
      var table_width;
      if(col_num > 7)
        table_width = col_num*125;
      else
        table_width = 840;
      var str_table_html = "<table class='Gls' style='width:"+(table_width-5)+"px' ><tbody class='Gls_list'>";
      var rspd_count;
      for(var r=0;r<chart_info.table_data.length;r++)
      {
        str_table_html += "<tr>";
        var row_item = chart_info.table_data[r];
        //last row
        if(r==chart_info.table_data.length-1)
        {
          var colspan = chart_info.table_data[r-1].length*2;//取上一行来计算跨度
          str_table_html += "<td  align='left' colspan='" + colspan + "'> " + row_item[0] + '：' + row_item[1];
          str_table_html += "</td>";
        }
        else
        {
          for(var c=0;c<row_item.length;c++)
          {
            if(row_item.length == 1)
              rspd_count = row_item[c];
            else
            {
              //统一加眼睛
              if(c==row_item.length-1)
                str_table_html += "<td style='width:80px'>" + row_item[c] + "</td>" ;
              else if(c===0)
                str_table_html += "<td style='width:120px'>" + row_item[c] + "</td>" ;
              else
                str_table_html += "<td style='width:50px'>" + row_item[c] + "</td>" ;
              if(chart_info.cell_info != null && chart_info.cell_info[r][c][0] == 1)
              {
                //有矩阵行
                if(chart_info.cell_info[r][c][2]!=null)
                {
                  str_table_html += "<td class='lbn'><a target='_blank' href='/report/openop/"+chart_info.project_id+"/"+chart_info.question_id+
                "/?v="+chart_info.version+"&op="+chart_info.cell_info[r][c][1]+"&r="+chart_info.cell_info[r][c][2]+"&pid="+chart_info.project_id+"' ><div class='j' title='查看填写内容'></div></a></td>";
                }
                else
                {
                  str_table_html += "<td class='lbn'><a target='_blank' href='/report/openop/"+chart_info.project_id+"/"+chart_info.question_id+
                "/?v="+chart_info.version+"&op="+chart_info.cell_info[r][c][1]+"&pid="+chart_info.project_id+"' ><div class='j' title='查看填写内容'></div></a></td>";
                }
              }
              else
              {
                str_table_html += "<td class='lbn'>&nbsp;</td>";
              }
            }
          }
        }
        str_table_html += "</tr>";
      }
      str_table_html += "</tbody></table>";
      if(rspd_count != null)
      {
        str_table_html += "<br/><div>"+rspd_count+"</div>";
      }
      $("#data_container_"+question_id).html(str_table_html);
    }
    else{//填空题表格数据
      if(chart_info.table_data.rspd_count == 0)
      {
        $("#data_container_"+question_id).html('该题无答题数据');
      }
      else{
        new GapFilling({obj:$("#data_container_"+question_id),url:'/report/ajax/chart_info/?pid='+project_id,rspd_url:'/report/rspd_detail/'+project_id+'/',data:chart_info});
      }
    }
  }
  //展示图表数据
  // if($("#param_chart_height_"+question_id).val())
  //   chart_options.chart.height = $("#param_chart_height_"+question_id).val();
  // if($("#param_chart_width_"+question_id).val())
  //   chart_options.chart.width = $("#param_chart_width_"+question_id).val();
  // chart_options.chart.width = 100%;
  if(chart_info.question_type == 6||chart_info.question_type == 8||chart_info.question_type == 95||chart_info.question_type == 100)
    return;
    //设置选项多的宽度高度
  if(chart_info.option_num>5)
  {
    var chart_height = 30*(chart_info.option_num - 5) + 300 +'px';
    var chart_width = 30*(chart_info.option_num - 5) + 840 + 'px';
    if(chart_type=='column'||chart_type=='line'||chart_type=='stackcolumn')
    {
      if(chart_info.option_num>10)
      {
        $("#container_"+question_id).css('width', chart_width);
        $("#container_"+question_id).css('height', '305px');
        $("#chart_div_"+question_id).css('overflow-x', 'auto');
      }
    }
    else if(chart_type=='bar'||chart_type=='stackbar')
    {
      $("#container_"+question_id).css('height', chart_height);
      $("#container_"+question_id).css('width', '840px');
      $("#chart_div_"+question_id).css('overflow-x','');
    }
    else if(chart_type=='pie'||chart_type=='donut'||chart_type=='radar')
    {
      $("#container_"+question_id).css('height', '305px');
      $("#container_"+question_id).css('width', '840px');
    }
  }
  else
  {
    $("#container_"+question_id).css('height', '305px');
    $("#container_"+question_id).css('width', '840px');
  }

  if(chart_info.matrixrow_num>6)
  {
    var chart_height_mt = 100*(chart_info.matrixrow_num - 5) + 300 +'px';
    var chart_width_mt = 30*(chart_info.matrixrow_num - 5) + 840 + 'px';
    if(chart_type=='bar'||chart_type=='stackbar')
    {
      $("#container_"+question_id).css('height', chart_height_mt);
      $("#container_"+question_id).css('width', '840px');
      $("#chart_div_"+question_id).css('overflow-x','');
    }
    else if(chart_type=='column'||chart_type=='line'||chart_type=='stackcolumn')
    {
      $("#container_"+question_id).css('width', chart_width_mt);
      $("#container_"+question_id).css('height', '305px');
      $("#chart_div_"+question_id).css('overflow-x', 'auto');
    }
    else
    {
      $("#container_"+question_id).css('height', '305px');
      $("#container_"+question_id).css('width', '840px');
    }
  }
  else if(chart_info.matrixrow_num!=0 && chart_info.matrixrow_num<=6)
  {
    $("#container_"+question_id).css('height', '455px');
    $("#container_"+question_id).css('width', '840px');
  }
  if(!chart_options)
    return

  var title = chart_info.title;
  if(title) {
    if(is_dynamic == 1)
      title = chart_info.title.split('答题人数')[0];
    chart_options.title.text = title;
    chart_options.title.margin = chart_info.title_margin;
  }
  var subtitle = chart_info.subtitle;
  if(subtitle) {
    chart_options.subtitle.text = subtitle;
  }
  if(chart_type == 'pie') {
    chart_options.series[0] = {
      'data': chart_info.pie_data,
      'name':''
    };
  } else if(chart_type == 'donut') {
    chart_options.series[0] = {
      'data': chart_info.pie_data,
      'innerSize': '50%'
    };
  } else if(chart_type == 'column') {
    chart_options.xAxis.categories = chart_info.labels;
    if(chart_info.labels.length<=3)
    {
      chart_options.plotOptions.column.pointWidth = 50;//当选项小于3项时，宽度设置为50
    }
    //多系列情况下系列项里面有data这个属性，单系列没有
    if(chart_info.datas[0].data != null)
    {
      if(chart_info.datas.length>3)
        chart_options.plotOptions.column.dataLabels.enabled = false;
      chart_options.series = chart_info.datas;
    }
    else
      chart_options.series[0].data = chart_info.datas;
  } else if(chart_type == 'bar') {
    chart_options.xAxis.categories = chart_info.labels;
    // if(chart_info.labels.length<=3)
    // {
      chart_options.plotOptions.bar.pointWidth = 15;//当选项小于3项时，宽度设置为50
    // }
    if(chart_info.datas[0].data != null)
    {
      if(chart_info.datas.length>3)
        chart_options.plotOptions.bar.dataLabels.enabled = false;
      chart_options.series = chart_info.datas;
    }
    else
    {
      chart_options.series[0] = {
      'data': chart_info.datas,
      'name': '选中人数'
    };
    }
  } else if(chart_type == 'line') {
    if(chart_info.question_type == 50 || chart_info.question_type == 7)
    {
      if(chart_info.display_type == 'show_value')
        chart_options.yAxis.title.text = '均值';
    }
    chart_options.xAxis.categories = chart_info.labels;
    if(chart_info.suffix != null)
      chart_options.tooltip.valueSuffix = chart_info.suffix;
    if(chart_info.datas[0].data != null)
      chart_options.series = chart_info.datas;
    else
    {
      chart_options.series[0] = {
      'data': chart_info.datas,
      'name': '选中人数'
    };
    }
  } else if(chart_type == 'radar') {
    chart_options.xAxis.categories = chart_info.labels;
    chart_options.series = chart_info.datas;
    /*chart_options.series[0] = {
      'data': chart_info.datas,
      'name': '平均值',
      'pointPlacement': 'on'
    };*/
  } else if(chart_type == 'stackcolumn' || chart_type == 'stackbar') {
    chart_options.xAxis.categories = chart_info.labels;
    chart_options.series = chart_info.datas;
  }

  var chart = new Highcharts.Chart(chart_options);
  //chart.exportChart();
  //渲染完之后默认设置导出图标隐藏
  $("#container_"+question_id).find(".highcharts-button").hide();
}

function show_chart_and_table2(question_id_arr, chart_type_arr, sort_type_arr, display_type_arr, chart_status_arr, table_status_arr,chart_info_list){
  var chart_arr = [];
  //图表和数据表数据均从此入口
  for(var i=0;i<question_id_arr.length;i++){
     var question_id = question_id_arr[i];
     var chart_type = chart_type_arr[i];
     var sort_type = sort_type_arr[i];
     var display_type = display_type_arr[i];
     var chart_status = chart_status_arr[i];
     var table_status = table_status_arr[i];
     var chart_info = chart_info_list[i];
    var chart_options = get_chart_base_options(question_id, chart_type, display_type);
 if(chart_info == null) {
    continue; //由chart_info_callback返回数据后重新调用show_chart
  }
  if(chart_info.rspd_count == 0||chart_info.rspd_count == '0')
  {
    $("#container_"+question_id).html("该题无答题数据");
    $("#container_"+question_id).css("height",'18px');
    continue;
  }
  //展示数据表数据
  if(chart_info.table_data != null)
  {
    if(chart_info.question_type!=6&&chart_info.question_type!=8&&chart_info.question_type!=95&&chart_info.question_type!=100)
    {
      col_num = chart_info.table_data[0].length;
      if(col_num > 6)
        table_width = col_num*110;
      else
        table_width = 840;
      var str_table_html = "<table class='Gls' style='width:"+table_width+"px' ><tbody class='Gls_list'>";
      var rspd_count;
      for(var r=0;r<chart_info.table_data.length;r++)
      {
        str_table_html += "<tr>";
        var row_item = chart_info.table_data[r];
        //last row
        if(r==chart_info.table_data.length-1)
        {
          var colspan = chart_info.table_data[r-1].length*2;//取上一行来计算跨度
          str_table_html += "<td  align='left' colspan='" + colspan + "'> " + row_item[0] + '：' + row_item[1];
          str_table_html += "</td>";
        }
        else
        {
          for(var c=0;c<row_item.length;c++)
          {
            if(row_item.length == 1)
              rspd_count = row_item[c];
            else
            {
              //统一加眼睛
              str_table_html += "<td style='width:100px'>" + row_item[c] + "</td>" ;
              if(chart_info.cell_info != null && chart_info.cell_info[r][c][0] == 1)
              {
                //有矩阵行
                if(chart_info.cell_info[r][c][2]!=null)
                {
                  str_table_html += "<td class='lbn'><a target='_blank' href='/report/openop/"+chart_info.project_id+"/"+chart_info.question_id+
                "/?v="+chart_info.version+"&op="+chart_info.cell_info[r][c][1]+"&r="+chart_info.cell_info[r][c][2]+"&pid="+chart_info.project_id+"' ><div class='j' title='查看填写内容'></div></a></td>";
                }
                else
                {
                  str_table_html += "<td class='lbn'><a target='_blank' href='/report/openop/"+chart_info.project_id+"/"+chart_info.question_id+
                "/?v="+chart_info.version+"&op="+chart_info.cell_info[r][c][1]+"&pid="+chart_info.project_id+"' ><div class='j' title='查看填写内容'></div></a></td>";
                }
              }
              else
              {
                str_table_html += "<td class='lbn'>&nbsp;</td>";
              }
            }
          }
        }
        str_table_html += "</tr>";
      }
      str_table_html += "</tbody></table>";
      if(rspd_count != null)
      {
        str_table_html += "<br/><div>"+rspd_count+"</div>";
      }
      $("#data_container_"+question_id).html(str_table_html);
    }
    else{//填空题表格数据
      if(chart_info.table_data.rspd_count == 0)
      {
        $("#data_container_"+question_id).html('该题无答题数据');
      }
      else{
        new GapFilling({obj:$("#data_container_"+question_id),url:'/report/ajax/chart_info/?pid='+project_id,rspd_url:'/report/rspd_detail/'+project_id+'/',data:chart_info});
      }
    }
  }

  if(chart_info.question_type == 6||chart_info.question_type == 8||chart_info.question_type == 95||chart_info.question_type == 100)
    continue;
    //设置选项多的宽度高度
  if(chart_info.option_num>5)
  {
    var chart_height = 30*(chart_info.option_num - 5) + 300 +'px';
    var chart_width = 30*(chart_info.option_num - 5) + 840 + 'px';
    if(chart_type=='column'||chart_type=='line'||chart_type=='stackcolumn')
    {
      if(chart_info.option_num>10)
      {
        $("#container_"+question_id).css('width', chart_width);
        $("#container_"+question_id).css('height', '305px');
        $("#chart_div_"+question_id).css('overflow-x', 'auto');
      }
    }
    else if(chart_type=='bar'||chart_type=='stackbar')
    {
      $("#container_"+question_id).css('height', chart_height);
      $("#container_"+question_id).css('width', '840px');
      $("#chart_div_"+question_id).css('overflow-x','');
    }
    else if(chart_type=='pie'||chart_type=='donut'||chart_type=='radar')
    {
      $("#container_"+question_id).css('height', '305px');
      $("#container_"+question_id).css('width', '840px');
    }
  }
  else
  {
    $("#container_"+question_id).css('height', '305px');
    $("#container_"+question_id).css('width', '840px');
  }

  if(chart_info.matrixrow_num>6)
  {
    var chart_height_mt = 30*(chart_info.matrixrow_num - 5) + 300 +'px';
    var chart_width_mt = 30*(chart_info.matrixrow_num - 5) + 840 + 'px';
    if(chart_type=='bar'||chart_type=='stackbar')
    {
      $("#container_"+question_id).css('height', chart_height_mt);
      $("#container_"+question_id).css('width', '840px');
      $("#chart_div_"+question_id).css('overflow-x','');
    }
    else if(chart_type=='column'||chart_type=='line'||chart_type=='stackcolumn')
    {
      $("#container_"+question_id).css('width', chart_width_mt);
      $("#container_"+question_id).css('height', '305px');
      $("#chart_div_"+question_id).css('overflow-x', 'auto');
    }
    else
    {
      $("#container_"+question_id).css('height', '305px');
      $("#container_"+question_id).css('width', '840px');
    }
  }
  else if(chart_info.matrixrow_num!=0 && chart_info.matrixrow_num<=6)
  {
    $("#container_"+question_id).css('height', '305px');
    $("#container_"+question_id).css('width', '840px');
  }
  var title = chart_info.title;
  if(title) {
    chart_options.title.text = title;
    chart_options.title.margin = chart_info.title_margin;
  }
  var subtitle = chart_info.subtitle;
  if(subtitle) {
    chart_options.subtitle.text = subtitle;
  }
  if(chart_type == 'pie') {
    chart_options.series[0] = {
      'data': chart_info.pie_data,
      'name':''
    };
  } else if(chart_type == 'donut') {
    chart_options.series[0] = {
      'data': chart_info.pie_data,
      'innerSize': '50%'
    };
  } else if(chart_type == 'column') {
    chart_options.xAxis.categories = chart_info.labels;
    if(chart_info.labels.length<=3)
    {
      chart_options.plotOptions.column.pointWidth = 50;//当选项小于3项时，宽度设置为50
    }
    //多系列情况下系列项里面有data这个属性，单系列没有
    if(chart_info.datas[0].data != null)
    {
      if(chart_info.datas.length>3)
        chart_options.plotOptions.column.dataLabels.enabled = false;
      chart_options.series = chart_info.datas;
    }
    else
      chart_options.series[0].data = chart_info.datas;
  } else if(chart_type == 'bar') {
    chart_options.xAxis.categories = chart_info.labels;

    if(chart_info.datas[0].data != null)
    {
      if(chart_info.datas.length>3)
        chart_options.plotOptions.bar.dataLabels.enabled = false;
      chart_options.series = chart_info.datas;
    }
    else
    {
      chart_options.series[0] = {
      'data': chart_info.datas,
      'name': '选中人数'
    };
    }
  } else if(chart_type == 'line') {
    if(chart_info.question_type == 50 || chart_info.question_type == 7)
    {
      if(chart_info.display_type == 'show_value')
        chart_options.yAxis.title.text = '均值';
    }
    chart_options.xAxis.categories = chart_info.labels;
    if(chart_info.suffix != null)
      chart_options.tooltip.valueSuffix = chart_info.suffix;
    if(chart_info.datas[0].data != null)
      chart_options.series = chart_info.datas;
    else
    {
      chart_options.series[0] = {
      'data': chart_info.datas,
      'name': '选中人数'
    };
    }
  } else if(chart_type == 'radar') {
    chart_options.xAxis.categories = chart_info.labels;
    chart_options.series = chart_info.datas;
  } else if(chart_type == 'stackcolumn' || chart_type == 'stackbar') {
    chart_options.xAxis.categories = chart_info.labels;
    chart_options.series = chart_info.datas;
  }

  var chart = new Highcharts.Chart(chart_options);
  //chart.exportChart();
    chart_arr[i] = chart;
     //渲染完之后默认设置导出图标隐藏
    $("#container_"+question_id).find(".highcharts-button").hide();
     }
    total_q_num = 0;

    var svg_arr = [];
    $('.svg-container').each(function(index) {
        if ($(this).text().trim() == "该题无答题数据"){
           svg_arr[index] = 'nothing';
        }else {
            var chart = $(this).highcharts();
            var svg = chart.getSVG()
            var svg_code = decorate_svg(svg);
            svg_arr[index] = svg_code;
        }
        //$("#svgContainer").append("<input name='svg' class='svg_class' type='hidden' value='"+svg_code+"' />");
    });
    proj_id = $("#proj_id").val();
    $.ajax({
	   type:"POST",
	   url:"/report/docx/?pid="+proj_id,
	   data:{proj_id:proj_id,"svg_arr":svg_arr, version:view_version,"_xsrf":getCookie('_xsrf')},
	   dataType:'json',
	   success: function(){
            location.href = "/report/docx?proj_id="+proj_id+"&version="+view_version;
            //makObj.click();
            loadMack({off:'off'});
	    }
	}); 
    doc_file_exist = true;
}


function get_chart_and_table_info(question_id, chart_type, sort_type, display_type, chart_status, table_status) {
  var chart_info = chart_info_dict[[question_id, chart_type, sort_type, display_type, chart_status, table_status]];
  if(chart_info == null) {
    $("#form_chart_info").find('input[name="question_id"]').val(question_id);
    $("#form_chart_info").find('input[name="chart_type"]').val(chart_type);
    $("#form_chart_info").find('input[name="sort_type"]').val(sort_type);
    $("#form_chart_info").find('input[name="display_type"]').val(display_type);
    $("#form_chart_info").find('input[name="chart_status"]').val(chart_status);
    $("#form_chart_info").find('input[name="table_status"]').val(table_status);
    $("#form_chart_info").find('input[name="version"]').val(view_version);
    ajaxSubmit($("#form_chart_info"));
    return null;
  } else {
    return chart_info;
  }
}

function get_chart_and_table_info_list(question_id_arr, chart_type_arr, sort_type_arr, display_type_arr, chart_status_arr, table_status_arr) {
    proj_id = $("#proj_id").val();
    $.ajax({
	   type:"POST",
	   url:"/report/ajax/chart_info_list/?pid="+proj_id,
	   data:{project_id:proj_id,"question_id_arr":question_id_arr,chart_type_arr:chart_type_arr,
           sort_type_arr:sort_type_arr,display_type_arr:display_type_arr, 
           chart_status_arr:chart_status_arr, table_status_arr:table_status_arr,version:view_version,"_xsrf":getCookie('_xsrf')},
	   dataType:'json',
	   success: function(info_list){
        show_chart_and_table2(question_id_arr, chart_type_arr, sort_type_arr, display_type_arr, chart_status_arr, table_status_arr, info_list)
	    }
	}); 
}


function chart_info_callback(info) {
  var question_id = info.question_id;
  var chart_type = info.chart_type;
  var sort_type = info.sort_type;
  var display_type = info.display_type;
  var chart_status = $("#param_chart_show_or_hide_"+question_id).val();
  var table_status = $("#param_table_show_or_hide_"+question_id).val();
  chart_info_dict[[question_id, chart_type, sort_type, display_type, chart_status, table_status]] = info;
  if(info) {
    show_chart_and_table(question_id, chart_type, sort_type, display_type, chart_status, table_status);
  }
}

function chart_type_changed(obj) {
  var question_id = obj.id.replace('chart_type_', '');
  var chart_type = $(obj).attr('typename');
  $("#param_chart_type_"+question_id).val(chart_type);
  var sort_type = $("#param_sort_type_"+question_id).val();
  var display_type = $("#param_show_type_"+question_id).val();
  //矩阵单选，多选默认百分比
  if($("#param_question_type_"+question_id).val() == '5'||$("#param_question_type_"+question_id).val() == '4')
    display_type = "show_percent";
  var chart_status = $("#param_chart_show_or_hide_"+question_id).val();
  var table_status = $("#param_table_show_or_hide_"+question_id).val();
  show_chart_and_table(question_id, chart_type, sort_type, display_type, chart_status, table_status);
}

function sort_type_changed(obj){
  var question_id = obj.id.replace('sort_type_','');
  var sort_type = $(obj).attr('sortname');
  $("#param_sort_type_"+question_id).val(sort_type);
  var chart_type = $("#param_chart_type_"+question_id).val();
  var display_type = $("#param_show_type_"+question_id).val();
  if(display_type == null || display_type == '')
    display_type = 'show_value';
  var chart_status = $("#param_chart_show_or_hide_"+question_id).val();
  var table_status = $("#param_table_show_or_hide_"+question_id).val();
  show_chart_and_table(question_id, chart_type, sort_type, display_type, chart_status, table_status);
}

function display_type_change(obj){
  var question_id = obj.id.replace('display_type_','');
  var display_type = $(obj).attr('dispname');
  if(!display_type)
    display_type = 'show_value';
  //只有显示值或百分比才需要记录值
  if(display_type == 'show_value'||display_type == 'show_percent')
    $("#param_show_type_"+question_id).val(display_type);
  var chart_type = $("#param_chart_type_"+question_id).val();
  var sort_type = $("#param_sort_type_"+question_id).val();
  //记录图表与数据表显示或隐藏值
  if(display_type == 'show_chart'||display_type == 'show_data'||display_type == 'hide_chart'||display_type == 'hide_data')
  {
    if(display_type == 'show_chart'||display_type == 'hide_chart')
      $("#param_chart_show_or_hide_"+question_id).val(display_type);
    else if(display_type == 'show_data'||display_type == 'hide_data')
      $("#param_table_show_or_hide_"+question_id).val(display_type);
  }
  //数据请求,切换数据类型需要发送请求加载图表数据[显示隐藏图表和数据只是前端DIV控制]
  //目前为了记录图表显示设置，开放该条件[2014-02-25]
  // if(display_type == 'show_value'||display_type == 'show_percent')
  // {
  var chart_status = $("#param_chart_show_or_hide_"+question_id).val();
  var table_status = $("#param_table_show_or_hide_"+question_id).val();
  var display_type_str = $("#param_show_type_"+question_id).val();
  show_chart_and_table(question_id,chart_type,sort_type,display_type_str,chart_status,table_status);
  // }
  elememts_deal(question_id, display_type, obj);
}

function elememts_deal(question_id, display_type, obj){
  //标签处理
  if(display_type == 'hide_chart')
  {  
    $("#container_"+question_id).css('display','none');
    // $("#param_chart_height_"+question_id).val($("#container_"+question_id).attr("height"));
    // $("#param_chart_width_"+question_id).val($("#container_"+question_id).attr("width"));
    $(obj).attr('dispname','show_chart');
    $(obj).text('显示图表');
  }
  else if(display_type == 'show_chart')
  {
    $("#container_"+question_id).css('display','');
    $(obj).attr('dispname','hide_chart');
    $(obj).text('隐藏图表');
  }
  else if(display_type == 'show_data')
  { 
    $("#data_container_"+question_id).css('display','');
    $(obj).attr('dispname','hide_data');
    $(obj).text('隐藏数据');
  }
  else if(display_type == 'hide_data')
  {
    $("#data_container_"+question_id).css('display','none');
    $(obj).attr('dispname','show_data');
    $(obj).text('显示数据');
  }
  else if(display_type == 'show_value')
  {
    $(obj).attr('dispname','show_percent');
    $(obj).text('显示百分比');
  }
  else if(display_type == 'show_percent')
  { 
    //打分题
    if($("#record_chart_type_"+question_id).val() == "50")
    {
      $(obj).attr('dispname','show_value');
      $(obj).text('显示均值');
    }
    else
    {
      $(obj).attr('dispname','show_value');
      $(obj).text('显示数值');
    }
  }
}

function selectQuestionChangeHandler(obj){
  if($(obj).get(0).selectedIndex == 0 )
  {
    for(var i=0;i<question_list.length;i++)
    {
      $("#question_content_"+question_list[i].qid).css("display","");
    }
  }
  else
  {
    for(var j=0;j<question_list.length;j++)
    {
      $("#question_content_"+question_list[j].qid).css("display","none");
    }
    var q_index = $(obj).get(0).selectedIndex - 1;
    $("#question_content_"+question_list[q_index].qid).css("display","");
    var chart_type = $("#param_chart_type_"+question_list[q_index].qid).val();
    if(chart_type==""||chart_type==undefined||chart_type==null)
      chart_type = $("#chart_type_"+question_list[q_index].qid).attr("typename");
    var sort_type = $("#param_sort_type_"+question_list[q_index].qid).val();
    if(sort_type==""||sort_type==undefined||sort_type==null)
      sort_type = "Keep";
    var show_type = $("#param_show_type_"+question_list[q_index].qid).val();
    if(show_type==""||show_type==undefined||show_type==null)
      show_type = "show_value";
    show_chart_and_table(question_list[q_index].qid,chart_type,sort_type,show_type,"show_chart","show_table");
  }
}

function chart_con_mouse_over(obj){
  $(obj).find('.highcharts-button').show();
  $(obj).find('#operation_btn').show();
}

function chart_con_mouse_out(obj){
  $(obj).find('.highcharts-button').hide();
  $(obj).find('#operation_btn').hide();
}



//生成历史数据时间轴
function DateData(data){
  
  var Dl = data.length;
  var timeArr=[];
  var nowtime = new Date();
  var MaxMargin =600;
  var Con ="";
  var Focus="";
  var mz=40;
  var FocusM=[];
  
  //if(Dl>8){$('.DateDataN').width(Dl*120+60);}
  if(Dl==0)return false;
  
  var marginL= new spacingFn();
  for(var i=0;i<Dl;i++){
	  if(Dl>11){
		   var pjz = 790/(Dl-1);
		   mz = i*pjz;
	  }else{
		   var pjz = 796/(Dl-1);
		   mz = i*pjz;
	  }
	  
	  if(data[i].focus==1){
		  Focus="Pf";
		  FocusM.push(i);
		  for(var mi=i+1;mi<Dl;mi++){
			 if(data[mi].is_merge==0){
				FocusM.push(mi);
				break;	 
			 }; 
		  }

	  }else{Focus=""};
	  
	  
	  var rspd_count = "第 "+(Dl-i-1)+" 次修改";
	  var time_count = wbtime(data[i].time)+" "+data[i].rspd_count+ "份";
	  
	  if(i==0){
		  var rspd_counts = "最新数据 "+ data[i].rspd_count +'份';
		  Con +='<div class="dateP Now_p"><a href="'+data[i].url+'" name="'+rspd_counts+'" class="point '+Focus+'"></a><p>现在</p></div>';
	  }else if(i==Dl-1){
		  var rspd_count = "首次发布";
		  
		  if(Dl<11){
			 Con+='<div class="dateP history_p Dstart"><a href="javascript:;" name="'+wbtime(data[i].time)+'" class="point '+Focus+'"></a><p>'+rspd_count+'</p></div>';
		  }else{
			 Con+='<div class="dateP history_p Dstart" style="width:120px;"><a href="javascript:;" name="'+wbtime(data[i].time)+'" class="point '+Focus+'"></a><p style="textAlign:right;">'+rspd_count+'</p></div>';
		  }
		  
	  }else{
		  
		  if(Dl<11){
			 Con+='<div class="dateP history_p" style=" left:'+mz+'px;"><a href="'+data[i].url+'" name="'+time_count+'" class="point '+Focus+'"></a><p>'+rspd_count+'</p></div>';
		  }else{
			 
			 var dn = 'display:none;';
			 Focus==""?dn:dn="width:120px;position:absolute;zIndex:999;"; 
			 
			 Con+='<div class="dateP history_p" style="left:'+mz+'px;"><a href="'+data[i].url+'" name="'+time_count+'" class="point '+Focus+'"></a><p class="Tdp" style="'+dn+'">'+rspd_count+'</p></div>'; 
		  }
		  
		  
	  }
	  
  }
  $('.DateDataN').append(Con);
  
  var BarL = $('.DateDataN .dateP:eq('+FocusM[0]+') .point').offset().left-$('.DateDataN').offset().left;
  var BarW = $('.DateDataN .dateP:eq('+FocusM[1]+') .point').offset().left-$('.DateDataN').offset().left-BarL;
  $('.topBar').css({left:BarL+'px',width:BarW+'px'});  
  
  function spacingFn(){
	this.outM=0;
	this.Fn=function(m){
		if(m==1){return}
		this.outM = parseInt(this.outM/2);
		m--;
		this.Fn(m);	
	}
	this.onFn=function(Max,m){
		this.outM=Max;
		this.Fn(m);
		return this.outM;
	}
}



var tjj = new Timetjj();
tjj.event('.point');

var tl = $('.DateDataN .Pf').offset().left-3;
var tt = $('.DateDataN .Pf').offset().top-36;

tjj.addimg(tt,tl,$('.DateDataN .Pf').attr('name'),$('.DateDataN .Pf').width());
		  
}
	  
//时间处理
function wbtime(t){
	//时间格式2011-12-22 15:10:54 处理
	var ntime = new Date(t.replace(/\-/g,"/"));
	var nowtime = new Date();
	var leftsecond=parseInt((nowtime.getTime()-ntime.getTime())/1000);
	//var y= ntime.getYear();
	//y+= (y < 2000) ? 1900 : 0; 
	var d=parseInt(leftsecond/3600/24);
	var h=parseInt((leftsecond/3600)%24);
	var m=parseInt((leftsecond/60)%60);
	var s=parseInt(leftsecond%60);
	
	var toTime = new ToTime(t);
	var todayTime = new ToTime(nowtime.format("yyyy-MM-dd hh:mm:ss"));
	var cd = todayTime.d-toTime.d;
	
//	if(d>365){
		return "<span style='color:#999'>"+toTime.y+"."+toTime.m+"."+toTime.d +"  "+toTime.h+":"+toTime.mm+"</span>";
//	}else if(d>2){
//		return toTime.m+1+"."+toTime.d+" <span>"+toTime.h+":"+toTime.mm+"</span>";
//	}else if(d>0){
//		
//		if(cd==1){
//		    return "昨天 <span>"+toTime.h+":"+toTime.mm+"</span>";
//		}else{
//			return "前天 <span>"+toTime.h+":"+toTime.mm+"</span>";
//		}	
//		
//	}else if(h>3){
//		
//		if(cd==0){
//		    return "今天 <span>"+toTime.h+":"+toTime.mm+"</span>";
//		}else{
//			return "昨天 <span>"+toTime.h+":"+toTime.mm+"</span>";
//		}	
//		
//		return h+"小时前";
//		
//	}else if(h>0&&h<=3){
//		return h+"小时前";
//	}else if(m>0){
//		return m+"分钟前";
//	}else{
//		return s+"秒前";
//	}
}
//时间输出
function ToTime(timeString) {
	this.y = timeString.substring(0,4);
	this.m = timeString.substring(5,7);
	this.d = timeString.substring(8,10);
	this.h = timeString.substring(11,13);
	this.mm = timeString.substring(14,16);
	this.ss = timeString.substring(17,19);
}

//时间轴提示信息
function Timetjj(ldDiv){
	
	this.event=function(ldDiv){		 

	    var _this= this;
		$(ldDiv).live("mouseover",function(){
			
			 $(".tccTx,.Tdp").hide();
			 $('.DateDataN .Pf p').hide();
			 
			 xsimg = false;
			 var tis = $(this);
			 var id = tis.attr("id");
			 var top= tis.offset().top-36;
			 var left= tis.offset().left-3;
			 var thiss =$(this);
			 //(top<=0)?top=top-90:top;
			 //(left>=620)?left=620:left;
			 //(left<=0)?left=0:left;
			
			var uid = $(this).attr("id");
			var texts = $(this).attr("name");
			
			_this.addimg(top,left,texts,$(this).width());
			
			var Td = $(this).parent().find('p');
			if(Td.is(':hidden')){
			   $(this).parent().find('p').addClass('Tdp').show();
			   $(this).parent().find('.Tdp').css({width:'120px',position:'absolute',zIndex:'999'});
			}
			
		});
		
//		$(ldDiv).live("mouseout",function(){
//			xsimg = true;
//			var ks = setTimeout("Timegb()",800);
//		});
//		
//		$(ldDiv).live("mouseover",function(){
//			xsimg = false;
//		});
		
		$('.DateDataCon').mouseleave(function(){
			
			setTimeout(function(){
				$('.Tdp:not(:last)').hide();
				var bgtjj = new Timetjj();
				var tl = $('.DateDataN .Pf').offset().left-3;
				var tt = $('.DateDataN .Pf').offset().top-36;
				bgtjj.addimg(tt,tl,$('.DateDataN .Pf').attr('name'),$('.DateDataN .Pf').width());
				var Td = $('.DateDataN .Pf').parent().find('p');
				if(Td.is(':hidden')){
				   Td.show().css({width:'120px',position:'absolute',zIndex:'999'});
				}
			},400);
			return false;
			
		});
			
		
	}
	
	this.addimg=function(top,left,texts,this_w,max_w){
		     
			if(!max_w){max_w=100};
			var imgcom = $('.Tjj');
			//alert(imgcom.length)
			var clz=this.removeHTMLTag(texts);
			var tw = this.getByteLen(clz)*16;
			//(tw>max_w)?tw=max_w:tw=50;
			
			var tw2 = tw/2;
			(tw2<19)?tw2=19:tw2;
			
			if(imgcom.length==0){
				var tcon =$('<div class="Tjj" style="width:'+tw+'px;">'+
								 '<div class="tjjcon"></div>'+
								 '<div class="tj"></div>'+
							'</div>');			
				 tcon.find('.tjjcon').html(texts);
				 $("body").append(tcon);
				
				 var obj_h = tcon.height()-15;

				 $('.Tjj').css({"top":top-obj_h+"px","left":(left-tw2+this_w/2-5)+"px"});

				 $('.tj').css({"left":tw2-2+"px"});
				 
			}else{	
				 imgcom.find(".tjjcon").html(texts);
				 var imgcom = $('.Tjj');
				 imgcom.css({"width":tw+"px","left":(left-tw2+this_w/2-5)+"px"});
				 
				 var obj_h = imgcom.height()-15;
				 imgcom.css({"top":top-obj_h+"px"});
				 imgcom.find('.tj').css({"left":tw2-2+"px"});
				 imgcom.show();
			}
    }
	
	this.getByteLen=function(val) {
			if(!val){val=0}
			var len = 0;
			for (var i = 0; i < val.length; i++) {
				if (val.charAt(i).match(/[^\x00-\xff]/ig) != null) //全角 
					len += 2; //如果是全角，占用两个字节
				else
					len += 1; //半角占用一个字节
			}
			return len/2;
	}
	this.removeHTMLTag=function(str){
            str = str.replace(/<[^>].*?>/g,''); //去除HTML tag
            str = str.replace(/[ | ]*n/g,'n'); //去除行尾空白
            //str = str.replace(/n[s| | ]*r/g,'n'); //去除多余空行
           // str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
            return str;
    }
	//return this.event(ldDiv);
					 
}
Date.prototype.format = function(format){
var o = {
"M+" : this.getMonth()+1, //month
"d+" : this.getDate(), //day
"h+" : this.getHours(), //hour
"m+" : this.getMinutes(), //minute
"s+" : this.getSeconds(), //second
"q+" : Math.floor((this.getMonth()+3)/3), //quarter
"S" : this.getMilliseconds() //millisecond
}

if(/(y+)/.test(format)) {
format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
}

for(var k in o) {
if(new RegExp("("+ k +")").test(format)) {
format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
}
}
return format;
} 

//填空题数据生成
function GapFilling(Obj){
          this.Tinfo = {
			  obj:Obj.obj,
			  rspd_url:Obj.rspd_url,
			  url:Obj.url
		  }
		  this.data=Obj.data;
		  this.ajax=function(ajaxurl,page,question_id,project_id,version){
			var _this = this;
			$.ajax({
			   url:ajaxurl,
			   type:'POST',
			   dataType:'json',
			   data:'page='+page+'&question_id='+question_id+'&_xsrf='+getCookie('_xsrf')+'&project_id='+project_id+'&version='+version,
			   error:function(){
				  alert("加载错误");   
			   },
			   success:function(statuses){
				 _this.data=statuses;
				 _this.CreateHtml();
			   }
			});	
		  }
	      this.CreateHtml=function(){
			  this.Con = $('<div qid="'+this.data.question_id+'" class="GapFilling"><div class="Gcon">'+this.CreateList()+'</div><div class="Gbot"><div class="G_paging" name="'+this.Tinfo.url+'">'+this.CreatePaging()+'</div><span>受访人数：'+this.data.table_data.rspd_count+'</span></div></div>');
			  this.Tinfo.obj.html('').append(this.Con);
			  
			  var this_=this;
			  //上一页
			  if(this.prevpage!==''){
				  this.Tinfo.obj.find('.G_paging').append(this.prevpage);
				  this.prevpage.bind('click',function(){
					  var question_id =$(this).parents('.GapFilling').attr('qid');
					  var page = $(this).attr('page');
					  this_.ajax(this_.Tinfo.url,page,question_id,this_.data.project_id,this_.data.version);
				  });
			  }
			  //下一页
			  if(this.nextpage!==''){
				  this.Tinfo.obj.find('.G_paging').append(this.nextpage);
				  this.nextpage.bind('click',function(){
					  var question_id =$(this).parents('.GapFilling').attr('qid');
					  var page = $(this).attr('page');
					  this_.ajax(this_.Tinfo.url,page,question_id,this_.data.project_id,this_.data.version);
	
				  });
			  }
			  this.Tinfo.obj.find('.G_paging').append(this.getpage);
			  
			  //跳转事件
			  this.Tinfo.obj.find('.G_paging .get_a').bind('click',function(){
                      get_fn($(this));
			  });
			  //回车事件
			  this.Tinfo.obj.find('.G_paging .get_page').bind('focus',function(){
				      $(this).keyup(function(e){
						  if(e.keyCode === 13){
							 get_fn($(this));
						  }
					  });
					 
			  }).blur(function(){
					 $(this).unbind('keyup');
		      });	
			  
			  function get_fn(obj){
				      var question_id =obj.parents('.GapFilling').attr('qid');
					  var page = obj.parent().find('.get_page').val();
					  if(page==''){return}
					  this_.ajax(this_.Tinfo.url,page,question_id,this_.data.project_id,this_.data.version);
			  }
			  
			  //防止非数字输入
			  this.Tinfo.obj.find('.get_page').keyup(function(){
				  $(this).val($(this).val().replace(/\D/g,''));   
			  });
			 // this.Width_mate(this.Tinfo.obj);
			 // return this.Con;
		  }
		  //创建列表
		  this.CreateList=function(){

			  var columns = this.data.table_data.columns;
				var mun = columns[0].value.length;
				var mat = this.data.table_data.matrixrow;
				if(mat!==undefined){var matClass ='mat'}else{var matClass =''}
				
				if(columns.length>4){
				   this.Tinfo.obj.parents('.chartdiv').css({'width':'903px','overflowX':'auto','overflowY':'hidden'});	
				   this.Tinfo.obj.parents('.chartdiv').find('.chart_table').width((columns.length*200)+"px");	
				  // this.Tinfo.obj.parents('.chartdiv').find('td').width(300+'px');
				}
				
				var list_tr='';
				for(var i=0;i<mun;i++){
                     var list_td='';
					 var title ='';
					 for(var ii=0;ii<columns.length;ii++){
						 
						 if(mat!==undefined&&ii==0){
					 
						   title+='<td>&nbsp;</td>';
						   list_td+='<td class="matTd">'+mat[i]+'</td>';

				     }
						 title+='<td>'+columns[ii].label+'</td>';
 
             list_td+='<td>'+columns[ii].value[i].text+'</td>';

						 if(ii==columns.length-1){
							 if(mat!==undefined){
							    title+='<td class="lbn">&nbsp;</td>';
  								if(i==0){
                      if(this.data.is_history==0){
  						          list_td+='<td class="lbn"><a target="_blank" href="'+this.Tinfo.rspd_url+columns[ii].value[i].seq+'/?v='+data_version+'&pid='+project_id+'"><div class="i" title="查看答卷详情"></div></a></td>'; 
                      }else{
                        list_td+='<td class="lbn">&nbsp;</td>'; 
                      }
  								}else{
  									  list_td+='<td class="lbn">&nbsp;</td>'; 
  								}
							 }else{
								    title+='<td class="lbn">&nbsp;</td>';
                    if(this.data.is_history==0){
						          list_td+='<td class="lbn"><a target="_blank" href="'+this.Tinfo.rspd_url+columns[ii].value[i].seq+'/?v='+data_version+'&pid='+project_id+'"><div class="i" title="查看答卷详情"></div></a></td>'; 
                    }else{
                      list_td+='<td class="lbn">&nbsp;</td>'; 
                    }
                    if(this.data.disp_type=="upload_file"){
                        title+='<td class="lbn"><div class="plDow" id="plDow'+this.data.question_id+'" onclick="get_down_link(\''+this.data.project_id+'\',\''+this.data.question_id+'\',\'filter\')">批量下载</div></td>';
                        list_td+='<td class="lbn"><a href="javascript:get_down_link(\''+this.data.project_id+'\',\''+this.data.question_id+'\',\''+columns[ii].value[i].seq+'\');"><div class="i d" title="下载"></div></a></td>'; 
                    }
							 }
					     }
						
				     }   
				     list_tr+='<tr>'+list_td+'</tr>';
				}
				
			    this.table='<table class="Gl" cellpadding="0" cellspacing="0">'+
								   '<thead>'+
									   '<tr class="Gl_title">'+
										   title+
									   '</tr>'+
								   '</thead>'+
								   '<tbody class="Gl_list '+matClass+'">'+
										   list_tr+
								   '</tbody>'+
							  '</table>';
			   return this.table;				  
		  } 
		  this.CreatePaging=function(){
			  this.cur_page = this.data.table_data.cur_page;
			  this.total_page = this.data.table_data.total_page;
			  
			  this.prevpage=$('<a class="prevpage" page="'+(this.cur_page*1-1)+'" href="javascript:;">上一页</a>');
		      this.nextpage=$('<a class="nextpage" page="'+(this.cur_page*1+1)+'" href="javascript:;">下一页</a>');
			  this.getpage=$('<input class="get_page" type="text" /><a class="get_a" href="javascript:;">跳转</a>');
			  
			  if(this.cur_page==1){this.prevpage=''}
			  if(this.cur_page==this.total_page){this.nextpage=''}
			  var con = this.cur_page+' / '+this.total_page;
			  return con;
		  }
		  //宽度处理
		  this.Width_mate=function(obj) {
			var objTable = $('table tr:eq(0) td', obj);
			var l = objTable.length;
			var mean_width = 80 / (l - 1);
			objTable.each(function(index, element) {
				if(index == 0) {
					$(this).attr('width', 20 + '%');
				}else {
					$(this).attr('width', mean_width + '%');
				}
			});
		  }
		  this.output=function(){
			  this.CreateHtml();
		  }
		  return this.output();
		  
     }

     function get_down_link(pid, qid, seq){
        $("#plDow"+qid).html("处理中...");
        $.ajax({
         type:"POST",
         url:"/report/ajax/get_download_link/?pid="+pid,
         data:{"pid":pid,"qid":qid, "seq":seq,"_xsrf":getCookie('_xsrf')},
         dataType:'json',
         success: function(info){
                $("#plDow"+qid).html("批量下载");
                if(info.result == 1)
                {
                  if(info.seq == "filter")
                  {
                    //复制所有,拆分info.links
                    var wb = new jsbox({
                        onlyid: qid,
                        title: '批量下载',
                        conw: 580,
                        conh: 390,
                        FixedTop:80,
                        Fun:showDates,
                        FunData:info.links,
                        range: true,
                        mack: true
                    }).show();
                    
                    

                  }
                  else{
                    //弹出新页面打开info.links
                    window.open(info.links);
                  }
                }
          }
      }); 
     }

     function showDates(id,data){
          var data = data.split(',');
          var datacon ='';
          for(var i=0;i<data.length;i++){
             if(!data[i]==""){
               datacon+=data[i]+'\n\n';
             }
          }
          var con ='<div class="plDowBox">将下列链接复制到迅雷等下载工具批量下载,这些链接24小时内有效,请及时下载！<textarea class="">'+datacon+'</textarea></div><div style="margin:0 15px 0 0;" id="cpId" class="WJButton wj_blue fr">复制</div>';
          $('#'+id+' .loaddiv').html(con);
          
          setTimeout(function(){
             var clipLink1 = new CopyLink('.plDowBox textarea','cpId');
          },1000);
     }
      
      //批量下载复制方法

      
      function copyToClipboard() {

          if(window.clipboardData) {
          window.clipboardData.setData("Text", $('.copylink span').text());
            loadMack({off:'on',Limg:0,text:'复制成功',set:1500});
          }
          else{
             clipLink1.setText($('.copylink span').text());
          }
          return false;

      }
      function CopyLink(setObj,glueObj){
         

          this.copyLinkInitOld=function(setObj,glueObj){
             this.Fun=new ZeroClipboard.Client();
            if(!window.clipboardData){
              var _this= this;
              
              this.Fun.setHandCursor(true);
              this.Fun.setText($(setObj).text());
              //addEventListener( "mouseover", _this.reset_copy_text);
              this.Fun.addEventListener("complete", _this.copy_success);
              this.Fun.glue(glueObj);
              
              $(window).resize(function(){_this.Fun.reposition();});

            }

            return false;
          }

          this.reset_copy_text=function(){
            this.Fun.setText($(setObj).text());
          }
          this.copy_success=function(){
            loadMack({off:'on',Limg:0,text:'复制成功',set:1500});
            return false;
          }

          return this.copyLinkInitOld(setObj,glueObj);

      }

      function unbindCopyLink(){if(!window.clipboardData){clipLink1.destroy();}}
      //批量下载复制方法---- end ----

function GetAbsPosition(obj) 
{ 
var curleft = 0, curtop = 0; 
do { 
curleft += obj.offsetLeft; 
curtop += obj.offsetTop; 
} while (obj = obj.offsetParent); 
return [curleft,curtop]; 
} 
function ShowFloatingImage(image, width, height) 
{ 
var id = "trailimageid"; 
var newdiv = document.getElementById(id); 
if(newdiv == null) 
{ 
newdiv = document.createElement('div'); 
newdiv.setAttribute('id',id); 
newdiv.setAttribute('onmouseout', "HideElement('"+id+"');"); 
document.body.appendChild(newdiv); 
} 
newdiv.innerHTML = '<img src='+image.src+ ' width='+(image.width + 120) + ' />'; 
var absPos = GetAbsPosition(image); 
newdiv.style.position = "absolute"; 
newdiv.style.left = absPos[0] + 50+"px"; 
newdiv.style.top = absPos[1] - 120-30+"px"; 
newdiv.style.display="block"; 
} 
function HideElement(id) 
{ 
var elem = document.getElementById(id); 
elem.style.display="none"; 
} 

function show_score_info(chart_type, chart_status, table_status){
  var score_info = get_score_data(chart_type, chart_status, table_status);
  if(score_info == null)
    return;
  show_chart(score_info, "area_score_info", "pie", "show_percent", "测评分数区间比例");
  show_chart(score_info, "basic_score_info", "column", "show_value", "测评分数分布图");
}

function show_chart(score_info, info_type, chart_type, display_type, title){
  var chart_options = get_chart_base_options(info_type, chart_type, display_type);
  chart_options.colors = ['#33aedc', '#90c689', '#fbe07f', '#ffb47f', '#f1917f', '#a09dd3', '#7fd1eb', '#7fb4eb', '#b6dda2', '#ff7f7f'];
  //展示数据表数据
  // if(score_info.table_data != null)
  // {
  //     var table_width = 650;
  //     var str_table_html = "<table class='Gls' style='width:"+(table_width-5)+"px' ><tbody class='Gls_list'>";
  //     var rspd_count;
  //     for(var r=0;r<score_info.table_data.length;r++)
  //     {
  //       str_table_html += "<tr>";
  //       var row_item = score_info.table_data[r];
  //       //last row
  //       if(r==score_info.table_data.length-1)
  //       {
  //         var colspan = score_info.table_data[r-1].length*2;//取上一行来计算跨度
  //         str_table_html += "<td  align='left' colspan='" + colspan + "'> " + row_item[0] + '：' + row_item[1];
  //         str_table_html += "</td>";
  //       }
  //       else
  //       {
  //         for(var c=0;c<row_item.length;c++)
  //         {
  //           if(row_item.length == 1)
  //             rspd_count = row_item[c];
  //           else
  //           {
  //             if(c==row_item.length-1)
  //               str_table_html += "<td style='width:80px'>" + row_item[c] + "</td>" ;
  //             else if(c===0)
  //               str_table_html += "<td style='width:120px'>" + row_item[c] + "</td>" ;
  //             else
  //               str_table_html += "<td style='width:50px'>" + row_item[c] + "</td>" ;
  //           }
  //         }
  //       }
  //       str_table_html += "</tr>";
  //     }
  //     str_table_html += "</tbody></table>";
  //     if(rspd_count != null)
  //     {
  //       str_table_html += "<br/><div>"+rspd_count+"</div>";
  //     }
  //     $("#data_container_score_info").html(str_table_html);
  // }
  chart_options.title.text = title;
  chart_options.subtitle.text = "答题人数" + score_info.rspd_count;
  if(chart_type == 'pie') {
    chart_options.series[0] = {
      'data': score_info.pie_data,
      'name':''
    };
  } else if(chart_type == 'donut') {
    chart_options.series[0] = {
      'data': score_info.pie_data,
      'innerSize': '50%'
    };
  } else if(chart_type == 'column') {
    chart_options.xAxis.categories = score_info.every_label;
    chart_options.xAxis.labels.step = score_info.step;
    chart_options.xAxis.labels.staggerLines = 1;
    if(score_info.every_label.length<=3)
    {
      chart_options.plotOptions.column.pointWidth = 50;//当选项小于3项时，宽度设置为50
    }
    //多系列情况下系列项里面有data这个属性，单系列没有
    if(score_info.every_datas[0].data != null)
    {
      if(score_info.every_datas.length>3)
        chart_options.plotOptions.column.dataLabels.enabled = false;
      chart_options.series = score_info.every_datas;
    }
    else
      chart_options.series[0].data = score_info.every_datas;
  } else if(chart_type == 'bar') {
    chart_options.xAxis.categories = score_info.labels;
    // if(score_info.labels.length<=3)
    // {
    //   chart_options.plotOptions.bar.pointWidth = 50;//当选项小于3项时，宽度设置为50
    // }
    if(score_info.datas[0].data != null)
    {
      if(score_info.datas.length>3)
        chart_options.plotOptions.bar.dataLabels.enabled = false;
      chart_options.series = score_info.datas;
    }
    else
    {
      chart_options.series[0] = {
      'data': score_info.datas,
      'name': '选中人数'
    };
    }
  }

  var chart = new Highcharts.Chart(chart_options);
  //渲染完之后默认设置导出图标隐藏
  $("#container_"+info_type).find(".highcharts-button").hide();
}

function get_score_data(chart_type, chart_status, table_status){
  var score_info = chart_info_dict[['score_info', chart_type, chart_status, table_status]];
  if(score_info == null) {
    $("#get_score_info_form input[name='chart_type']").val(chart_type);
    $("#get_score_info_form input[name='chart_status']").val(chart_status);
    $("#get_score_info_form input[name='table_status']").val(table_status);
    ajaxSubmit($("#get_score_info_form"));
    return null;
  } else {
    return score_info;
  }
}

function get_score_info_form_callback(info){
  if(info)
  {
    chart_info_dict[['score_info', info.chart_type, info.chart_status, info.table_status]] = info;
    show_score_info(info.chart_type, info.chart_status, info.table_status);
  }
}

function score_chart_type_changed(obj){
  var chart_type = $(obj).attr('typename');
  $("#get_score_info_form input[name='chart_type']").val(chart_type);
  var chart_status = $("#get_score_info_form input[name='chart_status']").val();
  var table_status = $("#get_score_info_form input[name='table_status']").val();
  show_score_info(chart_type, chart_status, table_status);
}

function score_display_type_change(obj){
  if($(obj).attr("id") == "chart_status_score")
  {
    $("#get_score_info_form input[name='chart_status']").val($(obj).attr("dispname"));
  }
  else if($(obj).attr("id") == "table_status_score")
  {
    $("#get_score_info_form input[name='table_status']").val($(obj).attr("dispname"));
  }
  var chart_type = $("#get_score_info_form input[name='chart_type']").val();
  var chart_status = $("#get_score_info_form input[name='chart_status']").val();
  var table_status = $("#get_score_info_form input[name='table_status']").val();
  show_score_info(chart_type, chart_status, table_status);
  elememts_deal("score_info", $(obj).attr("dispname"), obj)
}