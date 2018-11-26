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

       let editShoppingCart = document.getElementById("edit-shopping-cart");
       let editLink = document.getElementById("edit-link");
       editShoppingCart.removeChild(editLink);

       var basicElement = document.getElementById("basic");
       var title = document.createElement("h1");
       title.setAttribute("class", "pt-3");
       var titleText = document.createTextNode("Payment");
       title.appendChild(titleText);
       basicElement.appendChild(title);
       var break1 = document.createElement("br");
       basicElement.appendChild(break1);

       var div1outside = document.createElement("div");
       div1outside.setAttribute("class", "input-group mb-3");
       var div1inside = document.createElement("div");
       div1inside.setAttribute("class","input-group-prepend");
       var span1 = document.createElement("span");
       span1.setAttribute("class", "input-group-text");
       span1.setAttribute("id", "basic-addon3");
       span1.innerHTML = "Credit card number";
       div1inside.appendChild(span1);
       var input1 = document.createElement("input");
       input1.setAttribute("type", "text");
       input1.setAttribute("id", "creditCardNumber");
       input1.setAttribute("aria-describedly", "basic-addon3");
       input1.setAttribute("class", "form-control");
       div1outside.appendChild(div1inside);
       div1outside.appendChild(input1);
       basicElement.appendChild(div1outside);

       var div2outside = document.createElement("div");
       div2outside.setAttribute("class", "input-group mb-3");
       var div2inside = document.createElement("div");
       div2inside.setAttribute("class","input-group-prepend");
       var span2 = document.createElement("span");
       span2.setAttribute("class", "input-group-text");
       span2.setAttribute("id", "basic-addon3");
       span2.innerHTML = "Expiration date";
       div2inside.appendChild(span2);
       var input2 = document.createElement("input");
       input2.setAttribute("type", "text");
       input2.setAttribute("id", "expirationDate");
       input2.setAttribute("aria-describedly", "basic-addon3");
       input2.setAttribute("class", "form-control");
       div2outside.appendChild(div2inside);
       div2outside.appendChild(input2);
       basicElement.appendChild(div2outside);

       var div3outside = document.createElement("div");
       div3outside.setAttribute("class", "input-group mb-3");
       var div3inside = document.createElement("div");
       div3inside.setAttribute("class","input-group-prepend");
       var span3 = document.createElement("span");
       span3.setAttribute("class", "input-group-text");
       span3.setAttribute("id", "basic-addon3");
       span3.innerHTML = "Security code";
       div3inside.appendChild(span3);
       var input3 = document.createElement("input");
       input3.setAttribute("type", "text");
       input3.setAttribute("id", "securityCode");
       input3.setAttribute("aria-describedly", "basic-addon3");
       input3.setAttribute("class", "form-control");
       div3outside.appendChild(div3inside);
       div3outside.appendChild(input3);
       basicElement.appendChild(div3outside);

       var payButton = document.createElement("button");
       payButton.setAttribute("id", "paymentButton");
       payButton.setAttribute("class", "btn btn-warning");
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
