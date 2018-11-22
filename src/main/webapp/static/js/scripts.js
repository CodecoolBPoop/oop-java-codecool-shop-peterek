function sendItemId() {
    let addButtons = document.getElementsByClassName("buy");
    let numOfItems = document.getElementById("numOfItems");
    for (let button of addButtons) {
        button.addEventListener("click", function () {
            let productId = button.getAttribute("id");
            let params = {itemId: productId};
            $.post('/', $.param(params), function () {
                console.log("successful");
            });
            let numberOfItems = parseInt(numOfItems.innerHTML) + 1;
            numOfItems.innerHTML = numberOfItems;
        });
    }
}

function addOneItem(){
    let addButtons = document.getElementsByClassName("addItem");
    let quantities = document.getElementsByClassName("quantity");
    let fullPrices = document.getElementsByClassName("full");
    let price = document.getElementById("price");
    for(let i = 0; i < addButtons.length; i++) {
        addButtons.item(i).addEventListener("click", function(){
            let productId = addButtons.item(i).getAttribute("name");
            let params = {itemId: productId};
            $.post('/', $.param(params), function(){
                console.log("successful");
            });
            let addQuantity = parseInt(quantities.item(i).innerHTML) + 1;
            quantities.item(i).innerHTML = addQuantity;

            let addPrice = parseInt(fullPrices.item(i).getAttribute("name"));
            let fullPrice = parseFloat(addPrice * addQuantity).toFixed(1);
            let fullPriceText = fullPrice + ' USD';
            fullPrices.item(i).innerHTML = fullPriceText;

            let priceValue = parseInt(price.innerHTML);
            let newPrice = parseFloat(priceValue + addPrice).toFixed(1);
            price.innerHTML = newPrice + ' USD';
        });
    }
}

function subtractOneItem(){
    let subtractButtons = document.getElementsByClassName("subtractItem");
    let quantities = document.getElementsByClassName("quantity");
    let fullPrices = document.getElementsByClassName("full");
    let price = document.getElementById("price");
    for(let i = 0; i < subtractButtons.length; i++) {
        subtractButtons.item(i).addEventListener("click", function(){
            let productId = subtractButtons.item(i).getAttribute("name");
            let type = 1;
            let params = {itemId: productId, type: type};
            $.post('/shopping-cart', $.param(params), function(){
                console.log("successful");
            });
            let subtractQuantity = parseInt(quantities.item(i).innerHTML) - 1;
            quantities.item(i).innerHTML = subtractQuantity;
            if(subtractQuantity == 0){
                location.reload(true);
            } else {
                let addPrice = parseInt(fullPrices.item(i).getAttribute("name"));
                let fullPrice = parseFloat(addPrice * subtractQuantity).toFixed(1);
                let fullPriceText = fullPrice + ' USD';
                fullPrices.item(i).innerHTML = fullPriceText;

                let priceValue = parseInt(price.innerHTML);
                let newPrice = parseFloat(priceValue - addPrice).toFixed(1);
                price.innerHTML = newPrice + ' USD';
            }
        });
    }
}

function deleteItem(){
    let deleteButtons = document.getElementsByClassName("deleteBtn");
    for(let deleteButton of deleteButtons) {
        deleteButton.addEventListener("click", function(){
            let productId = deleteButton.getAttribute("name");
            let type = 2;
            let params = {itemId: productId, type: type};
            $.post('/shopping-cart', $.param(params), function(){
                console.log("successful");
            });
            location.reload(true);
        });
    }
}

