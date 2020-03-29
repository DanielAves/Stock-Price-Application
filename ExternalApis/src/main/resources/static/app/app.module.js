function getDataFromAPI1() {
    $.ajax({
        url: "/getfromapi1",
        type: "GET",
        success: function (data) {
            if (data == "invalid company"){
                document.getElementById("errorMsg").innerHTML = "This company has not returned a stock symbol, try something like 'Amazon'";
            }
            else{
                document.getElementById("result").innerHTML = data;
                document.getElementById("errorMsg").innerHTML = "";
                getDataFromAPI2();
            }
        }, error: function (jqXHR, textStatus, errorThrown) {
        }
    });
}
function getDataFromAPI2() {
    $.ajax({
        url: "/getfromapi2",
        type: "GET",
        success: function (data) {
            //window.alert(data);
            $.each(data, function (k, v) {
                if(v == 1 && k == "invalid company"){
                    document.getElementById("errorMsg").innerHTML = "This stock symbol hasn't returned data from Alphavantage.com";
                }
                if(v == 1 && k != "invalid company"){
                    document.getElementById("CDate").innerHTML = k;
                }
                if (v ==2){
                    document.getElementById("CPrice").innerHTML = k;
                }
                if (v == 3){
                    document.getElementById("CTime").innerHTML = k;
                }
                if (v == 4){
                    document.getElementById("PDate").innerHTML = k;
                }
                if (v == 5){
                    document.getElementById("PPrice").innerHTML = k;
                }
                if (v == 6){
                    document.getElementById("PTime").innerHTML = k;
                }
            })
            getDataFromAPI3();
        }, error: function (jqXHR, textStatus, errorThrown) {
        }
    });
}

function getDataFromAPI3() {
    $.ajax({
        url: "/getfromapi3",
        type: "GET",
        success: function (data) {
            //window.alert(data);
            document.getElementById("result4").innerHTML = data;
        }, error: function (jqXHR, textStatus, errorThrown) {
        }
    });
}


function postDataFromAPI() {

    var test = $("#stockCodeInput").val()

    $.ajax({
        type: "POST",
        url: "/postdata",
        headers: {
            "Content-Type": "application/json"
        },
        data: test,
        success: function (data) {
            console.log("POST API RESPONSE::"+data);
            getDataFromAPI1();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            window.alert(errorThrown + textStatus);
        }
    });
}