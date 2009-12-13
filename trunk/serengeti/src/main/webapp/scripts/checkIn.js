google.load('search', '1');

var lSearch;
var userLoc;

$(function() {
        $("#locList").hide();
        $("#locSearch").hide();
        lSearch = new google.search.LocalSearch();
        lSearch.setSearchCompleteCallback(this, searchComplete);
        lSearch.setAddressLookupMode(google.search.LocalSearch.ADDRESS_LOOKUP_DISABLED);
        userLoc = google.loader.ClientLocation;

        getNearbyLocations();
        
});

function checkIn(sessionId) {
    var request = $.get("checkIn", {userId : sessionId, teamId : $("select#teamSelect").val(), 
        locationId : $("select#locSelect").val()}, function(data) {
            var jsonData = JSON.parse(request.responseText);
            if (jsonData.overtaken == "true") {
                showPopup();
            } 
            $("#checkIn").replaceWith("<p>Thank you for checking in.</p><p>Your current location: "  + jsonData.visit.location.name + "</>");
        });
    $("#checkIn").fadeOut("slow");
    $("#thanks").fadeIn("slow"); 
}

function showSearch(){
        $("#locSearch").fadeIn("slow");
        $("#notHere").fadeOut("slow");
}

function searchLoc() {
        showLoader();     
        lSearch.execute($("#searchText").val() + " " + userLoc.address.city);
        $("#locSelect").empty();
        $("#locSearch").fadeOut("slow");
        $("#locList").append("<a href=../location/create>Add Location</a>");
        hideLoader();
}

function searchComplete() {
        var locSelect = $("#locSelect");
        if (lSearch.results && lSearch.results.length > 0) {
            var results = lSearch.results;
            jQuery.each(results, function(i, val) {
                 var number = "";
                 if (val.phoneNumbers) {
                     number = val.phoneNumbers[0].number;
                 }
                 var request = $.get("../location/handleResult", {name : val.titleNoFormatting, lat : parseFloat(val.lat),
                    lng : parseFloat(val.lng) , street : val.streetAddress, city : val.city,
                    state : val.region, phone : number},
                        function(data) {
                            var jsonData = JSON.parse(request.responseText);
                            locSelect.append("<option value=" + jsonData.id + ">" + jsonData.name + "</option>");
                        });
            });
        }
        else {
            locSelect.replaceWith("<p>Either we couldn't find any locations by that name or you couldn't possibly be there....cheater.</p>");
        }
}

function getNearbyLocations() {
        var request = $.get("../location/getNearbyLocations", {latitude : userLoc.latitude,
            longitude : userLoc.longitude}, function(data) {
                var jsonData = JSON.parse(request.responseText);
                populateLocations(jsonData.locations);
            });
        hideLoader();
}

function showLoader() {
        $("#loader").show();
        $("#locList").hide();  
}
    
function hideLoader() {
        $("#locList").show();
        $("#loader").hide();
}
    
function populateLocations(nearby) {
        var locSelect = $("#locSelect");
        jQuery.each(nearby, function(i, val) {
            locSelect.append("<option value=" + val.id + ">" + val.name + "</option>");
            });
}