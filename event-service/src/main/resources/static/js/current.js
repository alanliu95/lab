// function refresh() {
//     window.setInterval(getEvent,2000);
// }

var devs;
window.onload=function(){
    var httpReq= new XMLHttpRequest();
    httpReq.onreadystatechange=
        function () {
            if (httpReq.readyState==4 && httpReq.status==200)
            {
                var slt=document.getElementById("site");
                //document.getElementById("debug").innerHTML=httpReq.responseText;
                devs=JSON.parse(httpReq.responseText);
                for(var k in devs){
                    var objOption=document.createElement("OPTION");
                    objOption.text=k;
                    objOption.value=k;
                    slt.add(objOption);
                }
                setDev();
            }
        };
    httpReq.open("get","/data/devices",true);
    httpReq.send();
}
option = {
    tooltip : {
        formatter: "{a} <br/>{b} : {c}%"
    },
    toolbox: {
        feature: {
            restore: {},
            saveAsImage: {}
        }
    },
    series: [
        {
            name: '业务指标',
            type: 'gauge',
            detail: {formatter:'{value}%'},
            data: [{value: 50, name: '完成率'}]
        }
    ]
};
var myChart = echarts.init(document.getElementById('main'));
myChart.setOption(option);

var devName;

function recordHandler(){
    var httpReq= new XMLHttpRequest();
    httpReq.onreadystatechange=
        function () {
            if (httpReq.readyState==4 && httpReq.status==200)
            {
                var text=httpReq.responseText;
                console.debug(text);
                var recJson=JSON.parse(text);
                option.series[0].data[0].value = recJson.cpu;
                myChart.setOption(option, true);
            }
        };
    httpReq.open("get","/devices/"+devName,true);
    httpReq.send();

}
//var timerId;
function getRecords() {
    //setInterval(null,2000);
    //将表格数值清零
    devName =document.getElementById("device").value;
    setInterval(recordHandler,2000);
}

function setDev() {
    var siteSlt=document.getElementById("site");
    var index=siteSlt.selectedIndex;
    //if(index==0) return;
    var siteVal=siteSlt.options[index].text;
    var slt=document.getElementById("device");
    //console.debug(siteVal);
    slt.length=0;
    //sltCity[i+1]=new Option(provinceCity[i],provinceCity[i]);
    var arrDev=devs[siteVal];
    //console.debug(devs[siteVal][0]);
    for(var i=0;i<arrDev.length;i++){
        //console.debug(k);
        slt[i]=new Option(arrDev[i],arrDev[i]);
    }
}