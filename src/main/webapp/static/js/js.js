function buyItem (){
    let addButtons = document.getElementsByClassName("buy");
    for (let addButton of addButtons){
        addButton.addEventListener('click', function(){
            let itemId = addButton.getAttribute("id");
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "/", true);
            xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            var input = JSON.stringify({
                "Id": itemId,
            });
            xhttp.onload = function () {
                // do something to response
                console.log("Fasza!");
            };
            xhttp.send(input);
        });
    }
}

buyItem();
