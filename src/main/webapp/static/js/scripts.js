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
    for(let i = 0; i < addButtons.length; i++) {
        addButtons.item(i).addEventListener("click", function(){
            let productId = addButtons.item(i).getAttribute("name");
            let params = {itemId: productId};
            $.post('/', $.param(params), function(){
                console.log("successful");
            });
            let addQuantity = parseInt(quantities.item(i).innerHTML) + 1;
            quantities.item(i).innerHTML = addQuantity;
        });
    }
}

sendItemId();
addOneItem();