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
            if(subtractQuantity==0){
                location.reload(true);
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

sendItemId();
addOneItem();
subtractOneItem();
deleteItem();
