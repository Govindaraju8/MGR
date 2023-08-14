$(document).ready(function() {
  let allProducts = null;
  let currentProducts = null;
  const itemsPerPage = 9;
  let filteredProducts = [];
  // Get products from JSON file
  $.getJSON("http://localhost:9011/ShoppingCart/GettingData", function(products) {
    allProducts = products;
    currentProducts = allProducts.slice(0, 9);
    displayProducts(currentProducts);
  });
const isSessionStorageAvailable = isStorageAvailable('sessionStorage');
function isStorageAvailable(type) {
  try {
    const storage = window[type];
    const x = '__storage_test__';
    storage.setItem(x, x);
    storage.removeItem(x);
    return true;
  } catch (e) {
    return false;
  }
}
  function displayProducts(products) {
    const productsList = $("#products-list");
    productsList.empty(); // Clear previous product cards
    products.forEach((product) => {
      const pCard = $("<div>").addClass("product-card col-md-6 col-lg-4");
      const cardBody = $("<div>").addClass("card-body");
      const pImage = $("<img>").addClass("product-image ").attr({
        src: product['IMAGE'],
        alt: product['NAME'],
      });
      const pName = $("<h5>").addClass("product-name card-title").text(product['NAME']);
      const pCategory = $("<p>").addClass("product-category card-text").text("Category: " + product['CATEGORY']);
      const pDescription = $("<p>").addClass("product-description card-text").text(product['DESCRIPTION']);
      const pPrice = $("<p>").addClass("product-price card-text ").text("Price: $" + product['PRICE']);
      const addToCartBtn = $("<button>").addClass("btn btn-success btn-block add-to-cart-btn").text("Add to Cart");
      cardBody.append(pImage, pName, pCategory, pDescription, pPrice, addToCartBtn);
      pCard.append(cardBody);
      productsList.append(pCard);
    });
  }
  // Event listener for the "Search" button
  $("#search-btn").click(function() {
    const searchText = $("#product-search").val().toLowerCase().trim();
    if (searchText === "") {
      displayProducts(currentProducts); // Show the current products when the search text is empty
    } else {
      const filteredProducts = allProducts.filter((product) =>
        product['NAME'].toLowerCase().includes(searchText) ||
        product['CATEGORY'].toLowerCase().includes(searchText) ||
        product['DESCRIPTION'].toLowerCase().includes(searchText)
      );
      displayProducts(filteredProducts);
    }
  });
  // Event listener for the search input field
  $("#product-search").on("input", function() {
    const searchText = $(this).val().toLowerCase().trim();
    const suggestionsContainer = $("#suggestions-container");
    suggestionsContainer.empty();
    if (searchText === "") {
      suggestionsContainer.hide();
    } else {
      const filteredProducts = allProducts.filter((product) =>
        product['NAME'].toLowerCase().includes(searchText) ||
        product['CATEGORY'].toLowerCase().includes(searchText) ||
        product['DESCRIPTION'].toLowerCase().includes(searchText)
      );
      filteredProducts.slice(0, 5).forEach((product) => {
        const suggestionItem = $("<div>").addClass("suggestion-item").text(product['NAME']);
        suggestionItem.on("click", function() {
          $("#product-search").val(product['NAME']);
          suggestionsContainer.hide();
          performSearch(); // Perform search on click of suggestion item
        });
        suggestionsContainer.append(suggestionItem);
      });
      if (filteredProducts.length > 0) {
        suggestionsContainer.show();
      } else {
        suggestionsContainer.hide();
      }
    }
  });
  // Event listener for the "Filter" button (example: filter by price less than 500)
  $("#filter-btn").click(function() {
     $("#filterModal").modal("show");
  });
  $("#apply-filter-btn").click(function() {
    const minPrice = parseFloat($("#min-price").val());
    const maxPrice = parseFloat($("#max-price").val());

    // Check if the entered values are valid numbers
    if (!isNaN(minPrice) && !isNaN(maxPrice) && minPrice <= maxPrice) {
      filteredProducts = allProducts.filter((product) => {
        const productPrice = parseFloat(product['PRICE']);
        return productPrice >= minPrice && productPrice <= maxPrice;
      });
      displayProductsByPage(1);
    } 
  });
  function displayProductsByPage(pageNumber) {
    // Calculate the start and end indexes for the current page
    const startIndex = (pageNumber - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    // Get the products for the current page
    const productsForPage = filteredProducts.slice(startIndex, endIndex);
    // Display the products on the page
    displayProducts(productsForPage);
    // Update pagination buttons
    updatePaginationButtons(pageNumber);
  }
  function updatePaginationButtons(currentPage) {
    const totalPages = Math.ceil(filteredProducts.length / itemsPerPage);
    let paginationHtml = '<ul class="pagination">';
    for (let i = 1; i <= totalPages; i++) {
      paginationHtml += `<li class="page-item${i === currentPage ? ' active' : ''}"><a class="page-link" href="#">${i}</a></li>`;
    }
    paginationHtml += '</ul>';
    $("#pagination-container").html(paginationHtml);
    // Add click event listener to pagination buttons
    $(".page-link").click(function(e) {
      e.preventDefault();
      const pageNumber = parseInt($(this).text(), 10);
      displayProductsByPage(pageNumber);
    });
  }
  // // Cart functionality
  // const cartItems = [];
  $("#cart-btn").click(function() {
    const cartItemsStr = cartItems.map((item) => `${item.name} - Price: $${item.price}`).join("\n");
  });
let cartItems = [];

// Load cart items from session storage if available
if (isSessionStorageAvailable) {
  const storedCartItems = sessionStorage.getItem('cartItems');
  if (storedCartItems) {
    cartItems = JSON.parse(storedCartItems);
    updateCartItems();
    updateCartTotal();
    updateCartButton();
  }
}
  $(document).on("click", ".add-to-cart-btn", function() {
    const productCard = $(this).closest(".product-card");
    const productName = productCard.find(".product-name").text();
    const productPrice = parseFloat(productCard.find(".product-price").text().replace("Price: $", ""));
    // Check if the product is already in the cart
    const existingProduct = cartItems.find(item => item.name === productName && item.price === productPrice);
    if (existingProduct) {
      showToast("Already in Cart", `${productName} is already in the cart.`);
    } else {
      // If the product is not in the cart, add it
      cartItems.push({ name: productName, price: productPrice, quantity: 1 });
      showToast("Added to Cart", `${productName} - Price: $${productPrice}`);
    }
    // Save cart items to session storage
    if (isSessionStorageAvailable) {
		var newProduct = {
			"newProduct":JSON.stringify(cartItems)
		};
      $.ajax({
          url: 'http://localhost:9011/ShoppingCart/Cart',
          data: newProduct, 
          contentType: 'application/json',
          success: function(response) {
            console.log('Success:', response);
          },
          error: function(error) {
            console.log('Error:', error);
          }
        	});
     }

    updateCartItems();
    updateCartTotal();
    updateCartButton();
  });
  // Function to show Toast notification
  function showToast(title, message) {
    const toastContainer = $("#toast-container");
    const toast = $("<div>").addClass("toast").attr("role", "alert").attr("aria-live", "assertive").attr("aria-atomic", "true");
    const toastHeader = $("<div>").addClass("toast-header");
    const strong = $("<strong>").addClass("me-auto").text(title);
    const button = $("<button>").addClass("btn-close").attr("type", "button").attr("data-bs-dismiss", "toast").attr("aria-label", "Close");
    const toastBody = $("<div>").addClass("toast-body").text(message);
    toastHeader.append(strong, button);
    toast.append(toastHeader, toastBody);
    toastContainer.append(toast);
    // Show the toast
    $(".toast").toast({ delay: 1000 }).toast('show');
    // Remove the toast after it's hidden
    $(".toast").on("hidden.bs.toast", function() {
      $(this).remove();
    });
  }
  // Event listener for the "Remove" button in the cart modal
  $(document).on("click", ".remove-from-cart-btn", function() {
    const productName = $(this).data("product-name");
    const productPrice = parseFloat($(this).data("product-price"));
    // Remove the item from the cart
    const existingProductIndex = cartItems.findIndex(item => item.name === productName && item.price === productPrice);
    if (existingProductIndex !== -1) {
      cartItems.splice(existingProductIndex, 1);
    }
    // Save cart items to session storage
if (isSessionStorageAvailable) {
  var newProduct = {
			"newProduct":JSON.stringify(cartItems)
		};
      $.ajax({
          url: 'http://localhost:9011/ShoppingCart/Cart',
          data: newProduct, 
          contentType: 'application/json',
          success: function(response) {
            console.log('Success:', response);
          },
          error: function(error) {
            console.log('Error:', error);
          }
        	});
     }

    updateCartItems();
    updateCartTotal();
    updateCartButton();
  });
  // Function to update the cart items display in the cart modal
  function updateCartItems() {
    const cartItemsDiv = $("#cart-items");
    cartItemsDiv.empty();
    if (cartItems.length === 0) {
      cartItemsDiv.html('<p>Your cart is empty.</p>');
    } else {
      cartItems.forEach((item) => {
        const cartItemDiv = $("<div>");
        const productName = $("<h4>").text(item.name);
        const productPrice = $("<p>").text("Price: $" + item.price);
        const removeButton = $("<button>").addClass("btn btn-danger remove-from-cart-btn")
          .text("Remove").data("product-name", item.name).data("product-price", item.price);
        const hr = $("<hr>");
        cartItemDiv.append(productName, productPrice, removeButton, hr);
        cartItemsDiv.append(cartItemDiv);
      });
    }
  }
  // Function to update the cart total price
  function updateCartTotal() {
    const total = cartItems.reduce((acc, item) => acc + item.price * item.quantity, 0);
    $('#cart-total-price').text(`$${total.toFixed(2)}`);
  }
  // Function to update the cart button's text with the number of items in the cart
  function updateCartButton() {
    const cartButton = $('#cart-btn');
    if (cartItems.length === 0) {
      cartButton.text('Cart');
    } else {
      cartButton.text(`Cart (${cartItems.length})`);
    }
  }
});