function deliveryAddress(){
    let nextBtn = document.getElementById("next");
    nextBtn.addEventListener("click", function(){
       let type = 1;
       let firstName = document.getElementById("firstName").value;
       let lastName = document.getElementById("lastName").value;
       let address = document.getElementById("address").value;
       let postalCode = document.getElementById("postalCode").value;
       let city = document.getElementById("city").value;
       let country = document.getElementById("country").value;
       let phoneNumber = document.getElementById("phoneNumber").value;
       let email = document.getElementById("email").value;
       let params = {
           type: type,
           firstName: firstName,
           lastName: lastName,
           address: address,
           postalCode: postalCode,
           city: city,
           country: country,
           phoneNumber: phoneNumber,
           email: email};
       $.post('/payment', $.param(params), function(){
           console.log("success");
       });
       let element = document.getElementById("deliveryAddressForm");
       element.parentNode.removeChild(element);

       var basicElement = document.getElementById("basic");
       var title = document.createElement("h1");
       var titleText = document.createTextNode("Payment");
       title.appendChild(titleText);
       basicElement.appendChild(title);
       var break1 = document.createElement("br");
       basicElement.appendChild(break1);
       var p1 = document.createElement("p");
       var p1Text = document.createTextNode("Credit card number");
       p1.appendChild(p1Text);
       basicElement.appendChild(p1);
       var input1 = document.createElement("input");
       input1.setAttribute("type", "text");
       input1.setAttribute("id", "creditCardNumber");
       input1.setAttribute("value", "");
       basicElement.appendChild(input1);
       var break2 = document.createElement("br");
       basicElement.appendChild(break2);
       var break3 = document.createElement("br");
       basicElement.appendChild(break3);
       var p2 = document.createElement("p");
       var p2Text = document.createTextNode("Expiration date");
       p2.appendChild(p2Text);
       basicElement.appendChild(p2);
       var input2 = document.createElement("input");
       input2.setAttribute("type", "text");
       input2.setAttribute("id", "expirationDate");
       input2.setAttribute("value", "");
       basicElement.appendChild(input2);
       var break4 = document.createElement("br");
       basicElement.appendChild(break4);
       var break5 = document.createElement("br");
       basicElement.appendChild(break5);
       var p3 = document.createElement("p");
       var p3Text = document.createTextNode("Security code");
       p3.appendChild(p3Text);
       basicElement.appendChild(p3);
       var input3 = document.createElement("input");
       input3.setAttribute("type", "text");
       input3.setAttribute("id", "securityCode");
       input3.setAttribute("value", "");
       basicElement.appendChild(input3);
       var break6 = document.createElement("br");
       basicElement.appendChild(break6);
       var break7 = document.createElement("br");
       basicElement.appendChild(break7);
       var payButton = document.createElement("button");
       payButton.setAttribute("id", "paymentButton");
       payButton.innerHTML = "Pay";
       basicElement.appendChild(payButton);
       payment();
    });
}

function payment(){
    let button = document.getElementById("paymentButton");
    button.addEventListener("click", function () {
        console.log("button jó");
        let type = 2;
        let creditCardNumber = document.getElementById("creditCardNumber").value;
        let expirationDate = document.getElementById("expirationDate").value;
        let securityCode = document.getElementById("securityCode").value;
        let params = {
            type: type,
            creditCardNumber: creditCardNumber,
            expirationDate: expirationDate,
            securityCode: securityCode};
        $.post('/payment', $.param(params), function(){
            console.log("success");
        });
        let element1 = document.getElementById("body1");
        element1.parentNode.removeChild(element1);
        let element2 = document.getElementById("body2");
        element2.parentNode.removeChild(element2);

        var basicElement = document.getElementById("headBottom");

        var firstDiv = document.createElement("div");
        firstDiv.setAttribute("class", "col-lg-12");
        basicElement.appendChild(firstDiv);

        var title = document.createElement("h1");
        var titleText = document.createTextNode("Thank you for your payment! See you soon! Goodbye!");
        title.appendChild(titleText);
        firstDiv.appendChild(title);
        var break1 = document.createElement("br");
        firstDiv.appendChild(break1);
        var backToIndexHtmlButton = document.createElement("a");
        backToIndexHtmlButton.setAttribute("href", "/");
        backToIndexHtmlButton.setAttribute("class", "btn btn-warning");
        backToIndexHtmlButton.innerHTML = "Back to the shop";
        firstDiv.appendChild(backToIndexHtmlButton);
    });
}

sendItemId();
addOneItem();
subtractOneItem();
deleteItem();
deliveryAddress();
