function postJsonData(data) {
    fetch('/', {
        method: 'POST',
        body: JSON.stringify(data), // data can be `string` or {object}!
        headers:{
            'Content-Type': 'application/json'
        }
    }).then(res => res.json())
        .then(response => console.log('Success:', JSON.stringify(response)))
        .catch(error => console.error('Error:', error));
}


let addToCartButtons = document.getElementsByClassName("addToCart");

for (let i = 0; i < addToCartButtons.length; i++) {
    let cartButton = addToCartButtons[i];
    cartButton.addEventListener('click', function () {
        let productId = cartButton.getAttribute('name');
        postJsonData(productId);
    })
}

